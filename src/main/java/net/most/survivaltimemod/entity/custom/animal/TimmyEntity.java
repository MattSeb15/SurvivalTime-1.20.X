package net.most.survivaltimemod.entity.custom.animal;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.*;

import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Parrot;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.most.survivaltimemod.time.PlayerTimeProvider;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public class TimmyEntity extends Parrot {

    public TimmyEntity(EntityType<? extends Parrot> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    private boolean hasOwnerAroundXBlocks() {
        if (this.getOwner() == null) return false;
        List<LivingEntity> entitiesOfClass = this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(5),
                livingEntity -> livingEntity.is(this.getOwner()));
        return !entitiesOfClass.isEmpty();
    }

    public static AttributeSupplier.@NotNull Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 15D).add(Attributes.FLYING_SPEED, 0.9F).add(Attributes.MOVEMENT_SPEED, 0.45F);
    }

    @Override
    protected void registerGoals() {
        //        super.registerGoals();
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(2, new FollowOwnerGoal(this, 1.1D, 3.0F, 1.0F, true));
        this.goalSelector.addGoal(3, new FollowMobGoal(this, 1.0D, 3.0F, 7.0F));
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (tickCount % 20 == 0) {
            executeTimmyLogic();
        }
    }

    private void executeTimmyLogic() {
        if (hasOwnerAroundXBlocks()) {
            if (this.getOwner() instanceof ServerPlayer serverPlayer) {
//                serverPlayer.displayClientMessage(Component.literal("Timmy is Around").withStyle(ChatFormatting.DARK_GREEN), false);
                if (this.random.nextFloat() < 0.067F) {
//                    serverPlayer.displayClientMessage(Component.literal("effect").withStyle(ChatFormatting.LIGHT_PURPLE), false);
                    serverPlayer.getCapability(PlayerTimeProvider.PLAYER_TIME_CAPABILITY).ifPresent(playerTime -> {
                        float incrementTime = (this.random.nextInt(5) + 1) * 60;
                        this.level().playSound(null, this.blockPosition(), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.NEUTRAL, 0.5F,
                                this.random.nextFloat() * 0.1F + 0.9F);
                        playerTime.incrementTime(incrementTime, serverPlayer);
                    });
                }
            }
        }

    }
}
