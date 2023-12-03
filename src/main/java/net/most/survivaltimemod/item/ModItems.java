package net.most.survivaltimemod.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.most.survivaltimemod.SurvivalTimeMod;
import net.most.survivaltimemod.item.custom.FuelItem;
import net.most.survivaltimemod.item.custom.LostTimeSphereItem;

import java.util.List;

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
                            .stacksTo(1)
                    ));

    public static final RegistryObject<Item> TEMPORAL_TUBER =
            ITEMS.register("temporal_tuber",
                    () -> new Item(new
                            Item.Properties()
                            .food(ModFoods.TEMPORAL_TUBER)
                    ));

    public static final RegistryObject<Item> TEMPORAL_TUBER_COOKED =
            ITEMS.register("temporal_tuber_cooked",
                    () -> new Item(new
                            Item.Properties()
                            .food(ModFoods.TEMPORAL_TUBER_COOKED)
                    ));

    public static final RegistryObject<Item> TEMPORAL_TUBER_ROTTEN =
            ITEMS.register("temporal_tuber_rotten",
                    () -> new Item(new
                            Item.Properties()
                            .food(ModFoods.TEMPORAL_TUBER_ROTTEN)
                    ));
    public static final RegistryObject<Item> FIERY_TIME =
            ITEMS.register("fiery_time", () -> new FuelItem(new Item.Properties()
                    .stacksTo(60),
                    2400)
            );

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
    //shard swords modify pAttackDamage by 1 and attackSpeed by 0.5
//    public static final RegistryObject<Item> CHRONA_SWORD =
//            ITEMS.register("chrona_sword",
//                    () -> new SwordItem(
//                            ModToolTiers.CHRONA,
//                            4,
//                            2,
//                            new Item.Properties()
//                    )
//            );

//    public static final RegistryObject<Item> TEMPORA_SWORD =
//            ITEMS.register("tempora_sword",
//                    () -> new SwordItem(
//                            ModToolTiers.TEMPORA,
//                            3,
//                            2,
//                            new Item.Properties()
//                    )
//            );

//    public static final RegistryObject<Item> EPOCH_SWORD =
//            ITEMS.register("epoch_sword",
//                    () -> new SwordItem(
//                            ModToolTiers.EPOCH,
//                            3,
//                            2,
//                            new Item.Properties()
//                    )
//            );

    public static final RegistryObject<Item> FLUX_SWORD =
            ITEMS.register("flux_sword",
                    () -> new SwordItem(
                            ModToolTiers.FLUX,
                            2,
                            -2.2f,
                            new Item.Properties().fireResistant()
                    )
            );

    public static final RegistryObject<Item> LOOP_SWORD =
            ITEMS.register("loop_sword",
                    () -> new SwordItem(
                            ModToolTiers.LOOP,
                            2,
                            -2.3f,
                            new Item.Properties().fireResistant()
                    )
            );




    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
