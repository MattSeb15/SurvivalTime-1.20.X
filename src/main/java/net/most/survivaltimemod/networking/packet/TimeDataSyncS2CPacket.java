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
        float damageMultiplier = buf.readFloat();
        int[] effectInstancesDuration = buf.readVarIntArray();


        playerTimeData = new PlayerTimeData(maxTime, time, isTimeStopped, timeMultiplier, damageMultiplier, effectInstancesDuration);
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeFloat(playerTimeData.getTime());
        buf.writeFloat(playerTimeData.getMaxTime());
        buf.writeBoolean(playerTimeData.isTimeStopped());
        buf.writeFloat(playerTimeData.getTimeMultiplier());
        buf.writeFloat(playerTimeData.getDamageMultiplier());
        buf.writeVarIntArray(playerTimeData.getEffectInstancesDuration());
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
