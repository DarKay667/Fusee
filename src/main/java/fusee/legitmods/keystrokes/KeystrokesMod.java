package fusee.legitmods.keystrokes;

import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommand;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

@Mod(modid = "keystrokesmod", name = "Keystrokes Mod", version = "1.0", acceptedMinecraftVersions = "[1.8.9]")
public class KeystrokesMod
{
    private Minecraft mc = Minecraft.getMinecraft();
    public static int mode;
    public static boolean enabled, displayGui = false;
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        KeystrokesSettings.loadSettings();
        FMLCommonHandler.instance().bus().register(this);
        ClientCommandHandler.instance.registerCommand((ICommand) new KeystrokesCommand());
        MinecraftForge.EVENT_BUS.register(new KeystrokesRenderer());
    }
        
    @SubscribeEvent
    public void onTick(ClientTickEvent event)
    {
        if (displayGui && this.mc.currentScreen == null)
        {
            displayGui = false;
            this.mc.displayGuiScreen(new KeystrokesGui());
        }
    }
}