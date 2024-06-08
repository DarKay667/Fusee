package fusee.module.movement;

import fusee.module.Category;
import fusee.module.Module;

public class NoClip extends Module
{
    public NoClip()
    {
        super("NoClip", Category.Movement);
    }
    
    public void onUpdate()
    {
        if (getState())
        {
            if (this.mc.thePlayer.onGround)
            {
                this.mc.thePlayer.noClip = true;
                this.mc.thePlayer.fallDistance = 0.0F;
                this.mc.thePlayer.onGround = false;
                
                if (this.mc.gameSettings.keyBindJump.isKeyDown())
                {
                    this.mc.thePlayer.motionY = 0.04000000059604645D;
                }
                
                else if (this.mc.gameSettings.keyBindSneak.isKeyDown())
                {
                    this.mc.thePlayer.motionY -= 0.30000001192092896D;
                }
                
                else
                {
                    this.mc.thePlayer.motionY = 0.0D;
                }
            }
        }
        
        super.onUpdate();
    }
    
    public void onDisable()
    {
        this.mc.thePlayer.noClip = false;
        super.onDisable();
    }
}