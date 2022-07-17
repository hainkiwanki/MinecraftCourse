package com.hainkiwanki.minecraftcourse.item.custom;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MonsterJarItem extends Item {
    public MonsterJarItem(Properties pProperties) { super(pProperties); }

    @Override
    public InteractionResult interactLivingEntity(ItemStack pStack, Player pPlayer, LivingEntity pInteractionTarget, InteractionHand pUsedHand) {
        if (!pPlayer.level.isClientSide() && pUsedHand == InteractionHand.MAIN_HAND) {
            pPlayer.sendMessage(new TextComponent(pInteractionTarget.getType().getRegistryName().toString()), pPlayer.getUUID());
            CompoundTag nbtData = new CompoundTag();
            nbtData.putString("minecraftcourse_clicked_mob", pInteractionTarget.getType().getRegistryName().toString());
            pPlayer.getItemInHand(InteractionHand.MAIN_HAND).setTag(nbtData);
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(pPlayer.getItemInHand(pUsedHand).hasTag() && Screen.hasShiftDown()) {
            pPlayer.getItemInHand(pUsedHand).setTag(new CompoundTag());
            pPlayer.sendMessage(new TextComponent("removed"), pPlayer.getUUID());
        }

        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if(pStack.hasTag()) {
            String clickedMob = pStack.getTag().getString("minecraftcourse_clicked_mob");
            pTooltipComponents.add(new TextComponent(clickedMob));
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    @Override
    public boolean isFoil(ItemStack pStack) {
        return pStack.hasTag();
    }
}
