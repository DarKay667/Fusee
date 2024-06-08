package fusee.module.render;

import fusee.module.Category;
import fusee.module.Module;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraftforge.client.event.RenderWorldLastEvent;

public class ChestESP extends Module
{
    public ChestESP()
    {
        super("ChestESP", Category.Render);
    }
    
    public void onRender3D(RenderWorldLastEvent event)
    {
        if (getState())
        {
            if (mc.theWorld != null)
            {
                for (int i = 0; i < mc.theWorld.loadedTileEntityList.size(); i++)
                {
                    if (mc.theWorld.loadedTileEntityList.get(i) instanceof TileEntityChest)
                    {
                        TileEntityChest te = (TileEntityChest) mc.theWorld.loadedTileEntityList.get(i);
                        
                        if (te.getChestType() == 0)
                        {
                            drawChestESP(te.getPos().getX() - mc.thePlayer.lastTickPosX, te.getPos().getY() - mc.thePlayer.lastTickPosY, te.getPos().getZ() - mc.thePlayer.lastTickPosZ);
                        }
                        
                        else
                        {
                            drawTrappedChestESP(te.getPos().getX() - mc.thePlayer.lastTickPosX, te.getPos().getY() - mc.thePlayer.lastTickPosY, te.getPos().getZ() - mc.thePlayer.lastTickPosZ);
                        }
                    }
                    
                    if (mc.theWorld.loadedTileEntityList.get(i) instanceof TileEntityEnderChest)
                    {
                        TileEntityEnderChest te = (TileEntityEnderChest) mc.theWorld.loadedTileEntityList.get(i);
                        
                        drawEnderChestESP(te.getPos().getX() - mc.thePlayer.lastTickPosX, te.getPos().getY() - mc.thePlayer.lastTickPosY, te.getPos().getZ() - mc.thePlayer.lastTickPosZ);
                    }
                }
            }
        }
        
        super.onRender3D(event);
    }
}