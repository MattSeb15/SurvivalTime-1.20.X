package net.most.survivaltimemod.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.most.survivaltimemod.data.FormatTimeType;
import net.most.survivaltimemod.effect.ModEffects;
import net.most.survivaltimemod.time.PlayerTimeData;
import net.most.survivaltimemod.util.CoinFormatter;
import net.most.survivaltimemod.util.textures.*;

import java.util.Random;

public class TimeHudOverlay {

    private static final int padding = 5;
    private static final Random random = new Random();

    private static int pX(int x, int j) {
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
                int i = 0;
                int y = 0;
                int x = 0;
                int finalX;
                int finalY;
                int columns = 10;


                if (player.hasEffect(ModEffects.HEAL_TRIGGER.get())) {
                    timeType = TimeType.REGEN;
                    float lastIncrement = playerTimeData.getLastStats()[0];
                    String lastIncrementString = "+" + FormatTimeType.getFormattedStringByType(FormatTimeType.DEPENDS_NAMED, lastIncrement);

                    guiGraphics.pose().pushPose();
                    guiGraphics.pose().scale(1.3f, 1.3f, 1.3f);
                    if (lastIncrement > 1)
                        guiGraphics.drawString(gui.getFont(), lastIncrementString, (int) (x * 1.3f - x + TimeTexture.ICONS_BORDER_WIDTH * 8 + 5), padding, 0x4AFF50);
                    guiGraphics.pose().popPose();

                }
                if (player.hasEffect(ModEffects.GOLDEN_TIME.get())) {
                    timeType = TimeType.GOLDEN;
                }
                if (player.hasEffect(ModEffects.DAMAGE_TRIGGER.get())) {
                    timeType = TimeType.DAMAGE;
                    float lastDecrement = playerTimeData.getLastStats()[1];
                    String lastDecrementString = "-" + FormatTimeType.getFormattedStringByType(FormatTimeType.DEPENDS_NAMED, lastDecrement);
                    guiGraphics.pose().pushPose();
                    guiGraphics.pose().scale(1.3f, 1.3f, 1.3f);
                    if (lastDecrement > 1)
                        guiGraphics.drawString(gui.getFont(), lastDecrementString, (int) (x * 1.3f - x + TimeTexture.ICONS_BORDER_WIDTH * 8 + 5), padding, 0xFF0000);

                    guiGraphics.pose().popPose();
                }
                if (player.hasEffect(ModEffects.TIME_TEAR.get())) {
                    timeType = TimeType.TEAR;
                }


                TimeIcon timeIcon = TimeTexture.getTimeIcon(timeType);
                TimeIcon timeIconRemaining = TimeTexture.getTimeIcon(timeType, remainingMinutes);
                TimeIcon timeIconEmpty = TimeTexture.getTimeIcon(timeType, 0);


                for (int j = 0; j < maxHours; j++) {
                    if (j % columns == 0 && j != 0) i++;

                    finalX = pX(x, j - i * columns);
                    finalY = pY(y, i, gui.getGuiTicks(), playerTimeData);

                    if (j < fullHours) {
                        drawIcon(guiGraphics, timeIcon, finalX, finalY);
                    } else if (j == fullHours) {
                        drawIcon(guiGraphics, timeIconRemaining, finalX, finalY);

                    } else {
                        drawIcon(guiGraphics, timeIconEmpty, finalX, finalY);
                    }
                }

                drawStatsHub(guiGraphics, playerTimeData, gui, screenWidth, screenHeight);

                //                drawDamageMultiplier(guiGraphics, x, y + screenHeight/2 - 14, playerTimeData, gui);
                //                drawTimeMultiplier(guiGraphics,x,y + screenHeight/2 -14*2 -2, playerTimeData, gui);


                RenderSystem.disableBlend();
            }


        }


    });

    private static void drawStatsHub(GuiGraphics guiGraphics, PlayerTimeData playerTimeData, ForgeGui gui, int screenWidth, int screenHeight) {
        float timeMultiplier = playerTimeData.getTimeMultiplier();
        float damageMultiplier = playerTimeData.getDamageMultiplier();
        double coins = playerTimeData.getCoins();
        float coinsMultiplier = playerTimeData.getCoinsMultiplier();
        int width = 60;
        int height = 52;

        ExtraPositionIconTexture extraPositionIconTexture = new ExtraPositionIconTexture(0, 62, width, height);
        int px = 5;
        int py = screenHeight / 2 - height / 2;
        IconTexture.drawIcon(guiGraphics, extraPositionIconTexture, px, py);
        int pX = px + 5 + 11;
        int pY = py + 3;
        int pyMultipliers = py+12;
        int increment = 19;

        guiGraphics.drawString(gui.getFont(), CoinFormatter.formatCoins(coins), pX, pY, 0xFFFFFF);
        guiGraphics.drawString(gui.getFont(), "1D = "+ CoinFormatter.formatCoins(damageMultiplier) + "D", pX, pY + increment, 0xFFFFFF);
        guiGraphics.drawString(gui.getFont(), "1s = "+ FormatTimeType.getFormattedStringByType(FormatTimeType.DEPENDS_NAMED, timeMultiplier), pX, pY + increment * 2, 0xFFFFFF);

        guiGraphics.drawString(gui.getFont(), "x" + coinsMultiplier, pX,  pyMultipliers, 0xF5AE2B);
        guiGraphics.drawString(gui.getFont(), "x" + damageMultiplier, pX, pyMultipliers + increment, 0xcbcbcb);
        guiGraphics.drawString(gui.getFont(), "x" + timeMultiplier, pX, pyMultipliers + increment * 2, 0xB78350);

    }

    private static void drawTimeMultiplier(GuiGraphics guiGraphics, int x, int i, PlayerTimeData playerTimeData, ForgeGui gui) {
        float timeMultiplier = playerTimeData.getTimeMultiplier();
        ExtraPositionIconTexture extraPositionIconTexture = new ExtraPositionIconTexture(52, 62, 52, 14);
        IconTexture.drawIcon(guiGraphics, extraPositionIconTexture, x + 5, i);
        String pText = "x" + timeMultiplier;
        int pX = x + 5 + 15;
        int pY = i + 3;
        guiGraphics.drawString(gui.getFont(), pText, pX, pY, 0xFFFFFF);
    }

    private static void drawDamageMultiplier(GuiGraphics guiGraphics, int x, int y, PlayerTimeData playerTimeData, Gui gui) {
        float damageMultiplier = playerTimeData.getDamageMultiplier();
        ExtraPositionIconTexture extraPositionIconTexture = new ExtraPositionIconTexture(0, 62, 52, 14);

        IconTexture.drawIcon(guiGraphics, extraPositionIconTexture, x + 5, y);

        String pText = "x" + damageMultiplier;
        int pX = x + 5 + 15;
        int pY = y + 3;
        guiGraphics.drawString(gui.getFont(), pText, pX, pY, 0xFFFFFF);


    }

    private static void fill(GuiGraphics guiGraphics, int x, int y, int width, int height, int color) {
        guiGraphics.fill(x, y, x + width, y + height, color);

    }

    private static void drawIcon(GuiGraphics guiGraphics, TimeIcon timeIcon, int pX, int pY) {
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

        guiGraphics.blit(TIME_HUD_ICONS, x, y, icon.getOffsetU(), icon.getOffsetV(), icon.getWidth(), icon.getHeight());
    }

    private static void drawFillIcon(GuiGraphics guiGraphics, ExtraPositionIconTexture icon, int x, int y) {
        int progress = icon.getHeight();
        int iconHeight = TimeTexture.ICONS_FILL_HEIGHT;
        float fillPercentage = (float) progress / iconHeight;

        int adjustedY = y + (int) (iconHeight * (1 - fillPercentage)); // Calcula la posición Y ajustada
        int adjustedHeight = (int) (iconHeight * fillPercentage); // Calcula la nueva altura de la textura

        guiGraphics.blit(TIME_HUD_ICONS, x, adjustedY, icon.getOffsetU(), icon.getOffsetV() + iconHeight - adjustedHeight, icon.getWidth(), adjustedHeight);

    }


}
