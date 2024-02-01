package net.most.survivaltimemod.util;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ArmorMaterial;
import net.most.survivaltimemod.effect.ModEffects;
import net.most.survivaltimemod.effect.RandomIncrementTimeEffect;
import net.most.survivaltimemod.item.ModItems;

public class ModArmorUtils {


    public static ObjectArrayList<Object> getArmorPartsListByMaterial(ArmorMaterial material){
        String materialName = material.getName();
        return switch (materialName) {
            case "flux" -> ObjectArrayList.of(
                    ModItems.FLUX_BOOTS.get(),
                    ModItems.FLUX_HELMET.get(),
                    ModItems.FLUX_CHESTPLATE.get(),
                    ModItems.FLUX_LEGGINGS.get());
            case "epoch" -> ObjectArrayList.of(
                    ModItems.EPOCH_BOOTS.get(),
                    ModItems.EPOCH_HELMET.get(),
                    ModItems.EPOCH_CHESTPLATE.get(),
                    ModItems.EPOCH_LEGGINGS.get());
            case "tempora" -> ObjectArrayList.of(
                    ModItems.TEMPORA_BOOTS.get(),
                    ModItems.TEMPORA_HELMET.get(),
                    ModItems.TEMPORA_CHESTPLATE.get(),
                    ModItems.TEMPORA_LEGGINGS.get());
            case "chrona" -> ObjectArrayList.of(
                    ModItems.CHRONA_BOOTS.get(),
                    ModItems.CHRONA_HELMET.get(),
                    ModItems.CHRONA_CHESTPLATE.get(),
                    ModItems.CHRONA_LEGGINGS.get());
            default -> ObjectArrayList.of(
                    ModItems.LOOP_HELMET.get(),
                    ModItems.LOOP_CHESTPLATE.get(),
                    ModItems.LOOP_LEGGINGS.get(),
                    ModItems.LOOP_BOOTS.get()
                    );
        };
    }

    public static String textureNameByMaterial(ArmorMaterial material){
        String materialName = material.getName();
        return  materialName+"_armor";
    }

    public static MobEffectInstance mobEffectInstanceByMaterial(ArmorMaterial material){
        String materialName = material.getName();
        RandomIncrementTimeEffect effect = ModEffects.RANDOM_INCREMENT_TIME_EFFECT.get();
        return switch (materialName) {
            case "flux" -> new MobEffectInstance(effect.setMultiplier(1.5f).setProbability(0.05f), 200, 0, false, true, true);
            case "epoch" -> new MobEffectInstance(effect.setMultiplier(2.0f).setProbability(0.08f), 200, 0, false, true, true);
            case "tempora" -> new MobEffectInstance(effect.setMultiplier(2.5f).setProbability(0.10f), 200, 0, false, true, true);
            case "chrona" -> new MobEffectInstance(effect.setMultiplier(4.0f).setProbability(0.20f), 200, 0, false, true, true);
            default -> new MobEffectInstance(effect.setMultiplier(1.2f).setProbability(0.02f), 200, 0, false, true, true);
        };
    }

}
