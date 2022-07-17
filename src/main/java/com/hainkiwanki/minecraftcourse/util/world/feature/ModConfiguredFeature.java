package com.hainkiwanki.minecraftcourse.util.world.feature;

import com.hainkiwanki.minecraftcourse.block.ModBlocks;
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
    public static final ConfiguredFeature<TreeConfiguration, ?> CHERRY_BLOSSOM_TREE =
            FeatureUtils.register("cherry_blossom", Feature.TREE.configured(
                    new TreeConfiguration.TreeConfigurationBuilder(
                            BlockStateProvider.simple(ModBlocks.CHERRY_BLOSSOM_LOG.get()),
                            new StraightTrunkPlacer(5, 6, 3),
                            BlockStateProvider.simple(ModBlocks.CHERRY_BLOSSOM_LEAVES.get()),
                            new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 4),
                            new TwoLayersFeatureSize(1, 0, 2)).build()));

    public static final ConfiguredFeature<RandomFeatureConfiguration, ?> CHERRY_BLOSSOM_TREE_CHECKED =
            FeatureUtils.register("cherry_blossom_feature",
                    Feature.RANDOM_SELECTOR.configured(new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(
                            CHERRY_BLOSSOM_TREE.filteredByBlockSurvival(ModBlocks.CHERRY_BLOSSOM_SAPLING.get()), 0.1f)),
                            CHERRY_BLOSSOM_TREE.filteredByBlockSurvival(ModBlocks.CHERRY_BLOSSOM_SAPLING.get()))));

    public static final ConfiguredFeature<RandomPatchConfiguration, ?> PINK_ROSE = FeatureUtils.register("flower_pink_rose",
            Feature.FLOWER.configured(new RandomPatchConfiguration(64, 6, 2, () -> {
                return Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.PINK_ROSE.get()))).onlyWhenEmpty();
            })));

    /*public static final List<OreConfiguration.TargetBlockState> OVERWORLD_COBALT_ORES = List.of(
            OreConfiguration.target(STONE_ORE_REPLACEABLES, ModBlocks.COBALT_ORE.get().defaultBlockState()),
            OreConfiguration.target(DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_COBALT_ORE.get().defaultBlockState()));

    public static final ConfiguredFeature<?, ?> COBALT_ORE = FeatureUtils.register("cobalt_ore",
            Feature.ORE.configured(new OreConfiguration(OVERWORLD_COBALT_ORES, 9)));*/

    public static final List<OreConfiguration.TargetBlockState> ORE_COBALT_TARGET_LIST =
            List.of(OreConfiguration.target(STONE_ORE_REPLACEABLES, ModBlocks.COBALT_ORE.get().defaultBlockState()),
                    OreConfiguration.target(DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_COBALT_ORE.get().defaultBlockState()));

    public static final ConfiguredFeature<?, ?> ORE_COBALT = FeatureUtils.register("ore_cobalt",
            Feature.ORE.configured(new OreConfiguration(ORE_COBALT_TARGET_LIST, 20)));

}
