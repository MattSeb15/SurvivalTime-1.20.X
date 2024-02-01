package net.most.survivaltimemod.effect;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.most.survivaltimemod.time.PlayerTimeProvider;
import org.jetbrains.annotations.NotNull;

public class RandomDecrementTimeEffect extends MobEffect {

    float probability = 0.5f;
    float multiplier = 1.0f;
    public RandomDecrementTimeEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    public RandomDecrementTimeEffect setProbability(float probability) {
        this.probability = probability;
        return this;
    }

    public RandomDecrementTimeEffect setMultiplier(float multiplier) {
        this.multiplier = multiplier;
        return this;
    }

    public float getProbability() {
        return probability;
    }

    public float getMultiplier() {
        return multiplier;
    }

    @Override
    public void applyEffectTick(@NotNull LivingEntity pLivingEntity, int pAmplifier) {
        if (pLivingEntity instanceof ServerPlayer player) {
            super.applyEffectTick(pLivingEntity, pAmplifier);
            if(Math.random() > getProbability()) {
                return;
            }

            float increment = 3.0f * getMultiplier() * (pAmplifier + 1);
            player.getCapability(PlayerTimeProvider.PLAYER_TIME_CAPABILITY).ifPresent(playerTime -> {
                playerTime.decrementTime(increment, player, true);
            });
        }
    }


    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}
