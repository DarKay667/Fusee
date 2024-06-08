package fusee.legitmods.blur;

import javax.annotation.Nonnull;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.Tessellator;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.GuiConfigEntries;

public class BlurConfigGui extends GuiConfig
{
    public BlurConfigGui(GuiScreen parentScreen)
    {
        super(parentScreen, (new ConfigElement(Blur.instance.config.getCategory("general"))).getChildElements(), "blur", false, false, "Blur Config");
    }
    
    public void initGui() //func_73866_w_ //initGui
    {
        if (this.entryList == null || this.needsRefresh)
        {
            this.entryList = new GuiConfigEntries(this, this.mc)
            {
                protected void drawContainerBackground(@Nonnull Tessellator tessellator)
                {
                    if (this.mc.theWorld == null)
                    {
                        super.drawContainerBackground(tessellator);
                    }
                }
            };
            this.needsRefresh = false;
        }
        
        super.initGui();
    }
    
    public void drawDefaultBackground() //func_146276_q_ //drawDefaultBackground
    {
        drawWorldBackground(0);
    }
}