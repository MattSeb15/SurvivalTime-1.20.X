package net.most.survivaltimemod.util.records;

import net.minecraft.world.level.ItemLike;

public class FullBorderPattern extends HourglassPattern {
    private final ItemLike pIngredientA;
    private final ItemLike pIngredientB;
    private final ItemLike pIngredientC;

    public FullBorderPattern(ItemLike result, int resultCount, int craftTime, int energyCost, ItemLike pIngredientA, ItemLike pIngredientB,
                             ItemLike pIngredientC) {
        super(result, resultCount, craftTime, energyCost);
        this.pIngredientA = pIngredientA;
        this.pIngredientB = pIngredientB;
        this.pIngredientC = pIngredientC;
    }

    public ItemLike getIngredientA() {
        return pIngredientA;
    }

    public ItemLike getIngredientB() {
        return pIngredientB;
    }

    public ItemLike getIngredientC() {
        return pIngredientC;
    }
}
