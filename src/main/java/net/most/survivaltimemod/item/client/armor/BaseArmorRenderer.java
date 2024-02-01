package net.most.survivaltimemod.item.client.armor;

import net.minecraft.resources.ResourceLocation;
import net.most.survivaltimemod.SurvivalTimeMod;
import net.most.survivaltimemod.item.custom.BaseArmorItem;
import software.bernie.geckolib.GeckoLib;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class BaseArmorRenderer extends GeoArmorRenderer<BaseArmorItem> {
    public BaseArmorRenderer(String textureName) {
        super(new DefaultedItemGeoModel<>(new ResourceLocation(SurvivalTimeMod.MOD_ID, "armor/"+textureName)));
    }
}
