package net.most.survivaltimemod.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.most.survivaltimemod.SurvivalTimeMod;
import net.most.survivaltimemod.block.ModBlocks;
import net.most.survivaltimemod.data.FormatTimeType;
import net.most.survivaltimemod.time.PlayerTimeProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LostTimeSphereData extends Item {
    public LostTimeSphereData(Properties pProperties) {
        super(pProperties.stacksTo(1));
    }

//    @Override
//    public boolean canAttackBlock(@NotNull BlockState pState, @NotNull Level pLevel, @NotNull BlockPos pPos, @NotNull Player pPlayer) {
//        return false;
//    }

    public static final String TIME_VALUE_TAG = SurvivalTimeMod.MOD_ID + ".time_value";

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, @NotNull Player pPlayer, @NotNull InteractionHand pUsedHand) {


        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);
        if (itemStack.hasTag()) {
            CompoundTag tag = itemStack.getTag();
            if (tag == null) return super.use(pLevel, pPlayer, pUsedHand);
            int timeValue = itemStack.getTag().getInt(TIME_VALUE_TAG);
            if (timeValue == 0) return super.use(pLevel, pPlayer, pUsedHand);

            if (!pLevel.isClientSide()) {

                pPlayer.getCooldowns().addCooldown(this, 100);
                pPlayer.getCapability(PlayerTimeProvider.PLAYER_TIME_CAPABILITY).ifPresent(playerTime -> {
                    ServerPlayer player = (ServerPlayer) pPlayer;
                    playerTime.incrementTime(timeValue, player);
                    itemStack.setTag(null);
                });

                String timeString = FormatTimeType.getFormattedStringByType(FormatTimeType.DEPENDS_NAMED, timeValue);
                pPlayer.sendSystemMessage(Component.translatable("itranslatable.lost_time_sphere.on_success_consume_time", timeString).withStyle(ChatFormatting.GOLD));

            }

            return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand));

        }


        return InteractionResultHolder.fail(pPlayer.getItemInHand(pUsedHand));
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext pContext) {
        return InteractionResult.FAIL;
    }

    @Override
    public boolean isFoil(ItemStack pStack) {
        return pStack.hasTag();
    }

//    @Override
//    public @NotNull ItemStack getDefaultInstance() {
//        ItemStack itemStack = new ItemStack(this);
//        CompoundTag tag = new CompoundTag();
//        tag.putInt(TIME_VALUE_TAG, 0); // Aquí puedes poner el valor por defecto que desees
//        itemStack.setTag(tag);
//        return itemStack;
//    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
        if (pStack.hasTag()) {
            CompoundTag tag = pStack.getTag();
            if (tag == null) return;
            int timeValue = pStack.getTag().getInt(TIME_VALUE_TAG);
            String timeString = FormatTimeType.getFormattedStringByType(FormatTimeType.DEPENDS_NAMED, timeValue);
            Component tooltipComponent =
                    Component.empty().append(Component.literal("TE: ")).append(Component.literal(timeString).withStyle(ChatFormatting.GOLD));
            pTooltipComponents.add(tooltipComponent);
            pTooltipComponents.add(Component.empty());
            pTooltipComponents.add(Component.translatable("itranslatable.lost_time_sphere.consume_time").withStyle(ChatFormatting.BLUE));
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
