package net.most.survivaltimemod.time;

public class PlayerTimeData {

    public float MAX_TIME;
    public float TIME;
    public boolean IS_TIME_STOPPED;
    public float TIME_MULTIPLIER;

    public PlayerTimeData(float maxTime, float time, boolean isTimeStopped, float timeMultiplier) {
        this.MAX_TIME = maxTime;
        this.TIME = time;
        this.IS_TIME_STOPPED = isTimeStopped;
        this.TIME_MULTIPLIER = timeMultiplier;
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


}
