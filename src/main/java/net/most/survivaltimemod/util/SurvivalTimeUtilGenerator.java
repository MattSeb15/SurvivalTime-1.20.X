package net.most.survivaltimemod.util;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import net.most.survivaltimemod.block.ModBlocks;
import net.most.survivaltimemod.item.ModItems;

import java.util.List;
import java.util.Map;

public class SurvivalTimeUtilGenerator {

    public final static List<RegistryObject<Block>> NEEDS_STONE_TOOL_LIST = List.of(

    );
    public final static List<RegistryObject<Block>> NEEDS_IRON_TOOL_LIST = List.of(
            ModBlocks.OPAL_BLOCK,
            ModBlocks.OPAL_RAW_BLOCK,
            ModBlocks.OPAL_ORE,
            ModBlocks.DEEPSLATE_OPAL_ORE,
            ModBlocks.NETHER_OPAL_ORE,
            ModBlocks.END_STONE_OPAL_ORE,
            ModBlocks.FIERY_TIME_BLOCK
    );
    public final static List<RegistryObject<Block>> NEEDS_DIAMOND_TOOL_LIST = List.of(

    );

    public final static List<RegistryObject<Block>> NEEDS_NETHERITE_TOOL_LIST = List.of(

    );

    public final static List<RegistryObject<Item>> ITEM_LIST = List.of(
            ModItems.OPAL_SHARD_CHRONA,
            ModItems.OPAL_SHARD_TEMPORA,
            ModItems.OPAL_SHARD_EPOCH,
            ModItems.OPAL_SHARD_FLUX,
            ModItems.OPAL_SHARD_LOOP,
            ModItems.OPAL_RAW,
            ModItems.OPAL_INGOT,
            ModItems.LOST_TIME_SPHERE,
            ModItems.TEMPORAL_TUBER,
            ModItems.TEMPORAL_TUBER_COOKED,
            ModItems.TEMPORAL_TUBER_ROTTEN,
            ModItems.FIERY_TIME
    );

    public final static List<RegistryObject<Block>> DROP_SELF_LIST = List.of(
            ModBlocks.OPAL_BLOCK,
            ModBlocks.OPAL_RAW_BLOCK
    );

    public final static List<RegistryObject<Block>> OPAL_ORE_LIST = List.of(
            ModBlocks.OPAL_ORE,
            ModBlocks.NETHER_OPAL_ORE,
            ModBlocks.DEEPSLATE_OPAL_ORE,
            ModBlocks.END_STONE_OPAL_ORE
    );

    public final static Map<ItemLike, ExpCookTimeGroupItem> SMELTING_MAP = Map.of(
            ModItems.OPAL_RAW.get(), new ExpCookTimeGroupItem(4.0f, 1200, "opal_ingot", ModItems.OPAL_INGOT.get())
    );

    public final static Map<ItemLike, ExpCookTimeGroupItem> BLASTING_MAP = Map.of(
            ModItems.OPAL_RAW.get(), new ExpCookTimeGroupItem(2.7f, 600, "opal_ingot", ModItems.OPAL_INGOT.get())
    );

    public final static Map<ItemLike, ItemLike> BLOCK_FROM_ITEM_MAP = Map.of(
            ModBlocks.OPAL_BLOCK.get(),ModItems.OPAL_INGOT.get(),
            ModBlocks.OPAL_RAW_BLOCK.get(), ModItems.OPAL_RAW.get(),
            ModBlocks.FIERY_TIME_BLOCK.get(), ModItems.FIERY_TIME.get()
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
