package net.most.survivaltimemod.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.most.survivaltimemod.SurvivalTimeMod;
import net.most.survivaltimemod.block.ModBlocks;

import java.util.List;

public class ModConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_OPAL_ORE_KEY = registerKey("opal_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_OPAL_ORE_KEY = registerKey("nether_opal_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> END_OPAL_ORE_KEY = registerKey("end_opal_ore");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceabeles = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceabeles = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherrackReplaceabeles = new BlockMatchTest(Blocks.NETHERRACK);
        RuleTest endReplaceabeles = new BlockMatchTest(Blocks.END_STONE);

        List<OreConfiguration.TargetBlockState> overworldOpalOres = List.of(OreConfiguration.target(stoneReplaceabeles,
                        ModBlocks.OPAL_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceabeles, ModBlocks.DEEPSLATE_OPAL_ORE.get().defaultBlockState()));


        register(context, OVERWORLD_OPAL_ORE_KEY, Feature.ORE, new OreConfiguration(overworldOpalOres, 10));
        register(context, NETHER_OPAL_ORE_KEY, Feature.ORE, new OreConfiguration(netherrackReplaceabeles,
                ModBlocks.NETHER_OPAL_ORE.get().defaultBlockState(), 7));
        register(context, END_OPAL_ORE_KEY, Feature.ORE, new OreConfiguration(endReplaceabeles,
                ModBlocks.END_STONE_OPAL_ORE.get().defaultBlockState(), 6));


    }


    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(SurvivalTimeMod.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature,
                                                                                          FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
