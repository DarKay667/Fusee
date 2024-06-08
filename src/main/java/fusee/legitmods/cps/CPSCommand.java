package fusee.legitmods.cps;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public class CPSCommand extends CommandBase
{
    public String getCommandName()
    {
        return "cps";
    }
    
    public String getCommandUsage(ICommandSender sender)
    {
        return "/cps";
    }
    
    public void processCommand(ICommandSender sender, String[] args)
    {
        (new GuiCPS()).display();
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