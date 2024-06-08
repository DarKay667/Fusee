package fusee.client.gui.clickgui;

import org.lwjgl.opengl.GL11;

import fusee.setting.Setting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

public class Checkbox extends Component
{
    private boolean hovered;
    private Setting op;
    private Button parent;
    private int offset, x, y;
    
    public Checkbox(Setting option, Button button, int offset)
    {
        this.op = option;
        this.parent = button;
        this.x = button.parent.getX() + button.parent.getWidth();
        this.y = button.parent.getY() + button.offset;
        this.offset = offset;
    }
    
    public void renderComponents()
    {
        Gui.drawRect(this.parent.parent.getX() + 2, this.parent.parent.getY() + this.offset, this.parent.parent.getX() + this.parent.parent.getWidth() * 1, this.parent.parent.getY() + this.offset + 12, this.hovered ? -14540254 : -15658735);
        Gui.drawRect(this.parent.parent.getX(), this.parent.parent.getY() + this.offset, this.parent.parent.getX() + 2, this.parent.parent.getY() + this.offset + 12, -15658735);
        GL11.glPushMatrix();
        GL11.glScalef(0.5F, 0.5F, 0.5F);
        
        (Minecraft.getMinecraft()).fontRendererObj.drawStringWithShadow(this.op.getName(), (this.parent.parent.getX() + 10 + 4) * 2 + 5, (this.parent.parent.getY() + this.offset + 2) * 2 + 4, -1);
        
        GL11.glPopMatrix();
        Gui.drawRect(this.parent.parent.getX() + 3 + 4, this.parent.parent.getY() + this.offset + 3, this.parent.parent.getX() + 9 + 4, this.parent.parent.getY() + this.offset + 9, -6710887);
        
        if (this.op.getValueBoolean())
        {
            Gui.drawRect(this.parent.parent.getX() + 4 + 4, this.parent.parent.getY() + this.offset + 4, this.parent.parent.getX() + 8 + 4, this.parent.parent.getY() + this.offset + 8, -10066330); 
        }
    }
    
    public void setOff(int newOff)
    {
        this.offset = newOff;
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
            this.op.setValueBoolean(!this.op.getValueBoolean());
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