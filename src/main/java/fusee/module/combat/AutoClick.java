package fusee.module.combat;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

import fusee.module.Category;
import fusee.module.Module;

public class AutoClick extends Module
{    
    public AutoClick()
    {
        super("AutoClick", Category.Combat);
    }
    
    public void onUpdate()
    {
        if (getState())
        {
            if (this.mc.thePlayer != null && this.mc.theWorld != null)
            {
                Robot robot;
                
                try {
                    
                    robot = new Robot();
                    robot.mousePress(InputEvent.BUTTON1_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_MASK);
                    
                } catch (AWTException e) {
                    e.printStackTrace();
                }
                
                try {
                    
                    Thread.sleep(1000L);
                    
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        
        super.onUpdate();
    }
}