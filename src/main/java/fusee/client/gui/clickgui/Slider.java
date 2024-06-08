package fusee.client.gui.clickgui;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.lwjgl.opengl.GL11;

import fusee.setting.Setting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

public class Slider extends Component
{
    private boolean hovered;
    private Setting set;
    private Button parent;
    private int offset;
    private int x;
    private int y;
    private boolean dragging = false;
    private double renderWidth;
    
    public Slider(Setting value, Button button, int offset)
    {
        this.set = value;
        this.parent = button;
        this.x = button.parent.getX() + button.parent.getWidth();
        this.y = button.parent.getY() + button.offset;
        this.offset = offset;
    }
     
    
    public void renderComponents()
    {
        Gui.drawRect(this.parent.parent.getX() + 2, this.parent.parent.getY() + this.offset, this.parent.parent.getX() + this.parent.parent.getWidth(), this.parent.parent.getY() + this.offset + 12, this.hovered ? -14540254 : -15658735);
        int drag = (int)(this.set.getValueDouble() / this.set.getMax() * this.parent.parent.getWidth());
        Gui.drawRect(this.parent.parent.getX() + 2, this.parent.parent.getY() + this.offset, this.parent.parent.getX() + (int)this.renderWidth, this.parent.parent.getY() + this.offset + 12, this.hovered ? -11184811 : -12303292);
        Gui.drawRect(this.parent.parent.getX(), this.parent.parent.getY() + this.offset, this.parent.parent.getX() + 2, this.parent.parent.getY() + this.offset + 12, -15658735);
        GL11.glPushMatrix();
        GL11.glScalef(0.5F, 0.5F, 0.5F);
        (Minecraft.getMinecraft()).fontRendererObj.drawStringWithShadow(this.set.getName() + ": " + this.set.getValueDouble(), this.parent.parent.getX() * 2 + 15, (this.parent.parent.getY() + this.offset + 2) * 2 + 5, -1);
        GL11.glPopMatrix();
    }
     
    
    public void setOff(int newOff)
    {
        this.offset = newOff;
    }
     
    
    public void updateComponents(int mouseX, int mouseY)
    {
        this.hovered = (isMouseOnButtonD(mouseX, mouseY) || isMouseOnButtonI(mouseX, mouseY));
        this.y = this.parent.parent.getY() + this.offset;
        this.x = this.parent.parent.getX();
        
        double diff = Math.min(88, Math.max(0, mouseX - this.x));
          
        double min = this.set.getMin();
        double max = this.set.getMax();
          
        this.renderWidth = 88.0D * (this.set.getValueDouble() - min) / (max - min);
      
        if (this.dragging)
        {
            if (diff == 0.0D) 
            {
                this.set.setValueDouble(this.set.getMin());
            } 
            else 
            {   
                double newValue = roundToPlace(diff / 88.0D * (max - min) + min, 2);
                this.set.setValueDouble(newValue);
            } 
        }
    }
    
    private static double roundToPlace(double value, int places)
    {
        if (places < 0) 
        {
            throw new IllegalArgumentException();
        }
        
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
     
    
    public void mouseClicked(int mouseX, int mouseY, int button)
    {
        if (isMouseOnButtonD(mouseX, mouseY) && button == 0 && this.parent.open) 
        {
            this.dragging = true;
        }
        
        if (isMouseOnButtonI(mouseX, mouseY) && button == 0 && this.parent.open)
        {
            this.dragging = true;
        }
    }
     
    
    public void mouseReleased(int mouseX, int mouseY, int mouseButton)
    {
        this.dragging = false;
    }
    
    public boolean isMouseOnButtonD(int x, int y)
    {
        if (x > this.x && x < this.x + this.parent.parent.getWidth() / 2 + 1 && y > this.y && y < this.y + 12) 
        {
            return true;
        }
        return false;
    }
    
    public boolean isMouseOnButtonI(int x, int y) 
    {
        if (x > this.x + this.parent.parent.getWidth() / 2 && x < this.x + this.parent.parent.getWidth() && y > this.y && y < this.y + 12)
        {
            return true;
        }
        return false;
    }
}