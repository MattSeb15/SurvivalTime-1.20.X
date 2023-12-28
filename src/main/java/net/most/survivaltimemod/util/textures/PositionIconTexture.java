package net.most.survivaltimemod.util.textures;

public class PositionIconTexture {
    final int offsetU;
    final int offsetV;
    final int width;
    final int height;

    public PositionIconTexture(int offsetU, int offsetV, int width, int height) {
        this.offsetU = offsetU;
        this.offsetV = offsetV;
        this.width = width;
        this.height = height;
    }

    public PositionIconTexture(int offsetU, int offsetV, int size) {
        this(offsetU, offsetV, size, size);
    }

    public int getOffsetU() {
        return offsetU;
    }

    public int getOffsetV() {
        return offsetV;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
