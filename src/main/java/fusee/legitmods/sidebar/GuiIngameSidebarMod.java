package fusee.legitmods.sidebar;

import java.awt.Color;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.Team;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.GuiIngameForge;

public class GuiIngameSidebarMod extends GuiIngameForge
{
    private SidebarMod mod;
    private final char COLOR_CHAR = '§';
    private final Pattern STRIP_COLOR_PATTERN = Pattern.compile("(?i)" + String.valueOf('§') + "[0-9A-FK-OR]");
    private int minX, minY, maxX, maxY;
    
    public GuiIngameSidebarMod(SidebarMod mod, Minecraft mc)
    {
        super(mc);
        this.mod = mod;
    }
    
    private String stripColors(String message)
    {
        return this.STRIP_COLOR_PATTERN.matcher(message).replaceAll("");
    }
    
    int getMinX()
    {
        return this.minX;
    }
    
    int getMinY()
    {
        return this.minY;
    }
    
    int getMaxX()
    {
        return this.maxX;
    }
    
    int getMaxY()
    {
        return this.maxY;
    }
    
    protected void renderScoreboard(ScoreObjective scoreObjective, ScaledResolution scaledResolution)
    {
        if (this.mod.isHideSidebar())
        {
            return;
        }
        
        Scoreboard scoreboard = scoreObjective.getScoreboard();
        Collection<Score> collection = scoreboard.getSortedScores(scoreObjective);
        List<Score> list = Lists.newArrayList(Iterables.filter(collection, new Predicate<Score>()
        {
            public boolean apply(Score scoreApply)
            {
                return (scoreApply.getPlayerName() != null && !scoreApply.getPlayerName().startsWith("#"));
            }
        }));
        
        if (list.size() > 15)
        {
            collection = Lists.newArrayList(Iterables.skip(list, collection.size() - 15));
        }
        
        else
        {
            collection = list;
        }
        
        int i = getFontRenderer().getStringWidth(scoreObjective.getDisplayName());
        
        for (Score score : collection)
        {
            ScorePlayerTeam scorePlayerTeam = scoreboard.getPlayersTeam(score.getPlayerName());
            String s = ScorePlayerTeam.formatPlayerName((Team) scorePlayerTeam, score.getPlayerName()) + (!this.mod.isHideRedNumbers() ? (": " + EnumChatFormatting.RED + score.getScorePoints()) : "");
            i = Math.max(i, getFontRenderer().getStringWidth(s));
        }
        
        int i1 = collection.size() * (getFontRenderer()).FONT_HEIGHT;
        int j1 = scaledResolution.getScaledHeight() / 2 + i1 / 3;
        int k1 = 3;
        int l1 = scaledResolution.getScaledWidth() - i - k1;
        int j = 0;
        
        int addX = this.mod.getAddX();
        int addY = this.mod.getAddY();
        
        for (Score score1 : collection)
        {
            j++;
            
            ScorePlayerTeam scorePlayerTeam1 = scoreboard.getPlayersTeam(score1.getPlayerName());
            
            String s1 = ScorePlayerTeam.formatPlayerName((Team) scorePlayerTeam1, score1.getPlayerName());
            String s2 = EnumChatFormatting.RED + "" + score1.getScorePoints();
            
            int k = j1 - j * (getFontRenderer()).FONT_HEIGHT;
            int l = scaledResolution.getScaledWidth() - k1 + 2;
            
            drawRect(addX + l1 - 2, addY + k, addX + l, addY + k + (getFontRenderer()).FONT_HEIGHT, 1342177280);
            
            int rainbow = Color.HSBtoRGB((float) (System.currentTimeMillis() % 1000L) / 1000.0F, 0.8F, 0.8F);
            
            getFontRenderer().drawString(this.mod.isRainbow() ? stripColors(s1) : s1, addX + l1, addY + k, this.mod.isRainbow() ? rainbow : 553648127);
            
            if (!this.mod.isHideRedNumbers())
            {
                getFontRenderer().drawString(this.mod.isRainbow() ? stripColors(s2) : s2, addX + l - getFontRenderer().getStringWidth(s2), addY + k, this.mod.isRainbow() ? rainbow : 553648127);
            }
            
            if (j == collection.size())
            {
                String s3 = scoreObjective.getDisplayName();
                
                this.minX = l1 - 2;
                this.minY = k - (getFontRenderer()).FONT_HEIGHT - 1;
                this.maxX = l;
                this.maxY = j1;
                
                drawRect(addX + l1 - 2, addY + k - (getFontRenderer()).FONT_HEIGHT - 1, addX + l, addY + k - 1, 1610612736);
                drawRect(addX + l1 - 2, addY + k - 1, addX + l, addY + k, 1342177280);
                
                getFontRenderer().drawString(this.mod.isRainbow() ? stripColors(s3) : s3, addX + l1 + i / 2 - getFontRenderer().getStringWidth(s3) / 2, addY + k - (getFontRenderer()).FONT_HEIGHT, this.mod.isRainbow() ? rainbow : 553648127);
            }
        }
    }
}