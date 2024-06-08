package fusee.legitmods.reachdisplay;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.command.ICommand;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod(modid = "reachdisplaymod", name = "Reach Display Mod", version = "1.0", acceptedMinecraftVersions = "[1.8.9]")
public class ReachDisplayMod
{
    private String rangeText = "Hasn't attacked";
    private Minecraft mc;
    private boolean enabled = true, showGui;
    private int minX, minY, maxX, maxY, addX = 0, addY = 0;
    private int currentColorIndex;
    private long lastAttack;
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        ClientCommandHandler.instance.registerCommand((ICommand) new ReachDisplayModCommand(this));
        
        FMLCommonHandler.instance().bus().register(this);
        MinecraftForge.EVENT_BUS.register(this);
        
        this.mc = Minecraft.getMinecraft();
        
        loadConfig();
    }
    
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onAttack(AttackEntityEvent event)
    {
        if (this.mc.objectMouseOver != null && this.mc.objectMouseOver.typeOfHit == MovingObjectType.ENTITY && this.mc.objectMouseOver.entityHit.getEntityId() == event.target.getEntityId())
        {
            Vec3 vec3d = this.mc.getRenderViewEntity().getPositionEyes(1.0F);
            double range = this.mc.objectMouseOver.hitVec.distanceTo(vec3d);
            
            this.rangeText = (new DecimalFormat(".##")).format(range) + " blocks";
        } else {
            this.rangeText = "Not on target?";
        }
        
        this.lastAttack = System.currentTimeMillis();
    }
    
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onRenderTick(TickEvent.RenderTickEvent event)
    {
        if ((this.mc.currentScreen == null || this.mc.currentScreen instanceof ReachDisplayGuiScreen) && this.mc.theWorld != null && this.enabled)
        {
            if (System.currentTimeMillis() - this.lastAttack > 2000L)
            {
                this.rangeText = "Hasn't attacked";
            }
            
            int minX = this.addX;
            int minY = this.addY;
            int maxX = this.addX + 4 + this.mc.fontRendererObj.getStringWidth(this.rangeText);
            int maxY = this.addY + 4 + this.mc.fontRendererObj.FONT_HEIGHT;
            
            Gui.drawRect(minX, minY, maxX, maxY, 1342177280);
            
            this.mc.fontRendererObj.drawString(this.rangeText, minX + 2, minY + 2, java.awt.Color.white.getRGB());
            
            this.minX = minX;
            this.minY = minY;
            this.maxX = maxX;
            this.maxY = maxY;
            
            if (this.mc.currentScreen != null)
            {
                try {
                    
                    this.mc.currentScreen.handleInput();
                    
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event)
    {
        if (this.showGui)
        {
            this.mc.displayGuiScreen((GuiScreen) new ReachDisplayGuiScreen(this));
            this.showGui = false;
        }
    }
    
    public void setShowGui()
    {
        this.showGui = true;
    }
    
    public void setAddX(int addX)
    {
        this.addX = addX;
    }
    
    public void setAddY(int addY)
    {
        this.addY = addY;
    }
    
    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }
    
    public void setCurrentColorIndex(int currentColorIndex)
    {
        this.currentColorIndex = currentColorIndex;
    }
    
    public boolean isEnabled()
    {
        return this.enabled;
    }
    
    public int getCurrentColorIndex()
    {
        return this.currentColorIndex;
    }
    
    public int getMinX()
    {
        return this.minX;
    }
    
    public int getMinY()
    {
        return this.minY;
    }
    
    public int getMaxX()
    {
        return this.maxX;
    }
    
    public int getMaxY()
    {
        return this.maxY;
    }
    
    public int getAddX()
    {
        return this.addX;
    }
    
    public int getAddY()
    {
        return this.addY;
    }
    
    public void saveConfig()
    {
        try {
            
            File file = new File((Minecraft.getMinecraft()).mcDataDir + "/config", "ReachDisplayMod.cfg");
            
            if (!file.exists())
            {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            
            FileWriter writer = new FileWriter(file, false);
            
            writer.write(this.addX + "\n" + this.addY + "\n" + this.enabled + "\n" + this.currentColorIndex);
            writer.close();
            
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
    
    private void loadConfig()
    {
        try {
            File file = new File((Minecraft.getMinecraft()).mcDataDir + "/config", "ReachDisplayMod.cfg");
            
            if (!file.exists())
            {
                return;
            }
            
            BufferedReader reader = new BufferedReader(new FileReader(file));
            
            int i = 0;
            
            String line;
            
            while ((line = reader.readLine()) != null)
            {
                i++;
                
                switch (i) {
                    case 1:
                        this.addX = Integer.parseInt(line);
                        
                    case 2:
                        this.addY = Integer.parseInt(line);
                        
                    case 3:
                        this.enabled = Boolean.parseBoolean(line);
                        
                    case 4:
                        this.currentColorIndex = Integer.parseInt(line);
                }
            }
            
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}