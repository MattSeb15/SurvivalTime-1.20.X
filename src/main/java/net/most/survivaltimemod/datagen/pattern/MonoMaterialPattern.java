package net.most.survivaltimemod.datagen.pattern;

import net.minecraft.world.level.ItemLike;

public class MonoMaterialPattern extends HourglassPattern {

    public final ItemLike ingredient;
    public final int ingredientCount;

    public MonoMaterialPattern(ItemLike result, int resultCount, int craftTime, int energyCost, ItemLike ingredient, int ingredientCount) {
        super(result, resultCount, craftTime, energyCost);
        this.ingredient = ingredient;
        this.ingredientCount = ingredientCount;
    }

    public ItemLike getIngredient() {
        return ingredient;
    }

    public int getIngredientCount() {
        return ingredientCount;
    }
}
