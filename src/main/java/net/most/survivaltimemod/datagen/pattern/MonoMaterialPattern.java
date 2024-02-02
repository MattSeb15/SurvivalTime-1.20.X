package net.most.survivaltimemod.datagen.pattern;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.level.ItemLike;
import net.most.survivaltimemod.SurvivalTimeMod;

import java.util.function.Consumer;

public class MonoMaterialPattern extends MonoIngredientPattern implements IHourglassPattern {

    public final int ingredientCount;

    public MonoMaterialPattern(ItemLike result, int resultCount, int craftTime, int energyCost, ItemLike ingredient, int ingredientCount) {
        super(result, resultCount, craftTime, energyCost, ingredient);
        this.ingredientCount = ingredientCount;
    }

    public MonoMaterialPattern(ItemLike result, int resultCount, int craftTime, int energyCost, ItemLike ingredient) {
        super(result, resultCount, craftTime, energyCost, ingredient);
        this.ingredientCount = 1;
    }
    public int getIngredientCount() {
        return ingredientCount;
    }

    @Override
    public void create(Consumer<FinishedRecipe> pWriter) {
        if(getIngredientCount() >= 1 && getIngredientCount() <= 25){

        getShapelessBuilder()
                .requires(getIngredientA(), getIngredientCount())
                .unlockedBy(getHasName(getIngredientA()), has(getIngredientA()))
                .save(pWriter,SurvivalTimeMod.MOD_ID + ":" + getItemName(getResult()) + "_from_" + getItemName(getIngredientA()));

        }

    }
}
