package net.most.survivaltimemod.datagen.pattern;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.level.ItemLike;
import net.most.survivaltimemod.SurvivalTimeMod;

import java.util.function.Consumer;

public class MediumBorderPattern extends HourglassPattern implements IHourglassPattern{
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

    @Override
    public void create(Consumer<FinishedRecipe> pWriter) {
        getShapedBuilder()
                .pattern("     ")
                .pattern(" BBB ")
                .pattern(" BCB ")
                .pattern(" BBB ")
                .pattern("     ")
                .define('B', getIngredientB())
                .define('C', getIngredientC())
                .unlockedBy(getHasName(getIngredientB()), has(getIngredientB()))
                .save(pWriter, SurvivalTimeMod.MOD_ID + ":" + getItemName(getResult()) + "_medium_border_pattern");

    }
}
