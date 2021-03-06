package com.hainkiwanki.minecraftcourse.world;

import com.hainkiwanki.minecraftcourse.MinecraftCourseMod;
import com.hainkiwanki.minecraftcourse.world.gen.ModEntityGeneration;
import com.hainkiwanki.minecraftcourse.world.gen.ModTreeGeneration;
import com.hainkiwanki.minecraftcourse.world.gen.ModFlowerGeneration;
import com.hainkiwanki.minecraftcourse.world.gen.ModOreGeneration;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MinecraftCourseMod.MOD_ID)
public class ModWorldEvents {
    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event) {
        ModOreGeneration.generateOres(event);
        ModTreeGeneration.generateTrees(event);
        ModFlowerGeneration.generateFlowers(event);
        ModEntityGeneration.onEntitySpawn(event);
    }
}
