package fusee.module.movement;

import fusee.module.Category;
import fusee.module.Module;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;

public class NoFall extends Module
{
    public NoFall()
    {
        super("NoFall", Category.Movement);
    }
    
    public void onUpdate()
    {
        if (getState())
        {
            if (this.mc.thePlayer.fallDistance >= 2.0F)
            {
                this.mc.thePlayer.sendQueue.addToSendQueue((Packet) new C03PacketPlayer(true));
            }
        }
        
        super.onUpdate();
    }
}