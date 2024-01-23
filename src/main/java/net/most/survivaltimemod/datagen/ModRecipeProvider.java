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
import net.most.survivaltimemod.util.records.*;
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
                .pattern("LPL")
                .pattern("PAP")
                .pattern("LPL")
                .define('L', Items.LAPIS_BLOCK)
                .define('P', ModItems.OPAL_SHARD_LOOP.get())
                .define('A', Blocks.ANVIL)
                .unlockedBy(getHasName(ModBlocks.TIME_STATION.get()), has(ModItems.LAPISLOOPIUM.get()))
                .save(pWriter);


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

        HourglassHubShapedRecipeBuilder.shaped(ModItems.OPAL_RAW.get(), 2)
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
        for (SwordPattern swordPattern : SurvivalTimeUtilGenerator.SWORD_PATTERN_LIST) {
            swordPattern(pWriter, swordPattern);
        }
        for (PickaxePattern pickaxePattern : SurvivalTimeUtilGenerator.PICKAXE_PATTERN_LIST) {
            pickaxePattern(pWriter, pickaxePattern);
        }
        for (AxePattern axePattern : SurvivalTimeUtilGenerator.AXE_PATTERN_LIST) {
            axePattern(pWriter, axePattern);
        }
        for (ShovelPattern shovelPattern : SurvivalTimeUtilGenerator.SHOVEL_PATTERN_LIST) {
            shovelPattern(pWriter, shovelPattern);
        }
        for (HoePattern hoePattern : SurvivalTimeUtilGenerator.HOE_PATTERN_LIST) {
            hoePattern(pWriter, hoePattern);
        }

        for (StarCenterPattern starCenterPattern : SurvivalTimeUtilGenerator.STAR_CENTER_PATTERN_LIST) {
            starCenterPattern(pWriter, starCenterPattern);
        }
        for (MonoMaterialPattern monoMaterialPattern : SurvivalTimeUtilGenerator.MONO_MATERIAL_PATTERN_LIST) {
            monoMaterialPattern(pWriter, monoMaterialPattern);
        }

        for (XMaterialShapelessPattern xMaterialShapelessPattern : SurvivalTimeUtilGenerator.X_MATERIALS_PATTERN_LIST) {
            xMaterialShapelessPattern(pWriter, xMaterialShapelessPattern);
        }


//        HourglassHubShapelessRecipeBuilder.recipe(ModItems.COMPACTED_MILK.get(), 1)
//                .requires(Items.MILK_BUCKET, 4)
//                .craftTime(20 * 60)
//                .energyCost(60 * 4)
//                .unlockedBy(getHasName(ModItems.COMPACTED_MILK.get()), has(ModItems.COMPACTED_MILK.get()))
//                .save(pWriter);


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

    private void monoMaterialPattern(Consumer<FinishedRecipe> pWriter, MonoMaterialPattern pattern) {

        if (pattern.getIngredientCount() >= 1 && pattern.getIngredientCount() <= 25) {
            HourglassHubShapelessRecipeBuilder.recipe(pattern.getResult(), pattern.getResultCount())
                    .requires(pattern.getIngredient(), pattern.getIngredientCount())
                    .energyCost(pattern.getEnergyCost())
                    .craftTime(pattern.getCraftTime())
                    .unlockedBy(getHasName(pattern.getIngredient()), has(pattern.getIngredient()))
                    .save(pWriter, SurvivalTimeMod.MOD_ID + ":" + getItemName(pattern.getResult()) + "_mono_material_pattern");
        }

    }

    private void xMaterialShapelessPattern(Consumer<FinishedRecipe> pWriter, XMaterialShapelessPattern pattern) {
        HourglassHubShapelessRecipeBuilder.recipe(pattern.getResult(), pattern.getResultCount())
                .requires(pattern.getIngredients())
                .craftTime(pattern.getCraftTime())
                .energyCost(pattern.getEnergyCost())
                .unlockedBy(getHasName(pattern.getIngredients()[0]), has(pattern.getIngredients()[0]))
                .save(pWriter, SurvivalTimeMod.MOD_ID + ":" + getItemName(pattern.getResult()) + "_x_material_shapeless_pattern");

    }

    private void swordPattern(Consumer<FinishedRecipe> pWriter, SwordPattern swordPattern) {
        HourglassHubShapedRecipeBuilder.shaped(swordPattern.getResult(), swordPattern.getResultCount())
                .pattern("  M  ")
                .pattern("  M  ")
                .pattern("  M  ")
                .pattern(" DSD ")
                .pattern("  S  ")
                .define('M', swordPattern.getBladeIngredient())
                .define('D', swordPattern.getShardIngredient())
                .define('S', swordPattern.getStickIngredient())
                .craftTime(swordPattern.getCraftTime())
                .energyCost(swordPattern.getEnergyCost())
                .unlockedBy(getHasName(swordPattern.getBladeIngredient()), has(swordPattern.getBladeIngredient()))
                .save(pWriter, SurvivalTimeMod.MOD_ID + ":" + getItemName(swordPattern.getResult()) + "_sword_pattern");
    }

    private void pickaxePattern(Consumer<FinishedRecipe> pWriter, PickaxePattern pickaxePattern) {

        HourglassHubShapedRecipeBuilder.shaped(pickaxePattern.getResult(), pickaxePattern.getResultCount())
                .pattern(" DMD ")
                .pattern("M S M")
                .pattern("  S  ")
                .pattern("  S  ")
                .pattern("  S  ")
                .define('M', pickaxePattern.getBladeIngredient())
                .define('D', pickaxePattern.getShardIngredient())
                .define('S', pickaxePattern.getStickIngredient())
                .craftTime(pickaxePattern.getCraftTime() + 20 * 60 * 15)
                .energyCost(pickaxePattern.getEnergyCost() + 60 * 15)
                .unlockedBy(getHasName(pickaxePattern.getBladeIngredient()), has(pickaxePattern.getBladeIngredient()))
                .save(pWriter, SurvivalTimeMod.MOD_ID + ":" + getItemName(pickaxePattern.getResult()) + "_pickaxe_pattern");

    }

    private void hoePattern(Consumer<FinishedRecipe> pWriter, HoePattern hoePattern) {
        HourglassHubShapedRecipeBuilder.shaped(hoePattern.getResult(), hoePattern.getResultCount())
                .pattern("  MDM")
                .pattern("  D  ")
                .pattern("  S  ")
                .pattern("  S  ")
                .pattern("  S  ")
                .define('M', hoePattern.getBladeIngredient())
                .define('D', hoePattern.getShardIngredient())
                .define('S', hoePattern.getStickIngredient())
                .craftTime(hoePattern.getCraftTime())
                .energyCost(hoePattern.getEnergyCost())
                .unlockedBy(getHasName(hoePattern.getBladeIngredient()), has(hoePattern.getBladeIngredient()))
                .save(pWriter, SurvivalTimeMod.MOD_ID + ":" + getItemName(hoePattern.getResult()) + "_hoe_pattern");

    }

    private void shovelPattern(Consumer<FinishedRecipe> pWriter, ShovelPattern shovelPattern) {

        HourglassHubShapedRecipeBuilder.shaped(shovelPattern.getResult(), shovelPattern.getResultCount())
                .pattern("  M  ")
                .pattern("  D  ")
                .pattern("  S  ")
                .pattern("  S  ")
                .pattern("  S  ")
                .define('M', shovelPattern.getBladeIngredient())
                .define('D', shovelPattern.getShardIngredient())
                .define('S', shovelPattern.getStickIngredient())
                .craftTime(shovelPattern.getCraftTime())
                .energyCost(shovelPattern.getEnergyCost())
                .unlockedBy(getHasName(shovelPattern.getBladeIngredient()), has(shovelPattern.getBladeIngredient()))
                .save(pWriter, SurvivalTimeMod.MOD_ID + ":" + getItemName(shovelPattern.getResult()) + "_shovel_pattern");

    }

    private void axePattern(Consumer<FinishedRecipe> pWriter, AxePattern axePattern) {
        HourglassHubShapedRecipeBuilder.shaped(axePattern.getResult(), axePattern.getResultCount())
                .pattern("D M D")
                .pattern("DMSMD")
                .pattern("  S  ")
                .pattern("  S  ")
                .pattern("  S  ")
                .define('M', axePattern.getBladeIngredient())
                .define('D', axePattern.getShardIngredient())
                .define('S', axePattern.getStickIngredient())
                .craftTime(axePattern.getCraftTime())
                .energyCost(axePattern.getEnergyCost())
                .unlockedBy(getHasName(axePattern.getBladeIngredient()), has(axePattern.getBladeIngredient()))
                .save(pWriter, SurvivalTimeMod.MOD_ID + ":" + getItemName(axePattern.getResult()) + "_axe_pattern");


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
                .save(pWriter, SurvivalTimeMod.MOD_ID + ":" + getItemName(item) + "_from_block");

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
                .save(pWriter, SurvivalTimeMod.MOD_ID + ":" + getItemName(pFullBorderPattern.getResult()) + "_full_border_pattern");
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
                .save(pWriter, SurvivalTimeMod.MOD_ID + ":" + getItemName(pFullBorderPattern.getResult()) + "_medium_border_pattern");
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
