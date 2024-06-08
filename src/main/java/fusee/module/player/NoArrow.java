package fusee.module.player;

import fusee.module.Category;
import fusee.module.Module;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityArrow;

public class NoArrow extends Module
{
    public NoArrow()
    {
        super("NoArrow", Category.Player);
    }
    
    public void onUpdate()
    {
        if (getState())
        {
            try {
                
                for (Object o : this.mc.theWorld.loadedEntityList)
                {
                    if (o instanceof EntityArrow)
                    {
                        EntityArrow e = (EntityArrow) o;
                        
                        if (this.mc.thePlayer.canEntityBeSeen((Entity) e) && this.mc.thePlayer.getDistanceSqToEntity((Entity) e) <= 5.0D && !e.isCollided && !e.onGround)
                        {
                            this.mc.thePlayer.motionX = 0.0D;
                            this.mc.thePlayer.motionY = 0.0D;
                            this.mc.thePlayer.motionZ = 0.0D;
                        }
                    }
                }
                
            } catch (Exception e) {}
        }
        
        super.onUpdate();
    }
}