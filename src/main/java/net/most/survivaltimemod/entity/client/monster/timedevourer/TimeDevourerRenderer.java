package net.most.survivaltimemod.entity.client.monster.timedevourer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.most.survivaltimemod.SurvivalTimeMod;
import net.most.survivaltimemod.entity.custom.monster.TimeDevourerEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.BlockAndItemGeoLayer;

public class TimeDevourerRenderer extends GeoEntityRenderer<TimeDevourerEntity> {

    protected ItemStack mainHandItem;
    protected ItemStack offhandItem;
    public TimeDevourerRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new TimeDevourerModel());
        this.addRenderLayer(new BlockAndItemGeoLayer<>(this) {
            @Nullable
            @Override
            protected ItemStack getStackForBone(GeoBone bone, TimeDevourerEntity animatable) {
                return switch (bone.getName()) {
                    case "arm_2" -> animatable.isLeftHanded() ? TimeDevourerRenderer.this.mainHandItem : TimeDevourerRenderer.this.offhandItem;
                    case "arm_1" -> animatable.isLeftHanded() ? TimeDevourerRenderer.this.offhandItem : TimeDevourerRenderer.this.mainHandItem;
                    default -> null;
                };
            }

            @Override
            protected ItemDisplayContext getTransformTypeForStack(GeoBone bone, ItemStack stack, TimeDevourerEntity animatable) {

                return switch (bone.getName()) {
                    case "arm_2", "arm_1" -> ItemDisplayContext.THIRD_PERSON_RIGHT_HAND;
                    default -> ItemDisplayContext.NONE;
                };
            }

            @Override
            protected void renderStackForBone(PoseStack poseStack, GeoBone bone, ItemStack stack, TimeDevourerEntity animatable, MultiBufferSource bufferSource, float partialTick, int packedLight, int packedOverlay) {
                if (stack == TimeDevourerRenderer.this.mainHandItem) {
                    poseStack.mulPose(Axis.XP.rotationDegrees(-90.0F));
                    poseStack.translate(0, 0.1, -0.5);
                    if (stack.getItem() instanceof ShieldItem) {
                        poseStack.translate(0.0, 0.125, -0.25);
                    }
                } else if (stack == TimeDevourerRenderer.this.offhandItem) {
                    poseStack.mulPose(Axis.XP.rotationDegrees(-90.0F));
                    if (stack.getItem() instanceof ShieldItem) {
                        poseStack.translate(0.0, 0.125, 0.25);
                        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
                    }
                }

                super.renderStackForBone(poseStack, bone, stack, animatable, bufferSource, partialTick, packedLight, packedOverlay);
            }
        });
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull TimeDevourerEntity animatable) {
        return new ResourceLocation(SurvivalTimeMod.MOD_ID, "textures/entity/time_devourer.png");
    }

    @Override
    public void render(@NotNull TimeDevourerEntity entity, float entityYaw, float partialTick, @NotNull PoseStack poseStack, @NotNull MultiBufferSource bufferSource,
                       int packedLight) {
        if(entity.isBaby()){
            poseStack.scale(0.4f, 0.4f, 0.4f);
        }
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

    @Override
    public void preRender(PoseStack poseStack, TimeDevourerEntity animatable, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
        this.mainHandItem = animatable.getMainHandItem();
        this.offhandItem = animatable.getOffhandItem();
    }
}
