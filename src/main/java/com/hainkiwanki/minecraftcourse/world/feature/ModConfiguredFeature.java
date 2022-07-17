package com.hainkiwanki.minecraftcourse.world.feature;

import com.hainkiwanki.minecraftcourse.block.ModBlocks;
import com.hainkiwanki.minecraftcourse.config.MinecraftCourseCommonConfigs;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;

import static net.minecraft.data.worldgen.features.OreFeatures.DEEPSLATE_ORE_REPLACEABLES;
import static net.minecraft.data.worldgen.features.OreFeatures.STONE_ORE_REPLACEABLES;

public class ModConfiguredFeature {
    public static final Holder< ? extends ConfiguredFeature<TreeConfiguration, ?>> CHERRY_BLOSSOM_TREE =
            FeatureUtils.register("cherry_blossom", Feature.TREE,
                    new TreeConfiguration.TreeConfigurationBuilder(
                            BlockStateProvider.simple(ModBlocks.CHERRY_BLOSSOM_LOG.get()),
                            new StraightTrunkPlacer(5, 6, 3),
                            BlockStateProvider.simple(ModBlocks.CHERRY_BLOSSOM_LEAVES.get()),
                            new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 4),
                            new TwoLayersFeatureSize(1, 0, 2)).build());

    public static final Holder<PlacedFeature> CHERRY_BLOSSOM_CHECKED = PlacementUtils.register("cherry_blossom_checked",
            CHERRY_BLOSSOM_TREE, PlacementUtils.filteredByBlockSurvival(ModBlocks.CHERRY_BLOSSOM_SAPLING.get()));

    public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> CHERRY_BLOSSOM_SPAWN =
            FeatureUtils.register("cherry_blossom_spawn", Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(CHERRY_BLOSSOM_CHECKED, 0.5f)), CHERRY_BLOSSOM_CHECKED));

    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> PINK_ROSE = FeatureUtils.register("flower_pink_rose",
            Feature.FLOWER,
            new RandomPatchConfiguration(64, 6, 2,PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                    new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.PINK_ROSE.get())))));

    public static final List<OreConfiguration.TargetBlockState> ORE_COBALT_TARGET_LIST =
            List.of(OreConfiguration.target(STONE_ORE_REPLACEABLES, ModBlocks.COBALT_ORE.get().defaultBlockState()),
                    OreConfiguration.target(DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_COBALT_ORE.get().defaultBlockState()));

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ORE_COBALT = FeatureUtils.register("ore_cobalt",
            Feature.ORE, new OreConfiguration(ORE_COBALT_TARGET_LIST, MinecraftCourseCommonConfigs.COBALT_ORE_VEIN_SIZE.get()));

}
