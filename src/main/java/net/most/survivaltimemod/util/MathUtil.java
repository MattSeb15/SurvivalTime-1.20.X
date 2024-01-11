package net.most.survivaltimemod.util;

public class MathUtil {
    public static float percentageValue(float value, float percent) {
        return (percent / 100) * value;
    }

    public static int percentageValue(int value, float percent) {
        return (int) ((percent / 100) * value);
    }
}
