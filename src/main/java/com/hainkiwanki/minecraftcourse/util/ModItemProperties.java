package com.hainkiwanki.minecraftcourse.util;

import com.hainkiwanki.minecraftcourse.MinecraftCourseMod;
import com.hainkiwanki.minecraftcourse.item.ModItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;

public class ModItemProperties {
    public static void addCustomItemPorperties() {
        ItemProperties.register(ModItems.DATA_TABLET.get(), new ResourceLocation(MinecraftCourseMod.MOD_ID, "on"),
                ((pStack, pLevel, pEntity, pSeed) -> pStack.hasTag() ? 1f : 0f));
    }
}
