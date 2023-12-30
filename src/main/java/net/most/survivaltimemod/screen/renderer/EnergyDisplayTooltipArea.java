package net.most.survivaltimemod.screen.renderer;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraftforge.energy.IEnergyStorage;
import net.most.survivaltimemod.data.FormatTimeType;

import java.util.List;

/*
 *  BluSunrize
 *  Copyright (c) 2021
 *
 *  This code is licensed under "Blu's License of Common Sense"
 *  https://github.com/BluSunrize/ImmersiveEngineering/blob/1.19.2/LICENSE
 *
 *  Slightly Modified Version by: Kaupenjoe
 */
public class EnergyDisplayTooltipArea {
    private final int xPos;
    private final int yPos;
    private final int width;
    private final int height;
    private final IEnergyStorage energy;

    public EnergyDisplayTooltipArea(int xMin, int yMin, IEnergyStorage energy) {
        this(xMin, yMin, energy, 8, 64);
    }

    public EnergyDisplayTooltipArea(int xMin, int yMin, IEnergyStorage energy, int width, int height) {
        xPos = xMin;
        yPos = yMin;
        this.width = width;
        this.height = height;
        this.energy = energy;
    }

    public List<Component> getTooltips() {
        return List.of(Component.literal(
                FormatTimeType.getFormattedStringByType(FormatTimeType.DEPENDS_NAMED, energy.getEnergyStored()) +
                        " / " +
                        FormatTimeType.getFormattedStringByType(FormatTimeType.DEPENDS_NAMED, energy.getMaxEnergyStored())
                        + " TE"));
    }

    public static List<Component> getTooltips(int energyCost) {
        return List.of(Component.literal(
                FormatTimeType.getFormattedStringByType(FormatTimeType.DEPENDS_NAMED, energyCost) + " TE"));
    }

    public void render(GuiGraphics guiGraphics) {
        int stored = (int) (height * (energy.getEnergyStored() / (float) energy.getMaxEnergyStored()));
        guiGraphics.fillGradient(xPos, yPos + (height - stored), xPos + width,
                yPos + height, 0xfff2ef8e, 0xffcb8e24);
    }

    public static void render(GuiGraphics guiGraphics, int energyCost, int maxEnergy, int xPos, int yPos, int width, int height) {
        int stored = (int) (height * (energyCost / (float) maxEnergy));
        guiGraphics.fillGradient(xPos, yPos + (height - stored), xPos + width,
                yPos + height, 0xfff2ef8e, 0xffcb8e24);
    }

    public void render(GuiGraphics guiGraphics, int colorFrom, int colorTo) {
        int stored = (int) (height * (energy.getEnergyStored() / (float) energy.getMaxEnergyStored()));
        guiGraphics.fillGradient(xPos, yPos + (height - stored), xPos + width,
                yPos + height, colorFrom, colorTo);
    }
}