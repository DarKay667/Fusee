package fusee.module.combat;

import fusee.module.Category;
import fusee.module.Module;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class AutoArmor extends Module
{
    private int[] helmet, chestplate, leggings, boots;
    private int delay;
    
    public AutoArmor()
    {
        super("AutoArmor", Category.Combat);
        
        helmet = new int[] {310, 306, 314, 302, 298};
        chestplate = new int[] {311, 307, 315, 303, 299};
        leggings = new int[] {312, 308, 316, 304, 300};
        boots = new int[] {313, 309, 317, 305, 301};
    }
    
    public void onUpdate()
    {
        if (getState())
        {
            int item = -1;
            delay += 1;
            
            if (delay >= 10)
            {
                if (this.mc.thePlayer.inventory.armorInventory[0] == null)
                {
                    int[] boots;
                    int length = (boots = this.boots).length;
                    
                    for (int i = 0; i < length; i++)
                    {
                        int id = boots[i];
                        
                        if (getItem(id) != -1)
                        {
                            item = getItem(id);
                            break;
                        }
                    }
                }
                
                if (this.mc.thePlayer.inventory.armorInventory[1] == null)
                {
                    int[] leggings;
                    int length = (leggings = this.leggings).length;
                    
                    for (int i = 0; i < length; i++)
                    {
                        int id = leggings[i];
                        
                        if (getItem(id) != -1)
                        {
                            item = getItem(id);
                            break;
                        }
                    }
                }
                
                if (this.mc.thePlayer.inventory.armorInventory[2] == null)
                {
                    int[] chestplate;
                    int length = (chestplate = this.chestplate).length;
                    
                    for (int i = 0; i < length; i++)
                    {
                        int id = chestplate[i];
                        
                        if (getItem(id) != -1)
                        {
                            item = getItem(id);
                            break;
                        }
                    }
                }
                
                if (this.mc.thePlayer.inventory.armorInventory[3] == null)
                {
                    int[] helmet;
                    int length = (helmet = this.helmet).length;
                    
                    for (int i = 0; i < length; i++)
                    {
                        int id = helmet[i];
                        
                        if (getItem(id) != -1)
                        {
                            item = getItem(id);
                            break;
                        }
                    }
                }
                
                if (item != -1)
                {
                    this.mc.playerController.windowClick(0, item, 0, 1, this.mc.thePlayer);
                    delay = 0;
                }
            }
        }
        
        super.onUpdate();
    }
    
    private int getItem(int id)
    {
        for (int i = 9; i < 45; i++)
        {
            ItemStack item = this.mc.thePlayer.inventoryContainer.getSlot(i).getStack();
            
            if (item != null && Item.getIdFromItem(item.getItem()) == id)
                return i;
        }
        
        return -1;
    }
}