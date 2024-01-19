package net.most.survivaltimemod.entity.client.monster.timedevourer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.most.survivaltimemod.SurvivalTimeMod;
import net.most.survivaltimemod.entity.custom.animal.TimekeeperEntity;
import net.most.survivaltimemod.entity.custom.monster.TimeDevourerEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class TimeDevourerModel extends GeoModel<TimeDevourerEntity> {
    @Override
    public ResourceLocation getModelResource(TimeDevourerEntity timekeeperEntity) {
        return new ResourceLocation(SurvivalTimeMod.MOD_ID, "geo/time_devourer.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(TimeDevourerEntity timekeeperEntity) {
        return new ResourceLocation(SurvivalTimeMod.MOD_ID, "textures/entity/time_devourer.png");
    }

    @Override
    public ResourceLocation getAnimationResource(TimeDevourerEntity timekeeperEntity) {
        return new ResourceLocation(SurvivalTimeMod.MOD_ID, "animations/time_devourer.animation.json");
    }


    @Override
    public void setCustomAnimations(TimeDevourerEntity animatable, long instanceId, AnimationState<TimeDevourerEntity> animationState) {
        CoreGeoBone head = this.getAnimationProcessor().getBone("bone8");
        if(head!=null){
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch()* Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw()* Mth.DEG_TO_RAD);
        }
    }
}
