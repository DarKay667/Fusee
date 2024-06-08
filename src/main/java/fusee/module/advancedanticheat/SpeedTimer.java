package fusee.module.advancedanticheat;

import org.lwjgl.input.Keyboard;

import fusee.module.Category;
import fusee.module.Module;

public class SpeedTimer extends Module
{
    public SpeedTimer()
    {
        super("[AAC] SpeedTimer", Category.AdvancedAntiCheat);
    }
    
    public void onUpdate()
    {
        if (getState())
        {
            if (this.mc.thePlayer.moveForward > 0.0F)
            {
                double direction = getDirection();
                double speed = 1.69D;
                setTimerSpeed(2.0F);
                this.mc.thePlayer.motionX = -Math.sin(direction) * speed;
                this.mc.thePlayer.motionZ = Math.cos(direction) * speed;
            }
        }
        
        super.onUpdate();
    }
    
    public void onDisable()
    {
        setTimerSpeed(1.0F);
        
        super.onDisable();
    }
}