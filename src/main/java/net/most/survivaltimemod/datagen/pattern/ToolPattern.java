package net.most.survivaltimemod.datagen.pattern;

import net.minecraft.world.level.ItemLike;

public class ToolPattern extends HourglassPattern {
    private final ItemLike stickIngredient;
    private final ItemLike bladeIngredient;
    private final ItemLike shardIngredient;
    private final ItemLike lastTierIngredient;

    public ToolPattern(ItemLike result, int resultCount, int craftTime, int energyCost, ItemLike stickIngredient, ItemLike bladeIngredient,
                       ItemLike shardIngredient, ItemLike lastTierIngredient) {
        super(result, resultCount, craftTime, energyCost);
        this.stickIngredient = stickIngredient;
        this.bladeIngredient = bladeIngredient;
        this.shardIngredient = shardIngredient;
        this.lastTierIngredient = lastTierIngredient;
    }

    public ToolPattern(ItemLike result, int craftTime, int energyCost, ItemLike stickIngredient, ItemLike bladeIngredient,
                       ItemLike shardIngredient, ItemLike lastTierIngredient) {
        super(result, 1, craftTime, energyCost);
        this.stickIngredient = stickIngredient;
        this.bladeIngredient = bladeIngredient;
        this.shardIngredient = shardIngredient;
        this.lastTierIngredient = lastTierIngredient;
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

    public ItemLike getLastTierIngredient() {
        return lastTierIngredient;
    }

}
