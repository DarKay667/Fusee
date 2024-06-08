package fusee.module.render;

import fusee.module.Category;
import fusee.module.Module;

public class Fog extends Module
{
    public Fog()
    {
        super("Fog", Category.Render);
    }
    
    public void onUpdate()
    {
        if (!getState())
            return;
        
        super.onUpdate();
    }
}