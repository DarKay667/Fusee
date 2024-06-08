package fusee.module.combat;

import java.util.List;

import fusee.module.Category;
import fusee.module.Module;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

public class AimAssist extends Module
{
    public static Entity pointedEntity;
    public static MovingObjectPosition mop;
    public static float hitBoxMultiplier = 1.0F;
    
    public AimAssist()
    {
        super("AimAssist", Category.Combat);
    }
    
    public void onUpdate()
    {
        if (getState())
        {
            getMouseOver(1.0F);
        }
        
        super.onUpdate();
    }
    
    public void getMouseOver(float mouseOver)
    {
        if (this.mc.getRenderViewEntity() != null && (this.mc.theWorld != null))
        {
            this.pointedEntity = null;
            
            double d0 = 3.0D;
            this.mop = this.mc.getRenderViewEntity().rayTrace(d0, mouseOver);
            
            double d1 = d0;
            Vec3 vec3 = this.mc.getRenderViewEntity().getPositionEyes(mouseOver);
            
            if (this.mop != null)
            {
                d1 = mop.hitVec.distanceTo(vec3);
            }
            
            Vec3 vec31 = this.mc.getRenderViewEntity().getLook(mouseOver);
            Vec3 vec32 = vec3.addVector(vec31.xCoord * d0, vec31.yCoord * d0, vec31.zCoord * d0);
            
            this.pointedEntity = null;
            
            Vec3 vec33 = null;
            
            float f1 = 1.0F;
            
            List<Entity> list = (this.mc.theWorld.getEntitiesWithinAABBExcludingEntity(this.mc.getRenderViewEntity(), this.mc.getRenderViewEntity().getEntityBoundingBox().addCoord(vec31.xCoord * d0, vec31.yCoord * d0, vec31.zCoord * d0).expand(f1, f1, f1)));
            
            double d2 = d1;
            
            for (int i = 0; i < list.size(); i++)
            {
                Entity entity = list.get(i);
                
                if (entity.canBeCollidedWith())
                {
                    float f2 = 0.13F * hitBoxMultiplier;
                    
                    AxisAlignedBB axisAlignedBB = entity.getEntityBoundingBox().expand(f2, f2, f2);
                    
                    MovingObjectPosition movingObjectPosition = axisAlignedBB.calculateIntercept(vec3, vec32);
                    
                    if (axisAlignedBB.isVecInside(vec3))
                    {
                        if (0.0D < d2 || d2 == 0.0D)
                        {
                            this.pointedEntity = entity;
                            vec33 = (movingObjectPosition == null) ? vec3 : movingObjectPosition.hitVec;
                            d2 = 0.0D;
                        }
                    }
                    
                    else if (movingObjectPosition != null)
                    {
                        double d3 = vec3.distanceTo(movingObjectPosition.hitVec);
                        
                        if (d3 < d2 || d2 == 0.0D)
                        {
                            if (entity == (this.mc.getRenderViewEntity()).ridingEntity && !entity.canRiderInteract())
                            {
                                if (d2 == 0.0D)
                                {
                                    this.pointedEntity = entity;
                                    vec33 = movingObjectPosition.hitVec;
                                }
                            }
                            
                            else
                            {
                                this.pointedEntity = entity;
                                
                                vec33 = movingObjectPosition.hitVec;
                                
                                d2 = d3;
                            }
                        }
                    }
                }
            }
            
            if (this.pointedEntity != null && (d2 < d1 || this.mop == null))
            {
                this.mop = new MovingObjectPosition(this.pointedEntity, vec33);
                
                if (this.pointedEntity instanceof EntityLivingBase || this.pointedEntity instanceof EntityItemFrame)
                {
                    this.mc.pointedEntity = this.pointedEntity;
                }
            }
        }
    }
}