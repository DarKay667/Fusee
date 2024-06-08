package fusee.module.combat;

import java.util.Iterator;

import fusee.module.Category;
import fusee.module.Module;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemSword;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C02PacketUseEntity.Action;

public class KillAura extends Module
{
    private int cps, delay;
    
    public KillAura()
    {
        super("KillAura", Category.Combat);
    }
    
    public void onUpdate()
    {
        if (getState())
        {
            for (Iterator<Entity> entities = this.mc.theWorld.loadedEntityList.iterator(); entities.hasNext();)
            {
                Entity theEntity = entities.next();
                
                if (theEntity instanceof EntityLivingBase)
                {
                    EntityLivingBase e = (EntityLivingBase) theEntity;
                    
                    if (e instanceof EntityPlayerSP)
                        continue;
                    
                    if (this.mc.thePlayer.getDistanceToEntity((Entity) e) <= this.mc.playerController.getBlockReachDistance())
                    {
                        if (e != null && e != this.mc.thePlayer && this.mc.pointedEntity != null && !e.isInvisible() && e.isEntityAlive())
                        {
                            this.mc.playerController.attackEntity(this.mc.thePlayer, e);
                            this.mc.thePlayer.swingItem();
                            this.mc.thePlayer.sendQueue.addToSendQueue((Packet) new C02PacketUseEntity((Entity) e, Action.ATTACK));
                            continue;
                        }
                        
                        if ((this.mc.pointedEntity != null && this.mc.thePlayer.getHeldItem() != null && this.mc.thePlayer.getHeldItem().getItem() instanceof ItemSword))
                        {
                            this.mc.thePlayer.sendQueue.addToSendQueue((Packet) new C08PacketPlayerBlockPlacement());
                        }
                    }
                }
            }
        }
        
        super.onUpdate();
    }
}