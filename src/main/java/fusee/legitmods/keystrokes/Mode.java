package fusee.legitmods.keystrokes;

import java.util.Arrays;
import java.util.List;

import net.minecraft.client.Minecraft;

public enum Mode
{
    ZQSD(Arrays.asList(new Key[] { new Key("Z", (Minecraft.getMinecraft()).gameSettings.keyBindForward, 21, 1, 18, 18), new Key("Q", (Minecraft.getMinecraft()).gameSettings.keyBindLeft, 1, 21, 18, 18), new Key("S", (Minecraft.getMinecraft()).gameSettings.keyBindBack, 21, 21, 18, 18), new Key("D", (Minecraft.getMinecraft()).gameSettings.keyBindRight, 41, 21, 18, 18)})),
    ZQSD_MOUSE(Arrays.asList(new Key[] { new Key("Z", (Minecraft.getMinecraft()).gameSettings.keyBindForward, 21, 1, 18, 18), new Key("Q", (Minecraft.getMinecraft()).gameSettings.keyBindLeft, 1, 21, 18, 18), new Key("S", (Minecraft.getMinecraft()).gameSettings.keyBindBack, 21, 21, 18, 18), new Key("D", (Minecraft.getMinecraft()).gameSettings.keyBindRight, 41, 21, 18, 18), new Key("LMB", (Minecraft.getMinecraft()).gameSettings.keyBindAttack, 1, 41, 28, 18), new Key("RMB", (Minecraft.getMinecraft()).gameSettings.keyBindUseItem, 31, 41, 28, 18) })),
    ZQSD_JUMP_MOUSE(Arrays.asList(new Key[] { new Key("Z", (Minecraft.getMinecraft()).gameSettings.keyBindForward, 21, 1, 18, 18), new Key("Q", (Minecraft.getMinecraft()).gameSettings.keyBindLeft, 1, 21, 18, 18), new Key("S", (Minecraft.getMinecraft()).gameSettings.keyBindBack, 21, 21, 18, 18), new Key("D", (Minecraft.getMinecraft()).gameSettings.keyBindRight, 41, 21, 18, 18), new Key("LMB", (Minecraft.getMinecraft()).gameSettings.keyBindAttack, 1, 41, 28, 18), new Key("RMB", (Minecraft.getMinecraft()).gameSettings.keyBindUseItem, 31, 41, 28, 18), new Key("§m§l---", (Minecraft.getMinecraft()).gameSettings.keyBindJump, 1, 61, 58, 12) })),
    
    WASD(Arrays.asList(new Key[] { new Key("W", (Minecraft.getMinecraft()).gameSettings.keyBindForward, 21, 1, 18, 18), new Key("A", (Minecraft.getMinecraft()).gameSettings.keyBindLeft, 1, 21, 18, 18), new Key("S", (Minecraft.getMinecraft()).gameSettings.keyBindBack, 21, 21, 18, 18), new Key("D", (Minecraft.getMinecraft()).gameSettings.keyBindRight, 41, 21, 18, 18) })),
    WASD_MOUSE(Arrays.asList(new Key[] { new Key("W", (Minecraft.getMinecraft()).gameSettings.keyBindForward, 21, 1, 18, 18), new Key("A", (Minecraft.getMinecraft()).gameSettings.keyBindLeft, 1, 21, 18, 18), new Key("S", (Minecraft.getMinecraft()).gameSettings.keyBindBack, 21, 21, 18, 18), new Key("D", (Minecraft.getMinecraft()).gameSettings.keyBindRight, 41, 21, 18, 18), new Key("LMB", (Minecraft.getMinecraft()).gameSettings.keyBindAttack, 1, 41, 28, 18), new Key("RMB", (Minecraft.getMinecraft()).gameSettings.keyBindUseItem, 31, 41, 28, 18) })),
    WASD_JUMP_MOUSE(Arrays.asList(new Key[] { new Key("W", (Minecraft.getMinecraft()).gameSettings.keyBindForward, 21, 1, 18, 18), new Key("A", (Minecraft.getMinecraft()).gameSettings.keyBindLeft, 1, 21, 18, 18), new Key("S", (Minecraft.getMinecraft()).gameSettings.keyBindBack, 21, 21, 18, 18), new Key("D", (Minecraft.getMinecraft()).gameSettings.keyBindRight, 41, 21, 18, 18), new Key("LMB", (Minecraft.getMinecraft()).gameSettings.keyBindAttack, 1, 41, 28, 18), new Key("RMB", (Minecraft.getMinecraft()).gameSettings.keyBindUseItem, 31, 41, 28, 18), new Key("§m§l---", (Minecraft.getMinecraft()).gameSettings.keyBindJump, 1, 61, 58, 12) }));
    
    private final List<Key> keys;
    private int width, height;
    
    Mode(List<Key> keys)
    {
        this.width = 0;
        this.height = 0;
        this.keys = keys;
        
        for (Key key : keys)
        {
            this.width = Math.max(this.width, key.getX() + key.getWidth() + 10);
            this.height = Math.max(this.height, key.getY() + key.getHeight() + 10);
        }
    }
    
    public List<Key> getKeys()
    {
        return this.keys;
    }
    
    public int getWidth()
    {
        return this.width;
    }
    
    public int getHeight()
    {
        return this.height;
    }
}