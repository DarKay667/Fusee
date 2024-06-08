package fusee.module.movement;

import fusee.module.Category;
import fusee.module.Module;

public class Step extends Module
{
    public Step()
    {
        super("Step", Category.Movement);
    }
    
    public void onUpdate()
    {
        if (getState())
        { 
            this.mc.thePlayer.stepHeight = 3.0F;
        }
        
        super.onUpdate();
    }
    
    public void onDisable()
    {
        this.mc.thePlayer.stepHeight = 0.5F;
        super.onDisable();
    }
}