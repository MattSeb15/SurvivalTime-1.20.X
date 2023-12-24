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
        return List.of(Component.literal(this.progress + "/" + this.maxProgress));
    }


    public void setValues(int progress, int maxProgress) {
        this.progress = progress;
        this.maxProgress = maxProgress;
    }
}