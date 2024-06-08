package fusee.module.movement;

import fusee.module.Category;
import fusee.module.Module;

public class Sneak extends Module
{
    public Sneak()
    {
        super("Sneak", Category.Movement);
    }
    
    public void onUpdate()
    {
        if (getState())
        {
            if (!this.mc.thePlayer.isCollidedHorizontally && this.mc.thePlayer.moveForward > 0.0F)
            {
                this.mc.thePlayer.setSneaking(true);
            }
        }
        
        super.onUpdate();
    }
}