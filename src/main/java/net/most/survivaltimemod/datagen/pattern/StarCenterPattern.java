package net.most.survivaltimemod.datagen.pattern;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.level.ItemLike;
import net.most.survivaltimemod.SurvivalTimeMod;

import java.util.function.Consumer;

public class StarCenterPattern extends DuoIngredientPattern implements IHourglassPattern{


    public StarCenterPattern(ItemLike result, int resultCount, int craftTime, int energyCost, ItemLike ingredientBorder, ItemLike ingredientCenter) {
        super(result, resultCount, craftTime, energyCost, ingredientBorder, ingredientCenter);

    }

    @Override
    public void create(Consumer<FinishedRecipe> pWriter) {
        getShapedBuilder()
                .pattern("     ")
                .pattern("  B  ")
                .pattern(" BCB ")
                .pattern("  B  ")
                .pattern("     ")
                .define('B', getIngredientA())
                .define('C', getIngredientB())
                .unlockedBy(getHasName(getResult()), has(getResult()))
                .save(pWriter, SurvivalTimeMod.MOD_ID + ":" + getItemName(getResult()) + "_center_pattern");
    }
}
