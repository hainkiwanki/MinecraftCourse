package com.hainkiwanki.minecraftcourse.event;

import com.hainkiwanki.minecraftcourse.MinecraftCourseMod;
import com.hainkiwanki.minecraftcourse.entity.ModEntityTypes;
import com.hainkiwanki.minecraftcourse.entity.client.armor.CobaltArmorRenderer;
import com.hainkiwanki.minecraftcourse.entity.custom.RaccoonEntity;
import com.hainkiwanki.minecraftcourse.entity.custom.TigerEntity;
import com.hainkiwanki.minecraftcourse.item.custom.CobaltArmorItem;
import com.hainkiwanki.minecraftcourse.recipe.CobaltBlasterRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = MinecraftCourseMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerModifierSerializers(@Nonnull final RegisterEvent event) {
        event.register(ForgeRegistries.Keys.RECIPE_TYPES, recipeTypeRegisterHelper -> {
            recipeTypeRegisterHelper.register(new ResourceLocation(MinecraftCourseMod.MOD_ID, CobaltBlasterRecipe.Type.ID),
                    CobaltBlasterRecipe.Type.INSTANCE);
        });
    }
    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.RACCOON.get(), RaccoonEntity.setAttributes());
        event.put(ModEntityTypes.TIGER.get(), TigerEntity.setAttributes());
    }

    @SubscribeEvent
    public static void registerArmorModels(final EntityRenderersEvent.AddLayers event) {
        GeoArmorRenderer.registerArmorRenderer(CobaltArmorItem.class, new CobaltArmorRenderer());
    }
}