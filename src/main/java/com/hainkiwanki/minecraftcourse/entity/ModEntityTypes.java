package com.hainkiwanki.minecraftcourse.entity;

import com.hainkiwanki.minecraftcourse.MinecraftCourseMod;
import com.hainkiwanki.minecraftcourse.entity.custom.RaccoonEntity;
import com.hainkiwanki.minecraftcourse.entity.custom.TigerEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITIES, MinecraftCourseMod.MOD_ID);

    public static final RegistryObject<EntityType<RaccoonEntity>> RACCOON = ENTITY_TYPES.register("raccoon",
            () -> EntityType.Builder.of(RaccoonEntity::new, MobCategory.CREATURE)
                    .sized(0.8f, 0.6f)
                    .build(new ResourceLocation(MinecraftCourseMod.MOD_ID, "raccoon").toString()));

    public static final RegistryObject<EntityType<TigerEntity>> TIGER = ENTITY_TYPES.register("tiger",
            () -> EntityType.Builder.of(TigerEntity::new, MobCategory.CREATURE)
                    .sized(1f, 0.75f)
                    .build(new ResourceLocation(MinecraftCourseMod.MOD_ID, "tiger").toString()));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
