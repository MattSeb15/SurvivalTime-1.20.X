package net.most.survivaltimemod.effect;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.most.survivaltimemod.time.PlayerTimeProvider;
import org.jetbrains.annotations.NotNull;

public class TimeIgnite extends MobEffect {
    public TimeIgnite(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    int instanceDuration = 0;
    int tickCount = 0;

    @Override
    public void applyEffectTick(@NotNull LivingEntity pLivingEntity, int pAmplifier) {

        if (pLivingEntity instanceof ServerPlayer player) {

            super.applyEffectTick(pLivingEntity, pAmplifier);

            player.getCapability(PlayerTimeProvider.PLAYER_TIME_CAPABILITY).ifPresent(playerTime -> {
                if (playerTime.getTimeIgniteEffectTickCount() != 0) {
                    tickCount = playerTime.getTimeIgniteEffectTickCount();
                }
                tickCount++;
                playerTime.setTimeIgniteTickCount(tickCount, player);
                if (tickCount == 1) {

                    if (playerTime.getTimeIgniteEffectDuration() != 0) {
                        instanceDuration = playerTime.getTimeIgniteEffectDuration();
                    } else {
                        MobEffectInstance instance = player.getEffect(this);
                        if (instance != null) {
                            instanceDuration = instance.getDuration() / 20;
                        }
                        playerTime.setTimeIgniteEffectDuration(instanceDuration, player);
                    }

                }
                player.displayClientMessage(Component.literal("DEBUG TC" + playerTime.getTimeIgniteEffectTickCount() + "IDUR: " + playerTime.getTimeIgniteEffectDuration()), false);

                if (tickCount >= playerTime.getTimeIgniteEffectDuration() && playerTime.getTimeIgniteEffectDuration() != 0) {
                    int increment = tickCount * (pAmplifier + 1);
                    playerTime.incrementTime(increment, player, true);
                    player.displayClientMessage(Component.literal("DEBUG INCREMENT: " + increment), false);

                    player.level().playSound(null, player.blockPosition(), SoundEvents.FIRE_EXTINGUISH,
                            SoundSource.PLAYERS, 1.0f, 1.0f);
                    playerTime.setTimeIgniteEffectDuration(0, player);
                    playerTime.setTimeIgniteTickCount(0, player);
                    tickCount = 0;
                    instanceDuration = 0;
                }
            });




        }
    }

    @Override
    public void removeAttributeModifiers(@NotNull LivingEntity pLivingEntity, @NotNull AttributeMap pAttributeMap, int pAmplifier) {
        super.removeAttributeModifiers(pLivingEntity, pAttributeMap, pAmplifier);
        tickCount = 0;
        instanceDuration = 0;
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return pDuration % 20 == 0;
    }
}
