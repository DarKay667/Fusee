package fusee.module.player;

import fusee.module.Category;
import fusee.module.Module;

public class AntiAFK extends Module
{
    private int tick;
    
    public AntiAFK()
    {
        super("AntiAFK", Category.Player);
        this.tick = 0;
    }
    
    public void onUpdate()
    {
        if (getState())
        {
            this.tick++;
            
            if (this.tick > 200)
            {
                this.tick = 0;
                this.mc.thePlayer.rotationYaw -= 180.0F;
                this.mc.thePlayer.moveForward = 1.0F;
                
                if (this.mc.thePlayer.onGround)
                    this.mc.thePlayer.jump();
            }
        }
        
        super.onUpdate();
    }
}