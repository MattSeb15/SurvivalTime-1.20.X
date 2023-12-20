package net.most.survivaltimemod.util;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;

import java.util.function.Consumer;

public class GuiHelper {

    public static int playerInventoryStartX = 8;
    public static int playerInventoryStartY = 84;

    public static int playerHotBarStartX = playerInventoryStartX;
    public static int playerHotBarStartY = 142;

    public static void addPlayerInventory(Inventory playerInventory, Consumer<Slot> slotConsumer) {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                slotConsumer.accept(getInventorySlot(playerInventory, j, i));
//                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, startX + j * 18, startY + i * 18));
            }
        }
    }

    public static void addPlayerHotBar(Inventory playerInventory, Consumer<Slot> slotConsumer) {

        for (int i = 0; i < 9; ++i) {
            slotConsumer.accept(getHotBarSlot(playerInventory, i));
//            this.addSlot(new Slot(playerInventory, i, pXWithHB(i), playerHotBarStartY));
        }
    }

    private static Slot getHotBarSlot(Inventory playerInventory, int i) {
        return new Slot(playerInventory, i, pXWithHB(i), playerHotBarStartY);
    }

    private static int pXWithHB(int i) {
        return playerHotBarStartX + i * 18;
    }


    private static Slot getInventorySlot(Inventory playerInventory, int i, int j) {
        return new Slot(playerInventory, pSlotIndexWith(i, j), pXWith(i), pYWith(j));
    }

    private static int pSlotIndexWith(int i, int j) {
        return i + j * 9 + 9;
    }

    private static int pXWith(int j) {
        return playerInventoryStartX + j * 18;
    }

    private static  int pYWith(int i) {
        return playerInventoryStartY + i * 18;
    }
}
