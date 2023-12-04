package net.most.survivaltimemod.events;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.most.survivaltimemod.SurvivalTimeMod;
import net.most.survivaltimemod.client.TimeHudOverlay;

public class ClientEvents {
    @Mod.EventBusSubscriber(modid = SurvivalTimeMod.MOD_ID, value = Dist.CLIENT)
    public class ClientForgeEvents{

    }

    @Mod.EventBusSubscriber(modid = SurvivalTimeMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents{
        @SubscribeEvent
        public static void onRegisterOverlay(RegisterGuiOverlaysEvent event) {
            event.registerAboveAll("survivaltimemod_time", TimeHudOverlay.HUD_TIME);

        }

    }


}
