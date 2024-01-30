package net.most.survivaltimemod.datagen.util;

import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.level.ItemLike;
import net.most.survivaltimemod.datagen.custom.HourglassHubShapedRecipeBuilder;

import java.util.function.Consumer;

public interface IArmorPattern {
    void create(Consumer<FinishedRecipe> pWriter);
    ItemLike getResult();
    int getResultCount();
    int getCraftTime();
    int getEnergyCost();

    default HourglassHubShapedRecipeBuilder getRecipeBuilder() {
        return HourglassHubShapedRecipeBuilder.shaped(this.getResult(), this.getResultCount())
                .craftTime(this.getCraftTime())
                .energyCost(this.getEnergyCost());
    }

    default String getHasName(ItemLike pItemLike) {
        return "has_" + getItemName(pItemLike);
    }

    default String getItemName(ItemLike pItemLike) {
        return BuiltInRegistries.ITEM.getKey(pItemLike.asItem()).getPath();
    }

    default InventoryChangeTrigger.TriggerInstance has(ItemLike pItemLike) {
        return inventoryTrigger(ItemPredicate.Builder.item().of(pItemLike).build());
    }

    default InventoryChangeTrigger.TriggerInstance inventoryTrigger(ItemPredicate... pPredicates) {
        return new InventoryChangeTrigger.TriggerInstance(ContextAwarePredicate.ANY, MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, pPredicates);
    }
}
