package net.most.survivaltimemod.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;
import net.most.survivaltimemod.SurvivalTimeMod;
import net.most.survivaltimemod.util.ModTags;

import java.util.List;

public class ModToolTiers {
    //shards tiers
    public static final Tier OPAL = TierSortingRegistry.registerTier(
            new ForgeTier(
                    10,
                    6050,
                    15.0f,
                    10.0f,
                    30,
                    ModTags.Blocks.NEEDS_OPAL_TOOL,
                    () -> Ingredient.of(ModItems.OPAL_INGOT.get())
            ),
            new ResourceLocation(SurvivalTimeMod.MOD_ID, "opal_ingot"),
            List.of(Tiers.NETHERITE),
            List.of()
    );
    public static final Tier CHRONA = TierSortingRegistry.registerTier(
            new ForgeTier(
                    9,
                    4550,
                    14.0f,
                    9.0f,
                    20,
                    ModTags.Blocks.NEEDS_CHRONA_TOOL,
                    () -> Ingredient.of(ModItems.CHRONA_INGOT.get())
            ),
            new ResourceLocation(SurvivalTimeMod.MOD_ID, "chrona_ingot"),
            List.of(Tiers.NETHERITE),
            List.of()
    );

    public static final Tier TEMPORA = TierSortingRegistry.registerTier(
            new ForgeTier(
                    8,
                    4050,
                    12.5f,
                    8.5f,
                    17,
                    ModTags.Blocks.NEEDS_TEMPORA_TOOL,
                    () -> Ingredient.of(ModItems.TEMPORA_INGOT.get())
            ),
            new ResourceLocation(SurvivalTimeMod.MOD_ID, "tempora_ingot"),
            List.of(Tiers.NETHERITE),
            List.of()
    );

    public static final Tier EPOCH = TierSortingRegistry.registerTier(
            new ForgeTier(
                    7,
                    3050,
                    12.0f,
                    8.0f,
                    17,
                    ModTags.Blocks.NEEDS_EPOCH_TOOL,
                    () -> Ingredient.of(ModItems.EPOCH_INGOT.get())
            ),
            new ResourceLocation(SurvivalTimeMod.MOD_ID, "epoch_ingot"),
            List.of(Tiers.NETHERITE),
            List.of()

    );

    public static final Tier FLUX = TierSortingRegistry.registerTier(
            new ForgeTier(
                    6,
                    2550,
                    11.0f,
                    7.0f,
                    16,
                    ModTags.Blocks.NEEDS_FLUX_TOOL,
                    () -> Ingredient.of(ModItems.FLUX_INGOT.get())
            ),
            new ResourceLocation(SurvivalTimeMod.MOD_ID, "flux_ingot"),
            List.of(Tiers.NETHERITE),
            List.of()
    );

    public static final Tier LOOP = TierSortingRegistry.registerTier(
            new ForgeTier(
                    5,
                    2050,
                    10.0f,
                    6.0f,
                    16,
                    ModTags.Blocks.NEEDS_LOOP_TOOL,
                    () -> Ingredient.of(ModItems.LOOP_INGOT.get())
            ),
            new ResourceLocation(SurvivalTimeMod.MOD_ID, "loop_ingot"),
            List.of(Tiers.NETHERITE),
            List.of()
    );


}
