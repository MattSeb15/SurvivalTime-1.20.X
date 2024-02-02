package net.most.survivaltimemod.datagen.pattern;

import net.minecraft.world.level.ItemLike;

public class QuadIngredientPattern extends TriIngredientPattern {

    private final ItemLike ingredientD;

    public QuadIngredientPattern(ItemLike result, int resultCount, int craftTime, int energyCost, ItemLike ingredientA, ItemLike ingredientB, ItemLike ingredientC,
                                 ItemLike ingredientD) {
        super(result, resultCount, craftTime, energyCost, ingredientA, ingredientB, ingredientC);
        this.ingredientD = ingredientD;
    }

    public ItemLike getIngredientD() {
        return ingredientD;
    }
}
