package net.most.survivaltimemod.datagen.pattern;

import net.minecraft.world.level.ItemLike;

public class ArmorPattern extends HourglassPattern{
    private final ItemLike moreWeightIngredient;
    private final ItemLike lessWeightIngredient;
    private final ItemLike lastTierArmor;
    public ArmorPattern(ItemLike result, int resultCount, int craftTime, int energyCost, ItemLike moreWeightIngredient, ItemLike lessWeightIngredient, ItemLike lastTierArmor) {
        super(result, resultCount, craftTime, energyCost);
        this.moreWeightIngredient = moreWeightIngredient;
        this.lessWeightIngredient = lessWeightIngredient;
        this.lastTierArmor = lastTierArmor;
    }

    public ItemLike getMoreWeightIngredient() {
        return moreWeightIngredient;
    }

    public ItemLike getLessWeightIngredient() {
        return lessWeightIngredient;
    }

    public ItemLike getLastTierArmor() {
        return lastTierArmor;
    }
}
