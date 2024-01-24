package net.most.survivaltimemod.entity.client.animal.timmy;


import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ParrotRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Parrot;
import net.most.survivaltimemod.SurvivalTimeMod;
import org.jetbrains.annotations.NotNull;


public class TimmyRenderer extends ParrotRenderer {

    public TimmyRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull Parrot pEntity) {
        return new ResourceLocation(SurvivalTimeMod.MOD_ID, "textures/entity/timmy.png");
    }
}
