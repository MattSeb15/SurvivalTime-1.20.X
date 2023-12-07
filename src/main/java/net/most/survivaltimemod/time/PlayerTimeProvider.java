package net.most.survivaltimemod.time;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerTimeProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<PlayerTime> PLAYER_TIME_CAPABILITY =
            CapabilityManager.get(new CapabilityToken<PlayerTime>() {
            });

    private PlayerTime playerTime = null;
    private final LazyOptional<PlayerTime> optional = LazyOptional.of(this::createPlayerTime);

    private PlayerTime createPlayerTime() {
        if (this.playerTime == null) {
            this.playerTime = new PlayerTime();
        }
        return this.playerTime;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return cap == PLAYER_TIME_CAPABILITY ? optional.cast() : LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerTime().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerTime().loadNBTData(nbt);

    }
}
