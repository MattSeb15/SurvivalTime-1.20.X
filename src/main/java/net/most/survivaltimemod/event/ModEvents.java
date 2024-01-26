package net.most.survivaltimemod.event;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
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
import net.most.survivaltimemod.command.coin.AddCoinCommand;
import net.most.survivaltimemod.command.coin.InfoCoinCommand;
import net.most.survivaltimemod.command.coin.SetCoinCommand;
import net.most.survivaltimemod.command.coin.SubtractCoinCommand;
import net.most.survivaltimemod.command.coinmultiplier.InfoCoinMultiplierCommand;
import net.most.survivaltimemod.command.coinmultiplier.SetCoinMultiplierCommand;
import net.most.survivaltimemod.command.coinmultiplier.SetDefaultCoinMultiplierCommand;
import net.most.survivaltimemod.command.dmultiplier.InfoDamageMultiplierCommand;
import net.most.survivaltimemod.command.dmultiplier.SetDamageMultiplierCommand;
import net.most.survivaltimemod.command.dmultiplier.SetDefaultDamageMultiplierCommand;
import net.most.survivaltimemod.command.item.AddNbtToItem;
import net.most.survivaltimemod.command.maxtime.InfoMaxTimeCommand;
import net.most.survivaltimemod.command.maxtime.SetMaxTimeCommand;
import net.most.survivaltimemod.command.time.*;
import net.most.survivaltimemod.command.tmultiplier.InfoTimeMultiplierCommand;
import net.most.survivaltimemod.command.tmultiplier.SetDefaultTimeMultiplierCommand;
import net.most.survivaltimemod.command.tmultiplier.SetTimeMultiplierCommand;
import net.most.survivaltimemod.effect.ModEffects;
import net.most.survivaltimemod.time.PlayerTime;
import net.most.survivaltimemod.time.PlayerTimeProvider;
import net.most.survivaltimemod.util.ComponentHelper;


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
                    if (playerTime.isTimeStopped() || player.gameMode.getGameModeForPlayer() == GameType.CREATIVE) {
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
                            Component.literal("T: " + playerTime.getFormattedTime() +" | MT: " + playerTime.getFormattedMaxTime() + " (x" +
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
                float damageMultiplier = playerTime.getDamageMultiplier();

                if (time >= 0) {
                    float timeToDecrement = (damage * damageMultiplier);
                    playerTime.decrementTime(timeToDecrement, player);
                    player.displayClientMessage(ComponentHelper.getOnPlayerHurtComponent(time, damage, damageMultiplier, timeToDecrement,
                            playerTime.getTime()), false);

                }
            });

            player.addEffect(new MobEffectInstance(ModEffects.DAMAGE_TRIGGER.get(), 8,
                    1, false, false));

//            player.drop(new ItemStack(Items.BEEF), true, true); //TODO: REFACTOR CODE

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
        new InfoTimeCommand(event.getDispatcher());
        new ResetTimeCommand(event.getDispatcher());
        new SetDefaultTimeMultiplierCommand(event.getDispatcher());
        new SetTimeCommand(event.getDispatcher());
        new SetTimeMultiplierCommand(event.getDispatcher());
        new StopTimeCommand(event.getDispatcher());
        new SubtractTimeCommand(event.getDispatcher());
        new ToggleTimeStatusCommand(event.getDispatcher());
        new AddNbtToItem(event.getDispatcher(), event.getBuildContext());
        new SetDamageMultiplierCommand(event.getDispatcher());
        new SetDefaultDamageMultiplierCommand(event.getDispatcher());
        new InfoMaxTimeCommand(event.getDispatcher());
        new SetMaxTimeCommand(event.getDispatcher());
        new AddCoinCommand(event.getDispatcher());
        new InfoCoinCommand(event.getDispatcher());
        new SetCoinCommand(event.getDispatcher());
        new SubtractCoinCommand(event.getDispatcher());
        new SetCoinMultiplierCommand(event.getDispatcher());
        new SetDefaultCoinMultiplierCommand(event.getDispatcher());
        new InfoCoinMultiplierCommand(event.getDispatcher());
        new InfoDamageMultiplierCommand(event.getDispatcher());
        new InfoTimeMultiplierCommand(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());
    }

}
