package net.most.survivaltimemod.util;

public class MouseUtil {
    public static boolean isMouseOver(double mouseX, double mouseY, int x, int y) {
        return isMouseOver(mouseX, mouseY, x, y, 16);
    }

    public static boolean isMouseOver(double mouseX, double mouseY, int x, int y, int size) {
        return isMouseOver(mouseX, mouseY, x, y, size, size);
    }

    public static boolean isMouseOver(double mouseX, double mouseY, int x, int y, int sizeX, int sizeY) {
        return (mouseX >= x && mouseX <= x + sizeX) && (mouseY >= y && mouseY <= y + sizeY);
    }

    public static boolean isMouseAboveArea(int pMouseX, int pMouseY, int pX, int pY, int offsetX, int offsetY, int pWidth, int pHeight) {
        return isMouseOver(pMouseX, pMouseY, pX + offsetX, pY + offsetY, pWidth, pHeight);
    }
}
