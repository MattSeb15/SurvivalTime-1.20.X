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
import net.most.survivaltimemod.data.PlayerTime;

import java.util.Collection;

public class StopTimeCommand {
    public StopTimeCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("sut")
                .then(Commands.literal("time").then(Commands.literal("pause").then(
                        Commands.argument("player", EntityArgument.players()).requires(
                                (source) -> source.hasPermission(Commands.LEVEL_OWNERS)
                        ).executes(this::execute)
                )))
        );
    }

    private int execute(CommandContext<CommandSourceStack> context) {
        try {

            Collection<ServerPlayer> players = EntityArgument.getPlayers(context, "player");

            StringBuilder playerNames = new StringBuilder().append("[");
            for (ServerPlayer player : players) {


                boolean isTimeStopped = PlayerTime.getIsTimeStopped(player.getUUID());
                if (player == players.toArray()[players.size() - 1]) {

                    playerNames.append(player.getName().getString()).append(
                            !isTimeStopped ? " (stopped)" : " (x)"
                    ).append("]");
                } else {
                    playerNames.append(player.getName().getString()).append(
                            !isTimeStopped ? " (stopped)" : " (x)"
                    ).append(", ");
                }

                if(!isTimeStopped){
                    player.displayClientMessage(
                            Component.literal("Time stopped").withStyle(ChatFormatting.GREEN),
                            false
                    );
                }
                PlayerTime.stopTimeStatus(player.getUUID());


            }
            context.getSource().sendSuccess(
                    () -> Component.literal("Time stopped for ").append(
                            playerNames.toString()
                    ).withStyle(ChatFormatting.GREEN),
                    true
            );

        } catch (CommandSyntaxException e) {
            context.getSource().sendFailure(Component.literal(e.getMessage()));
        }

        return 1;
    }
}
