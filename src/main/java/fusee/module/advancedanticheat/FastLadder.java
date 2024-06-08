package fusee.module.advancedanticheat;

import org.lwjgl.input.Keyboard;

import fusee.module.Category;
import fusee.module.Module;

public class FastLadder extends Module
{
    public FastLadder()
    {
        super("[AAC] FastLadder", Category.AdvancedAntiCheat);
    }
    
    public void onUpdate()
    {
        if (getState())
        {
            if (this.mc.thePlayer.isOnLadder())
            {
                this.mc.thePlayer.motionY = 0.169D;
            }
        }
        
        super.onUpdate();
    }
}