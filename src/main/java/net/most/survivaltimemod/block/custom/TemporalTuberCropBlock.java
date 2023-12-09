package net.most.survivaltimemod.block.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.most.survivaltimemod.item.ModItems;
import org.jetbrains.annotations.NotNull;

public class TemporalTuberCropBlock extends CropBlock {

    public static final int MAX_AGE = BlockStateProperties.MAX_AGE_7;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_7;


    public TemporalTuberCropBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected @NotNull ItemLike getBaseSeedId() {
        return ModItems.TEMPORAL_TUBER_SEEDS.get();
    }


    @Override
    protected int getBonemealAgeIncrease(@NotNull Level pLevel) {
        return 0;
    }

    @Override
    public @NotNull IntegerProperty getAgeProperty() {
        return AGE;
    }


    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }


    @Override
    public @NotNull InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer,
                                          InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide()) {
            if(pHand.equals(InteractionHand.MAIN_HAND)) {
                return InteractionResult.SUCCESS;
            }

            //SOLO PASAR SI EL ITEM ES UN RELOJ
            //TODO: reloj custom (aun no añadido) utilizar el reloj de mc en su lugar

            if(pPlayer.getMainHandItem().getItem() == Items.BONE_MEAL) {
                return InteractionResult.SUCCESS;
            }
            if(pPlayer.getMainHandItem().getItem() != Items.CLOCK) {
                return InteractionResult.PASS;
            }
            int currentAge = pState.getValue(AGE);
            if(currentAge == MAX_AGE) {
                pPlayer.displayClientMessage(Component.literal("HARVESTED").withStyle(ChatFormatting.AQUA), false);
                return InteractionResult.PASS;
            }
            pLevel.setBlockAndUpdate(pPos, pState.setValue(AGE, Math.min(currentAge+1, MAX_AGE)));
            pPlayer.displayClientMessage(Component.literal("INTERACTED"), false);
            pPlayer.getMainHandItem().shrink(1); // Consume el item
            return InteractionResult.SUCCESS;


        }
        return InteractionResult.PASS;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(AGE);
    }
}
