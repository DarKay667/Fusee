package fusee.legitmods.memoryfix;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = "memoryfix", name = "Memory Fix Mod", version = "1.0", acceptedMinecraftVersions = "[1.8.9]", useMetadata = true)
public class MemoryFix
{  
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(this);
    }
}