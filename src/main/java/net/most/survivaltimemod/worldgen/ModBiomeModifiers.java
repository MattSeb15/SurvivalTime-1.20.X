package net.most.survivaltimemod.worldgen;


import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.holdersets.AnyHolderSet;
import net.minecraftforge.registries.holdersets.HolderSetType;
import net.most.survivaltimemod.SurvivalTimeMod;
import net.most.survivaltimemod.entity.ModEntities;

import java.util.List;

public class ModBiomeModifiers {


    public static final ResourceKey<BiomeModifier> ADD_OPAL_ORE = registerKey("add_opal_ore");
    public static final ResourceKey<BiomeModifier> ADD_NETHER_OPAL_ORE = registerKey("add_nether_opal_ore");
    public static final ResourceKey<BiomeModifier> ADD_END_OPAL_ORE = registerKey("add_end_opal_ore");
    public static final ResourceKey<BiomeModifier> SPAWN_TIMEKEEPER = registerKey("spawn_timekeeper");
    public static final ResourceKey<BiomeModifier> SPAWN_TIME_DEVOURER = registerKey("spawn_time_devourer");
    public static final ResourceKey<BiomeModifier> SPAWN_TIME_DEVOURER_NETHER = registerKey("spawn_time_devourer_nether");
    public static final ResourceKey<BiomeModifier> SPAWN_TIMMY = registerKey("spawn_timmy");
    public static final ResourceKey<BiomeModifier> SPAWN_GHOST_WITCH = registerKey("spawn_ghost_witch");
    public static final ResourceKey<BiomeModifier> SPAWN_GHOST_WITCH_NETHER = registerKey("spawn_ghost_witch_nether");

    public static void bootstrap(BootstapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(ADD_OPAL_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.OPAL_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_NETHER_OPAL_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_NETHER),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.NETHER_OPAL_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_END_OPAL_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_END),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.END_OPAL_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(SPAWN_TIMEKEEPER, new ForgeBiomeModifiers.AddSpawnsBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.IS_DESERT),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.TIMEKEEPER.get(), 95, 1, 2))
        ));


        context.register(SPAWN_TIME_DEVOURER, new ForgeBiomeModifiers.AddSpawnsBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.TIME_DEVOURER.get(), 90, 2, 3))
        ));

        context.register(SPAWN_TIME_DEVOURER_NETHER, new ForgeBiomeModifiers.AddSpawnsBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_NETHER),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.TIME_DEVOURER.get(), 90, 2, 4))
        ));

        context.register(SPAWN_TIMMY, new ForgeBiomeModifiers.AddSpawnsBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_JUNGLE),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.TIMMY.get(), 60, 1, 2))
        ));

        context.register(SPAWN_GHOST_WITCH, new ForgeBiomeModifiers.AddSpawnsBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.GHOST_WITCH.get(), 65, 1, 2))
        ));

        context.register(SPAWN_GHOST_WITCH_NETHER, new ForgeBiomeModifiers.AddSpawnsBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_NETHER),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.GHOST_WITCH.get(), 70, 2, 4))
        ));

    }


    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(SurvivalTimeMod.MOD_ID, name));
    }
}
