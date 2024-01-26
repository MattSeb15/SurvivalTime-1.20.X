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
        float[] lastStats = readFloatArray(buf);
        double coins = buf.readDouble();
        float coinsMultiplier = buf.readFloat();


        playerTimeData = new PlayerTimeData(maxTime,
                time,
                isTimeStopped,
                timeMultiplier,
                damageMultiplier,
                effectInstancesDuration,
                lastStats,
                coins,
                coinsMultiplier);
    }

    private float[] readFloatArray(FriendlyByteBuf buf) {
        int length = buf.readVarInt();
        float[] array = new float[length];
        for (int i = 0; i < length; i++) {
            array[i] = buf.readFloat();
        }
        return array;
    }

    private void writeFloatArray(FriendlyByteBuf buf, float[] array) {
        buf.writeVarInt(array.length);
        for (float f : array) {
            buf.writeFloat(f);
        }
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeFloat(playerTimeData.getTime());
        buf.writeFloat(playerTimeData.getMaxTime());
        buf.writeBoolean(playerTimeData.isTimeStopped());
        buf.writeFloat(playerTimeData.getTimeMultiplier());
        buf.writeFloat(playerTimeData.getDamageMultiplier());
        buf.writeVarIntArray(playerTimeData.getEffectInstancesDuration());
        writeFloatArray(buf, playerTimeData.getLastStats());
        buf.writeDouble(playerTimeData.getCoins());
        buf.writeFloat(playerTimeData.getCoinsMultiplier());

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
