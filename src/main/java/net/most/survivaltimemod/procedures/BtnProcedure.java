package net.most.survivaltimemod.procedures;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.most.survivaltimemod.item.custom.LostTimeSphereItem;
import net.most.survivaltimemod.time.PlayerTimeProvider;

import java.util.Map;
import java.util.function.Supplier;

public class BtnProcedure {

    public static void execute(Player player) {
        if (player == null)
            return;
        if (player.containerMenu instanceof Supplier<?> _current && _current.get() instanceof Map<?, ?> _slots) {
            ItemStack lost_sphere_slot_stack = ((Slot) _slots.get(0)).getItem();
            ItemStack lapisloopium_slot_stack = ((Slot) _slots.get(1)).getItem();

            if (lost_sphere_slot_stack.getCount() >= 1 && lapisloopium_slot_stack.getCount() >= 10) {
                int increment = 3600;
                int maxTime = 36000;
                //10 lapisloopium = 1 hour
                int lapisloopiumCost = Math.floorDiv(lapisloopium_slot_stack.getCount(), 10);
                int pToIncrementValue = increment * lapisloopiumCost;
                int pToShrinkValue = lapisloopiumCost * 10;


                if (lost_sphere_slot_stack.getTag() != null && lost_sphere_slot_stack.getTag().contains(LostTimeSphereItem.TIME_VALUE_TAG)) {
                    int time_value = lost_sphere_slot_stack.getOrCreateTag().getInt(LostTimeSphereItem.TIME_VALUE_TAG);
                    int toAddTime = time_value + pToIncrementValue;

                    if (toAddTime > maxTime) {
                        pToShrinkValue = Math.floorDiv(maxTime - time_value, increment) * 10;
                        toAddTime = maxTime;

                    }
                    lost_sphere_slot_stack.getOrCreateTag().putInt(LostTimeSphereItem.TIME_VALUE_TAG, toAddTime);

                } else {
                    lost_sphere_slot_stack.getOrCreateTag().putInt(LostTimeSphereItem.TIME_VALUE_TAG, pToIncrementValue);
                }
                lapisloopium_slot_stack.shrink(pToShrinkValue);
                ((Slot) _slots.get(0)).set(lost_sphere_slot_stack);
                ((Slot) _slots.get(1)).set(lapisloopium_slot_stack);
                player.playSound(SoundEvents.ANVIL_USE, 1.0f, 1.0f);
                player.getCapability(PlayerTimeProvider.PLAYER_TIME_CAPABILITY).ifPresent(playerTime -> {
                    playerTime.decrementTime(increment * lapisloopiumCost);
                });

            }
            player.containerMenu.broadcastChanges();


        }
    }
}
