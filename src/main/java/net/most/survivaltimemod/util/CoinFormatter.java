package net.most.survivaltimemod.util;

import java.util.HashMap;
import java.util.Map;

public class CoinFormatter {
    private static final Map<Integer, String> letters = new HashMap<>();
    static {
        letters.put(3, "K");
        letters.put(6, "M");
        letters.put(9, "B");
        letters.put(12, "T");
        letters.put(15, "Qa");
        letters.put(18, "Qi");
        letters.put(21, "Sx");
        letters.put(24, "Sp");
        letters.put(27, "Oc");
        letters.put(30, "No");
        letters.put(33, "Dc");
        letters.put(36, "Ud");
        letters.put(39, "Dd");
        letters.put(42, "Td");
        letters.put(45, "Qad");
    }

    public static String formatCoins(double coins){
        if (coins < 1000) {
            return String.format("%.2f", coins);
        }
        int exponent = (int) (Math.log10(coins) / 3.0) * 3;
        double formattedCount = Math.floor(coins / Math.pow(10, exponent) * 10) / 10.0;
        return String.format("%.1f%s", formattedCount, letters.get(exponent));
    }

    public static String simpleFormatCoins(double coins){
        return String.format("%,.2f", coins);
    }
}
