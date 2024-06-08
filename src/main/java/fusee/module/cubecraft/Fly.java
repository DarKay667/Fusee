package fusee.module.cubecraft;

import org.lwjgl.input.Keyboard;

import fusee.module.Category;
import fusee.module.Module;

public class Fly extends Module
{
    public Fly()
    {
        super("[Cubecraft] Fly", Category.Cubecraft);
    }
    
    public void onUpdate()
    {
        if (getState())
        {
            this.mc.thePlayer.capabilities.isFlying = true;
            setTimerSpeed(0.29F);
        }
        
        super.onUpdate();
    }
    
    public void onDisable()
    {
        if (this.mc.thePlayer != null && this.mc.thePlayer.capabilities != null)
        {
            this.mc.thePlayer.capabilities.isFlying = false;
            setTimerSpeed(1.0F);
        }
        
        super.onDisable();
    }
}