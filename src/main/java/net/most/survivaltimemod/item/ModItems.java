package net.most.survivaltimemod.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
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


    public static final List<RegistryObject<Item>> ITEMS_LIST = List.of(
            OPAL_SHARD_CHRONA,
            OPAL_SHARD_TEMPORA,
            OPAL_SHARD_EPOCH,
            OPAL_SHARD_FLUX,
            OPAL_SHARD_LOOP,
            OPAL_RAW,
            OPAL_INGOT,
            LOST_TIME_SPHERE,
            TEMPORAL_TUBER,
            TEMPORAL_TUBER_COOKED,
            TEMPORAL_TUBER_ROTTEN,
            FIERY_TIME
    );

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
