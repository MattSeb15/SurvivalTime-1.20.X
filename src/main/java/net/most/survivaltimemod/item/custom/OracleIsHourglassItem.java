package net.most.survivaltimemod.item.custom;

import io.netty.buffer.Unpooled;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.network.NetworkHooks;
import net.most.survivaltimemod.data.FormatTimeType;
import net.most.survivaltimemod.item.inventory.OracleIsHourglassInventoryCapability;
import net.most.survivaltimemod.time.PlayerTimeProvider;
import net.most.survivaltimemod.world.inventory.OracleIsHourglassMenu;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class OracleIsHourglassItem extends Item {
    public OracleIsHourglassItem(Properties pProperties) {
        super(pProperties.stacksTo(1));

    }


    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, @NotNull Player pPlayer, @NotNull InteractionHand pUsedHand) {

        InteractionResultHolder<ItemStack> ar = super.use(pLevel, pPlayer, pUsedHand);
        ItemStack itemstack = ar.getObject();
        double x = pPlayer.getX();
        double y = pPlayer.getY();
        double z = pPlayer.getZ();

        HitResult hitResult = pPlayer.pick(10.0D, 0.0F, false);
        if (hitResult.getType() == HitResult.Type.ENTITY) {
            return InteractionResultHolder.success(itemstack);
        }

        if (pPlayer instanceof ServerPlayer serverPlayer && serverPlayer.isCrouching()) {
            NetworkHooks.openScreen(serverPlayer, new MenuProvider() {
                @Override
                public @NotNull Component getDisplayName() {
                    return Component.translatable("container.oracle_is_hourglass");
                }

                @Override
                public AbstractContainerMenu createMenu(int id, @NotNull Inventory inventory, @NotNull Player player) {
                    FriendlyByteBuf packetBuffer = new FriendlyByteBuf(Unpooled.buffer());
                    packetBuffer.writeBlockPos(pPlayer.blockPosition());
                    packetBuffer.writeByte(pUsedHand == InteractionHand.MAIN_HAND ? 0 : 1);
                    return new OracleIsHourglassMenu(id, inventory, packetBuffer);
                }
            }, buf -> {
                buf.writeBlockPos(pPlayer.blockPosition());
                buf.writeByte(pUsedHand == InteractionHand.MAIN_HAND ? 0 : 1);
            });
            return InteractionResultHolder.success(itemstack);
        }


        if (pPlayer instanceof ServerPlayer serverPlayer && !serverPlayer.isCrouching()) {
            serverPlayer.getCapability(PlayerTimeProvider.PLAYER_TIME_CAPABILITY).ifPresent(playerTime -> {
                serverPlayer.displayClientMessage(Component.translatable("action.item.oracle_is_hourglass.right_click",
                        playerTime.getFormattedTime(FormatTimeType.DEPENDS_NAMED)).withStyle(ChatFormatting.AQUA), true);
            });
            itemstack.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
                if (capability.getStackInSlot(0).getCount() > 0) {
                    capability.extractItem(0, 1, false);
                } else {
                    serverPlayer.displayClientMessage(Component.translatable("action.item.oracle_is_hourglass.no_iflux_item").withStyle(ChatFormatting.RED), true);
                }
            });
            return InteractionResultHolder.success(itemstack);

        }

        return ar;

    }

    @Override
    public @NotNull InteractionResult interactLivingEntity(@NotNull ItemStack pStack, @NotNull Player pPlayer,
                                                           @NotNull LivingEntity pInteractionTarget, @NotNull InteractionHand pUsedHand) {


        if (pInteractionTarget instanceof ServerPlayer targetPlayer) {
            targetPlayer.getCapability(PlayerTimeProvider.PLAYER_TIME_CAPABILITY).ifPresent(playerTime -> {
                pPlayer.displayClientMessage(Component.translatable("action.item.oracle_is_hourglass.right_click_target",
                        targetPlayer.getGameProfile().getName(),
                        playerTime.getFormattedTime(FormatTimeType.DEPENDS_NAMED)).withStyle(ChatFormatting.GOLD), true);
            });

            ItemStack stack = pPlayer.getMainHandItem();

            stack.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
                int cost = 5;
                if (capability.getStackInSlot(0).getCount() >= cost) {
                    capability.extractItem(0, 5, false);
                } else {
                    pPlayer.displayClientMessage(Component.translatable("action.item.oracle_is_hourglass.no_iflux_item_target").withStyle(ChatFormatting.RED), true);
                }
            });
            return InteractionResult.SUCCESS;
//            pPlayer.displayClientMessage(Component.literal("TargetPlayer: " + targetPlayer.getName()), false);
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel,
                                @NotNull List<Component> pTooltipComponents,
                                @NotNull TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("itranslatable.oracle_is_hourglass.tooltip_right_click_target").withStyle(ChatFormatting.GREEN));
        pTooltipComponents.add(Component.translatable("itranslatable.oracle_is_hourglass.tooltip_right_click").withStyle(ChatFormatting.AQUA));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag compound) {
        return new OracleIsHourglassInventoryCapability();
    }

    @Override
    public CompoundTag getShareTag(ItemStack stack) {
        CompoundTag nbt = stack.getOrCreateTag();
        stack.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> nbt.put("Inventory",
                ((ItemStackHandler) capability).serializeNBT()));
        return nbt;
    }

    @Override
    public void readShareTag(ItemStack stack, @Nullable CompoundTag nbt) {
        super.readShareTag(stack, nbt);
        if (nbt != null)
            stack.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> ((ItemStackHandler) capability).deserializeNBT((CompoundTag) nbt.get("Inventory")));
    }


}
