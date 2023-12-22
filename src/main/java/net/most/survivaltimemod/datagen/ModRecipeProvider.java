package net.most.survivaltimemod.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.most.survivaltimemod.SurvivalTimeMod;
import net.most.survivaltimemod.datagen.custom.HourglassHubShapedRecipeBuilder;
import net.most.survivaltimemod.datagen.custom.HourglassHubShapelessRecipeBuilder;
import net.most.survivaltimemod.datagen.util.SurvivalTimeUtilGenerator;
import net.most.survivaltimemod.item.ModItems;
import net.most.survivaltimemod.util.ExpCookTimeGroupItem;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {

        for (Map.Entry<ItemLike, ExpCookTimeGroupItem> entry : SurvivalTimeUtilGenerator.SMELTING_RECIPE_MAP.entrySet()) {
            ItemLike item = entry.getKey();
            ExpCookTimeGroupItem expCookTimeGroup = entry.getValue();
            oreSmelting(pWriter,
                    item,
                    RecipeCategory.MISC,
                    expCookTimeGroup.getResult(),
                    expCookTimeGroup.getExp(),
                    expCookTimeGroup.getCookTime(),
                    expCookTimeGroup.getGroup());


        }
        for (Map.Entry<ItemLike, ExpCookTimeGroupItem> entry : SurvivalTimeUtilGenerator.BLASTING_RECIPE_MAP.entrySet()) {
            ItemLike item = entry.getKey();
            ExpCookTimeGroupItem expCookTimeGroup = entry.getValue();
            oreBlasting(pWriter,
                    item,
                    RecipeCategory.MISC,
                    expCookTimeGroup.getResult(),
                    expCookTimeGroup.getExp(),
                    expCookTimeGroup.getCookTime(),
                    expCookTimeGroup.getGroup());


        }

        for (Map.Entry<ItemLike, ItemLike> entry : SurvivalTimeUtilGenerator.BLOCK_FROM_ITEM_RECIPE_MAP.entrySet()) {
            ItemLike block = entry.getKey();
            ItemLike item = entry.getValue();

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, block)
                    .pattern("###")
                    .pattern("###")
                    .pattern("###")
                    .define('#', item)
                    .unlockedBy(getHasName(item), has(item))
                    .save(pWriter);

            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, item, 9)
                    .requires(block)
                    .unlockedBy(getHasName(block), has(block))
                    .save(pWriter, SurvivalTimeMod.MOD_ID + ":" + getItemName(item) + "_from_block");

            HourglassHubShapedRecipeBuilder.shaped(block)
                    .pattern("#####")
                    .pattern("#####")
                    .pattern("#####")
                    .pattern("#####")
                    .pattern("#####")
                    .define('#', item)
                    .craftTime(20 * 120)
                    .energyCost(60 * 10)
                    .unlockedBy(getHasName(item), has(item))
                    .save(pWriter);

            HourglassHubShapelessRecipeBuilder.recipe(item, 25)
                    .requires(block)
                    .craftTime(20 * 60)
                    .energyCost(60 * 5)
                    .unlockedBy(getHasName(block), has(block))
                    .save(pWriter, SurvivalTimeMod.MOD_ID + ":" + getItemName(item) + "hgh_from_block");
        }
        for (Map.Entry<ItemLike, ItemLike> entry : SurvivalTimeUtilGenerator.STICKS_FROM_SHARDS_RECIPE_MAP.entrySet()) {
            ItemLike result = entry.getKey();
            ItemLike shard_item = entry.getValue();

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result, 1)
                    .pattern(" # ")
                    .pattern("#P#")
                    .pattern(" # ")
                    .define('#', shard_item)
                    .define('P', Items.STICK)
                    .unlockedBy(getHasName(shard_item), has(shard_item))
                    .save(pWriter);
        }

        for (Map.Entry<ItemLike, ItemLike> entry : SurvivalTimeUtilGenerator.RAW_SHARDS_RECIPE_MAP.entrySet()) {
            ItemLike raw_item = entry.getKey();
            ItemLike shard_item = entry.getValue();

            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, raw_item, 2)
                    .pattern(" # ")
                    .pattern("#R#")
                    .pattern(" # ")
                    .define('#', shard_item)
                    .define('R', Items.CLOCK)
                    .unlockedBy(getHasName(shard_item), has(shard_item))
                    .save(pWriter);
        }


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.FIERY_TIME.get())
                .pattern(" # ")
                .pattern("#E#")
                .pattern(" # ")
                .define('#', ModItems.OPAL_SHARD_EPOCH.get())
                .define('E', Ingredient.of(Items.COAL, Items.CHARCOAL))
                .unlockedBy(getHasName(ModItems.FIERY_TIME.get()), has(ModItems.FIERY_TIME.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.OPAL_RAW.get(), 2)
                .requires(ModItems.OPAL_SHARD_CHRONA.get())
                .requires(ModItems.OPAL_SHARD_TEMPORA.get())
                .requires(ModItems.OPAL_SHARD_EPOCH.get())
                .requires(ModItems.OPAL_SHARD_FLUX.get())
                .requires(ModItems.OPAL_SHARD_LOOP.get())
                .requires(Items.DIAMOND, 4)
                .unlockedBy(getHasName(ModItems.OPAL_RAW.get()), has(ModItems.OPAL_RAW.get()))
                .save(pWriter);
        HourglassHubShapelessRecipeBuilder.recipe(ModItems.LAPISLOOPIUM.get(), 10)
                .requires(Items.LAPIS_LAZULI)
                .requires(ModItems.OPAL_SHARD_LOOP.get())
                .craftTime(20 * 30)
                .energyCost(60 * 5)
                .unlockedBy(getHasName(ModItems.OPAL_SHARD_LOOP.get()), has(ModItems.OPAL_SHARD_LOOP.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.LAPISLOOPIUM.get(), 1)
                .requires(Items.LAPIS_LAZULI)
                .requires(ModItems.OPAL_SHARD_LOOP.get())
                .unlockedBy(getHasName(ModItems.OPAL_SHARD_LOOP.get()), has(ModItems.OPAL_SHARD_LOOP.get()))
                .save(pWriter, SurvivalTimeMod.MOD_ID + ":" + getItemName(ModItems.LAPISLOOPIUM.get()) + "_from_crafting_table");

//        HourglassHubShapelessRecipeBuilder.recipe(item, 25)
//                .requires(block)
//                .craftTime(20*60)
//                .energyCost(60*5)
//                .unlockedBy(getHasName(block), has(block))
//        HourglassHubShapelessRecipeBuilder.recipe(ModItems.OPAL_RAW.get(), 2)
//                .requires(ModItems.OPAL_SHARD_CHRONA.get())
//                .requires(ModItems.OPAL_SHARD_TEMPORA.get())
//                .requires(ModItems.OPAL_SHARD_EPOCH.get())
//                .requires(ModItems.OPAL_SHARD_FLUX.get())
//                .requires(ModItems.OPAL_SHARD_LOOP.get())
//                .unlockedBy(getHasName(ModItems.OPAL_RAW.get()), has(ModItems.OPAL_RAW.get()))
//                .save(pWriter);


    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients,
                                      RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme
            , String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pIngredients,
                                      RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme
            , String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients,
                                      RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime
            , String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pIngredients,
                                      RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime
            , String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<?
            extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory,
                                     ItemLike pResult, float pExperience, int pCookingTime, String pGroup,
                                     String pRecipeName) {
        for (ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(
                            Ingredient.of(itemlike),
                            pCategory,
                            pResult,
                            pExperience,
                            pCookingTime
                            , pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(
                            pFinishedRecipeConsumer,
                            SurvivalTimeMod.MOD_ID
                                    + ":"
                                    + getItemName(pResult)
                                    + pRecipeName
                                    + "_"
                                    + getItemName(itemlike)
                    );
        }

    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<?
            extends AbstractCookingRecipe> pCookingSerializer, ItemLike pIngredients, RecipeCategory pCategory,
                                     ItemLike pResult, float pExperience, int pCookingTime, String pGroup,
                                     String pRecipeName) {
        SimpleCookingRecipeBuilder.generic(
                        Ingredient.of(pIngredients),
                        pCategory,
                        pResult,
                        pExperience,
                        pCookingTime
                        , pCookingSerializer)
                .group(pGroup).unlockedBy(getHasName(pIngredients), has(pIngredients))
                .save(
                        pFinishedRecipeConsumer,
                        SurvivalTimeMod.MOD_ID
                                + ":"
                                + getItemName(pResult)
                                + pRecipeName
                                + "_"
                                + getItemName(pIngredients)
                );
    }


}
