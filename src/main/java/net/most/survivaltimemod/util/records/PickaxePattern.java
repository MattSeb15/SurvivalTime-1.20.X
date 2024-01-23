package net.most.survivaltimemod.util.records;

import net.minecraft.world.level.ItemLike;

public class PickaxePattern extends ToolPattern {


    public PickaxePattern(ItemLike result, int craftTime, int energyCost, ItemLike stickIngredient, ItemLike bladeIngredient,
                          ItemLike shardIngredient) {
        super(result, craftTime, energyCost, stickIngredient, bladeIngredient, shardIngredient);
    }
}
