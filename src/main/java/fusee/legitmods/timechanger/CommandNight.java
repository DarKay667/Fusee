package fusee.legitmods.timechanger;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;

public class CommandNight extends CommandBase
{
    public String getCommandName()
    {
        return "night";
    }
    
    public String getCommandUsage(ICommandSender sender)
    {
        return "/night";
    }
    
    public void processCommand(ICommandSender sender, String[] args) throws CommandException
    {
        TimeChanger.TIME_TYPE = TimeType.NIGHT;
        sender.addChatMessage((new ChatComponentText("Time set to night.")).setChatStyle((new ChatStyle()).setColor(EnumChatFormatting.GREEN)));
    }
    
    public int getRequiredPermissionLevel()
    {
        return 0;
    }
}