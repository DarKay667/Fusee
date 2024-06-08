package fusee.legitmods.sidebar;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public class SidebarCommand extends CommandBase
{
    private SidebarMod mod;
    
    public SidebarCommand(SidebarMod mod)
    {
        this.mod = mod;
    }
    
    public String getCommandName()
    {
        return "sidebar";
    }
    
    public String getCommandUsage(ICommandSender sender)
    {
        return "/sidebar";
    }
    
    public void processCommand(ICommandSender sender, String[] args)
    {
        this.mod.setShowGui();
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