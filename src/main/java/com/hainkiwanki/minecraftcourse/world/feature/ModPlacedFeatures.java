package com.hainkiwanki.minecraftcourse.world.feature;

import com.hainkiwanki.minecraftcourse.config.MinecraftCourseCommonConfigs;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModPlacedFeatures {
    public static final Holder<PlacedFeature> CHERRY_BLOSSOM_PLACED = PlacementUtils.register("cherry_blossom_placed",
            ModConfiguredFeature.CHERRY_BLOSSOM_SPAWN, VegetationPlacements.treePlacement(
                    PlacementUtils.countExtra(1, 0.1f, 2)));

    public static final Holder<PlacedFeature> PINK_ROSE_PLACED = PlacementUtils.register("pink_rose_placed",
            ModConfiguredFeature.PINK_ROSE, InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

    public static final Holder<PlacedFeature> COBALT_ORE_PLACED = PlacementUtils.register("cobalt_ore_placed",
            ModConfiguredFeature.ORE_COBALT, ModOrePlacement.commonOrePlacement(MinecraftCourseCommonConfigs.COBALT_ORE_VEINS_PER_CHUNK.get(),
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(88))));
}
