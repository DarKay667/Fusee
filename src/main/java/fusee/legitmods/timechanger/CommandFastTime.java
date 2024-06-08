package fusee.legitmods.timechanger;

import org.apache.commons.lang3.math.NumberUtils;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;

public class CommandFastTime extends CommandBase
{
    public String getCommandName()
    {
        return "fasttime";
    }
    
    public String getCommandUsage(ICommandSender sender)
    {
        return "/fasttime <multiplier>";
    }
    
    public void processCommand(ICommandSender sender, String[] args) throws CommandException
    {
        if (args.length == 0)
        {
            sender.addChatMessage((new ChatComponentText("Please use /fasttime <multiplier>!")).setChatStyle((new ChatStyle()).setColor(EnumChatFormatting.RED)));
            return;
        }
        
        double multiplier = NumberUtils.toDouble(args[0], -1.0D);
        
        if (multiplier < 0.0D)
        {
            sender.addChatMessage((new ChatComponentText("Invalid multiplier!.")).setChatStyle((new ChatStyle()).setColor(EnumChatFormatting.RED)));
            return;
        }
        
        TimeChanger.TIME_TYPE = TimeType.FAST;
        TimeChanger.fastTimeMultiplier = multiplier;
        sender.addChatMessage((new ChatComponentText("Time set to fast (" + multiplier + ").")).setChatStyle((new ChatStyle()).setColor(EnumChatFormatting.GREEN)));
    }
    
    public int getRequiredPermissionLevel()
    {
        return 0;
    }
}