package fusee.module.player;

import fusee.module.Category;
import fusee.module.Module;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.network.play.client.C03PacketPlayer;

public class DeathBypass extends Module
{
    public boolean activate;
    
    public DeathBypass()
    {
        super("DeathBypass", Category.Player);
        this.activate = false;
    }
    
    public void onUpdate()
    {
        if (getState())
        {
            if (this.mc.currentScreen instanceof GuiGameOver)
            {
                this.mc.displayGuiScreen((GuiScreen) null);
                this.mc.thePlayer.isDead = false;
                this.mc.thePlayer.setHealth(20.0F);
                this.activate = true;
                this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(false));
            }
        }
        
        super.onUpdate();
    }
    
    public void onDisable()
    {
        if (this.activate)
        {
            this.activate = false;
            this.mc.thePlayer.respawnPlayer();
        }
        
        super.onDisable();
    }
}