package net.most.survivaltimemod.command.coin;

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
import net.most.survivaltimemod.time.PlayerTimeProvider;
import net.most.survivaltimemod.util.CoinFormatter;

import java.util.Collection;

public class AddCoinCommand {
    public AddCoinCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("sut")
                .requires(
                        (source) -> source.hasPermission(Commands.LEVEL_OWNERS)

                )
                .then(Commands.literal("coin").then(Commands.literal("add").then(
                        Commands.argument("player", EntityArgument.players()).then(
                                Commands.argument("coins",
                                        IntegerArgumentType.integer(0, 10*1000000)).executes(this::execute)
                        )
                )))
        );
    }

    private int execute(CommandContext<CommandSourceStack> context) {

        try {

            Collection<ServerPlayer> players = EntityArgument.getPlayers(context, "player");
            int coinsToAdd = context.getArgument("coins", Integer.class);
            String formattedCoins = CoinFormatter.formatCoins(coinsToAdd);
            StringBuilder playerNames = new StringBuilder().append("[");
            for (ServerPlayer player : players) {
                player.getCapability(PlayerTimeProvider.PLAYER_TIME_CAPABILITY).ifPresent(playerTime -> {
                    playerTime.incrementCoins(coinsToAdd, player);
                    if (player == players.toArray()[players.size() - 1]) {
                        playerNames.append(player.getName().getString()).append("]");
                    } else {
                        playerNames.append(player.getName().getString()).append(", ");
                    }


                    player.displayClientMessage(
                            Component.translatable("chat.notification.sut.coin.add", formattedCoins).withStyle(ChatFormatting.AQUA),
                            false
                    );
                });

            }


            context.getSource().sendSuccess(
                    () -> Component.translatable("chat.notification.general_command.set_players", formattedCoins, "Coins" , playerNames.toString()).withStyle(ChatFormatting.GREEN),
                    true
            );

        } catch (CommandSyntaxException e) {
            context.getSource().sendFailure(Component.literal(e.getMessage()));
        }

        return 1;
    }
}
