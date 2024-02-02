package net.most.survivaltimemod.datagen.pattern;

import net.minecraft.world.level.ItemLike;

public class MonoIngredientPattern extends HourglassPattern{
    private final ItemLike ingredientA;
    public MonoIngredientPattern(ItemLike result, int resultCount, int craftTime, int energyCost, ItemLike ingredientA) {
        super(result, resultCount, craftTime, energyCost);
        this.ingredientA = ingredientA;
    }

    public ItemLike getIngredientA() {
        return ingredientA;
    }
}
