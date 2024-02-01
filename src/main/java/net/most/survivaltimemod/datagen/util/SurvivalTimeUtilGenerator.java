package net.most.survivaltimemod.datagen.util;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import net.most.survivaltimemod.block.ModBlocks;
import net.most.survivaltimemod.block.custom.TemporalTuberCropBlock;
import net.most.survivaltimemod.data.CropBlockSeedItem;
import net.most.survivaltimemod.data.CropBlockSeedItemModel;
import net.most.survivaltimemod.datagen.pattern.*;
import net.most.survivaltimemod.item.ModItems;
import net.most.survivaltimemod.util.ShardOptions;
import net.most.survivaltimemod.util.WeightMinMax;

import java.util.List;
import java.util.Map;

public class SurvivalTimeUtilGenerator {

    ////----->>> NEEDS_X_TOOL <<<-----\\\\
    public final static List<RegistryObject<Block>> NEEDS_STONE_TOOL_LIST = List.of(
            ModBlocks.HOURGLASS_HUB_STATION,
            ModBlocks.TIME_STATION
    );
    public final static List<RegistryObject<Block>> NEEDS_IRON_TOOL_LIST = List.of(
            ModBlocks.OPAL_BLOCK,
            ModBlocks.OPAL_RAW_BLOCK,
            ModBlocks.OPAL_ORE,
            ModBlocks.DEEPSLATE_OPAL_ORE,
            ModBlocks.NETHER_OPAL_ORE,
            ModBlocks.END_STONE_OPAL_ORE,
            ModBlocks.FIERY_TIME_BLOCK,
            //raw shards blocks
            ModBlocks.RAW_CHRONA_BLOCK,
            ModBlocks.RAW_TEMPORA_BLOCK,
            ModBlocks.RAW_EPOCH_BLOCK,
            ModBlocks.RAW_FLUX_BLOCK,
            ModBlocks.RAW_LOOP_BLOCK,
            //shards blocks
            ModBlocks.CHRONA_BLOCK,
            ModBlocks.TEMPORA_BLOCK,
            ModBlocks.EPOCH_BLOCK,
            ModBlocks.FLUX_BLOCK,
            ModBlocks.LOOP_BLOCK

    );
    public final static List<RegistryObject<Block>> NEEDS_DIAMOND_TOOL_LIST = List.of(

    );

    public final static List<RegistryObject<Block>> NEEDS_NETHERITE_TOOL_LIST = List.of(

    );
    //.-.-.-.-.- needs shards tool lists
    public final static List<RegistryObject<Block>> NEEDS_OPAL_TOOL_LIST = List.of(

    );

    public final static List<RegistryObject<Block>> NEEDS_CHRONA_TOOL_LIST = List.of(

    );

    public final static List<RegistryObject<Block>> NEEDS_TEMPORA_TOOL_LIST = List.of(

    );

    public final static List<RegistryObject<Block>> NEEDS_EPOCH_TOOL_LIST = List.of(

    );

    public final static List<RegistryObject<Block>> NEEDS_FLUX_TOOL_LIST = List.of(

    );

    public final static List<RegistryObject<Block>> NEEDS_LOOP_TOOL_LIST = List.of(

    );

    ////----->>> BLOCK REGISTER MODEL <<<-----\\\\----------------------->>> BLOCK REGISTER MODEL <<<-----\\\\

    public final static List<RegistryObject<Block>> BLOCK_REGISTER_MODEL_LIST = List.of(
            ModBlocks.OPAL_BLOCK,
            ModBlocks.OPAL_RAW_BLOCK,
            ModBlocks.OPAL_ORE,
            ModBlocks.DEEPSLATE_OPAL_ORE,
            ModBlocks.NETHER_OPAL_ORE,
            ModBlocks.END_STONE_OPAL_ORE,
            ModBlocks.FIERY_TIME_BLOCK,
            //raw shards blocks
            ModBlocks.RAW_CHRONA_BLOCK,
            ModBlocks.RAW_TEMPORA_BLOCK,
            ModBlocks.RAW_EPOCH_BLOCK,
            ModBlocks.RAW_FLUX_BLOCK,
            ModBlocks.RAW_LOOP_BLOCK,
            //shards blocks
            ModBlocks.CHRONA_BLOCK,
            ModBlocks.TEMPORA_BLOCK,
            ModBlocks.EPOCH_BLOCK,
            ModBlocks.FLUX_BLOCK,
            ModBlocks.LOOP_BLOCK
    );

    public final static Map<String, Block> WITH_MODEL_REGISTER_BLOCK_ENTITY_REGISTER_BLOCK = Map.of(
            "hourglass_hub_station", ModBlocks.HOURGLASS_HUB_STATION.get()
    );

    public final static Map<String, Block> WITH_MODEL_REGISTER_SIMPLE_BLOCK_WITH_ITEM = Map.of(
            "time_station", ModBlocks.TIME_STATION.get()
    );

    public final static List<CropBlockSeedItemModel> CROP_REGISTER_MODEL_LIST = List.of(
            new CropBlockSeedItemModel((TemporalTuberCropBlock) ModBlocks.TEMPORAL_TUBER_CROP.get(),
                    "temporal_tuber_stage",
                    "temporal_tuber_stage", 7)
    );
    ////----->>> ITEM REGISTER MODEL <<<-----\\\\----------------------->>> ITEM REGISTER MODEL <<<-----\\\\
    public final static List<RegistryObject<Item>> ITEM_REGISTER_MODEL_LIST = List.of(
            ModItems.OPAL_SHARD_CHRONA,
            ModItems.OPAL_SHARD_TEMPORA,
            ModItems.OPAL_SHARD_EPOCH,
            ModItems.OPAL_SHARD_FLUX,
            ModItems.OPAL_SHARD_LOOP,
            //opal
            ModItems.OPAL_RAW,
            ModItems.OPAL_INGOT,
            //others
            ModItems.LOST_TIME_SPHERE,
            ModItems.TEMPORAL_TUBER,
            ModItems.TEMPORAL_TUBER_COOKED,
            ModItems.TEMPORAL_TUBER_ROTTEN,
            ModItems.FIERY_TIME,
            //raw
            ModItems.RAW_CHRONA,
            ModItems.RAW_TEMPORA,
            ModItems.RAW_EPOCH,
            ModItems.RAW_FLUX,
            ModItems.RAW_LOOP,
            //ingots
            ModItems.CHRONA_INGOT,
            ModItems.TEMPORA_INGOT,
            ModItems.EPOCH_INGOT,
            ModItems.FLUX_INGOT,
            ModItems.LOOP_INGOT,
            //sticks
            ModItems.CHRONA_STICK,
            ModItems.TEMPORA_STICK,
            ModItems.EPOCH_STICK,
            ModItems.FLUX_STICK,
            ModItems.LOOP_STICK,
            //seeds
            ModItems.TEMPORAL_TUBER_SEEDS,
            //lapisloopium
            ModItems.LAPISLOOPIUM,
            ModItems.IFLUX,
            ModItems.CLOCK_FRAGMENT,
            ModItems.PILE_OF_MEAT,
            ModItems.COMPACTED_MILK,
            ModItems.CHRONO_COIN,
            ModItems.LOOP_HELMET,
            ModItems.LOOP_CHESTPLATE,
            ModItems.LOOP_LEGGINGS,
            ModItems.LOOP_BOOTS,
            ModItems.FLUX_HELMET,
            ModItems.FLUX_CHESTPLATE,
            ModItems.FLUX_LEGGINGS,
            ModItems.FLUX_BOOTS,
            ModItems.EPOCH_HELMET,
            ModItems.EPOCH_CHESTPLATE,
            ModItems.EPOCH_LEGGINGS,
            ModItems.EPOCH_BOOTS,
            ModItems.TEMPORA_HELMET,
            ModItems.TEMPORA_CHESTPLATE,
            ModItems.TEMPORA_LEGGINGS,
            ModItems.TEMPORA_BOOTS,
            ModItems.CHRONA_HELMET,
            ModItems.CHRONA_CHESTPLATE,
            ModItems.CHRONA_LEGGINGS,
            ModItems.CHRONA_BOOTS
    );

    ////----->>> RECIPES <<<-----\\\\----------------------->>> RECIPES <<<-----\\\\
    public final static List<FullBorderPattern> FULL_BORDER_PATTERN_LIST = List.of(
            new FullBorderPattern(ModItems.TEMPORAL_TUBER_SEEDS.get(), 5, 20 * 60 * 2, 60 * 5, Items.WHEAT_SEEDS, Items.PUMPKIN_SEEDS, Items.MELON_SEEDS)
    );
    public final static List<MediumBorderPattern> MEDIUM_BORDER_PATTERN_LIST = List.of(

    );

    public final static List<MonoMaterialPattern> MONO_MATERIAL_PATTERN_LIST = List.of(
            new MonoMaterialPattern(ModItems.COMPACTED_MILK.get(), 1, 20 * 60 * 5, 60 * 15, Items.MILK_BUCKET, 4),
            new MonoMaterialPattern(ModItems.OPAL_INGOT.get(), 1, 20 * 60 * 10, 60 * 30, ModItems.OPAL_RAW.get()),
            new MonoMaterialPattern(ModItems.CHRONA_INGOT.get(), 1, 20 * 60 * 5, 60 * 25, ModItems.RAW_CHRONA.get()),
            new MonoMaterialPattern(ModItems.TEMPORA_INGOT.get(), 1, 20 * 60 * 4, 60 * 20, ModItems.RAW_TEMPORA.get()),
            new MonoMaterialPattern(ModItems.EPOCH_INGOT.get(), 1, 20 * 60 * 3, 60 * 15, ModItems.RAW_EPOCH.get()),
            new MonoMaterialPattern(ModItems.FLUX_INGOT.get(), 1, 20 * 60 * 2, 60 * 10, ModItems.RAW_FLUX.get()),
            new MonoMaterialPattern(ModItems.LOOP_INGOT.get(), 1, 20 * 60, 60*5, ModItems.RAW_LOOP.get()),
            new MonoMaterialPattern(ModItems.FIERY_TIME.get(), 9, 20 * 60 * 5, 60 * 5, ModBlocks.FIERY_TIME_BLOCK.get()),
            new MonoMaterialPattern(ModItems.OPAL_RAW.get(), 9, 20 * 60 * 6, 60 * 30, ModBlocks.OPAL_RAW_BLOCK.get()),
            new MonoMaterialPattern(ModItems.RAW_CHRONA.get(), 9, 20 * 60 * 5, 60 * 25, ModBlocks.RAW_CHRONA_BLOCK.get()),
            new MonoMaterialPattern(ModItems.RAW_TEMPORA.get(), 9, 20 * 60 * 4, 60 * 20, ModBlocks.RAW_TEMPORA_BLOCK.get()),
            new MonoMaterialPattern(ModItems.RAW_EPOCH.get(), 9, 20 * 60 * 3, 60 * 15, ModBlocks.RAW_EPOCH_BLOCK.get()),
            new MonoMaterialPattern(ModItems.RAW_FLUX.get(), 9, 20 * 60 * 2, 60 * 10, ModBlocks.RAW_FLUX_BLOCK.get()),
            new MonoMaterialPattern(ModItems.RAW_LOOP.get(), 9, 20 * 60, 60 *5, ModBlocks.RAW_LOOP_BLOCK.get()),
            new MonoMaterialPattern(ModItems.OPAL_INGOT.get(), 9, 20 * 60 * 7, 60 * 35, ModBlocks.OPAL_BLOCK.get()),
            new MonoMaterialPattern(ModItems.CHRONA_INGOT.get(), 9, 20 * 60 * 6, 60 * 30, ModBlocks.CHRONA_BLOCK.get()),
            new MonoMaterialPattern(ModItems.TEMPORA_INGOT.get(), 9, 20 * 60 * 5, 60 * 25, ModBlocks.TEMPORA_BLOCK.get()),
            new MonoMaterialPattern(ModItems.EPOCH_INGOT.get(), 9, 20 * 60 * 4, 60 * 20, ModBlocks.EPOCH_BLOCK.get()),
            new MonoMaterialPattern(ModItems.FLUX_INGOT.get(), 9, 20 * 60 * 3, 60 * 15, ModBlocks.FLUX_BLOCK.get()),
            new MonoMaterialPattern(ModItems.LOOP_INGOT.get(), 9, 20 * 60 * 2, 60 * 10, ModBlocks.LOOP_BLOCK.get())
    );
    public final static List<BlockPattern> BLOCK_PATTERN_LIST = List.of(
            new BlockPattern(ModBlocks.FIERY_TIME_BLOCK.get(),20 * 60 * 6, 60 * 10, ModItems.FIERY_TIME.get()),
            new BlockPattern(ModBlocks.OPAL_RAW_BLOCK.get(),20 * 60 * 10, 60 * 40, ModItems.OPAL_RAW.get()),
            new BlockPattern(ModBlocks.RAW_CHRONA_BLOCK.get(),20 * 60 * 7, 60 * 35, ModItems.RAW_CHRONA.get()),
            new BlockPattern(ModBlocks.RAW_TEMPORA_BLOCK.get(),20 * 60 * 5, 60 * 30, ModItems.RAW_TEMPORA.get()),
            new BlockPattern(ModBlocks.RAW_EPOCH_BLOCK.get(),20 * 60 * 4, 60 * 25, ModItems.RAW_EPOCH.get()),
            new BlockPattern(ModBlocks.RAW_FLUX_BLOCK.get(),20 * 60 * 3, 60 * 20, ModItems.RAW_FLUX.get()),
            new BlockPattern(ModBlocks.RAW_LOOP_BLOCK.get(),20 * 60 * 2, 60 * 10, ModItems.RAW_LOOP.get()),
            new BlockPattern(ModBlocks.OPAL_BLOCK.get(),20 * 60 * 12, 60 * 45, ModItems.OPAL_INGOT.get()),
            new BlockPattern(ModBlocks.CHRONA_BLOCK.get(),20 * 60 * 10, 60 * 40, ModItems.CHRONA_INGOT.get()),
            new BlockPattern(ModBlocks.TEMPORA_BLOCK.get(),20 * 60 * 8, 60 * 35, ModItems.TEMPORA_INGOT.get()),
            new BlockPattern(ModBlocks.EPOCH_BLOCK.get(),20 * 60 * 7, 60 * 30, ModItems.EPOCH_INGOT.get()),
            new BlockPattern(ModBlocks.FLUX_BLOCK.get(),20 * 60 * 6, 60 * 25, ModItems.FLUX_INGOT.get()),
            new BlockPattern(ModBlocks.LOOP_BLOCK.get(),20 * 60 * 5, 60 * 20, ModItems.LOOP_INGOT.get())
    );

    public final static List<XMaterialShapelessPattern> X_MATERIALS_PATTERN_LIST = List.of(
            new XMaterialShapelessPattern(ModItems.IFLUX.get(), 3, 20 * 60, 60 * 3, ModItems.OPAL_SHARD_FLUX.get(), Items.IRON_INGOT),
            new XMaterialShapelessPattern(ModItems.LAPISLOOPIUM.get(), 3, 20 * 60 + 20 * 30, 60 * 3, ModItems.OPAL_SHARD_LOOP.get(), Items.LAPIS_LAZULI)
    );

    public final static List<SwordPattern> SWORD_PATTERN_LIST = List.of(
            new SwordPattern(ModItems.LOOP_SWORD.get(), 20 * 60 * 30, 60 * 60, ModItems.LOOP_STICK.get(), ModBlocks.LOOP_BLOCK.get(), ModItems.LOOP_INGOT.get(), Items.NETHERITE_SWORD),
            new SwordPattern(ModItems.FLUX_SWORD.get(), 20 * 60 * 40, 60 * 60 * 2, ModItems.FLUX_STICK.get(), ModBlocks.FLUX_BLOCK.get(), ModItems.FLUX_INGOT.get(), ModItems.LOOP_SWORD.get()),
            new SwordPattern(ModItems.EPOCH_SWORD.get(), 20 * 60 * 50, 60 * 60 * 3, ModItems.EPOCH_STICK.get(), ModBlocks.EPOCH_BLOCK.get(), ModItems.EPOCH_INGOT.get(), ModItems.FLUX_SWORD.get()),
            new SwordPattern(ModItems.TEMPORA_SWORD.get(), 20 * 60 * 60, 60 * 60 * 4, ModItems.TEMPORA_STICK.get(), ModBlocks.TEMPORA_BLOCK.get(), ModItems.TEMPORA_INGOT.get(), ModItems.EPOCH_SWORD.get()),
            new SwordPattern(ModItems.CHRONA_SWORD.get(), 20 * 60 * 80, 60 * 60 * 5, ModItems.CHRONA_STICK.get(), ModBlocks.CHRONA_BLOCK.get(), ModItems.CHRONA_INGOT.get(), ModItems.TEMPORA_SWORD.get())
    );

    public final static List<PickaxePattern> PICKAXE_PATTERN_LIST = List.of(
            new PickaxePattern(ModItems.LOOP_PICKAXE.get(),20 * 60 * 30, 60 * 60, ModItems.LOOP_STICK.get(), ModBlocks.LOOP_BLOCK.get(), ModItems.LOOP_INGOT.get(), Items.NETHERITE_PICKAXE),
            new PickaxePattern(ModItems.FLUX_PICKAXE.get(),20 * 60 * 40, 60 * 60 * 2, ModItems.FLUX_STICK.get(), ModBlocks.FLUX_BLOCK.get(), ModItems.FLUX_INGOT.get(), ModItems.LOOP_PICKAXE.get()),
            new PickaxePattern(ModItems.EPOCH_PICKAXE.get(),20 * 60 * 50, 60 * 60 * 3, ModItems.EPOCH_STICK.get(), ModBlocks.EPOCH_BLOCK.get(), ModItems.EPOCH_INGOT.get(), ModItems.FLUX_PICKAXE.get()),
            new PickaxePattern(ModItems.TEMPORA_PICKAXE.get(), 20 * 60 * 60, 60 * 60 * 4, ModItems.TEMPORA_STICK.get(), ModBlocks.TEMPORA_BLOCK.get(), ModItems.TEMPORA_INGOT.get(), ModItems.EPOCH_PICKAXE.get()),
            new PickaxePattern(ModItems.CHRONA_PICKAXE.get(), 20 * 60 * 80, 60 * 60 * 5, ModItems.CHRONA_STICK.get(), ModBlocks.CHRONA_BLOCK.get(), ModItems.CHRONA_INGOT.get(), ModItems.TEMPORA_PICKAXE.get())
    );

    public final static List<AxePattern> AXE_PATTERN_LIST = List.of(
            new AxePattern(ModItems.LOOP_AXE.get(), 20 * 60 * 30, 60 * 60, ModItems.LOOP_STICK.get(), ModBlocks.LOOP_BLOCK.get(), ModItems.LOOP_INGOT.get(), Items.NETHERITE_AXE),
            new AxePattern(ModItems.FLUX_AXE.get(), 20 * 60 * 40, 60 * 60 * 2, ModItems.FLUX_STICK.get(), ModBlocks.FLUX_BLOCK.get(), ModItems.FLUX_INGOT.get(), ModItems.LOOP_AXE.get()),
            new AxePattern(ModItems.EPOCH_AXE.get(), 20 * 60 * 50, 60 * 60 * 3, ModItems.EPOCH_STICK.get(), ModBlocks.EPOCH_BLOCK.get(), ModItems.EPOCH_INGOT.get(), ModItems.FLUX_AXE.get()),
            new AxePattern(ModItems.TEMPORA_AXE.get(),20 * 60 * 60, 60 * 60 * 4, ModItems.TEMPORA_STICK.get(), ModBlocks.TEMPORA_BLOCK.get(), ModItems.TEMPORA_INGOT.get(), ModItems.EPOCH_AXE.get()),
            new AxePattern(ModItems.CHRONA_AXE.get(),20 * 60 * 80, 60 * 60 * 5, ModItems.CHRONA_STICK.get(), ModBlocks.CHRONA_BLOCK.get(), ModItems.CHRONA_INGOT.get(), ModItems.TEMPORA_AXE.get())
    );

    public final static List<ShovelPattern> SHOVEL_PATTERN_LIST = List.of(
            new ShovelPattern(ModItems.LOOP_SHOVEL.get(),20 * 60 * 30, 60 * 60, ModItems.LOOP_STICK.get(),ModBlocks.LOOP_BLOCK.get(), ModItems.LOOP_INGOT.get(), Items.NETHERITE_SHOVEL),
            new ShovelPattern(ModItems.FLUX_SHOVEL.get(),20 * 60 * 40, 60 * 60 * 2, ModItems.FLUX_STICK.get(), ModBlocks.FLUX_BLOCK.get(), ModItems.FLUX_INGOT.get(), ModItems.LOOP_SHOVEL.get()),
            new ShovelPattern(ModItems.EPOCH_SHOVEL.get(),20 * 60 * 50, 60 * 60 * 3, ModItems.EPOCH_STICK.get(), ModBlocks.EPOCH_BLOCK.get(), ModItems.EPOCH_INGOT.get(), ModItems.FLUX_SHOVEL.get()),
            new ShovelPattern(ModItems.TEMPORA_SHOVEL.get(), 20 * 60 * 60, 60 * 60 * 4, ModItems.TEMPORA_STICK.get(), ModBlocks.TEMPORA_BLOCK.get(), ModItems.TEMPORA_INGOT.get(), ModItems.EPOCH_SHOVEL.get()),
            new ShovelPattern(ModItems.CHRONA_SHOVEL.get(), 20 * 60 * 80, 60 * 60 * 5, ModItems.CHRONA_STICK.get(), ModBlocks.CHRONA_BLOCK.get(), ModItems.CHRONA_INGOT.get(), ModItems.TEMPORA_SHOVEL.get())
    );

    public final static List<HoePattern> HOE_PATTERN_LIST = List.of(
            new HoePattern(ModItems.LOOP_HOE.get(),20 * 60 * 30,60 * 60, ModItems.LOOP_STICK.get(), ModBlocks.LOOP_BLOCK.get(), ModItems.LOOP_INGOT.get(), Items.NETHERITE_HOE),
            new HoePattern(ModItems.FLUX_HOE.get(),20 * 60 * 40,60 * 60 * 2, ModItems.FLUX_STICK.get(), ModBlocks.FLUX_BLOCK.get(), ModItems.FLUX_INGOT.get(), ModItems.LOOP_HOE.get()),
            new HoePattern(ModItems.EPOCH_HOE.get(),20 * 60 * 50,60 * 60 * 3, ModItems.EPOCH_STICK.get(), ModBlocks.EPOCH_BLOCK.get(), ModItems.EPOCH_INGOT.get(), ModItems.FLUX_HOE.get()),
            new HoePattern(ModItems.TEMPORA_HOE.get(), 20 * 60 * 60,60 * 60 * 4, ModItems.TEMPORA_STICK.get(), ModBlocks.TEMPORA_BLOCK.get(), ModItems.TEMPORA_INGOT.get(), ModItems.EPOCH_HOE.get()),
            new HoePattern(ModItems.CHRONA_HOE.get(), 20 * 60 * 80, 60 * 60 * 5, ModItems.CHRONA_STICK.get(), ModBlocks.CHRONA_BLOCK.get(), ModItems.CHRONA_INGOT.get(), ModItems.TEMPORA_HOE.get())
    );

    public final static List<StarCenterPattern> STAR_CENTER_PATTERN_LIST = List.of(
            new StarCenterPattern(ModItems.CURSE_ITEM.get(), 1, 20 * 60 * 5, 60 * 15, ModItems.PILE_OF_MEAT.get(), ModItems.COMPACTED_MILK.get()),
            new StarCenterPattern(ModItems.RAW_LOOP.get(), 1 , 20 * 30, 60 * 7, ModItems.OPAL_SHARD_LOOP.get() , Items.CLOCK),
            new StarCenterPattern(ModItems.RAW_FLUX.get(), 1 , 20 * 30 * 2, 60 * 10, ModItems.OPAL_SHARD_FLUX.get(), ModItems.RAW_LOOP.get()),
            new StarCenterPattern(ModItems.RAW_EPOCH.get(), 1 , 20 * 30 * 3, 60 * 13, ModItems.OPAL_SHARD_EPOCH.get(), ModItems.RAW_FLUX.get()),
            new StarCenterPattern(ModItems.RAW_TEMPORA.get(), 1 , 20 * 30 * 4, 60 * 16, ModItems.OPAL_SHARD_TEMPORA.get(), ModItems.RAW_EPOCH.get()),
            new StarCenterPattern(ModItems.RAW_CHRONA.get(), 1 , 20 * 30 * 5, 60 * 20, ModItems.OPAL_SHARD_CHRONA.get(), ModItems.RAW_TEMPORA.get()),
            new StarCenterPattern(ModItems.LOOP_STICK.get(), 1, 20 * 10, 60 * 5, ModItems.OPAL_SHARD_LOOP.get(), Items.STICK),
            new StarCenterPattern(ModItems.FLUX_STICK.get(), 1, 20 * 10 * 2, 60 * 6, ModItems.OPAL_SHARD_FLUX.get(), ModItems.LOOP_STICK.get()),
            new StarCenterPattern(ModItems.EPOCH_STICK.get(), 1, 20 * 10 * 3, 60 * 7, ModItems.OPAL_SHARD_EPOCH.get(), ModItems.FLUX_STICK.get()),
            new StarCenterPattern(ModItems.TEMPORA_STICK.get(), 1, 20 * 10 * 4, 60 * 8, ModItems.OPAL_SHARD_TEMPORA.get(), ModItems.EPOCH_STICK.get()),
            new StarCenterPattern(ModItems.CHRONA_STICK.get(), 1, 20 * 10 * 5, 60 * 10, ModItems.OPAL_SHARD_CHRONA.get(), ModItems.TEMPORA_STICK.get())
    );

    public final static List<HelmetHourglassPattern> HELMET_PATTERN_LIST = List.of(
            new HelmetHourglassPattern(ModItems.LOOP_HELMET.get(), 1, 20 * 60 * 10, 60 * 30, ModBlocks.LOOP_BLOCK.get(),ModItems.LOOP_INGOT.get(), Items.NETHERITE_HELMET),
            new HelmetHourglassPattern(ModItems.FLUX_HELMET.get(), 1, 20 * 60 * 15, 60 * 35, ModBlocks.FLUX_BLOCK.get(), ModItems.FLUX_INGOT.get(), ModItems.LOOP_HELMET.get()),
            new HelmetHourglassPattern(ModItems.EPOCH_HELMET.get(), 1, 20 * 60 * 20, 60 * 40, ModBlocks.EPOCH_BLOCK.get(), ModItems.EPOCH_INGOT.get(), ModItems.FLUX_HELMET.get()),
            new HelmetHourglassPattern(ModItems.TEMPORA_HELMET.get(), 1, 20 * 60 * 25, 60 * 45, ModBlocks.TEMPORA_BLOCK.get(), ModItems.TEMPORA_INGOT.get(), ModItems.EPOCH_HELMET.get()),
            new HelmetHourglassPattern(ModItems.CHRONA_HELMET.get(), 1, 20 * 60 * 30,60 * 50,  ModBlocks.CHRONA_BLOCK.get(), ModItems.CHRONA_INGOT.get(), ModItems.TEMPORA_HELMET.get())
    );

    public final static List<ChestplateHourglassPattern> CHESTPLATE_PATTERN_LIST = List.of(
            new ChestplateHourglassPattern(ModItems.LOOP_CHESTPLATE.get(), 1, 20 * 60 * 30, 60 * 60, ModBlocks.LOOP_BLOCK.get(),ModItems.LOOP_INGOT.get(), Items.NETHERITE_CHESTPLATE),
            new ChestplateHourglassPattern(ModItems.FLUX_CHESTPLATE.get(), 1, 20 * 60 * 45, 60 * 60 * 2, ModBlocks.FLUX_BLOCK.get(), ModItems.FLUX_INGOT.get(), ModItems.LOOP_CHESTPLATE.get()),
            new ChestplateHourglassPattern(ModItems.EPOCH_CHESTPLATE.get(), 1, 20 * 60 * 50, 60 * 60 * 3, ModBlocks.EPOCH_BLOCK.get(), ModItems.EPOCH_INGOT.get(), ModItems.FLUX_CHESTPLATE.get()),
            new ChestplateHourglassPattern(ModItems.TEMPORA_CHESTPLATE.get(), 1, 20 * 60 * 55, 60 * 60 * 4, ModBlocks.TEMPORA_BLOCK.get(), ModItems.TEMPORA_INGOT.get(), ModItems.EPOCH_CHESTPLATE.get()),
            new ChestplateHourglassPattern(ModItems.CHRONA_CHESTPLATE.get(), 1, 20 * 60 * 60,60 * 60 * 5,  ModBlocks.CHRONA_BLOCK.get(), ModItems.CHRONA_INGOT.get(), ModItems.TEMPORA_CHESTPLATE.get())
    );

    public final static List<LeggingsHourglassPattern> LEGGINGS_PATTERN_LIST = List.of(
            new LeggingsHourglassPattern(ModItems.LOOP_LEGGINGS.get(), 1, 20 * 60 * 25, 60 * 40, ModBlocks.LOOP_BLOCK.get(),ModItems.LOOP_INGOT.get(), Items.NETHERITE_LEGGINGS),
            new LeggingsHourglassPattern(ModItems.FLUX_LEGGINGS.get(), 1, 20 * 60 * 27, 60 * 45, ModBlocks.FLUX_BLOCK.get(), ModItems.FLUX_INGOT.get(), ModItems.LOOP_LEGGINGS.get()),
            new LeggingsHourglassPattern(ModItems.EPOCH_LEGGINGS.get(), 1, 20 * 60 * 30, 60 * 50, ModBlocks.EPOCH_BLOCK.get(), ModItems.EPOCH_INGOT.get(), ModItems.FLUX_LEGGINGS.get()),
            new LeggingsHourglassPattern(ModItems.TEMPORA_LEGGINGS.get(), 1, 20 * 60 * 35, 60 * 55, ModBlocks.TEMPORA_BLOCK.get(), ModItems.TEMPORA_INGOT.get(), ModItems.EPOCH_LEGGINGS.get()),
            new LeggingsHourglassPattern(ModItems.CHRONA_LEGGINGS.get(), 1, 20 * 60 * 40,60 * 60,  ModBlocks.CHRONA_BLOCK.get(), ModItems.CHRONA_INGOT.get(), ModItems.TEMPORA_LEGGINGS.get())
    );

    public final static List<BootsHourglassPattern> BOOTS_PATTERN_LIST = List.of(
            new BootsHourglassPattern(ModItems.LOOP_BOOTS.get(), 1, 20 * 60 * 10, 60 * 30, ModBlocks.LOOP_BLOCK.get(),ModItems.LOOP_INGOT.get(), Items.NETHERITE_BOOTS),
            new BootsHourglassPattern(ModItems.FLUX_BOOTS.get(), 1, 20 * 60 * 15, 60 * 35, ModBlocks.FLUX_BLOCK.get(), ModItems.FLUX_INGOT.get(), ModItems.LOOP_BOOTS.get()),
            new BootsHourglassPattern(ModItems.EPOCH_BOOTS.get(), 1, 20 * 60 * 20, 60 * 40, ModBlocks.EPOCH_BLOCK.get(), ModItems.EPOCH_INGOT.get(), ModItems.FLUX_BOOTS.get()),
            new BootsHourglassPattern(ModItems.TEMPORA_BOOTS.get(), 1, 20 * 60 * 25, 60 * 45, ModBlocks.TEMPORA_BLOCK.get(), ModItems.TEMPORA_INGOT.get(), ModItems.EPOCH_BOOTS.get()),
            new BootsHourglassPattern(ModItems.CHRONA_BOOTS.get(), 1, 20 * 60 * 30,60 * 50,  ModBlocks.CHRONA_BLOCK.get(), ModItems.CHRONA_INGOT.get(), ModItems.TEMPORA_BOOTS.get())
    );

    ////----->>> LOOT TABLES <<<-----\\\\----------------------->>> LOOT TABLES <<<-----\\\\
    public final static List<RegistryObject<Block>> DROP_SELF_BLOCKS_LOOT_TABLE_LIST = List.of(
            ModBlocks.OPAL_BLOCK,
            ModBlocks.OPAL_RAW_BLOCK,
            ModBlocks.RAW_CHRONA_BLOCK,
            ModBlocks.RAW_TEMPORA_BLOCK,
            ModBlocks.RAW_EPOCH_BLOCK,
            ModBlocks.RAW_FLUX_BLOCK,
            ModBlocks.RAW_LOOP_BLOCK,
            ModBlocks.CHRONA_BLOCK,
            ModBlocks.TEMPORA_BLOCK,
            ModBlocks.EPOCH_BLOCK,
            ModBlocks.FLUX_BLOCK,
            ModBlocks.LOOP_BLOCK,
            ModBlocks.HOURGLASS_HUB_STATION,
            ModBlocks.TIME_STATION
    );

    public final static List<CropBlockSeedItem> CROP_BLOCK_SEED_ITEM_LIST = List.of(
            new CropBlockSeedItem(ModBlocks.TEMPORAL_TUBER_CROP.get(), ModItems.TEMPORAL_TUBER_SEEDS.get(),
                    ModItems.TEMPORAL_TUBER_ROTTEN.get(), ModItems.TEMPORAL_TUBER.get(), TemporalTuberCropBlock.AGE,
                    TemporalTuberCropBlock.MAX_AGE)
    );
    public final static Map<RegistryObject<Block>, ShardOptions> OPAL_ORE_LOOT_TABLE_MAP = Map.of(
            ModBlocks.OPAL_ORE, new ShardOptions(
                    new WeightMinMax(5, 1.0f, 2.0f),
                    new WeightMinMax(4, 1.0f, 3.0f),
                    new WeightMinMax(3, 2.0f, 3.0f),
                    new WeightMinMax(2, 1.0f, 4.0f),
                    new WeightMinMax(1, 1.0f, 3.0f)
            ),
            ModBlocks.DEEPSLATE_OPAL_ORE, new ShardOptions(
                    new WeightMinMax(5, 2.0f, 3.0f),
                    new WeightMinMax(4, 2.0f, 3.0f),
                    new WeightMinMax(3, 2.0f, 4.0f),
                    new WeightMinMax(2, 2.0f, 4.0f),
                    new WeightMinMax(1, 2.0f, 3.0f)
            ),
            ModBlocks.NETHER_OPAL_ORE, new ShardOptions(
                    new WeightMinMax(2, 2.0f, 3.0f),
                    new WeightMinMax(3, 2.0f, .0f),
                    new WeightMinMax(5, 2.0f, 4.0f),
                    new WeightMinMax(3, 2.0f, 4.0f),
                    new WeightMinMax(1, 1.0f, 5.0f)
            ),
            ModBlocks.END_STONE_OPAL_ORE, new ShardOptions(
                    new WeightMinMax(3, 2.0f, 5.0f),
                    new WeightMinMax(5, 1.0f, 3.0f),
                    new WeightMinMax(3, 2.0f, 5.0f),
                    new WeightMinMax(5, 1.0f, 3.0f),
                    new WeightMinMax(4, 1.0f, 2.0f)
            )
    );


}
