package net.most.survivaltimemod.potion;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.most.survivaltimemod.SurvivalTimeMod;
import net.most.survivaltimemod.effect.ModEffects;
import net.most.survivaltimemod.util.MathUtil;

public class ModPotions {
    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(ForgeRegistries.POTIONS, SurvivalTimeMod.MOD_ID);

    static int goldenBD = 1200;
    static int timeTearBD = 1200;
    static String goldenTimeName = "golden_time_potion";
    static String timeTearName = "time_tear_potion";
    static String instantTimeDamageName = "instant_time_damage_potion";
    static String instantTimeRegenName = "instant_time_regen_potion";


    public static final RegistryObject<Potion> GOLDEN_TIME = POTIONS.register(goldenTimeName,
            () -> new Potion(new MobEffectInstance(ModEffects.GOLDEN_TIME.get(), goldenBD)));
    public static final RegistryObject<Potion> LONG_GOLDEN_TIME = POTIONS.register(getLongName(goldenTimeName),
            () -> new Potion(goldenTimeName, new MobEffectInstance(ModEffects.GOLDEN_TIME.get(), getLongDuration(goldenBD))));
    public static final RegistryObject<Potion> STRONG_GOLDEN_TIME = POTIONS.register(getStrongName(goldenTimeName),
            () -> new Potion(goldenTimeName, new MobEffectInstance(ModEffects.GOLDEN_TIME.get(), getStrongDuration(goldenBD), 1)));
    public static final RegistryObject<Potion> TIME_TEAR = POTIONS.register(timeTearName,
            () -> new Potion(new MobEffectInstance(ModEffects.TIME_TEAR.get(), timeTearBD)));
    public static final RegistryObject<Potion> LONG_TIME_TEAR = POTIONS.register(getLongName(timeTearName),
            () -> new Potion(timeTearName, new MobEffectInstance(ModEffects.TIME_TEAR.get(), getLongDuration(timeTearBD))));
    public static final RegistryObject<Potion> STRONG_TIME_TEAR = POTIONS.register(getStrongName(timeTearName),
            () -> new Potion(timeTearName, new MobEffectInstance(ModEffects.TIME_TEAR.get(), getStrongDuration(timeTearBD), 1)));
    public static final RegistryObject<Potion> INSTANT_TIME_DAMAGE = POTIONS.register(instantTimeDamageName,
            () -> new Potion(new MobEffectInstance(ModEffects.INSTANT_TIME_DAMAGE.get(), 1)));
    public static final RegistryObject<Potion> STRONG_INSTANT_TIME_DAMAGE = POTIONS.register(getStrongName(instantTimeDamageName),
            () -> new Potion(instantTimeDamageName, new MobEffectInstance(ModEffects.INSTANT_TIME_DAMAGE.get(), 1, 1)));
    public static final RegistryObject<Potion> INSTANT_TIME_REGEN = POTIONS.register(instantTimeRegenName,
            () -> new Potion(new MobEffectInstance(ModEffects.INSTANT_TIME_REGEN.get(), 1)));
    public static final RegistryObject<Potion> STRONG_INSTANT_TIME_REGEN = POTIONS.register(getStrongName(instantTimeRegenName),
            () -> new Potion(instantTimeRegenName, new MobEffectInstance(ModEffects.INSTANT_TIME_REGEN.get(), 1, 1)));

    private static String getLongName(String name) {
        return "long_" + name;
    }

    private static String getStrongName(String name) {
        return "strong_" + name;
    }

    private static int getLongDuration(int baseDuration) {
        return (baseDuration * 2) + 600;
    }

    private static int getStrongDuration(int baseDuration) {
        return MathUtil.percentageValue(baseDuration, 75);
    }

    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }
}
