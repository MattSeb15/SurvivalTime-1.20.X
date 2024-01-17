package net.most.survivaltimemod.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.most.survivaltimemod.SurvivalTimeMod;
import net.most.survivaltimemod.entity.custom.TimekeeperEntity;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, SurvivalTimeMod.MOD_ID);


    public static final RegistryObject<EntityType<TimekeeperEntity>> TIMEKEEPER = ENTITY_TYPES.register("timekeeper",
            () -> EntityType.Builder.of(TimekeeperEntity::new, MobCategory.CREATURE)
                    .sized(1.0f, 1.75f)
                    .build(new ResourceLocation(SurvivalTimeMod.MOD_ID, "timekeeper").toString())
    );

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }

}
