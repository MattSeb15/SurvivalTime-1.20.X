package net.most.survivaltimemod.datagen.pattern;

import net.minecraft.world.level.ItemLike;

public class TriIngredientPattern extends DuoIngredientPattern{
    private final ItemLike ingredientC;
    public TriIngredientPattern(ItemLike result, int resultCount, int craftTime, int energyCost, ItemLike ingredientA, ItemLike ingredientB, ItemLike ingredientC) {
        super(result, resultCount, craftTime, energyCost, ingredientA, ingredientB);
        this.ingredientC = ingredientC;
    }
    public ItemLike getIngredientC() {
        return ingredientC;
    }
}
