package fusee.module.combat;

import fusee.module.Category;
import fusee.module.Module;

public class Velocity extends Module
{
    public Velocity()
    {
        super("Velocity", Category.Combat);
    }
    
    public void onUpdate()
    {
        if (getState())
        {
            if (this.mc.thePlayer != null)
            {
                this.mc.thePlayer.motionX *= 0.5D;
                this.mc.thePlayer.motionY *= 0.5D;
                this.mc.thePlayer.motionZ *= 0.5D;
                this.mc.thePlayer.velocityChanged = true;
            }
        }
        
        super.onUpdate();
    }
    
    public void onDisable()
    {
        this.mc.thePlayer.motionX *= 1.0D;
        this.mc.thePlayer.motionY *= 1.0D;
        this.mc.thePlayer.motionZ *= 1.0D;
        
        super.onDisable();
    }
}