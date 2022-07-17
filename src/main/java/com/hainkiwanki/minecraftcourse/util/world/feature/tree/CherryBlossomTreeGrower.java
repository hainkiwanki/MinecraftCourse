package com.hainkiwanki.minecraftcourse.util.world.feature.tree;

import com.hainkiwanki.minecraftcourse.util.world.feature.ModConfiguredFeature;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class CherryBlossomTreeGrower extends AbstractTreeGrower {
    @Nullable
    @Override
    protected ConfiguredFeature<?, ?> getConfiguredFeature(Random pRandom, boolean pLargeHive) {
        return ModConfiguredFeature.CHERRY_BLOSSOM_TREE;
    }
}
