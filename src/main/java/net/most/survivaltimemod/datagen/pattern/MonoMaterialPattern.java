package net.most.survivaltimemod.datagen.pattern;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.level.ItemLike;

import java.util.function.Consumer;

public class MonoMaterialPattern extends HourglassPattern implements IHourglassPattern {

    public final ItemLike ingredient;
    public final int ingredientCount;

    public MonoMaterialPattern(ItemLike result, int resultCount, int craftTime, int energyCost, ItemLike ingredient, int ingredientCount) {
        super(result, resultCount, craftTime, energyCost);
        this.ingredient = ingredient;
        this.ingredientCount = ingredientCount;
    }

    public MonoMaterialPattern(ItemLike result, int resultCount, int craftTime, int energyCost, ItemLike ingredient) {
        super(result, resultCount, craftTime, energyCost);
        this.ingredient = ingredient;
        this.ingredientCount = 1;
    }

    public ItemLike getIngredient() {
        return ingredient;
    }

    public int getIngredientCount() {
        return ingredientCount;
    }

    @Override
    public void create(Consumer<FinishedRecipe> pWriter) {
        if(getIngredientCount() >= 1 && getIngredientCount() <= 25){

        getShapelessBuilder()
                .requires(getIngredient(), getIngredientCount())
                .unlockedBy(getHasName(getIngredient()), has(getIngredient()))
                .save(pWriter,getItemName(getResult()) + "_from_" + getItemName(getIngredient()));
        }

    }
}
