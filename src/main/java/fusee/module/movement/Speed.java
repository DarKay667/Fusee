package fusee.module.movement;

import fusee.module.Category;
import fusee.module.Module;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;

public class Speed extends Module
{
    public Speed()
    {
        super("Speed", Category.Movement);
    }
    
    public void onUpdate()
    {
        if (getState())
        {
            float yaw = this.mc.thePlayer.rotationYaw * 0.017453292F;
            this.mc.thePlayer.motionX -= Math.sin(yaw) * 0.6D;
            this.mc.thePlayer.motionZ += Math.cos(yaw) / 0.6D;
            
            if (this.mc.thePlayer.fallDistance > 2.0F)
            {
                this.mc.thePlayer.sendQueue.addToSendQueue((Packet) new C03PacketPlayer.C04PacketPlayerPosition(this.mc.thePlayer.posX, this.mc.thePlayer.getCollisionBoundingBox().minY + this.mc.thePlayer.posY, this.mc.thePlayer.posZ, true));
            }
        }
        
        super.onUpdate();
    }
}