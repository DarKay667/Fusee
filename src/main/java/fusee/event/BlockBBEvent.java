package fusee.event;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;

public class BlockBBEvent
{
    private BlockPos pos;
    private Block block;
    private AxisAlignedBB aabb;
    private List<AxisAlignedBB> axisAlignedBBList = new ArrayList<AxisAlignedBB>();
    
    public BlockBBEvent(BlockPos pos, Block block, AxisAlignedBB aabb)
    {
        this.pos = pos;
        this.block = block;
        this.aabb = aabb;
    }
    
    public BlockPos getPos()
    {
        return pos;
    }
    
    public void setPos(BlockPos pos) 
    {
        this.pos = pos;
    }
    
    public Block getBlock()
    {
        return block;
    }
    
    public void setBlock(Block block)
    {
        this.block = block;
    }
    
    public AxisAlignedBB getAabb()
    {
        return aabb;
    }
    
    public void setAabb(AxisAlignedBB aabb)
    {
        this.aabb = aabb;
    }
    
    public List<AxisAlignedBB> getAxisAlignedBBList()
    {
        return axisAlignedBBList;
    }
}