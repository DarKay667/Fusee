package fusee.legitmods.reachdisplay;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class ReachDisplayGuiScreen extends GuiScreen
{
    private ReachDisplayMod mod;
    private boolean dragging;
    private int lastMouseX, lastMouseY, lastAddX, lastAddY;
    
    public ReachDisplayGuiScreen(ReachDisplayMod mod)
    {
        this.mod = mod;
    }
    
    public void initGui()
    {
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 6 + 72, "Enabled: " + (this.mod.isEnabled() ? "Yes" : "No")));
    }
    
    protected void actionPerformed(GuiButton guiButton)
    {
        if (guiButton.enabled)
        {
            String colorName;
            
            switch (guiButton.id) {
                case 1:
                    this.mod.setEnabled(!this.mod.isEnabled());
                    guiButton.displayString = "Enabled: " + (this.mod.isEnabled() ? "Yes" : "No");
                    break;
            }
        }
    }
    
    protected void mouseReleased(int mouseX, int mouseY, int state)
    {
        if (state == 0)
        {
            this.dragging = false;
        }
    }
    
    protected void mouseClickMove(int mouseX, int mouseY, int lastButtonClicked, long timeSinceMouseclick)
    {
        if (!this.dragging && this.mod.isEnabled() && mouseX > this.mod.getMinX() && mouseX < this.mod.getMaxX() && mouseY > this.mod.getMinY() && mouseY < this.mod.getMaxY())
        {
            this.dragging = true;
            this.lastMouseX = mouseX;
            this.lastMouseY = mouseY;
            this.lastAddX = this.mod.getAddX();
            this.lastAddY = this.mod.getAddY();
            
        } else if (this.dragging) {
            this.mod.setAddX(this.lastAddX + mouseX - this.lastMouseX);
            this.mod.setAddY(this.lastAddY + mouseY - this.lastMouseY);
        }
    }
    
    public void onGuiClosed()
    {
        this.mod.saveConfig();
    }
    
    public boolean doesGuiPauseGame()
    {
        return false;
    }
}