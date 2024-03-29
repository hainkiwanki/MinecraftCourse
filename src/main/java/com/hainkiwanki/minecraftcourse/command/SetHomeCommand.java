package com.hainkiwanki.minecraftcourse.command;

import com.hainkiwanki.minecraftcourse.MinecraftCourseMod;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class SetHomeCommand {
    public SetHomeCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("home").then(Commands.literal("set").executes((command) -> {
            return setHome(command.getSource());
        })));
    }

    private int setHome(CommandSourceStack source) throws CommandSyntaxException {
        ServerPlayer player = source.getPlayerOrException();
        BlockPos playerPos = player.blockPosition();
        String pos = "(" + playerPos.getX() + ", " + playerPos.getY() + ", " + playerPos.getZ() + ")";

        player.getPersistentData().putIntArray(MinecraftCourseMod.MOD_ID + "homepos",
                new int[]{playerPos.getX(), playerPos.getY(), playerPos.getZ()});

        source.sendSuccess(Component.literal("Set Home at " + pos), true);
        return 1;
    }
}