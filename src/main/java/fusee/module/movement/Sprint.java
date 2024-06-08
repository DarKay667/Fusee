package fusee.module.movement;

import fusee.module.Category;
import fusee.module.Module;

public class Sprint extends Module
{
    public Sprint()
    {
        super("Sprint", Category.Movement);
        setState(true);
    }
    
    public void onUpdate()
    {
        if (getState())
        {
            if (!this.mc.thePlayer.isCollidedHorizontally && this.mc.thePlayer.moveForward > 0.0F)
            {
                this.mc.thePlayer.setSprinting(true);
            }
        }
        
        super.onUpdate();
    }
}