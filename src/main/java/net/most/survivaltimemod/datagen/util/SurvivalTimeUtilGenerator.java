package net.most.survivaltimemod.datagen.util;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import net.most.survivaltimemod.block.ModBlocks;
import net.most.survivaltimemod.block.custom.TemporalTuberCropBlock;
import net.most.survivaltimemod.data.CropBlockSeedItem;
import net.most.survivaltimemod.data.CropBlockSeedItemModel;
import net.most.survivaltimemod.item.ModItems;
import net.most.survivaltimemod.util.ExpCookTimeGroupItem;
import net.most.survivaltimemod.util.ShardOptions;
import net.most.survivaltimemod.util.WeightMinMax;
import net.most.survivaltimemod.util.records.*;

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
            ModItems.COMPACTED_MILK
    );

    ////----->>> RECIPES <<<-----\\\\----------------------->>> RECIPES <<<-----\\\\
    public final static Map<ItemLike, ItemLike> RAW_SHARDS_RECIPE_MAP = Map.of(
            ModItems.RAW_LOOP.get(), ModItems.OPAL_SHARD_LOOP.get(),
            ModItems.RAW_FLUX.get(), ModItems.OPAL_SHARD_FLUX.get(),
            ModItems.RAW_EPOCH.get(), ModItems.OPAL_SHARD_EPOCH.get(),
            ModItems.RAW_TEMPORA.get(), ModItems.OPAL_SHARD_TEMPORA.get(),
            ModItems.RAW_CHRONA.get(), ModItems.OPAL_SHARD_CHRONA.get()

    );

    public final static List<FullBorderPattern> FULL_BORDER_PATTERN_LIST = List.of(
            new FullBorderPattern(ModItems.TEMPORAL_TUBER_SEEDS.get(), 5, 20 * 60 * 2, 60 * 5,
                    Items.WHEAT_SEEDS,
                    Items.PUMPKIN_SEEDS,
                    Items.MELON_SEEDS)
    );
    public final static List<MediumBorderPattern> MEDIUM_BORDER_PATTERN_LIST = List.of(

    );

    public final static List<MonoMaterialPattern> MONO_MATERIAL_PATTERN_LIST = List.of(
            new MonoMaterialPattern(ModItems.COMPACTED_MILK.get(),
                    1,
                    20 * 60 * 5,
                    60 * 15,
                    Items.MILK_BUCKET,
                    4),
            new MonoMaterialPattern(ModItems.LOOP_INGOT.get(),
                    1,
                    20 * 60,
                    60,
                    ModItems.RAW_LOOP.get(),
                    1),
            new MonoMaterialPattern(ModItems.FLUX_INGOT.get(),
                    1,
                    20 * 60 * 2,
                    60 * 2,
                    ModItems.RAW_FLUX.get(),
                    1),
            new MonoMaterialPattern(ModItems.EPOCH_INGOT.get(),
                    1,
                    20 * 60 * 3,
                    60 * 3,
                    ModItems.RAW_EPOCH.get(),
                    1),
            new MonoMaterialPattern(ModItems.TEMPORA_INGOT.get(),
                    1,
                    20 * 60 * 4,
                    60 * 4,
                    ModItems.RAW_TEMPORA.get(),
                    1),
            new MonoMaterialPattern(ModItems.CHRONA_INGOT.get(),
                    1,
                    20 * 60 * 5,
                    60 * 5,
                    ModItems.RAW_CHRONA.get(),
                    1),
            new MonoMaterialPattern(ModItems.OPAL_INGOT.get(),
                    1,
                    20 * 60 * 10,
                    60 * 10,
                    ModItems.OPAL_RAW.get(),
                    1)

    );

    public final static List<XMaterialShapelessPattern> X_MATERIALS_PATTERN_LIST = List.of(
            new XMaterialShapelessPattern(ModItems.IFLUX.get(),
                    3,
                    20 * 60,
                    60 * 3,
                    ModItems.OPAL_SHARD_FLUX.get(),
                    Items.IRON_INGOT
            ),
            new XMaterialShapelessPattern(ModItems.LAPISLOOPIUM.get(),
                    3,
                    20 * 60 + 20 * 30,
                    60 * 3,
                    ModItems.OPAL_SHARD_LOOP.get(),
                    Items.LAPIS_LAZULI
            )
    );

    public final static List<SwordPattern> SWORD_PATTERN_LIST = List.of(
            new SwordPattern(ModItems.LOOP_SWORD.get(),
                    20 * 60 * 30,
                    60 * 60,
                    ModItems.LOOP_STICK.get(),
                    ModBlocks.LOOP_BLOCK.get(),
                    ModItems.LOOP_INGOT.get()
            ),
            new SwordPattern(ModItems.FLUX_SWORD.get(),
                    20 * 60 * 40,
                    60 * 60 * 2,
                    ModItems.FLUX_STICK.get(),
                    ModBlocks.FLUX_BLOCK.get(),
                    ModItems.FLUX_INGOT.get()
            ),
            new SwordPattern(ModItems.EPOCH_SWORD.get(),
                    20 * 60 * 50,
                    60 * 60 * 3,
                    ModItems.EPOCH_STICK.get(),
                    ModBlocks.EPOCH_BLOCK.get(),
                    ModItems.EPOCH_INGOT.get()
            ),
            new SwordPattern(ModItems.TEMPORA_SWORD.get(),
                    20 * 60 * 60,
                    60 * 60 * 4,
                    ModItems.TEMPORA_STICK.get(),
                    ModBlocks.TEMPORA_BLOCK.get(),
                    ModItems.TEMPORA_INGOT.get()
            ),
            new SwordPattern(ModItems.CHRONA_SWORD.get(),
                    20 * 60 * 80,
                    60 * 60 * 5,
                    ModItems.CHRONA_STICK.get(),
                    ModBlocks.CHRONA_BLOCK.get(),
                    ModItems.CHRONA_INGOT.get()
            )

    );

    public final static List<PickaxePattern> PICKAXE_PATTERN_LIST = List.of(
            new PickaxePattern(ModItems.LOOP_PICKAXE.get(),
                    20 * 60 * 30,
                    60 * 60,
                    ModItems.LOOP_STICK.get(),
                    ModBlocks.LOOP_BLOCK.get(),
                    ModItems.LOOP_INGOT.get()
            ),
            new PickaxePattern(ModItems.FLUX_PICKAXE.get(),
                    20 * 60 * 40,
                    60 * 60 * 2,
                    ModItems.FLUX_STICK.get(),
                    ModBlocks.FLUX_BLOCK.get(),
                    ModItems.FLUX_INGOT.get()
            ),
            new PickaxePattern(ModItems.EPOCH_PICKAXE.get(),
                    20 * 60 * 50,
                    60 * 60 * 3,
                    ModItems.EPOCH_STICK.get(),
                    ModBlocks.EPOCH_BLOCK.get(),
                    ModItems.EPOCH_INGOT.get()
            ),
            new PickaxePattern(ModItems.TEMPORA_PICKAXE.get(),
                    20 * 60 * 60,
                    60 * 60 * 4,
                    ModItems.TEMPORA_STICK.get(),
                    ModBlocks.TEMPORA_BLOCK.get(),
                    ModItems.TEMPORA_INGOT.get()
            ),
            new PickaxePattern(ModItems.CHRONA_PICKAXE.get(),
                    20 * 60 * 80,
                    60 * 60 * 5,
                    ModItems.CHRONA_STICK.get(),
                    ModBlocks.CHRONA_BLOCK.get(),
                    ModItems.CHRONA_INGOT.get()
            )

    );

    public final static List<AxePattern> AXE_PATTERN_LIST = List.of(
            new AxePattern(ModItems.LOOP_AXE.get(),
                    20 * 60 * 30,
                    60 * 60,
                    ModItems.LOOP_STICK.get(),
                    ModBlocks.LOOP_BLOCK.get(),
                    ModItems.LOOP_INGOT.get()
            ),
            new AxePattern(ModItems.FLUX_AXE.get(),
                    20 * 60 * 40,
                    60 * 60 * 2,
                    ModItems.FLUX_STICK.get(),
                    ModBlocks.FLUX_BLOCK.get(),
                    ModItems.FLUX_INGOT.get()
            ),
            new AxePattern(ModItems.EPOCH_AXE.get(),
                    20 * 60 * 50,
                    60 * 60 * 3,
                    ModItems.EPOCH_STICK.get(),
                    ModBlocks.EPOCH_BLOCK.get(),
                    ModItems.EPOCH_INGOT.get()
            ),
            new AxePattern(ModItems.TEMPORA_AXE.get(),
                    20 * 60 * 60,
                    60 * 60 * 4,
                    ModItems.TEMPORA_STICK.get(),
                    ModBlocks.TEMPORA_BLOCK.get(),
                    ModItems.TEMPORA_INGOT.get()
            ),
            new AxePattern(ModItems.CHRONA_AXE.get(),
                    20 * 60 * 80,
                    60 * 60 * 5,
                    ModItems.CHRONA_STICK.get(),
                    ModBlocks.CHRONA_BLOCK.get(),
                    ModItems.CHRONA_INGOT.get()
            )

    );

    public final static List<ShovelPattern> SHOVEL_PATTERN_LIST = List.of(
            new ShovelPattern(ModItems.LOOP_SHOVEL.get(),
                    20 * 60 * 30,
                    60 * 60,
                    ModItems.LOOP_STICK.get(),
                    ModBlocks.LOOP_BLOCK.get(),
                    ModItems.LOOP_INGOT.get()
            ),
            new ShovelPattern(ModItems.FLUX_SHOVEL.get(),
                    20 * 60 * 40,
                    60 * 60 * 2,
                    ModItems.FLUX_STICK.get(),
                    ModBlocks.FLUX_BLOCK.get(),
                    ModItems.FLUX_INGOT.get()
            ),
            new ShovelPattern(ModItems.EPOCH_SHOVEL.get(),
                    20 * 60 * 50,
                    60 * 60 * 3,
                    ModItems.EPOCH_STICK.get(),
                    ModBlocks.EPOCH_BLOCK.get(),
                    ModItems.EPOCH_INGOT.get()
            ),
            new ShovelPattern(ModItems.TEMPORA_SHOVEL.get(),
                    20 * 60 * 60,
                    60 * 60 * 4,
                    ModItems.TEMPORA_STICK.get(),
                    ModBlocks.TEMPORA_BLOCK.get(),
                    ModItems.TEMPORA_INGOT.get()
            ),
            new ShovelPattern(ModItems.CHRONA_SHOVEL.get(),
                    20 * 60 * 80,
                    60 * 60 * 5,
                    ModItems.CHRONA_STICK.get(),
                    ModBlocks.CHRONA_BLOCK.get(),
                    ModItems.CHRONA_INGOT.get()
            )

    );

    public final static List<HoePattern> HOE_PATTERN_LIST = List.of(
            new HoePattern(ModItems.LOOP_HOE.get(),
                    20 * 60 * 30,
                    60 * 60,
                    ModItems.LOOP_STICK.get(),
                    ModBlocks.LOOP_BLOCK.get(),
                    ModItems.LOOP_INGOT.get()
            ),
            new HoePattern(ModItems.FLUX_HOE.get(),
                    20 * 60 * 40,
                    60 * 60 * 2,
                    ModItems.FLUX_STICK.get(),
                    ModBlocks.FLUX_BLOCK.get(),
                    ModItems.FLUX_INGOT.get()
            ),
            new HoePattern(ModItems.EPOCH_HOE.get(),
                    20 * 60 * 50,
                    60 * 60 * 3,
                    ModItems.EPOCH_STICK.get(),
                    ModBlocks.EPOCH_BLOCK.get(),
                    ModItems.EPOCH_INGOT.get()
            ),
            new HoePattern(ModItems.TEMPORA_HOE.get(),
                    20 * 60 * 60,
                    60 * 60 * 4,
                    ModItems.TEMPORA_STICK.get(),
                    ModBlocks.TEMPORA_BLOCK.get(),
                    ModItems.TEMPORA_INGOT.get()
            ),
            new HoePattern(ModItems.CHRONA_HOE.get(),
                    20 * 60 * 80,
                    60 * 60 * 5,
                    ModItems.CHRONA_STICK.get(),
                    ModBlocks.CHRONA_BLOCK.get(),
                    ModItems.CHRONA_INGOT.get()
            )

    );

    public final static List<StarCenterPattern> STAR_CENTER_PATTERN_LIST = List.of(
            new StarCenterPattern(ModItems.CURSE_ITEM.get(),
                    1,
                    20 * 60 * 5,
                    60 * 15,
                    ModItems.PILE_OF_MEAT.get(),
                    ModItems.COMPACTED_MILK.get()
            )

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
            Map.entry(ModItems.LOOP_STICK.get(), ModItems.OPAL_SHARD_LOOP.get()),
            Map.entry(ModItems.FLUX_STICK.get(), ModItems.OPAL_SHARD_FLUX.get()),
            Map.entry(ModItems.EPOCH_STICK.get(), ModItems.OPAL_SHARD_EPOCH.get()),
            Map.entry(ModItems.TEMPORA_STICK.get(), ModItems.OPAL_SHARD_TEMPORA.get()),
            Map.entry(ModItems.CHRONA_STICK.get(), ModItems.OPAL_SHARD_CHRONA.get())
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
