package net.most.survivaltimemod.event;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.most.survivaltimemod.SurvivalTimeMod;
import net.most.survivaltimemod.block.entity.ModBlockEntities;
import net.most.survivaltimemod.block.entity.renderer.HourglassHubStationBlockRenderer;
import net.most.survivaltimemod.client.TimeHudOverlay;
import net.most.survivaltimemod.client.gui.OracleIsHourglassScreen;
import net.most.survivaltimemod.client.gui.TimeStationScreen;
import net.most.survivaltimemod.client.gui.HourglassHubStationScreen;
import net.most.survivaltimemod.screen.ModMenuTypes;

public class ClientEvents {
    @Mod.EventBusSubscriber(modid = SurvivalTimeMod.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        @SubscribeEvent
        public static void onRenderGui(RenderGuiOverlayEvent.Pre event) {

            if (event.getOverlay().id() == VanillaGuiOverlay.PLAYER_HEALTH.id()) {
                event.setCanceled(true);
            }
        }
    }

    @Mod.EventBusSubscriber(modid = SurvivalTimeMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            event.enqueueWork(() -> {
                MenuScreens.register(ModMenuTypes.HOURGLASS_HUB_STATION_MENU.get(), HourglassHubStationScreen::new);
                MenuScreens.register(ModMenuTypes.TIME_STATION_MENU.get(), TimeStationScreen::new);
                MenuScreens.register(ModMenuTypes.ORACLE_IS_HOURGLASS_MENU.get(), OracleIsHourglassScreen::new);
            });

        }

        @SubscribeEvent
        public static void onRegisterOverlay(RegisterGuiOverlaysEvent event) {
            event.registerBelowAll("survivaltimemod_time", TimeHudOverlay.HUD_TIME);

        }

        @SubscribeEvent
        public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
            event.registerBlockEntityRenderer(ModBlockEntities.HOURGLASS_HUB_STATION.get(),
                    HourglassHubStationBlockRenderer::new);

        }


    }


}
