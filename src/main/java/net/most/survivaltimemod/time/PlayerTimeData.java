package net.most.survivaltimemod.time;

public class PlayerTimeData {

    public float MAX_TIME;
    public float TIME;
    public boolean IS_TIME_STOPPED;
    public float TIME_MULTIPLIER;
    public float DAMAGE_MULTIPLIER;
    public int[] EFFECT_INSTANCES_DURATION;
    public float[] LAST_STATS;
    public double COINS;
    public float COINS_MULTIPLIER;

    public PlayerTimeData(float maxTime, float time, boolean isTimeStopped, float timeMultiplier, float damageMultiplier, int[] effectInstancesDuration, float[] lastStats, double coins, float coinsMultiplier) {
        this.MAX_TIME = maxTime;
        this.TIME = time;
        this.IS_TIME_STOPPED = isTimeStopped;
        this.TIME_MULTIPLIER = timeMultiplier;
        this.DAMAGE_MULTIPLIER = damageMultiplier;
        this.EFFECT_INSTANCES_DURATION = effectInstancesDuration;
        this.LAST_STATS = lastStats;
        this.COINS = coins;
        this.COINS_MULTIPLIER = coinsMultiplier;
    }

    public float getMaxTime() {
        return this.MAX_TIME;
    }

    public float getTime() {
        return this.TIME;
    }

    public boolean isTimeStopped() {
        return this.IS_TIME_STOPPED;
    }

    public float getTimeMultiplier() {
        return this.TIME_MULTIPLIER;
    }

    public int[] getEffectInstancesDuration() {
        return this.EFFECT_INSTANCES_DURATION;
    }

    public float[] getLastStats() {
        return this.LAST_STATS;
    }

    public double getCoins() {
        return this.COINS;
    }

    public float getCoinsMultiplier() {
        return this.COINS_MULTIPLIER;
    }

    public void setMaxTime(float maxTime) {
        this.MAX_TIME = maxTime;
    }

    public void setTime(float time) {
        this.TIME = time;
    }

    public void setIsTimeStopped(boolean isTimeStopped) {
        this.IS_TIME_STOPPED = isTimeStopped;
    }

    public void setTimeMultiplier(float timeMultiplier) {
        this.TIME_MULTIPLIER = timeMultiplier;
    }

    public float getDamageMultiplier() {
        return this.DAMAGE_MULTIPLIER;
    }

    public void setDamageMultiplier(float damageMultiplier) {
        this.DAMAGE_MULTIPLIER = damageMultiplier;
    }

    public void setEffectInstancesDuration(int[] effectInstancesDuration) {
        this.EFFECT_INSTANCES_DURATION = effectInstancesDuration;
    }

    public void setLastStats(float[] lastStats) {
        this.LAST_STATS = lastStats;
    }

    public void setCoins(double coins) {
        this.COINS = coins;
    }

    public void setCoinsMultiplier(float coinsMultiplier) {
        this.COINS_MULTIPLIER = coinsMultiplier;
    }
}
