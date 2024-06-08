package fusee.module.advancedanticheat;

import org.lwjgl.input.Keyboard;

import fusee.module.Category;
import fusee.module.Module;

public class HighJump extends Module
{
    public HighJump()
    {
        super("[AAC] HighJump", Category.AdvancedAntiCheat);
    }
    
    public void onUpdate()
    {
        if (getState())
        {
            if (this.mc.thePlayer.onGround)
            {
                this.mc.thePlayer.motionY = 4;
            }
        }
        
        super.onUpdate();
    }
}