package net.most.survivaltimemod.datagen.pattern;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.level.ItemLike;
import net.most.survivaltimemod.SurvivalTimeMod;

import java.util.function.Consumer;

public class BlockPattern extends MonoIngredientPattern implements IHourglassPattern{
    public BlockPattern(ItemLike result, int resultCount, int craftTime, int energyCost, ItemLike itemIngredient) {
        super(result, resultCount, craftTime, energyCost, itemIngredient);

    }
    public BlockPattern(ItemLike result, int craftTime, int energyCost, ItemLike itemIngredient) {
        super(result, 1, craftTime, energyCost, itemIngredient);
    }
    @Override
    public void create(Consumer<FinishedRecipe> pWriter) {
        getShapedBuilder()
                .pattern("     ")
                .pattern(" ### ")
                .pattern(" ### ")
                .pattern(" ### ")
                .pattern("     ")
                .define('#', getIngredientA())
                .unlockedBy(getHasName(getIngredientA()), has(getIngredientA()))
                .save(pWriter);
    }
}
