package fusee.module.advancedanticheat;

import fusee.module.Category;
import fusee.module.Module;
import net.minecraft.network.play.client.C00PacketKeepAlive;

public class AirStuck extends Module
{    
    public AirStuck()
    {
        super("[AAC] AirStuck", Category.AdvancedAntiCheat);
    }
    
    public void onUpdate()
    {
        if (getState())
        {
            if (getPacket() instanceof C00PacketKeepAlive || getPacket().getClass().getSimpleName().startsWith("S") || !getPacket().getClass().getName().contains("play"))
            {
            
            } else {
                setState(false);
            }
        }
        
        super.onUpdate();
    }
    
    /*public void onEvent(Packet packet)
    {
        if (getState())
        {
            if (getPacket() instanceof C00PacketKeepAlive || getPacket().getClass().getSimpleName().startsWith("S") || !getPacket().getClass().getName().contains("play")) {
                
            } else {
                setState(false);
            }
        }
        
        super.onEvent(packet);
    }*/
}