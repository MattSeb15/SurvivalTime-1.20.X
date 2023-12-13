package net.most.survivaltimemod.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.most.survivaltimemod.block.custom.HourglassHubStationBlock;
import net.most.survivaltimemod.block.entity.HourglassHubStationBlockEntity;

import java.util.List;


public class HourglassHubStationBlockRenderer implements BlockEntityRenderer<HourglassHubStationBlockEntity> {
    private final ItemRenderer itemRenderer;

    public HourglassHubStationBlockRenderer(BlockEntityRendererProvider.Context context) {
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(HourglassHubStationBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer,
                       int pPackedLight, int pPackedOverlay) {

        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

        ItemStack resultStack = pBlockEntity.getItemResultStack();


        List<ItemStack> stacks = List.of(resultStack);
        float angleCTD = pBlockEntity.getBlockState().getValue(HourglassHubStationBlock.FACING).toYRot();
        float translateX = 0.5f;
        float translateY = 1.0f;
        float translateZ = 0.5f;

        float scale = 0.3f;


        for (ItemStack stack : stacks) {
            pPoseStack.pushPose();
            pPoseStack.translate(translateX, translateY, translateZ);
            pPoseStack.scale(scale, scale, scale);
            pPoseStack.mulPose(Axis.YN.rotationDegrees(angleCTD));
//            pPoseStack.mulPose(Axis.XP.rotationDegrees(90));
            pPoseStack.mulPose(Axis.YP.rotationDegrees(180));

            itemRenderer.renderStatic(
                    stack,
                    ItemDisplayContext.FIXED,
                    getLightLevel(pBlockEntity.getLevel(), pBlockEntity.getBlockPos()),
                    OverlayTexture.NO_OVERLAY,
                    pPoseStack,
                    pBuffer,
                    pBlockEntity.getLevel(),
                    1);
            pPoseStack.popPose();

        }


    }

    private int getLightLevel(Level level, BlockPos pos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);

    }
}
