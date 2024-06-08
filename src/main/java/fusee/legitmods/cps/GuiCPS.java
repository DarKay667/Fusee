package fusee.legitmods.cps;

import java.io.IOException;

import fusee.legitmods.keystrokes.Colors;
import fusee.legitmods.keystrokes.KeystrokesSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

public class GuiCPS extends GuiScreen
{
    private boolean isDragging = false;
    public static int color = 15;
    private int cps, lastX, lastY;
    private GuiButton buttonReset, buttonAllign, buttonAllign2, buttonAllign3, buttonToggle, buttonColor, buttonColorPressed;
    
    public void initGui()
    {
        this.buttonList.add(this.buttonReset = new GuiButton(0, this.width / 2 - 85, this.height / 2 - 70, 170, 20, "Reset Position"));
        this.buttonList.add(this.buttonAllign = new GuiButton(1, this.width / 2 - 85, this.height / 2 - 48, 170, 20, "Allign Under WASD"));
        this.buttonList.add(this.buttonAllign2 = new GuiButton(2, this.width / 2 - 85, this.height / 2 - 26, 170, 20, "Allign Under WASD_MOUSE"));
        this.buttonList.add(this.buttonAllign3 = new GuiButton(3, this.width / 2 - 85, this.height / 2 - 4, 170, 20, "Allign Under WASD_JUMP_MOUSE"));
        this.buttonList.add(this.buttonToggle = new GuiButton(4, this.width / 2 - 85, this.height / 2 - 93, 170, 20, "Enabled: " + CPSMod.enabled));
        this.buttonList.add(this.buttonColor = new GuiButton(5, this.width / 2 - 85, this.height / 2 + 18, 170, 20, "Color: " + Colors.values()[color]));
    }
    
    public void display()
    {
        FMLCommonHandler.instance().bus().register(this);
    }
    
    @SubscribeEvent
    public void onClientTick(ClientTickEvent event)
    {
        FMLCommonHandler.instance().bus().unregister(this);
        Minecraft.getMinecraft().displayGuiScreen(this);
    }
    
    public void drawScreen(int x, int y, float partialTicks)
    {
        drawDefaultBackground();
        String text = this.cps + "CPS";
        Gui.drawRect(CPSMod.cpsCounterPosX, CPSMod.cpsCounterPosY.intValue(), CPSMod.cpsCounterPosX + 58, CPSMod.cpsCounterPosY.intValue() + 13, 1140850688);
        this.mc.fontRendererObj.drawString(text, CPSMod.cpsCounterPosX + 15, CPSMod.cpsCounterPosY.intValue() + 3, -1);
        super.drawScreen(x, y, partialTicks);
    }
    
    public void updateScreen()
    {
        this.cps = CPSMod.getClicks();
    }
    
    protected void keyTyped(char c, int key)
    {
        if (key == 1)
        {
            this.mc.displayGuiScreen((GuiScreen) null);
        }
    }
    
    protected void mouseClicked(int x, int y, int time) throws IOException
    {
        int minX = CPSMod.cpsCounterPosX;
        int minY = CPSMod.cpsCounterPosY.intValue();
        int maxX = CPSMod.cpsCounterPosX + this.fontRendererObj.getStringWidth(this.cps + " CPS") + 30;
        int maxY = CPSMod.cpsCounterPosY.intValue() + 12;
        
        if (x >= minX && x <= maxX && y >= minY && y <= maxY)
        {
            this.isDragging = true;
            this.lastX = x;
            this.lastY = y;
        }
        
        super.mouseClicked(x, y, time);
    }
    
    protected void mouseReleased(int x, int y, int which)
    {
        if (which == 0 && this.isDragging)
        {
            this.isDragging = false;
        }
        
        super.mouseReleased(x, y, which);
    }
    
    protected void mouseClickMove(int x, int y, int lastButtonClicked, long timeSinceClick)
    {
        if (this.isDragging)
        {
            CPSMod.cpsCounterPosX += x - this.lastX;
            CPSMod.cpsCounterPosY = Integer.valueOf(CPSMod.cpsCounterPosY.intValue() + y - this.lastY);
            this.lastX = x;
            this.lastY = y;
        }
        
        super.mouseClickMove(x, y, lastButtonClicked, timeSinceClick);
    }
    
    protected void actionPerformed(GuiButton button)
    {
        if (button == this.buttonReset)
        {
            CPSMod.cpsCounterPosX = 0;
            CPSMod.cpsCounterPosY = Integer.valueOf(0);
        }
        
        if (button == this.buttonToggle)
        {
            CPSMod.enabled = !CPSMod.enabled;
            this.buttonToggle.displayString = "Enabled: " + CPSMod.enabled;
        }
        
        else if (button == this.buttonAllign)
        {
            CPSMod.cpsCounterPosX = KeystrokesSettings.keystrokesPosX + 1;
            CPSMod.cpsCounterPosY = Integer.valueOf(KeystrokesSettings.keystrokesPosY + 41);
        }
        
        else if (button == this.buttonAllign2)
        {
            CPSMod.cpsCounterPosX = KeystrokesSettings.keystrokesPosX + 1;
            CPSMod.cpsCounterPosY = Integer.valueOf(KeystrokesSettings.keystrokesPosY + 61);
        }
        
        else if (button == this.buttonAllign3)
        {
            CPSMod.cpsCounterPosX = KeystrokesSettings.keystrokesPosX + 1;
            CPSMod.cpsCounterPosY = Integer.valueOf(KeystrokesSettings.keystrokesPosY + 75);
        }
        
        else if (button == this.buttonColor)
        {
            color++;
            
            if (color == (Colors.values()).length)
            {
                color = 0;
            }
            
            this.buttonColor.displayString = "Color: " + Colors.values()[color];
        }
    }
    
    public void onGuiClosed()
    {
        CPSMod.saveSettings();
    }
    
    public boolean doesGuiPauseGame()
    {
        return false;
    }
}