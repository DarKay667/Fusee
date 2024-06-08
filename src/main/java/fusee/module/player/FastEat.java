package fusee.module.player;

import fusee.module.Category;
import fusee.module.Module;
import net.minecraft.item.ItemBucketMilk;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemPotion;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;

public class FastEat extends Module
{
    public FastEat()
    {
        super("FastEat", Category.Player);
    }
    
    public void onUpdate()
    {
        if (getState())
        {
            if (this.mc.thePlayer.isEating() && this.mc.thePlayer.getHeldItem().getItemUseAction() != null && this.mc.thePlayer.getHeldItem().getItem() instanceof ItemFood || this.mc.thePlayer.getHeldItem().getItem() instanceof ItemPotion || this.mc.thePlayer.getHeldItem().getItem() instanceof ItemBucketMilk && this.mc.thePlayer.fallDistance < 3.0F)
            {
                for (int i = 0; i < 8; i++)
                {
                    this.mc.thePlayer.sendQueue.addToSendQueue((Packet) new C03PacketPlayer(this.mc.thePlayer.onGround));
                }
            }
        }
        
        super.onUpdate();
    }
}