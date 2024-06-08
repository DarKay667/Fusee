package fusee.module.movement;

import fusee.module.Category;
import fusee.module.Module;

public class Glide extends Module
{
    public Glide()
    {
        super("Glide", Category.Movement);
    }
    
    public void onUpdate()
    {
        if (getState())
        {
            if (this.mc.theWorld.isRemote)
            {
                if (this.mc.thePlayer.motionY < 0.0D)
                {
                    double horizontalSpeed, verticalSpeed;
                    
                    if (this.mc.thePlayer.isSneaking())
                    {
                        horizontalSpeed = 0.1D;
                        verticalSpeed = 0.9D;
                    } else {
                        horizontalSpeed = 0.03D;
                        verticalSpeed = 0.7D;
                    }
                    
                    this.mc.thePlayer.motionY *= verticalSpeed;
                    double x = Math.cos(Math.toRadians((this.mc.thePlayer.rotationYawHead + 90.0F))) * horizontalSpeed;
                    double z = Math.sin(Math.toRadians((this.mc.thePlayer.rotationYawHead + 90.0F))) * horizontalSpeed;
                    this.mc.thePlayer.motionX += x;
                    this.mc.thePlayer.motionZ += z;
                    this.mc.thePlayer.fallDistance = 0.0F;
                }
            }
            
            if (!this.mc.theWorld.isRemote)
            {
                if (this.mc.thePlayer.motionY < 0.0D)
                {
                    double horizontalSpeed, verticalSpeed;
                    
                    if (this.mc.thePlayer.isSneaking())
                    {
                        horizontalSpeed = 0.1D;
                        verticalSpeed = 0.7D;
                    } else {
                        horizontalSpeed = 0.03D;
                        verticalSpeed = 0.4D;
                    }
                    
                    this.mc.thePlayer.motionY *= verticalSpeed;
                    double x = Math.cos(Math.toRadians((this.mc.thePlayer.rotationYawHead + 90.0F))) * horizontalSpeed;
                    double z = Math.sin(Math.toRadians((this.mc.thePlayer.rotationYawHead + 90.0F))) * horizontalSpeed;
                    this.mc.thePlayer.motionX += x;
                    this.mc.thePlayer.motionZ += z;
                    this.mc.thePlayer.fallDistance = 0.0F;
                }
            }
        }
        
        super.onUpdate();
    }
}