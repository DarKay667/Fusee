package fusee.legitmods.timechanger;

import java.lang.reflect.Field;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.play.client.C14PacketTabComplete;
import net.minecraft.network.play.server.S00PacketKeepAlive;
import net.minecraft.network.play.server.S01PacketJoinGame;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.network.play.server.S03PacketTimeUpdate;
import net.minecraft.network.play.server.S04PacketEntityEquipment;
import net.minecraft.network.play.server.S05PacketSpawnPosition;
import net.minecraft.network.play.server.S06PacketUpdateHealth;
import net.minecraft.network.play.server.S07PacketRespawn;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.network.play.server.S09PacketHeldItemChange;
import net.minecraft.network.play.server.S0APacketUseBed;
import net.minecraft.network.play.server.S0BPacketAnimation;
import net.minecraft.network.play.server.S0CPacketSpawnPlayer;
import net.minecraft.network.play.server.S0DPacketCollectItem;
import net.minecraft.network.play.server.S0EPacketSpawnObject;
import net.minecraft.network.play.server.S0FPacketSpawnMob;
import net.minecraft.network.play.server.S10PacketSpawnPainting;
import net.minecraft.network.play.server.S11PacketSpawnExperienceOrb;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.network.play.server.S13PacketDestroyEntities;
import net.minecraft.network.play.server.S14PacketEntity;
import net.minecraft.network.play.server.S18PacketEntityTeleport;
import net.minecraft.network.play.server.S19PacketEntityHeadLook;
import net.minecraft.network.play.server.S19PacketEntityStatus;
import net.minecraft.network.play.server.S1BPacketEntityAttach;
import net.minecraft.network.play.server.S1CPacketEntityMetadata;
import net.minecraft.network.play.server.S1DPacketEntityEffect;
import net.minecraft.network.play.server.S1EPacketRemoveEntityEffect;
import net.minecraft.network.play.server.S1FPacketSetExperience;
import net.minecraft.network.play.server.S20PacketEntityProperties;
import net.minecraft.network.play.server.S21PacketChunkData;
import net.minecraft.network.play.server.S22PacketMultiBlockChange;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.network.play.server.S24PacketBlockAction;
import net.minecraft.network.play.server.S25PacketBlockBreakAnim;
import net.minecraft.network.play.server.S26PacketMapChunkBulk;
import net.minecraft.network.play.server.S27PacketExplosion;
import net.minecraft.network.play.server.S28PacketEffect;
import net.minecraft.network.play.server.S29PacketSoundEffect;
import net.minecraft.network.play.server.S2APacketParticles;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.network.play.server.S2CPacketSpawnGlobalEntity;
import net.minecraft.network.play.server.S2DPacketOpenWindow;
import net.minecraft.network.play.server.S2EPacketCloseWindow;
import net.minecraft.network.play.server.S2FPacketSetSlot;
import net.minecraft.network.play.server.S30PacketWindowItems;
import net.minecraft.network.play.server.S31PacketWindowProperty;
import net.minecraft.network.play.server.S32PacketConfirmTransaction;
import net.minecraft.network.play.server.S34PacketMaps;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.network.play.server.S36PacketSignEditorOpen;
import net.minecraft.network.play.server.S37PacketStatistics;
import net.minecraft.network.play.server.S38PacketPlayerListItem;
import net.minecraft.network.play.server.S39PacketPlayerAbilities;
import net.minecraft.network.play.server.S3APacketTabComplete;
import net.minecraft.network.play.server.S3BPacketScoreboardObjective;
import net.minecraft.network.play.server.S3CPacketUpdateScore;
import net.minecraft.network.play.server.S3DPacketDisplayScoreboard;
import net.minecraft.network.play.server.S3EPacketTeams;
import net.minecraft.network.play.server.S3FPacketCustomPayload;
import net.minecraft.network.play.server.S40PacketDisconnect;
import net.minecraft.network.play.server.S41PacketServerDifficulty;
import net.minecraft.network.play.server.S42PacketCombatEvent;
import net.minecraft.network.play.server.S43PacketCamera;
import net.minecraft.network.play.server.S44PacketWorldBorder;
import net.minecraft.network.play.server.S45PacketTitle;
import net.minecraft.network.play.server.S47PacketPlayerListHeaderFooter;
import net.minecraft.network.play.server.S48PacketResourcePackSend;
import net.minecraft.network.play.server.S49PacketUpdateEntityNBT;

public class TimeChangerNetHandler extends NetHandlerPlayClient
{
    private NetHandlerPlayClient parent;
    
    public TimeChangerNetHandler(NetHandlerPlayClient parent)
    {
        super(Minecraft.getMinecraft(), getGuiScreen(parent), parent.getNetworkManager(), parent.getGameProfile());
        this.parent = parent;
    }
    
    private static GuiScreen getGuiScreen(NetHandlerPlayClient parent)
    {
        for (Field field : parent.getClass().getDeclaredFields())
        {
            if (field.getType().equals(GuiScreen.class))
            {
                field.setAccessible(true);
                
                try {
                    
                    return (GuiScreen) field.get(parent);
                    
                } catch (Exception e) {
                    return null;
                }
            }
        }
        
        return null;
    }
    
    public void handleJoinGame(S01PacketJoinGame packetIn)
    {
        this.parent.handleJoinGame(packetIn);
    }
    
    public void handleSpawnObject(S0EPacketSpawnObject packetIn)
    {
        this.parent.handleSpawnObject(packetIn);
    }
    
    public void handleSpawnExperienceOrb(S11PacketSpawnExperienceOrb packetIn)
    {
        this.parent.handleSpawnExperienceOrb(packetIn);
    }
    
    public void handleSpawnGlobalEntity(S2CPacketSpawnGlobalEntity sPacketSpawnGlobalEntity)
    {
        this.parent.handleSpawnGlobalEntity(sPacketSpawnGlobalEntity);
    }
    
    public void handleSpawnPainting(S10PacketSpawnPainting packetIn)
    {
        this.parent.handleSpawnPainting(packetIn);
    }
    
    public void handleEntityVelocity(S12PacketEntityVelocity sPacketEntityVelocity)
    {
        this.parent.handleEntityVelocity(sPacketEntityVelocity);
    }
    
    public void handleEntityMetadata(S1CPacketEntityMetadata sPacketEntityMetadata)
    {
        this.parent.handleEntityMetadata(sPacketEntityMetadata);
    }
    
    public void handleSpawnPlayer(S0CPacketSpawnPlayer packetIn)
    {
        this.parent.handleSpawnPlayer(packetIn);
    }
    
    public void handleEntityTeleport(S18PacketEntityTeleport sPacketEntityTeleport)
    {
        this.parent.handleEntityTeleport(sPacketEntityTeleport);
    }
    
    public void handleHeldItemChange(S09PacketHeldItemChange packetIn)
    {
        this.parent.handleHeldItemChange(packetIn);
    }
    
    public void handleEntityMovement(S14PacketEntity sPacketEntity)
    {
        this.parent.handleEntityMovement(sPacketEntity);
    }
    
    public void handleEntityHeadLook(S19PacketEntityHeadLook sPacketEntityHeadLook)
    {
        this.parent.handleEntityHeadLook(sPacketEntityHeadLook);
    }
    
    public void handleDestroyEntities(S13PacketDestroyEntities packetIn)
    {
        this.parent.handleDestroyEntities(packetIn);
    }

    public void handlePlayerPosLook(S08PacketPlayerPosLook packetIn)
    {
        this.parent.handlePlayerPosLook(packetIn);
    }

    public void handleMultiBlockChange(S22PacketMultiBlockChange packetIn)
    {
        this.parent.handleMultiBlockChange(packetIn);
    }

    public void handleChunkData(S21PacketChunkData packetIn)
    {
        this.parent.handleChunkData(packetIn);
    }

    public void handleBlockChange(S23PacketBlockChange packetIn)
    {
        this.parent.handleBlockChange(packetIn);
    }
    
    public void handleMapChunkBulk(S26PacketMapChunkBulk packetIn)
    {
        this.parent.handleMapChunkBulk(packetIn);
    }

    public void handleDisconnect(S40PacketDisconnect packetIn)
    {
        this.parent.handleDisconnect(packetIn);
    }
    
    public void handleCollectItem(S0DPacketCollectItem packetIn)
    {
        this.parent.handleCollectItem(packetIn);
    }

    public void handleChat(S02PacketChat packetIn)
    {
        this.parent.handleChat(packetIn);
    }

    public void handleAnimation(S0BPacketAnimation packetIn)
    {
        this.parent.handleAnimation(packetIn);
    }

    public void handleUseBed(S0APacketUseBed packetIn)
    {
        this.parent.handleUseBed(packetIn);
    }

    public void handleSpawnMob(S0FPacketSpawnMob packetIn)
    {
        this.parent.handleSpawnMob(packetIn);
    }
    
    public void handleTimeUpdate(S03PacketTimeUpdate sPacketTimeUpdate)
    {
        switch (TimeChanger.TIME_TYPE)
        {
            case DAY:
                this.parent.handleTimeUpdate(new S03PacketTimeUpdate(sPacketTimeUpdate.getWorldTime(), -6000L, true));
                return;
            case SUNSET:
                this.parent.handleTimeUpdate(new S03PacketTimeUpdate(sPacketTimeUpdate.getWorldTime(), -22880L, true));
                return;
            case NIGHT:
                this.parent.handleTimeUpdate(new S03PacketTimeUpdate(sPacketTimeUpdate.getWorldTime(), -18000L, true));
                return;
            case VANILLA:
                this.parent.handleTimeUpdate(sPacketTimeUpdate);
                return;
            case FAST:
                return;
        }
    }
    
    public void handleSpawnPosition(S05PacketSpawnPosition packetIn)
    {
        this.parent.handleSpawnPosition(packetIn);
    }
    
    public void handleEntityAttach(S1BPacketEntityAttach sPacketEntityAttach)
    {
        this.parent.handleEntityAttach(sPacketEntityAttach);
    }
    
    public void handleEntityStatus(S19PacketEntityStatus sPacketEntityStatus)
    {
        this.parent.handleEntityStatus(sPacketEntityStatus);
    }
    
    public void handleUpdateHealth(S06PacketUpdateHealth packetIn)
    {
        this.parent.handleUpdateHealth(packetIn);
    }

    public void handleSetExperience(S1FPacketSetExperience packetIn)
    {
        this.parent.handleSetExperience(packetIn);
    }

    public void handleRespawn(S07PacketRespawn packetIn)
    {
        this.parent.handleRespawn(packetIn);
    }

    public void handleExplosion(S27PacketExplosion packetIn)
    {
        this.parent.handleExplosion(packetIn);
    }

    public void handleOpenWindow(S2DPacketOpenWindow packetIn)
    {
        this.parent.handleOpenWindow(packetIn);
    }

    public void handleSetSlot(S2FPacketSetSlot packetIn)
    {
        this.parent.handleSetSlot(packetIn);
    }

    public void handleConfirmTransaction(S32PacketConfirmTransaction packetIn)
    {
        this.parent.handleConfirmTransaction(packetIn);
    }

    public void handleWindowItems(S30PacketWindowItems packetIn)
    {
        this.parent.handleWindowItems(packetIn);
    }

    public void handleSignEditorOpen(S36PacketSignEditorOpen packetIn)
    {
        this.parent.handleSignEditorOpen(packetIn);
    }
    
    public void handleUpdateTileEntity(S35PacketUpdateTileEntity sPacketUpdateTileEntity)
    {
        this.parent.handleUpdateTileEntity(sPacketUpdateTileEntity);
    }
    
    public void handleWindowProperty(S31PacketWindowProperty packetIn)
    {
        this.parent.handleWindowProperty(packetIn);
    }
    
    public void handleEntityEquipment(S04PacketEntityEquipment sPacketEntityEquipment)
    {
        this.parent.handleEntityEquipment(sPacketEntityEquipment);
    }
    
    public void handleCloseWindow(S2EPacketCloseWindow packetIn)
    {
        this.parent.handleCloseWindow(packetIn);
    }

    public void handleBlockAction(S24PacketBlockAction packetIn)
    {
        this.parent.handleBlockAction(packetIn);
    }
    
    public void handleBlockBreakAnim(S25PacketBlockBreakAnim packetIn)
    {
        this.parent.handleBlockBreakAnim(packetIn);
    }

    public void handleChangeGameState(S2BPacketChangeGameState packetIn)
    {
        this.parent.handleChangeGameState(packetIn);
    }

    public void handleMaps(S34PacketMaps packetIn)
    {
        this.parent.handleMaps(packetIn);
    }

    public void handleEffect(S28PacketEffect packetIn)
    {
        this.parent.handleEffect(packetIn);
    }

    public void handleStatistics(S37PacketStatistics packetIn)
    {
        this.parent.handleStatistics(packetIn);
    }
    
    public void handleEntityEffect(S1DPacketEntityEffect sPacketEntityEffect)
    {
        this.parent.handleEntityEffect(sPacketEntityEffect);
    }
    
    public void handleCombatEvent(S42PacketCombatEvent packetIn)
    {
        this.parent.handleCombatEvent(packetIn);
    }

    public void handleServerDifficulty(S41PacketServerDifficulty packetIn)
    {
        this.parent.handleServerDifficulty(packetIn);
    }

    public void handleCamera(S43PacketCamera packetIn)
    {
        this.parent.handleCamera(packetIn);
    }
    
    public void handleWorldBorder(S44PacketWorldBorder packetIn)
    {
        this.parent.handleWorldBorder(packetIn);
    }
    
    public void handleTitle(S45PacketTitle packetIn)
    {
        this.parent.handleTitle(packetIn);
    }
    
    public void handlePlayerListHeaderFooter(S47PacketPlayerListHeaderFooter packetIn)
    {
        this.parent.handlePlayerListHeaderFooter(packetIn);
    }
    
    public void handleRemoveEntityEffect(S1EPacketRemoveEntityEffect sPacketRemoveEntityEffect)
    {
        this.parent.handleRemoveEntityEffect(sPacketRemoveEntityEffect);
    }
    
    public void handlePlayerListItem(S38PacketPlayerListItem packetIn)
    {
        this.parent.handlePlayerListItem(packetIn);
    }
    
    public void handleKeepAlive(S00PacketKeepAlive packetIn)
    {
        this.parent.handleKeepAlive(packetIn);
    }
    
    public void handlePlayerAbilities(S39PacketPlayerAbilities packetIn)
    {
        this.parent.handlePlayerAbilities(packetIn);
    }
    
    public void handleTabComplete(S3APacketTabComplete packetIn)
    {
        this.parent.handleTabComplete(packetIn);
    }
    
    public void handleSoundEffect(S29PacketSoundEffect packetIn)
    {
        this.parent.handleSoundEffect(packetIn);
    }
    
    public void handleResourcePack(S48PacketResourcePackSend packetIn)
    {
        this.parent.handleResourcePack(packetIn);
    }
    
    public void handleUpdateEntityNBT(S49PacketUpdateEntityNBT packetIn)
    {
        this.parent.handleEntityNBT(packetIn);
    }
    
    public void handleCustomPayload(S3FPacketCustomPayload packetIn)
    {
        this.parent.handleCustomPayload(packetIn);
    }
    
    public void handleScoreboardObjective(S3BPacketScoreboardObjective packetIn)
    {
        this.parent.handleScoreboardObjective(packetIn);
    }
    
    public void handleUpdateScore(S3CPacketUpdateScore packetIn)
    {
        this.parent.handleUpdateScore(packetIn);
    }
    
    public void handleDisplayObjective(S3DPacketDisplayScoreboard sPacketDisplayObjective)
    {
        this.parent.handleDisplayScoreboard(sPacketDisplayObjective);
    }
    
    public void handleTeams(S3EPacketTeams sPacketTeams)
    {
        this.parent.handleTeams(sPacketTeams);
    }
    
    public void handleParticles(S2APacketParticles sPacketParticles)
    {
        this.parent.handleParticles(sPacketParticles);
    }
    
    public void handleEntityProperties(S20PacketEntityProperties sPacketEntityProperties)
    {
        this.parent.handleEntityProperties(sPacketEntityProperties);
    }
}