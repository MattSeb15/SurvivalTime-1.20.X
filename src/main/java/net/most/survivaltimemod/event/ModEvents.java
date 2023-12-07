package net.most.survivaltimemod.event;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;
import net.most.survivaltimemod.SurvivalTimeMod;
import net.most.survivaltimemod.command.*;
import net.most.survivaltimemod.data.FormatTimeType;
import net.most.survivaltimemod.time.PlayerTime;
import net.most.survivaltimemod.time.PlayerTimeProvider;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Mod.EventBusSubscriber(modid = SurvivalTimeMod.MOD_ID)
public class ModEvents {
    private static final Map<UUID, ScheduledExecutorService> playerSchedulers = new HashMap<>();

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity().level().isClientSide) {
            return;
        }

        if (event.getEntity() instanceof ServerPlayer player) {

            player.getCapability(PlayerTimeProvider.PLAYER_TIME_CAPABILITY).ifPresent(playerTime -> {
                ScheduledExecutorService playerScheduler = Executors.newScheduledThreadPool(1);
                playerScheduler.scheduleAtFixedRate(() -> {
                    if (playerTime.isTimeStopped()) {
                        return;
                    }
                    if (playerTime.getTime() <= 0) {
                        if (player.gameMode.getGameModeForPlayer() != GameType.SPECTATOR) {
                            player.setGameMode(GameType.SPECTATOR);
                        }
                        return;
                    }
                    if (player.gameMode.getGameModeForPlayer() == GameType.SPECTATOR && playerTime.getTime() > 0) {
                        player.setGameMode(GameType.SURVIVAL);
                    }
                    playerTime.decrementTime(1, player);
                    player.displayClientMessage(
                            Component.literal("Time: " + playerTime.getFormattedTime() + " (x" +
                                    playerTime.getTimeMultiplier() + ")"),
                            true);
                }, 0, 1, TimeUnit.SECONDS);
                playerSchedulers.put(player.getUUID(), playerScheduler);

            });
        }


    }

    @SubscribeEvent
    public static void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        if (event.getEntity().level().isClientSide) {
            return;
        }
        if (event.getEntity() instanceof ServerPlayer player) {
            ScheduledExecutorService playerScheduler = playerSchedulers.get(player.getUUID());
            playerScheduler.shutdown();
            playerSchedulers.remove(player.getUUID());
        }


    }

    @SubscribeEvent
    public static void onPlayerHurt(LivingDamageEvent event) {
        if (event.getEntity().level().isClientSide()) {
            return;
        }
        if (event.getEntity() instanceof ServerPlayer player) {

            float damage = event.getAmount();
            event.setCanceled(true);

            player.getCapability(PlayerTimeProvider.PLAYER_TIME_CAPABILITY).ifPresent(playerTime -> {
                float time = playerTime.getTime();

                if (time >= 0) {
                    float timeToDecrement = (damage * 20);

                    playerTime.decrementTime(timeToDecrement, player);
                    player.displayClientMessage(
                            Component.literal("Has recibido " + damage + " y perdido " +
                                    FormatTimeType.getFormattedStringByType(FormatTimeType.DEPENDS_NAMED,
                                            timeToDecrement) + " segundos" +
                                    " de " +
                                    "tiempo." + "Time: " + playerTime.getFormattedTime()).withStyle(ChatFormatting.DARK_RED),
                            false);


                }
            });

        }

    }

    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            if (!event.getObject().getCapability(PlayerTimeProvider.PLAYER_TIME_CAPABILITY).isPresent()) {
                event.addCapability(new ResourceLocation(SurvivalTimeMod.MOD_ID, "player_time"),
                        new PlayerTimeProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            event.getOriginal()
                    .getCapability(PlayerTimeProvider.PLAYER_TIME_CAPABILITY)
                    .ifPresent(
                            oldStore -> event.getOriginal().getCapability(PlayerTimeProvider.PLAYER_TIME_CAPABILITY)
                                    .ifPresent(
                                            newStore -> newStore.copyFrom(oldStore)
                                    )
                    );
        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(PlayerTime.class);
    }

    @SubscribeEvent
    public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {
        if (!event.getLevel().isClientSide() && event.getEntity() instanceof ServerPlayer player) {
            player.getCapability(PlayerTimeProvider.PLAYER_TIME_CAPABILITY).ifPresent(playerTime -> {
                playerTime.syncServerToClientData(player);
//                ModMessages.sendToPlayer(new TimeDataSyncS2CPacket(playerTime.getPlayerTimeData()), player);
            });

        }
    }

    @SubscribeEvent
    public static void onCommandRegister(RegisterCommandsEvent event) {
        new AddTimeCommand(event.getDispatcher());
        new PlayTimeCommand(event.getDispatcher());
        new ResetTimeCommand(event.getDispatcher());
        new SetDefaultTimeMultiplierCommand(event.getDispatcher());
        new SetTimeCommand(event.getDispatcher());
        new SetTimeMultiplierCommand(event.getDispatcher());
        new StopTimeCommand(event.getDispatcher());
        new SubtractTimeCommand(event.getDispatcher());
        new ToggleTimeStatusCommand(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());
    }

}
