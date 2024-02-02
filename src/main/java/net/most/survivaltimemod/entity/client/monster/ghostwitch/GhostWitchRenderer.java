package net.most.survivaltimemod.entity.client.monster.ghostwitch;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.GhastRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Ghast;
import net.most.survivaltimemod.SurvivalTimeMod;
import org.jetbrains.annotations.NotNull;

public class GhostWitchRenderer extends GhastRenderer {


    private static final ResourceLocation GHOSTWITCH_LOCATION = new ResourceLocation(SurvivalTimeMod.MOD_ID, "textures/entity/ghostwitch/ghostwitch.png");
    private static final ResourceLocation GHOSTWITCH_SHOOTING_LOCATION = new ResourceLocation(SurvivalTimeMod.MOD_ID, "textures/entity/ghostwitch/ghostwitch_shooting.png");

    public GhostWitchRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull Ghast pEntity) {
        return pEntity.isCharging() ? GHOSTWITCH_SHOOTING_LOCATION : GHOSTWITCH_LOCATION;
    }

    @Override
    protected void scale(@NotNull Ghast pLivingEntity, @NotNull PoseStack pPoseStack, float pPartialTickTime) {
        pPoseStack.scale(1F, 1F, 1F);
    }
}
