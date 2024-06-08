package fusee.legitmods.timechanger;

import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.command.ICommand;
import net.minecraft.network.INetHandler;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod(modid = "timechanger", name = "Time Changer", version = "1.0", acceptedMinecraftVersions = "[1.8.9]")
public class TimeChanger
{
    public static TimeType TIME_TYPE = TimeType.VANILLA;
    public static double fastTimeMultiplier = 1.0D;
    private Minecraft mc = Minecraft.getMinecraft();
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        ClientCommandHandler.instance.registerCommand((ICommand) new CommandDay());
        ClientCommandHandler.instance.registerCommand((ICommand) new CommandNight());
        ClientCommandHandler.instance.registerCommand((ICommand) new CommandSunSet());
        ClientCommandHandler.instance.registerCommand((ICommand) new CommandResetTime());
        ClientCommandHandler.instance.registerCommand((ICommand) new CommandFastTime());
        ClientCommandHandler.instance.registerCommand((ICommand) new CommandStopWeather());
        
        FMLCommonHandler.instance().bus().register(this);
    }
    
    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event)
    {
        if (this.mc.theWorld != null)
        {
            INetHandler parent = this.mc.thePlayer.sendQueue.getNetworkManager().getNetHandler();
            
            if (!(parent instanceof TimeChangerNetHandler))
            {
                this.mc.thePlayer.sendQueue.getNetworkManager().setNetHandler((INetHandler) new TimeChangerNetHandler((NetHandlerPlayClient) parent));
            }
            
            if (TIME_TYPE == TimeType.FAST)
            {
                this.mc.theWorld.setWorldTime((long) (System.currentTimeMillis() * fastTimeMultiplier % 24000.0D));
            }
        }
    }
}