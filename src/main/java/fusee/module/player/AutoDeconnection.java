package fusee.module.player;

import fusee.module.Category;
import fusee.module.Module;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.WorldClient;

public class AutoDeconnection extends Module
{
    public AutoDeconnection()
    {
        super("AutoDeconnection", Category.Player);
    }
    
    public void onUpdate()
    {
        if (getState())
        {
            if (this.mc.thePlayer.getHealth() < 2.0F)
            {
                boolean flag = this.mc.isIntegratedServerRunning();
                this.mc.theWorld.sendQuittingDisconnectingPacket();
                this.mc.loadWorld((WorldClient) null);
                
                if (flag)
                {
                    this.mc.displayGuiScreen((GuiScreen) new GuiMainMenu());
                }
            }
        }
        
        super.onUpdate();
    }
}