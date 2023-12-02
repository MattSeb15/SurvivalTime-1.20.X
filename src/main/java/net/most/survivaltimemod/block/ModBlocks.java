package net.most.survivaltimemod.block;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.most.survivaltimemod.SurvivalTimeMod;
import net.most.survivaltimemod.item.ModItems;
import net.most.survivaltimemod.item.custom.FuelItemBlock;

import java.util.List;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, SurvivalTimeMod.MOD_ID);

    public static final RegistryObject<Block> OPAL_BLOCK = registerBlock("opal_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                    .mapColor(DyeColor.PURPLE)
                    .strength(15.0f, 2000.0f)
                    .sound(SoundType.AMETHYST)));
    public static final RegistryObject<Block> OPAL_RAW_BLOCK = registerBlock("opal_raw_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                    .mapColor(DyeColor.MAGENTA)
                    .strength(10.0f, 1800.0f)
                    .sound(SoundType.CORAL_BLOCK)));
    // Ores
    public static final RegistryObject<Block> OPAL_ORE = registerBlock("opal_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(5.0f, 10.0f)
                    .requiresCorrectToolForDrops(), UniformInt.of(3, 9)));
    public static final RegistryObject<Block> DEEPSLATE_OPAL_ORE = registerBlock("deepslate_opal_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)
                    .strength(6.0f, 10.0f)
                    .requiresCorrectToolForDrops(), UniformInt.of(5, 9)));
    public static final RegistryObject<Block> NETHER_OPAL_ORE = registerBlock("nether_opal_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.NETHERRACK)
                    .strength(4.0f, 10.0f)
                    .requiresCorrectToolForDrops(), UniformInt.of(6, 10)));
    public static final RegistryObject<Block> END_STONE_OPAL_ORE = registerBlock("end_stone_opal_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.END_STONE)
                    .strength(8.0f, 50.0f)
                    .requiresCorrectToolForDrops(), UniformInt.of(8, 10)));
    public static final RegistryObject<Block> FIERY_TIME_BLOCK = registerBlock("fiery_time_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.COAL_BLOCK)
                    .mapColor(DyeColor.RED)
                    .strength(15.0f, 10.0f)),
            new Item.Properties()
                    .stacksTo(16),
            24000);

    // raw shards blocks

    public static final RegistryObject<Block> RAW_CHRONA_BLOCK = registerBlock("raw_chrona_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.RAW_IRON_BLOCK)
                    .mapColor(DyeColor.BLUE)
                    .strength(20.0f, 50.0f)));

    public static final RegistryObject<Block> RAW_TEMPORA_BLOCK = registerBlock("raw_tempora_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.RAW_IRON_BLOCK)
                    .mapColor(DyeColor.MAGENTA)
                    .strength(17.0f, 40.0f)));

    public static final RegistryObject<Block> RAW_EPOCH_BLOCK = registerBlock("raw_epoch_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.RAW_IRON_BLOCK)
                    .mapColor(DyeColor.RED)
                    .strength(14.0f, 30.0f)));

    public static final RegistryObject<Block> RAW_FLUX_BLOCK = registerBlock("raw_flux_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.RAW_IRON_BLOCK)
                    .mapColor(DyeColor.LIGHT_BLUE)
                    .strength(11.0f, 20.0f)));

    public static final RegistryObject<Block> RAW_LOOP_BLOCK = registerBlock("raw_loop_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.RAW_IRON_BLOCK)
                    .mapColor(DyeColor.PURPLE)
                    .strength(9.0f, 10.0f)));

    // shards blocks

    public static final RegistryObject<Block> CHRONA_BLOCK = registerBlock("chrona_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                    .mapColor(DyeColor.BLUE)
                    .strength(17.0f, 30.0f)));

    public static final RegistryObject<Block> TEMPORA_BLOCK = registerBlock("tempora_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                    .mapColor(DyeColor.MAGENTA)
                    .strength(15.0f, 30.0f)));

    public static final RegistryObject<Block> EPOCH_BLOCK = registerBlock("epoch_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                    .mapColor(DyeColor.RED)
                    .strength(11.0f, 30.0f)));

    public static final RegistryObject<Block> FLUX_BLOCK = registerBlock("flux_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                    .mapColor(DyeColor.LIGHT_BLUE)
                    .strength(9.0f, 30.0f)));

    public static final RegistryObject<Block> LOOP_BLOCK = registerBlock("loop_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                    .mapColor(DyeColor.PURPLE)
                    .strength(7.0f, 30.0f)));



    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> registeredBlock = BLOCKS.register(name, block);
        registerBlockItem(name, registeredBlock);
        return registeredBlock;
    }
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, Item.Properties properties) {
        RegistryObject<T> registeredBlock = BLOCKS.register(name, block);
        registerBlockItem(name, registeredBlock, properties);
        return registeredBlock;
    }
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, Item.Properties properties, int burnTime) {
        RegistryObject<T> registeredBlock = BLOCKS.register(name, block);
        registerBlockItem(name, registeredBlock, properties, burnTime);
        return registeredBlock;
    }
    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block, Item.Properties properties) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), properties));
    }
    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block, Item.Properties properties, int burnTime) {
        ModItems.ITEMS.register(name, () -> new FuelItemBlock(block.get(), properties,burnTime));
    }

//    public static List<RegistryObject<Block>> BLOCK_LIST = List.of(
//            OPAL_BLOCK,
//            OPAL_RAW_BLOCK,
//            //Ores
//            OPAL_ORE,
//            DEEPSLATE_OPAL_ORE,
//            NETHER_OPAL_ORE,
//            END_STONE_OPAL_ORE,
//            //Other
//            FIERY_TIME_BLOCK
//    );

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
