package net.most.survivaltimemod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;


public class ModFoods {
    public static final FoodProperties TEMPORAL_TUBER = (new FoodProperties.Builder())
            .nutrition(1)
            .saturationMod(0.2F)
            .alwaysEat()
            .build();


    public static final FoodProperties TEMPORAL_TUBER_COOKED = (new FoodProperties.Builder())
            .nutrition(4)
            .saturationMod(0.4F)
            .effect(() -> new MobEffectInstance(MobEffects.GLOWING, 20*5), 0.2F)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20*5, 1), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 20, 2), 0.5F)
            .alwaysEat()
            .build();

    public static final FoodProperties TEMPORAL_TUBER_ROTTEN = (new FoodProperties.Builder())
            .nutrition(1)
            .saturationMod(0.1F)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 20*10, 2), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.BLINDNESS, 20*10, 2), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.LEVITATION, 20*12, 2), 0.8F)
            .fast()
            .alwaysEat()
            .build();

}
