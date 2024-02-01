package net.most.survivaltimemod.datagen.pattern;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.level.ItemLike;
import net.most.survivaltimemod.SurvivalTimeMod;

import java.util.function.Consumer;

public class FullBorderPattern extends HourglassPattern implements IHourglassPattern{
    private final ItemLike pIngredientA;
    private final ItemLike pIngredientB;
    private final ItemLike pIngredientC;

    public FullBorderPattern(ItemLike result, int resultCount, int craftTime, int energyCost, ItemLike pIngredientA, ItemLike pIngredientB,
                             ItemLike pIngredientC) {
        super(result, resultCount, craftTime, energyCost);
        this.pIngredientA = pIngredientA;
        this.pIngredientB = pIngredientB;
        this.pIngredientC = pIngredientC;
    }

    public ItemLike getIngredientA() {
        return pIngredientA;
    }

    public ItemLike getIngredientB() {
        return pIngredientB;
    }

    public ItemLike getIngredientC() {
        return pIngredientC;
    }

    @Override
    public void create(Consumer<FinishedRecipe> pWriter) {
        ItemLike ingredientA = getIngredientA();
         getShapedBuilder()
                 .pattern("AAAAA")
                .pattern("ABBBA")
                .pattern("ABCBA")
                .pattern("ABBBA")
                .pattern("AAAAA")
                .define('A', ingredientA)
                .define('B', getIngredientB())
                .define('C', getIngredientC())
                .unlockedBy(getHasName(ingredientA), has(ingredientA))
                .save(pWriter, SurvivalTimeMod.MOD_ID + ":" + getItemName(getResult()) + "_full_border_pattern");
    }
}
