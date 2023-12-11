package net.most.survivaltimemod.block.entity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.most.survivaltimemod.SurvivalTimeMod;
import net.most.survivaltimemod.block.ModBlocks;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, SurvivalTimeMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<HourglassHubStationBlockEntity>> HOURGLASS_HUB_STATION =
            BLOCK_ENTITIES.register("hourglass_hub_station",
                    () -> BlockEntityType.Builder.of(HourglassHubStationBlockEntity::new,
                            ModBlocks.HOURGLASS_HUB_STATION.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
