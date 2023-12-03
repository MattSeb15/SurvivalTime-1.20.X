package net.most.survivaltimemod.events;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.gui.overlay.NamedGuiOverlay;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.most.survivaltimemod.SurvivalTimeMod;
import net.most.survivaltimemod.data.PlayerTime;

@Mod.EventBusSubscriber(modid = SurvivalTimeMod.MOD_ID)
public class ModEvents {

    private static int tickCounter = 0;

    @SubscribeEvent
    public static void onPlayerHurt(LivingDamageEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            // Cancela el evento para evitar que el jugador reciba daño de la manera predeterminada
            float damage = event.getAmount();
            event.setCanceled(true);

            // Implementa tu propio sistema de daño basado en el tiempo
            int time = PlayerTime.getTime(player);
            if (time <= 0) {
                // Si el tiempo del jugador es 0 o menos, mata al jugador
                player.kill();
            } else {
                // Si no, decrementa el tiempo del jugador

                int timeToDecrement = (int) (damage * 20);

                player.displayClientMessage(
                        Component.literal("Has recibido " + damage + " y perdido " + timeToDecrement + " segundos de tiempo."),
                        false);

                PlayerTime.decrementTime(player, timeToDecrement);
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {

        if (event.getEntity() instanceof ServerPlayer player) {

            CompoundTag playerData = player.getPersistentData();
            if (playerData.contains(PlayerTime.COMPOUND_TAG_KEY)) {
                int time = playerData.getInt(PlayerTime.COMPOUND_TAG_KEY);
                PlayerTime.setTime(player, time);
            } else {
                playerData.putInt(PlayerTime.COMPOUND_TAG_KEY, PlayerTime.DEFAULT_TIME);
                PlayerTime.setTime(player, PlayerTime.DEFAULT_TIME);
            }

        }
    }

    @SubscribeEvent
    public static void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {

        if (event.getEntity() instanceof ServerPlayer player) {

            CompoundTag playerData = player.getPersistentData();
            int time = PlayerTime.getTime(player);
            playerData.putInt(PlayerTime.COMPOUND_TAG_KEY, time);
        }
    }

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {

        if (event.phase == TickEvent.Phase.END) {
            tickCounter++;
            if (tickCounter >= 20) {
                for (ServerPlayer player : event.getServer().getPlayerList().getPlayers()) {

                    PlayerTime.decrementTime(player, 1);

                    player.displayClientMessage(
                            Component.literal("Time: " + PlayerTime.getFormattedTime(player)),
                            true);
                }
                tickCounter = 0;
            }

        }
    }

    @SubscribeEvent
    public static void onPlayerDeath(LivingDeathEvent event) {

        if (event.getEntity() instanceof ServerPlayer player) {
            player.displayClientMessage(
                    Component.literal("Haz muerto, perderás tiempo al morir."),
                    false);
            //perder una hora de tiempo
            PlayerTime.decrementTime(player, 3600);
            PlayerTime.stopTime(player);
        }
    }

    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            PlayerTime.startTime(player);
        }
    }

}
