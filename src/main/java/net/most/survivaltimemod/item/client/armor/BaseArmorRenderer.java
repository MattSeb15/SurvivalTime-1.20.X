package net.most.survivaltimemod.item.client.armor;

import net.most.survivaltimemod.item.custom.BaseArmorItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class BaseArmorRenderer extends GeoArmorRenderer<BaseArmorItem> {
    public BaseArmorRenderer(String textureName) {
        super(new BaseArmorModel(textureName));
    }
}
