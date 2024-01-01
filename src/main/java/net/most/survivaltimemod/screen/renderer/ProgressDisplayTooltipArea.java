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
public class ProgressDisplayTooltipArea {
    private int progress;
    private int maxProgress;

    public ProgressDisplayTooltipArea(int progress, int maxProgress) {
        this.progress = progress;
        this.maxProgress = maxProgress;
    }

    public int getProgress() {
        return progress;
    }

    public int getMaxProgress() {
        return maxProgress;
    }

    public List<Component> getTooltips() {
        int progressInTicks = this.progress;
        int maxProgressInTicks = this.maxProgress;
        int remainingProgressInTicks = maxProgressInTicks - progressInTicks;
        ///20 ticks = 1 second

        int remainingProgressInSeconds = remainingProgressInTicks / 20;
        FormatTimeType formatTimeType = FormatTimeType.DEPENDS_NAMED;
        String formattedRemainingProgress = FormatTimeType.getFormattedStringByType(formatTimeType, remainingProgressInSeconds);

        return List.of(Component.translatable("gui.survivaltimemod.hourglass_hub_station.remaining_time", formattedRemainingProgress));
    }


    public void setValues(int progress, int maxProgress) {
        this.progress = progress;
        this.maxProgress = maxProgress;
    }
}