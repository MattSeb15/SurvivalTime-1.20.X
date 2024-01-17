package net.most.survivaltimemod.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.most.survivaltimemod.SurvivalTimeMod;
import net.most.survivaltimemod.entity.custom.TimekeeperEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class TimekeeperModel  extends GeoModel<TimekeeperEntity> {
    @Override
    public ResourceLocation getModelResource(TimekeeperEntity timekeeperEntity) {
        return new ResourceLocation(SurvivalTimeMod.MOD_ID, "geo/timekeeper.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(TimekeeperEntity timekeeperEntity) {
        return new ResourceLocation(SurvivalTimeMod.MOD_ID, "textures/entity/timekeeper.png");
    }

    @Override
    public ResourceLocation getAnimationResource(TimekeeperEntity timekeeperEntity) {
        return new ResourceLocation(SurvivalTimeMod.MOD_ID, "animations/timekeeper.animation.json");
    }


    @Override
    public void setCustomAnimations(TimekeeperEntity animatable, long instanceId, AnimationState<TimekeeperEntity> animationState) {
        CoreGeoBone head = this.getAnimationProcessor().getBone("head_bone");
        if(head!=null){
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch()* Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw()* Mth.DEG_TO_RAD);
        }
    }
}
