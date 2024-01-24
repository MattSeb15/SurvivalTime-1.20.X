package net.most.survivaltimemod.event;

import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.most.survivaltimemod.SurvivalTimeMod;
import net.most.survivaltimemod.entity.ModEntities;
import net.most.survivaltimemod.entity.custom.animal.TimekeeperEntity;
import net.most.survivaltimemod.entity.custom.animal.TimmyEntity;
import net.most.survivaltimemod.entity.custom.monster.TimeDevourerEntity;

@Mod.EventBusSubscriber(modid = SurvivalTimeMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(ModEntities.TIMEKEEPER.get(), TimekeeperEntity.setAttributes());
        event.put(ModEntities.TIME_DEVOURER.get(), TimeDevourerEntity.setAttributes());
        event.put(ModEntities.TIMMY.get(), TimmyEntity.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerSpawnPlacement(SpawnPlacementRegisterEvent event) {
        event.register(ModEntities.TIMEKEEPER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(ModEntities.TIME_DEVOURER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Monster::checkAnyLightMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
    }
}
