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

    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(SurvivalTimeMod.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }


}
