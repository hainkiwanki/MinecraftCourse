package com.hainkiwanki.minecraftcourse.event;

import com.hainkiwanki.minecraftcourse.MinecraftCourseMod;
import com.hainkiwanki.minecraftcourse.block.ModBlocks;
import com.hainkiwanki.minecraftcourse.block.ModWoodTypes;
import com.hainkiwanki.minecraftcourse.block.entity.ModBlockEntities;
import com.hainkiwanki.minecraftcourse.block.entity.client.PedestalBlockEntityRenderer;
import com.hainkiwanki.minecraftcourse.entity.ModEntityTypes;
import com.hainkiwanki.minecraftcourse.entity.client.RaccoonRenderer;
import com.hainkiwanki.minecraftcourse.entity.client.TigerRenderer;
import com.hainkiwanki.minecraftcourse.screen.CobaltBlasterScreen;
import com.hainkiwanki.minecraftcourse.screen.ModMenuTypes;
import com.hainkiwanki.minecraftcourse.util.ModItemProperties;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = MinecraftCourseMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {
    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.PEDESTAL.get(), PedestalBlockEntityRenderer::new);
    }

    @SubscribeEvent
    public static void clientSetup(final FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.WINTER_WINDOW.get(), RenderType.translucent());

        ModItemProperties.addCustomItemPorperties();
        MenuScreens.register(ModMenuTypes.COBALT_BLASTER_MENU.get(), CobaltBlasterScreen::new);
        WoodType.register(ModWoodTypes.CHERRY_BLOSSOM);

        EntityRenderers.register(ModEntityTypes.RACCOON.get(), RaccoonRenderer::new);
        EntityRenderers.register(ModEntityTypes.TIGER.get(), TigerRenderer::new);
    }
}