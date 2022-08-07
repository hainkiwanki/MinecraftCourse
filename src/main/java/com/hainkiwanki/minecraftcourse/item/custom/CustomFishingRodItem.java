package com.hainkiwanki.minecraftcourse.item.custom;

import net.minecraft.advancements.critereon.FishingHookPredicate;
import net.minecraft.advancements.critereon.FishingRodHookedTrigger;
import net.minecraft.client.renderer.entity.FishingHookRenderer;
import net.minecraft.data.loot.FishingLoot;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.enchantment.FishingSpeedEnchantment;

public class CustomFishingRodItem extends FishingRodItem {
    public CustomFishingRodItem(Properties properties) {
        super(properties);
    }
}
