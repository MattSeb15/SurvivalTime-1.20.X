package net.most.survivaltimemod.datagen.pattern;

import net.minecraft.world.level.ItemLike;

public class XMaterialShapelessPattern extends HourglassPattern {
    public final ItemLike[] ingredients;

    public XMaterialShapelessPattern(ItemLike result, int resultCount, int craftTime, int energyCost, ItemLike... ingredients) {
        super(result, resultCount, craftTime, energyCost);
        this.ingredients = ingredients;
    }

    public ItemLike[] getIngredients() {
        return ingredients;
    }
}
