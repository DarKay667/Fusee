package fusee.module.world;

import java.util.HashMap;
import java.util.Objects;

import fusee.Fusee;
import fusee.module.Category;
import fusee.module.Module;
import fusee.module.render.HUD;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;

public class UnclaimFinder extends Module
{
    private HashMap<String, Integer> values = new HashMap<String, Integer>();
    
    public UnclaimFinder()
    {
        super("UnclaimFinder", Category.World);
    }
    
    public void onRender2D()
    {
        if (getState())
        {
            if (!this.mc.gameSettings.showDebugInfo && Objects.nonNull(this.mc.thePlayer) && Objects.nonNull(this.mc.theWorld) && Fusee.instance.proxy.moduleManager.getModule(HUD.class).getState())
            {                
                this.values.clear();
                this.values.put("Chests", Integer.valueOf(0));
                this.values.put("ModdedChests", Integer.valueOf(0));
                
                for (Object o : this.mc.theWorld.loadedTileEntityList)
                {
                    if (o instanceof TileEntity)
                    {
                        TileEntity te = (TileEntity) o;
                        
                        if (te.getClass().toString().toLowerCase().contains("net.minecraft.tileentity.tileentitychest") || (te.getClass().toString().toLowerCase().contains("net.minecraft.tileentity.tileentityenderchest")))
                        {
                            this.values.put("Chests", Integer.valueOf(((Integer) this.values.get("Chests")).intValue() + 1));
                            continue;
                        }
                        
                        if (te.getClass().toString().toLowerCase().contains("chest"))
                        {
                            this.values.put("ModdedChests", Integer.valueOf(((Integer) this.values.get("ModdedChests")).intValue() + 1));
                        }
                    }
                }
                
                int x = 2, y = 20;
                
                this.fr.drawStringWithShadow("Chests: ", x, y, -1);
                this.fr.drawStringWithShadow(EnumChatFormatting.RED + "" + this.values.get("Chests") + "%", x + this.fr.getStringWidth("Chests: ") / 2 - this.fr.getStringWidth((new StringBuilder()).append(this.values.get("Chests")).append("%").toString()) / 2, y + 10, -1);
                x += this.fr.getStringWidth("Chests: ") + 2;
                
                this.fr.drawStringWithShadow("ModdedChests: ", x, y, -1);
                this.fr.drawStringWithShadow(EnumChatFormatting.RED + "" + this.values.get("ModdedChests") + "%", x + this.fr.getStringWidth("ModdedChests: ") / 2 - this.fr.getStringWidth((new StringBuilder()).append(this.values.get("ModdedChests")).append("%").toString()) / 2, y + 10, -1);
                x += this.fr.getStringWidth("ModdedChests: ") + 2;
            }
        }
        
        super.onRender2D();
    }
}