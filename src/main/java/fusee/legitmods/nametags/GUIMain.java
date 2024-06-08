package fusee.legitmods.nametags;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fml.client.config.GuiSlider;

public class GUIMain extends GuiScreen
{
    private GuiButton buttonSelfTag, buttonBackgroundColor;
    private GuiSlider sliderOffset, sliderScale, red, green, blue, alpha;
    private Integer[] positionY;
    private int positionX;
    
    public void initGui()
    {
        int buttonLength = 180;
        centerButtons(5, buttonLength);
        
        this.buttonList.add(this.buttonSelfTag = new GuiButton(1, this.positionX, this.positionY[0].intValue(), buttonLength, 20, "Own nametag: " + getSelfTag()));
        this.buttonList.add(this.alpha = new GuiSlider(7, this.positionX, this.positionY[1].intValue(), buttonLength, 20, "Background alpha: ", "", 0.0D, 25.0D, (NameTagRenderer.alpha * 100.0F), false, true));
        this.buttonList.add(this.sliderOffset = new GuiSlider(2, this.positionX, this.positionY[3].intValue(), buttonLength, 20, "Y Offset: ", "", -20.0D, 0.0D, NameTagRenderer.getOffset(), false, true));
        this.buttonList.add(this.sliderScale = new GuiSlider(3, this.positionX, this.positionY[4].intValue(), buttonLength, 20, "Scale: ", "%", 0.0D, 100.0D, NameTagRenderer.getScale(), false, true));
    }
    
    private String getSelfTag()
    {
        if (NameTagRenderer.selftag)
        {
            return EnumChatFormatting.DARK_GREEN + "enabled";
        }
        
        return EnumChatFormatting.RED + "disabled";
    }
    
    public void centerButtons(int amount, int buttonLength)
    {
        this.positionX = this.width / 2 - buttonLength / 2;
        this.positionY = new Integer[amount];
        
        int center = (this.height + amount * 24) / 2;
        int buttonStarts = center - amount * 24;
        
        for (int i = 0; i != amount; i++)
        {
            this.positionY[i] = Integer.valueOf(buttonStarts + 24 * i);
        }
    }
    
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        drawCenteredString((Minecraft.getMinecraft()).fontRendererObj, "Player nametags", this.width / 2, this.positionY[2].intValue() + 7, 16777215);
        
        if (this.sliderOffset.dragging)
        {
            NameTagRenderer.setOffset(this.sliderOffset.getValueInt());
        }
        
        if (this.alpha.dragging)
        {
            NameTagRenderer.setAlpha(this.alpha.getValueInt());
        }
        
        if (this.sliderScale.dragging)
        {
            NameTagRenderer.setScale(this.sliderScale.getValueInt());
        }
    }
    
    protected void actionPerformed(GuiButton button) throws IOException
    {
        switch (button.id)
        {
            case 1:
                NameTagRenderer.selftag = !NameTagRenderer.selftag;
                button.displayString = "Own nametag: " + getSelfTag();
                break;
        }
    }
    
    public void onGuiClosed()
    {
        Main.config.get("Background", "Alpha", 25).set(this.alpha.getValueInt());
        Main.config.get("Player nametags", "Scale", 100).set(this.sliderScale.getValueInt());
        Main.config.get("Player nametags", "Y offset", 0).set(this.sliderOffset.getValueInt());
        Main.config.get("Other", "Own nametag", false).set(NameTagRenderer.selftag);
        Main.config.save();
    }
}