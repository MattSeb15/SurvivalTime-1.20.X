package net.most.survivaltimemod.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.most.survivaltimemod.client.ClientPlayerTimeData;
import net.most.survivaltimemod.time.PlayerTimeData;

import java.util.function.Supplier;

public class TimeDataSyncS2CPacket {

    private final PlayerTimeData playerTimeData;

    public TimeDataSyncS2CPacket(PlayerTimeData playerTimeData) {
        this.playerTimeData = playerTimeData;
    }

    public TimeDataSyncS2CPacket(FriendlyByteBuf buf) {
        float time = buf.readFloat();
        float maxTime = buf.readFloat();
        boolean isTimeStopped = buf.readBoolean();
        float timeMultiplier = buf.readFloat();
        playerTimeData = new PlayerTimeData(maxTime, time, isTimeStopped, timeMultiplier);
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeFloat(playerTimeData.getTime());
        buf.writeFloat(playerTimeData.getMaxTime());
        buf.writeBoolean(playerTimeData.isTimeStopped());
        buf.writeFloat(playerTimeData.getTimeMultiplier());
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // Here we are on the client
            ClientPlayerTimeData.set(playerTimeData);
        });
        return true;
    }
}
