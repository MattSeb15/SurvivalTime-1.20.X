package net.most.survivaltimemod.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, "survivaltimemod");

    public static final RegistryObject<CreativeModeTab> SURVIVAL_TIME_TAB =
            CREATIVE_MODE_TABS.register("survival_time_tab",
                    () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.OPAL.get()))
                            .title(Component.translatable("creativetab.survivaltimemod_tab"))
                            .displayItems((pParameters, pOutput) -> {
                                for (RegistryObject<Item> item : ModItems.ITEMS_LIST) {
                                    pOutput.accept(item.get());
                                }
                            })
                            .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
