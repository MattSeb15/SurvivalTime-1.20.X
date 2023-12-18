package net.most.survivaltimemod.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.most.survivaltimemod.SurvivalTimeMod;
import net.most.survivaltimemod.data.FormatTimeType;
import net.most.survivaltimemod.item.ModItems;
import net.most.survivaltimemod.time.PlayerTimeProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LostTimeSphereItem extends Item {
    public LostTimeSphereItem(Properties pProperties) {
        super(pProperties.stacksTo(1).setNoRepair());
    }

//    @Override
//    public boolean canAttackBlock(@NotNull BlockState pState, @NotNull Level pLevel, @NotNull BlockPos pPos, @NotNull Player pPlayer) {
//        return false;
//    }


    public static final String TIME_VALUE_TAG = SurvivalTimeMod.MOD_ID + ".time_value";

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, @NotNull Player pPlayer, @NotNull InteractionHand pUsedHand) {


        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);
        if (pLevel.isClientSide()) return InteractionResultHolder.fail(pPlayer.getItemInHand(pUsedHand));
        CompoundTag tag = itemStack.getTag();
        int lapisloopiumCost = 10;


        if (pPlayer.isCrouching()) {
            int lapisloopiumIndex = getPlayerLapisloopiumIndex(pPlayer);
            if (lapisloopiumIndex == -1) {
                pPlayer.sendSystemMessage(Component.translatable("itranslatable.lost_time_sphere.on_fail_consume_time_not_lapisloopium").withStyle(ChatFormatting.RED));
                return InteractionResultHolder.fail(pPlayer.getItemInHand(pUsedHand));
            }
            if (!itemStack.hasTag()) {
                ItemStack lapisloopiumStack = pPlayer.getInventory().getItem(lapisloopiumIndex);
                if (lapisloopiumStack.getCount() < lapisloopiumCost) {
                    pPlayer.sendSystemMessage(Component.translatable("itranslatable.lost_time_sphere.on_fail_consume_time_not_enough_lapisloopium").withStyle(ChatFormatting.RED));
                    return InteractionResultHolder.fail(pPlayer.getItemInHand(pUsedHand));
                }
                if (lapisloopiumStack.getCount() == lapisloopiumCost) {
                    pPlayer.getInventory().setItem(lapisloopiumIndex, ItemStack.EMPTY);
                } else {
                    lapisloopiumStack.setCount(lapisloopiumStack.getCount() - lapisloopiumCost);
                }
                itemStack.getOrCreateTag().putInt(TIME_VALUE_TAG, 3600);
                consumePlayerTime((ServerPlayer) pPlayer, itemStack);


            } else {
                ItemStack lapisloopiumStack = pPlayer.getInventory().getItem(lapisloopiumIndex);
                if (lapisloopiumStack.getCount() < lapisloopiumCost) {
                    pPlayer.sendSystemMessage(Component.translatable("itranslatable.lost_time_sphere.on_fail_consume_time_not_enough_lapisloopium").withStyle(ChatFormatting.RED));
                    return InteractionResultHolder.fail(pPlayer.getItemInHand(pUsedHand));
                }
                if (lapisloopiumStack.getCount() == lapisloopiumCost) {
                    pPlayer.getInventory().setItem(lapisloopiumIndex, ItemStack.EMPTY);
                } else {
                    lapisloopiumStack.setCount(lapisloopiumStack.getCount() - lapisloopiumCost);
                }
                if (tag != null && tag.contains(TIME_VALUE_TAG)) {
                    int currentTimeValue = itemStack.getOrCreateTag().getInt(TIME_VALUE_TAG);
                    itemStack.getOrCreateTag().putInt(TIME_VALUE_TAG, currentTimeValue + 3600);
                    consumePlayerTime((ServerPlayer) pPlayer, itemStack);
                }
            }
            return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand));

        } else {
            if (itemStack.hasTag()) {
                if (tag == null) return InteractionResultHolder.fail(pPlayer.getItemInHand(pUsedHand));
                if (!tag.contains(TIME_VALUE_TAG)) return InteractionResultHolder.fail(pPlayer.getItemInHand(pUsedHand));
                int timeValue = tag.getInt(TIME_VALUE_TAG);
                if (timeValue == 0) return super.use(pLevel, pPlayer, pUsedHand);

                pPlayer.getCooldowns().addCooldown(this, 100);
                pPlayer.getCapability(PlayerTimeProvider.PLAYER_TIME_CAPABILITY).ifPresent(playerTime -> {
                    ServerPlayer player = (ServerPlayer) pPlayer;
                    playerTime.incrementTime(timeValue, player);
                    itemStack.setTag(null);
                });


                String timeString = FormatTimeType.getFormattedStringByType(FormatTimeType.DEPENDS_NAMED, timeValue);
                pPlayer.sendSystemMessage(Component.translatable("itranslatable.lost_time_sphere.on_success_consume_time", timeString).withStyle(ChatFormatting.GOLD));


                return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand));

            }
        }


        return InteractionResultHolder.fail(pPlayer.getItemInHand(pUsedHand));
    }

    private void consumePlayerTime(ServerPlayer pPlayer, ItemStack itemStack) {

        pPlayer.getCapability(PlayerTimeProvider.PLAYER_TIME_CAPABILITY).ifPresent(playerTime -> {
            float currentTime = playerTime.getTime();
            if (currentTime < 3600) {
                pPlayer.sendSystemMessage(Component.translatable("itranslatable.lost_time_sphere.on_fail_consume_time_not_enough_time").withStyle(ChatFormatting.RED));
                return;
            }
            ;
            playerTime.decrementTime(3600, pPlayer);
            pPlayer.getCooldowns().addCooldown(this, 40);
            pPlayer.sendSystemMessage(Component.translatable("itranslatable.lost_time_sphere.on_success_consume_lapisloopium", "TE: " +
                    FormatTimeType.getFormattedStringByType(FormatTimeType.DEPENDS_NAMED, itemStack.getOrCreateTag().getInt(TIME_VALUE_TAG))).withStyle(ChatFormatting.GREEN));

        });
    }

    private int getPlayerLapisloopiumIndex(Player pPlayer) {
        Inventory inventory = pPlayer.getInventory();
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack itemStack = inventory.getItem(i);
            if (itemStack.is(ModItems.LAPISLOOPIUM.get()) && itemStack.getCount() >= 10) {
                return i;
            }
        }
        return -1;
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
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents,
                                @NotNull TooltipFlag pIsAdvanced) {
        if (pStack.hasTag()) {
            CompoundTag tag = pStack.getTag();
            if (tag == null) return;
            int timeValue = pStack.getTag().getInt(TIME_VALUE_TAG);
            String timeString = FormatTimeType.getFormattedStringByType(FormatTimeType.DEPENDS_NAMED, timeValue);
            Component tooltipComponent =
                    Component.empty().append(Component.literal("TE: ")).append(Component.literal(timeString).withStyle(ChatFormatting.GOLD));
            pTooltipComponents.add(tooltipComponent);
            pTooltipComponents.add(Component.empty());
            pTooltipComponents.add(Component.translatable("itranslatable.lost_time_sphere.tooltip_right_click").withStyle(ChatFormatting.DARK_BLUE));
            pTooltipComponents.add(Component.translatable("itranslatable.lost_time_sphere.tooltip_shift_right_click").withStyle(ChatFormatting.BLUE));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
            return;
        }
        pTooltipComponents.add(Component.empty());
        pTooltipComponents.add(Component.translatable("itranslatable.lost_time_sphere.tooltip_shift_right_click").withStyle(ChatFormatting.BLUE));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
