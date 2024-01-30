package net.most.survivaltimemod.item.client.armor;

import net.minecraft.resources.ResourceLocation;
import net.most.survivaltimemod.SurvivalTimeMod;
import net.most.survivaltimemod.item.custom.BaseArmorItem;
import software.bernie.geckolib.model.GeoModel;

public class BaseArmorModel extends GeoModel<BaseArmorItem> {
    final String textureName;

    public BaseArmorModel(String textureName) {this.textureName = textureName;}

    private String getTextureName() {
        return textureName;
    }

    @Override
    public ResourceLocation getModelResource(BaseArmorItem baseArmorItem) {
        return new ResourceLocation(SurvivalTimeMod.MOD_ID, "geo/base_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BaseArmorItem baseArmorItem) {
        return new ResourceLocation(SurvivalTimeMod.MOD_ID, "textures/armor/" + getTextureName() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(BaseArmorItem baseArmorItem) {
        return new ResourceLocation(SurvivalTimeMod.MOD_ID, "animations/base_armor.animation.json");
    }
}
