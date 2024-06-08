package fusee.legitmods.timechanger;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;

public class CommandSunSet extends CommandBase
{
    public String getCommandName()
    {
        return "sunset";
    }
    
    public String getCommandUsage(ICommandSender sender)
    {
        return "/sunset";
    }
    
    public void processCommand(ICommandSender sender, String[] args) throws CommandException
    {
        TimeChanger.TIME_TYPE = TimeType.SUNSET;
        sender.addChatMessage((new ChatComponentText("Time set to sunset.")).setChatStyle((new ChatStyle()).setColor(EnumChatFormatting.GREEN)));
    }
    
    public int getRequiredPermissionLevel()
    {
        return 0;
    }
}