package fusee.legitmods.nametags;

import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.client.event.RenderLivingEvent.Specials;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EH
{
    @SubscribeEvent
    public void onLabelRender(Specials.Pre<EntityLivingBase> e)
    {
        e.setCanceled(true);
        NameTagRenderer.a = e.renderer;
        NameTagRenderer.renderName(e.entity, e.x, e.y, e.z);
    }
}