package net.most.survivaltimemod.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.most.survivaltimemod.SurvivalTimeMod;

import java.util.List;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, SurvivalTimeMod.MOD_ID);

    public static final RegistryObject<Item> OPAL =
            ITEMS.register("opal", () -> new Item(new Item.Properties()));
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


    public static final List<RegistryObject<Item>> ITEMS_LIST = List.of(
            OPAL,
            OPAL_SHARD_CHRONA,
            OPAL_SHARD_TEMPORA,
            OPAL_SHARD_EPOCH,
            OPAL_SHARD_FLUX,
            OPAL_SHARD_LOOP,
            OPAL_RAW,
            OPAL_INGOT
    );

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
