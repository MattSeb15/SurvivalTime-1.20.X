package net.most.survivaltimemod.data;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.most.survivaltimemod.time.PlayerTime;
import org.jetbrains.annotations.NotNull;

public class FinishUsingItemData {

    private final @NotNull ItemStack pStack;
    private final @NotNull Level pLevel;
    private final @NotNull ServerPlayer serverPlayer;
    private final PlayerTime playerTime;

    public FinishUsingItemData(@NotNull ItemStack pStack, @NotNull Level pLevel, @NotNull ServerPlayer serverPlayer,
                               PlayerTime playerTime) {
        this.pStack = pStack;
        this.pLevel = pLevel;
        this.serverPlayer = serverPlayer;
        this.playerTime = playerTime;
    }

    public @NotNull ItemStack getStack() {
        return this.pStack;
    }

    public @NotNull Level getLevel() {
        return this.pLevel;
    }

    public @NotNull ServerPlayer getServerPlayer() {
        return this.serverPlayer;
    }

    public PlayerTime getPlayerTime() {
        return this.playerTime;
    }
}
