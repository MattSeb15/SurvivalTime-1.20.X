package net.most.survivaltimemod.effect;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.InstantenousMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.most.survivaltimemod.time.PlayerTimeProvider;
import org.jetbrains.annotations.NotNull;

public class InstantTimeDamage extends InstantenousMobEffect {
    public InstantTimeDamage(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public void applyEffectTick(@NotNull LivingEntity pLivingEntity, int pAmplifier) {
        if (pLivingEntity instanceof ServerPlayer player) {
            super.applyEffectTick(pLivingEntity, pAmplifier);
            float decrement = 60.0f * 15 * (pAmplifier + 1);
            player.getCapability(PlayerTimeProvider.PLAYER_TIME_CAPABILITY).ifPresent(playerTime -> {
                playerTime.decrementTime(decrement, player, true);
            });
        }
    }
}
