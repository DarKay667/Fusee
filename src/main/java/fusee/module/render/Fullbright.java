package fusee.module.render;

import fusee.module.Category;
import fusee.module.Module;

public class Fullbright extends Module
{
    private float gammaSetting = 0.0F;
    
    public Fullbright()
    {
        super("Fullbright", Category.Render);
    }
    
    public void onEnable()
    {
        this.gammaSetting = this.mc.gameSettings.gammaSetting;
        super.onEnable();
    }
    
    public void onUpdate()
    {
        if (getState())
        {
            this.mc.gameSettings.gammaSetting = 100.0F;
        }
        
        super.onUpdate();
    }
    
    public void onDisable()
    {
        this.mc.gameSettings.gammaSetting = this.gammaSetting;
        super.onDisable();
    }
}