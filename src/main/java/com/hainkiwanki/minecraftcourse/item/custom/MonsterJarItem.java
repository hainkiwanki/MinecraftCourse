package com.hainkiwanki.minecraftcourse.item.custom;

import com.hainkiwanki.minecraftcourse.item.ModItems;
import com.hainkiwanki.minecraftcourse.util.InventoryUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.checkerframework.checker.units.qual.C;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MonsterJarItem extends Item {
    public MonsterJarItem(Properties pProperties) { super(pProperties); }

    @Override
    public InteractionResult interactLivingEntity(ItemStack pStack, Player pPlayer, LivingEntity pInteractionTarget, InteractionHand pUsedHand) {
        //if (pInteractionTarget.level.isClientSide()) return InteractionResult.PASS;
        if (!pPlayer.level.isClientSide() && pUsedHand == InteractionHand.MAIN_HAND) {
            if (pInteractionTarget instanceof Mob) {
                Mob target = (Mob) pInteractionTarget;
                pPlayer.sendMessage(new TextComponent("item.minecraftcourse.monster_jar.clicked " + target.getClass().getName()), pPlayer.getUUID());
                CompoundTag nbtData = new CompoundTag();
                nbtData.putString("minecraftcourse.clicked_mob", "Mob clicked: " + target.getClass().getName());
                ItemStack i = pPlayer.getInventory().getItem(InventoryUtil.getFirstInventoryIndex(pPlayer, ModItems.MONSTER_JAR.get()));
                if(i == pStack) {
                    pPlayer.sendMessage(new TextComponent("Same object"), pPlayer.getUUID());
                } else {
                    pPlayer.sendMessage(new TextComponent("Not same object"), pPlayer.getUUID());
                }
                pStack.setTag(nbtData);
                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.FAIL;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(pPlayer.getItemInHand(pUsedHand).hasTag()) {
            pPlayer.getItemInHand(pUsedHand).setTag(new CompoundTag());
        }

        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if(pStack.hasTag()) {
            String clickedMob = pStack.getTag().getString("minecraftcourse.clicked_mob");
            pTooltipComponents.add(new TextComponent(clickedMob));
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    @Override
    public boolean isFoil(ItemStack pStack) {
        return pStack.hasTag();
    }
}
