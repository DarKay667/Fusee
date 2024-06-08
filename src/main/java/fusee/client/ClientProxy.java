package fusee.client;

import org.lwjgl.input.Keyboard;

import fusee.Fusee;
import fusee.common.CommonProxy;
import fusee.event.EventHandler;
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
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class ClientProxy extends CommonProxy
{
    private static final int NA = Keyboard.KEY_NONE;
    
    //AdvancedAntiCheat
    public static final KeyBinding AAC_AIRSTUCK = new KeyBinding("[AAC] AirStuck", NA, "Fusee");
    public static final KeyBinding AAC_FASTLADDER = new KeyBinding("[AAC] FastLadder", NA, "Fusee");
    public static final KeyBinding AAC_HIGHJUMP = new KeyBinding("[AAC] HighJump", NA, "Fusee");
    public static final KeyBinding AAC_NOFALL = new KeyBinding("[AAC] NoFall", NA, "Fusee");
    public static final KeyBinding AAC_NOWEB = new KeyBinding("[AAC] NoWeb", NA, "Fusee");
    public static final KeyBinding AAC_SLIMEJUMP = new KeyBinding("[AAC] SlimeJump", NA, "Fusee");
    public static final KeyBinding AAC_SNOWFLY = new KeyBinding("[AAC] SnowFly", NA, "Fusee");
    public static final KeyBinding AAC_SPEED = new KeyBinding("[AAC] Speed", NA, "Fusee");
    public static final KeyBinding AAC_SPEEDTIMER = new KeyBinding("[AAC] SpeedTimer", NA, "Fusee");
    public static final KeyBinding AAC_SPIDER = new KeyBinding("[AAC] Spider", NA, "Fusee");
    public static final KeyBinding AAC_STAIRSPEED = new KeyBinding("[AAC] StairSpeed", NA, "Fusee");
    
    //Combat
    public static final KeyBinding AIMASSIST = new KeyBinding("AimAssist", NA, "Fusee");
    public static final KeyBinding AIMBOT = new KeyBinding("AimBot", NA, "Fusee");
    public static final KeyBinding ANTIBOT = new KeyBinding("AntiBot", NA, "Fusee");
    public static final KeyBinding ANTIKNOCKBACK = new KeyBinding("AntiKnockback", NA, "Fusee");
    public static final KeyBinding AUTOARMOR = new KeyBinding("AutoArmor", NA, "Fusee");
    public static final KeyBinding AUTOBLOCK = new KeyBinding("AutoBlock", NA, "Fusee");
    public static final KeyBinding AUTOCLICK = new KeyBinding("AutoClick", NA, "Fusee");
    public static final KeyBinding CRITICALS = new KeyBinding("Criticals", NA, "Fusee");
    public static final KeyBinding KILLAURA = new KeyBinding("KillAura", NA, "Fusee");
    public static final KeyBinding REACH = new KeyBinding("Reach", NA, "Fusee");
    public static final KeyBinding VELOCITY = new KeyBinding("Velocity", NA, "Fusee");
    
    //Cubecraft
    public static final KeyBinding CUBECRAFT_FLY = new KeyBinding("[Cubecraft] Fly", NA, "Fusee");
    
    //Movement
    public static final KeyBinding FASTLADDER = new KeyBinding("FastLadder", NA, "Fusee");
    public static final KeyBinding FLY = new KeyBinding("Fly", NA, "Fusee");
    public static final KeyBinding GLIDE = new KeyBinding("Glide", NA, "Fusee");
    public static final KeyBinding LONGJUMP = new KeyBinding("LongJump", NA, "Fusee");
    public static final KeyBinding NOCLIP = new KeyBinding("NoClip", NA, "Fusee");
    public static final KeyBinding NOFALL = new KeyBinding("NoFall", NA, "Fusee");
    public static final KeyBinding PHASE = new KeyBinding("Phase", NA, "Fusee");
    public static final KeyBinding SNEAK = new KeyBinding("Sneak", NA, "Fusee");
    public static final KeyBinding SPEED = new KeyBinding("Speed", NA, "Fusee");
    public static final KeyBinding SPIDER = new KeyBinding("Spider", NA, "Fusee");
    public static final KeyBinding SPRINT = new KeyBinding("Sprint", NA, "Fusee");
    public static final KeyBinding STEP = new KeyBinding("Step", NA, "Fusee");
    public static final KeyBinding WATERJUMP = new KeyBinding("WaterJump", NA, "Fusee");
    public static final KeyBinding WATERWALK = new KeyBinding("WaterWalk", NA, "Fusee");
    
    //Player
    public static final KeyBinding ANTIAFK = new KeyBinding("AntiAFK", NA, "Fusee");
    public static final KeyBinding ANTIFIRE = new KeyBinding("AntiFire", NA, "Fusee");
    public static final KeyBinding AUTODECONNECTION = new KeyBinding("AutoDeconnection", NA, "Fusee");
    public static final KeyBinding BLINK = new KeyBinding("Blink", NA, "Fusee");
    public static final KeyBinding CHESTSTEALER = new KeyBinding("ChestStealer", NA, "Fusee");
    public static final KeyBinding DEATHBYPASS = new KeyBinding("DeathBypass", NA, "Fusee");
    public static final KeyBinding DERP = new KeyBinding("Derp", NA, "Fusee");
    public static final KeyBinding FASTEAT = new KeyBinding("FastEat", NA, "Fusee");
    public static final KeyBinding NOARROW = new KeyBinding("NoArrow", NA, "Fusee");
    public static final KeyBinding STAFFDETECTOR = new KeyBinding("StaffDetector", NA, "Fusee");
    
    //Render
    public static final KeyBinding CHESTESP = new KeyBinding("ChestESP", NA, "Fusee");
    public static final KeyBinding CLICKGUI = new KeyBinding("ClickGui", Keyboard.KEY_RSHIFT, "Fusee");
    public static final KeyBinding FOG = new KeyBinding("Fog", NA, "Fusee");
    public static final KeyBinding FULLBRIGHT = new KeyBinding("Fullbright", NA, "Fusee");
    public static final KeyBinding HUD_KEY = new KeyBinding("HUD", Keyboard.KEY_H, "Fusee");
    
    //World
    public static final KeyBinding FASTPLACE = new KeyBinding("FastPlace", NA, "Fusee");
    public static final KeyBinding UNCLAIMFINDER = new KeyBinding("UnclaimFinder", NA, "Fusee");
    
    @SubscribeEvent
    public void onKeyPressed(InputEvent.KeyInputEvent event)
    {
        //AdvancedAntiCheat
        if (this.AAC_AIRSTUCK.isPressed())
        {
            Fusee.instance.proxy.moduleManager.getModule(AirStuck.class).state();
        }
        
        if (this.AAC_FASTLADDER.isPressed())
        {
            Fusee.instance.proxy.moduleManager.getModule(FastLadder.class).state();
        }
        
        if (this.AAC_HIGHJUMP.isPressed())
        {
            Fusee.instance.proxy.moduleManager.getModule(HighJump.class).state();
        }
        
        if (this.AAC_NOFALL.isPressed())
        {
            Fusee.instance.proxy.moduleManager.getModule(NoFall.class).state();
        }
        
        if (this.AAC_NOWEB.isPressed())
        {
            Fusee.instance.proxy.moduleManager.getModule(NoWeb.class).state();
        }
        
        if (this.AAC_SLIMEJUMP.isPressed())
        {
            Fusee.instance.proxy.moduleManager.getModule(SlimeJump.class).state();
        }
        
        if (this.AAC_SNOWFLY.isPressed())
        {
            Fusee.instance.proxy.moduleManager.getModule(SnowFly.class).state();
        }
        
        if (this.AAC_SPEED.isPressed())
        {
            Fusee.instance.proxy.moduleManager.getModule(Speed.class).state();
        }
        
        if (this.AAC_SPEEDTIMER.isPressed())
        {
            Fusee.instance.proxy.moduleManager.getModule(SpeedTimer.class).state();
        }
        
        if (this.AAC_SPIDER.isPressed())
        {
            Fusee.instance.proxy.moduleManager.getModule(Spider.class).state();
        }
        
        if (this.AAC_STAIRSPEED.isPressed())
        {
            Fusee.instance.proxy.moduleManager.getModule(StairSpeed.class).state();
        }
        
        //Combat
        if (this.AIMASSIST.isPressed())
        {
            Fusee.instance.proxy.moduleManager.getModule(AimAssist.class).state();
        }
        
        if (this.AIMBOT.isPressed())
        {
            Fusee.instance.proxy.moduleManager.getModule(AimBot.class).state();
        }
        
        if (this.ANTIBOT.isPressed())
        {
            Fusee.instance.proxy.moduleManager.getModule(AntiBot.class).state();
        }
        
        if (this.ANTIKNOCKBACK.isPressed())
        {
            Fusee.instance.proxy.moduleManager.getModule(AntiKnockback.class).state();
        }
        
        if (this.AUTOARMOR.isPressed())
        {
            Fusee.instance.proxy.moduleManager.getModule(AutoArmor.class).state();
        }
        
        if (this.AUTOBLOCK.isPressed())
        {
            Fusee.instance.proxy.moduleManager.getModule(AutoBlock.class).state();
        }
        
        if (this.AUTOCLICK.isPressed())
        {
            Fusee.instance.proxy.moduleManager.getModule(AutoClick.class).state();
        }
        
        if (this.CRITICALS.isPressed())
        {
            Fusee.instance.proxy.moduleManager.getModule(Criticals.class).state();
        }
        
        if (this.KILLAURA.isPressed())
        {
            Fusee.instance.proxy.moduleManager.getModule(KillAura.class).state();
        }
        
        if (this.REACH.isPressed())
        {
            Fusee.instance.proxy.moduleManager.getModule(Reach.class).state();
        }
        
        if (this.VELOCITY.isPressed())
        {
            Fusee.instance.proxy.moduleManager.getModule(Velocity.class).state();
        }
        
        //Cubecraft
        if (this.CUBECRAFT_FLY.isPressed())
        {
            Fusee.instance.proxy.moduleManager.getModule(Fly.class).state();
        }
        
        //Movement
        if (this.FASTLADDER.isPressed())
        {
            Fusee.instance.proxy.moduleManager.getModule(fusee.module.movement.FastLadder.class).state();
        }
        
        if (this.FLY.isPressed())
        {
            Fusee.instance.proxy.moduleManager.getModule(fusee.module.movement.Fly.class).state();
        }
        
        if (this.GLIDE.isPressed())
        {
            Fusee.instance.proxy.moduleManager.getModule(Glide.class).state();
        }
        
        if (this.LONGJUMP.isPressed())
        {
            Fusee.instance.proxy.moduleManager.getModule(LongJump.class).state();
        }
        
        if (this.NOCLIP.isPressed())
        {
            Fusee.instance.proxy.moduleManager.getModule(NoClip.class).state();
        }
        
        if (this.NOFALL.isPressed())
        {
            Fusee.instance.proxy.moduleManager.getModule(fusee.module.movement.NoFall.class).state();
        }
        
        if (this.PHASE.isPressed())
        {
            Fusee.instance.proxy.moduleManager.getModule(Phase.class).state();
        }
        
        if (this.SNEAK.isPressed())
        {
            Fusee.instance.proxy.moduleManager.getModule(Sneak.class).state();
        }
        
        if (this.SPEED.isPressed())
        {
            Fusee.instance.proxy.moduleManager.getModule(fusee.module.movement.Speed.class).state();
        }
        
        if (this.SPIDER.isPressed())
        {
            Fusee.instance.proxy.moduleManager.getModule(fusee.module.movement.Spider.class).state();
        }
        
        if (this.SPRINT.isPressed())
        {
            Fusee.instance.proxy.moduleManager.getModule(Sprint.class).state();
        }
        
        if (this.STEP.isPressed())
        {
            Fusee.instance.proxy.moduleManager.getModule(Step.class).state();
        }
        
        if (this.WATERJUMP.isPressed())
        {
            Fusee.instance.proxy.moduleManager.getModule(WaterJump.class).state();
        }
        
        if (this.WATERWALK.isPressed())
        {
            Fusee.instance.proxy.moduleManager.getModule(WaterWalk.class).state();
        }
        
        //Player
        if (this.ANTIAFK.isPressed())
        {
            Fusee.instance.proxy.moduleManager.getModule(AntiAFK.class).state();
        }
        
        if (this.ANTIFIRE.isPressed())
        {
            Fusee.instance.proxy.moduleManager.getModule(AntiFire.class).state();
        }
        
        if (this.AUTODECONNECTION.isPressed())
        {
            Fusee.instance.proxy.moduleManager.getModule(AutoDeconnection.class).state();
        }
        
        if (this.BLINK.isPressed())
        {
            Fusee.instance.proxy.moduleManager.getModule(Blink.class).state();
        }
        
        if (this.CHESTSTEALER.isPressed())
        {
            Fusee.instance.proxy.moduleManager.getModule(ChestStealer.class).state();
        }
        
        if (this.DEATHBYPASS.isPressed())
        {
            Fusee.instance.proxy.moduleManager.getModule(DeathBypass.class).state();
        }
        
        if (this.DERP.isPressed())
        {
            Fusee.instance.proxy.moduleManager.getModule(Derp.class).state();
        }
        
        if (this.FASTEAT.isPressed())
        {
            Fusee.instance.proxy.moduleManager.getModule(FastEat.class).state();
        }
        
        if (this.NOARROW.isPressed())
        {
            Fusee.instance.proxy.moduleManager.getModule(NoArrow.class).state();
        }
        
        if (this.STAFFDETECTOR.isPressed())
        {
            Fusee.instance.proxy.moduleManager.getModule(StaffDetector.class).state();
        }
        
        //Render
        if (this.CHESTESP.isPressed())
        {
            Fusee.instance.proxy.moduleManager.getModule(ChestESP.class).state();
        }
        
        if (this.CLICKGUI.isPressed())
        {
            Fusee.instance.proxy.moduleManager.getModule(ClickGui.class).state();
        }
        
        if (this.FOG.isPressed())
        {
            Fusee.instance.proxy.moduleManager.getModule(Fog.class).state();
        }
        
        if (this.FULLBRIGHT.isPressed())
        {
            Fusee.instance.proxy.moduleManager.getModule(Fullbright.class).state();
        }
        
        if (this.HUD_KEY.isPressed())
        {
            Fusee.instance.proxy.moduleManager.getModule(HUD.class).state();
        }
        
        //World
        if (this.FASTPLACE.isPressed())
        {
            Fusee.instance.proxy.moduleManager.getModule(FastPlace.class).state();
        }
        
        if (this.UNCLAIMFINDER.isPressed())
        {
            Fusee.instance.proxy.moduleManager.getModule(UnclaimFinder.class).state();
        }
    }
    
    public ClientProxy()
    {
        MinecraftForge.EVENT_BUS.register(this);
    }
    
    public void registerRenders()
    {
        EventHandler eventHandler = new EventHandler();
        
        MinecraftForge.EVENT_BUS.register(eventHandler);
        FMLCommonHandler.instance().bus().register(eventHandler);
        
        super.registerRenders();
    }
    
    public void init(FMLInitializationEvent event)
    {
      //AdvancedAntiCheat
        ClientRegistry.registerKeyBinding(AAC_AIRSTUCK);
        ClientRegistry.registerKeyBinding(AAC_FASTLADDER);
        ClientRegistry.registerKeyBinding(AAC_HIGHJUMP);
        ClientRegistry.registerKeyBinding(AAC_NOFALL);
        ClientRegistry.registerKeyBinding(AAC_NOWEB);
        ClientRegistry.registerKeyBinding(AAC_SLIMEJUMP);
        ClientRegistry.registerKeyBinding(AAC_SNOWFLY);
        ClientRegistry.registerKeyBinding(AAC_SPEED);
        ClientRegistry.registerKeyBinding(AAC_SPEEDTIMER);
        ClientRegistry.registerKeyBinding(AAC_SPIDER);
        ClientRegistry.registerKeyBinding(AAC_STAIRSPEED);
        
        //Combat
        ClientRegistry.registerKeyBinding(AIMASSIST);
        ClientRegistry.registerKeyBinding(AIMBOT);
        ClientRegistry.registerKeyBinding(ANTIBOT);
        ClientRegistry.registerKeyBinding(ANTIKNOCKBACK);
        ClientRegistry.registerKeyBinding(AUTOARMOR);
        ClientRegistry.registerKeyBinding(AUTOBLOCK);
        ClientRegistry.registerKeyBinding(AUTOCLICK);
        ClientRegistry.registerKeyBinding(CRITICALS);
        ClientRegistry.registerKeyBinding(KILLAURA);
        ClientRegistry.registerKeyBinding(REACH);
        ClientRegistry.registerKeyBinding(VELOCITY);
        
        //Cubecraft
        ClientRegistry.registerKeyBinding(CUBECRAFT_FLY);
        
        //Movement
        ClientRegistry.registerKeyBinding(FASTLADDER);
        ClientRegistry.registerKeyBinding(FLY);
        ClientRegistry.registerKeyBinding(GLIDE);
        ClientRegistry.registerKeyBinding(LONGJUMP);
        ClientRegistry.registerKeyBinding(NOCLIP);
        ClientRegistry.registerKeyBinding(NOFALL);
        ClientRegistry.registerKeyBinding(PHASE);
        ClientRegistry.registerKeyBinding(SNEAK);
        ClientRegistry.registerKeyBinding(SPEED);
        ClientRegistry.registerKeyBinding(SPIDER);
        ClientRegistry.registerKeyBinding(SPRINT);
        ClientRegistry.registerKeyBinding(STEP);
        ClientRegistry.registerKeyBinding(WATERJUMP);
        ClientRegistry.registerKeyBinding(WATERWALK);
        
        //Player
        ClientRegistry.registerKeyBinding(ANTIAFK);
        ClientRegistry.registerKeyBinding(ANTIFIRE);
        ClientRegistry.registerKeyBinding(AUTODECONNECTION);
        ClientRegistry.registerKeyBinding(BLINK);
        ClientRegistry.registerKeyBinding(CHESTSTEALER);
        ClientRegistry.registerKeyBinding(DEATHBYPASS);
        ClientRegistry.registerKeyBinding(DERP);
        ClientRegistry.registerKeyBinding(FASTEAT);
        ClientRegistry.registerKeyBinding(NOARROW);
        ClientRegistry.registerKeyBinding(STAFFDETECTOR);
        
        //Render
        ClientRegistry.registerKeyBinding(CHESTESP);
        ClientRegistry.registerKeyBinding(CLICKGUI);
        ClientRegistry.registerKeyBinding(FOG);
        ClientRegistry.registerKeyBinding(FULLBRIGHT);
        ClientRegistry.registerKeyBinding(HUD_KEY);
        
        //World
        ClientRegistry.registerKeyBinding(FASTPLACE);
        ClientRegistry.registerKeyBinding(UNCLAIMFINDER);
        
        super.init(event);
    }
}