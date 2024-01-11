package net.most.survivaltimemod.time;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.most.survivaltimemod.data.FormatTimeType;
import net.most.survivaltimemod.effect.ModEffects;
import net.most.survivaltimemod.networking.ModMessages;
import net.most.survivaltimemod.networking.packet.TimeDataSyncS2CPacket;

public class PlayerTime {


    //MAX_TIME IN SECONDS, MAX = 10 HOURS

    private static final float DEFAULT_TIME = 36000.0F;
    private static final float MIN_TIME = 0.0F;

    private static final boolean DEFAULT_IS_TIME_STOPPED = false;
    private static final float DEFAULT_TIME_MULTIPLIER = 1.0F;
    private static final float DEFAULT_DAMAGE_MULTIPLIER = 20.0F;

    private PlayerTimeData playerTimeData = new PlayerTimeData(
            DEFAULT_TIME,
            DEFAULT_TIME,
            DEFAULT_IS_TIME_STOPPED,
            DEFAULT_TIME_MULTIPLIER,
            DEFAULT_DAMAGE_MULTIPLIER
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
        this.setPlayerTimeData(playerTimeData);
        syncServerToClientData(player);
    }

    public void setTime(float time) {
        this.playerTimeData.setTime(time);
    }

    public void setTime(float time, ServerPlayer player) {
        this.setTime(time);
        syncServerToClientData(player);
    }

    public void setMaxTime(float maxTime) {
        this.playerTimeData.setMaxTime(maxTime);
    }

    public void setMaxTime(float maxTime, ServerPlayer player) {
        this.setMaxTime(maxTime);
        syncServerToClientData(player);
    }

    public void incrementMaxTime(float increment) {
        float currentMaxTime = this.playerTimeData.getMaxTime();
        float newMaxTime = currentMaxTime + increment;
        this.playerTimeData.setMaxTime(newMaxTime);
    }

    public void incrementMaxTime(float increment, ServerPlayer player) {
        this.incrementMaxTime(increment);
        syncServerToClientData(player);
    }

    public void decrementMaxTime(float decrement) {
        float currentMaxTime = this.playerTimeData.getMaxTime();
        float newMaxTime = Math.max(currentMaxTime - decrement, MIN_TIME);
        this.playerTimeData.setMaxTime(newMaxTime);
    }

    public void decrementMaxTime(float decrement, ServerPlayer player) {
        this.decrementMaxTime(decrement);
        syncServerToClientData(player);
    }

    public void setIsTimeStopped(boolean isTimeStopped) {
        this.playerTimeData.setIsTimeStopped(isTimeStopped);
    }

    public void setIsTimeStopped(boolean isTimeStopped, ServerPlayer player) {
        this.setIsTimeStopped(isTimeStopped);
        syncServerToClientData(player);
    }

    public void setTimeMultiplier(float timeMultiplier) {
        this.playerTimeData.setTimeMultiplier(timeMultiplier);
    }

    public void setTimeMultiplier(float timeMultiplier, ServerPlayer player) {
        this.setTimeMultiplier(timeMultiplier);
        syncServerToClientData(player);
    }

    public void setDefaultTimeMultiplier(ServerPlayer player) {
        this.setTimeMultiplier(DEFAULT_TIME_MULTIPLIER, player);
    }

    public void setDamageMultiplier(float damageMultiplier) {
        this.playerTimeData.setDamageMultiplier(damageMultiplier);
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

    public float getDamageMultiplier() {
        return this.playerTimeData.getDamageMultiplier();
    }

    public void incrementTime(float increment) {
        float currentTime = this.playerTimeData.getTime();
        float multiplier = this.playerTimeData.getTimeMultiplier();
        float maxTime = this.playerTimeData.getMaxTime();
        float newTime = Math.min(currentTime + (increment * multiplier), maxTime);
        this.playerTimeData.setTime(newTime);
    }

    public void incrementTime(float increment, ServerPlayer player, boolean withEffect) {
        this.incrementTime(increment);
        syncServerToClientData(player);
        if (withEffect) {
            player.addEffect(new MobEffectInstance(ModEffects.HEAL_TRIGGER.get(), 8,
                    1, false, false));
        }
    }

    public void incrementTime(float increment, ServerPlayer player) {
        this.incrementTime(increment, player, true);
    }


    public void decrementTime(float decrement) {
        float currentTime = this.playerTimeData.getTime();
        float multiplier = this.playerTimeData.getTimeMultiplier();
        float newTime = Math.max(currentTime - (decrement * multiplier), MIN_TIME);
        this.playerTimeData.setTime(newTime);
    }

    public void decrementTime(float decrement, ServerPlayer player, boolean withEffect) {
        this.decrementTime(decrement);
        syncServerToClientData(player);
        if (withEffect) {
            player.addEffect(new MobEffectInstance(ModEffects.DAMAGE_TRIGGER.get(), 8,
                    1, false, false));
        }
    }

    public void decrementTime(float decrement, ServerPlayer player) {
        this.decrementTime(decrement, player, false);
    }


    public void resetTime() {
        this.playerTimeData.setTime(DEFAULT_TIME);
    }

    public void resetTime(ServerPlayer player) {
        this.resetTime();
        syncServerToClientData(player);
    }

    public void toggleTimeStatus() {
        this.playerTimeData.setIsTimeStopped(!this.playerTimeData.isTimeStopped());
    }

    public void toggleTimeStatus(ServerPlayer player) {
        this.toggleTimeStatus();
        syncServerToClientData(player);
    }

    public void stopTimeStatus() {
        this.playerTimeData.setIsTimeStopped(true);
    }

    public void stopTimeStatus(ServerPlayer player) {
        if (!this.playerTimeData.isTimeStopped() && this.playerTimeData.getTime() > 0) {
            this.stopTimeStatus();
            syncServerToClientData(player);
        }
    }

    public void startTimeStatus() {
        this.playerTimeData.setIsTimeStopped(false);
    }

    public void startTimeStatus(ServerPlayer player) {
        if (this.playerTimeData.isTimeStopped()) {
            this.startTimeStatus();
            syncServerToClientData(player);
        }
    }


    public void setDamageMultiplier(float damageMultiplier, ServerPlayer player) {
        this.setDamageMultiplier(damageMultiplier);
        syncServerToClientData(player);
    }

    public void setDefaultDamageMultiplier(ServerPlayer player) {
        this.setDamageMultiplier(DEFAULT_DAMAGE_MULTIPLIER, player);
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
        playerTimeDataTag.putFloat("damage_multiplier", playerTimeData.getDamageMultiplier());
        compoundTag.put(COMPOUND_TAG_KEY, playerTimeDataTag);
    }

    public void loadNBTData(CompoundTag compoundTag) {
        CompoundTag playerTimeDataTag = compoundTag.getCompound(COMPOUND_TAG_KEY);
        playerTimeData.setMaxTime(playerTimeDataTag.getFloat("max_time"));
        playerTimeData.setTime(playerTimeDataTag.getFloat("time"));
        playerTimeData.setIsTimeStopped(playerTimeDataTag.getBoolean("is_time_stopped"));
        playerTimeData.setTimeMultiplier(playerTimeDataTag.getFloat("time_multiplier"));
        playerTimeData.setDamageMultiplier(playerTimeDataTag.getFloat("damage_multiplier"));
    }

    private String getFormattedTime(float time) {
        float hours = time / 3600;
        float minutes = (time % 3600) / 60;
        float seconds = time % 60;

        return String.format("%02d:%02d:%02d", (int) hours, (int) minutes, (int) seconds);
    }

    public String getFormattedTime() {
        return getFormattedTime(this.playerTimeData.getTime());

    }

    public String getFormattedTime(FormatTimeType type) {
        return FormatTimeType.getFormattedStringByType(type, this.playerTimeData.getTime());
    }


    public String getFormattedMaxTime() {
        return getFormattedTime(this.playerTimeData.getMaxTime());
    }
}
