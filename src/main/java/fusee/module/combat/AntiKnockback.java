package fusee.module.combat;

import fusee.module.Category;
import fusee.module.Module;
import net.minecraft.client.entity.EntityPlayerSP;

public class AntiKnockback extends Module
{
    private double kb;
    
    public AntiKnockback()
    {
        super("AntiKnockback", Category.Combat);
        this.kb = 0.0D;
    }
    
    public void onUpdate()
    {
        if (getState())
        {
            if (this.mc.thePlayer.hurtResistantTime > 0 && (this.mc.thePlayer.hurtTime > 0))
            {
                this.mc.thePlayer.hurtResistantTime = 0;
                this.mc.thePlayer.hurtTime = 0;
                this.mc.thePlayer.motionX = 0.0D;
                EntityPlayerSP entityPlayerSP = this.mc.thePlayer;
                entityPlayerSP.motionY /= 10.0D;
                this.mc.thePlayer.motionZ = 0.0D; 
            }
        }
        
        super.onUpdate();
    }
}