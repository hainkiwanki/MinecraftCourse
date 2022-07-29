package com.hainkiwanki.minecraftcourse;

import com.hainkiwanki.minecraftcourse.block.ModBlocks;
import com.hainkiwanki.minecraftcourse.block.ModWoodTypes;
import com.hainkiwanki.minecraftcourse.block.entity.ModBlockEntities;
import com.hainkiwanki.minecraftcourse.effect.ModEffects;
import com.hainkiwanki.minecraftcourse.enchantment.ModEnchantments;
import com.hainkiwanki.minecraftcourse.entity.ModEntityTypes;
import com.hainkiwanki.minecraftcourse.item.ModItems;
import com.hainkiwanki.minecraftcourse.painting.ModPaintings;
import com.hainkiwanki.minecraftcourse.potion.ModPotions;
import com.hainkiwanki.minecraftcourse.recipe.ModRecipes;
import com.hainkiwanki.minecraftcourse.screen.ModMenuTypes;
import com.hainkiwanki.minecraftcourse.sound.ModSounds;
import com.hainkiwanki.minecraftcourse.util.BetterBrewingRecipe;
import com.hainkiwanki.minecraftcourse.villager.ModVillagers;
import com.hainkiwanki.minecraftcourse.world.feature.ModPlacedFeatures;
import com.hainkiwanki.minecraftcourse.world.structure.ModStructures;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;

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
        ModBlockEntities.register(eventBus);
        ModMenuTypes.register(eventBus);
        ModRecipes.register(eventBus);
        ModEffects.register(eventBus);
        ModPotions.register(eventBus);
        ModEntityTypes.register(eventBus);
        ModStructures.register(eventBus);
        ModVillagers.register(eventBus);
        ModPlacedFeatures.register(eventBus);
        GeckoLib.initialize();

        eventBus.addListener(this::setup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        event.enqueueWork(() -> {
            ComposterBlock.COMPOSTABLES.put(ModItems.TURNIP_SEEDS.get(), 0.3f);
            ComposterBlock.COMPOSTABLES.put(ModItems.TURNIP.get(), 0.65f);


            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.PINK_ROSE.getId(), ModBlocks.POTTED_PINK_ROSE);
            ModVillagers.registerPOIs();
            BlockEntityRenderers.register(ModBlockEntities.SIGN_BLOCK_ENTITIES.get(), SignRenderer::new);
            Sheets.addWoodType(ModWoodTypes.CHERRY_BLOSSOM);
            BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(Potions.AWKWARD, ModItems.COBALT_INGOT.get(), ModPotions.FREEZE_POTION.get()));
        });
    }
}
