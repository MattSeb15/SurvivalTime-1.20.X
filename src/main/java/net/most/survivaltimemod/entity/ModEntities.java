package net.most.survivaltimemod.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.most.survivaltimemod.SurvivalTimeMod;
import net.most.survivaltimemod.entity.custom.monster.TimeDevourerEntity;
import net.most.survivaltimemod.entity.custom.animal.TimekeeperEntity;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, SurvivalTimeMod.MOD_ID);


    public static final RegistryObject<EntityType<TimekeeperEntity>> TIMEKEEPER = ENTITY_TYPES.register("timekeeper",
            () -> EntityType.Builder.of(TimekeeperEntity::new, MobCategory.CREATURE)
                    .sized(0.9f, 1.75f)
                    .build(new ResourceLocation(SurvivalTimeMod.MOD_ID, "timekeeper").toString())
    );
    public static final RegistryObject<EntityType<TimeDevourerEntity>> TIME_DEVOURER = ENTITY_TYPES.register("time_devourer",
            () -> EntityType.Builder.of(TimeDevourerEntity::new, MobCategory.MONSTER)
                    .sized(0.6f, 1.8f)
                    .build(new ResourceLocation(SurvivalTimeMod.MOD_ID, "time_devourer").toString())
    );

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }

}
