package fusee.module.movement;

import fusee.module.Category;
import fusee.module.Module;

public class WaterJump extends Module
{
    public WaterJump()
    {
        super("WaterJump", Category.Movement);
    }
    
    public void onUpdate()
    {
        if (getState())
        {
            if (this.mc.thePlayer.isInWater())
            {
                this.mc.thePlayer.jump();
            }
        }
        
        super.onUpdate();
    }
}