package fusee.legitmods.blur;

import java.util.Map;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.SortingIndex;

@SortingIndex(2147483647)
public class BlurPlugin implements IFMLLoadingPlugin
{
    public String[] getASMTransformerClass()
    {
        return new String[] {"fusee.legitmods.blur.BlurTransformer"};
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