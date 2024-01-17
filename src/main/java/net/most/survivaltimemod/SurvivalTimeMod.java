package net.most.survivaltimemod;

import com.mojang.logging.LogUtils;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.most.survivaltimemod.block.ModBlocks;
import net.most.survivaltimemod.block.entity.ModBlockEntities;
import net.most.survivaltimemod.effect.ModEffects;
import net.most.survivaltimemod.entity.ModEntities;
import net.most.survivaltimemod.item.ModCreativeModTabs;
import net.most.survivaltimemod.item.ModItems;
import net.most.survivaltimemod.loot.ModLootModifiers;
import net.most.survivaltimemod.networking.ModMessages;
import net.most.survivaltimemod.potion.ModPotions;
import net.most.survivaltimemod.recipe.ModRecipes;
import net.most.survivaltimemod.screen.ModMenuTypes;
import net.most.survivaltimemod.sound.ModSounds;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(SurvivalTimeMod.MOD_ID)
public class SurvivalTimeMod {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "survivaltimemod";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public SurvivalTimeMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModCreativeModTabs.register(modEventBus);
        ModEffects.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModLootModifiers.register(modEventBus);

        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModRecipes.register(modEventBus);
        ModSounds.register(modEventBus);
        ModEntities.register(modEventBus);

        ModPotions.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        ModMessages.register();
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {


    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

}
