package fusee.module.player;

import fusee.module.Category;
import fusee.module.Module;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSnow;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class Derp extends Module
{
    public int a, direction;
    
    public Derp()
    {
        super("Derp", Category.Player);
    }
    
    public void onEnable()
    {
        a = 1;
        direction = MathHelper.floor_double((double) ((this.mc.thePlayer.rotationYaw * 4.0F) / 360.0F) + 0.5D) & 3;
        
        super.onEnable();
    }
    
    public void onUpdate()
    {
        if (getState())
        {
            BlockPos playerBlock = new BlockPos(this.mc.thePlayer.posX, this.mc.thePlayer.getEntityBoundingBox().minY, this.mc.thePlayer.posZ);
            
            if (direction == 0)
            {
                if (a == 0)
                {
                    this.mc.thePlayer.rotationYaw = 0.0F;
                    this.mc.thePlayer.rotationPitch = 0.0F;
                }
                
                if (onEdge())
                {
                    this.mc.thePlayer.rotationYaw = -135.0F;
                    this.mc.thePlayer.rotationPitch = 75.8F;
                    placeBlock(playerBlock.add(0, -1, 0), EnumFacing.SOUTH);
                    a = 0;
                }
            }
            
            else if (direction == 1)
            {
                if (a == 0)
                {
                    this.mc.thePlayer.rotationYaw = 90.0F;
                    this.mc.thePlayer.rotationPitch = 0.0F;
                }
                
                if (onEdge())
                {
                    this.mc.thePlayer.rotationYaw = -45.0F;
                    this.mc.thePlayer.rotationPitch = 75.8F;
                    placeBlock(playerBlock.add(0, -1, 0), EnumFacing.WEST);
                    a = 0;
                }
            }
            
            else if (direction == 2)
            {
                if (a == 0)
                {
                    this.mc.thePlayer.rotationYaw = -180.0F;
                    this.mc.thePlayer.rotationPitch = 0.0F;
                }
                
                if (onEdge())
                {
                    this.mc.thePlayer.rotationYaw = 45.0F;
                    this.mc.thePlayer.rotationPitch = 75.8F;
                    placeBlock(playerBlock.add(0, -1, 0), EnumFacing.NORTH);
                    a = 0;
                }
            }
            
            else if (direction == 3)
            {
                if (a == 0)
                {
                    this.mc.thePlayer.rotationYaw = -90.0F;
                    this.mc.thePlayer.rotationPitch = 0.0F;
                }
                
                if (onEdge())
                {
                    this.mc.thePlayer.rotationYaw = 135.0F;
                    this.mc.thePlayer.rotationPitch = 75.8F;
                    placeBlock(playerBlock.add(0, -1, 0), EnumFacing.EAST);
                    a = 0;
                }
            }
        }
        
        super.onUpdate();
    }
    
    public void onDisable()
    {
        a = 1;
        
        super.onDisable();
    }
    
    private void placeBlock(BlockPos pos, EnumFacing face)
    {
        if (!doesSlotHaveBlocks(this.mc.thePlayer.inventory.currentItem))
            this.mc.thePlayer.inventory.currentItem = getFirstHotBarSlotWithBlocks();
        
        if (!isBlockAir(getBlock(pos)))
            return;
        
        if (face == EnumFacing.UP)
        {
            pos = pos.add(0, -1, 0);
        }
        
        else if (face == EnumFacing.NORTH)
        {
            pos = pos.add(0, 0, 1);
        }
        
        else if (face == EnumFacing.EAST)
        {
            pos = pos.add(-1, 0, 0);
        }
        
        else if (face == EnumFacing.SOUTH)
        {
            pos = pos.add(0, 0, -1);
        }
        
        else if (face == EnumFacing.WEST)
        {
            pos = pos.add(1, 0, 0);
        }
        
        if (this.mc.thePlayer.getHeldItem() != null && this.mc.thePlayer.getHeldItem().getItem() instanceof ItemBlock)
        {
            this.mc.thePlayer.swingItem();
            this.mc.playerController.onPlayerRightClick(this.mc.thePlayer, this.mc.theWorld, this.mc.thePlayer.getHeldItem(), pos, face, new Vec3(0.5D, 0.5D, 0.5D));
            a = 1;
        }
    }
    
    private boolean onEdge()
    {
        if (direction == 0)
        {
            if (Math.round(Math.abs(this.mc.thePlayer.posZ - Math.floor(this.mc.thePlayer.posZ)) * 10.0D) <= 3L && Math.round(Math.abs(this.mc.thePlayer.posZ - Math.floor(this.mc.thePlayer.posZ)) * 10.0D) >= 1L)
                return true;
        }
        
        else if (direction == 3)
        {
            if (Math.round(Math.abs(this.mc.thePlayer.posX - Math.floor(this.mc.thePlayer.posX)) * 10.0D) <= 3L && Math.round(Math.abs(this.mc.thePlayer.posX - Math.floor(this.mc.thePlayer.posX)) * 10.0D) >= 1L)
                return true;
        }
        
        else if (direction == 1)
        {
            if (Math.round(Math.abs(this.mc.thePlayer.posX - Math.floor(this.mc.thePlayer.posX)) * 10.0D) <= 9L && Math.round(Math.abs(this.mc.thePlayer.posX - Math.floor(this.mc.thePlayer.posX)) * 10.0D) >= 7L)
                return true;
        }
        
        else if (direction == 2)
        {
            if (Math.round(Math.abs(this.mc.thePlayer.posZ - Math.floor(this.mc.thePlayer.posZ)) * 10.0D) <= 9L && Math.round(Math.abs(this.mc.thePlayer.posZ - Math.floor(this.mc.thePlayer.posZ)) * 10.0D) >= 7L)
                return true;
        }
        
        return false;
    }
    
    private boolean isBlockAir(Block block)
    {
        if (block.getMaterial().isReplaceable())
        {
            if (block instanceof BlockSnow && block.getBlockBoundsMaxY() > 0.125D)
                return false;
            return true;
        }
        
        return false;
    }
    
    private Block getBlock(BlockPos pos)
    {
        return (this.mc.theWorld.getBlockState(pos).getBlock());
    }
    
    private int getFirstHotBarSlotWithBlocks()
    {
        for (int i = 0; i < 9; i++)
        {
            if (this.mc.thePlayer.inventory.getStackInSlot(i) != null && this.mc.thePlayer.inventory.getStackInSlot(i).getItem() instanceof ItemBlock)
                return i;
        }
        
        return 0;
    }
    
    private boolean doesSlotHaveBlocks(int slotToCheck)
    {
        if (this.mc.thePlayer.inventory.getStackInSlot(slotToCheck) != null && this.mc.thePlayer.inventory.getStackInSlot(slotToCheck).getItem() instanceof ItemBlock && (this.mc.thePlayer.inventory.getStackInSlot(slotToCheck)).stackSize > 0)
            return true;
        
        return false;
    }
}