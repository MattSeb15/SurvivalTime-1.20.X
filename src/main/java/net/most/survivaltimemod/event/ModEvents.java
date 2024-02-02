package net.most.survivaltimemod.event;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.IpBanList;
import net.minecraft.server.players.IpBanListEntry;
import net.minecraft.server.players.PlayerList;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
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
import net.most.survivaltimemod.command.coinmultiplier.AddCoinMultiplierCommand;
import net.most.survivaltimemod.command.coinmultiplier.InfoCoinMultiplierCommand;
import net.most.survivaltimemod.command.coinmultiplier.SetCoinMultiplierCommand;
import net.most.survivaltimemod.command.coinmultiplier.SetDefaultCoinMultiplierCommand;
import net.most.survivaltimemod.command.dmultiplier.*;
import net.most.survivaltimemod.command.item.AddNbtToItem;
import net.most.survivaltimemod.command.maxtime.AddMaxTimeCommand;
import net.most.survivaltimemod.command.maxtime.InfoMaxTimeCommand;
import net.most.survivaltimemod.command.maxtime.SetMaxTimeCommand;
import net.most.survivaltimemod.command.maxtime.SubtractMaxTimeCommand;
import net.most.survivaltimemod.command.time.*;
import net.most.survivaltimemod.command.timeplayed.AddTimePlayedCommand;
import net.most.survivaltimemod.command.timeplayed.InfoTimePlayedCommand;
import net.most.survivaltimemod.command.timeplayed.SetTimePlayedCommand;
import net.most.survivaltimemod.command.timeplayed.SubtractTimePlayedCommand;
import net.most.survivaltimemod.command.tmultiplier.*;
import net.most.survivaltimemod.effect.ModEffects;
import net.most.survivaltimemod.item.ModItems;
import net.most.survivaltimemod.time.PlayerTime;
import net.most.survivaltimemod.time.PlayerTimeProvider;
import net.most.survivaltimemod.util.ComponentHelper;


import java.util.Date;
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
                    GameType gameType = player.gameMode.getGameModeForPlayer();
                    if (playerTime.isTimeStopped() || gameType == GameType.CREATIVE) {
                        return;
                    }
                    if (playerTime.getTime() <= 0 && gameType != GameType.SPECTATOR) {
                        player.setGameMode(GameType.SPECTATOR);
                        MinecraftServer server = player.level().getServer();
                        String playerName = player.getGameProfile().getName();
                        if (server != null) {
                            server.sendSystemMessage(Component.translatable("chat.notification.time_out", playerName).withStyle(ChatFormatting.RED));
                            PlayerList playerList = server.getPlayerList();
                            IpBanList ipBanList = playerList.getIpBans();
                            String reason = "Time out";
                            String source = "Server";
                            IpBanListEntry ipBanListEntry = new IpBanListEntry(player.getIpAddress(), new Date(), source, null, reason);
                            ipBanList.add(ipBanListEntry);
                            player.connection.disconnect(Component.translatable("multiplayer.disconnect.timed_out"));

                        }
                        return;
                    }
                    if (gameType == GameType.SPECTATOR && playerTime.getTime() > 0) {
                        player.setGameMode(GameType.SURVIVAL);
                    }

                    playerTime.incrementTimePlayed(1);
                    playerTime.decrementTime(1, player);
                    double timePlayed =  playerTime.getTimePlayed();
                    if(timePlayed % 1800 == 0){
                        int pCount = player.getRandom().nextInt(9) + 1;
                        if(!player.addItem(new ItemStack(ModItems.CHRONO_COIN.get(), pCount))){
                            player.drop(new ItemStack(ModItems.CHRONO_COIN.get(), pCount), true, true);
                        }
                    }
                    if(timePlayed% 7200 == 0){
                        playerTime.incrementCoinMultiplier(0.1f, player);
                        playerTime.decrementDamageMultiplier(0.1f, player);
                        player.displayClientMessage(ComponentHelper.getOnCoinsMultiplierChangeComponent(playerTime.getCoinsMultiplier(), playerTime.getDamageMultiplier()), false);
                    }
                    if(timePlayed % 10800 == 0){
                        playerTime.incrementTimeMultiplier(0.2f, player);
                        player.displayClientMessage(ComponentHelper.getOnTimeMultiplierChangeComponent(playerTime.getTimeMultiplier()), false);
                    }
                    if (timePlayed % 18000 == 0){
                        playerTime.incrementMaxTime(3600, player);
                        player.displayClientMessage(ComponentHelper.getOnMaxTimeChangeComponent(playerTime.getMaxTime()), false);
                    }
                    player.displayClientMessage(
                            Component.literal("T: " + playerTime.getFormattedTime() +" | MT: " + playerTime.getFormattedMaxTime() + " (x" +
                                    playerTime.getTimeMultiplier() + ")" + "TP: "+ playerTime.getFormattedPlayedTime()),
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
                    player.displayClientMessage(ComponentHelper.getOnPlayerHurtComponent(time, damage, damageMultiplier, timeToDecrement, playerTime.getTime()), false);

                }
            });

            player.addEffect(new MobEffectInstance(ModEffects.DAMAGE_TRIGGER.get(), 8, 1, false, false));

            //            player.drop(new ItemStack(Items.BEEF), true, true); //TODO: REFACTOR CODE

        }

    }

    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            if (!event.getObject().getCapability(PlayerTimeProvider.PLAYER_TIME_CAPABILITY).isPresent()) {
                event.addCapability(new ResourceLocation(SurvivalTimeMod.MOD_ID, "player_time"), new PlayerTimeProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            event.getOriginal().getCapability(PlayerTimeProvider.PLAYER_TIME_CAPABILITY).ifPresent(oldStore -> event.getOriginal().getCapability(PlayerTimeProvider.PLAYER_TIME_CAPABILITY).ifPresent(newStore -> newStore.copyFrom(oldStore)));
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
        new SetTimePlayedCommand(event.getDispatcher());
        new AddTimePlayedCommand(event.getDispatcher());
        new InfoTimePlayedCommand(event.getDispatcher());
        new SubtractTimePlayedCommand(event.getDispatcher());
        new AddCoinMultiplierCommand(event.getDispatcher());
        new SubtractTimeMultiplierCommand(event.getDispatcher());
        new AddDamageMultiplierCommand(event.getDispatcher());
        new SubtractDamageMultiplierCommand(event.getDispatcher());
        new AddMaxTimeCommand(event.getDispatcher());
        new SubtractMaxTimeCommand(event.getDispatcher());
        new AddTimeMultiplierCommand(event.getDispatcher());
        new SubtractTimeMultiplierCommand(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());
    }

}
