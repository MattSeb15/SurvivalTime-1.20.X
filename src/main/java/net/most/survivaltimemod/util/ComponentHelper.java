package net.most.survivaltimemod.util;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.most.survivaltimemod.data.FormatTimeType;

public class ComponentHelper {

    public static Component getOnPlayerHurtComponent(float time, float damage, float damageMultiplier, float timeToDecrement, float preTime) {
        return Component.empty().append(
                Component.literal("PosTime: " + FormatTimeType.getFormattedStringByType(
                                FormatTimeType.DEPENDS_NAMED, time
                        ))
                        .withStyle(ChatFormatting.GOLD)
        ).append(
                Component.literal(" | ")
                        .withStyle(ChatFormatting.DARK_GRAY)
        ).append(
                Component.literal("Daño: " + damage)
                        .withStyle(ChatFormatting.DARK_PURPLE)
        ).append(
                Component.literal(" | ")
                        .withStyle(ChatFormatting.DARK_GRAY)
        ).append(
                Component.literal("Damage mult " + damageMultiplier)
                        .withStyle(ChatFormatting.YELLOW)
        ).append(
                Component.literal(" | ")
                        .withStyle(ChatFormatting.DARK_GRAY)
        ).append(
                Component.literal("Perdida: " + FormatTimeType.getFormattedStringByType(FormatTimeType.DEPENDS_NAMED,
                                timeToDecrement))
                        .withStyle(ChatFormatting.RED)
        ).append(
                Component.literal(" | ")
                        .withStyle(ChatFormatting.DARK_GRAY)
        ).append(
                Component.literal("PreTime: " + FormatTimeType.getFormattedStringByType(
                                FormatTimeType.DEPENDS_NAMED, preTime
                        ))
                        .withStyle(ChatFormatting.DARK_RED)
        );
    }
}
