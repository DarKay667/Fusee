package fusee.client.gui.clickgui;

import java.awt.Color;
import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import fusee.Fusee;
import fusee.module.Module;
import fusee.module.render.ClickGui;
import fusee.setting.Setting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

public class Button extends Component
{
    public Module mod;
    public Frame parent;
    public int offset;
    private boolean isHovered;
    private ArrayList<Component> subComponents;
    public boolean open;
    private int height;
    
    public Button(Module mod, Frame parent, int offset)
    {
        this.mod = mod;
        this.parent = parent;
        this.offset = offset;
        this.subComponents = new ArrayList<>();
        this.open = false;
        this.height = 12;
        int opY = offset + 12;
        
        if (Fusee.instance.proxy.settingsManager.getSettingsByModule(mod) != null)
        {
            for (Setting s : Fusee.instance.proxy.settingsManager.getSettingsByModule(mod))
            {
                if (s.isCombo())
                {
                    this.subComponents.add(new ModeButton(s, this, mod, opY));
                    opY += 12;
                }
                
                if (s.isSlider())
                {
                    this.subComponents.add(new Slider(s, this, opY));
                    opY += 12;
                }
                
                if (s.isCheck())
                {
                    this.subComponents.add(new Checkbox(s, this, opY));
                    opY += 12;
                }
            }
        }
        
        this.subComponents.add(new VisibleButton(this, mod, opY));
    }
    
    public void setOff(int newOff)
    {
        this.offset = newOff;
        int opY = this.offset + 12;
        
        for (Component component : this.subComponents)
        {
            component.setOff(opY);
            opY += 12;
        }
    }
    
    public void renderComponents()
    {
        Gui.drawRect(this.parent.getX(), this.parent.getY() + this.offset, this.parent.getX() + this.parent.getWidth(), this.parent.getY() + 12 + this.offset, this.isHovered ? (this.mod.getState() ? (new Color(-14540254)).darker().getRGB() : -14540254) : (this.mod.getState() ? (new Color(14, 14, 14)).getRGB() : -15658735));
        GL11.glPushMatrix();
        GL11.glScalef(1F, 1F, 1F);
        (Minecraft.getMinecraft()).fontRendererObj.drawStringWithShadow(this.mod.getName(), (this.parent.getX() + 2), (this.parent.getY() + this.offset + 2), this.mod.getState() ? 10066329 : Color.white.getRGB());
        
        if (this.subComponents.size() > 2)
            (Minecraft.getMinecraft()).fontRendererObj.drawStringWithShadow(this.open ? "-" : "+", (this.parent.getX() + this.parent.getWidth() - 10), (this.parent.getY() + this.offset + 2), Color.white.getRGB());
        
        GL11.glPopMatrix();
        
        if (this.open && !this.subComponents.isEmpty())
        {
            for (Component component : this.subComponents)
            {
                component.renderComponents();
            }
            
            Gui.drawRect(this.parent.getX() + 2, this.parent.getY() + this.offset + 12, this.parent.getX() + 3, this.parent.getY() + this.offset + (this.subComponents.size() + 1) * 12, ClickGui.color);
        }
    }
    
    public int getHeight()
    {
        if (this.open)
        {
            return 12 * (this.subComponents.size() + 1);
        }
        
        return 12;
    }
    
    public void updateComponents(int mouseX, int mouseY)
    {
        this.isHovered = isMouseOnButton(mouseX, mouseY);
        
        if (!this.subComponents.isEmpty())
        {
            for (Component component : this.subComponents)
            {
                component.updateComponents(mouseX, mouseY);
            }
        }
    }
    
    public void mouseClicked(int mouseX, int mouseY, int button)
    {
        if (isMouseOnButton(mouseX, mouseY) && button == 0)
        {
            this.mod.onToggle();
        }
        
        if (isMouseOnButton(mouseX, mouseY) && button == 1)
        {
            this.open = !this.open;
            this.parent.refresh();
        }
        
        for (Component component : this.subComponents)
        {
            component.mouseClicked(mouseX, mouseY, button);
        }
    }
    
    public void mouseReleased(int mouseX, int mouseY, int mouseButton)
    {
        for (Component component : this.subComponents)
        {
            component.mouseReleased(mouseX, mouseY, mouseButton);
        }
    }
    
    public void keyTyped(char typedChar, int key)
    {
        for (Component component : this.subComponents)
        {
            component.keyTyped(typedChar, key);
        }
    }
    
    public boolean isMouseOnButton(int x, int y)
    {
        if (x > this.parent.getX() && x < this.parent.getX() + this.parent.getWidth() && y > this.parent.getY() + this.offset && y < this.parent.getY() + 12 + this.offset)
        {
            return true;
        }
        
        return false;
    }
}