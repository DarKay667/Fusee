package fusee.legitmods.keystrokes;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public class KeystrokesCommand extends CommandBase
{
    public String getCommandName()
    {
        return "keystrokes";
    }
    
    public String getCommandUsage(ICommandSender sender)
    {
        return "keystrokes";
    }
    
    public void processCommand(ICommandSender sender, String[] args)
    {
        KeystrokesMod.displayGui = true;
    }
    
    public int getRequiredPermissionLevel()
    {
        return 0;
    }
    
    public boolean canCommandSenderUseCommand(ICommandSender sender)
    {
        return true;
    }
}