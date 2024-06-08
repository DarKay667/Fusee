package fusee.module.advancedanticheat;

import fusee.module.Category;
import fusee.module.Module;
import net.minecraft.block.BlockSlime;
import net.minecraft.util.BlockPos;

public class SlimeJump extends Module
{
    public SlimeJump()
    {
        super("[AAC] SlimeJump", Category.AdvancedAntiCheat);
    }
    
    public void onUpdate()
    {
        if (getState())
        {
            BlockPos pos = new BlockPos(Math.floor(this.mc.thePlayer.posX), Math.ceil(this.mc.thePlayer.posY), Math.floor(this.mc.thePlayer.posZ));
            
            if (this.mc.theWorld.getBlockState(pos.add(0, -1, 0)).getBlock() instanceof BlockSlime && this.mc.thePlayer.onGround)
            {
                this.mc.thePlayer.motionY = 1.5D;
            }
        }
        
        super.onUpdate();
    }
}