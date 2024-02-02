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
    private static final int[] DEFAULT_EFFECT_INSTANCES = new int[]{0, 0, 0, 0};
    private static final float[] DEFAULT_LAST_STATS = new float[]{0, 0, 0, 0};

    public static final double DEFAULT_COINS = 0;

    public static final float DEFAULT_COINS_MULTIPLIER = 1.0F;
    public static final double DEFAULT_TIME_PLAYED = 0;

    private PlayerTimeData playerTimeData = new PlayerTimeData(
            DEFAULT_TIME,
            DEFAULT_TIME,
            DEFAULT_IS_TIME_STOPPED,
            DEFAULT_TIME_MULTIPLIER,
            DEFAULT_DAMAGE_MULTIPLIER,
            DEFAULT_EFFECT_INSTANCES,
            DEFAULT_LAST_STATS,
            DEFAULT_COINS,
            DEFAULT_COINS_MULTIPLIER,
            DEFAULT_TIME_PLAYED
    );
    public static final String COMPOUND_TAG_KEY = "player_time_data";

    public static final int LAST_INCREMENT_INDEX = 0;
    public static final int LAST_DECREMENT_INDEX = 1;


    public void syncServerToClientData(ServerPlayer player) {
        ModMessages.sendToPlayer(new TimeDataSyncS2CPacket(this.playerTimeData), player);
    }

    public PlayerTimeData getPlayerTimeData() {
        return this.playerTimeData;
    }
    private void setLastStats(float[] lastStats) {
        this.playerTimeData.setLastStats(lastStats);
    }

    public void setLastStats(float[] lastStats, ServerPlayer player) {
        this.setLastStats(lastStats);
        syncServerToClientData(player);
    }

    private void setLastIncrement(float lastTime) {
        this.playerTimeData.getLastStats()[LAST_INCREMENT_INDEX] = lastTime;
    }

    private void setLastDecrement(float lastTime) {
        this.playerTimeData.getLastStats()[LAST_DECREMENT_INDEX] = lastTime;
    }

    public float getLastIncrement() {
        return this.playerTimeData.getLastStats()[LAST_INCREMENT_INDEX];
    }

    public float getLastDecrement() {
        return this.playerTimeData.getLastStats()[LAST_DECREMENT_INDEX];
    }


    public void setPlayerTimeData(PlayerTimeData playerTimeData) {
        this.playerTimeData = playerTimeData;
    }

    public void setPlayerTimeData(PlayerTimeData playerTimeData, ServerPlayer player) {
        this.setPlayerTimeData(playerTimeData);
        syncServerToClientData(player);
    }

    private void setTime(float time) {
        this.playerTimeData.setTime(time);
    }

    public void setTime(float time, ServerPlayer player) {
        this.setTime(time);
        syncServerToClientData(player);
    }

    private void setMaxTime(float maxTime) {
        this.playerTimeData.setMaxTime(maxTime);
    }

    public void setMaxTime(float maxTime, ServerPlayer player) {
        this.setMaxTime(maxTime);
        syncServerToClientData(player);
    }

    private void incrementMaxTime(float increment) {
        float currentMaxTime = this.playerTimeData.getMaxTime();
        float newMaxTime = Math.min(currentMaxTime + increment, 30*3600);
        this.playerTimeData.setMaxTime(newMaxTime);
    }

    public void incrementMaxTime(float increment, ServerPlayer player) {
        this.incrementMaxTime(increment);
        syncServerToClientData(player);
    }

    private void decrementMaxTime(float decrement) {
        float currentMaxTime = this.playerTimeData.getMaxTime();
        float newMaxTime = Math.max(currentMaxTime - decrement, 3600);
        this.playerTimeData.setMaxTime(newMaxTime);
    }

    public void decrementMaxTime(float decrement, ServerPlayer player) {
        this.decrementMaxTime(decrement);
        syncServerToClientData(player);
    }

    private void setIsTimeStopped(boolean isTimeStopped) {
        this.playerTimeData.setIsTimeStopped(isTimeStopped);
    }

    public void setIsTimeStopped(boolean isTimeStopped, ServerPlayer player) {
        this.setIsTimeStopped(isTimeStopped);
        syncServerToClientData(player);
    }

    private void setTimeMultiplier(float timeMultiplier) {
        this.playerTimeData.setTimeMultiplier(timeMultiplier);
    }

    public void setTimeMultiplier(float timeMultiplier, ServerPlayer player) {
        this.setTimeMultiplier(timeMultiplier);
        syncServerToClientData(player);
    }

    public void incrementTimeMultiplier(float timeMultiplier, ServerPlayer player) {
        float currentTimeMultiplier = this.getTimeMultiplier();
        float newTimeMultiplier = Math.min(currentTimeMultiplier + timeMultiplier, Float.MAX_VALUE);
        this.setTimeMultiplier(newTimeMultiplier, player);
    }

    public void decrementTimeMultiplier(float timeMultiplier, ServerPlayer player) {
        float currentTimeMultiplier = this.getTimeMultiplier();
        float newTimeMultiplier = Math.max(currentTimeMultiplier - timeMultiplier, 0.01f);
        this.setTimeMultiplier(newTimeMultiplier, player);
    }

    public void setDefaultTimeMultiplier(ServerPlayer player) {
        this.setTimeMultiplier(DEFAULT_TIME_MULTIPLIER, player);
    }

    private void setDamageMultiplier(float damageMultiplier) {
        this.playerTimeData.setDamageMultiplier(damageMultiplier);
    }

    public void setEffectInstancesDuration(int[] effectInstancesDuration) {
        this.playerTimeData.setEffectInstancesDuration(effectInstancesDuration);
    }

    public static int timeExtinguisherEffectIndex = 0;
    public static int timeExtinguisherTickCountIndex = timeExtinguisherEffectIndex + 1;
    public static int timeIgniteEffectIndex = 2;
    public static int timeIgniteTickCountIndex = timeIgniteEffectIndex + 1;

    public void setTimeExtinguisherEffectDuration(int duration) {
        this.playerTimeData.getEffectInstancesDuration()[timeExtinguisherEffectIndex] = duration;
    }

    public void setTimeExtinguisherTickCount(int tickCount) {
        this.playerTimeData.getEffectInstancesDuration()[timeExtinguisherTickCountIndex] = tickCount;
    }

    public void setTimeIgniteEffectDuration(int duration) {
        this.playerTimeData.getEffectInstancesDuration()[timeIgniteEffectIndex] = duration;
    }

    public void setTimeIgniteTickCount(int tickCount) {
        this.playerTimeData.getEffectInstancesDuration()[timeIgniteTickCountIndex] = tickCount;
    }

    public void setTimeExtinguisherEffectDuration(int duration, ServerPlayer player) {
        this.setTimeExtinguisherEffectDuration(duration);
        syncServerToClientData(player);
    }

    public void setTimeExtinguisherTickCount(int tickCount, ServerPlayer player) {
        this.setTimeExtinguisherTickCount(tickCount);
        syncServerToClientData(player);
    }

    public void setTimeIgniteEffectDuration(int duration, ServerPlayer player) {
        this.setTimeIgniteEffectDuration(duration);
        syncServerToClientData(player);
    }

    public void setTimeIgniteTickCount(int tickCount, ServerPlayer player) {
        this.setTimeIgniteTickCount(tickCount);
        syncServerToClientData(player);
    }

    private void setCoins(double coins) {
        this.playerTimeData.setCoins(coins);
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

    public int[] getEffectInstancesDuration() {
        return this.playerTimeData.getEffectInstancesDuration();
    }

    public int getTimeExtinguisherEffectDuration() {
        return this.getEffectInstancesDuration()[0];
    }

    public int getTimeExtinguisherEffectTickCount() {
        return this.getEffectInstancesDuration()[1];
    }

    public int getTimeIgniteEffectDuration() {
        return this.getEffectInstancesDuration()[2];
    }

    public int getTimeIgniteEffectTickCount() {
        return this.getEffectInstancesDuration()[3];
    }

    public double getCoins() {
        return this.playerTimeData.getCoins();
    }

    public float getCoinsMultiplier() {
        return this.playerTimeData.getCoinsMultiplier();
    }

    public double getTimePlayed() {
        return this.playerTimeData.getTimePlayed();
    }

    private void incrementTime(float increment) {
        float currentTime = this.playerTimeData.getTime();
        float multiplier = this.playerTimeData.getTimeMultiplier();
        float maxTime = this.playerTimeData.getMaxTime();
        float v = increment * multiplier;
        float newTime = Math.min(currentTime + v, maxTime);
        this.playerTimeData.setTime(newTime);
        this.setLastIncrement(v);
    }

    public void incrementTime(float increment, ServerPlayer player, boolean withEffect) {
        this.incrementTime(increment);
        syncServerToClientData(player);
        if (withEffect) {
            player.addEffect(new MobEffectInstance(ModEffects.HEAL_TRIGGER.get(), 8,
                    1, false, false, false));
        }
    }

    public void incrementTime(float increment, ServerPlayer player) {
        this.incrementTime(increment, player, true);
    }


    public void decrementTime(float decrement) {
        float currentTime = this.playerTimeData.getTime();
        float multiplier = this.playerTimeData.getTimeMultiplier();
        float d = decrement * multiplier;
        float newTime = Math.max(currentTime - d, MIN_TIME);
        this.playerTimeData.setTime(newTime);
        this.setLastDecrement(d);
    }

    public void decrementTime(float decrement, ServerPlayer player, boolean withEffect) {
        this.decrementTime(decrement);
        syncServerToClientData(player);
        if (withEffect) {
            player.addEffect(new MobEffectInstance(ModEffects.DAMAGE_TRIGGER.get(), 8,
                    1, false, false, false));
        }
    }

    public void decrementTime(float decrement, ServerPlayer player) {
        this.decrementTime(decrement, player, false);
    }

    public void incrementTimePlayed(double increment) {
        double currentTimePlayed = this.playerTimeData.getTimePlayed();
        double newTimePlayed = currentTimePlayed + increment;
        this.playerTimeData.setTimePlayed(newTimePlayed);
    }

    public void incrementTimePlayed(double increment, ServerPlayer player) {
        this.incrementTimePlayed(increment);
        syncServerToClientData(player);
    }

    public void decrementTimePlayed(double decrement) {
        double currentTimePlayed = this.playerTimeData.getTimePlayed();
        double newTimePlayed = Math.max(currentTimePlayed - decrement, 0);
        this.playerTimeData.setTimePlayed(newTimePlayed);
    }

    public void decrementTimePlayed(double decrement, ServerPlayer player) {
        this.decrementTimePlayed(decrement);
        syncServerToClientData(player);
    }


    private void resetTime() {
        float currentTime = this.playerTimeData.getTime();
        float maxTime = this.playerTimeData.getMaxTime();
        float multiplier = this.playerTimeData.getTimeMultiplier();
        float difference = maxTime - currentTime * multiplier;
        this.incrementTime(difference);
    }

    public void resetTime(ServerPlayer player) {
        this.resetTime();
        syncServerToClientData(player);
    }

    private void toggleTimeStatus() {
        this.playerTimeData.setIsTimeStopped(!this.playerTimeData.isTimeStopped());
    }

    public void toggleTimeStatus(ServerPlayer player) {
        this.toggleTimeStatus();
        syncServerToClientData(player);
    }

    private void stopTimeStatus() {
        this.playerTimeData.setIsTimeStopped(true);
    }

    public void stopTimeStatus(ServerPlayer player) {
        if (!this.playerTimeData.isTimeStopped() && this.playerTimeData.getTime() > 0) {
            this.stopTimeStatus();
            syncServerToClientData(player);
        }
    }

    private void startTimeStatus() {
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

    public void incrementDamageMultiplier(float damageMultiplier, ServerPlayer player) {
        float currentDamageMultiplier = this.getDamageMultiplier();
        float newDamageMultiplier = Math.min(currentDamageMultiplier + damageMultiplier, Float.MAX_VALUE);
        this.setDamageMultiplier(newDamageMultiplier, player);
    }

    public void decrementDamageMultiplier(float damageMultiplier, ServerPlayer player) {
        float currentDamageMultiplier = this.getDamageMultiplier();
        float newDamageMultiplier = Math.max(currentDamageMultiplier - damageMultiplier, 0.1f);
        this.setDamageMultiplier(newDamageMultiplier, player);
    }

    public void setDefaultDamageMultiplier(ServerPlayer player) {
        this.setDamageMultiplier(DEFAULT_DAMAGE_MULTIPLIER, player);

    }

    public void setCoins(double coins, ServerPlayer player) {
        this.setCoins(coins);
        syncServerToClientData(player);
    }

    public void incrementCoins(long coins, ServerPlayer player) {
        double currentCoins = this.getCoins();
        float coinsMultiplier = this.getCoinsMultiplier();
        double newCoins = Math.min(currentCoins + coins * coinsMultiplier, Long.MAX_VALUE);
        this.setCoins(newCoins, player);

    }

    public void decrementCoins(long coins, ServerPlayer player) {
        double currentCoins = this.getCoins();
        float coinsMultiplier = this.getCoinsMultiplier();
        double newCoins = Math.max(currentCoins - coins * coinsMultiplier, 0);
        this.setCoins(newCoins, player);
    }

    public void setCoinsMultiplier(float coinsMultiplier, ServerPlayer player) {
        this.playerTimeData.setCoinsMultiplier(coinsMultiplier);
        syncServerToClientData(player);
    }

    public void incrementCoinMultiplier(float coinsMultiplier, ServerPlayer player) {
        float currentCoinMultiplier = this.getCoinsMultiplier();
        float newCoinMultiplier = Math.min(currentCoinMultiplier + coinsMultiplier, Float.MAX_VALUE);
        this.setCoinsMultiplier(newCoinMultiplier, player);
    }

    public void decrementCoinMultiplier(float coinsMultiplier, ServerPlayer player) {
        float currentCoinMultiplier = this.getCoinsMultiplier();
        float newCoinMultiplier = Math.max(currentCoinMultiplier - coinsMultiplier, DEFAULT_COINS_MULTIPLIER);
        this.setCoinsMultiplier(newCoinMultiplier, player);
    }

    public void setDefaultCoinMultiplier(ServerPlayer player) {
        this.setCoinsMultiplier(DEFAULT_COINS_MULTIPLIER, player);
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
        playerTimeDataTag.putIntArray("effect_instances_duration", playerTimeData.getEffectInstancesDuration());
        playerTimeDataTag.putDouble("coins", playerTimeData.getCoins());
        playerTimeDataTag.putFloat("coins_multiplier", playerTimeData.getCoinsMultiplier());
        playerTimeDataTag.putDouble("time_played", playerTimeData.getTimePlayed());
        compoundTag.put(COMPOUND_TAG_KEY, playerTimeDataTag);
    }

    public void loadNBTData(CompoundTag compoundTag) {
        CompoundTag playerTimeDataTag = compoundTag.getCompound(COMPOUND_TAG_KEY);
        playerTimeData.setMaxTime(playerTimeDataTag.getFloat("max_time"));
        playerTimeData.setTime(playerTimeDataTag.getFloat("time"));
        playerTimeData.setIsTimeStopped(playerTimeDataTag.getBoolean("is_time_stopped"));
        playerTimeData.setTimeMultiplier(playerTimeDataTag.getFloat("time_multiplier"));
        playerTimeData.setDamageMultiplier(playerTimeDataTag.getFloat("damage_multiplier"));
        playerTimeData.setEffectInstancesDuration(playerTimeDataTag.getIntArray("effect_instances_duration"));
        playerTimeData.setCoins(playerTimeDataTag.getDouble("coins"));
        playerTimeData.setCoinsMultiplier(playerTimeDataTag.getFloat("coins_multiplier"));
        playerTimeData.setTimePlayed(playerTimeDataTag.getDouble("time_played"));

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


    public String getFormattedPlayedTime() {
        return getFormattedTime(this.playerTimeData.getTimePlayed());
    }

    private String getFormattedTime(double timePlayed) {
        return FormatTimeType.getFormattedStringByType(FormatTimeType.DEPENDS_NAMED, (float) timePlayed);
    }

    public void setTimePlayed(int timeToSet, ServerPlayer player) {
        this.playerTimeData.setTimePlayed(timeToSet);
        syncServerToClientData(player);

    }
}
