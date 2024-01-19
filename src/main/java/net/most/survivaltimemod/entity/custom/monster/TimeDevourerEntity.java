package net.most.survivaltimemod.entity.custom.monster;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.most.survivaltimemod.effect.ModEffects;
import net.most.survivaltimemod.sound.ModSounds;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class TimeDevourerEntity extends Monster implements GeoEntity {
    public TimeDevourerEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }


    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private long lastSwing;
    public String animationProcedure = "empty";
    private boolean lastLoop;

    @Override
    public @NotNull MobType getMobType() {
        return MobType.UNDEAD;
    }

    @Override
    protected SoundEvent getHurtSound(@NotNull DamageSource pDamageSource) {
        return ModSounds.TIME_DEVOURER_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.TIME_DEVOURER_AMBIENT.get();
    }

    public static AttributeSupplier setAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 40.0D)
                .add(Attributes.FOLLOW_RANGE, 40.0D)
                .add(Attributes.MOVEMENT_SPEED, (double) 0.27F)
                .add(Attributes.ATTACK_DAMAGE, 6.0D)
                .add(Attributes.ARMOR, 5.0D)
                .add(Attributes.ATTACK_SPEED, 5.0D)
                .add(Attributes.ARMOR_TOUGHNESS)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE).build();
    }


    @Override
    public int getExperienceReward() {
        this.xpReward = (int) ((double) this.xpReward * 3.0D);
        return super.getExperienceReward();
    }


    public boolean doHurtTarget(@NotNull Entity pEntity) {
        boolean flag = super.doHurtTarget(pEntity);
        if (flag) {
            float f = this.level().getCurrentDifficultyAt(this.blockPosition()).getEffectiveDifficulty();
            if (this.getMainHandItem().isEmpty() && this.isOnFire() && this.random.nextFloat() < f * 0.3F) {
                pEntity.setSecondsOnFire(2 * (int) f);
            }
            if (pEntity instanceof ServerPlayer serverPlayer && !this.level().isClientSide()) {
                int duration = this.random.nextInt(20 * 60 * 5) + 20 * 60;
                MobEffect pEffect = ModEffects.TIME_EXTINGUISHER.get();
                if (serverPlayer.hasEffect(pEffect)) {
                    MobEffectInstance effect = serverPlayer.getEffect(pEffect);
                    serverPlayer.removeEffect(pEffect);
                    serverPlayer.addEffect(new MobEffectInstance(pEffect,
                            effect != null ? effect.getDuration() + duration : duration,
                            0, true, true));
                }else{
                    serverPlayer.addEffect(new MobEffectInstance(pEffect,
                            duration,
                            0, true, true));
                }

            }
        }

        return flag;
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (this.isAlive()) {
            boolean flag = this.isSunSensitive() && this.isSunBurnTick();
            if (flag) {
                ItemStack itemstack = this.getItemBySlot(EquipmentSlot.HEAD);
                if (!itemstack.isEmpty()) {
                    if (itemstack.isDamageableItem()) {
                        itemstack.setDamageValue(itemstack.getDamageValue() + this.random.nextInt(2));
                        if (itemstack.getDamageValue() >= itemstack.getMaxDamage()) {
                            this.broadcastBreakEvent(EquipmentSlot.HEAD);
                            this.setItemSlot(EquipmentSlot.HEAD, ItemStack.EMPTY);
                        }
                    }

                    flag = false;
                }

                if (flag) {
                    this.setSecondsOnFire(8);
                }
            }
        }
    }

    private boolean isSunSensitive() {
        return false;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "Walk/Idle", 4, this::movementPredicate));
        controllerRegistrar.add(new AnimationController<>(this, "Attack", 4, this::attackingPredicate));
        controllerRegistrar.add(new AnimationController<>(this, "Procedure", 4, this::procedurePredicate));

    }

    private PlayState procedurePredicate(AnimationState<TimeDevourerEntity> event) {
        Entity entity = this;
        Level world = entity.level();
        boolean loop = false;
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();
        if (!loop && this.lastLoop) {
            this.lastLoop = false;
            event.getController().setAnimation(RawAnimation.begin().thenPlay(this.animationProcedure));
            event.getController().forceAnimationReset();

            return PlayState.STOP;
        }
        if (!this.animationProcedure.equals("empty") && event.getController().getAnimationState() == AnimationController.State.STOPPED) {
            if (!loop) {
                event.getController().setAnimation(RawAnimation.begin().thenPlay(this.animationProcedure));
                if (event.getController().getAnimationState() == AnimationController.State.STOPPED) {
                    this.animationProcedure = "empty";
                    event.getController().forceAnimationReset();
                }
            } else {
                event.getController().setAnimation(RawAnimation.begin().thenLoop(this.animationProcedure));
                this.lastLoop = true;
            }
        }
        return PlayState.CONTINUE;
    }


    private PlayState attackingPredicate(AnimationState<TimeDevourerEntity> event) {
        double d1 = this.getX() - this.xOld;
        double d0 = this.getZ() - this.zOld;
        float velocity = (float) Math.sqrt(d1 * d1 + d0 * d0);
        if (getAttackAnim(event.getPartialTick()) > 0f && !this.swinging) {
            this.swinging = true;
            this.lastSwing = level().getGameTime();
        }
        if (this.swinging && this.lastSwing + 7L <= level().getGameTime()) {
            this.swinging = false;
        }
        if (this.swinging && event.getController().getAnimationState() == AnimationController.State.STOPPED) {
            event.getController().forceAnimationReset();
            return event.setAndContinue(RawAnimation.begin().thenPlay("animation.time_devourer.attack"));
        }
        return PlayState.CONTINUE;
    }

    private PlayState movementPredicate(AnimationState<TimeDevourerEntity> state) {
        if (this.animationProcedure.equals("empty")) {
            if ((state.isMoving() || !(state.getLimbSwingAmount() > -0.15F && state.getLimbSwingAmount() < 0.15F))

            ) {
                return state.setAndContinue(RawAnimation.begin().thenLoop("animation.time_devourer.walk"));
            }
            return state.setAndContinue(RawAnimation.begin().thenLoop("animation.time_devourer.idle"));
        }
        return PlayState.STOP;

    }


    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 10.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.addBehaviourGoals();
    }

    protected void addBehaviourGoals() {
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 2.0D, true));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this).setAlertOthers());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Zombie.class, true));

    }


    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
