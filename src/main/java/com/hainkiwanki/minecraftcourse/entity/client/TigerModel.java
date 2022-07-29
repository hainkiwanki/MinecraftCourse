package com.hainkiwanki.minecraftcourse.entity.client;

import com.hainkiwanki.minecraftcourse.MinecraftCourseMod;
import com.hainkiwanki.minecraftcourse.entity.custom.TigerEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class TigerModel extends AnimatedGeoModel<TigerEntity> {
    @Override
    public ResourceLocation getModelResource(TigerEntity entity)	{
        return new ResourceLocation(MinecraftCourseMod.MOD_ID, "geo/tiger.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(TigerEntity entity) {
        return new ResourceLocation(MinecraftCourseMod.MOD_ID, "textures/entity/tiger/tiger.png");
    }

    @Override
    public ResourceLocation getAnimationResource(TigerEntity entity)	{
        return new ResourceLocation(MinecraftCourseMod.MOD_ID, "animations/tiger.animation.json");
    }
}