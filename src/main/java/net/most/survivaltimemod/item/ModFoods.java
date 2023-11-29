package net.most.survivaltimemod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;


public class ModFoods {
    public static final FoodProperties TEMPORAL_TUBER = (new FoodProperties.Builder())
            .nutrition(2)
            .saturationMod(0.4F)
            .build();


    public static final FoodProperties TEMPORAL_TUBER_COOKED = (new FoodProperties.Builder())
            .nutrition(6)
            .saturationMod(0.8F)
            .effect(() -> new MobEffectInstance(MobEffects.GLOWING, 100), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 100,2), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100,2), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 20,2), 0.5F)
            .alwaysEat()
            .build();

    public static final FoodProperties TEMPORAL_TUBER_ROTTEN = (new FoodProperties.Builder())
            .nutrition(1)
            .saturationMod(0.1F)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 200,2), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.BLINDNESS, 200,2), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.LEVITATION, 250,2), 1.0F)
            .fast()
            .alwaysEat()
            .build();

}
