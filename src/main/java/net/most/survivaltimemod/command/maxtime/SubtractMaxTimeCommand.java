package net.most.survivaltimemod.command.maxtime;

import com.mojang.brigadier.CommandDispatcher;
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

public class SubtractMaxTimeCommand {
    public SubtractMaxTimeCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("sut")
                .requires(
                        (source) -> source.hasPermission(Commands.LEVEL_OWNERS)

                )
                .then(Commands.literal("maxtime").then(Commands.literal("subtract").then(
                        Commands.argument("player", EntityArgument.players()).then(
                                Commands.argument("time",
                                        IntegerArgumentType.integer(0, 50 * 3600)).executes(this::execute)
                        )
                )))
        );
    }

    private int execute(CommandContext<CommandSourceStack> context) {

        try {

            Collection<ServerPlayer> players = EntityArgument.getPlayers(context, "player");
            int timeToSet = context.getArgument("time", Integer.class);
            String formattedTime = FormatTimeType.getFormattedStringByType(FormatTimeType.DEPENDS_NAMED, timeToSet);
            StringBuilder playerNames = new StringBuilder().append("[");
            for (ServerPlayer player : players) {
                player.getCapability(PlayerTimeProvider.PLAYER_TIME_CAPABILITY).ifPresent(playerTime -> {
                    playerTime.decrementMaxTime(timeToSet, player);
                    if (player == players.toArray()[players.size() - 1]) {
                        playerNames.append(player.getName().getString()).append("]");
                    } else {
                        playerNames.append(player.getName().getString()).append(", ");
                    }


                    player.displayClientMessage(
                            Component.translatable("chat.notification.sut.maxtime.subtract", formattedTime).withStyle(ChatFormatting.AQUA),
                            false
                    );
                });

            }
            context.getSource().sendSuccess(
                    () -> Component.translatable("chat.notification.general_command.sub_players", formattedTime, "Max Time", playerNames.toString()).withStyle(ChatFormatting.GREEN),
                    true
            );

        } catch (CommandSyntaxException e) {
            context.getSource().sendFailure(Component.literal(e.getMessage()));
        }

        return 1;
    }
}
