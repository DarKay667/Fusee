package fusee.module;

import java.lang.reflect.Field;

import org.lwjgl.opengl.GL11;

import com.mojang.authlib.GameProfile;

import fusee.event.BlockBBEvent;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSnow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.Packet;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovementInput;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;

public class Module
{
    public Minecraft mc = Minecraft.getMinecraft();
    public FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
    private String name;
    private Category category;
    public boolean state;
    private Packet packet;
    
    public Module(String name, Category category, boolean state)
    {
        this.name = name;
        this.category = category;
        this.state = state;
        
        if (state)
            onEnable();
    }
    
    public Module(String name, Category category)
    {
        this(name, category, false);
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public Category getCategory()
    {
        return this.category;
    }
    
    public boolean isCategory(Category category)
    {
        return (this.category == category);
    }
    
    public boolean getState()
    {
        return this.state;
    }
    
    public void state()
    {
        this.state = !this.state;
        
        if (this.state)
        {
            onEnable();
        } else {
            onDisable();
        }
    }
    
    public void setState(boolean state)
    {
        if (state != this.state)
        {
            onToggle();
        }
        
        if (state)
        {
            onEnable();
        } else {
            onDisable();
        }
        
        this.state = state;
    }
    
    public void onEnable() {}
    
    public void onDisable() {}
    
    public void onUpdate() {}
    
    public void onToggle() {}
    
    public void onRender2D() {}
    
    public void onRender3D(RenderWorldLastEvent renderWorldLastEvent) {}
    
    public void onEvent(Packet packet) {}
    
    public void onCollide(BlockBBEvent blockBBEvent) {}
    
    public Packet getPacket()
    {
        return packet;
    }
    
    public static void setTimerSpeed(float timerSpeed)
    {
        try {
            Class<?> mcClass = Minecraft.class;
            Field timerField = mcClass.getDeclaredField("timer");
            timerField.setAccessible(true);
            
            try {
                Object timer = timerField.get(Minecraft.getMinecraft());
                Class<?> timerClass = timer.getClass();
                Field timerSpeedField = timerClass.getDeclaredField("timerSpeed");
                timerSpeedField.setAccessible(true);
                timerSpeedField.setFloat(timer, timerSpeed);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
    
    public float getDirection()
    {
        float var1 = this.mc.thePlayer.rotationYaw;
        
        if (this.mc.thePlayer.moveForward < 0.0F)
        {
            var1 += 180.0F;
        }
        
        float forward = 1.0F;
        
        if (this.mc.thePlayer.moveForward < 0.0F)
        {
            forward = -0.5F;
            
        } else if (this.mc.thePlayer.moveForward > 0.0F)
        {
            forward = 0.5F;
        }
        
        if (this.mc.thePlayer.moveStrafing > 0.0F)
        {
            var1 -= 90.0F * forward;
        }
        
        if (this.mc.thePlayer.moveStrafing < 0.0F)
        {
            var1 += 90.0F * forward;
        }
        
        var1 *= 0.017453292F;
        
        return var1;
    }
    
    public static float getDirection(EntityLivingBase entityLivingBase)
    {
        float yaw = entityLivingBase.rotationYaw;
        float forward = entityLivingBase.moveForward;
        float strafe = entityLivingBase.moveStrafing;
                
        if (strafe < 0.0F)
        {
            yaw += ((forward == 0.0F) ? 90 : ((forward < 0.0F) ? -45 : 45));
        }
        
        if (strafe > 0.0F)
        {
            yaw -= ((forward == 0.0F) ? 90 : ((forward < 0.0F) ? -45 : 45));
        }
        
        return yaw * 0.017453292F;
    }
    
    public static float getDirections()
    {
        return getDirection((EntityLivingBase) Minecraft.getMinecraft().thePlayer);
    }
    
    public static void setSpeed(Entity entity, double speed)
    {
        entity.motionX = -Math.sin(getDirections()) * speed;
        entity.motionZ = Math.cos(getDirections()) * speed;
    }
    
    public static void setSpeed(double speed)
    {
        setSpeed((Entity) Minecraft.getMinecraft().thePlayer, speed);
    }
    
    public static double getSpeed(EntityLivingBase entityLivingBase)
    {
        return Math.sqrt(square(entityLivingBase.motionX) + square(entityLivingBase.motionZ));
    }
    
    public static double square(double in)
    {
        return in * in;
    }
    
    public static double getSpeed()
    {
        return getSpeed((EntityLivingBase) Minecraft.getMinecraft().thePlayer);
    }
    
    public static Field getField(Class clazz, String fieldName) throws NoSuchFieldException
    {
        try {
            
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field;
            
        } catch (NoSuchFieldException e) {
            
            Class superClass = clazz.getSuperclass();
            
            if (superClass == null)
            {
                throw e;
            } else {
                return getField(superClass, fieldName);
            }
        }
    }
    
    public boolean isBlockValid(Block block)
    {
        return block instanceof BlockSnow;
    }
    
    public static class EntityFakePlayer extends EntityOtherPlayerMP
    {
        private static MovementInput movementInput = null;
        
        public EntityFakePlayer(World world, GameProfile gameProfile)
        {
            super(world, gameProfile);
        }
        
        public void setMovementInput(MovementInput movementInput) 
        {
            EntityFakePlayer.movementInput = movementInput;
            
            if (movementInput.jump && this.onGround)
            {
                jump();
            }
            
            moveEntityWithHeading(movementInput.moveStrafe, movementInput.moveForward);
        }
        
        public void moveEntity(double x, double y, double z)
        {
            this.onGround = true;
            super.moveEntity(x, y, z);
            this.onGround = false;
        }
        
        public boolean isSneaking()
        {
            return false;
        }
        
        public void onLivingUpdate()
        {
            super.onLivingUpdate();
            this.noClip = true;
            this.motionX = 0.0D;
            this.motionY = 0.0D;
            this.motionZ = 0.0D;
            this.noClip = false;
        }
    }
    
    protected void drawChestESP(double d, double d1, double d2)
    {
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glLineWidth(0.5F);
        GL11.glDisable(3553);
        GL11.glDepthMask(false);
        GL11.glEnable(2848);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glEnable(2848);
        GL11.glColor4f(0.0F, 255.0F, 0.0F, 0.2F);
        drawBoundingBox(new AxisAlignedBB(d + 1.0D, d1 + 1.0D, d2 + 1.0D, d, d1, d2));
        GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.2F);
        drawOutlinedBoundingBox(new AxisAlignedBB(d + 1.0D, d1 + 1.0D, d2 + 1.0D, d, d1, d2));
        GL11.glDepthMask(true);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
    }
    
    protected void drawEnderChestESP(double d, double d1, double d2)
    {
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glLineWidth(0.5F);
        GL11.glDisable(3553);
        GL11.glDepthMask(false);
        GL11.glEnable(2848);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glEnable(2848);
        GL11.glColor4f(0.0F, 0.0F, 255.0F, 0.2F);
        drawBoundingBox(new AxisAlignedBB(d + 1.0D, d1 + 1.0D, d2 + 1.0D, d, d1, d2));
        GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.2F);
        drawOutlinedBoundingBox(new AxisAlignedBB(d + 1.0D, d1 + 1.0D, d2 + 1.0D, d, d1, d2));
        GL11.glDepthMask(true);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
    }
    
    protected void drawTrappedChestESP(double d, double d1, double d2)
    {
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glLineWidth(0.5F);
        GL11.glDisable(3553);
        GL11.glDepthMask(false);
        GL11.glEnable(2848);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glEnable(2848);
        GL11.glColor4f(255.0F, 0.0F, 0.0F, 0.2F);
        drawBoundingBox(new AxisAlignedBB(d + 1.0D, d1 + 1.0D, d2 + 1.0D, d, d1, d2));
        GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.2F);
        drawOutlinedBoundingBox(new AxisAlignedBB(d + 1.0D, d1 + 1.0D, d2 + 1.0D, d, d1, d2));
        GL11.glDepthMask(true);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
    }
    
    public static void drawOutlinedBoundingBox(AxisAlignedBB aabb)
    {
        Tessellator t = Tessellator.getInstance();
        WorldRenderer wr = t.getWorldRenderer();
        
        wr.begin(3, new VertexFormat());
        wr.pos(aabb.minX, aabb.minY, aabb.minZ);
        wr.pos(aabb.maxX, aabb.minY, aabb.minZ);
        wr.pos(aabb.maxX, aabb.minY, aabb.maxZ);
        wr.pos(aabb.minX, aabb.minY, aabb.maxZ);
        wr.pos(aabb.minX, aabb.minY, aabb.minZ);
        t.draw();
        
        wr.begin(3, new VertexFormat());
        wr.pos(aabb.minX, aabb.maxY, aabb.minZ);
        wr.pos(aabb.maxX, aabb.maxY, aabb.minZ);
        wr.pos(aabb.maxX, aabb.maxY, aabb.maxZ);
        wr.pos(aabb.minX, aabb.maxY, aabb.maxZ);
        wr.pos(aabb.minX, aabb.maxY, aabb.minZ);
        t.draw();
        
        wr.begin(1, new VertexFormat());
        wr.pos(aabb.minX, aabb.minY, aabb.minZ);
        wr.pos(aabb.minX, aabb.maxY, aabb.minZ);
        wr.pos(aabb.maxX, aabb.minY, aabb.minZ);
        wr.pos(aabb.maxX, aabb.maxY, aabb.minZ);
        wr.pos(aabb.maxX, aabb.minY, aabb.maxZ);
        wr.pos(aabb.maxX, aabb.maxY, aabb.maxZ);
        wr.pos(aabb.minX, aabb.minY, aabb.maxZ);
        wr.pos(aabb.minX, aabb.maxY, aabb.maxZ);
        t.draw();        
    }
    
    public static void drawBoundingBox(AxisAlignedBB aabb)
    {
        Tessellator t = Tessellator.getInstance();
        WorldRenderer wr = t.getWorldRenderer();
        
        wr.reset();
        wr.pos(aabb.minX, aabb.minY, aabb.minZ);
        wr.pos(aabb.minX, aabb.maxY, aabb.minZ);
        wr.pos(aabb.maxX, aabb.minY, aabb.minZ);
        wr.pos(aabb.maxX, aabb.maxY, aabb.minZ);
        wr.pos(aabb.maxX, aabb.minY, aabb.maxZ);
        wr.pos(aabb.maxX, aabb.maxY, aabb.maxZ);
        wr.pos(aabb.minX, aabb.minY, aabb.maxZ);
        wr.pos(aabb.minX, aabb.maxY, aabb.maxZ);
        t.draw();
        
        wr.reset();
        wr.pos(aabb.maxX, aabb.maxY, aabb.minZ);
        wr.pos(aabb.maxX, aabb.minY, aabb.minZ);
        wr.pos(aabb.minX, aabb.maxY, aabb.minZ);
        wr.pos(aabb.minX, aabb.minY, aabb.minZ);
        wr.pos(aabb.minX, aabb.maxY, aabb.maxZ);
        wr.pos(aabb.minX, aabb.minY, aabb.maxZ);
        wr.pos(aabb.maxX, aabb.maxY, aabb.maxZ);
        wr.pos(aabb.maxX, aabb.minY, aabb.maxZ);
        t.draw();
        
        wr.reset();
        wr.pos(aabb.minX, aabb.maxY, aabb.minZ);
        wr.pos(aabb.maxX, aabb.maxY, aabb.minZ);
        wr.pos(aabb.maxX, aabb.maxY, aabb.maxZ);
        wr.pos(aabb.minX, aabb.maxY, aabb.maxZ);
        wr.pos(aabb.minX, aabb.maxY, aabb.minZ);
        wr.pos(aabb.minX, aabb.maxY, aabb.maxZ);
        wr.pos(aabb.maxX, aabb.maxY, aabb.maxZ);
        wr.pos(aabb.maxX, aabb.maxY, aabb.minZ);
        t.draw();
        
        wr.reset();
        wr.pos(aabb.minX, aabb.minY, aabb.minZ);
        wr.pos(aabb.maxX, aabb.minY, aabb.minZ);
        wr.pos(aabb.maxX, aabb.minY, aabb.maxZ);
        wr.pos(aabb.minX, aabb.minY, aabb.maxZ);
        wr.pos(aabb.minX, aabb.minY, aabb.minZ);
        wr.pos(aabb.minX, aabb.minY, aabb.maxZ);
        wr.pos(aabb.maxX, aabb.minY, aabb.maxZ);
        wr.pos(aabb.maxX, aabb.minY, aabb.minZ);
        t.draw();
        
        wr.reset();
        wr.pos(aabb.minX, aabb.minY, aabb.minZ);
        wr.pos(aabb.minX, aabb.maxY, aabb.minZ);
        wr.pos(aabb.minX, aabb.minY, aabb.maxZ);
        wr.pos(aabb.minX, aabb.maxY, aabb.maxZ);
        wr.pos(aabb.maxX, aabb.minY, aabb.maxZ);
        wr.pos(aabb.maxX, aabb.maxY, aabb.maxZ);
        wr.pos(aabb.maxX, aabb.minY, aabb.minZ);
        wr.pos(aabb.maxX, aabb.maxY, aabb.minZ);
        t.draw();
        
        wr.reset();
        wr.pos(aabb.minX, aabb.maxY, aabb.maxZ);
        wr.pos(aabb.minX, aabb.minY, aabb.maxZ);
        wr.pos(aabb.minX, aabb.maxY, aabb.minZ);
        wr.pos(aabb.minX, aabb.minY, aabb.minZ);
        wr.pos(aabb.maxX, aabb.maxY, aabb.minZ);
        wr.pos(aabb.maxX, aabb.minY, aabb.minZ);
        wr.pos(aabb.maxX, aabb.maxY, aabb.maxZ);
        wr.pos(aabb.maxX, aabb.minY, aabb.maxZ);
        t.draw();
    }
}