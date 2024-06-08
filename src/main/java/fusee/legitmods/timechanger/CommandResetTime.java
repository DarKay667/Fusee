package fusee.legitmods.timechanger;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;

public class CommandResetTime extends CommandBase
{
    public String getCommandName()
    {
        return "resettime";
    }
    
    public String getCommandUsage(ICommandSender sender)
    {
        return "/resettime";
    }
    
    public void processCommand(ICommandSender sender, String[] args) throws CommandException
    {
        TimeChanger.TIME_TYPE = TimeType.VANILLA;
        sender.addChatMessage((new ChatComponentText("Now using vanilla time.")).setChatStyle((new ChatStyle()).setColor(EnumChatFormatting.GREEN)));
    }
    
    public int getRequiredPermissionLevel()
    {
        return 0;
    }
}