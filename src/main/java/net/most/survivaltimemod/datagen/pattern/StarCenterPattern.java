package net.most.survivaltimemod.datagen.pattern;

import net.minecraft.world.level.ItemLike;

public class StarCenterPattern extends HourglassPattern {
    private final ItemLike ingredientBorder;
    private final ItemLike ingredientCenter;

    public StarCenterPattern(ItemLike result, int resultCount, int craftTime, int energyCost, ItemLike ingredientBorder, ItemLike ingredientCenter) {
        super(result, resultCount, craftTime, energyCost);
        this.ingredientBorder = ingredientBorder;
        this.ingredientCenter = ingredientCenter;
    }


    public ItemLike getIngredientBorder() {
        return ingredientBorder;
    }

    public ItemLike getIngredientCenter() {
        return ingredientCenter;
    }

}
