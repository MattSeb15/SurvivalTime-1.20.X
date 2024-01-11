package net.most.survivaltimemod.util.textures;

public enum TimeType {
    NORMAL(0),
    PAUSED(1),
    DAMAGE(2),
    REGEN(3),
    TEAR(4),
    GOLDEN(5);

    private final int index;

    TimeType(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
