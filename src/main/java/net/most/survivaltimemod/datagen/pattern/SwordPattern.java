package net.most.survivaltimemod.datagen.pattern;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.level.ItemLike;
import net.most.survivaltimemod.SurvivalTimeMod;

import java.util.function.Consumer;

public class SwordPattern extends ToolPattern implements IHourglassPattern {


    public SwordPattern(ItemLike result, int craftTime, int energyCost, ItemLike stickIngredient, ItemLike bladeIngredient, ItemLike shardIngredient, ItemLike lastTierIngredient) {
        super(result, craftTime, energyCost, stickIngredient, bladeIngredient, shardIngredient, lastTierIngredient);
    }

    @Override
    public void create(Consumer<FinishedRecipe> pWriter) {
        getShapedBuilder()
                .pattern("  D  ")
                .pattern(" DDD ")
                .pattern(" DTD ")
                .pattern(" MSM ")
                .pattern("  S  ")
                .define('M', getBladeIngredient())
                .define('D', getShardIngredient())
                .define('S', getStickIngredient())
                .define('T', getLastTierIngredient())
                .unlockedBy(getHasName(getBladeIngredient()), has(getBladeIngredient()))
                .save(pWriter, SurvivalTimeMod.MOD_ID + ":" + getItemName(getResult()) + "_sword_pattern");
    }
}
