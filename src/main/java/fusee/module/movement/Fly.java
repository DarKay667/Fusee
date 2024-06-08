package fusee.module.movement;

import fusee.module.Category;
import fusee.module.Module;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;

public class Fly extends Module
{
    public static float flySpeed, fallDistance = 2.0F;
    
    public Fly()
    {
        super("Fly", Category.Movement);
    }
    
    public void onUpdate()
    {
        if (getState())
        {
            setTimerSpeed(1.5F);
            this.mc.thePlayer.capabilities.isFlying = false;
            this.mc.thePlayer.capabilities.allowFlying = false;
            this.mc.thePlayer.motionX = 0.0D;
            this.mc.thePlayer.motionY = 0.0D;
            this.mc.thePlayer.motionZ = 0.0D;
            this.mc.thePlayer.jumpMovementFactor = flySpeed;
            
            if (this.mc.gameSettings.keyBindJump.isPressed())
            {
                this.mc.thePlayer.motionY += flySpeed;
                this.mc.thePlayer.fallDistance = fallDistance;
                this.mc.thePlayer.sendQueue.addToSendQueue((Packet) new C03PacketPlayer(true));
            }
            
            if (this.mc.gameSettings.keyBindSneak.isPressed())
            {
                this.mc.thePlayer.motionY -= flySpeed;
                this.mc.thePlayer.fallDistance = fallDistance;
                this.mc.thePlayer.sendQueue.addToSendQueue((Packet) new C03PacketPlayer(true));
            }
        }
        
        super.onUpdate();
    }
    
    public void onDisable()
    {
        setTimerSpeed(20.0F);
        this.mc.thePlayer.motionX = 0.0D;
        this.mc.thePlayer.motionY = 0.0D;
        this.mc.thePlayer.motionZ = 0.0D;
        
        super.onDisable();
    }
}