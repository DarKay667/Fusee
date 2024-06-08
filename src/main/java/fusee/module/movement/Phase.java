package fusee.module.movement;

import fusee.module.Category;
import fusee.module.Module;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockHopper;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;

public class Phase extends Module
{
    private int reset;
    private double distance = 1.0D;
    
    public Phase()
    {
        super("Phase", Category.Movement);
    }
    
    public void onUpdate()
    {
        if (getState())
        {
            reset = -1;
            double xOff = 0.0D, zOff = 0.0D, multi = 2.6D;
            double mx = Math.cos(Math.toRadians(this.mc.thePlayer.rotationYaw + 90.0F));
            double mz = Math.sin(Math.toRadians(this.mc.thePlayer.rotationYaw + 90.0F));
            xOff = this.mc.thePlayer.moveForward * 2.6D * mx + this.mc.thePlayer.moveStrafing * 2.6D * mz;
            zOff = this.mc.thePlayer.moveForward * 2.6D * mz + this.mc.thePlayer.moveStrafing * 2.6D * mx;
            
            if (isInsideBlock() && this.mc.thePlayer.isSneaking())
               reset = 1;
            
            if (reset > 0)
            {
                this.mc.thePlayer.getEntityBoundingBox().offset(xOff, 0, zOff);
            }
        }
        
        super.onUpdate();
    }
    
    private boolean isInsideBlock()
    {
        for (int x = MathHelper.floor_double(this.mc.thePlayer.getEntityBoundingBox().minX); x < MathHelper.floor_double(this.mc.thePlayer.getEntityBoundingBox().maxX) + 1; x++)
        {
            for (int y = MathHelper.floor_double(this.mc.thePlayer.getEntityBoundingBox().minY); y < MathHelper.floor_double(this.mc.thePlayer.getEntityBoundingBox().maxY) + 1; y++)
            {
                for (int z = MathHelper.floor_double(this.mc.thePlayer.getEntityBoundingBox().minZ); z < MathHelper.floor_double(this.mc.thePlayer.getEntityBoundingBox().maxZ) + 1; z++)
                {
                    Block block = this.mc.theWorld.getBlockState(new BlockPos(x, y, z)).getBlock();
                    
                    if (block != null && !(block instanceof BlockAir))
                    {
                        AxisAlignedBB boundingBox = block.getCollisionBoundingBox(this.mc.theWorld, new BlockPos(x, y, z), this.mc.theWorld.getBlockState(new BlockPos(x, y, z)));
                        
                        if (block instanceof BlockHopper)
                            boundingBox = new AxisAlignedBB(x, y, z, x + 1, y + 1, z + 1);
                        
                        if (boundingBox != null && this.mc.thePlayer.getEntityBoundingBox().intersectsWith(boundingBox))
                            return true;
                    }
                }
            }
        }
        
        return false;
    }
}