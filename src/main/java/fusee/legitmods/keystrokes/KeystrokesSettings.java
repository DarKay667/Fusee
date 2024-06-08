package fusee.legitmods.keystrokes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

public class KeystrokesSettings
{
    public static int keystrokesPosX, keystrokesPosY;
    
    public KeystrokesSettings()
    {
        keystrokesPosX = 0;
        keystrokesPosY = 0;
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        loadSettings();
        MinecraftForge.EVENT_BUS.register(new KeystrokesRenderer());
    }
    
    static void loadSettings()
    {
        File settings = new File((Minecraft.getMinecraft()).mcDataDir + "/config", "KeystrokesMod.cfg");
        
        if (!settings.exists())
        {
            return;
        }
        
        try {
            
            BufferedReader reader = new BufferedReader(new FileReader(settings));
            String[] options = reader.readLine().split(":");
            keystrokesPosX = Integer.valueOf(options[0]).intValue();
            keystrokesPosY = Integer.valueOf(options[1]).intValue();
            KeystrokesMod.enabled = Boolean.valueOf(options[2]).booleanValue();
            KeystrokesGui.color = Integer.valueOf(options[3]).intValue();
            KeystrokesGui.colorPressed = Integer.valueOf(options[4]).intValue();
            KeystrokesMod.mode = Integer.valueOf(options[5]).intValue();
            reader.close();
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void saveSettings()
    {
        File settings = new File((Minecraft.getMinecraft()).mcDataDir + "/config", "KeystrokesMod.cfg");
        
        try {
            
            BufferedWriter writer = new BufferedWriter(new FileWriter(settings));
            writer.write(keystrokesPosX + ":" + keystrokesPosY + ":" + KeystrokesMod.enabled +  ":" + KeystrokesGui.color + ":" + KeystrokesGui.colorPressed + ":" + KeystrokesMod.mode);
            writer.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    static
    {
        KeystrokesGui.color = 15;
        KeystrokesGui.colorPressed = 0;
        KeystrokesMod.enabled = true;
        KeystrokesMod.mode = 0;
    }
}