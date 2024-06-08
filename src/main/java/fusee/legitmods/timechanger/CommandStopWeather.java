package fusee.legitmods.timechanger;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;

public class CommandStopWeather extends CommandBase
{
    public String getCommandName()
    {
        return "stopweather";
    }
    
    public String getCommandUsage(ICommandSender sender)
    {
        return "/stopweather";
    }
    
    public void processCommand(ICommandSender sender, String[] args) throws CommandException
    {
        Minecraft.getMinecraft().theWorld.setRainStrength(0.0F);
        sender.addChatMessage((new ChatComponentText("Weather set to stop raining.")).setChatStyle((new ChatStyle()).setColor(EnumChatFormatting.GREEN)));
    }
    
    public int getRequiredPermissionLevel()
    {
        return 0;
    }
}