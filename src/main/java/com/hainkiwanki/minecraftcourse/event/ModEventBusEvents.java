package com.hainkiwanki.minecraftcourse.event;

import com.hainkiwanki.minecraftcourse.MinecraftCourseMod;
import com.hainkiwanki.minecraftcourse.entity.ModEntityTypes;
import com.hainkiwanki.minecraftcourse.entity.client.armor.CobaltArmorRenderer;
import com.hainkiwanki.minecraftcourse.entity.custom.RaccoonEntity;
import com.hainkiwanki.minecraftcourse.entity.custom.TigerEntity;
import com.hainkiwanki.minecraftcourse.event.loot.DowsingRodInIglooAdditionModifier;
import com.hainkiwanki.minecraftcourse.event.loot.TurnipSeedsFromGrassAdditionModifier;
import com.hainkiwanki.minecraftcourse.item.custom.CobaltArmorItem;
import com.hainkiwanki.minecraftcourse.recipe.CobaltBlasterRecipe;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import software.bernie.geckolib3.item.GeoArmorItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = MinecraftCourseMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerModifierSerializers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>>
                                                           event) {
        event.getRegistry().registerAll(
                new TurnipSeedsFromGrassAdditionModifier.Serializer().setRegistryName
                        (new ResourceLocation(MinecraftCourseMod.MOD_ID,"turnip_seeds_from_grass")),
                new DowsingRodInIglooAdditionModifier.Serializer().setRegistryName
                        (new ResourceLocation(MinecraftCourseMod.MOD_ID,"dowsing_rod_in_igloo"))
        );
    }

    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.RACCOON.get(), RaccoonEntity.setAttributes());
        event.put(ModEntityTypes.TIGER.get(), TigerEntity.setAttributes());
    }

    @SubscribeEvent
    public static void registerRecipeTypes(final RegistryEvent.Register<RecipeSerializer<?>> event) {
        Registry.register(Registry.RECIPE_TYPE, CobaltBlasterRecipe.Type.ID, CobaltBlasterRecipe.Type.INSTANCE);
    }

    @SubscribeEvent
    public static void registerArmorModels(final EntityRenderersEvent.AddLayers event) {
        GeoArmorRenderer.registerArmorRenderer(CobaltArmorItem.class, new CobaltArmorRenderer());
    }
}