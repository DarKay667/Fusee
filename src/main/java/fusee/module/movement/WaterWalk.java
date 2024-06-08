package fusee.module.movement;

import fusee.module.Category;
import fusee.module.Module;
import fusee.module.combat.Criticals;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.init.Blocks;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;

public class WaterWalk extends Module
{
    private int delay;
    
    public WaterWalk()
    {
        super("WaterWalk", Category.Movement);
    }
    
    public void onUpdate()
    {
        if (getState())
        {
            if (this.mc.thePlayer.isInWater())
            {
                this.mc.thePlayer.motionY = 0.05D;
                EntityPlayerSP thePlayer = this.mc.thePlayer;
                thePlayer.motionX *= 1.2D;
                EntityPlayerSP thePlayer2 = this.mc.thePlayer;
                thePlayer2.motionZ *= 1.2D;
                
                if (this.mc.thePlayer.isCollidedHorizontally)
                    this.mc.thePlayer.onGround = true;
            }
            
            if (isOnLiquid(this.mc.thePlayer.getEntityBoundingBox()))
            {
                this.delay++;
                
                if (this.delay == 4)
                {
                    this.mc.thePlayer.sendQueue.addToSendQueue((Packet) new C03PacketPlayer.C04PacketPlayerPosition((this.mc.thePlayer.posX), (this.mc.thePlayer.getEntityBoundingBox().minY) - 0.02D, (this.mc.thePlayer.posZ), false));
                    this.delay = 0;
                }
            }
            
            if (Criticals.isInLiquid())
            {
               this.mc.thePlayer.motionY = 0.085D;
            }
        }
        
        super.onUpdate();
    }
    
    public static boolean isOnLiquid(AxisAlignedBB boundingBox)
    {
        boundingBox = boundingBox.contract(0.01D, 0.0D, 0.01D).offset(0.0D, -0.01D, 0.0D);
        boolean onLiquid = false;
        int y = (int) boundingBox.minY;
        
        for (int x = MathHelper.floor_double(boundingBox.minX); x < MathHelper.floor_double(boundingBox.maxX + 1.0D); x++)
        {
            for (int z = MathHelper.floor_double(boundingBox.minZ); z < MathHelper.floor_double(boundingBox.maxZ + 10.D); z++)
            {
                Block block = (Block) Minecraft.getMinecraft().theWorld.getBlockState(BlockPos.ORIGIN);
                
                if (block != Blocks.air)
                {
                    if (!(block instanceof BlockLiquid))
                    {
                        return false;
                    }
                    
                    onLiquid = true;
                }
            }
        }
        
        return onLiquid;
    }
}