package fusee.module.advancedanticheat;

import org.lwjgl.input.Keyboard;

import fusee.event.BlockBBEvent;
import fusee.module.Category;
import fusee.module.Module;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.AxisAlignedBB;

public class Spider extends Module
{
    public Spider()
    {
        super("[AAC] Spider", Category.AdvancedAntiCheat);
    }
    
    public void onUpdate()
    {
        if (!getState())
            return;
        
        if (this.mc.thePlayer.isCollidedHorizontally && Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
        {
            return;
        }
        
        if (this.mc.thePlayer.isCollidedHorizontally)
        {
            if (this.mc.thePlayer.onGround && !this.mc.thePlayer.isOnLadder())
            {
                this.mc.thePlayer.motionY = 0.4001D;
            }
        }
        
        super.onUpdate();
    }
    
    public void onEvent(Packet packet)
    {
        if (getPacket() instanceof C03PacketPlayer)
        {
            C03PacketPlayer p = (C03PacketPlayer) getPacket();
            
            if (mc.thePlayer.isCollidedHorizontally && !mc.thePlayer.isOnLadder() && getState())
            {
                double speed = 0.0000001D;
                float f = getDirection();
            }
        }
        
        super.onEvent(packet);
    }
    
    public void onCollide(BlockBBEvent event)
    {
        if (!getState())
            return;
        
        if (mc.thePlayer.isCollidedHorizontally && !mc.thePlayer.isOnLadder())
        {
            if (getState() && event.getPos().getY() < mc.thePlayer.posY)
            {
                event.setAabb(new AxisAlignedBB(event.getPos(), event.getPos().add(1, 1, 1)));
            }
        }
        
        super.onCollide(event);
    }
}