package fusee.module.movement;

import fusee.module.Category;
import fusee.module.Module;

public class Spider extends Module
{
    public Spider()
    {
        super("Spider", Category.Movement);
    }
    
    public void onUpdate()
    {
        if (getState())
        {
            if (this.mc.thePlayer.motionY < 0.0D && this.mc.thePlayer.isCollidedHorizontally)
                this.mc.thePlayer.jump();
        }
        
        super.onUpdate();
    }
}