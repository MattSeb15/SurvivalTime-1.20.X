package net.most.survivaltimemod.datagen.pattern;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.level.ItemLike;
import net.most.survivaltimemod.SurvivalTimeMod;

import java.util.function.Consumer;

public class MediumWithExtensionBorderPattern extends HourglassPattern implements IHourglassPattern{

    private final ItemLike ingredientA;
    private final ItemLike ingredientB;
    private final ItemLike ingredientC;

    public MediumWithExtensionBorderPattern(ItemLike result, int resultCount, int craftTime, int energyCost, ItemLike ingredientA, ItemLike ingredientB, ItemLike ingredientC) {
        super(result, resultCount, craftTime, energyCost);
        this.ingredientA = ingredientA;
        this.ingredientB = ingredientB;
        this.ingredientC = ingredientC;
    }
    public ItemLike getIngredientA() {
        return ingredientA;
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
                .pattern(" ABA ")
                .pattern(" BCB ")
                .pattern(" ABA ")
                .pattern("     ")
                .define('B', getIngredientB())
                .define('C', getIngredientC())
                .define('A', getIngredientA())
                .unlockedBy(getHasName(getIngredientB()), has(getIngredientB()))
                .save(pWriter, SurvivalTimeMod.MOD_ID + ":" + getItemName(getResult()) + "_medium_with_extension_border_pattern");

    }
}
