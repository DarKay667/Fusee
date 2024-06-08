package fusee.legitmods.mousedelayfix;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraftforge.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;

public class ClassTransformer implements IClassTransformer
{
    public byte[] transform(String name, String transformedName, byte[] bytes)
    {
        if (transformedName.equals("net.minecraft.entity.EntityLivingBase"))
        {
            ClassReader classReader = new ClassReader(bytes);
            ClassNode classNode = new ClassNode();
            classReader.accept((ClassVisitor) classNode, 0);
            
            for (MethodNode method : classNode.methods)
            {
                String mappedMethodName = FMLDeobfuscatingRemapper.INSTANCE.mapMethodName(classNode.name, method.name, method.desc);
                
                if (mappedMethodName.equals("func_70676_i") || mappedMethodName.equals("getLook"))
                {
                    String entity = FMLDeobfuscatingRemapper.INSTANCE.unmap("net/minecraft/entity/Entity");
                    String entityPlayerSP = FMLDeobfuscatingRemapper.INSTANCE.unmap("net/minecraft/client/entity/EntityPlayerSP");
                    
                    InsnList insnList = new InsnList();
                    insnList.add((AbstractInsnNode) new VarInsnNode(25, 0));
                    insnList.add((AbstractInsnNode) new TypeInsnNode(193, entityPlayerSP));
                    
                    LabelNode label = new LabelNode();
                    insnList.add((AbstractInsnNode) new JumpInsnNode(153, label));
                    insnList.add((AbstractInsnNode) new VarInsnNode(25, 0));
                    insnList.add((AbstractInsnNode) new VarInsnNode(23, 1));
                    insnList.add((AbstractInsnNode) new MethodInsnNode(183, entity, method.name, method.desc));
                    insnList.add((AbstractInsnNode) new InsnNode(176));
                    insnList.add((AbstractInsnNode) label);
                    method.instructions.insertBefore(method.instructions.getFirst(), insnList);
                    
                    break;
                }
            }
            
            ClassWriter classWriter = new ClassWriter(3);
            classNode.accept(classWriter);
            return classWriter.toByteArray();
        }
        
        return bytes;
    }
}