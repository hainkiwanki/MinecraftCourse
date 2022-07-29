package com.hainkiwanki.minecraftcourse.item.custom;

import com.hainkiwanki.minecraftcourse.item.ModItems;
import com.hainkiwanki.minecraftcourse.sound.ModSounds;
import com.hainkiwanki.minecraftcourse.util.InventoryUtil;
import com.hainkiwanki.minecraftcourse.util.ModTags;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DowsingRodItem extends Item {

    public DowsingRodItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if(pContext.getLevel().isClientSide()) {
            BlockPos positionClicked = pContext.getClickedPos();
            Player player = pContext.getPlayer();
            boolean foundBlock = false;

            for(int i = 0; i <= positionClicked.getY() + 64; i++) {
                BlockState blockBelow = pContext.getLevel().getBlockState(positionClicked.below(i));

                if(isValuableBlock(blockBelow)) {
                    outputValuableCoordinates(positionClicked.below(i), player, blockBelow.getBlock());
                    foundBlock = true;

                    if(InventoryUtil.hasPlayerStackInInventory(player, ModItems.DATA_TABLET.get())) {
                        addNbtToDataTablet(player, positionClicked.below(i), blockBelow.getBlock());
                    }

                    // player.playSound(ModSounds.DOWSING_ROD_FOUND_ORE.get(), 1f, 1f);
                    pContext.getLevel().playSound(player, positionClicked, ModSounds.DOWSING_ROD_FOUND_ORE.get(), SoundSource.BLOCKS, 1f, 1f);

                    break;
                }
            }

            if(!foundBlock) {
                player.sendSystemMessage(Component.translatable("item.minecraftcourse.dowsing_rod.no_valuables"));
            }
        }

        pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(),
                (player) -> player.broadcastBreakEvent(player.getUsedItemHand()));

        return super.useOn(pContext);
    }

    private void addNbtToDataTablet(Player player, BlockPos pos, Block blockBelow) {
        ItemStack dataTablet = player.getInventory().getItem(InventoryUtil.getFirstInventoryIndex(player, ModItems.DATA_TABLET.get()));
        CompoundTag nbtData = new CompoundTag();
        nbtData.putString("minecraftcourse.last_ore", "Found " + blockBelow.getName() + " at (" +
                pos.getX() + ", " + pos.getY() + ", " + pos.getZ() + ")");
        dataTablet.setTag(nbtData);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if(Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("tooltip.minecraftcourse.dowsing_rod.tooltip.shift"));
        } else {
            pTooltipComponents.add(Component.translatable("tooltip.minecraftcourse.dowsing_rod.tooltip"));
        }
    }

    private void outputValuableCoordinates(BlockPos blockPos, Player player, Block blockBelow) {
        player.sendSystemMessage(Component.literal("Found: " + blockBelow.getName() + " at (" +
                blockPos.getX() + ", " + blockPos.getY() + ", " + blockPos.getZ() + ")"));
    }

    private boolean isValuableBlock(BlockState blockstate) {
        return blockstate.is(ModTags.Blocks.DOWSING_ROD_VALUABLES);
        //return block == Blocks.COAL_BLOCK || block == Blocks.COPPER_ORE || block == Blocks.GOLD_ORE || block == Blocks.DIAMOND_ORE || block == Blocks.IRON_ORE;
    }
}
