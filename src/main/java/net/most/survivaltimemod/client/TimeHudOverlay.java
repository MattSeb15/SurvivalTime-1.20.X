package net.most.survivaltimemod.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.most.survivaltimemod.effect.ModEffects;
import net.most.survivaltimemod.time.PlayerTimeData;
import net.most.survivaltimemod.util.textures.ExtraPositionIconTexture;
import net.most.survivaltimemod.util.textures.TimeIcon;
import net.most.survivaltimemod.util.textures.TimeTexture;
import net.most.survivaltimemod.util.textures.TimeType;

import java.util.Random;

public class TimeHudOverlay {

    private static final int padding = 5;

    private static int pX(int x, int j, int i) {
        return x + padding + (j * (TimeTexture.ICONS_BORDER_WIDTH + 1));
    }


    private static int pY(int y, int i, int tickCount, PlayerTimeData playerTimeData) {
        boolean isPlayerTimeStopped = playerTimeData.isTimeStopped();

        Random random = new Random();
        boolean randomBool = random.nextBoolean();
        int py = y + padding + (i * (TimeTexture.ICONS_BORDER_HEIGHT + 1));
        if (tickCount % (20) == 0 && !isPlayerTimeStopped && randomBool) {
            return py + (random.nextInt(4) - 1);
        }


        return py;
    }


    private static final ResourceLocation TIME_HUD_ICONS = TimeTexture.TIME_HUD_ICONS;


    public static IGuiOverlay HUD_TIME = ((gui, guiGraphics, partialTick, screenWidth, screenHeight) -> {


        if (!gui.getMinecraft().options.hideGui && gui.shouldDrawSurvivalElements()) {

            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.enableBlend();
            LocalPlayer player = gui.getMinecraft().player;

            if (player != null) {
                PlayerTimeData playerTimeData = ClientPlayerTimeData.get();
                if (playerTimeData == null) {
                    return;
                }
                float totalSeconds = playerTimeData.getTime();///-1.0f
                float maxSeconds = playerTimeData.getMaxTime();
                int maxHours = Math.round(maxSeconds / 3600);

                int fullHours = (int) (totalSeconds / 3600);
                int remainingSeconds = (int) (totalSeconds % 3600);
                int remainingMinutes = remainingSeconds / 60;

                boolean isPlayerTimeStopped = playerTimeData.isTimeStopped();

                TimeType timeType = isPlayerTimeStopped ? TimeType.PAUSED : TimeType.NORMAL;

                if (player.hasEffect(ModEffects.HEAL_TRIGGER.get())) {
                    timeType = TimeType.REGEN;
                }
                if (player.hasEffect(ModEffects.GOLDEN_TIME.get())) {
                    timeType = TimeType.GOLDEN;
                }
                if (player.hasEffect(ModEffects.DAMAGE_TRIGGER.get())) {
                    timeType = TimeType.DAMAGE;
                }
                if (player.hasEffect(ModEffects.TIME_TEAR.get())) {
                    timeType = TimeType.TEAR;
                }


                TimeIcon timeIcon = TimeTexture.getTimeIcon(timeType);
                TimeIcon timeIconRemaining = TimeTexture.getTimeIcon(timeType, remainingMinutes);
                TimeIcon timeIconEmpty = TimeTexture.getTimeIcon(timeType, 0);

//                for (int i = 0; i < rows; i++)
                int i = 0;
                int y = 0;
                int x = 0;
                int finalX;
                int finalY;
                int columns = 10;
                for (int j = 0; j < maxHours; j++) {
                    if (j % columns == 0 && j != 0) i++;

                    finalX = pX(x, j - i * columns, i);
                    finalY = pY(y, i, gui.getGuiTicks(), playerTimeData);

                    if (j < fullHours) {
                        drawIcon(guiGraphics, timeIcon, finalX, finalY);
                    } else if (j == fullHours) {
                        drawIcon(guiGraphics, timeIconRemaining, finalX, finalY);

                    } else {
                        drawIcon(guiGraphics, timeIconEmpty, finalX, finalY);
                    }
                }

                RenderSystem.disableBlend();
            }


        }


    });

    private static void drawIcon(GuiGraphics guiGraphics,
                                 TimeIcon timeIcon,
                                 int pX,
                                 int pY
    ) {
        ExtraPositionIconTexture border = timeIcon.border();
        ExtraPositionIconTexture fill = timeIcon.fill();
        ExtraPositionIconTexture background = timeIcon.background();
        int bgFillX = 3;
        int bgFillY = 2;


        drawIcon(guiGraphics, border, pX, pY);
        drawIcon(guiGraphics, background, pX + bgFillX, pY + bgFillY);
        drawFillIcon(guiGraphics, fill, pX + bgFillX, pY + bgFillY);

    }

    private static void drawIcon(GuiGraphics guiGraphics, ExtraPositionIconTexture icon, int x, int y) {

        guiGraphics.blit(TIME_HUD_ICONS, x, y,
                icon.getOffsetU(),
                icon.getOffsetV(),
                icon.getWidth(),
                icon.getHeight());
    }

    private static void drawFillIcon(GuiGraphics guiGraphics, ExtraPositionIconTexture icon, int x, int y) {
        int progress = icon.getHeight();
        int iconHeight = TimeTexture.ICONS_FILL_HEIGHT;
        float fillPercentage = (float) progress / iconHeight;

        int adjustedY = y + (int) (iconHeight * (1 - fillPercentage)); // Calcula la posición Y ajustada
        int adjustedHeight = (int) (iconHeight * fillPercentage); // Calcula la nueva altura de la textura

        guiGraphics.blit(TIME_HUD_ICONS, x, adjustedY,
                icon.getOffsetU(),
                icon.getOffsetV() + iconHeight - adjustedHeight,
                icon.getWidth(),
                adjustedHeight);

    }


}
