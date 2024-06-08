package fusee.module.render;

import fusee.module.Category;
import fusee.module.Module;
import net.minecraft.client.gui.GuiScreen;

public class ClickGui extends Module
{
    public static fusee.client.gui.clickgui.ClickGui gui;
    public static int color;
    
    public ClickGui()
    {
        super("ClickGui", Category.None);
    }
    
    public void state()
    {
        if (this.gui == null)
        {
            this.gui = new fusee.client.gui.clickgui.ClickGui();
        }
        
        this.mc.displayGuiScreen((GuiScreen) gui);
        
        super.state();
    }
}