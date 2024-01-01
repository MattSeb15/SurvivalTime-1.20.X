package net.most.survivaltimemod.util.records;

import net.minecraft.world.level.ItemLike;

public class HourglassPattern {
    protected ItemLike result;
    protected int resultCount;
    protected int craftTime;
    protected int energyCost;

    public HourglassPattern(ItemLike result, int resultCount, int craftTime, int energyCost) {
        this.result = result;
        this.resultCount = resultCount;
        this.craftTime = craftTime;
        this.energyCost = energyCost;
    }

    public ItemLike getResult() {
        return result;
    }

    public int getResultCount() {
        return resultCount;
    }

    public int getCraftTime() {
        return craftTime;
    }

    public int getEnergyCost() {
        return energyCost;
    }

    public void setResult(ItemLike result) {
        this.result = result;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

    public void setCraftTime(int craftTime) {
        this.craftTime = craftTime;
    }

    public void setEnergyCost(int energyCost) {
        this.energyCost = energyCost;
    }

}
