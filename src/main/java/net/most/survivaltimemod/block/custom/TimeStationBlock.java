package net.most.survivaltimemod.block.custom;

import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import net.most.survivaltimemod.block.entity.HourglassHubStationBlockEntity;
import net.most.survivaltimemod.block.entity.ModBlockEntities;
import net.most.survivaltimemod.world.inventory.TimeStationMenu;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TimeStationBlock extends Block {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public TimeStationBlock(Properties pProperties) {
        super(pProperties);
    }

    public static final VoxelShape SHAPE = Block.box(1, 0, 1, 15, 16, 15);


    public BlockState rotate(BlockState pState, Rotation pRot) {
        return pState.setValue(FACING, pRot.rotate(pState.getValue(FACING)));
    }

    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos,
                                        CollisionContext pContext) {
        return SHAPE;
    }


    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
    }
    /*BlockEntity*/

    @Override
    public @NotNull RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {
        return 5;
    }




    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer,
                                 InteractionHand pHand, BlockHitResult pHit) {

        //if player has a block in his hand fail place block


        if (!pLevel.isClientSide) {
            if (pPlayer instanceof ServerPlayer _ent) {

                BlockPos _bpos = BlockPos.containing(pPos.getX(), pPos.getY(), pPos.getZ());
                NetworkHooks.openScreen((ServerPlayer) _ent, new MenuProvider() {
                    @Override
                    public @NotNull Component getDisplayName() {
                        return Component.translatable("block.survivaltimemod.time_station");
                    }

                    @Override
                    public AbstractContainerMenu createMenu(int id, @NotNull Inventory inventory, @NotNull Player player) {
                        return new TimeStationMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
                    }
                }, _bpos);
            }
        }
        if (pPlayer.getItemInHand(pHand).getItem() instanceof BlockItem) {
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

}
