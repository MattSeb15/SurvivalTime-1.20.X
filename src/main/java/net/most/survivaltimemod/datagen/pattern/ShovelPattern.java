package net.most.survivaltimemod.datagen.pattern;

import net.minecraft.world.level.ItemLike;

public class ShovelPattern extends ToolPattern {


    public ShovelPattern(ItemLike result, int craftTime, int energyCost, ItemLike stickIngredient, ItemLike bladeIngredient,
                         ItemLike shardIngredient) {
        super(result, craftTime, energyCost, stickIngredient, bladeIngredient, shardIngredient);
    }
}
