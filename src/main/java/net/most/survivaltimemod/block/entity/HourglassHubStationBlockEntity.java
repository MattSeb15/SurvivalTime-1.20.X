package net.most.survivaltimemod.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.inventory.TransientCraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.most.survivaltimemod.recipe.HourglassHubStationShapedRecipe;
import net.most.survivaltimemod.recipe.HourglassHubStationShapelessRecipe;
import net.most.survivaltimemod.screen.HourglassHubStationMenu;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class HourglassHubStationBlockEntity extends BlockEntity implements MenuProvider {

    private final ItemStackHandler itemHandler = new ItemStackHandler(26) {
        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            setChanged();
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return slot != OUTPUT_SLOT;
        }
    };


    //output slot = 26
    private static final int OUTPUT_SLOT = 25;
    //grid input slots = 0-25
    private static final int GRID_INPUT_SLOT_START = 0;
    private static final int GRID_INPUT_SLOT_END = 24;
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 100;
    private AbstractContainerMenu menu;

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
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inv", itemHandler.serializeNBT());
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inv"));
    }

    public void tick(Level level, BlockPos pPos, BlockState pState) {

        if (isOutputSlotEmptyOrReceivable() && hasRecipe()) {
            increaseCraftingProgress();
            setChanged(level, pPos, pState);

            if (hasProgressFinished()) {
                craftItem();
                resetProgress();
            }
        } else {
            resetProgress();
        }


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
        if(menu== null){
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

        if(recipe.isPresent() && recipe2.isPresent()) {
            return true;
        }

        if (recipe.isPresent()) {
            ItemStack resultItem = recipe.get().getResultItem(getLevel().registryAccess());
            return canInsertAmountIntoOutputSlot(resultItem.getCount())
                    && canInsertItemIntoOutputSlot(resultItem.getItem());

        }

        if (recipe2.isPresent()) {
            ItemStack resultItem = recipe2.get().getResultItem(getLevel().registryAccess());
            return canInsertAmountIntoOutputSlot(resultItem.getCount())
                    && canInsertItemIntoOutputSlot(resultItem.getItem());
        }

        return false;



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


}
