package net.most.survivaltimemod.datagen.pattern;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.level.ItemLike;
import net.most.survivaltimemod.SurvivalTimeMod;

import java.util.function.Consumer;

public class NonFourBorderPattern extends DuoIngredientPattern implements IHourglassPattern{
    public NonFourBorderPattern(ItemLike result, int resultCount, int craftTime, int energyCost, ItemLike ingredientA, ItemLike ingredientB) {
        super(result, resultCount, craftTime, energyCost, ingredientA, ingredientB);
    }

    @Override
    public void create(Consumer<FinishedRecipe> pWriter) {
        getShapedBuilder()
                .pattern(" BBB ")
                .pattern("BBBBB")
                .pattern("BBCBB")
                .pattern("BBBBB")
                .pattern(" BBB ")
                .define('B', getIngredientA())
                .define('C', getIngredientB())
                .unlockedBy(getHasName(getIngredientB()), has(getIngredientB()))
                .save(pWriter, SurvivalTimeMod.MOD_ID + ":" + getItemName(getResult()) + "_non_four_border_pattern");

    }
}
