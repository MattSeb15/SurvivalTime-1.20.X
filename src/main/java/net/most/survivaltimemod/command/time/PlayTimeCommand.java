package net.most.survivaltimemod.command.time;

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

public class PlayTimeCommand {

    public PlayTimeCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("sut").requires(
                                (source) -> source.hasPermission(Commands.LEVEL_OWNERS)
                        )
                        .then(Commands.literal("time").then(Commands.literal("play").then(
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
                                isTimeStopped ? " (resumed)" : " (x)"
                        ).append("]");
                    } else {
                        playerNames.append(player.getName().getString()).append(
                                isTimeStopped ? " (resumed)" : " (x)"
                        ).append(", ");
                    }

                    if (isTimeStopped) {
                        player.displayClientMessage(
                                Component.translatable("chat.notification.sut.time.play").withStyle(ChatFormatting.AQUA),
                                false
                        );
                    }
                    playerTime.startTimeStatus(player);
                });


            }
            context.getSource().sendSuccess(
                    () -> Component.translatable("chat.notification.sut.time.play_players", playerNames.toString()).withStyle(ChatFormatting.GREEN),
                    true
            );

        } catch (CommandSyntaxException e) {
            context.getSource().sendFailure(Component.literal(e.getMessage()));
        }

        return 1;
    }


}
