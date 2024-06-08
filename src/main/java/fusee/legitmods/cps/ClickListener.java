package fusee.legitmods.cps;

import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

public class ClickListener
{
    private boolean hasClickedThisTick = false;
    
    @SubscribeEvent
    public void onMouse(MouseEvent event)
    {
        if (event.button != 0)
        {
            return;
        }
        
        if (event.buttonstate)
        {
            this.hasClickedThisTick = true;
            CPSMod.addClick();
        }
    }
    
    @SubscribeEvent
    public void onClientTick(ClientTickEvent event)
    { 
        this.hasClickedThisTick = false;
    }
}