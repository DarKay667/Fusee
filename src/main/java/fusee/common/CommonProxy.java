package fusee.common;

import fusee.Fusee;
import fusee.module.ModuleManager;
import fusee.setting.SettingsManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

public class CommonProxy
{
    public ModuleManager moduleManager;
    public SettingsManager settingsManager;
    
    public void init(FMLInitializationEvent event)
    {
        try {
            
            this.moduleManager = new ModuleManager();
            this.moduleManager.registerModules();
            this.settingsManager = new SettingsManager();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        Fusee.instance.proxy.registerRenders();
    }
    
    public void registerRenders() {}
}