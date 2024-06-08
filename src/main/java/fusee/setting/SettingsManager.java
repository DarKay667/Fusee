package fusee.setting;

import java.util.ArrayList;

import fusee.module.Module;

public class SettingsManager
{
    private ArrayList<Setting> settings = new ArrayList<>();
    
    public void addSetting(Setting setting)
    {
        this.settings.add(setting);
    }
    
    public ArrayList<Setting> getSettings()
    {
        return this.settings;
    }
    
    public ArrayList<Setting> getSettingsByModule(Module mod)
    {
        ArrayList<Setting> out = new ArrayList<>();
        
        for (Setting s : getSettings())
        {
            if (s.getParentModule().equals(mod))
            {
                out.add(s);
            }
        }
        
        if (out.isEmpty())
        {
            return null;
        }
        
        return out;
    }
    
    public Setting getSettingByName(String name)
    {
        for (Setting set : getSettings())
        {
            if (set.getName().equalsIgnoreCase(name))
            {
                return set;
            }
        }
        
        return null;
    }
}