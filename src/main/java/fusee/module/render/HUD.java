package fusee.module.render;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Objects;

import org.lwjgl.opengl.GL11;

import fusee.Fusee;
import fusee.module.Category;
import fusee.module.Module;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;

public class HUD extends Module
{
    public HUD()
    {
        super("HUD", Category.Render);
        setState(true);
    }
    
    public void onRender2D()
    {
        if (!getState())
        {
            return;
        }
        
        ScaledResolution sr = new ScaledResolution(this.mc);
        
        if (!this.mc.gameSettings.showDebugInfo && Objects.nonNull(this.mc.thePlayer) && Objects.nonNull(this.mc.theWorld))
        {
            renderArrayList(sr);
            Gui.drawRect(0, 0, 0, 0, 0);
            GL11.glPushMatrix();
            GL11.glScalef(1.5F, 1.5F, 1.5F);
            drawStringWithShadow("Fusee", 2, 2, 0.6F);
            GL11.glScalef(1.0F, 1.0F, 1.0F);
            GL11.glPopMatrix();
            this.fr.drawStringWithShadow("1.1", (int) (this.fr.getStringWidth("Fusee") * 1.5F) + 5, 2, Color.lightGray.getRGB());
        }
        
        super.onRender2D();
    }
    
    private void renderArrayList(ScaledResolution sr)
    {
        int index = 0, yCount = 4, right = sr.getScaledWidth();
        long x = 0L;
        ArrayList<Module> module = Fusee.instance.proxy.moduleManager.getModules();
        
        for (Module modules : Fusee.instance.proxy.moduleManager.getModules())
        {
            if (!modules.getState()  || modules.getCategory() == Category.None)
            {
                continue;
            }
            
            if (modules.getCategory() == Category.Fillers)
            {
                return;
            }
            
            drawRect((right - this.fr.getStringWidth(modules.getName()) - 2), yCount, (right - 20), (yCount + 15), 0);
            
            if (modules.getCategory() == Category.Combat || modules.getCategory() == Category.Movement || modules.getCategory() == Category.Player || modules.getCategory() == Category.Render || modules.getCategory() == Category.World || modules.getCategory() == Category.AdvancedAntiCheat || modules.getCategory() == Category.Cubecraft)
            {
                this.fr.drawStringWithShadow("" + modules.getName(), right - this.fr.getStringWidth(modules.getName()) - 1, yCount, Color.green.getRGB());
            }
            
            yCount += 10;
            index++;
            x++;
        }
    }
    
    private void drawRect(float xStart, float yStart, float xEnd, float yEnd, int color)
    {
        Gui.drawRect((int) xStart, (int) yStart, (int) xEnd, (int) yEnd, color);
        
        float alpha = (color >> 24 & 0xFF) / 255.0F, red = (color >> 16 & 0xFF) / 255.0F, green = (color >> 8 & 0xFF) / 255.0F, blue = (color & 0xFF) / 255.0F;
        
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glPushMatrix();
        GL11.glColor4f(red, green, blue, alpha);
        GL11.glBegin(7);
        GL11.glVertex2d(xEnd, yStart);
        GL11.glVertex2d(xStart, yStart);
        GL11.glVertex2d(xStart, yEnd);
        GL11.glVertex2d(xEnd, yEnd);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
    }
    
    public void drawStringWithShadow(String string, int x, int y, float brightness)
    {
        int xPos = x;
        
        for (int i = 0; i < string.length(); i++)
        {
            String s = string.charAt(i) + "";
            this.fr.drawStringWithShadow(s, xPos, y, effect(i * 3500000L, brightness, 100).getRGB());
            xPos += this.fr.getStringWidth(s);
        }
    }
    
    private Color effect(long offset, float brightness, int speed)
    {
        float hue = (float) (System.nanoTime() + offset * speed) / 1.0E10F % 1.0F;
        long color = Long.parseLong(Integer.toHexString(Integer.valueOf(Color.HSBtoRGB(hue, brightness, 1.0F)).intValue()), 16);
        Color c = new Color((int) color);
        return new Color(c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F, c.getAlpha() / 255.0F);
    }
}