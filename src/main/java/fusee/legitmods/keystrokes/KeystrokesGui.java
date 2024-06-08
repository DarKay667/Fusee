package fusee.legitmods.keystrokes;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

public class KeystrokesGui extends GuiScreen
{
    private boolean isDragging;
    private int lastX, lastY;
    public static int color, colorPressed;
    int index = 0;
    long x = 0L;
    
    private GuiButton buttonToggle, buttonLocation, buttonMode, buttonReset, buttonColor, buttonSave, buttonColorPressed;
    final Mode mode;
    
    public void initGui()
    {
        this.buttonList.add(this.buttonToggle = new GuiButton(0, this.width / 2 - 75, this.height / 2 - 62, 150, 20, "Enabled: " + KeystrokesMod.enabled));
        this.buttonList.add(this.buttonMode = new GuiButton(1, this.width / 2 - 75, this.height / 2 - 40, 150, 20, "Mode: " + Mode.values()[KeystrokesMod.mode]));
        this.buttonList.add(this.buttonReset = new GuiButton(2, this.width / 2 - 75, this.height / 2 - 18, 150, 20, "Reset Position"));
        this.buttonList.add(this.buttonColor = new GuiButton(3, this.width / 2 - 75, this.height / 2 + 4, 150, 20, "Color: " + Colors.values()[color]));
        this.buttonList.add(this.buttonColorPressed = new GuiButton(4, this.width / 2 - 75, this.height / 2 + 26, 150, 20, "Color Pressed: " + Colors.values()[colorPressed]));
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
        
        for (Key key : this.mode.getKeys())
        {
            int textWidth = this.mc.fontRendererObj.getStringWidth(key.getName());
            Gui.drawRect(KeystrokesSettings.keystrokesPosX + key.getX(), KeystrokesSettings.keystrokesPosY + key.getY(), KeystrokesSettings.keystrokesPosX + key.getX() + key.getWidth(), KeystrokesSettings.keystrokesPosY + key.getY() + key.getHeight(), key.isDown() ? Integer.MAX_VALUE : 2130706432);
            this.mc.fontRendererObj.drawString(key.getName(), KeystrokesSettings.keystrokesPosX + key.getX() + key.getWidth() / 2 - textWidth / 2, KeystrokesSettings.keystrokesPosY + key.getY() + key.getHeight() / 2 - 4, key.isDown() ? 2130706432 : Integer.MAX_VALUE);
            
            super.drawScreen(x, y, partialTicks);
        }        
    }
    
    protected void actionPerformed(GuiButton button)
    {
        if (button == this.buttonToggle)
        {
            KeystrokesMod.enabled = !KeystrokesMod.enabled;
            this.buttonToggle.displayString = "Enabled: " + KeystrokesMod.enabled;
        }
        
        else if (button == this.buttonReset)
        {
            KeystrokesSettings.keystrokesPosX = 0;
            KeystrokesSettings.keystrokesPosY = 0;
        }
        
        else if (button == this.buttonMode)
        {
            KeystrokesMod.mode++;
            
            if (KeystrokesMod.mode == (Mode.values()).length)
            {
                KeystrokesMod.mode = 0;
            }
            
            this.buttonMode.displayString = "Mode: " + Mode.values()[KeystrokesMod.mode];
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
        
        else if (button == this.buttonColorPressed)
        {
            colorPressed++;
            
            if (colorPressed == (Colors.values()).length)
            {
                colorPressed = 0;
            }
            
            this.buttonColorPressed.displayString = "Color Pressed: " + Colors.values()[colorPressed];
        }
    }
    
    public KeystrokesGui()
    {
        this.mode = Mode.values()[KeystrokesMod.mode];
        this.isDragging = false;
        this.lastX = 0;
        this.lastY = 0;
    }
    
    protected void keyTyped(char c, int key)
    {
        this.mc.displayGuiScreen((GuiScreen) null);
    }
    
    protected void mouseClicked(int x, int y, int time) throws IOException
    {
        int minX = KeystrokesSettings.keystrokesPosX;
        int minY = KeystrokesSettings.keystrokesPosY;
        int maxX = KeystrokesSettings.keystrokesPosX + 60;
        int maxY = KeystrokesSettings.keystrokesPosY + 75;
        
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
            KeystrokesSettings.keystrokesPosX += x - this.lastX;
            KeystrokesSettings.keystrokesPosY += y - this.lastY;
            this.lastX = x;
            this.lastY = y;
        }
        
        super.mouseClickMove(x, y, lastButtonClicked, timeSinceClick);
    }
    
    public void onGuiClosed()
    {
        KeystrokesSettings.saveSettings();
    }
    
    public boolean doesGuiPauseGame()
    {
        return false;
    }
}