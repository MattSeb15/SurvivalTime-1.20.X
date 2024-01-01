package net.most.survivaltimemod.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import net.most.survivaltimemod.SurvivalTimeMod;

import net.most.survivaltimemod.screen.renderer.ProgressDisplayTooltipArea;
import net.most.survivaltimemod.world.inventory.HourglassHubStationMenu;
import net.most.survivaltimemod.screen.renderer.EnergyDisplayTooltipArea;
import net.most.survivaltimemod.util.MouseUtil;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class HourglassHubStationScreen extends AbstractContainerScreen<HourglassHubStationMenu> {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation(SurvivalTimeMod.MOD_ID, "textures/gui/hourglass_hub_station.png");
    private EnergyDisplayTooltipArea energyDisplayTooltipArea;
    private ProgressDisplayTooltipArea progressDisplayTooltipArea;
    private int leftPos, topPos;


    public HourglassHubStationScreen(HourglassHubStationMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }


    @Override
    protected void init() {
        super.init();
        this.leftPos = (this.width - this.imageWidth) / 2;
        this.topPos = (this.height - this.imageHeight) / 2;
        this.inventoryLabelY = 10000;
        this.titleLabelY = 10000;
        assignEnergyDisplayTooltipArea();
        assignProgressDisplayTooltipArea();

    }

    private void assignProgressDisplayTooltipArea() {
        this.progressDisplayTooltipArea = new ProgressDisplayTooltipArea(
                menu.getProgress(),
                menu.getMaxProgress());
    }


    private void assignEnergyDisplayTooltipArea() {
        this.energyDisplayTooltipArea = new EnergyDisplayTooltipArea(
                leftPos + 13,
                (topPos + 11) - 18,
                menu.blockEntity.getEnergyStorage(),
                9,
                88);
    }

    @Override
    protected void renderLabels(@NotNull GuiGraphics pGuiGraphics, int pMouseX, int pMouseY) {

        renderEnergyAreaToolTip(pGuiGraphics, pMouseX, pMouseY);
        renderProgressTooltip(pGuiGraphics, pMouseX, pMouseY);

    }

    private void renderEnergyAreaToolTip(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY) {
        if (isMouseAboveArea(pMouseX, pMouseY, leftPos, topPos, 13, 11 - 18, 9, 88)) {
            pGuiGraphics.renderTooltip(this.font, energyDisplayTooltipArea.getTooltips(),
                    Optional.empty(),
                    pMouseX - leftPos,
                    pMouseY - topPos);
        }

    }

    private void renderProgressTooltip(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY) {
        if (isMouseAboveArea(pMouseX, pMouseY, leftPos, topPos, 120, 54-18, 26, 3) && menu.isCrafting()) {
            pGuiGraphics.renderTooltip(this.font, progressDisplayTooltipArea.getTooltips(),
                    Optional.empty(),
                    pMouseX - leftPos,
                    pMouseY - topPos);
        }

    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - 202) / 2;

        guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, 202);
        progressDisplayTooltipArea.setValues(menu.getProgress(), menu.getMaxProgress());

        renderProgressArrow(guiGraphics, x, y);
        energyDisplayTooltipArea.render(guiGraphics);


    }

    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y) {
        if (menu.isCrafting()) {
            guiGraphics.blit(TEXTURE, x + 120, y + 51, 176, 0, menu.getScaledProgress(), 9);
        }
    }

    @Override
    public void render(@NotNull GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pGuiGraphics);
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }

    private boolean isMouseAboveArea(int pMouseX, int pMouseY, int pX, int pY, int offsetX, int offsetY, int pWidth, int pHeight) {
        return MouseUtil.isMouseOver(pMouseX, pMouseY, pX + offsetX, pY + offsetY, pWidth, pHeight);
    }
}
