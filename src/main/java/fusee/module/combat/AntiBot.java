package fusee.module.combat;

import fusee.module.Category;
import fusee.module.Module;
import net.minecraft.entity.Entity;

public class AntiBot extends Module
{
    public AntiBot()
    {
        super("AntiBot", Category.Combat);
    }
    
    public void onUpdate()
    {
        if (getState())
        {
            for (Object entity : this.mc.theWorld.loadedEntityList)
            {
                if (((Entity) entity).isInvisible() && entity != this.mc.thePlayer)
                    this.mc.theWorld.removeEntity((Entity) entity);
            }
        }
        
        super.onUpdate();
    }
}