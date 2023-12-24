package net.most.survivaltimemod.screen;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.most.survivaltimemod.SurvivalTimeMod;
import net.most.survivaltimemod.world.inventory.HourglassHubStationMenu;
import net.most.survivaltimemod.world.inventory.OracleIsHourglassMenu;
import net.most.survivaltimemod.world.inventory.TimeStationMenu;

public class ModMenuTypes {

    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, SurvivalTimeMod.MOD_ID);

    public static final RegistryObject<MenuType<HourglassHubStationMenu>> HOURGLASS_HUB_STATION_MENU =
            registerMenuType(HourglassHubStationMenu::new, "hourglass_hub_station_menu");

    public static final RegistryObject<MenuType<TimeStationMenu>> TIME_STATION_MENU =
            registerMenuType(TimeStationMenu::new,"time_station_menu");

    public static final RegistryObject<MenuType<OracleIsHourglassMenu>> ORACLE_IS_HOURGLASS_MENU =
            registerMenuType(OracleIsHourglassMenu::new,"oracle_is_hourglass_menu");


    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory, String name) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

//    private static <T extends AbstractContainerMenu> MenuType<T> registerMenu(String pKey, MenuType.MenuSupplier<T> pFactory) {
//        return Registry.register(BuiltInRegistries.MENU, pKey, new MenuType<>(pFactory, FeatureFlags.VANILLA_SET));
//    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }

}
