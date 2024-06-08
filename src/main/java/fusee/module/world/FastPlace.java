package fusee.module.world;

import java.lang.reflect.Field;

import fusee.module.Category;
import fusee.module.Module;

public class FastPlace extends Module
{
    public FastPlace()
    {
        super("FastPlace", Category.World);
    }
    
    public void onUpdate()
    {
        if (getState())
        {
            try {
                Field f = this.mc.getClass().getDeclaredField("rightClickDelayTimer");
                f.setAccessible(true);
                f.set(this.mc, Integer.valueOf(0));
            } catch (Exception ex) {
                try {
                    Field f = this.mc.getClass().getDeclaredField("field_71467_ac");
                    f.setAccessible(true);
                    f.set(this.mc, Integer.valueOf(0));
                } catch (Exception exx) {
                    ex.printStackTrace();
                    exx.printStackTrace();
                }
            }
        }
        
        super.onUpdate();
    }
}