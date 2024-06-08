package fusee.event;

import java.awt.Color;

import fusee.Fusee;
import fusee.client.gui.CustomGuiMainMenu;
import fusee.module.ModuleManager;
import fusee.module.combat.AimAssist;
import fusee.module.render.Fog;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.client.event.EntityViewRenderEvent.FogDensity;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EventHandler
{
    @SubscribeEvent
    public void onRender2D(RenderGameOverlayEvent event)
    {
        if (event.type == ElementType.TEXT)
        {
            try {
                
                Fusee.instance.proxy.moduleManager.onRender2D();
                
            } catch (Exception e) {}
        }
    }
    
    @SubscribeEvent
    public void onRender3D(RenderWorldLastEvent event)
    {
        try {
            
            ModuleManager.modules.stream().filter(mod -> (mod.getState() && Minecraft.getMinecraft().theWorld != null)).forEachOrdered(mod -> mod.onRender3D(event));;
            
        } catch (Exception e) {}
    }
    
    @SubscribeEvent
    public void onUpdate(LivingUpdateEvent event)
    {
        if (event.entityLiving == null)
        {
            return;
        }
        
        if (event.entityLiving instanceof EntityPlayerSP)
        {
            try {
                
                Fusee.instance.proxy.moduleManager.onUpdate();
                
            } catch (Exception e) {}
        }
    }
    
    @SideOnly(Side.CLIENT)
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onOverlay(RenderGameOverlayEvent.Pre event)
    {
        String[] directions = {"SOUTH", "WEST", "NORTH", "EAST"};
        
        Minecraft mc = Minecraft.getMinecraft();
        FontRenderer fr = mc.fontRendererObj;
        Color c = Color.white;
        BiomeGenBase biome = mc.theWorld.getBiomeGenForCoords(BlockPos.ORIGIN);
        
        if (event.type == ElementType.DEBUG)
        {
            fr.drawStringWithShadow("Minecraft (release)", 2, 2, c.getRGB());
            fr.drawStringWithShadow(mc.debug, 2, 12, c.getRGB());
            fr.drawStringWithShadow("XYZ: " + (int) mc.thePlayer.posX + " / " + (int) mc.thePlayer.posY + " / " + (int) mc.thePlayer.posZ, 2, 32, c.getRGB());
            fr.drawStringWithShadow("Direction: " + directions[(MathHelper.floor_double(mc.thePlayer.rotationYaw * 4.0F / 360.0F + 0.5D) & 0x3)], 2, 42, c.getRGB());
            fr.drawStringWithShadow("Biome: " + biome.biomeName + " (" + biome.biomeID + ")", 2, 62, c.getRGB());
            
            event.setCanceled(true);
        }
    }
    
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onRenderFog(FogDensity event)
    {
        if (event.block.getMaterial() == Material.water || event.block.getMaterial() == Material.lava && (Fusee.instance.proxy.moduleManager.getModule(Fog.class).getState()))
        {
            event.density = 0.0F;
            event.setCanceled(true);
        }
    }
    
    /*@SubscribeEvent
    public void onAttack(AttackEntityEvent event)
    {
        if (!Fusee.instance.proxy.moduleManager.getModule(KillAura.class).getState() && Fusee.instance.proxy.moduleManager.getModule(Criticals.class).getState() && !Minecraft.getMinecraft().thePlayer.isInWater() && !Minecraft.getMinecraft().thePlayer.isInsideOfMaterial(Material.lava) && !Minecraft.getMinecraft().thePlayer.isInsideOfMaterial(Material.web) && (Minecraft.getMinecraft().thePlayer.onGround) && (Minecraft.getMinecraft().gameSettings.keyBindAttack.isPressed()))
        {
            if (Minecraft.getMinecraft().objectMouseOver != null)
            {
                if ((Minecraft.getMinecraft().objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY))
                {
                    event.setCanceled(true);
                    //Minecraft.getMinecraft().thePlayer.throwableShake = 0.1000000014901161D;
                    Minecraft.getMinecraft().thePlayer.fallDistance = 0.1F;
                    event.setCanceled(Minecraft.getMinecraft().thePlayer.onGround = false); 
                }
            }
        }
    }*/
    
    @SubscribeEvent
    public void onMouse(MouseEvent event)
    {
        try {
            
            if (Fusee.instance.proxy.moduleManager.getModule(AimAssist.class).mop != null && event.button == 0 && event.buttonstate && Fusee.instance.proxy.moduleManager.getModule(AimAssist.class).getState())
            {
                Minecraft.getMinecraft().objectMouseOver = Fusee.instance.proxy.moduleManager.getModule(AimAssist.class).mop;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @SubscribeEvent
    public void onTick(ClientTickEvent event)
    {
        Fusee.instance.proxy.moduleManager.getModule(AimAssist.class).getMouseOver(1.0F);
    }
    
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onOpenGui(GuiOpenEvent event)
    {
        if ((event.gui != null) && ((event.gui.getClass() == GuiMainMenu.class) || (event.gui.getClass().getName().equalsIgnoreCase("net.minecraft.client.gui.GuiMainMenu"))))
        {
            event.gui = new CustomGuiMainMenu();
        }
    }
}