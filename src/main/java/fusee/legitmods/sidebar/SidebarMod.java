package fusee.legitmods.sidebar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.command.ICommand;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;

@Mod(modid = "sidebarmod", name = "Sidebar Mod", version = "1.0", acceptedMinecraftVersions = "[1.8.9]")
public class SidebarMod
{
    private Minecraft mc;
    private GuiIngameSidebarMod guiIngameSidebarMod;
    private boolean showGui, hideSidebar, hideRedNumbers, rainbow;
    private int addX, addY;
    
     @EventHandler
     public void init(FMLInitializationEvent event)
     {
         this.mc = Minecraft.getMinecraft();
         
         ClientCommandHandler.instance.registerCommand((ICommand) new SidebarCommand(this));
         
         FMLCommonHandler.instance().bus().register(this);
         
         loadConfig();
     }
     
     @SubscribeEvent
     public void onRenderTick(RenderTickEvent event)
     {
         if (this.mc.ingameGUI != null && !(this.mc.ingameGUI instanceof GuiIngameSidebarMod))
         {
             this.guiIngameSidebarMod = new GuiIngameSidebarMod(this, this.mc);
             this.mc.ingameGUI = (GuiIngame) this.guiIngameSidebarMod;
         }
         
         if (this.mc.currentScreen != null && this.mc.currentScreen instanceof GuiScreenSidebarMod)
         {
             try {
                 
                 this.mc.currentScreen.handleInput();
                 
             } catch (Exception e) {
                 e.printStackTrace();
             }
         }
     }
     
     @SubscribeEvent
     public void onClientTick(ClientTickEvent event)
     {
         if (this.showGui)
         {
             this.mc.displayGuiScreen((GuiScreen) new GuiScreenSidebarMod(this));
             this.showGui = false;
         }
     }
     
     public void setHideSidebar(boolean hideSidebar)
     {
         this.hideSidebar = hideSidebar;
     }
     
     public void setHideRedNumbers(boolean hideRedNumbers)
     {
         this.hideRedNumbers = hideRedNumbers;
     }
     
     public void setAddX(int addX)
     {
         this.addX = addX;
     }
     
     public void setAddY(int addY)
     {
         this.addY = addY;
     }
     
     public void setRainbow(boolean rainbow)
     {
         this.rainbow = rainbow;
     }
     
     public void setShowGui()
     {
         this.showGui = true;
     }
     
     public void saveConfig()
     {
         try {
             
             File file = new File(mc.mcDataDir + "/config", "SidebarMod.cfg");
             
             if (!file.exists())
             {
                 file.getParentFile().mkdirs();
                 file.createNewFile();
             }
             
             FileWriter writer = new FileWriter(file, false);
             
             writer.write(this.hideSidebar + "\n" + this.hideRedNumbers + "\n" + this.rainbow + "\n" + this.addX + "\n" + this.addY);
             writer.close();
             
         } catch (Throwable e) {
             e.printStackTrace();
         }
     }
     
     private void loadConfig()
     {
         try {
             
             File file = new File(mc.mcDataDir + "/config", "SidebarMod.cfg");
             
             if (!file.exists())
             {
                 return;
             }
             
             BufferedReader reader = new BufferedReader(new FileReader(file));
             
             int i = 0;
             
             String line;
             
             while ((line = reader.readLine()) != null)
             {
                 i++;
                 
                 switch (i) 
                 {
                     case 1:
                         this.hideSidebar = Boolean.parseBoolean(line);
                         
                     case 2:
                         this.hideRedNumbers = Boolean.parseBoolean(line);
                         
                     case 3:
                         this.rainbow = Boolean.parseBoolean(line);
                         
                     case 4:
                         this.addX = Integer.parseInt(line);
                         
                     case 5:
                         this.addY = Integer.parseInt(line);
                 }
             }
             
             reader.close();
             
         } catch (Throwable e) {
             e.printStackTrace();
         }
     }
     
     public GuiIngameSidebarMod getGuiIngameSidebarMod()
     {
         return this.guiIngameSidebarMod;
     }
     
     public boolean isHideSidebar()
     {
         return this.hideSidebar;
     }
     
     public boolean isHideRedNumbers()
     {
         return this.hideRedNumbers;
     }
     
     public boolean isRainbow()
     {
         return this.rainbow;
     }
     
     public int getAddX()
     {
         return this.addX;
     }
     
     public int getAddY()
     {
         return this.addY;
     }
}