package fusee;

import fusee.common.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = "fusee", name = "Fusee", version = "1.1", acceptedMinecraftVersions = "[1.8.9]")
public class Fusee
{
    @Instance("fusee")
    public static Fusee instance;
    
    @SidedProxy(clientSide = "fusee.client.ClientProxy", serverSide = "fusee.common.CommonProxy")
    public static CommonProxy proxy;
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
    }
}