package net.most.survivaltimemod.datagen.pattern;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.level.ItemLike;

import java.util.function.Consumer;

public class DuoIngredientPattern extends MonoIngredientPattern{
    private final ItemLike ingredientB;
    public DuoIngredientPattern(ItemLike result, int resultCount, int craftTime, int energyCost, ItemLike ingredientA, ItemLike ingredientB) {
        super(result, resultCount, craftTime, energyCost, ingredientA);
        this.ingredientB = ingredientB;
    }
    public ItemLike getIngredientB() {
        return ingredientB;
    }
}
