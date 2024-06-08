package fusee.module.advancedanticheat;

import org.lwjgl.input.Keyboard;

import fusee.module.Category;
import fusee.module.Module;

public class NoFall extends Module
{
    public NoFall()
    {
        super("[AAC] NoFall", Category.AdvancedAntiCheat);
    }
    
    public void onUpdate()
    {
        if (getState())
        {
            if (this.mc.theWorld == null || this.mc.thePlayer == null)
                return;
            
            if (this.mc.thePlayer.onGround && !this.mc.thePlayer.isOnLadder() && !this.mc.thePlayer.isInWater() && !this.mc.thePlayer.isInLava())
            {
                this.mc.thePlayer.motionY = -6.0D;
            }
        }
        
        super.onUpdate();
    }
}