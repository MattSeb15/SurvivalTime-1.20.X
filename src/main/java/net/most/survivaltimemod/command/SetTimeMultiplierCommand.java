package net.most.survivaltimemod.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.most.survivaltimemod.data.FormatTimeType;
import net.most.survivaltimemod.time.PlayerTimeProvider;


import java.util.Collection;

public class SetTimeMultiplierCommand {

    public SetTimeMultiplierCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("sut")
                .requires(
                        (source) -> source.hasPermission(Commands.LEVEL_OWNERS)

                )
                .then(Commands.literal("multiplier").then(Commands.literal("set").then(
                        Commands.argument("player", EntityArgument.players()).then(
                                Commands.argument("amount",
                                        FloatArgumentType.floatArg(-5.0f, 5.0f)).executes(this::execute)
                        )
                )))
        );
    }

    private int execute(CommandContext<CommandSourceStack> context) {

        try {

            Collection<ServerPlayer> players = EntityArgument.getPlayers(context, "player");
            float multiplier = FloatArgumentType.getFloat(context, "amount");
            StringBuilder playerNames = new StringBuilder().append("[");
            for (ServerPlayer player : players) {
                player.getCapability(PlayerTimeProvider.PLAYER_TIME_CAPABILITY).ifPresent(playerTime -> {
                    playerTime.setTimeMultiplier(multiplier, player);
                    if (player == players.toArray()[players.size() - 1]) {
                        playerNames.append(player.getName().getString()).append("]");
                    } else {
                        playerNames.append(player.getName().getString()).append(", ");
                    }

                    player.displayClientMessage(
                            Component.literal("Your time multiplier has been set to " + multiplier).withStyle(ChatFormatting.AQUA),
                            false
                    );
                });

            }
            context.getSource().sendSuccess(
                    () -> Component.literal("Set ").append(
                            "x(" + multiplier + ") multiplier"
                    ).append(
                            "to "
                    ).append(playerNames.toString()).withStyle(ChatFormatting.GREEN),
                    false
            );

        } catch (CommandSyntaxException e) {
            context.getSource().sendFailure(Component.literal(e.getMessage()));
        }

        return 1;
    }
}
