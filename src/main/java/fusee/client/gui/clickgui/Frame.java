package fusee.client.gui.clickgui;

import java.awt.Color;
import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import fusee.Fusee;
import fusee.module.Category;
import fusee.module.Module;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;

public class Frame
{
    public ArrayList<Component> components;
    public Category category;
    private boolean open, isDragging;
    private int width, x, y, barHeight;
    public int dragX, dragY;
    private long oof;
    
    public Frame(Category category)
    {
        this.components = new ArrayList<>();
        this.category = category;
        this.width = 88;
        this.x = 5;
        this.y = 5;
        this.barHeight = 13;
        this.dragX = 0;
        this.open = false;
        this.isDragging = false;
        int tY = this.barHeight;
        
        for (Module mod : Fusee.instance.proxy.moduleManager.getModuleInCategory(this.category))
        {
            Button modButton = new Button(mod, this, tY);
            this.components.add(modButton);
            tY += 12;
        }
    }
    
    public ArrayList<Component> getComponents()
    {
        return this.components;
    }
    
    public void setX(int newX)
    {
        this.x = newX;
    }
    
    public void setY(int newY)
    {
        this.y = newY;
    }
    
    public void setDrag(boolean drag)
    {
        this.isDragging = drag;
    }
    
    public boolean isOpen()
    {
        return this.open;
    }
    
    public void setOpen(boolean open)
    {
        this.open = open;
    }
    
    public void renderFrame(FontRenderer fr)
    {
        Gui.drawRect(this.x - 1, this.y, this.x + this.width, this.y + this.barHeight, Color.blue.getRGB());
        GL11.glPushMatrix();
        GL11.glScalef(1F, 1F, 1F);
        fr.drawStringWithShadow(this.category.name(), (this.x + 2), (int) ((this.y + 2.5F)), Color.white.getRGB());
        fr.drawStringWithShadow(this.open ? "-" : "+", (this.x + this.width - 10), (int) ((this.y + 2.5F)), Color.white.getRGB());
        GL11.glPopMatrix();
        
        if (this.open && !this.components.isEmpty())
        {
            for (Component component : this.components)
            {
                component.renderComponents();
            }
        }
    }
    
    public void refresh()
    {
        int off = this.barHeight;
        
        for (Component component : this.components)
        {
            component.setOff(off);
            off += component.getHeight();
        }
    }
    
    public int getX()
    {
        return this.x;
    }
    
    public int getY()
    {
        return this.y;
    }
    
    public int getWidth()
    {
        return this.width;
    }
    
    public void updatePosition(int mouseX, int mouseY)
    {
        if (this.isDragging)
        {
            setX(mouseX - this.dragX);
            setY(mouseY - this.dragY);
        }
    }
    
    public boolean isWithinHeader(int x, int y)
    {
        if (x >= this.x && x <= this.x + this.width && y >= this.y && y <= this.y + this.barHeight)
        {
            return true;
        }
        
        return false;
    }
}