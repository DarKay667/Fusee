package fusee.module.advancedanticheat;

import fusee.event.BlockBBEvent;
import fusee.module.Category;
import fusee.module.Module;
import net.minecraft.util.AxisAlignedBB;

public class SnowFly extends Module
{
    public SnowFly()
    {
        super("[AAC] SnowFly", Category.AdvancedAntiCheat);
    }
    
    public void onUpdate()
    {
        if (getState())
        {
            if (isBlockValid(this.mc.theWorld.getBlockState(this.mc.thePlayer.getPosition().add(0, -1, 0)).getBlock()) && isBlockValid(this.mc.theWorld.getBlockState(this.mc.thePlayer.getPosition()).getBlock()))
            {
                if (this.mc.gameSettings.keyBindJump.isKeyDown() && this.mc.thePlayer.onGround)
                {
                    this.mc.thePlayer.motionX *= 0.25D;
                    this.mc.thePlayer.motionZ *= 0.25D;
                    this.mc.thePlayer.jump();
                    this.mc.thePlayer.motionY = 3.0D;
                }
                
                this.mc.thePlayer.onGround = false;
            }
        }
        
        super.onUpdate();
    }
    
    public void onCollide(BlockBBEvent event)
    {
        if (!getState())
            return;
        
        if (!this.mc.gameSettings.keyBindSneak.isKeyDown() && this.mc.thePlayer.posY > event.getPos().getY())
        {
            if (isBlockValid(event.getBlock()))
            {
                event.setAabb(new AxisAlignedBB(event.getPos(), event.getPos().add(1, 1, 1)));
            }
        }
        
        super.onCollide(event);
    }
}