package fusee.module.combat;

import fusee.module.Category;
import fusee.module.Module;

public class Reach extends Module
{
    public Reach()
    {
        super("Reach", Category.Combat);
    }
    
    public void onUpdate()
    {
        if (!getState())
            return;
        
        super.onUpdate();
    }
}