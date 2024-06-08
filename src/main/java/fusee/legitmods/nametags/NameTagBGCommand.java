package fusee.legitmods.nametags;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

public class NameTagBGCommand implements ICommand
{
    public String getCommandName()
    {
        return "nametag";
    }
    
    public String getCommandUsage(ICommandSender sender)
    {
        return "/nametag";
    }
    
    public void processCommand(ICommandSender sender, String[] args) throws CommandException
    {
        MinecraftForge.EVENT_BUS.register(this);
    }
    
    @SubscribeEvent
    public void onClientTick(ClientTickEvent event)
    {
        MinecraftForge.EVENT_BUS.unregister(this);
        Minecraft.getMinecraft().displayGuiScreen(new GUIMain());
    }
    
    public boolean canCommandSenderUseCommand(ICommandSender sender)
    {
        return true;
    }
    
    public int compareTo(ICommand o)
    {
        return 0;
    }
    
    public boolean isUsernameIndex(String[] args, int index)
    {
        return false;
    }
    
    public List<String> getCommandAliases()
    {
        return new ArrayList<String>();
    }

    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos)
    {
        return null;
    }
}