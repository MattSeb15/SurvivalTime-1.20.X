package net.most.survivaltimemod.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.most.survivaltimemod.entity.custom.throwable.CurseEntity;
import net.most.survivaltimemod.entity.custom.throwable.ProsperityEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CurseItem extends Item {

    public CurseItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, @NotNull Player pPlayer, @NotNull InteractionHand pUsedHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pUsedHand);
        pLevel.playSound((Player) null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, 0.5F,
                0.4F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!pLevel.isClientSide) {
            float velocity = 1.0F;
            if (pPlayer.isCrouching()) velocity = 0.35f;
            CurseEntity curseEntity = new CurseEntity(pLevel, pPlayer);
            curseEntity.setItem(itemstack);
            curseEntity.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, velocity, 0F);
            pLevel.addFreshEntity(curseEntity);
        }

        pPlayer.awardStat(Stats.ITEM_USED.get(this));
        if (!pPlayer.getAbilities().instabuild) {
            itemstack.shrink(1);
        }

        return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());
    }

    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents,
                                @NotNull TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.literal("Removes beneficial effects from the target entity.").withStyle(ChatFormatting.RED));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
