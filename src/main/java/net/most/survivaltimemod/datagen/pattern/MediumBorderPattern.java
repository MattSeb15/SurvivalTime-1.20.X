package net.most.survivaltimemod.datagen.pattern;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.level.ItemLike;
import net.most.survivaltimemod.SurvivalTimeMod;

import java.util.function.Consumer;

public class MediumBorderPattern extends DuoIngredientPattern implements IHourglassPattern{


    public MediumBorderPattern(ItemLike result, int resultCount, int craftTime, int energyCost, ItemLike ingredientA, ItemLike ingredientB) {
        super(result, resultCount, craftTime, energyCost, ingredientA, ingredientB);
    }
    @Override
    public void create(Consumer<FinishedRecipe> pWriter) {
        getShapedBuilder()
                .pattern("     ")
                .pattern(" BBB ")
                .pattern(" BCB ")
                .pattern(" BBB ")
                .pattern("     ")
                .define('B', getIngredientA())
                .define('C', getIngredientB())
                .unlockedBy(getHasName(getIngredientB()), has(getIngredientB()))
                .save(pWriter, SurvivalTimeMod.MOD_ID + ":" + getItemName(getResult()) + "_medium_border_pattern");

    }
}
