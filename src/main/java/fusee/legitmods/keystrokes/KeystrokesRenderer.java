package fusee.legitmods.keystrokes;

import java.util.Objects;

import org.lwjgl.opengl.GL11;

import fusee.legitmods.cps.ClickCountRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class KeystrokesRenderer
{
    private Minecraft mc;
    private ClickCountRenderer clickCountRenderer;
    int index = 0;
    long x = 0L;
    
    public KeystrokesRenderer()
    {
        this.mc = Minecraft.getMinecraft();
    }
    
    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent event)
    {
        if (!KeystrokesMod.enabled || (this.mc.currentScreen != null && !(this.mc.currentScreen instanceof KeystrokesGui)) || this.mc.gameSettings.showDebugInfo)
        {
            return;
        }
        
        if (event.isCancelable() || event.type != ElementType.EXPERIENCE)
        {
            return;
        }
        
        if (this.mc.currentScreen != null || this.mc.gameSettings.showDebugInfo)
        {
            return;
        }
        
        if (!this.mc.gameSettings.showDebugInfo && Objects.nonNull(this.mc.thePlayer) && Objects.nonNull(this.mc.theWorld))
        {
            Mode mode = Mode.values()[KeystrokesMod.mode];
            
            int keyColor = Integer.MAX_VALUE;
            
            if (KeystrokesGui.color == 0)
            {
                keyColor = 2130706602;
            }
            
            else if (KeystrokesGui.color == 1)
            {
                keyColor = 2130706602;
            }
            
            else if (KeystrokesGui.color == 2) 
            {
                keyColor = 2130749952;
            }
            
            else if (KeystrokesGui.color == 3) 
            {
                keyColor = 2130750122;
            }
            
            else if (KeystrokesGui.color == 4) 
            {
                keyColor = 2141847552;
            }
            
            else if (KeystrokesGui.color == 5)
            {
                keyColor = 2141847722;
            }
            
            else if (KeystrokesGui.color == 6)
            {
                keyColor = 2147461632;
            }
            
            else if (KeystrokesGui.color == 7) 
            {
                keyColor = 2141891242;
            }
            
            else if (KeystrokesGui.color == 8)
            {
                keyColor = 2136298837;
            }
            
            else if (KeystrokesGui.color == 9) 
            {
                keyColor = 2136299007;
            }
            
            else if (KeystrokesGui.color == 10)
            {
                keyColor = 2136342357;
            }
            
            else if (KeystrokesGui.color == 11) 
            {
                keyColor = 2136342527;
            }
            
            else if (KeystrokesGui.color == 12) 
            {
                keyColor = 2147439957;
            }
            
            else if (KeystrokesGui.color == 13) 
            {
                keyColor = 2147440127;
            }
            
            else if (KeystrokesGui.color == 14) 
            {
                keyColor = 2147483477;
            }
            
            else if (KeystrokesGui.color == 15) 
            {
                keyColor = Integer.MAX_VALUE;
            }
            
            else if (KeystrokesGui.color == 16)
            {
                keyColor = clickCountRenderer.rainbowEffect(this.index + (float)this.x * 2000.0F, 1.0F).getRGB();
            }
            
            int keyColorPressed = 2130706432;
            
            if (KeystrokesGui.colorPressed == 0)
            {
                keyColorPressed = 2130706432;
            }
            
            else if (KeystrokesGui.colorPressed == 1) 
            {
                keyColorPressed = 2130706602;
            }
            
            else if (KeystrokesGui.colorPressed == 2) 
            {
                keyColorPressed = 2130749952;
            }
            
            else if (KeystrokesGui.colorPressed == 3)
            {
                keyColorPressed = 2130750122;
            }
            
            else if (KeystrokesGui.colorPressed == 4)
            {
                keyColorPressed = 2141847552;
            }
            
            else if (KeystrokesGui.colorPressed == 5) 
            {
                keyColorPressed = 2141847722;
            }
            
            else if (KeystrokesGui.colorPressed == 6)
            {
                keyColorPressed = 2147461632;
            }
            
            else if (KeystrokesGui.colorPressed == 7)
            {
                keyColorPressed = 2141891242;
            }
            
            else if (KeystrokesGui.colorPressed == 8)
            {
                keyColorPressed = 2136298837;
            }
            
            else if (KeystrokesGui.colorPressed == 9)
            {
                keyColorPressed = 2136299007;
            }
            
            else if (KeystrokesGui.colorPressed == 10)
            {
                keyColorPressed = 2136342527;
            }
            
            else if (KeystrokesGui.colorPressed == 11) 
            {
                keyColorPressed = 2136342357;
            }
            
            else if (KeystrokesGui.colorPressed == 12) 
            {
                keyColorPressed = 2147439957;
            }
            
            else if (KeystrokesGui.colorPressed == 13) 
            {
                keyColorPressed = 2147440127;
            }
            
            else if (KeystrokesGui.colorPressed == 14)
            {
                keyColorPressed = 2147483477;
            }
            
            else if (KeystrokesGui.colorPressed == 15) 
            {
                keyColorPressed = Integer.MAX_VALUE;
            }
            
            else if (KeystrokesGui.colorPressed == 16)
            {
                keyColorPressed = clickCountRenderer.rainbowEffect(this.index + (float)this.x * 2000.0F, 1.0F).getRGB();
            }
            
            boolean blend = GL11.glIsEnabled(3042);
            GL11.glDisable(3042);
            
            for (Key key : mode.getKeys())
            {
                int textWidth = this.mc.fontRendererObj.getStringWidth(key.getName());
                Gui.drawRect(KeystrokesSettings.keystrokesPosX + key.getX(), KeystrokesSettings.keystrokesPosY + key.getY(), KeystrokesSettings.keystrokesPosX + key.getX() + key.getWidth(), KeystrokesSettings.keystrokesPosY + key.getY() + key.getHeight(), key.isDown() ? Integer.MAX_VALUE : 2130706432);
                this.mc.fontRendererObj.drawString(key.getName(), KeystrokesSettings.keystrokesPosX + key.getX() + key.getWidth() / 2 - textWidth / 2, KeystrokesSettings.keystrokesPosY + key.getY() + key.getHeight() / 2 - 4, key.isDown() ? keyColorPressed : keyColor);
            }
            
            if (blend)
                GL11.glEnable(3042);
        }
    }
}