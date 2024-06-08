package fusee.legitmods.cps;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommand;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = "cpsmod", name = "CPS Mod", version = "1.0", acceptedMinecraftVersions = "[1.8.9]")
public class CPSMod
{
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        loadSettings();
        ClickListener clickListener = new ClickListener();
        MinecraftForge.EVENT_BUS.register(clickListener);
        MinecraftForge.EVENT_BUS.register(new ClickCountRenderer());
        FMLCommonHandler.instance().bus().register(clickListener);
        ClientCommandHandler.instance.registerCommand((ICommand) new CPSCommand());
    }
    
    private static void loadSettings()
    {
        File settings = new File((Minecraft.getMinecraft()).mcDataDir + "/config", "CPSMod.cfg");
        
        if (!settings.exists())
        {
            return;
        }
        
        try {
            
            BufferedReader reader = new BufferedReader(new FileReader(settings));
            String[] options = reader.readLine().split(":");
            cpsCounterPosX = Integer.valueOf(options[0]).intValue();
            cpsCounterPosY = Integer.valueOf(options[1]);
            enabled = Boolean.valueOf(options[2]).booleanValue();
            reader.close();
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void saveSettings()
    {
        File settings = new File((Minecraft.getMinecraft()).mcDataDir + "/config", "CPSMod.cfg");
        
        try {
            
            BufferedWriter writer = new BufferedWriter(new FileWriter(settings));
            writer.write(cpsCounterPosX + ":" + cpsCounterPosY + ":" + enabled + ":" + GuiCPS.color);
            writer.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void addClick()
    {
        clicks.add(Long.valueOf(System.currentTimeMillis()));
    }
    
    public static int getClicks()
    {
        Iterator<Long> iterator = clicks.iterator();
        
        while (iterator.hasNext())
        {
            if (((Long) iterator.next()).longValue() < System.currentTimeMillis() - 1000L)
            {
                iterator.remove();
            }
        }
        
        return clicks.size();
    }
    
    private static List<Long> clicks = new ArrayList<Long>();
    public static int cpsCounterPosX = 0;
    public static boolean enabled, preventDoubleClicks;
    
    public static Integer cpsCounterPosY = Integer.valueOf(0);
    
    static
    {
        enabled = true;
        GuiCPS.color = 15;
    }
}