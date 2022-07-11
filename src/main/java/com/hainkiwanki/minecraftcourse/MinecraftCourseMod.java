package com.hainkiwanki.minecraftcourse;

import com.hainkiwanki.minecraftcourse.block.ModBlocks;
import com.hainkiwanki.minecraftcourse.block.entity.ModBlockEntities;
import com.hainkiwanki.minecraftcourse.enchantment.ModEnchantments;
import com.hainkiwanki.minecraftcourse.fluid.ModFluids;
import com.hainkiwanki.minecraftcourse.item.ModItems;
import com.hainkiwanki.minecraftcourse.painting.ModPaintings;
import com.hainkiwanki.minecraftcourse.screen.CobaltBlasterScreen;
import com.hainkiwanki.minecraftcourse.screen.ModMenuTypes;
import com.hainkiwanki.minecraftcourse.sound.ModSounds;
import com.hainkiwanki.minecraftcourse.util.ModItemProperties;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MinecraftCourseMod.MOD_ID)
public class MinecraftCourseMod
{
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "minecraftcourse";

    public MinecraftCourseMod() {
        // Register the setup method for modloading
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItems.register(eventBus);
        ModBlocks.register(eventBus);
        ModEnchantments.register(eventBus);
        ModSounds.register(eventBus);
        ModPaintings.register(eventBus);
        ModFluids.register(eventBus);
        ModBlockEntities.register(eventBus);
        ModMenuTypes.register(eventBus);

        eventBus.addListener(this::setup);
        eventBus.addListener(this::clientSetup);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.CHERRY_BLOSSOM_DOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.CHERRY_BLOSSOM_TRAPDOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.TURNIP_CROP.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.PINK_ROSE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.POTTED_PINK_ROSE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.COBALT_BLASTER.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(ModFluids.HONEY_BLOCK.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModFluids.HONEY_FLUID.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModFluids.HONEY_FLOWING.get(), RenderType.cutout());

        ModItemProperties.addCustomItemPorperties();
        MenuScreens.register(ModMenuTypes.COBALT_BLASTER_MENU.get(), CobaltBlasterScreen::new);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        event.enqueueWork(() -> {
            ComposterBlock.COMPOSTABLES.put(ModItems.TURNIP_SEEDS.get(), 0.3f);
            ComposterBlock.COMPOSTABLES.put(ModItems.TURNIP.get(), 0.65f);

            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.PINK_ROSE.getId(), ModBlocks.POTTED_PINK_ROSE);
        });
    }
}
