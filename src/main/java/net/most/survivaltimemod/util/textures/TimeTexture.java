package net.most.survivaltimemod.util.textures;

import net.minecraft.resources.ResourceLocation;

import java.util.List;

public class TimeTexture {

    public static final ResourceLocation TIME_HUD_ICONS = IconTexture.TIME_HUD_ICONS;

    public static final int ICONS_BORDER_HEIGHT = 14;
    public static final int ICONS_BORDER_WIDTH = 12;
    public static final int ICONS_FILL_HEIGHT = 10;
    public static final int ICONS_FILL_WIDTH = 6;
    public static final int ICONS_BG_HEIGHT = 10;
    public static final int ICONS_BG_WIDTH = 6;
    public static final int ICONS_BORDER_OFFSET_X = 0;
    public static final int ICONS_BORDER_OFFSET_Y = 0;
    public static final int ICONS_FILL_OFFSET_X = 0;
    public static final int ICONS_FILL_OFFSET_Y = 15;
    public static final int ICONS_BG_OFFSET_X = 0;
    public static final int ICONS_BG_OFFSET_Y = 26;

    public static List<TimeIcon> getTimeIconList = List.of(
            createTimeIcon(TimeType.NORMAL.getIndex()),
            createTimeIcon(TimeType.PAUSED.getIndex()),
            createTimeIcon(TimeType.DAMAGE.getIndex()),
            createTimeIcon(TimeType.REGEN.getIndex()),
            createTimeIcon(TimeType.TAIR.getIndex())
    );

    public static List<TimeIcon> getTimeIconList(int fill_height) {
        return List.of(
                createTimeIcon(TimeType.NORMAL.getIndex(), fill_height),
                createTimeIcon(TimeType.PAUSED.getIndex(), fill_height),
                createTimeIcon(TimeType.DAMAGE.getIndex(), fill_height),
                createTimeIcon(TimeType.REGEN.getIndex(), fill_height),
                createTimeIcon(TimeType.TAIR.getIndex(), fill_height)
        );
    }

    public static TimeIcon getTimeIcon(TimeType timeType) {
        return getTimeIconList.get(timeType.getIndex());
    }

    public static TimeIcon getTimeIcon(TimeType timeType, int fill_height) {
        return getTimeIconList(getHeight(fill_height)).get(timeType.getIndex());
    }

    public static float getHeightPercent(int progress) {
        return (float) Math.ceil((progress / 100.0) * ICONS_FILL_HEIGHT);
    }

    public static int getHeight(int progress) {
        int maxProgress = 60;

        return progress != 0 ? progress * ICONS_FILL_HEIGHT / maxProgress : 0;
    }

    public static TimeIcon getNormalTimeIcon(int fill_height) {
        return getTimeIcon(TimeType.NORMAL, fill_height);
    }

    public static TimeIcon getPausedTimeIcon(int fill_height) {
        return getTimeIcon(TimeType.PAUSED, fill_height);
    }

    public static TimeIcon getDamageTimeIcon(int fill_height) {
        return getTimeIcon(TimeType.DAMAGE, fill_height);
    }

    public static TimeIcon getRegenTimeIcon(int fill_height) {
        return getTimeIcon(TimeType.REGEN, fill_height);
    }

    public static TimeIcon getTairTimeIcon(int fill_height) {
        return getTimeIcon(TimeType.TAIR, fill_height);
    }


    public static TimeIcon createTimeIcon(int i) {
        return new TimeIcon(createBorderTexture(i),
                createFillTexture(i),
                createBackgroundTexture(i));
    }

    public static TimeIcon createTimeIcon(int i, int fill_height) {
        return new TimeIcon(createBorderTexture(i),
                createFillTexture(i, fill_height),
                createBackgroundTexture(i));
    }

    private static ExtraPositionIconTexture createTexture(int offsetX, int offsetY, int i, int width, int height) {
        return IconTexture.createTextureWithIndex(offsetX, offsetY, i, width, height);
    }

    public static ExtraPositionIconTexture createBorderTexture(int i) {
        return createTexture(ICONS_BORDER_OFFSET_X, ICONS_BORDER_OFFSET_Y, i, ICONS_BORDER_WIDTH, ICONS_BORDER_HEIGHT);
    }

    public static ExtraPositionIconTexture createFillTexture(int i) {
        return createTexture(ICONS_FILL_OFFSET_X, ICONS_FILL_OFFSET_Y, i, ICONS_FILL_WIDTH, ICONS_FILL_HEIGHT);
    }

    public static ExtraPositionIconTexture createFillTexture(int i, int fill_height) {
        return createTexture(ICONS_FILL_OFFSET_X, ICONS_FILL_OFFSET_Y, i, ICONS_FILL_WIDTH, fill_height);
    }

    public static ExtraPositionIconTexture createBackgroundTexture(int i) {
        return createTexture(ICONS_BG_OFFSET_X, ICONS_BG_OFFSET_Y, i, ICONS_BG_WIDTH, ICONS_BG_HEIGHT);
    }

}
