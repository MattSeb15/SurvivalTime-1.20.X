package net.most.survivaltimemod.datagen.pattern;

import net.minecraft.world.level.ItemLike;

public class PentIngredientPattern extends QuadIngredientPattern {

    private final ItemLike ingredientE;
    public PentIngredientPattern(ItemLike result, int resultCount, int craftTime, int energyCost, ItemLike ingredientA, ItemLike ingredientB, ItemLike ingredientC, ItemLike ingredientD, ItemLike ingredientE) {
        super(result, resultCount, craftTime, energyCost, ingredientA, ingredientB, ingredientC, ingredientD);
        this.ingredientE = ingredientE;
    }
    public ItemLike getIngredientE() {
        return ingredientE;
    }
}
