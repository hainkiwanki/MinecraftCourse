package com.hainkiwanki.minecraftcourse.world.feature;

import com.hainkiwanki.minecraftcourse.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GeodeBlockSettings;
import net.minecraft.world.level.levelgen.GeodeCrackSettings;
import net.minecraft.world.level.levelgen.GeodeLayerSettings;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
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
            Feature.ORE, new OreConfiguration(ORE_COBALT_TARGET_LIST, 9));

    public static final Holder<ConfiguredFeature<GeodeConfiguration, ?>> COBALT_GEODE =
            FeatureUtils.register("cobalt_geode", Feature.GEODE ,
                    new GeodeConfiguration(new GeodeBlockSettings(BlockStateProvider.simple(Blocks.AIR),
                            BlockStateProvider.simple(Blocks.DEEPSLATE),
                            BlockStateProvider.simple(ModBlocks.COBALT_ORE.get()),
                            BlockStateProvider.simple(Blocks.DIRT),
                            BlockStateProvider.simple(Blocks.EMERALD_BLOCK),
                            List.of(ModBlocks.COBALT_BLOCK.get().defaultBlockState()),
                            BlockTags.FEATURES_CANNOT_REPLACE , BlockTags.GEODE_INVALID_BLOCKS),
                            new GeodeLayerSettings(1.7D, 1.2D, 2.5D, 3.5D),
                            new GeodeCrackSettings(0.25D, 1.5D, 1), 0.5D, 0.1D,
                            true, UniformInt.of(3, 8),
                            UniformInt.of(2, 6), UniformInt.of(1, 2),
                            -18, 18, 0.075D, 1));
}
