package net.most.survivaltimemod.util.textures;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.most.survivaltimemod.SurvivalTimeMod;

import java.util.List;

public class IconTexture {
    public static final ResourceLocation TIME_HUD_ICONS = new ResourceLocation(SurvivalTimeMod.MOD_ID,
            "textures/gui/mod_icons.png");

    public static ExtraPositionIconTexture createTextureWithIndex(int offsetX, int offsetY, int i, int width, int height) {
        return new ExtraPositionIconTexture(offsetX + (width * i), offsetY, width, height);
    }

    public static void drawIcon(GuiGraphics guiGraphics, ExtraPositionIconTexture extraPositionIconTexture, int px, int py) {
        guiGraphics.blit(TIME_HUD_ICONS, px, py, extraPositionIconTexture.getOffsetU(), extraPositionIconTexture.getOffsetV(),
                extraPositionIconTexture.getWidth(), extraPositionIconTexture.getHeight());
    }

    public static void drawIcon(GuiGraphics guiGraphics, PositionIconTexture extraPositionIconTexture) {
        guiGraphics.blit(TIME_HUD_ICONS, extraPositionIconTexture.y(), extraPositionIconTexture.x(), extraPositionIconTexture.offsetU(),
                extraPositionIconTexture.offsetV(),
                extraPositionIconTexture.width(), extraPositionIconTexture.height());
    }


    public static void drawIcon(GuiGraphics guiGraphics, List<PositionIconTexture> positionIconTextureList) {

        for (PositionIconTexture positionIconTexture : positionIconTextureList) {
            guiGraphics.blit(TIME_HUD_ICONS, positionIconTexture.y(), positionIconTexture.x(), positionIconTexture.offsetU(),
                    positionIconTexture.offsetV(),
                    positionIconTexture.width(), positionIconTexture.height());
        }

    }

    public static void drawIcon(GuiGraphics guiGraphics, List<PositionIconTexture> positionIconTextureList, int px, int py) {

        for (PositionIconTexture positionIconTexture : positionIconTextureList) {
            guiGraphics.blit(TIME_HUD_ICONS, px + positionIconTexture.x(), py + positionIconTexture.y(), positionIconTexture.offsetU(),
                    positionIconTexture.offsetV(),
                    positionIconTexture.width(), positionIconTexture.height());
        }

    }
}
