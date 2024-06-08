package fusee.module.advancedanticheat;

import org.lwjgl.input.Keyboard;

import fusee.module.Category;
import fusee.module.Module;
import net.minecraft.client.entity.EntityPlayerSP;

public class NoWeb extends Module
{
    public NoWeb()
    {
        super("[AAC] NoWeb", Category.AdvancedAntiCheat);
    }
    
    public void onUpdate()
    {   
        if (getState())
        {
            try {
                if (getField(EntityPlayerSP.class, "isInWeb").getBoolean(this.mc.thePlayer))
                    this.mc.thePlayer.onGround = false;
                                
                getField(EntityPlayerSP.class, "isInWeb").setBoolean(this.mc.thePlayer, false);
                
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        
        super.onUpdate();
    }
}