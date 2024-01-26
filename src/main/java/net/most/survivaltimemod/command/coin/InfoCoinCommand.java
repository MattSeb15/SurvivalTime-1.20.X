package net.most.survivaltimemod.command.coin;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.most.survivaltimemod.time.PlayerTimeProvider;
import net.most.survivaltimemod.util.CoinFormatter;

import java.util.Collection;

public class InfoCoinCommand {
    public InfoCoinCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("sut")
                .requires(
                        (source) -> source.hasPermission(Commands.LEVEL_OWNERS)

                )
                .then(Commands.literal("coin").then(Commands.literal("info").then(
                        Commands.argument("player", EntityArgument.players()).executes(this::execute)
                )))
        );
    }

    private int execute(CommandContext<CommandSourceStack> context) {

        try {

            Collection<ServerPlayer> players = EntityArgument.getPlayers(context, "player");

            for (ServerPlayer player : players) {
                player.getCapability(PlayerTimeProvider.PLAYER_TIME_CAPABILITY).ifPresent(playerTime -> {
                    String currentFormattedCoins = CoinFormatter.simpleFormatCoins(playerTime.getCoins());
                    String playerName = player.getGameProfile().getName();
                    int playerIndex = players.stream().toList().indexOf(player) + 1;

                    context.getSource().sendSuccess(() -> Component.translatable("commands.sut.coin.info", playerIndex, playerName,
                            currentFormattedCoins), false);
                });

            }

        } catch (CommandSyntaxException e) {
            context.getSource().sendFailure(Component.literal(e.getMessage()));
        }

        return 1;
    }
}
