package net.most.survivaltimemod.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.most.survivaltimemod.data.FormatTimeType;
import net.most.survivaltimemod.sound.ModSounds;
import net.most.survivaltimemod.time.PlayerTimeProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

public class OpalSword extends SwordItem {

    float timeStealMultiplier;
    float probability;
    Random random = new Random();

    public OpalSword(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, float timeStealMultiplier, float probability,
                     Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
        this.timeStealMultiplier = timeStealMultiplier;
        this.probability = probability;
    }

    public float getTimeStealMultiplier() {
        return timeStealMultiplier;
    }

    public float getProbability() {
        return probability;
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack pStack, @NotNull LivingEntity pTarget, @NotNull LivingEntity pAttacker) {
        if (pAttacker instanceof ServerPlayer serverPlayer) {

            if (random.nextFloat() <= probability) {
                if (pTarget instanceof ServerPlayer targetPlayer) {

                    targetPlayer.getCapability(PlayerTimeProvider.PLAYER_TIME_CAPABILITY).ifPresent(ptargetPlayerTime -> {
                        float playerIncrease =
                                (60 * timeStealMultiplier * this.getDamage() * (ptargetPlayerTime.getTime() / ptargetPlayerTime.getMaxTime()));
                        if (playerIncrease <= 0) return;
                        ptargetPlayerTime.decrementTime(playerIncrease, targetPlayer, true);
                        serverPlayer.getCapability(PlayerTimeProvider.PLAYER_TIME_CAPABILITY).ifPresent(pAttackerPlayerTime -> {
                            pAttackerPlayerTime.incrementTime(playerIncrease, serverPlayer);
                        });

                    });

                } else {

                    serverPlayer.getCapability(PlayerTimeProvider.PLAYER_TIME_CAPABILITY).ifPresent(playerTime -> {
                        float increase = (float) (60 * timeStealMultiplier * this.getDamage() * 0.6 * (pTarget.getHealth() / pTarget.getMaxHealth()));
                        if (increase <= 0) return;
                        playerTime.incrementTime(increase, serverPlayer);
                    });


                }
                serverPlayer.level().playSound(null, serverPlayer.blockPosition(), ModSounds.TIME_STEAL_IMPACT.get(), serverPlayer.getSoundSource(),
                        1.0f, 1.0f);
            }


        }
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents,
                                @NotNull TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("item.survival_time_mod.opal_sword.tooltip.time_steal_multiplier", getTimeStealMultiplier()).withStyle(ChatFormatting.GOLD));
        pTooltipComponents.add(Component.translatable("item.survival_time_mod.opal_sword.tooltip.probability", String.format("%.2f%%",
                getProbability() * 100)).withStyle(ChatFormatting.AQUA));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
