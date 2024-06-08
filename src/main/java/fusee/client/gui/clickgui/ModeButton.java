package fusee.client.gui.clickgui;

import org.lwjgl.opengl.GL11;

import fusee.module.Module;
import fusee.setting.Setting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

public class ModeButton extends Component
{
    private boolean hovered;
    private Button parent;
    private Setting set;
    private int offset, x, y, modeIndex;
    private Module mod;
    
    public ModeButton(Setting set, Button button, Module mod, int offset)
    {
        this.set = set;
        this.parent = button;
        this.mod = mod;
        this.x = button.parent.getX() + button.parent.getWidth();
        this.y = button.parent.getY() + button.offset;
        this.offset = offset;
        this.modeIndex = 0;
    }
    
    public void setOff(int newOff)
    {
        this.offset = newOff;
    }
    
    public void renderComponents()
    {
        Gui.drawRect(this.parent.parent.getX() + 2, this.parent.parent.getY() + this.offset, this.parent.parent.getX() + this.parent.parent.getWidth() * 1, this.parent.parent.getY() + this.offset + 12, this.hovered ? -14540254 : -15658735);
        Gui.drawRect(this.parent.parent.getX(), this.parent.parent.getY() + this.offset, this.parent.parent.getX() + 2, this.parent.parent.getY() + this.offset + 12, -15658735);
        GL11.glPushMatrix();
        GL11.glScalef(0.5F, 0.5F, 0.5F);
        (Minecraft.getMinecraft()).fontRendererObj.drawStringWithShadow("Mode: " + this.set.getValueString(), (this.parent.parent.getX() + 7) * 2, (this.parent.parent.getY() + this.offset + 2) * 2 + 5, -1);
        GL11.glPopMatrix();
    }
    
    public void updateComponents(int mouseX, int mouseY)
    {
        this.hovered = isMouseOnButton(mouseX, mouseY);
        this.y = this.parent.parent.getY() + this.offset;
        this.x = this.parent.parent.getX();
    }
    
    public void mouseClicked(int mouseX, int mouseY, int button)
    {
        if (isMouseOnButton(mouseX, mouseY) && button == 0 && this.parent.open)
        {
            int maxIndex = this.set.getOptions().size();
            
            if (this.modeIndex + 1 > maxIndex)
            {
                this.modeIndex = 0;
            }
            
            else
            {
                this.modeIndex++;
            }
            
            this.set.setValueString(this.set.getOptions().get(this.modeIndex));
        }
    }
    
    public boolean isMouseOnButton(int x, int y)
    {
        if (x > this.x && x < this.x + 88 && y > this.y && y < this.y + 12)
        {
            return true;
        }
        
        return false;
    }
}