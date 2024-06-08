package fusee.legitmods.sidebar;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class GuiScreenSidebarMod extends GuiScreen
{
    private SidebarMod mod;
    private boolean dragging;
    private int lastMouseX, lastMouseY, lastAddX, lastAddY, clickCounter;
    private long lastClick;
    
    public GuiScreenSidebarMod(SidebarMod mod)
    {
        this.mod = mod;
    }
    
    public void initGui()
    {
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 6 + 168, "Display: " + (!this.mod.isHideSidebar() ? "Yes" : "No")));
        this.buttonList.add(new GuiButton(2, this.width / 2 - 100, this.height / 6 + 144, "Red Numbers: " + (!this.mod.isHideRedNumbers() ? "Yes" : "No")));
        this.buttonList.add(new GuiButton(3, this.width / 2 - 100, this.height / 6 + 120, "Reset Position"));
    }

    protected void actionPerformed(GuiButton guiButton)
    {
        if (guiButton.enabled)
        {
            switch (guiButton.id)
            {
                case 1:
                    this.mod.setHideSidebar(!this.mod.isHideSidebar());
                    guiButton.displayString = "Display: " + (!this.mod.isHideSidebar() ? "Yes" : "No");
                    break;
                    
                case 2:
                    this.mod.setHideRedNumbers(!this.mod.isHideRedNumbers());
                    guiButton.displayString = "Red Numbers: " + (!this.mod.isHideRedNumbers() ? "Yes" : "No");
                    break;
                    
                case 3:
                    this.mod.setAddX(0);
                    this.mod.setAddY(0);
                    break;
            }
        }
    }
    
    protected void mouseClicked(int mouseX, int mouseY, int buttonClicked) throws IOException
    {
        super.mouseClicked(mouseX, mouseY, buttonClicked);
        
        if (buttonClicked == 0)
        {
            int minX = this.mod.getAddX() + this.mod.getGuiIngameSidebarMod().getMinX();
            int maxX = this.mod.getAddX() + this.mod.getGuiIngameSidebarMod().getMaxX();
            int minY = this.mod.getAddY() + this.mod.getGuiIngameSidebarMod().getMinY();
            int maxY = this.mod.getAddY() + this.mod.getGuiIngameSidebarMod().getMaxY();
            
            if (!this.dragging && mouseX > minX && mouseX < maxX && mouseY > minY && mouseY < maxY)
            {
                if (System.currentTimeMillis() - this.lastClick < 300L)
                {
                    this.clickCounter++;
                    
                    if (this.clickCounter > 1)
                    {
                        this.mod.setRainbow(!this.mod.isRainbow());
                        this.clickCounter = 0;
                    }
                }
                
                else
                {
                    this.clickCounter = 0;
                }
                
                this.lastClick = System.currentTimeMillis();
            }
        }
    }
    
    protected void mouseClickMove(int mouseX, int mouseY, int lastButtonClicked, long timeSinceMouseClick)
    {
        int minX = this.mod.getAddX() + this.mod.getGuiIngameSidebarMod().getMinX();
        int maxX = this.mod.getAddX() + this.mod.getGuiIngameSidebarMod().getMaxX();
        int minY = this.mod.getAddY() + this.mod.getGuiIngameSidebarMod().getMinY();
        int maxY = this.mod.getAddY() + this.mod.getGuiIngameSidebarMod().getMaxY();
        
        if (!this.dragging && mouseX > minX && mouseX < maxX && mouseY > minY && mouseY < maxY)
        {
            this.dragging = true;
            
            this.lastMouseX = mouseX;
            this.lastMouseY = mouseY;
            
            this.lastAddX = this.mod.getAddX();
            this.lastAddY = this.mod.getAddY();
        }
        
        if (this.dragging)
        { 
            this.mod.setAddX(this.lastAddX + mouseX - this.lastMouseX);
            this.mod.setAddY(this.lastAddY + mouseY - this.lastMouseY);
        }
    }
    
    protected void mouseReleased(int mouseX, int mouseY, int which) //mouseMovedOrUp
    {
        super.mouseReleased(mouseX, mouseY, which);
        
        if (which != -1)
        {
            this.dragging = false;
            this.lastMouseX = 0;
            this.lastMouseY = 0;
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