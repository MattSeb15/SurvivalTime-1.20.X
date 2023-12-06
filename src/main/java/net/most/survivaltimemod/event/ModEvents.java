package net.most.survivaltimemod.event;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;
import net.most.survivaltimemod.SurvivalTimeMod;
import net.most.survivaltimemod.command.*;
import net.most.survivaltimemod.data.PlayerTime;


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
//        if (event.getEntity().level().isClientSide) {
//            return;
//        }
        if (event.getEntity() instanceof ServerPlayer player) {

            CompoundTag playerData = player.getPersistentData();
            if (playerData.contains(PlayerTime.COMPOUND_TAG_KEY)) {
                float time = playerData.getFloat(PlayerTime.COMPOUND_TAG_KEY);
                PlayerTime.setTime(player.getUUID(), time);
            } else {
                playerData.putFloat(PlayerTime.COMPOUND_TAG_KEY, PlayerTime.getMaxTime(player.getUUID()));
                PlayerTime.setTime(player.getUUID(), PlayerTime.getMaxTime(player.getUUID()));
            }

            ScheduledExecutorService playerScheduler = Executors.newScheduledThreadPool(1);
            playerScheduler.scheduleAtFixedRate(() -> {
                if (PlayerTime.getIsTimeStopped(player.getUUID())) {
                    return;
                }
                if (PlayerTime.getTime(player.getUUID()) <= 0) {
                    if (player.gameMode.getGameModeForPlayer() != GameType.SPECTATOR) {
                        player.setGameMode(GameType.SPECTATOR);
                    }
                    return;
                }
                if (player.gameMode.getGameModeForPlayer() == GameType.SPECTATOR && PlayerTime.getTime(player.getUUID()) > 0) {
                    player.setGameMode(GameType.SURVIVAL);
                }
                PlayerTime.decrementTime(player.getUUID(), 1);
                player.displayClientMessage(
                        Component.literal("Time: " + PlayerTime.getFormattedTime(player.getUUID()) + " (x" +
                                PlayerTime.getTimeMultiplier(player.getUUID()) + ")"),
                        true);
            }, 0, 1, TimeUnit.SECONDS);

            playerSchedulers.put(player.getUUID(), playerScheduler);

        }


    }

    @SubscribeEvent
    public static void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
//        if (event.getEntity().level().isClientSide) {
//            return;
//        }
        if (event.getEntity() instanceof ServerPlayer player) {
            ScheduledExecutorService playerScheduler = playerSchedulers.get(player.getUUID());
            playerScheduler.shutdown();
            playerSchedulers.remove(player.getUUID());
        }


    }

    @SubscribeEvent
    public static void onPlayerHurt(LivingDamageEvent event) {
//        if (event.getEntity().level().isClientSide) {
//            return;
//        }
        if (event.getEntity() instanceof ServerPlayer player) {
            // Cancela el evento para evitar que el jugador reciba daño de la manera predeterminada
            float damage = event.getAmount();
            event.setCanceled(true);

            // Implementa tu propio sistema de daño basado en el tiempo
            float time = PlayerTime.getTime(player.getUUID());
            if (time <= 0) {
                // Si el tiempo del jugador es 0 o menos, mata al jugador
                player.kill();
            } else {
                // Si no, decrementa el tiempo del jugador
                float timeToDecrement = (damage * 20);

                player.displayClientMessage(
                        Component.literal("Has recibido " + damage + " y perdido " + timeToDecrement + " segundos" +
                                " de " +
                                "tiempo."),
                        false);

                PlayerTime.decrementTime(player.getUUID(), timeToDecrement);
            }

        }

    }

    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            PlayerTime.startTimeStatus(player.getUUID());
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
