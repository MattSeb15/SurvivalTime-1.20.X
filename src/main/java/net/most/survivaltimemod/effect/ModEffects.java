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
            () -> new DamageTimeTriggerEffect(MobEffectCategory.NEUTRAL, 0xff6bff7c));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }

}
