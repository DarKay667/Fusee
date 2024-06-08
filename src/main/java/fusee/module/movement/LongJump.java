package fusee.module.movement;

import fusee.module.Category;
import fusee.module.Module;
import net.minecraft.potion.Potion;

public class LongJump extends Module
{
    public LongJump()
    {
        super("LongJump", Category.Movement);
    }
    
    public void onUpdate()
    {
        if (getState())
        {
            if ((this.mc.gameSettings.keyBindForward.isKeyDown() || this.mc.gameSettings.keyBindLeft.isKeyDown() || this.mc.gameSettings.keyBindRight.isKeyDown() || this.mc.gameSettings.keyBindBack.isKeyDown() && this.mc.gameSettings.keyBindJump.isKeyDown()))
            {
                float dir = this.mc.thePlayer.rotationYaw + ((this.mc.thePlayer.moveForward < 0.0F) ? 180 : 0) + ((this.mc.thePlayer.moveStrafing > 0.0F) ? (-90.0F * ((this.mc.thePlayer.moveForward < 0.0F) ? - 0.5F : ((this.mc.thePlayer.moveForward > 0.0F) ? 0.4F : 1.0F))) : 0);
                float xDir = (float) Math.cos((dir + 90.0F) * Math.PI / 180);
                float zDir = (float) Math.sin((dir + 90.0F) * Math.PI / 180);
                
                if (this.mc.thePlayer.isCollidedVertically && (this.mc.gameSettings.keyBindForward.isKeyDown() || this.mc.gameSettings.keyBindLeft.isKeyDown() || this.mc.gameSettings.keyBindRight.isKeyDown() || this.mc.gameSettings.keyBindBack.isKeyDown() && this.mc.gameSettings.keyBindJump.isKeyDown()))
                {
                    this.mc.thePlayer.motionX = xDir * 0.29F;
                    this.mc.thePlayer.motionZ = zDir * 0.29F;
                }
                
                if (this.mc.thePlayer.motionY == 0.33319999363422365D && (this.mc.gameSettings.keyBindForward.isKeyDown() || this.mc.gameSettings.keyBindLeft.isKeyDown() || this.mc.gameSettings.keyBindRight.isKeyDown() || this.mc.gameSettings.keyBindBack.isKeyDown()))
                {
                    if (this.mc.thePlayer.isPotionActive(Potion.moveSpeed))
                    {
                        this.mc.thePlayer.motionX = xDir * 1.34;
                        this.mc.thePlayer.motionZ = zDir * 1.34;
                    }
                    
                    else
                    {
                        this.mc.thePlayer.motionX = xDir * 1.261;
                        this.mc.thePlayer.motionZ = zDir * 1.261;
                    }
                }
            }
        }
        
        super.onUpdate();
    }
}