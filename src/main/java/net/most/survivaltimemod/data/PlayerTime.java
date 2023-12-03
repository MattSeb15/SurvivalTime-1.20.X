package net.most.survivaltimemod.data;

import net.minecraft.server.level.ServerPlayer;

import java.util.HashMap;
import java.util.UUID;

public class PlayerTime {


    //MAX_TIME IN SECONDS, MAX = 24 HOURS
    private static final int MAX_TIME = 86400;
    private static final int MIN_TIME = 0;
    public static final int DEFAULT_TIME = 86400;
    public static final String COMPOUND_TAG_KEY = "player_time_counter";

    //TODO: Crear una neuva clase que se encargue de gestionar el tiempo de los jugadores.
    private static final HashMap<UUID, Integer> playertime = new HashMap<>();
    private static final HashMap<UUID, Boolean> isTimeStopped = new HashMap<>();
    private static final HashMap<UUID, Float> timeMultiplier = new HashMap<>();

    public static String getFormattedTime(ServerPlayer player) {
        int totalSeconds = getTime(player);

        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds % 3600) / 60;
        int seconds = totalSeconds % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public static float getTimeMultiplier(ServerPlayer player) {
        return timeMultiplier.getOrDefault(player.getUUID(), 1.0f);
    }

    public static void setTimeMultiplier(ServerPlayer player, float multiplier) {
        timeMultiplier.put(player.getUUID(), multiplier);
    }

    public static int getTime(ServerPlayer player) {
        return playertime.getOrDefault(player.getUUID(), DEFAULT_TIME);
    }

    public static void setTime(ServerPlayer player, int time) {
        playertime.put(player.getUUID(), time);
    }

    public static void incrementTime(ServerPlayer player, int increment) {
        int currentTime = getTime(player);
        setTime(player, Math.min(currentTime + increment, MAX_TIME));
    }

    public static void decrementTime(ServerPlayer player, int decrement) {
        if (!isTimeStopped.getOrDefault(player.getUUID(), false)) {
            int currentTime = getTime(player);
            float multiplier = getTimeMultiplier(player);
            setTime(player, Math.max(currentTime - (int)(decrement * multiplier), MIN_TIME));
        }
    }

    public static void stopTime(ServerPlayer player) {
        isTimeStopped.put(player.getUUID(), true);
    }

    public static void startTime(ServerPlayer player) {
        isTimeStopped.put(player.getUUID(), false);
    }
}
