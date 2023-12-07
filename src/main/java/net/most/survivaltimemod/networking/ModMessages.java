package net.most.survivaltimemod.networking;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import net.most.survivaltimemod.SurvivalTimeMod;
import net.most.survivaltimemod.networking.packet.TemplateC2SPacket;
import net.most.survivaltimemod.networking.packet.TimeDataSyncS2CPacket;

public class ModMessages {
    private static SimpleChannel INSTANCE;
    private static int packetId = 0;
    public static String registryName = "main_network_channel";

    private static int id() {
        return packetId++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(SurvivalTimeMod.MOD_ID, registryName))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(a -> true)
                .serverAcceptedVersions(a -> true)
                .simpleChannel();

        INSTANCE = net;

        //template packet
//        net.messageBuilder(TemplateC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
//                .decoder(TemplateC2SPacket::new)
//                .encoder(TemplateC2SPacket::toBytes)
//                .consumerMainThread(TemplateC2SPacket::handle)
//                .add();

        net.messageBuilder(TimeDataSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(TimeDataSyncS2CPacket::new)
                .encoder(TimeDataSyncS2CPacket::toBytes)
                .consumerMainThread(TimeDataSyncS2CPacket::handle)
                .add();

    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
}
