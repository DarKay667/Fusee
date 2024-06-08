package fusee.module.movement;

import fusee.module.Category;
import fusee.module.Module;

public class FastLadder extends Module
{
    public FastLadder()
    {
        super("FastLadder", Category.Movement);
    }
    
    public void onUpdate()
    {
        if (getState())
        {
            if (this.mc.thePlayer.moveForward != 0.0F || this.mc.thePlayer.moveStrafing != 0.0F && this.mc.thePlayer.isOnLadder())
            {
                this.mc.thePlayer.motionY = 1.0D;
            }
        }
        
        super.onUpdate();
    }
}