package net.most.survivaltimemod.datagen.pattern;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.level.ItemLike;
import net.most.survivaltimemod.SurvivalTimeMod;

import java.util.function.Consumer;

public class BlockPattern extends HourglassPattern implements IHourglassPattern{
    public final ItemLike itemIngredient;

    public BlockPattern(ItemLike result, int resultCount, int craftTime, int energyCost, ItemLike itemIngredient) {
        super(result, resultCount, craftTime, energyCost);
        this.itemIngredient = itemIngredient;
    }

    public BlockPattern(ItemLike result, int craftTime, int energyCost, ItemLike itemIngredient) {
        super(result, 1, craftTime, energyCost);
        this.itemIngredient = itemIngredient;
    }


    public ItemLike getItemIngredient() {
        return itemIngredient;
    }

    @Override
    public void create(Consumer<FinishedRecipe> pWriter) {
        getShapedBuilder()
                .pattern("     ")
                .pattern(" ### ")
                .pattern(" ### ")
                .pattern(" ### ")
                .pattern("     ")
                .define('#', getItemIngredient())
                .unlockedBy(getHasName(getItemIngredient()), has(getItemIngredient()))
                .save(pWriter);
    }
}
