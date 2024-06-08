package fusee.module.player;

import fusee.module.Category;
import fusee.module.Module;
import fusee.module.movement.WaterWalk;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;

public class AntiFire extends Module
{
    public AntiFire()
    {
        super("AntiFire", Category.Player);
    }
    
    public void onUpdate()
    {
        if (getState())
        {
            if (!WaterWalk.isOnLiquid(this.mc.thePlayer.getEntityBoundingBox()) && this.mc.thePlayer.isBurning() && (this.mc.thePlayer.onGround))
            {
                for (int i = 0; i < 10; i++)
                {
                    this.mc.thePlayer.sendQueue.addToSendQueue((Packet) new C03PacketPlayer(false));
                }
            }
        }
            
        super.onUpdate();
    }
}