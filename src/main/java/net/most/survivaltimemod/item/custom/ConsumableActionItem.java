package net.most.survivaltimemod.item.custom;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.most.survivaltimemod.data.FinishUsingItemData;
import net.most.survivaltimemod.time.PlayerTimeProvider;
import org.jetbrains.annotations.NotNull;
import java.util.function.Consumer;

public class ConsumableActionItem extends Item {

    private final Consumer<FinishUsingItemData> action;

    public ConsumableActionItem(Properties pProperties, Consumer<FinishUsingItemData> action) {
        super(pProperties);
        this.action = action;
    }

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack pStack, @NotNull Level pLevel,
                                              @NotNull LivingEntity pLivingEntity) {

        if (!pLevel.isClientSide() && action != null) {
            if (pLivingEntity instanceof ServerPlayer player) {
                player.getCapability(PlayerTimeProvider.PLAYER_TIME_CAPABILITY).ifPresent(playerTime -> {
                    action.accept(new FinishUsingItemData(pStack, pLevel, player, playerTime));
                });

            }

        }
        return super.finishUsingItem(pStack, pLevel, pLivingEntity);
    }
}