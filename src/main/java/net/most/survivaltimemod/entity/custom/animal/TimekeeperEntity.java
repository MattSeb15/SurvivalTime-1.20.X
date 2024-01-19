package net.most.survivaltimemod.entity.custom.animal;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.most.survivaltimemod.entity.ModEntities;
import net.most.survivaltimemod.item.ModItems;
import net.most.survivaltimemod.sound.ModSounds;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class TimekeeperEntity extends Animal implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    private final String itemTimeCompound = "ItemLayTime";
    private final String favoriteFoodAmountCompound = "FavoriteFoodAmount";
    private final Item itemToSpawn = ModItems.CLOCK_FRAGMENT.get();
    private final int itemTimeMin = 24000; //24000
    private final int itemTimeMax = 36000; //36000
    private static final Ingredient FAVORITE_FOOD = Ingredient.of(ModItems.TEMPORAL_TUBER_COOKED.get(), Items.PUMPKIN_PIE, Items.COOKIE);
    public int itemTime = this.random.nextInt(itemTimeMax - itemTimeMin) + itemTimeMin;
    public int favoriteFoodAmount = this.random.nextInt(4) + 3;


    private static final RawAnimation WALK = RawAnimation.begin().thenPlay("animation.timekeeper.walk");
    private static final RawAnimation IDLE = RawAnimation.begin().thenPlay("animation.timekeeper.idle");
    private static final RawAnimation INTERACT_RIGHT =
            RawAnimation.begin().thenPlay("animation.timekeeper.eat");
    private static final RawAnimation BAD = RawAnimation.begin().thenPlay("animation.timekeeper.bad");

    public TimekeeperEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public boolean isFavoriteFood(ItemStack pStack) {
        return FAVORITE_FOOD.test(pStack);
    }

    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .build();
    }

    @Override
    public boolean canHoldItem(@NotNull ItemStack pStack) {
        return FAVORITE_FOOD.test(pStack);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new TemptGoal(this, 1.0D, FAVORITE_FOOD, false));
        this.goalSelector.addGoal(2, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));

    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (!this.level().isClientSide && this.isAlive() && !this.isBaby() && --this.itemTime <= 0) {
            if (this.random.nextBoolean()) {
                this.playSound(SoundEvents.CHICKEN_EGG, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
                this.spawnAtLocation(itemToSpawn);
                this.gameEvent(GameEvent.ENTITY_PLACE);
                this.itemTime = this.random.nextInt(itemTimeMax - itemTimeMin) + itemTimeMin;
                this.favoriteFoodAmount = this.random.nextInt(4) + 3;
            }
        }
    }


    @Override
    public @NotNull InteractionResult mobInteract(@NotNull Player pPlayer, @NotNull InteractionHand pHand) {
        ItemStack pItemStackInHand = pPlayer.getItemInHand(pHand);

        if (!this.level().isClientSide() && !pItemStackInHand.isEmpty() &&
                this.isFavoriteFood(pPlayer.getItemInHand(pHand))
        ) {

            if (this.favoriteFoodAmount > 0) {
                this.favoriteFoodAmount--;
                if (pItemStackInHand.is(ModItems.TEMPORAL_TUBER_COOKED.get())) {
                    this.itemTime -= 20 * 60 * 5;
                } else if (pItemStackInHand.is(Items.PUMPKIN_PIE)) {
                    this.itemTime -= 20 * 60 * 3;
                } else if (pItemStackInHand.is(Items.COOKIE)) {
                    this.itemTime -= 20 * 20;
                }
                playTimekeeperGoodSound();
                setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(pItemStackInHand.copy().getItem(), 1));
                usePlayerItem(pPlayer, pHand, pItemStackInHand);
                eat(this.level(), getMainHandItem());
                this.level().broadcastEntityEvent(this, (byte) 99);

//                this.level().playSound(null, this.blockPosition(), ModSounds.TIMEKEEPER_GOOD.get(), this.getSoundSource(), 1.0F, 1.0F);
            } else {

                pPlayer.displayClientMessage(Component.literal("I'm full!"),
                        false);
                playTimekeeperBadSound();
//                this.triggerAnim("Cant Eat", "bad");
            }
            return InteractionResult.SUCCESS;

        }

        return InteractionResult.PASS;
    }

    private void playTimekeeperGoodSound() {
        this.level().playSound(null, this.blockPosition(), ModSounds.TIMEKEEPER_GOOD.get(), this.getSoundSource(), 1.0F, 1.0F);

    }

    private void playTimekeeperBadSound() {
        this.level().playSound(null, this.blockPosition(), ModSounds.TIMEKEEPER_BAD.get(), this.getSoundSource(), 1.0F, 1.0F);
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.TIMEKEEPER_AMBIENT.get();
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        if (pCompound.contains(itemTimeCompound)) {
            this.itemTime = pCompound.getInt(itemTimeCompound);
        }
        if (pCompound.contains(favoriteFoodAmountCompound)) {
            this.favoriteFoodAmount = pCompound.getInt(favoriteFoodAmountCompound);
        }
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt(itemTimeCompound, this.itemTime);
        pCompound.putInt(favoriteFoodAmountCompound, this.favoriteFoodAmount);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(@NotNull ServerLevel pLevel, @NotNull AgeableMob pOtherParent) {
        return ModEntities.TIMEKEEPER.get().create(pLevel);
    }

    @Override
    public void handleEntityEvent(byte pId) {
        if (pId == 99) {
            for (int i = 0; i < 7; ++i) {
                double d0 = this.random.nextGaussian() * 0.02D;
                double d1 = this.random.nextGaussian() * 0.02D;
                double d2 = this.random.nextGaussian() * 0.02D;
                this.level().addParticle(ParticleTypes.HEART, this.getRandomX(1.0D), this.getRandomY() + 0.5D, this.getRandomZ(1.0D), d0, d1, d2);
            }
        } else {
            super.handleEntityEvent(pId);
        }
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "Walk/Idle", 0, this::predicate));
//                new AnimationController<>(this, "Right Hand", 10, state -> predicateHandPose(getRightHand(), state))
//                .triggerableAnim("interact", INTERACT_RIGHT),
//                new AnimationController<>(this, "Cant Eat", 10, state -> predicateCantEatPose(getRightHand(), state))
//                        .triggerableAnim("bad", BAD))


    }

//    private PlayState predicateCantEatPose(InteractionHand rightHand, AnimationState<TimekeeperEntity> state) {
//
//        if(state.getController().hasAnimationFinished()){
//            return PlayState.STOP;
//        }
//        return PlayState.STOP;
//    }
//
//
//    private PlayState predicateHandPose(InteractionHand hand, AnimationState<TimekeeperEntity> state) {
//        state.getController().setAnimation(INTERACT_RIGHT);
//        ItemStack itemstack = this.getItemInHand(hand);
//        if(itemstack.isEmpty()){
//            return PlayState.STOP;
//        }
//
//        return PlayState.CONTINUE;
//    }
//
//    protected InteractionHand getRightHand() {
//        return !this.isLeftHanded() ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND;
//    }

    private PlayState predicate(@NotNull AnimationState<TimekeeperEntity> state) {
        state.getController().setAnimation(state.isMoving() ? WALK : IDLE);
        return PlayState.CONTINUE;
    }


    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
