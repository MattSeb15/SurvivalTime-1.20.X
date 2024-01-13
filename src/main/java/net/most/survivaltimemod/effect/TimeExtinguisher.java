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

public class TimeExtinguisher extends MobEffect {
    public TimeExtinguisher(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }


    int tickCount = 0;
    int instanceDuration = 0;

    @Override
    public void applyEffectTick(@NotNull LivingEntity pLivingEntity, int pAmplifier) {

        if (pLivingEntity instanceof ServerPlayer player) {
            super.applyEffectTick(pLivingEntity, pAmplifier);

            player.getCapability(PlayerTimeProvider.PLAYER_TIME_CAPABILITY).ifPresent(playerTime -> {
                if (playerTime.getTimeExtinguisherEffectTickCount() != 0) {
                    tickCount = playerTime.getTimeExtinguisherEffectTickCount();
                }
                tickCount++;
                playerTime.setTimeExtinguisherTickCount(tickCount, player);
                if (tickCount == 1) {

                    if (playerTime.getTimeExtinguisherEffectDuration() != 0) {
                        instanceDuration = playerTime.getTimeExtinguisherEffectDuration();
                    } else {
                        MobEffectInstance instance = player.getEffect(this);
                        if (instance != null) {
                            instanceDuration = instance.getDuration() / 20;
                        }
                        playerTime.setTimeExtinguisherEffectDuration(instanceDuration, player);
                    }

                }
                player.displayClientMessage(Component.literal("DEBUG TC" + playerTime.getTimeExtinguisherEffectTickCount() + "IDUR: " + playerTime.getTimeExtinguisherEffectDuration()), false);

                if (tickCount >= playerTime.getTimeExtinguisherEffectDuration() && playerTime.getTimeExtinguisherEffectDuration() != 0) {
                    int decrement = tickCount * (pAmplifier + 1);
                    playerTime.decrementTime(decrement, player, true);
                    player.displayClientMessage(Component.literal("DEBUG DECREMENT: " + decrement), false);

                    player.level().playSound(null, player.blockPosition(), SoundEvents.BLAZE_DEATH,
                            SoundSource.PLAYERS, 1.0f, 1.0f);
                    playerTime.setTimeExtinguisherEffectDuration(0, player);
                    playerTime.setTimeExtinguisherTickCount(0, player);
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
