package net.most.survivaltimemod.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.most.survivaltimemod.sound.ModSounds;
import net.most.survivaltimemod.time.PlayerTimeProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

public class OpalPickaxe extends PickaxeItem implements OpalTool {

    private final float timeStealMultiplier;
    private final float probability;
    private final Random random = new Random();

    public OpalPickaxe(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, float timeStealMultiplier,
                       float probability, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
        this.timeStealMultiplier = timeStealMultiplier;
        this.probability = probability;
    }

    @Override
    public float getTimeStealMultiplier() {
        return this.timeStealMultiplier;
    }

    @Override
    public float getProbability() {
        return this.probability;
    }

    @Override
    public boolean mineBlock(@NotNull ItemStack pStack, @NotNull Level pLevel, @NotNull BlockState pState, @NotNull BlockPos pPos,
                             @NotNull LivingEntity pEntityLiving) {
        float destroySpeed = pState.getDestroySpeed(pLevel, pPos);
        if (destroySpeed <= 0.0f) return false;
        if (!pLevel.isClientSide()) {
            if(!pState.is(BlockTags.MINEABLE_WITH_PICKAXE)) return super.mineBlock(pStack, pLevel, pState, pPos, pEntityLiving);
            if (random.nextFloat() > getProbability()) return super.mineBlock(pStack, pLevel, pState, pPos, pEntityLiving);


            float increase = 5 * getTimeStealMultiplier() * destroySpeed;
            if (pState.is(Blocks.EMERALD_ORE)) increase *= 1.55F;
            if (pState.is(Blocks.DIAMOND_ORE)) increase *= 1.5F;
            if (pState.is(Blocks.LAPIS_ORE) || pState.is(Blocks.REDSTONE_ORE) || pState.is(Blocks.NETHER_QUARTZ_ORE)) increase *= 1.15F;
            if (pState.is(Blocks.COAL_ORE)) increase *= 1.1F;
            if (pState.is(Blocks.NETHER_GOLD_ORE)) increase *= 1.05F;
            if (pState.is(Blocks.ANCIENT_DEBRIS)) increase *= 1.95F;
            if (pState.is(Blocks.GOLD_ORE)) increase *= 1.33F;
            if (pState.is(Blocks.IRON_ORE)) increase *= 1.12F;
            if (pState.is(Blocks.COPPER_ORE)) increase *= 1.05F;
            if (pState.is(Blocks.GLOWSTONE)) increase *= 1.02F;

            if (pEntityLiving instanceof ServerPlayer serverPlayer) {
                serverPlayer.displayClientMessage(pState.getBlock().getName(), false);
                serverPlayer.displayClientMessage(Component.literal("DESTROY SPEED | INCREASE " + destroySpeed + " " + increase), false);
                float finalIncrease = increase;
                serverPlayer.getCapability(PlayerTimeProvider.PLAYER_TIME_CAPABILITY).ifPresent(playerTime -> {
                    playerTime.incrementTime(finalIncrease, serverPlayer);
                });
                serverPlayer.level().playSound(null,
                        pPos,
                        ModSounds.TIME_STEAL_IMPACT.get(),
                        pEntityLiving.getSoundSource(),
                        0.8F,
                        this.random.nextFloat() * 0.4F + 0.8F
                );
            }

        }

        return super.mineBlock(pStack, pLevel, pState, pPos, pEntityLiving);

    }

    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents,
                                @NotNull TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("item.survival_time_mod.opal_pickaxe.tooltip.info").withStyle(ChatFormatting.LIGHT_PURPLE));
        pTooltipComponents.add(Component.empty());
        pTooltipComponents.add(Component.translatable("item.survival_time_mod.opal_tool.tooltip.time_steal_multiplier",
                getTimeStealMultiplier()).withStyle(ChatFormatting.GOLD));
        pTooltipComponents.add(Component.translatable("item.survival_time_mod.opal_tool.tooltip.probability", String.format("%.2f%%",
                getProbability() * 100)).withStyle(ChatFormatting.AQUA));

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
