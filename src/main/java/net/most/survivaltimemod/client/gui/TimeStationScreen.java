package net.most.survivaltimemod.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.most.survivaltimemod.SurvivalTimeMod;
import net.most.survivaltimemod.item.custom.LostTimeSphereItem;
import net.most.survivaltimemod.networking.ModMessages;
import net.most.survivaltimemod.networking.packet.TimeStationButtonC2SPacket;
import net.most.survivaltimemod.world.inventory.TimeStationMenu;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class TimeStationScreen extends AbstractContainerScreen<TimeStationMenu> {
    private final static HashMap<String, Object> guistate = TimeStationMenu.guistate;
    private final Level world;
    private final int x, y, z;
    private final Player player;
    Button button;
    private int infoCurrentTimeValueSlot = 0;
    private int infoCurrentLapisloopiumCost = 0;
    private int infoDecrementTime = 0;
    private int infoResultItemTime = 0;


    public TimeStationScreen(TimeStationMenu container, Inventory inventory, Component text) {
        super(container, inventory, text);
        this.world = container.world;
        this.x = container.x;
        this.y = container.y;
        this.z = container.z;
        this.player = container.entity;
    }

    private static final ResourceLocation TEXTURE =
            new ResourceLocation(SurvivalTimeMod.MOD_ID, "textures/gui/time_station_gui.png");

    @Override
    public void render(@NotNull GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pGuiGraphics);
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        pGuiGraphics.blit(TEXTURE,
                this.leftPos,
                this.topPos,
                0,
                0,
                this.imageWidth,
                this.imageHeight
        );

        pGuiGraphics.drawCenteredString(this.font, "" + infoCurrentTimeValueSlot, this.leftPos + 85, this.topPos + 18, 0xFFC96400);
        pGuiGraphics.drawCenteredString(this.font, "" + infoCurrentLapisloopiumCost, this.leftPos + 85, this.topPos + 53, 0xFF521CAC);
        pGuiGraphics.drawCenteredString(this.font, "" + infoDecrementTime, this.leftPos + 110, this.topPos + 33, 0xFFdee58c);
        pGuiGraphics.drawCenteredString(this.font, "" + infoResultItemTime, this.leftPos + 151, this.topPos + 42, 0xFF741a65);
        RenderSystem.disableBlend();
    }

    private int secondsToHours(int seconds) {
        if (seconds < 0)
            return 0;
        if (seconds < 3600)
            return 1;

        return seconds / 3600;
    }

    @Override
    public boolean keyPressed(int pKeyCode, int pScanCode, int pModifiers) {
        if (pKeyCode == 256) {
            this.minecraft.player.closeContainer();
            return true;
        }
        return super.keyPressed(pKeyCode, pScanCode, pModifiers);
    }


    @Override
    protected void containerTick() {
        super.containerTick();
        if (player == null)
            return;
        if (player.containerMenu instanceof Supplier<?> _current && _current.get() instanceof Map<?, ?> _slots) {
            ItemStack lost_sphere_slot_stack = ((Slot) _slots.get(0)).getItem();
            ItemStack lapisloopium_slot = ((Slot) _slots.get(1)).getItem();


            int toAddTimeFlag = 0;
            if (!lost_sphere_slot_stack.isEmpty()) {
                if (!lost_sphere_slot_stack.hasTag()) {
                    toAddTimeFlag = 1;
                    infoCurrentTimeValueSlot = 0;
                } else {
                    infoCurrentTimeValueSlot = secondsToHours(lost_sphere_slot_stack.getOrCreateTag().getInt(LostTimeSphereItem.TIME_VALUE_TAG));
                }

            } else {
                infoCurrentTimeValueSlot = 0;
            }
            if (!button.isActive()) {
                return;
            }

            if (!lapisloopium_slot.isEmpty() && lapisloopium_slot.getCount() >= 10) {
                int maxTimeHours = 10;


                infoCurrentLapisloopiumCost = Math.min(lapisloopium_slot.getCount() / 10, maxTimeHours) * 10;

            } else {
                infoCurrentLapisloopiumCost = 0;
            }

            if (infoCurrentLapisloopiumCost > 0) {

                infoDecrementTime = Math.floorDiv(lapisloopium_slot.getCount(), 10);
            } else {
                infoDecrementTime = 0;
            }

            if (infoCurrentLapisloopiumCost > 0 || toAddTimeFlag > 0) {
                int toInfoResultItemTime = infoCurrentTimeValueSlot + infoDecrementTime;
                infoResultItemTime = Math.min(toInfoResultItemTime, 10);

            } else {
                infoResultItemTime = 0;
            }

            button.active = isButtonActive(lost_sphere_slot_stack, lapisloopium_slot);

        }
    }

    private boolean isButtonActive(ItemStack lost_sphere_slot_stack, ItemStack lapisloopium_slot) {
        return !lost_sphere_slot_stack.isEmpty() &&
                !lapisloopium_slot.isEmpty() &&
                lapisloopium_slot.getCount() >= 10 &&
                lost_sphere_slot_stack.getOrCreateTag().getInt(LostTimeSphereItem.TIME_VALUE_TAG) < 3600 * 10;
    }

    @Override
    protected void init() {
        super.init();
//        this.titleLabelY = 1000000;
        this.inventoryLabelY = 1000000;
        button = Button.builder(Component.translatable("gui.time_station_gui.confirm_button"), e -> {
            if (true) {
                ModMessages.sendToServer(new TimeStationButtonC2SPacket(0, x, y, z));
                TimeStationButtonC2SPacket.handleButtonAction(player, 0, x, y, z);
            }
        }).bounds(this.leftPos + 12, this.topPos + 32, 40, 12).build();
        button.active = false;
        guistate.put("button:confirm_button", button);
        this.addRenderableWidget(button);
    }
}
