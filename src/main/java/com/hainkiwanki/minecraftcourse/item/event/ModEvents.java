package com.hainkiwanki.minecraftcourse.item.event;

import com.hainkiwanki.minecraftcourse.MinecraftCourseMod;
import com.hainkiwanki.minecraftcourse.command.ReturnHomeCommand;
import com.hainkiwanki.minecraftcourse.command.SetHomeCommand;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

@Mod.EventBusSubscriber(modid = MinecraftCourseMod.MOD_ID)
public class ModEvents {
    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {
        new SetHomeCommand(event.getDispatcher());
        new ReturnHomeCommand(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());
    }

    @SubscribeEvent
    public static void onPlayerCloneEvent(PlayerEvent.Clone event) {
        if(!event.getOriginal().level.isClientSide()) {
            event.getPlayer().getPersistentData().putIntArray(MinecraftCourseMod.MOD_ID + "homepos",
                    event.getOriginal().getPersistentData().getIntArray(MinecraftCourseMod.MOD_ID + "homepos"));
        }
    }
}
