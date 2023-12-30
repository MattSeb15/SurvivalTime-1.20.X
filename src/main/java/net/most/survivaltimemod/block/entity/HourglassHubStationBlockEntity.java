package net.most.survivaltimemod.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.TransientCraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.most.survivaltimemod.item.ModItems;
import net.most.survivaltimemod.item.custom.LostTimeSphereItem;
import net.most.survivaltimemod.recipe.HourglassHubStationShapedRecipe;
import net.most.survivaltimemod.recipe.HourglassHubStationShapelessRecipe;
import net.most.survivaltimemod.world.inventory.HourglassHubStationMenu;
import net.most.survivaltimemod.util.ModEnergyStorage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HourglassHubStationBlockEntity extends BlockEntity implements MenuProvider {

    private final ItemStackHandler itemHandler = new ItemStackHandler(27) {
        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            setChanged();

            if (level != null && !level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return switch (slot) {
                case OUTPUT_SLOT -> false;
                case ENERGY_TIME_SLOT -> stack.getItem() == ModItems.LOST_TIME_SPHERE.get();
                default -> super.isItemValid(slot, stack);
            };
        }
    };


    //output slot = 25
    public static final int OUTPUT_SLOT = 25;
    //energy input slot = 26
    public static final int ENERGY_TIME_SLOT = 26;
    //grid input slots = 0-25
    public static final int GRID_INPUT_SLOT_START = 0;
    public static final int GRID_INPUT_SLOT_END = 24;

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    private LazyOptional<IEnergyStorage> lazyEnergyHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 100;

    private final int DEFAULT_MAX_PROGRESS = 100;
    private int energyCost = 0;
    private final int DEFAULT_ENERGY_COST = 1000;


    private AbstractContainerMenu menu;
    private final ModEnergyStorage ENERGY_TIME_STORAGE = createEnergyStorage();

    private ModEnergyStorage createEnergyStorage() {
        return new ModEnergyStorage(36000, 300) {
            @Override
            public void onEnergyChanged() {
                setChanged();
                if (level != null && !level.isClientSide()) {
                    level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
                }

            }
        };
    }


    public List<ItemStack> getItemStacks() {
        List<ItemStack> itemStacks = new ArrayList<>();
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            itemStacks.add(itemHandler.getStackInSlot(i));
        }
        return itemStacks;
    }

    public ItemStack getItemResultStack() {
        return itemHandler.getStackInSlot(OUTPUT_SLOT);
    }


    public int getProgress() {
        return this.progress;
    }

    public IEnergyStorage getEnergyStorage() {
        return ENERGY_TIME_STORAGE;
    }

    public HourglassHubStationBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.HOURGLASS_HUB_STATION.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> HourglassHubStationBlockEntity.this.progress;
                    case 1 -> HourglassHubStationBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> HourglassHubStationBlockEntity.this.progress = value;
                    case 1 -> HourglassHubStationBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.literal("Hourglass Hub Station");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, @NotNull Inventory pPlayerInventory,
                                            @NotNull Player pPlayer) {
        menu = new HourglassHubStationMenu(pContainerId, pPlayerInventory, this, this.data);

        return new HourglassHubStationMenu(pContainerId, pPlayerInventory, this, this.data);

    }


    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ENERGY) {
            return lazyEnergyHandler.cast();
        }
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
        lazyEnergyHandler = LazyOptional.of(() -> ENERGY_TIME_STORAGE);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
        lazyEnergyHandler.invalidate();
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        if (this.level != null) {
            Containers.dropContents(this.level, this.worldPosition, inventory);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inv", itemHandler.serializeNBT());
        pTag.putInt("hourglass_hub_progress", progress);
        pTag.putInt("hourglass_hub_max_progress", maxProgress);
        pTag.putInt("hourglass_hub_energy_cost", energyCost);
        pTag.putInt("hourglass_hub_energy", ENERGY_TIME_STORAGE.getEnergyStored());
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inv"));
        progress = pTag.getInt("hourglass_hub_progress");
        maxProgress = pTag.getInt("hourglass_hub_max_progress");
        energyCost = pTag.getInt("hourglass_hub_energy_cost");
        ENERGY_TIME_STORAGE.setEnergy(pTag.getInt("hourglass_hub_energy"));
    }

    public void tick(Level level, BlockPos pPos, BlockState pState, HourglassHubStationBlockEntity blockEntity) {
        fillUpOnEnergyThenConsumeItem();


        if (isOutputSlotEmptyOrReceivable() && hasRecipe() && hasEnoughEnergyToCraft()) {
            increaseCraftingProgress();

            setChanged(level, pPos, pState);

            if (hasProgressFinished()) {
                craftItem();
                resetProgress();
                extractEnergy();

            }
        } else {
            resetProgress();


        }


    }


    private void extractEnergy() {
//        this.ENERGY_TIME_STORAGE.extractEnergy(1000, false);
        this.ENERGY_TIME_STORAGE.setEnergy(this.ENERGY_TIME_STORAGE.getEnergyStored() - energyCost);
    }

    private void fillUpOnEnergyThenConsumeItem() {
        if (hasEnergyItemInSlot()) {
            if (ENERGY_TIME_STORAGE.getEnergyStored() >= ENERGY_TIME_STORAGE.getMaxEnergyStored()) return;
            ItemStack energyItem = this.itemHandler.getStackInSlot(ENERGY_TIME_SLOT);
            if (energyItem.hasTag()) {
                CompoundTag tag = energyItem.getTag();
                if (tag != null) {
                    if (tag.contains(LostTimeSphereItem.TIME_VALUE_TAG)) {
                        int timeEnergyValue = tag.getInt(LostTimeSphereItem.TIME_VALUE_TAG);
                        if (timeEnergyValue > 0) {
                            int toReceiveEnergy = Math.min(60, timeEnergyValue);
                            this.ENERGY_TIME_STORAGE.receiveEnergy(toReceiveEnergy, false);
                            tag.putInt(LostTimeSphereItem.TIME_VALUE_TAG, timeEnergyValue - toReceiveEnergy);

                        } else {
                            energyItem.setTag(null);
                        }
                    }
                }
            }
        }


//        if(ENERGY_TIME_STORAGE.getEnergyStored() < ENERGY_TIME_STORAGE.getMaxEnergyStored()) {
//            if(!itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty()) {
//                ENERGY_TIME_STORAGE.addEnergy(100);
//                itemHandler.extractItem(OUTPUT_SLOT, 1, false);
//            }
//        }
    }

    private boolean hasEnergyItemInSlot() {
        return !this.itemHandler.getStackInSlot(ENERGY_TIME_SLOT).isEmpty()
                && this.itemHandler.getStackInSlot(ENERGY_TIME_SLOT).getItem() == ModItems.LOST_TIME_SPHERE.get()
                && this.itemHandler.getStackInSlot(ENERGY_TIME_SLOT).hasTag();

    }

    private void craftItem() {
        Optional<HourglassHubStationShapelessRecipe> shapelessRecipe = getCurrentShapelessRecipe();
        Optional<HourglassHubStationShapedRecipe> shapedRecipe = getCurrentShapedRecipe();

        if (shapelessRecipe.isEmpty() && shapedRecipe.isEmpty()) return;
        if (shapedRecipe.isPresent()) {
            craftShapedItem(shapedRecipe);
        }
        if (shapelessRecipe.isPresent()) {
            craftShapelessItem(shapelessRecipe);
        }


    }

    private void craftShapedItem(Optional<HourglassHubStationShapedRecipe> shapedRecipe) {

        for (int i = GRID_INPUT_SLOT_START; i <= GRID_INPUT_SLOT_END; i++) {
            itemHandler.extractItem(i, 1, false);
        }

        ItemStack resultItem = shapedRecipe.get().getResultItem(getLevel().registryAccess());

        this.itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(resultItem.getItem(),
                this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + resultItem.getCount()));
    }

    private void craftShapelessItem(Optional<HourglassHubStationShapelessRecipe> shapelessRecipe) {
        for (int i = GRID_INPUT_SLOT_START; i <= GRID_INPUT_SLOT_END; i++) {
            itemHandler.extractItem(i, 1, false);
        }

        ItemStack resultItem = shapelessRecipe.get().getResultItem(getLevel().registryAccess());

        this.itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(resultItem.getItem(),
                this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + resultItem.getCount()));

    }

    private Optional<HourglassHubStationShapedRecipe> getCurrentShapedRecipe() {
        int slots = this.itemHandler.getSlots();
        if (menu == null) {
            return Optional.empty();
        }
//        SimpleContainer inventory = new SimpleContainer(slots);
        TransientCraftingContainer craftingContainer = new TransientCraftingContainer(this.menu, 5, 5);
        for (int i = 0; i < craftingContainer.getContainerSize(); i++) {
            craftingContainer.setItem(i, this.itemHandler.getStackInSlot(i));
        }

        assert this.level != null;
        return this.level.getRecipeManager().getRecipeFor(HourglassHubStationShapedRecipe.Type.INSTANCE, craftingContainer, this.level);

    }

    private Optional<HourglassHubStationShapelessRecipe> getCurrentShapelessRecipe() {
        int slots = this.itemHandler.getSlots();
        SimpleContainer inventory = new SimpleContainer(slots);
        for (int i = 0; i < slots; i++) {
            inventory.setItem(i, this.itemHandler.getStackInSlot(i));
        }
        assert this.level != null;
        return this.level.getRecipeManager().getRecipeFor(HourglassHubStationShapelessRecipe.Type.INSTANCE, inventory, this.level);
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private boolean hasProgressFinished() {
        return this.progress >= this.maxProgress;
    }

    private void increaseCraftingProgress() {
        this.progress++;
    }

    private boolean hasRecipe() {
        Optional<HourglassHubStationShapelessRecipe> recipe = getCurrentShapelessRecipe();
        Optional<HourglassHubStationShapedRecipe> recipe2 = getCurrentShapedRecipe();

        if (recipe.isPresent()) {
            maxProgress = recipe.get().getCraftTime();
            energyCost = recipe.get().getEnergyCost();
            ItemStack resultItem = recipe.get().getResultItem(getLevel().registryAccess());
            return canInsertAmountIntoOutputSlot(resultItem.getCount())
                    && canInsertItemIntoOutputSlot(resultItem.getItem());

        }

        if (recipe2.isPresent()) {
            maxProgress = recipe2.get().getCraftTime();
            energyCost = recipe2.get().getEnergyCost();
            ItemStack resultItem = recipe2.get().getResultItem(getLevel().registryAccess());
            return canInsertAmountIntoOutputSlot(resultItem.getCount())
                    && canInsertItemIntoOutputSlot(resultItem.getItem());
        }

        return false;


    }

    private boolean hasEnoughEnergyToCraft() {
        return ENERGY_TIME_STORAGE.getEnergyStored() >= energyCost; //* maxProgress
    }


    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() || this.itemHandler.getStackInSlot(OUTPUT_SLOT).is(item);
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + count <= this.itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    }

    private boolean isOutputSlotEmptyOrReceivable() {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() ||
                this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() < this.itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public @NotNull CompoundTag getUpdateTag() {
        return saveWithFullMetadata();
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        super.onDataPacket(net, pkt);
    }
}
