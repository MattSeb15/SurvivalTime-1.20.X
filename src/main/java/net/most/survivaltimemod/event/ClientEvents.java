package net.most.survivaltimemod.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.most.survivaltimemod.SurvivalTimeMod;
import net.most.survivaltimemod.client.TimeHudOverlay;

public class ClientEvents {
    @Mod.EventBusSubscriber(modid = SurvivalTimeMod.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents{
        @SubscribeEvent
        public static void onRenderGui(RenderGuiOverlayEvent.Pre event) {

            if (event.getOverlay().id() == VanillaGuiOverlay.PLAYER_HEALTH.id()) {
                event.setCanceled(true);
            }
        }
    }

    @Mod.EventBusSubscriber(modid = SurvivalTimeMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents{
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
        @SubscribeEvent
        public static void onRegisterOverlay(RegisterGuiOverlaysEvent event) {
            event.registerBelowAll("survivaltimemod_time", TimeHudOverlay.HUD_TIME);

        }


    }


}
