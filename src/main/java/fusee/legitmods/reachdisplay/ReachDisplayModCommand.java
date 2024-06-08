package fusee.legitmods.reachdisplay;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

public class ReachDisplayModCommand extends CommandBase
{
    private ReachDisplayMod mod;
    
    public ReachDisplayModCommand(ReachDisplayMod mod)
    {
        this.mod = mod;
    }
    
    public String getCommandName()
    {
        return "reachdisplay";
    }

    public String getCommandUsage(ICommandSender sender)
    {
        return "/reachdisplay";
    }

    public void processCommand(ICommandSender sender, String[] args) throws CommandException
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