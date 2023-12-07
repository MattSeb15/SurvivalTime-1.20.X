package net.most.survivaltimemod.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.most.survivaltimemod.time.PlayerTimeProvider;


import java.util.Collection;

public class ToggleTimeStatusCommand {
    public ToggleTimeStatusCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("sut")
                .requires(
                        (source) -> source.hasPermission(Commands.LEVEL_OWNERS)
                )
                .then(Commands.literal("time").then(Commands.literal("toggle").then(
                        Commands.argument("player", EntityArgument.players()).executes(this::execute)
                )))
        );
    }

    private int execute(CommandContext<CommandSourceStack> context) {
        try {

            Collection<ServerPlayer> players = EntityArgument.getPlayers(context, "player");

            StringBuilder playerNames = new StringBuilder().append("[");
            for (ServerPlayer player : players) {
                player.getCapability(PlayerTimeProvider.PLAYER_TIME_CAPABILITY).ifPresent(playerTime -> {
                    boolean isTimeStopped = playerTime.isTimeStopped();
                    if (player == players.toArray()[players.size() - 1]) {

                        playerNames.append(player.getName().getString()).append(
                                isTimeStopped ? " (resumed)" : " (paused)"
                        ).append("]");
                    } else {
                        playerNames.append(player.getName().getString()).append(
                                isTimeStopped ? " (resumed)" : " (paused)"
                        ).append(", ");
                    }

                    player.displayClientMessage(
                            Component.literal(isTimeStopped
                                    ? "Your time has been resumed" : "Your time has been paused").withStyle(ChatFormatting.GREEN),
                            false
                    );
                    playerTime.toggleTimeStatus(player);
                });


            }
            context.getSource().sendSuccess(
                    () -> Component.literal("Time toggled for ").append(
                            playerNames.toString()
                    ).withStyle(ChatFormatting.GREEN),
                    false
            );

        } catch (CommandSyntaxException e) {
            context.getSource().sendFailure(Component.literal(e.getMessage()));
        }

        return 1;
    }
}
