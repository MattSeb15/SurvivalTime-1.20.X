package net.most.survivaltimemod.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class LostTimeSphereItem extends Item {
    public LostTimeSphereItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, @NotNull Player pPlayer,
                                                           @NotNull InteractionHand pUsedHand) {

        if (!pLevel.isClientSide()) {
            pPlayer.getCooldowns().addCooldown(this, 100);
            pPlayer.sendSystemMessage(Component.literal("You have used the Lost Time Sphere!"));
        }

        return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand));
    }
}
