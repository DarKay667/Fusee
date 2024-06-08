package fusee.legitmods.timechanger;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;

public class CommandDay extends CommandBase
{
    public String getCommandName()
    {
        return "day";
    }
    
    public String getCommandUsage(ICommandSender sender)
    {
        return "/day";
    }
    
    public void processCommand(ICommandSender sender, String[] args) throws CommandException
    {
        TimeChanger.TIME_TYPE = TimeType.DAY;
        sender.addChatMessage((new ChatComponentText("Time set to day.")).setChatStyle((new ChatStyle()).setColor(EnumChatFormatting.GREEN)));
    }
    
    public int getRequiredPermissionLevel()
    {
        return 0;
    }
}