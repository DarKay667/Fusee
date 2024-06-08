package fusee.module.combat;

import fusee.module.Category;
import fusee.module.Module;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockLiquid;
import net.minecraft.client.Minecraft;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C02PacketUseEntity.Action;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;

public class Criticals extends Module
{
    public Criticals()
    {
        super("Criticals", Category.Combat);
    }
    
    public void onEvent(Packet packet)
    {
        if (getState())
        {
            if (canCriticals())
            {
                if (packet instanceof C02PacketUseEntity)
                {
                    C02PacketUseEntity c02PacketUseEntity = (C02PacketUseEntity) getPacket();
                    
                    if (c02PacketUseEntity.getAction() == Action.ATTACK)
                    {
                        this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(this.mc.thePlayer.posX, this.mc.thePlayer.posY + .1625, this.mc.thePlayer.posZ, false));
                        this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(this.mc.thePlayer.posX, this.mc.thePlayer.posY, this.mc.thePlayer.posZ, false));
                        this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(this.mc.thePlayer.posX, this.mc.thePlayer.posY + 4.0E-6, this.mc.thePlayer.posZ, false));
                        this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(this.mc.thePlayer.posX, this.mc.thePlayer.posY, this.mc.thePlayer.posZ, false));
                        this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(this.mc.thePlayer.posX, this.mc.thePlayer.posY + 1.0E-6, this.mc.thePlayer.posZ, false));
                        this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(this.mc.thePlayer.posX, this.mc.thePlayer.posY, this.mc.thePlayer.posZ, false));
                        this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer());
                    }
                }
            }
        }
        
        super.onEvent(packet);
    }
    
    private boolean canCriticals()
    {
        return isInLiquid() && this.mc.thePlayer.onGround;
    }
    
    public static boolean isInLiquid()
    {
        for (int x = MathHelper.floor_double(Minecraft.getMinecraft().thePlayer.getCollisionBoundingBox().minY); x < MathHelper.floor_double(Minecraft.getMinecraft().thePlayer.getCollisionBoundingBox().maxX) + 1; ++x)
        {
            for (int z = MathHelper.floor_double(Minecraft.getMinecraft().thePlayer.getCollisionBoundingBox().minZ); z < MathHelper.floor_double(Minecraft.getMinecraft().thePlayer.getCollisionBoundingBox().maxZ) + 1; ++z)
            {
                BlockPos pos = new BlockPos(x, (int) Minecraft.getMinecraft().thePlayer.getCollisionBoundingBox().minY, z);
                Block block = Minecraft.getMinecraft().theWorld.getBlockState(pos).getBlock();
                
                if (block != null && !(block instanceof BlockAir))
                    return block instanceof BlockLiquid;
            }
        }
        
        return false;
    }
}