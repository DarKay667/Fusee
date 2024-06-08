package fusee.legitmods.memoryfix;

import java.util.Map;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.MCVersion;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.SortingIndex;

@MCVersion("1.8.9")
@SortingIndex(1001)
public class FMLLoadingPlugin implements IFMLLoadingPlugin
{
    public String[] getASMTransformerClass()
    {
        return new String[] {ClassTransformer.class.getName()};
    }
    
    public String getModContainerClass()
    {
        return null;
    }
    
    public String getSetupClass()
    {
        return null;
    }
    
    public void injectData(Map<String, Object> data) {}
    
    public String getAccessTransformerClass()
    {
        return null;
    }
}