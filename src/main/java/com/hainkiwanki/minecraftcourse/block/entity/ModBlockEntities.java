package com.hainkiwanki.minecraftcourse.block.entity;

import com.hainkiwanki.minecraftcourse.MinecraftCourseMod;
import com.hainkiwanki.minecraftcourse.block.ModBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, MinecraftCourseMod.MOD_ID);

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }

    public static final RegistryObject<BlockEntityType<CobaltBlasterBlockEntity>> COBALT_BLASTER = BLOCK_ENTITIES.register("cobalt_blaster",
            () -> BlockEntityType.Builder.of(CobaltBlasterBlockEntity::new, ModBlocks.COBALT_BLASTER.get()).build(null));
}
