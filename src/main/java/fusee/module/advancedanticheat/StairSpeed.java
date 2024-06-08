package fusee.module.advancedanticheat;

import fusee.module.Category;
import fusee.module.Module;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStairs;
import net.minecraft.util.BlockPos;

public class StairSpeed extends Module
{
    private float speed = 0.0F;
    
    public StairSpeed()
    {
        super("[AAC] StairSpeed", Category.AdvancedAntiCheat);
    }
    
    public void onUpdate()
    {
        if (getState())
        {
            BlockPos pos = new BlockPos(Math.floor(this.mc.thePlayer.posX), Math.ceil(this.mc.thePlayer.posY), Math.floor(this.mc.thePlayer.posZ));
            
            if ((this.mc.theWorld.getBlockState(pos.add(0, -1, 0)).getBlock() instanceof BlockStairs || this.mc.theWorld.getBlockState(this.mc.thePlayer.getPosition().add(0, -0.5D, 0)).getBlock() instanceof BlockStairs))
            {
                if (this.mc.thePlayer.movementInput.moveForward > 0.0F)
                {
                    if (this.mc.theWorld.getBlockState(pos.add(0, -0.5D, 0)).getBlock() instanceof BlockSlab)
                        speed += 0.025F;
                    else speed += 0.075F;
                    
                    if (speed > 0.95F)
                        speed = 0.95F;
                }
            } else {
                if (this.mc.thePlayer.onGround)
                    speed -= 0.05F;
                else {
                    speed -= 0.33F;
                }
                
                if (speed < 0.0F)
                    speed = 0.0F;
            }
            
            if (this.mc.thePlayer.movementInput.moveForward == 0.0F)
                speed = 0.0F;
            
            if (speed != 0.0F && this.mc.thePlayer.onGround)
            {
                float f = getDirection();
                this.mc.thePlayer.motionX -= (double) (Math.sin(f) * speed);
                this.mc.thePlayer.motionZ += (double) (Math.cos(f) * speed);
            }
        }
        
        super.onUpdate();
    }
}