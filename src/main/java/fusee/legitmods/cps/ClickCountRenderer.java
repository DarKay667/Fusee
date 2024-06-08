package fusee.legitmods.cps;

import java.awt.Color;
import java.util.Objects;

import org.lwjgl.opengl.GL11;

import fusee.Fusee;
import fusee.module.render.HUD;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClickCountRenderer
{
    private final Minecraft mc;
    int index = 0;
    long x = 0L;
    
    public static Color rainbowEffect(float f, float fade)
    {
        float hue = ((float) System.nanoTime() + f) / 4.0E9F % 1.0F;
        long color = Long.parseLong(Integer.toHexString(Integer.valueOf(Color.HSBtoRGB(hue, 1.0F, 1.0F)).intValue()), 16);
        Color c = new Color((int) color);
        return new Color(c.getRed() / 255.0F * fade, c.getGreen() / 255.0F * fade, c.getBlue() / 255.0F * fade, c.getAlpha() / 255.0F);
    }
    
    public ClickCountRenderer()
    {
        this.mc = Minecraft.getMinecraft();
    }
    
    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent event)
    {
        int cpsWidth;
        
        if (event.type != ElementType.EXPERIENCE || event.isCancelable())
        {
            return;
        }
        
        if (!CPSMod.enabled || (this.mc.currentScreen != null && !(this.mc.currentScreen instanceof GuiCPS) || this.mc.gameSettings.showDebugInfo))
        {
            return;
        }
        
        if (this.mc.currentScreen != null || this.mc.gameSettings.showDebugInfo)
        {
            return;
        }
        
        if (!this.mc.gameSettings.showDebugInfo && Objects.nonNull(this.mc.thePlayer) && Objects.nonNull(this.mc.theWorld))
        {
            int cps = CPSMod.getClicks();
            int cpsColor = Integer.MAX_VALUE;
            
            if (GuiCPS.color == 0)
            {
                cpsColor = 2130706432;
            }
            
            else if (GuiCPS.color == 1)
            {
                cpsColor = 2130706602;
            }
    
            else if (GuiCPS.color == 2)
            {
                cpsColor = 2130749952;
            }
            
            else if (GuiCPS.color == 3)
            {
                cpsColor = 2130750122;
            }
            
            else if (GuiCPS.color == 4)
            {
                cpsColor = 2141847552;
            }
            
            else if (GuiCPS.color == 5)
            {
                cpsColor = 2141847722;
            }
            
            else if (GuiCPS.color == 6)
            {
                cpsColor = 2147461632;
            }
            
            else if (GuiCPS.color == 7)
            {
                cpsColor = 2141891242;
            }
            
            else if (GuiCPS.color == 8)
            {
                cpsColor = 2136298837;
            }
            
            else if (GuiCPS.color == 9)
            {
                cpsColor = 2136299007;
            }
            
            else if (GuiCPS.color == 10)
            {
                cpsColor = 2136342357;
            }
            
            else if (GuiCPS.color == 11)
            {
                cpsColor = 2136342527;
            }
            
            else if (GuiCPS.color == 12)
            {
                cpsColor = 2147439957;
            }
            
            else if (GuiCPS.color == 13)
            {
                cpsColor = 2147440127;
            }
            
            else if (GuiCPS.color == 14)
            {
                cpsColor = 2147483477;
            }
            
            else if (GuiCPS.color == 15)
            {
                cpsColor = Integer.MAX_VALUE;
            }
            
            else if (GuiCPS.color == 16)
            {
                cpsColor = rainbowEffect(this.index + (float) this.x * 2000.0F, 1.0F).getRGB();
            }
            
            if (CPSMod.getClicks() > 9)
            {
                cpsWidth = 12;
            }
            
            else
            {
                cpsWidth = 15;
            }
            
            String text = cps + " CPS";
            boolean blendEnabled = GL11.glIsEnabled(3042);
            GL11.glEnable(3042);
            Gui.drawRect(CPSMod.cpsCounterPosX, CPSMod.cpsCounterPosY.intValue(), CPSMod.cpsCounterPosX + 58, CPSMod.cpsCounterPosY.intValue() + 13, 2130706432);
            this.mc.fontRendererObj.drawString(text, CPSMod.cpsCounterPosX + cpsWidth, CPSMod.cpsCounterPosY.intValue() + 3, cpsColor);
            
            if (blendEnabled)
                GL11.glDisable(3042);
        }
    }
}