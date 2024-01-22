package net.most.survivaltimemod.util.records;

import net.minecraft.world.level.ItemLike;

public class SwordPattern extends HourglassPattern {
    private final ItemLike stickIngredient;
    private final ItemLike bladeIngredient;
    private final ItemLike shardIngredient;

    public SwordPattern(ItemLike result, int resultCount, int craftTime, int energyCost, ItemLike stickIngredient, ItemLike bladeIngredient,
                        ItemLike shardIngredient) {
        super(result, resultCount, craftTime, energyCost);
        this.stickIngredient = stickIngredient;
        this.bladeIngredient = bladeIngredient;
        this.shardIngredient = shardIngredient;
    }

    public SwordPattern(ItemLike result, int craftTime, int energyCost, ItemLike stickIngredient, ItemLike bladeIngredient,
                        ItemLike shardIngredient) {
        super(result, 1, craftTime, energyCost);
        this.stickIngredient = stickIngredient;
        this.bladeIngredient = bladeIngredient;
        this.shardIngredient = shardIngredient;
    }

    public ItemLike getStickIngredient() {
        return stickIngredient;
    }

    public ItemLike getBladeIngredient() {
        return bladeIngredient;
    }

    public ItemLike getShardIngredient() {
        return shardIngredient;
    }

}
