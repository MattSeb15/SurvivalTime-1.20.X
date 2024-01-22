package net.most.survivaltimemod.sound;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.most.survivaltimemod.SurvivalTimeMod;

public class ModSounds {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, SurvivalTimeMod.MOD_ID);

    public static final RegistryObject<SoundEvent> TIMEKEEPER_AMBIENT = registerSoundEvents("timekeeper.ambient");
    public static final RegistryObject<SoundEvent> TIMEKEEPER_BAD = registerSoundEvents("timekeeper.bad");
    public static final RegistryObject<SoundEvent> TIMEKEEPER_GOOD = registerSoundEvents("timekeeper.good");

    public static final RegistryObject<SoundEvent> TIME_DEVOURER_AMBIENT = registerSoundEvents("time_devourer.ambient");
    public static final RegistryObject<SoundEvent> TIME_DEVOURER_HURT = registerSoundEvents("time_devourer.hurt");
    public static final RegistryObject<SoundEvent> PURIFIER_IMPACT = registerSoundEvents("purifier.impact");
    public static final RegistryObject<SoundEvent> PROSPERITY_IMPACT = registerSoundEvents("prosperity.impact");
    public static final RegistryObject<SoundEvent> CURSE_IMPACT = registerSoundEvents("curse.impact");
    public static final RegistryObject<SoundEvent> NO_RESULT_IMPACT = registerSoundEvents("no_result.impact");
    public static final RegistryObject<SoundEvent> HOURGLASS_HUB_SUCCESS = registerSoundEvents("hourglass_hub.success");

    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(SurvivalTimeMod.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }


}
