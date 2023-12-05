package net.most.survivaltimemod.data;

import net.minecraft.server.level.ServerPlayer;

import java.util.HashMap;
import java.util.UUID;

public class PlayerTime {


    //MAX_TIME IN SECONDS, MAX = 10 HOURS

    private static final float DEFAULT_TIME = 10 * 3600;
    private static final float MIN_TIME = 0.0F;

    public static final String COMPOUND_TAG_KEY = "player_time_counter";
    private static final HashMap<UUID, PlayerTimeData> playertimedata = new HashMap<>();

    public static String getFormattedTime(UUID playerUUID) {

        float time = getTime(playerUUID);
        float hours = time / 3600;
        float minutes = (time % 3600) / 60;
        float seconds = time % 60;

        return String.format("%02d:%02d:%02d", (int) hours, (int) minutes, (int) seconds);
    }

    public static String getFormattedTime(float time) {

        float hours = time / 3600;
        float minutes = (time % 3600) / 60;
        float seconds = time % 60;

        return String.format("%02d:%02d:%02d", (int) hours, (int) minutes, (int) seconds);
    }

    public static String getFormattedTime(float time, FormatTimeType type) {
        return FormatTimeType.getFormattedStringByType(type, time);
    }

    public static PlayerTimeData getPlayerTimeData(UUID playerUUID) {
        return playertimedata.getOrDefault(playerUUID, new PlayerTimeData(
                DEFAULT_TIME,
                DEFAULT_TIME,
                false,
                1.0f
        ));
    }


    public static float getTimeMultiplier(UUID playerUUID) {
        return getPlayerTimeData(playerUUID).getTimeMultiplier();
    }

    public static void setTimeMultiplier(UUID playerUUID, float multiplier) {
        PlayerTimeData playerTimeData = getPlayerTimeData(playerUUID);
        playerTimeData.setTimeMultiplier(multiplier);
        playertimedata.put(playerUUID, playerTimeData);
    }

    public static float getTime(UUID playerUUID) {
        return getPlayerTimeData(playerUUID).getTime();
    }

    public static float getMaxTime(UUID playerUUID) {
        return getPlayerTimeData(playerUUID).getMaxTime();
    }


    public static void setTime(UUID playerUUID, float time) {

        PlayerTimeData playerTimeData = getPlayerTimeData(playerUUID);
        playerTimeData.setTime(time);
        playertimedata.put(playerUUID, playerTimeData);
    }

    public static void resetTime(UUID playerUUID) {
        PlayerTimeData playerTimeData = getPlayerTimeData(playerUUID);
        playerTimeData.setTime(DEFAULT_TIME);
        playertimedata.put(playerUUID, playerTimeData);
    }


    public static void incrementTime(UUID playerUUID, float increment) {
        float maxTime = getMaxTime(playerUUID);
        float currentTime = getTime(playerUUID);
        float multiplier = getTimeMultiplier(playerUUID);
        setTime(playerUUID, Math.min(currentTime + (increment * multiplier), maxTime));

    }

    public static void decrementTime(UUID playerUUID, float decrement) {

        float currentTime = getTime(playerUUID);
        float multiplier = getTimeMultiplier(playerUUID);
        setTime(playerUUID, Math.max(currentTime - (decrement * multiplier), MIN_TIME));
    }

    public static boolean getIsTimeStopped(UUID playerUUID) {
        return getPlayerTimeData(playerUUID).isTimeStopped();
    }

    public static void toggleTimeStatus(UUID playerUUID) {
        PlayerTimeData playerTimeData = getPlayerTimeData(playerUUID);
        playerTimeData.setIsTimeStopped(!playerTimeData.isTimeStopped());
        playertimedata.put(playerUUID, playerTimeData);
    }

    public static void stopTimeStatus(UUID playerUUID) {
        PlayerTimeData playerTimeData = getPlayerTimeData(playerUUID);
        if (!playerTimeData.isTimeStopped() && playerTimeData.getTime() > 0) {
            playerTimeData.setIsTimeStopped(true);
            playertimedata.put(playerUUID, playerTimeData);
        }
    }

    public static void startTimeStatus(UUID playerUUID) {
        PlayerTimeData playerTimeData = getPlayerTimeData(playerUUID);
        if (playerTimeData.isTimeStopped()) {
            playerTimeData.setIsTimeStopped(false);
            playertimedata.put(playerUUID, playerTimeData);
        }
    }
}
