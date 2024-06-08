package fusee.legitmods.mousedelayfix;

import java.util.Collections;

import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.LoadController;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.eventhandler.EventBus;

public class MouseDelayFix extends DummyModContainer
{
    public MouseDelayFix() 
    {
        super(new ModMetadata());
        ModMetadata meta = getMetadata();
        meta.modId = "mousedelayfix";
        meta.name = "MouseDelayFix";
        meta.version = "1.0";
        meta.authorList = Collections.singletonList("DarKay__");
    }
    
    public boolean registerBus(EventBus bus, LoadController controller)
    {
        return true;
    }
}