package fusee.legitmods.blur;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;

import javax.annotation.Nonnull;

import org.apache.commons.lang3.ArrayUtils;

import com.google.common.base.Throwables;
import com.google.common.eventbus.Subscribe;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.client.resources.SimpleReloadableResourceManager;
import net.minecraft.client.shader.Shader;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.client.shader.ShaderUniform;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

@Mod(modid = "blur", name = "Blur", version = "1.0", acceptedMinecraftVersions = "[1.8.9]", clientSideOnly = true, guiFactory = "fusee.legitmods.blur.BlurGuiFactory")
public class Blur
{
    @Instance("blur")
    public static Blur instance;
    public Configuration config;
    private String[] blurExclusions;
    private Field _listShaders;
    private long start;
    public int radius;
    private int fadeTime, colorFirst, colorSecond;
    
    @Nonnull
    private ShaderResourcePack dummyPack = new ShaderResourcePack();
    
    public Blur()
    {
        ((List<ShaderResourcePack>) ReflectionHelper.getPrivateValue(Minecraft.class, Minecraft.getMinecraft(), new String[] {"field_110449_ao", "defaultResourcePacks"})).add(this.dummyPack);
    }
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(this);
        
        ((SimpleReloadableResourceManager) Minecraft.getMinecraft().getResourceManager()).registerReloadListener((IResourceManagerReloadListener) this.dummyPack);
        
        this.config = new Configuration(new File(event.getModConfigurationDirectory(), "BlurMod.cfg"));
        saveConfig();
    }
    
    private void saveConfig()
    {
        this.blurExclusions = this.config.getStringList("guiExclusions", "general", new String[] {GuiChat.class.getName()}, "A list of classes to be excluded from the blur shader.");
        this.fadeTime = this.config.getInt("fadeTime", "general", 200, 0, 2147483647, "The time it takes for the blur to fade in, in ms.");
        
        int r = this.config.getInt("radius", "general", 12, 1, 100, "The radius of the blur effect. This controls how \"strong\" the blur is.");
        
        if (r != this.radius)
        {
            this.radius = r;
            this.dummyPack.onResourceManagerReload(Minecraft.getMinecraft().getResourceManager());
            
            if ((Minecraft.getMinecraft()).theWorld != null)
            {
                (Minecraft.getMinecraft()).entityRenderer.stopUseShader();
            }
        }
        
        this.colorFirst = Integer.parseUnsignedInt(this.config.getString("gradientStartColor", "general", "75000000", "The start color of the background gradient. Given in ARGB hex."), 16);
        
        this.colorSecond = Integer.parseUnsignedInt(this.config.getString("gradientEndColor", "general", "75000000", "The end color of the background gradient. Given in ARGB hex."), 16);
        
        this.config.save();
    }
    
    @Subscribe
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.modID.equals("blur"))
        {
            saveConfig();
        }
    }
    
    @SubscribeEvent
    public void onGuiChange(GuiOpenEvent event)
    {
        if (this._listShaders == null)
        {
            this._listShaders = ReflectionHelper.findField(ShaderGroup.class, new String[] {"field_148031_d", "listShaders"});
        }
        
        if ((Minecraft.getMinecraft()).theWorld != null)
        {
            EntityRenderer er = (Minecraft.getMinecraft()).entityRenderer;
            boolean excluded = (event.gui == null || ArrayUtils.contains((Object[]) this.blurExclusions, event.gui.getClass().getName()));
            
            if (!er.isShaderActive() && !excluded)
            {
                er.loadShader(new ResourceLocation("shaders/post/fade_in_blur.json"));
                this.start = System.currentTimeMillis();
            } else if (er.isShaderActive() && excluded) {
                er.stopUseShader();
            }
        }
    }
    
    private float getProgress()
    {
        return Math.min((float) (System.currentTimeMillis() - this.start) / this.fadeTime, 1.0F);
    }
    
    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent event)
    {
        if (event.phase == TickEvent.Phase.END && (Minecraft.getMinecraft()).currentScreen != null && (Minecraft.getMinecraft()).entityRenderer.isShaderActive())
        {
            ShaderGroup sg = (Minecraft.getMinecraft()).entityRenderer.getShaderGroup();
            
            try {
                
                List<Shader> shaders = (List<Shader>) this._listShaders.get(sg);
                
                for (Shader s : shaders)
                {
                    ShaderUniform su = s.getShaderManager().getShaderUniform("Progress");
                    
                    if (su != null) 
                    {
                        su.set(getProgress());
                    }
                }
                
            } catch (IllegalArgumentException e) {
                Throwables.propagate(e);
            } catch (IllegalAccessException e) {
                Throwables.propagate(e);
            }
        }
    }
    
    public static int getBackgroundColor(boolean second)
    {
        int color = second ? instance.colorSecond : instance.colorFirst;
        int a = color >>> 24;
        int r = color >> 16 & 0xFF;
        int b = color >> 8 & 0xFF;
        int g = color & 0xFF;
        float prog = instance.getProgress();
        a = (int) (a * prog);
        r = (int) (r * prog);
        g = (int) (g * prog);
        b = (int) (b * prog);
        return a << 24 | r << 16 | b << 8 | g;
    }
}