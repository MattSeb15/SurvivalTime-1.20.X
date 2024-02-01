package net.most.survivaltimemod.datagen.pattern;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.level.ItemLike;
import net.most.survivaltimemod.SurvivalTimeMod;

import java.util.function.Consumer;

public class AxePattern extends ToolPattern implements IHourglassPattern{


    public AxePattern(ItemLike result, int craftTime, int energyCost, ItemLike stickIngredient, ItemLike bladeIngredient, ItemLike shardIngredient, ItemLike lastTierIngredient) {
        super(result, craftTime, energyCost, stickIngredient, bladeIngredient, shardIngredient, lastTierIngredient);
    }

    @Override
    public void create(Consumer<FinishedRecipe> pWriter) {
        getShapedBuilder()
                .pattern("D M D")
                .pattern("DMTMD")
                .pattern("  S  ")
                .pattern("  S  ")
                .pattern("  S  ")
                .define('M', getBladeIngredient())
                .define('D', getShardIngredient())
                .define('S', getStickIngredient())
                .define('T', getLastTierIngredient())
                .unlockedBy(getHasName(getBladeIngredient()), has(getBladeIngredient()))
                .save(pWriter, SurvivalTimeMod.MOD_ID + ":" + getItemName(getResult()) + "_axe_pattern");
    }
}
