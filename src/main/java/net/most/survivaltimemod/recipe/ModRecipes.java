package net.most.survivaltimemod.recipe;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.most.survivaltimemod.SurvivalTimeMod;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, SurvivalTimeMod.MOD_ID);

    public static final RegistryObject<RecipeSerializer<HourglassHubStationShapelessRecipe>> HOURGLASS_HUB_SHAPELESS_SERIALIZER =
            SERIALIZERS.register("hourglass_hub_shapeless", () -> HourglassHubStationShapelessRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<HourglassHubStationShapedRecipe>> HOURGLASS_HUB_SHAPED_SERIALIZER =
            SERIALIZERS.register("hourglass_hub_shaped", () -> HourglassHubStationShapedRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
