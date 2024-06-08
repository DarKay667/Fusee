package fusee.module;

import java.util.ArrayList;

import fusee.module.advancedanticheat.AirStuck;
import fusee.module.advancedanticheat.FastLadder;
import fusee.module.advancedanticheat.HighJump;
import fusee.module.advancedanticheat.NoFall;
import fusee.module.advancedanticheat.NoWeb;
import fusee.module.advancedanticheat.SlimeJump;
import fusee.module.advancedanticheat.SnowFly;
import fusee.module.advancedanticheat.Speed;
import fusee.module.advancedanticheat.SpeedTimer;
import fusee.module.advancedanticheat.Spider;
import fusee.module.advancedanticheat.StairSpeed;
import fusee.module.combat.AimAssist;
import fusee.module.combat.AimBot;
import fusee.module.combat.AntiBot;
import fusee.module.combat.AntiKnockback;
import fusee.module.combat.AutoArmor;
import fusee.module.combat.AutoBlock;
import fusee.module.combat.AutoClick;
import fusee.module.combat.Criticals;
import fusee.module.combat.KillAura;
import fusee.module.combat.Reach;
import fusee.module.combat.Velocity;
import fusee.module.cubecraft.Fly;
import fusee.module.movement.Glide;
import fusee.module.movement.LongJump;
import fusee.module.movement.NoClip;
import fusee.module.movement.Phase;
import fusee.module.movement.Sneak;
import fusee.module.movement.Sprint;
import fusee.module.movement.Step;
import fusee.module.movement.WaterJump;
import fusee.module.movement.WaterWalk;
import fusee.module.player.AntiAFK;
import fusee.module.player.AntiFire;
import fusee.module.player.AutoDeconnection;
import fusee.module.player.Blink;
import fusee.module.player.ChestStealer;
import fusee.module.player.DeathBypass;
import fusee.module.player.Derp;
import fusee.module.player.FastEat;
import fusee.module.player.NoArrow;
import fusee.module.player.StaffDetector;
import fusee.module.render.ChestESP;
import fusee.module.render.ClickGui;
import fusee.module.render.Fog;
import fusee.module.render.Fullbright;
import fusee.module.render.HUD;
import fusee.module.world.FastPlace;
import fusee.module.world.UnclaimFinder;

public class ModuleManager
{
    public static ArrayList<Module> modules;
    
    public ModuleManager()
    {
        this.modules = new ArrayList<Module>();
    }
    
    public void registerModules()
    {
        //AdvancedAntiCheat
        addModule(new AirStuck());
        addModule(new FastLadder());
        addModule(new HighJump());
        addModule(new NoFall());
        addModule(new NoWeb());
        addModule(new SlimeJump());
        addModule(new SnowFly());
        addModule(new Spider());
        addModule(new Speed());
        addModule(new SpeedTimer());
        addModule(new StairSpeed());
        
        //Combat
        addModule(new AimAssist());
        addModule(new AimBot());
        addModule(new AntiBot());
        addModule(new AntiKnockback());
        addModule(new AutoArmor());
        addModule(new AutoBlock());
        addModule(new AutoClick());
        addModule(new Criticals());
        addModule(new KillAura());
        addModule(new Reach());
        addModule(new Velocity());
        
        //Cubecraft
        addModule(new Fly());
        
        //Movement
        addModule(new fusee.module.movement.FastLadder());
        addModule(new fusee.module.movement.Fly());
        addModule(new Glide());
        addModule(new LongJump());
        addModule(new NoClip());
        addModule(new fusee.module.movement.NoFall());
        addModule(new Phase());
        addModule(new Sneak());
        addModule(new fusee.module.movement.Speed());
        addModule(new fusee.module.movement.Spider());
        addModule(new Sprint());
        addModule(new Step());
        addModule(new WaterJump());
        addModule(new WaterWalk());
        
        //Player
        addModule(new AntiAFK());
        addModule(new AntiFire());
        addModule(new AutoDeconnection());
        addModule(new Blink());
        addModule(new ChestStealer());
        addModule(new DeathBypass());
        addModule(new Derp());
        addModule(new FastEat());
        addModule(new NoArrow());
        addModule(new StaffDetector());
        
        //Render
        addModule(new ChestESP());
        addModule(new ClickGui());
        addModule(new Fog());
        addModule(new Fullbright());
        addModule(new HUD());
        
        //World
        addModule(new FastPlace());
        addModule(new UnclaimFinder());
    }
    
    private void addModule(Module module)
    {
        this.modules.add(module);
    }
    
    public ArrayList<Module> getModules()
    {
        return this.modules;
    }
    
    public Module getModule(String name)
    {
        Module module = null;
        
        for (Module m : this.modules)
        {
            if (m.getName().equalsIgnoreCase(name))
            {
                module = m;
            }
        }
        
        return module;
    }
    
    public <T extends Module> T getModule(Class<T> clazz)
    {
        return (T) modules.stream().filter(mod -> mod.getClass() == clazz).findFirst().orElse(null);
    }
    
    public void onUpdate()
    {
        for (Module m : modules)
        {
            m.onUpdate();
        }
    }
    
    public void onRender2D()
    {
        for (Module m : modules)
        {
            m.onRender2D();
        }
    }
    
    public ArrayList<Module> getModuleInCategory(Category category)
    {
        ArrayList<Module> out = new ArrayList<Module>();
        
        for (Module m : getModules())
        {
            if (m.isCategory(category))
            {
                out.add(m);
            }
        }
        
        return out;
    }
}