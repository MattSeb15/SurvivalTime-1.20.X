package net.most.survivaltimemod.datagen.pattern;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.level.ItemLike;
import net.most.survivaltimemod.SurvivalTimeMod;

import java.util.function.Consumer;

public class XMaterialShapelessPattern extends HourglassPattern implements IHourglassPattern{
    public final ItemLike[] ingredients;

    public XMaterialShapelessPattern(ItemLike result, int resultCount, int craftTime, int energyCost, ItemLike... ingredients) {
        super(result, resultCount, craftTime, energyCost);
        this.ingredients = ingredients;
    }

    public ItemLike[] getIngredients() {
        return ingredients;
    }

    @Override
    public void create(Consumer<FinishedRecipe> pWriter) {
        getShapelessBuilder()
                .requires(getIngredients())
                .craftTime(getCraftTime())
                .energyCost(getEnergyCost())
                .unlockedBy(getHasName(getIngredients()[0]), has(getIngredients()[0]))
                .save(pWriter, SurvivalTimeMod.MOD_ID + ":" + getItemName(getResult()) + "_x_material_shapeless_pattern");

    }
}
