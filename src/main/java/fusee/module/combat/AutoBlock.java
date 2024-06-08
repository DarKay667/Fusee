package fusee.module.combat;

import fusee.Fusee;
import fusee.module.Category;
import fusee.module.Module;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;

public class AutoBlock extends Module
{
    public AutoBlock()
    {
        super("AutoBlock", Category.Combat);
    }
    
    public void onUpdate()
    {
        if (getState())
        {
            if (!Fusee.instance.proxy.moduleManager.getModule(KillAura.class).getState() && (this.mc.gameSettings.keyBindAttack.isPressed() && this.mc.thePlayer.getCurrentEquippedItem() != null && this.mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemSword))
            {
                ItemStack itemStack = this.mc.thePlayer.getCurrentEquippedItem();
                itemStack.useItemRightClick((World) this.mc.theWorld, (EntityPlayer) this.mc.thePlayer);
            }
        }
        
        super.onUpdate();
    }
}