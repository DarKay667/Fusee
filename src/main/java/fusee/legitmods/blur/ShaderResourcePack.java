package fusee.legitmods.blur;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.google.common.collect.ImmutableSet;

import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.client.resources.data.PackMetadataSection;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;

public class ShaderResourcePack implements IResourcePack, IResourceManagerReloadListener
{
    private final Map<ResourceLocation, String> loadedData = new HashMap<ResourceLocation, String>();
    
    protected boolean validPath(ResourceLocation location) 
    {
        return (location.getResourceDomain().equals("minecraft") && location.getResourcePath().startsWith("shaders/"));
    }
    
    public InputStream getInputStream(ResourceLocation location) throws IOException //func_110590_a //getInputStream
    {
        if (validPath(location))
        {
            String s = this.loadedData.computeIfAbsent(location, loc ->
            {
                InputStream in = Blur.class.getResourceAsStream("/" + location.getResourcePath());
                StringBuilder data = new StringBuilder();
                Scanner scan = new Scanner(in);
                
                try {
                    while (scan.hasNextLine())
                    {
                        data.append(scan.nextLine().replaceAll("@radius@", Integer.toString(Blur.instance.radius))).append('\n');
                    }
                } finally {
                    scan.close();
                }
                
                return data.toString();
            });
            return new ByteArrayInputStream(s.getBytes());
        }
        
        throw new FileNotFoundException(location.toString());
    }
    
    public boolean resourceExists(ResourceLocation location) //func_110589_b //resourceExists
    {
        return (validPath(location) && Blur.class.getResource("/" + location.getResourcePath()) != null);
    }
    
    public Set<String> getResourceDomains() //func_110587_b //getResourceDomains
    {
        return (Set<String>) ImmutableSet.of("minecraft");
    }
    
    public <T extends IMetadataSection> T getPackMetadata(IMetadataSerializer metadataSerializer, String metadataSectionName) throws IOException //func_135058_a //getPackMetadata
    {
        if ("pack".equals(metadataSectionName))
        {
            return (T) new PackMetadataSection((IChatComponent) new ChatComponentText("Blur's default shaders"), 3);
        }
        
        return null;
    }
    
    public BufferedImage getPackImage() throws IOException //func_110586_a //getPackImage
    {
        throw new FileNotFoundException("pack.png");
    }
    
    public String getPackName() //func_130077_b //getPackName
    {
        return "Blur dummy resource pack";
    }
    
    public void onResourceManagerReload(IResourceManager resourceManager) //func_110549_a //onResourceManagerReload
    {
        this.loadedData.clear();
    }
}