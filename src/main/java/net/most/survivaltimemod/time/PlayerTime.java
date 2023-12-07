package net.most.survivaltimemod.time;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.most.survivaltimemod.data.FormatTimeType;
import net.most.survivaltimemod.networking.ModMessages;
import net.most.survivaltimemod.networking.packet.TimeDataSyncS2CPacket;

public class PlayerTime {


    //MAX_TIME IN SECONDS, MAX = 10 HOURS

    private static final float DEFAULT_TIME = 36000.0F;
    private static final float MIN_TIME = 0.0F;
    private PlayerTimeData playerTimeData = new PlayerTimeData(
            DEFAULT_TIME,
            DEFAULT_TIME,
            false,
            1.0f
    );
    public static final String COMPOUND_TAG_KEY = "player_time_data";

    public void syncServerToClientData(ServerPlayer player) {
        ModMessages.sendToPlayer(new TimeDataSyncS2CPacket(this.playerTimeData), player);
    }

    public PlayerTimeData getPlayerTimeData() {
        return this.playerTimeData;
    }

    public void setPlayerTimeData(PlayerTimeData playerTimeData) {
        this.playerTimeData = playerTimeData;
    }

    public void setPlayerTimeData(PlayerTimeData playerTimeData, ServerPlayer player) {
        this.playerTimeData = playerTimeData;
        syncServerToClientData(player);
    }

    public void setTime(float time) {
        this.playerTimeData.setTime(time);
    }

    public void setTime(float time, ServerPlayer player) {
        this.playerTimeData.setTime(time);
        syncServerToClientData(player);
    }

    public void setMaxTime(float maxTime) {
        this.playerTimeData.setMaxTime(maxTime);
    }

    public void setMaxTime(float maxTime, ServerPlayer player) {
        this.playerTimeData.setMaxTime(maxTime);
        syncServerToClientData(player);
    }

    public void setIsTimeStopped(boolean isTimeStopped) {
        this.playerTimeData.setIsTimeStopped(isTimeStopped);
    }

    public void setIsTimeStopped(boolean isTimeStopped, ServerPlayer player) {
        this.playerTimeData.setIsTimeStopped(isTimeStopped);
        syncServerToClientData(player);
    }

    public void setTimeMultiplier(float timeMultiplier) {
        this.playerTimeData.setTimeMultiplier(timeMultiplier);
    }

    public void setTimeMultiplier(float timeMultiplier, ServerPlayer player) {
        this.playerTimeData.setTimeMultiplier(timeMultiplier);
        syncServerToClientData(player);
    }

    public float getTime() {
        return this.playerTimeData.getTime();
    }

    public float getMaxTime() {
        return this.playerTimeData.getMaxTime();
    }

    public boolean isTimeStopped() {
        return this.playerTimeData.isTimeStopped();
    }

    public float getTimeMultiplier() {
        return this.playerTimeData.getTimeMultiplier();
    }

    public void incrementTime(float increment) {
        float currentTime = this.playerTimeData.getTime();
        float multiplier = this.playerTimeData.getTimeMultiplier();
        float maxTime = this.playerTimeData.getMaxTime();
        float newTime = Math.min(currentTime + (increment * multiplier), maxTime);
        this.playerTimeData.setTime(newTime);
    }

    public void incrementTime(float increment, ServerPlayer player) {
        float currentTime = this.playerTimeData.getTime();
        float multiplier = this.playerTimeData.getTimeMultiplier();
        float maxTime = this.playerTimeData.getMaxTime();
        float newTime = Math.min(currentTime + (increment * multiplier), maxTime);
        this.playerTimeData.setTime(newTime);
        syncServerToClientData(player);
    }

    public void decrementTime(float decrement) {
        float currentTime = this.playerTimeData.getTime();
        float multiplier = this.playerTimeData.getTimeMultiplier();
        float newTime = Math.max(currentTime - (decrement * multiplier), MIN_TIME);
        this.playerTimeData.setTime(newTime);
    }

    public void decrementTime(float decrement, ServerPlayer player) {
        float currentTime = this.playerTimeData.getTime();
        float multiplier = this.playerTimeData.getTimeMultiplier();
        float newTime = Math.max(currentTime - (decrement * multiplier), MIN_TIME);
        this.playerTimeData.setTime(newTime);
        syncServerToClientData(player);
    }

    public void resetTime() {
        this.playerTimeData.setTime(DEFAULT_TIME);
    }

    public void resetTime(ServerPlayer player) {
        this.playerTimeData.setTime(DEFAULT_TIME);
        syncServerToClientData(player);
    }

    public void toggleTimeStatus() {
        this.playerTimeData.setIsTimeStopped(!this.playerTimeData.isTimeStopped());
    }

    public void toggleTimeStatus(ServerPlayer player) {
        this.playerTimeData.setIsTimeStopped(!this.playerTimeData.isTimeStopped());
        syncServerToClientData(player);
    }

    public void stopTimeStatus() {
        if (!this.playerTimeData.isTimeStopped() && this.playerTimeData.getTime() > 0) {
            this.playerTimeData.setIsTimeStopped(true);
        }
    }

    public void stopTimeStatus(ServerPlayer player) {
        if (!this.playerTimeData.isTimeStopped() && this.playerTimeData.getTime() > 0) {
            this.playerTimeData.setIsTimeStopped(true);
            syncServerToClientData(player);
        }
    }

    public void startTimeStatus() {
        if (this.playerTimeData.isTimeStopped()) {
            this.playerTimeData.setIsTimeStopped(false);
        }
    }

    public void startTimeStatus(ServerPlayer player) {
        if (this.playerTimeData.isTimeStopped()) {
            this.playerTimeData.setIsTimeStopped(false);
            syncServerToClientData(player);
        }
    }

    public void copyFrom(PlayerTime playerTime) {
        this.playerTimeData = playerTime.playerTimeData;
    }

    public void saveNBTData(CompoundTag compoundTag) {
        CompoundTag playerTimeDataTag = new CompoundTag();
        playerTimeDataTag.putFloat("max_time", playerTimeData.getMaxTime());
        playerTimeDataTag.putFloat("time", playerTimeData.getTime());
        playerTimeDataTag.putBoolean("is_time_stopped", playerTimeData.isTimeStopped());
        playerTimeDataTag.putFloat("time_multiplier", playerTimeData.getTimeMultiplier());
        compoundTag.put(COMPOUND_TAG_KEY, playerTimeDataTag);
    }

    public void loadNBTData(CompoundTag compoundTag) {
        CompoundTag playerTimeDataTag = compoundTag.getCompound(COMPOUND_TAG_KEY);
        playerTimeData.setMaxTime(playerTimeDataTag.getFloat("max_time"));
        playerTimeData.setTime(playerTimeDataTag.getFloat("time"));
        playerTimeData.setIsTimeStopped(playerTimeDataTag.getBoolean("is_time_stopped"));
        playerTimeData.setTimeMultiplier(playerTimeDataTag.getFloat("time_multiplier"));
    }
    public String getFormattedTime() {

        float hours = this.playerTimeData.getTime() / 3600;
        float minutes = (this.playerTimeData.getTime() % 3600) / 60;
        float seconds = this.playerTimeData.getTime() % 60;

        return String.format("%02d:%02d:%02d", (int) hours, (int) minutes, (int) seconds);
    }

    public String getFormattedTime( FormatTimeType type) {
        return FormatTimeType.getFormattedStringByType(type, this.playerTimeData.getTime());
    }
}
