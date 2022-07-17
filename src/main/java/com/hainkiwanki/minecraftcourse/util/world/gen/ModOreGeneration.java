package com.hainkiwanki.minecraftcourse.util.world.gen;

import com.hainkiwanki.minecraftcourse.util.world.feature.ModPlacedFeatures;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.List;
import java.util.function.Supplier;

public class ModOreGeneration {
    public static void generateOres(final BiomeLoadingEvent event) {
        List<Supplier<PlacedFeature>> base =
                event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES);

        base.add(() -> ModPlacedFeatures.COBALT_ORE_PLACED);
    }
}
