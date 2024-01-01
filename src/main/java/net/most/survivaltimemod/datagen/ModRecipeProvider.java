package net.most.survivaltimemod.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.most.survivaltimemod.SurvivalTimeMod;
import net.most.survivaltimemod.block.ModBlocks;
import net.most.survivaltimemod.datagen.custom.HourglassHubShapedRecipeBuilder;
import net.most.survivaltimemod.datagen.custom.HourglassHubShapelessRecipeBuilder;
import net.most.survivaltimemod.datagen.util.SurvivalTimeUtilGenerator;
import net.most.survivaltimemod.item.ModItems;
import net.most.survivaltimemod.util.ExpCookTimeGroupItem;
import net.most.survivaltimemod.util.records.FullBorderPattern;
import net.most.survivaltimemod.util.records.MediumBorderPattern;
import net.most.survivaltimemod.util.records.StarCenterPattern;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> pWriter) {

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.HOURGLASS_HUB_STATION.get())
                .pattern("LCL")
                .pattern("E#F")
                .pattern("LTL")
                .define('L', ModItems.OPAL_SHARD_LOOP.get())
                .define('F', ModItems.OPAL_SHARD_FLUX.get())
                .define('E', ModItems.OPAL_SHARD_EPOCH.get())
                .define('T', ModItems.OPAL_SHARD_TEMPORA.get())
                .define('C', ModItems.OPAL_SHARD_CHRONA.get())
                .define('#', Blocks.CRAFTING_TABLE)
                .unlockedBy(getHasName(ModBlocks.HOURGLASS_HUB_STATION.get()), has(Blocks.CRAFTING_TABLE))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.TIME_STATION.get())
                .pattern("LLL")
                .pattern("LAL")
                .pattern("LLL")
                .define('L', ModItems.LAPISLOOPIUM.get())
                .define('A', Blocks.ANVIL)
                .unlockedBy(getHasName(ModBlocks.TIME_STATION.get()), has(ModItems.LAPISLOOPIUM.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.LAPISLOOPIUM.get(), 1)
                .requires(Items.LAPIS_LAZULI, 4)
                .requires(ModItems.OPAL_SHARD_LOOP.get())
                .unlockedBy(getHasName(ModItems.OPAL_SHARD_LOOP.get()), has(ModItems.OPAL_SHARD_LOOP.get()))
                .save(pWriter, SurvivalTimeMod.MOD_ID + ":" + getItemName(ModItems.LAPISLOOPIUM.get()) + "_from_crafting_table");

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






        HourglassHubShapedRecipeBuilder.shaped(ModItems.FIERY_TIME.get())
                .pattern("     ")
                .pattern("  #  ")
                .pattern(" #E# ")
                .pattern("  #  ")
                .pattern("     ")
                .define('#', ModItems.OPAL_SHARD_EPOCH.get())
                .define('E', Ingredient.of(Items.COAL, Items.CHARCOAL))
                .unlockedBy(getHasName(ModItems.FIERY_TIME.get()) + "_base", has(ModItems.FIERY_TIME.get()))
                .save(pWriter, SurvivalTimeMod.MOD_ID + ":" + getItemName(ModItems.FIERY_TIME.get()) + "_base");

        HourglassHubShapedRecipeBuilder.shaped(ModItems.OPAL_RAW.get(),2)
                .pattern("     ")
                .pattern(" DLD ")
                .pattern(" TCF ")
                .pattern(" DED ")
                .pattern("     ")
                .define('L', ModItems.OPAL_SHARD_LOOP.get())
                .define('F', ModItems.OPAL_SHARD_FLUX.get())
                .define('E', ModItems.OPAL_SHARD_EPOCH.get())
                .define('T', ModItems.OPAL_SHARD_TEMPORA.get())
                .define('C', ModItems.OPAL_SHARD_CHRONA.get())
                .define('D', Items.DIAMOND)
                .unlockedBy(getHasName(ModItems.OPAL_RAW.get()), has(ModItems.OPAL_RAW.get()))
                .save(pWriter, SurvivalTimeMod.MOD_ID + ":" + getItemName(ModItems.OPAL_RAW.get()) + "_from_shards");

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
                .unlockedBy(getHasName(ModItems.LAPISLOOPIUM.get()), has(ModItems.LAPISLOOPIUM.get()))
                .save(pWriter);

        for (Map.Entry<ItemLike, ItemLike> entry : SurvivalTimeUtilGenerator.BLOCK_FROM_ITEM_RECIPE_MAP.entrySet()) {
            ItemLike block = entry.getKey();
            ItemLike item = entry.getValue();

            blockItemPattern(pWriter, block, item);
        }

        for (Map.Entry<ItemLike, ItemLike> entry : SurvivalTimeUtilGenerator.STICKS_FROM_SHARDS_RECIPE_MAP.entrySet()) {
            ItemLike result = entry.getKey();
            ItemLike shard_item = entry.getValue();
            int entryIndex = SurvivalTimeUtilGenerator.STICKS_FROM_SHARDS_RECIPE_MAP.entrySet().stream().toList().indexOf(entry);
            int craftTime = 20 * 10 * (entryIndex + 1);
            int energyCost = 60 * (entryIndex + 1);
            starCenterPattern(pWriter, new StarCenterPattern(result, 1, craftTime, energyCost, shard_item, Items.STICK));
        }

        for (Map.Entry<ItemLike, ItemLike> entry : SurvivalTimeUtilGenerator.RAW_SHARDS_RECIPE_MAP.entrySet()) {
            ItemLike raw_item = entry.getKey();
            ItemLike shard_item = entry.getValue();
            int entryIndex = SurvivalTimeUtilGenerator.RAW_SHARDS_RECIPE_MAP.entrySet().stream().toList().indexOf(entry);
            int craftTime = 20 * 30 * (entryIndex + 1);
            int energyCost = 60 * 2 * (entryIndex + 1);

            starCenterPattern(pWriter, new StarCenterPattern(raw_item, 2, craftTime, energyCost, shard_item, Items.CLOCK));
        }

        for (FullBorderPattern fullBorderPattern : SurvivalTimeUtilGenerator.FULL_BORDER_PATTERN_LIST) {
            fullBorderPattern(pWriter, fullBorderPattern);
        }

        for (MediumBorderPattern mediumBorderPattern : SurvivalTimeUtilGenerator.MEDIUM_BORDER_PATTERN_LIST) {
            mediumBorderPattern(pWriter, mediumBorderPattern);
        }

        for (StarCenterPattern starCenterPattern : SurvivalTimeUtilGenerator.STAR_CENTER_PATTERN_LIST) {
            starCenterPattern(pWriter, starCenterPattern);
        }

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

    private void blockItemPattern(Consumer<FinishedRecipe> pWriter, ItemLike block, ItemLike item) {
        HourglassHubShapedRecipeBuilder.shaped(block)
                .pattern("     ")
                .pattern(" ### ")
                .pattern(" ### ")
                .pattern(" ### ")
                .pattern("     ")
                .define('#', item)
                .craftTime(20 * 120)
                .energyCost(60 * 10)
                .unlockedBy(getHasName(item), has(item))
                .save(pWriter);

        HourglassHubShapelessRecipeBuilder.recipe(item, 9)
                .requires(block)
                .craftTime(20 * 60)
                .energyCost(60 * 5)
                .unlockedBy(getHasName(block), has(block))
                .save(pWriter, SurvivalTimeMod.MOD_ID + ":" + getItemName(item) + "from_block");

    }


    protected static void fullBorderPattern(Consumer<FinishedRecipe> pWriter, FullBorderPattern pFullBorderPattern) {
        ItemLike ingredientA = pFullBorderPattern.getIngredientA();

        HourglassHubShapedRecipeBuilder.shaped(pFullBorderPattern.getResult(), pFullBorderPattern.getResultCount())
                .pattern("AAAAA")
                .pattern("ABBBA")
                .pattern("ABCBA")
                .pattern("ABBBA")
                .pattern("AAAAA")
                .define('A', ingredientA)
                .define('B', pFullBorderPattern.getIngredientB())
                .define('C', pFullBorderPattern.getIngredientC())
                .craftTime(pFullBorderPattern.getCraftTime())
                .energyCost(pFullBorderPattern.getEnergyCost())
                .unlockedBy(getHasName(ingredientA), has(ingredientA))
                .save(pWriter);
    }

    protected static void mediumBorderPattern(Consumer<FinishedRecipe> pWriter, MediumBorderPattern pFullBorderPattern) {
        ItemLike ingredientB = pFullBorderPattern.getIngredientB();

        HourglassHubShapedRecipeBuilder.shaped(pFullBorderPattern.getResult(), pFullBorderPattern.getResultCount())
                .pattern("     ")
                .pattern(" BBB ")
                .pattern(" BCB ")
                .pattern(" BBB ")
                .pattern("     ")
                .define('B', pFullBorderPattern.getIngredientB())
                .define('C', pFullBorderPattern.getIngredientC())
                .craftTime(pFullBorderPattern.getCraftTime())
                .energyCost(pFullBorderPattern.getEnergyCost())
                .unlockedBy(getHasName(ingredientB), has(ingredientB))
                .save(pWriter);
    }

    protected static void starCenterPattern(Consumer<FinishedRecipe> pWriter, StarCenterPattern starCenterPattern) {
        HourglassHubShapedRecipeBuilder.shaped(starCenterPattern.getResult(), starCenterPattern.getResultCount())
                .pattern("     ")
                .pattern("  B  ")
                .pattern(" BCB ")
                .pattern("  B  ")
                .pattern("     ")
                .define('B', starCenterPattern.getIngredientBorder())
                .define('C', starCenterPattern.getIngredientCenter())
                .craftTime(starCenterPattern.getCraftTime())
                .energyCost(starCenterPattern.getEnergyCost())
                .unlockedBy(getHasName(starCenterPattern.getResult()), has(starCenterPattern.getResult()))
                .save(pWriter, SurvivalTimeMod.MOD_ID + ":" + getItemName(starCenterPattern.getResult()) + "_center_pattern");
    }

    protected static void oreSmelting(@NotNull Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients,
                                      @NotNull RecipeCategory pCategory, @NotNull ItemLike pResult, float pExperience, int pCookingTIme
            , @NotNull String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pIngredients,
                                      RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme
            , String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(@NotNull Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients,
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
