package fusee.legitmods.mousedelayfix;

import java.util.Map;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

public class FMLLoadingPlugin implements IFMLLoadingPlugin
{
    public String[] getASMTransformerClass()
    {
        return new String[] {ClassTransformer.class.getName()};
    }
    
    public String getModContainerClass() 
    {
        return MouseDelayFix.class.getName();
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