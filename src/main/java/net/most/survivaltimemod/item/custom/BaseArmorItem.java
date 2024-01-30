package net.most.survivaltimemod.item.custom;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.most.survivaltimemod.item.client.armor.BaseArmorRenderer;
import net.most.survivaltimemod.util.ModArmorUtils;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Set;
import java.util.function.Consumer;

public class BaseArmorItem extends ArmorItem implements GeoItem {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public String animationProcedure = "empty";
    public final String textureName;
    public final ArmorMaterial pMaterial;

    public BaseArmorItem(ArmorMaterial pMaterial, Type pType, Properties pProperties) {
        super(pMaterial, pType, pProperties);
        this.textureName = ModArmorUtils.textureNameByMaterial(pMaterial);
        this.pMaterial = pMaterial;
    }

    @Override
    public void initializeClient(@NotNull Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private BaseArmorRenderer renderer;

            @Override
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack,
                                                                   EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
                if (this.renderer == null)
                    this.renderer = new BaseArmorRenderer(textureName);

                this.renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original);
                return this.renderer;
            }
        });
    }

    @Override
    public void onInventoryTick(ItemStack stack, Level level, Player player, int slotIndex, int selectedIndex) {
        super.onInventoryTick(stack, level, player, slotIndex, selectedIndex);

        Set<Item> wornArmor = new ObjectOpenHashSet<>();

        for (ItemStack armorStack : player.getArmorSlots()) {
            // We can stop immediately if any of the slots are empty
            if (armorStack.isEmpty())
                return;

            wornArmor.add(armorStack.getItem());
        }
        // Check each of the pieces match our set
        boolean isFullSet = ModArmorUtils.getArmorPartsListByMaterial(pMaterial).containsAll(wornArmor);
        if(isFullSet){
            player.displayClientMessage(Component.literal("FULL LOOP ARMOR"), false);
        }
    }


    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar registrar) {
        registrar.add(new AnimationController<>(this, "controller", 5, this::predicate));
        registrar.add(new AnimationController<>(this, "procedureController", 5, this::procedurePredicate));

    }


    private PlayState predicate(AnimationState<BaseArmorItem> event) {
        if (this.animationProcedure.equals("empty")) {
            event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.base_armor.idle"));

            Entity entity = event.getData(DataTickets.ENTITY);

            if (entity instanceof ArmorStand) {
                return PlayState.CONTINUE;
            }

            Set<Item> wornArmor = new ObjectOpenHashSet<>();

            for (ItemStack stack : entity.getArmorSlots()) {
                if (stack.isEmpty())
                    return PlayState.STOP;

                wornArmor.add(stack.getItem());
            }

            boolean isWearingAll = ModArmorUtils.getArmorPartsListByMaterial(pMaterial).containsAll(wornArmor);

            return isWearingAll ? PlayState.CONTINUE : PlayState.STOP;
        }
        return PlayState.STOP;
    }


    private PlayState procedurePredicate(AnimationState<BaseArmorItem> event) {
        if (!this.animationProcedure.equals("empty") && event.getController().getAnimationState() == AnimationController.State.STOPPED) {
            event.getController().setAnimation(RawAnimation.begin().thenPlay(this.animationProcedure));
            if (event.getController().getAnimationState() == AnimationController.State.STOPPED) {
                this.animationProcedure = "empty";
                event.getController().forceAnimationReset();
            }

            Entity entity = event.getData(DataTickets.ENTITY);

            if (entity instanceof ArmorStand) {
                return PlayState.CONTINUE;
            }

            Set<Item> wornArmor = new ObjectOpenHashSet<>();

            for (ItemStack stack : entity.getArmorSlots()) {
                if (stack.isEmpty())
                    return PlayState.STOP;

                wornArmor.add(stack.getItem());
            }

            boolean isWearingAll = ModArmorUtils.getArmorPartsListByMaterial(pMaterial).containsAll(wornArmor);

            return isWearingAll ? PlayState.CONTINUE : PlayState.STOP;
        }
        return PlayState.CONTINUE;
    }



    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
