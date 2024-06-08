package fusee.legitmods.nametags;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.Team;
import net.minecraftforge.client.event.RenderLivingEvent.Specials;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;

public class NameTagRenderer
{
    public static float alpha = 0.25F, offset = 0.0F, scale = 1.0F;
    public static boolean selftag = false;
    public static RendererLivingEntity a;
    
    public static void renderName(EntityLivingBase entity, double x, double y, double z)
    {
        boolean isPlayer = entity instanceof EntityPlayer;
        
        if (isPlayer)
        {
            if (scale == 0.0F)
                return;
            
            y += offset;
        }
        
        if ((entity instanceof EntityArmorStand && entity.getAlwaysRenderNameTagForRender()) || canRenderName(entity))
        {
            double d0 = entity.getDistanceSqToEntity((a.getRenderManager()).livingPlayer);
            float f = entity.isSneaking() ? RendererLivingEntity.NAME_TAG_RANGE_SNEAK : RendererLivingEntity.NAME_TAG_RANGE;
            
            if (d0 < (f * f))
            {
                String s = entity.getDisplayName().getFormattedText();
                float f1 = isPlayer ? (0.02666667F * scale) : 0.02666667F;
                GlStateManager.alphaFunc(516, 0.1F);
                
                if (entity.isSneaking())
                {
                    FontRenderer fr = a.getFontRendererFromRenderManager();
                    GlStateManager.pushMatrix();
                    GlStateManager.translate((float) x, (float) y + entity.height + 0.5F - (entity.isChild() ? (entity.height / 2.0F) : 0.0F), (float) z);
                    GL11.glNormal3f(0.0F, 1.0F, 0.0F);
                    GlStateManager.rotate(-(a.getRenderManager()).playerViewY, 0.0F, 1.0F, 0.0F);
                    GlStateManager.rotate((a.getRenderManager()).playerViewX, 1.0F, 0.0F, 0.0F);
                    GlStateManager.scale(-f1, -f1, f1);
                    GlStateManager.translate(0.0F, 9.374999F, 0.0F);
                    GlStateManager.disableLighting();
                    GlStateManager.depthMask(false);
                    GlStateManager.enableBlend();
                    GlStateManager.enableTexture2D();
                    GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
                    int i = fr.getStringWidth(s) / 2;
                    Tessellator tessellator = Tessellator.getInstance();
                    WorldRenderer worldRenderer = tessellator.getWorldRenderer();
                    worldRenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
                    
                    if (alpha != 0.0)
                    {
                        worldRenderer.pos((-i - 1), -1.0D, 0.0D).color(0.0F, 0.0F, 0.0F, alpha).endVertex();
                        worldRenderer.pos((-i - 1), 8.0D, 0.0D).color(0.0F, 0.0F, 0.0F, alpha).endVertex();
                        worldRenderer.pos((i + 1), 8.0D, 0.0D).color(0.0F, 0.0F, 0.0F, alpha).endVertex();
                        worldRenderer.pos((i + 1), -1.0D, 0.0D).color(0.0F, 0.0F, 0.0F, alpha).endVertex();
                    }
                    
                    tessellator.draw();
                    GlStateManager.enableTexture2D();
                    GlStateManager.depthMask(true);
                    fr.drawString(s, -fr.getStringWidth(s) / 2, 0, 553648127);
                    GlStateManager.enableLighting();
                    GlStateManager.disableBlend();
                    GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                    GlStateManager.popMatrix();
                }
                
                else if (isPlayer)
                {
                    playerRenderOffsetLivingLabel(entity, x, y - (entity.isChild() ? (entity.height / 2.0F) : 0.0D), z, s, 0.02666667F, d0);
                }
                
                else
                {
                    renderOffsetLivingLabel(entity, x, y - (entity.isChild() ? (entity.height / 2.0F) : 0.0D), z, s, 0.02666667F, d0, false);
                }
            }
        }
        
        MinecraftForge.EVENT_BUS.post((Event) new Specials.Post(entity, a, x, y, z));
    }
    
    protected static void playerRenderOffsetLivingLabel(EntityLivingBase entityIn, double x, double y, double z, String str, float p_177069_9_, double p_177069_10_)
    {
        if (p_177069_10_ < 100.0D)
        {
            Scoreboard scoreboard = ((EntityPlayer) entityIn).getWorldScoreboard();
            ScoreObjective scoreObjective = scoreboard.getObjectiveInDisplaySlot(2);
            
            if (scoreObjective != null)
            {
                Score score = scoreboard.getValueFromObjective(entityIn.getName(), scoreObjective);
                renderLivingLabel(entityIn, score.getScorePoints() + " " + scoreObjective.getDisplayName(), x, y, z, 64, true);
                y += ((a.getFontRendererFromRenderManager()).FONT_HEIGHT * 1.15F * p_177069_9_);
            }
        }
        
        renderOffsetLivingLabel(entityIn, x, y, z, str, p_177069_9_, p_177069_10_, true);
    }
    
    protected static boolean canRenderName(EntityLivingBase entity)
    {
        return (canRenderName2(entity) && (entity.getAlwaysRenderNameTagForRender() || (entity.hasCustomName() && entity == (a.getRenderManager()).pointedEntity)));
    }
    
    protected static boolean canRenderName2(EntityLivingBase entity)
    {
        if (entity == (a.getRenderManager()).livingPlayer)
        {
            if (selftag) {}
            return false;
        }
        
        EntityPlayerSP entityPlayerSP = (Minecraft.getMinecraft()).thePlayer;
        
        if (entity instanceof EntityPlayer && entity != entityPlayerSP)
        {
            Team team = entity.getTeam();
            Team team1 = entityPlayerSP.getTeam();
            
            if (team != null)
            {
                Team.EnumVisible teamEnumVisible = team.getNameTagVisibility();
                
                switch (teamEnumVisible)
                {
                    case ALWAYS:
                        return true;
                    case NEVER:
                        return false;
                    case HIDE_FOR_OTHER_TEAMS:
                        return (team1 == null || team.isSameTeam(team1));
                    case HIDE_FOR_OWN_TEAM:
                        return (team1 == null || !team.isSameTeam(team1));
                }
                
                return true;
            }
        }
        
        return (Minecraft.isGuiEnabled() && !entity.isInvisibleToPlayer((EntityPlayer) entityPlayerSP) && entity.riddenByEntity == null);
    }
    
    protected static void renderOffsetLivingLabel(EntityLivingBase entityIn, double x, double y, double z, String str, float p_177069_9_, double p_177069_10_, boolean isPlayer)
    {
        renderLivingLabel(entityIn, str, x, y, z, 64, isPlayer);
    }
    
    protected static void renderLivingLabel(EntityLivingBase entityIn, String str, double x, double y, double z, int maxDistance, boolean isPlayer)
    {
        double d0 = entityIn.getDistanceSqToEntity((a.getRenderManager()).livingPlayer);
        
        if (d0 <= (maxDistance * maxDistance))
        {
            FontRenderer fr = a.getFontRendererFromRenderManager();
            float f1 = isPlayer ? (0.02666667F * scale) : 0.02666667F;
            GlStateManager.pushMatrix();
            GlStateManager.translate((float) x + 0.0F, (float) y + entityIn.height + 0.5F, (float) z);
            GL11.glNormal3f(0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(-(a.getRenderManager()).playerViewY, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate((a.getRenderManager()).playerViewX, 1.0F, 0.0F, 0.0F);
            GlStateManager.scale(-f1, -f1, f1);
            GlStateManager.disableLighting();
            GlStateManager.depthMask(false);
            GlStateManager.disableDepth();
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            Tessellator tessellator = Tessellator.getInstance();
            WorldRenderer worldRenderer = tessellator.getWorldRenderer();
            int i = 0;
            
            if (str.equals("deadmau5"))
            {
                i = - 10;
            }
            
            int j = fr.getStringWidth(str) / 2;
            GlStateManager.enableTexture2D();
            worldRenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
            
            if (alpha != 0.0F)
            {
                worldRenderer.pos((-j - 1), (-1 + i), 0.0D).color(0.0F, 0.0F, 0.0F, alpha).endVertex();
                worldRenderer.pos((-j - 1), (8 + i), 0.0D).color(0.0F, 0.0F, 0.0F, alpha).endVertex();
                worldRenderer.pos((j + 1), (8 + i), 0.0D).color(0.0F, 0.0F, 0.0F, alpha).endVertex();
                worldRenderer.pos((j + 1), (-1 + i), 0.0D).color(0.0F, 0.0F, 0.0F, alpha).endVertex();
            }
            
            tessellator.draw();
            GlStateManager.enableTexture2D();
            fr.drawString(str, -fr.getStringWidth(str) / 2, i, 553648127);
            GlStateManager.enableDepth();
            GlStateManager.depthMask(true);
            fr.drawString(str, -fr.getStringWidth(str) / 2, i, -1);
            GlStateManager.enableLighting();
            GlStateManager.disableBlend();
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.popMatrix();
        }
    }
    
    public static void setAlpha(int a)
    {
        alpha = a / 100.0F;
    }
    
    public static void setScale(int s)
    {
        if (s == 0)
            scale = 0.0F;
        
        scale = s / 100.0F;
    }
    
    public static void setSelftag(boolean tag)
    {
        selftag = tag;
    }
    
    public static void setOffset(int off)
    {
        offset = off / 100.0F;
    }
    
    public static int getOffset()
    {
        return (int) (offset * 100.0F);
    }
    
    public static int getScale()
    {
        return (int) (scale * 100.0F);
    }
}