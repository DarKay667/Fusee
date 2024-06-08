package fusee.legitmods.blur;

import java.util.Iterator;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

import net.minecraft.launchwrapper.IClassTransformer;

public class BlurTransformer implements IClassTransformer
{
    private static final String GUI_SCREEN_CLASS_NAME = "net.minecraft.client.gui.GuiScreen";
    private static final String DRAW_WORLD_BACKGROUND_METHOD = "drawWorldBackground";
    private static final String DRAW_WORLD_BACKGROUND_METHOD_OBF = "func_146270_b";
    private static final String BLUR_MAIN_CLASS = "fusee/legitmods/blur/Blur";
    private static final String COLOR_HOOK_METHOD_NAME = "getBackgroundColor";
    private static final String COLOR_HOOK_METHOD_DESC = "(Z)I";
    
    public byte[] transform(String name, String transformedName, byte[] basicClass)
    {
        if (transformedName.equals("net.minecraft.client.gui.GuiScreen"))
        {
            ClassNode classNode = new ClassNode();
            ClassReader classReader = new ClassReader(basicClass);
            classReader.accept((ClassVisitor) classNode, 0);
            
            Iterator<MethodNode> methods = classNode.methods.iterator();
            
            while (methods.hasNext())
            {
                MethodNode m = methods.next();
                
                if (m.name.equals("drawWorldBackground") || m.name.equals("func_146270_b"))
                {
                    for (int i = 0; i < m.instructions.size(); i++)
                    {
                        AbstractInsnNode next = m.instructions.get(i);
                        
                        if (next.getOpcode() == 18)
                        {
                            MethodInsnNode methodInsnNode = new MethodInsnNode(184, "fusee/legitmods/blur/Blur", "getBackgroundColor", "(Z)I", false);
                            AbstractInsnNode colorHook2 = methodInsnNode.clone(null);
                            
                            m.instructions.set(next, (AbstractInsnNode) methodInsnNode);
                            m.instructions.set(methodInsnNode.getNext(), colorHook2);
                            
                            m.instructions.insertBefore((AbstractInsnNode) methodInsnNode, (AbstractInsnNode) new InsnNode(4));
                            m.instructions.insertBefore(colorHook2, (AbstractInsnNode) new InsnNode(3));
                            
                            break;
                        }
                    }
                    break;
                }
            }
            
            ClassWriter cw = new ClassWriter(1);
            classNode.accept(cw);
            return cw.toByteArray();
        }
        
        return basicClass;
    }
}