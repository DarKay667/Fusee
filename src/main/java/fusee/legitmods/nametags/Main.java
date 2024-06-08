package fusee.legitmods.nametags;

import java.io.File;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "nametags", name = "Name Tags Mod", version = "1.0", acceptedMinecraftVersions = "[1.8.9]")
public class Main
{
    public static Configuration config;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(new EH());
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        ClientCommandHandler.instance.registerCommand(new NameTagBGCommand());
        loadConfig();
    }
    
    public void loadConfig()
    {
        try {
            
            config = new Configuration(new File((Minecraft.getMinecraft()).mcDataDir + "/config/NameTagsMod.cfg"));
            config.load();
            
            int alpha = config.get("Background", "Alpha", 25).getInt();
            int offset = config.get("Player nametags", "Y offset", 0).getInt();
            int scale = config.get("Player nametags", "Scale", 100).getInt();
            boolean selftag = config.get("Other", "Own nametag", false).getBoolean();
            
            if (alpha < 0 || alpha > 25)
                alpha = 25;
            
            if (offset < -20 || offset > 0)
                offset = 0;
            
            if (scale < 0 || scale > 100)
                scale = 100;
            
            NameTagRenderer.setAlpha(alpha);
            NameTagRenderer.setOffset(offset);
            NameTagRenderer.setScale(scale);
            NameTagRenderer.setSelftag(selftag);
            
        } catch (Exception e) {
            
        } finally {
            config.save();
        }
    }
}