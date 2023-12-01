package net.most.survivaltimemod.util;

public class WeightMinMax {
    private final float min;
    private final float max;

    private final int weight;

    public WeightMinMax(int weight, float min, float max) {
        this.min = min;
        this.max = max;
        this.weight = weight;
    }

    public float getMin() {
        return min;
    }

    public float getMax() {
        return max;
    }

    public int getWeight() {
        return weight;
    }


}
