package net.most.survivaltimemod.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.most.survivaltimemod.SurvivalTimeMod;
import net.most.survivaltimemod.block.ModBlocks;
import net.most.survivaltimemod.entity.ModEntities;
import net.most.survivaltimemod.item.custom.*;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, SurvivalTimeMod.MOD_ID);

    public static final RegistryObject<Item> OPAL_SHARD_CHRONA =
            ITEMS.register("opal_shard_chrona", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> OPAL_SHARD_TEMPORA =
            ITEMS.register("opal_shard_tempora", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> OPAL_SHARD_EPOCH =
            ITEMS.register("opal_shard_epoch", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> OPAL_SHARD_FLUX =
            ITEMS.register("opal_shard_flux", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> OPAL_SHARD_LOOP =
            ITEMS.register("opal_shard_loop", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> OPAL_RAW =
            ITEMS.register("opal_raw", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> OPAL_INGOT =
            ITEMS.register("opal_ingot", () -> new Item(new Item.Properties()));
    //TODO: Añadir un item que al ser usado, te consuma tiempo y se convierta en algo cuando lo haga y sea para
    // craftear algo.
    public static final RegistryObject<Item> LOST_TIME_SPHERE =
            ITEMS.register("lost_time_sphere",
                    () -> new LostTimeSphereItem(new
                            Item.Properties()
                            .fireResistant()
                            .rarity(Rarity.EPIC)
                    ));

    public static final RegistryObject<Item> TEMPORAL_TUBER =
            ITEMS.register("temporal_tuber",
                    () -> new ConsumableActionItem(new
                            Item.Properties()
                            .food(ModFoods.TEMPORAL_TUBER),
                            (finishUsingItemData) -> {
                                finishUsingItemData.getPlayerTime().incrementTime(5.0f,
                                        finishUsingItemData.getServerPlayer());
                            }
                    ));

    public static final RegistryObject<Item> TEMPORAL_TUBER_COOKED =
            ITEMS.register("temporal_tuber_cooked",
                    () -> new ConsumableActionItem(new
                            Item.Properties().food(ModFoods.TEMPORAL_TUBER_COOKED),
                            (finishUsingItemData) -> {
                                finishUsingItemData.getPlayerTime().incrementTime(90.0f,
                                        finishUsingItemData.getServerPlayer());
                            }
                    ));

    public static final RegistryObject<Item> TEMPORAL_TUBER_ROTTEN =
            ITEMS.register("temporal_tuber_rotten",
                    () -> new ConsumableActionItem(new
                            Item.Properties()
                            .food(ModFoods.TEMPORAL_TUBER_ROTTEN),
                            (finishUsingItemData) -> {
                                finishUsingItemData.getPlayerTime().decrementTime(60.0f * 2.0f,
                                        finishUsingItemData.getServerPlayer());
                            }
                    ));
    public static final RegistryObject<Item> FIERY_TIME =
            ITEMS.register("fiery_time", () -> new FuelItem(new Item.Properties()
                    .stacksTo(60),
                    2400)
            );

    public static final RegistryObject<Item> LAPISLOOPIUM =
            ITEMS.register("lapisloopium", () -> new Item(new Item.Properties()));

    //raw shards

    public static final RegistryObject<Item> RAW_CHRONA =
            ITEMS.register("raw_chrona", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_TEMPORA =
            ITEMS.register("raw_tempora", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_EPOCH =
            ITEMS.register("raw_epoch", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_FLUX =
            ITEMS.register("raw_flux", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_LOOP =
            ITEMS.register("raw_loop", () -> new Item(new Item.Properties()));

    //shards ingots

    public static final RegistryObject<Item> CHRONA_INGOT =
            ITEMS.register("chrona_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TEMPORA_INGOT =
            ITEMS.register("tempora_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> EPOCH_INGOT =
            ITEMS.register("epoch_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FLUX_INGOT =
            ITEMS.register("flux_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LOOP_INGOT =
            ITEMS.register("loop_ingot", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> TEMPORAL_TUBER_SEEDS =
            ITEMS.register("temporal_tuber_seeds",
                    () -> new ItemNameBlockItem(ModBlocks.TEMPORAL_TUBER_CROP.get(), new Item.Properties()));
    //shard swords modify pAttackDamage by 1 and attackSpeed by 0.5
    public static final RegistryObject<Item> CHRONA_SWORD =
            ITEMS.register("chrona_sword",
                    () -> new OpalSword(
                            ModToolTiers.CHRONA,
                            4,
                            -2.2f,
                            5.0f,
                            0.75f,
                            new Item.Properties().fireResistant()
                    )
            );

    public static final RegistryObject<Item> TEMPORA_SWORD =
            ITEMS.register("tempora_sword",
                    () -> new OpalSword(
                            ModToolTiers.TEMPORA,
                            4,
                            -2.2f,
                            3.2f,
                            0.62f,
                            new Item.Properties().fireResistant()
                    )
            );

    public static final RegistryObject<Item> EPOCH_SWORD =
            ITEMS.register("epoch_sword",
                    () -> new OpalSword(
                            ModToolTiers.EPOCH,
                            3,
                            -2.2f,
                            2.3f,
                            0.51f,
                            new Item.Properties().fireResistant()
                    )
            );

    public static final RegistryObject<Item> FLUX_SWORD =
            ITEMS.register("flux_sword",
                    () -> new OpalSword(
                            ModToolTiers.FLUX,
                            2,
                            -2.2f,
                            1.5f,
                            0.46f,
                            new Item.Properties().fireResistant()
                    )
            );

    public static final RegistryObject<Item> LOOP_SWORD =
            ITEMS.register("loop_sword",
                    () -> new OpalSword(
                            ModToolTiers.LOOP,
                            2,
                            -5.0f,
                            1.2f,
                            0.3333f,
                            new Item.Properties().fireResistant()
                    )
            );

    public static final RegistryObject<Item> CHRONA_PICKAXE =
            ITEMS.register("chrona_pickaxe",
                    () -> new OpalPickaxe(
                            ModToolTiers.CHRONA,
                            -5,
                            -3.0f,
                            5.0f,
                            0.3999f,
                            new Item.Properties().fireResistant()
                    )
            );

    public static final RegistryObject<Item> TEMPORA_PICKAXE =
            ITEMS.register("tempora_pickaxe",
                    () -> new OpalPickaxe(
                            ModToolTiers.TEMPORA,
                            -5,
                            -3.0f,
                            3.2f,
                            0.2755f,
                            new Item.Properties().fireResistant()
                    )
            );

    public static final RegistryObject<Item> EPOCH_PICKAXE =
            ITEMS.register("epoch_pickaxe",
                    () -> new OpalPickaxe(
                            ModToolTiers.EPOCH,
                            -5,
                            -3.0f,
                            2.3f,
                            0.2215f,
                            new Item.Properties().fireResistant()
                    )
            );

    public static final RegistryObject<Item> FLUX_PICKAXE =
            ITEMS.register("flux_pickaxe",
                    () -> new OpalPickaxe(
                            ModToolTiers.FLUX,
                            -5,
                            -3.0f,
                            1.5f,
                            0.1788f,
                            new Item.Properties().fireResistant()
                    )
            );

    public static final RegistryObject<Item> LOOP_PICKAXE =
            ITEMS.register("loop_pickaxe",
                    () -> new OpalPickaxe(
                            ModToolTiers.LOOP,
                            -5,
                            -3.0f,
                            1.2f,
                            0.12f,
                            new Item.Properties().fireResistant()
                    )
            );

    public static final RegistryObject<Item> CHRONA_AXE =
            ITEMS.register("chrona_axe",
                    () -> new OpalAxe(
                            ModToolTiers.CHRONA,
                            5,
                            -3.0f,
                            4.0f,
                            0.2599f,
                            new Item.Properties().fireResistant()
                    )
            );

    public static final RegistryObject<Item> TEMPORA_AXE =
            ITEMS.register("tempora_axe",
                    () -> new OpalAxe(
                            ModToolTiers.TEMPORA,
                            5,
                            -3.1f,
                            3.0f,
                            0.1755f,
                            new Item.Properties().fireResistant()
                    )
            );

    public static final RegistryObject<Item> EPOCH_AXE =
            ITEMS.register("epoch_axe",
                    () -> new OpalAxe(
                            ModToolTiers.EPOCH,
                            5,
                            -3.2f,
                            2.5f,
                            0.1215f,
                            new Item.Properties().fireResistant()
                    )
            );

    public static final RegistryObject<Item> FLUX_AXE =
            ITEMS.register("flux_axe",
                    () -> new OpalAxe(
                            ModToolTiers.FLUX,
                            5,
                            -3.3f,
                            2.1f,
                            0.0888f,
                            new Item.Properties().fireResistant()
                    )
            );

    public static final RegistryObject<Item> LOOP_AXE =
            ITEMS.register("loop_axe",
                    () -> new OpalAxe(
                            ModToolTiers.LOOP,
                            5,
                            -3.4f,
                            1.7f,
                            0.06f,
                            new Item.Properties().fireResistant()
                    )
            );

    public static final RegistryObject<Item> CHRONA_SHOVEL =
            ITEMS.register("chrona_shovel",
                    () -> new OpalShovel(
                            ModToolTiers.CHRONA,
                            -5,
                            -3.0f,
                            4.8f,
                            0.1587f,
                            new Item.Properties().fireResistant()
                    )
            );

    public static final RegistryObject<Item> TEMPORA_SHOVEL =
            ITEMS.register("tempora_shovel",
                    () -> new OpalShovel(
                            ModToolTiers.TEMPORA,
                            -5,
                            -3.0f,
                            3.5f,
                            0.1234f,
                            new Item.Properties().fireResistant()
                    )
            );

    public static final RegistryObject<Item> EPOCH_SHOVEL =
            ITEMS.register("epoch_shovel",
                    () -> new OpalShovel(
                            ModToolTiers.EPOCH,
                            -5,
                            -3.0f,
                            2.11f,
                            0.0999f,
                            new Item.Properties().fireResistant()
                    )
            );

    public static final RegistryObject<Item> FLUX_SHOVEL =
            ITEMS.register("flux_shovel",
                    () -> new OpalShovel(
                            ModToolTiers.FLUX,
                            -5,
                            -3.0f,
                            1.7f,
                            0.05f,
                            new Item.Properties().fireResistant()
                    )
            );

    public static final RegistryObject<Item> LOOP_SHOVEL =
            ITEMS.register("loop_shovel",
                    () -> new OpalShovel(
                            ModToolTiers.LOOP,
                            -5,
                            -3.0f,
                            1.2f,
                            0.02f,
                            new Item.Properties().fireResistant()
                    )
            );

    public static final RegistryObject<Item> CHRONA_HOE =
            ITEMS.register("chrona_hoe",
                    () -> new OpalHoe(
                            ModToolTiers.CHRONA,
                            -5,
                            -3.0f,
                            6.2f,
                            0.030f,
                            new Item.Properties().fireResistant()
                    )
            );

    public static final RegistryObject<Item> TEMPORA_HOE =
            ITEMS.register("tempora_hoe",
                    () -> new OpalHoe(
                            ModToolTiers.TEMPORA,
                            -5,
                            -3.0f,
                            5.8f,
                            0.021f,
                            new Item.Properties().fireResistant()
                    )
            );

    public static final RegistryObject<Item> EPOCH_HOE =
            ITEMS.register("epoch_hoe",
                    () -> new OpalHoe(
                            ModToolTiers.EPOCH,
                            -5,
                            -3.0f,
                            5.5f,
                            0.017f,
                            new Item.Properties().fireResistant()
                    )
            );

    public static final RegistryObject<Item> FLUX_HOE =
            ITEMS.register("flux_hoe",
                    () -> new OpalHoe(
                            ModToolTiers.FLUX,
                            -5,
                            -3.0f,
                            5.2f,
                            0.015f,
                            new Item.Properties().fireResistant()
                    )
            );

    public static final RegistryObject<Item> LOOP_HOE =
            ITEMS.register("loop_hoe",
                    () -> new OpalHoe(
                            ModToolTiers.LOOP,
                            -5,
                            -3.0f,
                            5.0f,
                            0.01f,
                            new Item.Properties().fireResistant()
                    )
            );


    public static final RegistryObject<Item> ORACLE_IS_HOURGLASS =
            ITEMS.register("oracle_is_hourglass", () -> new OracleIsHourglassItem(new Item.Properties()));

    public static final RegistryObject<Item> IFLUX =
            ITEMS.register("iflux", () -> new Item(new Item.Properties()));

///    public static final RegistryObject<Item> TIMEKEEPER_IS_EMBLEM =
//            ITEMS.register("timekeeper_is_emblem", () -> new TimekeeperIsEmblem(new Item.Properties()));

    ////shards sticks
    public static final RegistryObject<Item> CHRONA_STICK =
            ITEMS.register("chrona_stick", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> TEMPORA_STICK =
            ITEMS.register("tempora_stick", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> EPOCH_STICK =
            ITEMS.register("epoch_stick", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FLUX_STICK =
            ITEMS.register("flux_stick", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> LOOP_STICK =
            ITEMS.register("loop_stick", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CLOCK_FRAGMENT =
            ITEMS.register("clock_fragment", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> TIMEKEEPER_SPAWN_EGG =
            ITEMS.register("timekeeper_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.TIMEKEEPER,
                    0xff5c2c5e,
                    0xff44a25f,
                    new Item.Properties()));

    public static final RegistryObject<Item> TIME_DEVOURER_SPAWN_EGG =
            ITEMS.register("time_devourer_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.TIME_DEVOURER,
                    0x000000,
                    0xffffff,
                    new Item.Properties()));

    public static final RegistryObject<Item> PURIFIER_ITEM =
            ITEMS.register("purifier_item", () -> new PurifierItem(new Item.Properties().stacksTo(8)));
    public static final RegistryObject<Item> PROSPERITY_ITEM =
            ITEMS.register("prosperity_item", () -> new ProsperityItem(new Item.Properties().stacksTo(8)));
    public static final RegistryObject<Item> CURSE_ITEM =
            ITEMS.register("curse_item", () -> new CurseItem(new Item.Properties().stacksTo(8)));

    public static final RegistryObject<Item> PILE_OF_MEAT =
            ITEMS.register("pile_of_meat", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> COMPACTED_MILK =
            ITEMS.register("compacted_milk", () -> new Item(new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
