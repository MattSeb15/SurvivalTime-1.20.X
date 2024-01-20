package net.most.survivaltimemod.entity.custom.throwable;

import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.registries.ForgeRegistries;
import net.most.survivaltimemod.entity.ModEntities;
import net.most.survivaltimemod.item.ModItems;
import net.most.survivaltimemod.sound.ModSounds;
import org.jetbrains.annotations.NotNull;

public class PurifierEntity extends ThrowableItemProjectile {
    public PurifierEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public PurifierEntity(Level pLevel) {
        super(ModEntities.PURIFIER_PROJECTILE.get(), pLevel);
    }

    public PurifierEntity(Level pLevel, LivingEntity livingEntity) {
        super(ModEntities.PURIFIER_PROJECTILE.get(), livingEntity, pLevel);
    }

    @Override
    protected @NotNull Item getDefaultItem() {
        return ModItems.PURIFIER_ITEM.get();
    }

    private ParticleOptions getParticle() {
        return ParticleTypes.ITEM_SNOWBALL;
    }

    @Override
    public void handleEntityEvent(byte pId) {
        super.handleEntityEvent(pId);
        if (pId == 3) {
            ParticleOptions particleoptions = this.getParticle();

            for (int i = 0; i < 8; ++i) {
                this.level().addParticle(particleoptions, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    protected void onHitEntity(@NotNull EntityHitResult pResult) {
        if (!this.level().isClientSide()) {
            Entity entity = pResult.getEntity();
            if (entity instanceof LivingEntity livingEntity) {
                SoundEvent sound = ModSounds.NO_RESULT_IMPACT.get();
                if (livingEntity.removeAllEffects()) {
                    sound = ModSounds.PURIFIER_IMPACT.get();
                }
                this.level().playSound(null, entity.getX(), entity.getY(), entity.getZ(), sound,
                        entity.getSoundSource(), 1.0F, this.random.nextFloat() * 0.4F + 0.9F);
            }
        }
        this.discard();
        super.onHitEntity(pResult);
    }

    @Override
    protected void onHitBlock(@NotNull BlockHitResult pResult) {
        super.onHitBlock(pResult);
        this.discard();
    }

    @Override
    protected void onHit(@NotNull HitResult pResult) {
        super.onHit(pResult);
        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte) 3);
            this.discard();
        }
    }
}
