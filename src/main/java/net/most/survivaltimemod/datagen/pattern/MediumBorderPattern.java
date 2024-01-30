package net.most.survivaltimemod.datagen.pattern;

import net.minecraft.world.level.ItemLike;

public class MediumBorderPattern extends HourglassPattern {
    private final ItemLike ingredientB;
    private final ItemLike ingredientC;

    public MediumBorderPattern(ItemLike result, int resultCount, int craftTime, int energyCost, ItemLike ingredientB, ItemLike ingredientC) {
        super(result, resultCount, craftTime, energyCost);
        this.ingredientB = ingredientB;
        this.ingredientC = ingredientC;
    }

    public ItemLike getIngredientB() {
        return ingredientB;
    }

    public ItemLike getIngredientC() {
        return ingredientC;
    }
}
