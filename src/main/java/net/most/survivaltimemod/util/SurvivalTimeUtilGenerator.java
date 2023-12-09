package net.most.survivaltimemod.util;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import net.most.survivaltimemod.block.ModBlocks;
import net.most.survivaltimemod.block.custom.TemporalTuberCropBlock;
import net.most.survivaltimemod.data.CropBlockSeedItem;
import net.most.survivaltimemod.data.CropBlockSeedItemModel;
import net.most.survivaltimemod.item.ModItems;

import java.util.List;
import java.util.Map;

public class SurvivalTimeUtilGenerator {

    ////----->>> NEEDS_X_TOOL <<<-----\\\\
    public final static List<RegistryObject<Block>> NEEDS_STONE_TOOL_LIST = List.of(

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

    public final static List<CropBlockSeedItemModel> CROP_REGISTER_MODEL_LIST = List.of(
            new CropBlockSeedItemModel((TemporalTuberCropBlock) ModBlocks.TEMPORAL_TUBER_CROP.get(),
                    "temporal_tuber_stage",
                    "temporal_tuber_stage", 6)
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
            ModItems.TEMPORAL_TUBER_SEEDS
    );

    ////----->>> RECIPES <<<-----\\\\----------------------->>> RECIPES <<<-----\\\\
    public final static Map<ItemLike, ExpCookTimeGroupItem> SMELTING_RECIPE_MAP = Map.of(
            ModItems.OPAL_RAW.get(), new ExpCookTimeGroupItem(4.0f, 1200, "survivaltimemod_ingot",
                    ModItems.OPAL_INGOT.get()),
            ModItems.RAW_CHRONA.get(), new ExpCookTimeGroupItem(3.5f, 800, "survivaltimemod_ingot",
                    ModItems.CHRONA_INGOT.get()),
            ModItems.RAW_TEMPORA.get(), new ExpCookTimeGroupItem(3.2f, 700, "survivaltimemod_ingot",
                    ModItems.TEMPORA_INGOT.get()),
            ModItems.RAW_EPOCH.get(), new ExpCookTimeGroupItem(3.0f, 600, "survivaltimemod_ingot",
                    ModItems.EPOCH_INGOT.get()),
            ModItems.RAW_FLUX.get(), new ExpCookTimeGroupItem(2.5f, 400, "survivaltimemod_ingot",
                    ModItems.FLUX_INGOT.get()),
            ModItems.RAW_LOOP.get(), new ExpCookTimeGroupItem(2.2f, 300, "survivaltimemod_ingot",
                    ModItems.LOOP_INGOT.get())

    );

    public final static Map<ItemLike, ExpCookTimeGroupItem> BLASTING_RECIPE_MAP = Map.of(
            ModItems.OPAL_RAW.get(), new ExpCookTimeGroupItem(2.7f, 600, "survivaltimemod_ingot",
                    ModItems.OPAL_INGOT.get()),
            ModItems.RAW_CHRONA.get(), new ExpCookTimeGroupItem(1.75f, 400, "survivaltimemod_ingot",
                    ModItems.CHRONA_INGOT.get()),
            ModItems.RAW_TEMPORA.get(), new ExpCookTimeGroupItem(1.6f, 350, "survivaltimemod_ingot",
                    ModItems.TEMPORA_INGOT.get()),
            ModItems.RAW_EPOCH.get(), new ExpCookTimeGroupItem(1.5f, 300, "survivaltimemod_ingot",
                    ModItems.EPOCH_INGOT.get()),
            ModItems.RAW_FLUX.get(), new ExpCookTimeGroupItem(1.25f, 200, "survivaltimemod_ingot",
                    ModItems.FLUX_INGOT.get()),
            ModItems.RAW_LOOP.get(), new ExpCookTimeGroupItem(1.1f, 150, "survivaltimemod_ingot",
                    ModItems.LOOP_INGOT.get())
    );

    public final static Map<ItemLike, ItemLike> RAW_SHARDS_RECIPE_MAP = Map.of(
            ModItems.RAW_CHRONA.get(), ModItems.OPAL_SHARD_CHRONA.get(),
            ModItems.RAW_TEMPORA.get(), ModItems.OPAL_SHARD_TEMPORA.get(),
            ModItems.RAW_EPOCH.get(), ModItems.OPAL_SHARD_EPOCH.get(),
            ModItems.RAW_FLUX.get(), ModItems.OPAL_SHARD_FLUX.get(),
            ModItems.RAW_LOOP.get(), ModItems.OPAL_SHARD_LOOP.get()
    );

    public final static Map<ItemLike, ItemLike> BLOCK_FROM_ITEM_RECIPE_MAP = Map.ofEntries(
            Map.entry(ModBlocks.OPAL_BLOCK.get(), ModItems.OPAL_INGOT.get()),
            Map.entry(ModBlocks.OPAL_RAW_BLOCK.get(), ModItems.OPAL_RAW.get()),
            Map.entry(ModBlocks.FIERY_TIME_BLOCK.get(), ModItems.FIERY_TIME.get()),
            Map.entry(ModBlocks.RAW_CHRONA_BLOCK.get(), ModItems.RAW_CHRONA.get()),
            Map.entry(ModBlocks.RAW_TEMPORA_BLOCK.get(), ModItems.RAW_TEMPORA.get()),
            Map.entry(ModBlocks.RAW_EPOCH_BLOCK.get(), ModItems.RAW_EPOCH.get()),
            Map.entry(ModBlocks.RAW_FLUX_BLOCK.get(), ModItems.RAW_FLUX.get()),
            Map.entry(ModBlocks.RAW_LOOP_BLOCK.get(), ModItems.RAW_LOOP.get()),
            Map.entry(ModBlocks.CHRONA_BLOCK.get(), ModItems.CHRONA_INGOT.get()),
            Map.entry(ModBlocks.TEMPORA_BLOCK.get(), ModItems.TEMPORA_INGOT.get()),
            Map.entry(ModBlocks.EPOCH_BLOCK.get(), ModItems.EPOCH_INGOT.get()),
            Map.entry(ModBlocks.FLUX_BLOCK.get(), ModItems.FLUX_INGOT.get()),
            Map.entry(ModBlocks.LOOP_BLOCK.get(), ModItems.LOOP_INGOT.get())
    );

    public final static Map<ItemLike, ItemLike> STICKS_FROM_SHARDS_RECIPE_MAP = Map.ofEntries(
            Map.entry(ModItems.CHRONA_STICK.get(), ModItems.OPAL_SHARD_CHRONA.get()),
            Map.entry(ModItems.TEMPORA_STICK.get(), ModItems.OPAL_SHARD_TEMPORA.get()),
            Map.entry(ModItems.EPOCH_STICK.get(), ModItems.OPAL_SHARD_EPOCH.get()),
            Map.entry(ModItems.FLUX_STICK.get(), ModItems.OPAL_SHARD_FLUX.get()),
            Map.entry(ModItems.LOOP_STICK.get(), ModItems.OPAL_SHARD_LOOP.get())
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
            ModBlocks.LOOP_BLOCK
    );

    public final static List<CropBlockSeedItem> CROP_BLOCK_SEED_ITEM_LIST = List.of(
            new CropBlockSeedItem(ModBlocks.TEMPORAL_TUBER_CROP.get(), ModItems.TEMPORAL_TUBER_SEEDS.get(),
                    ModItems.TEMPORAL_TUBER.get(), TemporalTuberCropBlock.AGE, TemporalTuberCropBlock.MAX_AGE)
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
