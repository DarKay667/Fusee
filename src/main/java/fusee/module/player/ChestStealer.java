package fusee.module.player;

import fusee.module.Category;
import fusee.module.Module;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class ChestStealer extends Module
{
    private int delay;
    
    public ChestStealer()
    {
        super("ChestStealer", Category.Player);
        this.delay = 0;
    }
    
    public void onUpdate()
    {
        if (getState())
        {
            if (!(this.mc.inGameHasFocus))
            {
                if (this.mc.currentScreen instanceof GuiChest)
                {
                    if (!isContainerEmpty(this.mc.thePlayer.openContainer))
                    {
                        int slotId = getNextSlotInContainer(this.mc.thePlayer.openContainer);
                        
                        if (this.delay >= 5)
                        {
                            this.mc.playerController.windowClick(this.mc.thePlayer.openContainer.windowId, slotId, 0, 1, (EntityPlayer) this.mc.thePlayer);
                            this.delay = 0;
                        }
                        
                        this.delay++;
                    }
                    
                    else
                    {
                        this.mc.thePlayer.closeScreen();
                    }
                }
            }
        }
        
        super.onUpdate();
    }
    
    private int getNextSlotInContainer(Container container)
    {
        for (int n = (container.inventorySlots.size() == 90) ? 54 : 27, slotAmount = n, i = 0; i < slotAmount; i++)
        {
            if (container.getInventory().get(i) != null)
            {
                return i;
            }
        }
        
        return -1;
    }
    
    private boolean isContainerEmpty(Container container)
    {
        for (int n = (container.inventorySlots.size() == 90) ? 54 : 17, slotAmount = n, i = 0; i < slotAmount; i++)
        {
            if (container.getSlot(i).getHasStack())
            {
                return false;
            }
        }
        
        return true;
    }
}