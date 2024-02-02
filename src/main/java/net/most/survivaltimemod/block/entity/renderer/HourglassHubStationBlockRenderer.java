package net.most.survivaltimemod.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
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
import org.jetbrains.annotations.NotNull;

import java.util.List;


public class HourglassHubStationBlockRenderer implements BlockEntityRenderer<HourglassHubStationBlockEntity> {
    private final ItemRenderer itemRenderer;
    private final BlockEntityRendererProvider.Context context;

    public HourglassHubStationBlockRenderer(BlockEntityRendererProvider.Context context) {
        this.itemRenderer = context.getItemRenderer();
        this.context = context;
    }

    @Override
    public void render(HourglassHubStationBlockEntity pBlockEntity, float pPartialTick, @NotNull PoseStack pPoseStack, @NotNull MultiBufferSource pBuffer, int pPackedLight,
                       int pPackedOverlay) {

        Level level = pBlockEntity.getLevel();
        if (level == null) return;

        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

        ItemStack resultStack = pBlockEntity.getItemResultStack();
        ItemStack noEnergyStack = new ItemStack(Items.BARRIER);
        if (resultStack.isEmpty()) return;


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


            itemRenderer.renderStatic(stack, ItemDisplayContext.FIXED, getLightLevel(level, pBlockEntity.getBlockPos()), OverlayTexture.NO_OVERLAY, pPoseStack, pBuffer,
                    level, 1);


            int count = stack.getCount();
            if(count> 1){
                Font font = context.getFont();
                pPoseStack.scale(0.05f, -0.05f, 0.05f);
                pPoseStack.translate(-10f + font.width(count + "") / 2f, -16.0f, 0.0f);
                font.drawInBatch(count + "", 0, 0, 0xffffff, false, pPoseStack.last().pose(), pBuffer, Font.DisplayMode.NORMAL, 0, getLightLevel(level,
                        pBlockEntity.getBlockPos()));
                pPoseStack.mulPose(Axis.YP.rotationDegrees(180));
                pPoseStack.translate(-5f - font.width(count + "") / 2f, 0, 0);
                font.drawInBatch(count + "", 0, 0, 0xffffff, false, pPoseStack.last().pose(), pBuffer, Font.DisplayMode.NORMAL, 0, getLightLevel(level,
                        pBlockEntity.getBlockPos()));
            }
            pPoseStack.popPose();

        }
        if(pBlockEntity.canCraftButNoEnergy()){
            pPoseStack.pushPose();
            pPoseStack.translate(0.5, 1.5, 0.5);
            pPoseStack.scale(0.3f, 0.3f, 0.3f);
            pPoseStack.mulPose(Axis.YN.rotationDegrees(angleCTD));
            pPoseStack.mulPose(Axis.YP.rotationDegrees(180));
            itemRenderer.renderStatic(noEnergyStack, ItemDisplayContext.FIXED, getLightLevel(level, pBlockEntity.getBlockPos()), OverlayTexture.NO_OVERLAY, pPoseStack, pBuffer,
                    level, 1);
            pPoseStack.popPose();
        }


    }

    private int getLightLevel(Level level, BlockPos pos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);

    }
}
