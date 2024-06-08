package fusee.module.player;

import fusee.module.Category;
import fusee.module.Module;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;

public class StaffDetector extends Module
{
    public StaffDetector()
    {
        super("StaffDetector", Category.Player);
    }
    
    public void onUpdate()
    {
        if (getState())
        {
            for (Object o : this.mc.theWorld.playerEntities)
            {
                if (o instanceof EntityLivingBase && o != this.mc.thePlayer && !((EntityLivingBase) o).getName().isEmpty() && !((EntityLivingBase) o).getDisplayName().equals(" ") && ((EntityLivingBase) o).isInvisibleToPlayer((EntityPlayer) this.mc.thePlayer))
                {
                    this.mc.thePlayer.addChatMessage(new ChatComponentText("A staff member is near you"));
                }
            }
        }
        
        super.onUpdate();
    }
}