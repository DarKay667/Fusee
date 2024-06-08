package fusee.client.gui.clickgui;

import java.util.ArrayList;

import fusee.module.Category;
import net.minecraft.client.gui.GuiScreen;

public class ClickGui extends GuiScreen
{
    public static ArrayList<Frame> frames;
    public static int color = -1;
    
    public ClickGui()
    {
        frames = new ArrayList<>();
        int frameX = 5;
        
        for (Category category : Category.values())
        {
            if (category != Category.None && category != Category.Fillers)
            {
                Frame frame = new Frame(category);
                frame.setX(frameX);
                frames.add(frame);
                frameX += frame.getWidth() + 1;
            }
        }
    }
    
    public void initGui() {}
    
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        drawDefaultBackground();
        
        for (Frame frame : frames)
        {
            frame.renderFrame(this.fontRendererObj);
            frame.updatePosition(mouseX, mouseY);
            
            for (Component component : frame.getComponents())
            {
                component.updateComponents(mouseX, mouseY);
            }
        }
    }
    
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton)
    {
        for (Frame frame : frames)
        {
            if (frame.isWithinHeader(mouseX, mouseY) && mouseButton == 0)
            {
                frame.setDrag(true);
                frame.dragX = mouseX - frame.getX();
                frame.dragY = mouseY - frame.getY();
            }
            
            if (frame.isWithinHeader(mouseX, mouseY) && mouseButton == 1)
            {
                frame.setOpen(!frame.isOpen());
            }
            
            if (frame.isOpen() && !frame.getComponents().isEmpty())
            {
                for (Component component : frame.getComponents())
                {
                    component.mouseClicked(mouseX, mouseY, mouseButton);
                }
            }
        }
    }
    
    protected void keyTyped(char typedChar, int keyCode)
    {
        for (Frame frame : frames)
        {
            if (frame.isOpen() && keyCode != 1 && !frame.getComponents().isEmpty())
            {
                for (Component component : frame.getComponents())
                {
                    component.keyTyped(typedChar, keyCode);
                }
            }
        }
        
        if (keyCode == 1)
        {
            this.mc.displayGuiScreen(null);
        }
    }
    
    protected void mouseReleased(int mouseX, int mouseY, int state)
    {
        for (Frame frame : frames) 
        {
            frame.setDrag(false);
        }
        
        for (Frame frame : frames)
        {
            if (frame.isOpen() && !frame.getComponents().isEmpty())
            {
                for (Component component : frame.getComponents())
                {
                    component.mouseReleased(mouseX, mouseY, state);
                }
            }
        }
    }
    
    public boolean doesGuiPauseGame()
    {
        return false;
    }
}