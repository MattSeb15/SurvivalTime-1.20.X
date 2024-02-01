package net.most.survivaltimemod.datagen.pattern;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.level.ItemLike;

import java.util.function.Consumer;

public class ChestplateHourglassPattern extends ArmorPattern implements IHourglassPattern {
    public ChestplateHourglassPattern(ItemLike result, int resultCount, int craftTime, int energyCost, ItemLike moreWeightIngredient, ItemLike lessWeightIngredient,
                                      ItemLike lastTierArmor) {
        super(result, resultCount, craftTime, energyCost, moreWeightIngredient, lessWeightIngredient, lastTierArmor);
    }

    @Override
    public void create(Consumer<FinishedRecipe> pWriter) {
        this.getShapedBuilder()
                .pattern("L   L")
                .pattern("ML LM")
                .pattern("LLMLL")
                .pattern(" LTL ")
                .pattern(" LLL ")
                .define('M', this.getMoreWeightIngredient())
                .define('L', this.getLessWeightIngredient())
                .define('T', this.getLastTierArmor())
                .unlockedBy(getHasName(this.getResult()), has(this.getResult()))
                .save(pWriter, getItemName(this.getResult()) + "_chestplate");

    }
}
