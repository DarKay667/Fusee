package fusee.module.player;

import fusee.module.Category;
import fusee.module.Module;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class Blink extends Module
{
    public EntityFakePlayer freecam;
    
    public Blink()
    {
        super("Blink", Category.Player);
        this.freecam = null;
    }
    
    public void onUpdate()
    {
        if (getState())
        {
            if (this.mc.thePlayer != null && this.mc.theWorld != null)
            {
                this.freecam = new EntityFakePlayer((World) this.mc.theWorld, this.mc.thePlayer.getGameProfile());
                this.freecam.inventory = this.mc.thePlayer.inventory;
                this.freecam.getYOffset();
                this.freecam.rotationPitch = this.mc.thePlayer.rotationPitch;
                this.freecam.rotationYaw = this.mc.thePlayer.rotationYaw;
                this.freecam.rotationYawHead = this.mc.thePlayer.rotationYawHead;
                this.mc.theWorld.spawnEntityInWorld((Entity) this.freecam);
            }
        }
        
        super.onUpdate();
    }
    
    public void onDisable()
    {
        if (this.freecam != null && this.mc.theWorld != null)
        {
            this.mc.theWorld.removeEntity((Entity) this.freecam);
            this.freecam = null;
        }
        
        super.onDisable();
    }
}