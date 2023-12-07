package net.most.survivaltimemod.client;

import net.most.survivaltimemod.time.PlayerTimeData;

public class ClientPlayerTimeData {

    private static PlayerTimeData playerTimeData;

    public static void set(PlayerTimeData playerTimeData) {
        ClientPlayerTimeData.playerTimeData = playerTimeData;
    }

    public static PlayerTimeData get() {
        return playerTimeData;
    }

}
