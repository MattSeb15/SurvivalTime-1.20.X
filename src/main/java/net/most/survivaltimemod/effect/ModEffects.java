package net.most.survivaltimemod.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.most.survivaltimemod.SurvivalTimeMod;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, SurvivalTimeMod.MOD_ID);

    public static final RegistryObject<MobEffect> DAMAGE_TRIGGER = MOB_EFFECTS.register("damage_time_trigger_effect",
            () -> new DamageTimeTriggerEffect(MobEffectCategory.NEUTRAL, 0xff48110c));
    public static final RegistryObject<MobEffect> HEAL_TRIGGER = MOB_EFFECTS.register("heal_time_trigger_effect",
            () -> new HealTimeTriggerEffect(MobEffectCategory.NEUTRAL, 0xff6bff7c));

    public static final RegistryObject<MobEffect> GOLDEN_TIME = MOB_EFFECTS.register("golden_time",
            () -> new GoldenTimeEffect(MobEffectCategory.BENEFICIAL, 0xfff9d026));
    public static final RegistryObject<MobEffect> TIME_TEAR = MOB_EFFECTS.register("time_tear",
            () -> new TimeTearEffect(MobEffectCategory.HARMFUL, 0xff2c2222));

    public static final RegistryObject<MobEffect> TIME_EXTINGUISHER = MOB_EFFECTS.register("time_extinguisher",
            () -> new TimeExtinguisher(MobEffectCategory.HARMFUL, 0xff0f0303));

    public static final RegistryObject<MobEffect> TIME_IGNITE = MOB_EFFECTS.register("time_ignite",
            () -> new TimeIgnite(MobEffectCategory.BENEFICIAL, 0xffa55f22));

    public static final RegistryObject<MobEffect> INSTANT_TIME_DAMAGE = MOB_EFFECTS.register("instant_time_damage",
            () -> new InstantTimeDamage(MobEffectCategory.HARMFUL, 0xff4e1610));
    public static final RegistryObject<MobEffect> INSTANT_TIME_REGEN = MOB_EFFECTS.register("instant_time_regen",
            () -> new InstantTimeRegen(MobEffectCategory.BENEFICIAL, 0xff6bff7c));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }

}
