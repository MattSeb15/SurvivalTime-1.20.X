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
import net.most.survivaltimemod.datagen.pattern.*;
import net.most.survivaltimemod.datagen.util.SurvivalTimeUtilGenerator;
import net.most.survivaltimemod.item.ModItems;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.ArrayList;
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

        List<List<? extends IHourglassPattern>> allPatterns = new ArrayList<>();

        for (Field field : SurvivalTimeUtilGenerator.class.getDeclaredFields()) {
            if (List.class.isAssignableFrom(field.getType())) {
                try {
                    List<?> potentialPatternList = (List<?>) field.get(null);
                    if (!potentialPatternList.isEmpty() && potentialPatternList.get(0) instanceof IHourglassPattern) {
                        allPatterns.add((List<? extends IHourglassPattern>) potentialPatternList);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        for (List<? extends IHourglassPattern> patternList : allPatterns) {
            for (IHourglassPattern pattern : patternList) {
                pattern.create(pWriter);
            }
        }


//        for (FullBorderPattern fullBorderPattern : SurvivalTimeUtilGenerator.FULL_BORDER_PATTERN_LIST) {
//            fullBorderPattern.create(pWriter);
//        }
//
//        for (MediumBorderPattern mediumBorderPattern : SurvivalTimeUtilGenerator.MEDIUM_BORDER_PATTERN_LIST) {
//            mediumBorderPattern.create(pWriter);
//        }
//        for (SwordPattern swordPattern : SurvivalTimeUtilGenerator.SWORD_PATTERN_LIST) {
//            swordPattern.create(pWriter);
//        }
//        for (PickaxePattern pickaxePattern : SurvivalTimeUtilGenerator.PICKAXE_PATTERN_LIST) {
//            pickaxePattern.create(pWriter);
//        }
//        for (AxePattern axePattern : SurvivalTimeUtilGenerator.AXE_PATTERN_LIST) {
//            axePattern.create(pWriter);
//        }
//        for (ShovelPattern shovelPattern : SurvivalTimeUtilGenerator.SHOVEL_PATTERN_LIST) {
//            shovelPattern.create(pWriter);
//        }
//        for (HoePattern hoePattern : SurvivalTimeUtilGenerator.HOE_PATTERN_LIST) {
//            hoePattern.create(pWriter);
//        }
//
//        for (StarCenterPattern starCenterPattern : SurvivalTimeUtilGenerator.STAR_CENTER_PATTERN_LIST) {
//            starCenterPattern.create(pWriter);
//        }
//        for (MonoMaterialPattern monoMaterialPattern : SurvivalTimeUtilGenerator.MONO_MATERIAL_PATTERN_LIST) {
//            monoMaterialPattern.create(pWriter);
//        }
//
//        for (XMaterialShapelessPattern xMaterialShapelessPattern : SurvivalTimeUtilGenerator.X_MATERIALS_PATTERN_LIST) {
//            xMaterialShapelessPattern.create(pWriter);
//        }
//
//        for (HelmetHourglassPattern helmetPattern : SurvivalTimeUtilGenerator.HELMET_PATTERN_LIST) {
//            helmetPattern.create(pWriter);
//        }
//
//        for (ChestplateHourglassPattern chestplatePattern : SurvivalTimeUtilGenerator.CHESTPLATE_PATTERN_LIST) {
//            chestplatePattern.create(pWriter);
//        }
//
//        for (LeggingsHourglassPattern leggingsPattern : SurvivalTimeUtilGenerator.LEGGINGS_PATTERN_LIST) {
//            leggingsPattern.create(pWriter);
//        }
//
//        for (BootsHourglassPattern bootsPattern : SurvivalTimeUtilGenerator.BOOTS_PATTERN_LIST) {
//            bootsPattern.create(pWriter);
//        }


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
