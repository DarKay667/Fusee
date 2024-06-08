package fusee.module.combat;

import fusee.Fusee;
import fusee.module.Category;
import fusee.module.Module;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;

public class AimBot extends Module
{
    public AimBot()
    {
        super("AimBot", Category.Combat);
    }
    
    public void onUpdate()
    {
        if (getState())
        {
            try {
                
                if (!Fusee.instance.proxy.moduleManager.getModule(KillAura.class).getState())
                {
                    for (Object o : this.mc.theWorld.loadedEntityList)   
                    {
                        EntityPlayer entityPlayer = (EntityPlayer) o;
                        
                        if (!(o instanceof EntityPlayer) || entityPlayer instanceof EntityPlayerSP || this.mc.thePlayer.getDistanceToEntity((Entity) entityPlayer) > 6.0D || entityPlayer.isDead || !this.mc.thePlayer.canEntityBeSeen((Entity) entityPlayer) || !entityPlayer.isEntityAlive() || entityPlayer.isDead)
                        {
                            continue;
                        }
                        
                        faceEntity((Entity) entityPlayer);
                    }
                }
                
            } catch (Exception e) {}
        }
        
        super.onUpdate();
    }
    
    private void faceEntity(Entity entity)
    {
        double x = entity.posX - (this.mc.thePlayer.posX);
        double y = entity.posY - (this.mc.thePlayer.posY);
        double z = entity.posZ - (this.mc.thePlayer.posZ);
        double d1 = (this.mc.thePlayer.posY + this.mc.thePlayer.getEyeHeight() - entity.posY + entity.getEyeHeight());
        double d2 = MathHelper.sqrt_double(x * x + z * z);
        float f = (float) (Math.atan2(z, x) * 180.0D / Math.PI) - 90.0F;
        float f2 = (float) (-Math.atan2(d1, d2) * 180.0D / Math.PI);
        this.mc.thePlayer.setPositionAndRotation(this.mc.thePlayer.posX, this.mc.thePlayer.posY, this.mc.thePlayer.posZ, f, -f2);
    }
}