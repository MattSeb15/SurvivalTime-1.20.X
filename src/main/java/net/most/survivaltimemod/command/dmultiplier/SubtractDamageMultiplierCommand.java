package net.most.survivaltimemod.command.dmultiplier;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.FloatArgumentType;
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

public class SubtractDamageMultiplierCommand {

    public SubtractDamageMultiplierCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("sut")
                .requires(
                        (source) -> source.hasPermission(Commands.LEVEL_OWNERS)

                )
                .then(Commands.literal("multipliers").then(Commands.literal("damage").then(Commands.literal("subtract").then(
                        Commands.argument("player", EntityArgument.players()).then(
                                Commands.argument("amount",
                                        FloatArgumentType.floatArg(-1000.0f, 1000.0f)).executes(this::execute)
                        )
                ))))
        );
    }

    private int execute(CommandContext<CommandSourceStack> context) {

        try {

            Collection<ServerPlayer> players = EntityArgument.getPlayers(context, "player");
            float multiplier = FloatArgumentType.getFloat(context, "amount");
            StringBuilder playerNames = new StringBuilder().append("[");
            for (ServerPlayer player : players) {
                player.getCapability(PlayerTimeProvider.PLAYER_TIME_CAPABILITY).ifPresent(playerTime -> {
                    playerTime.decrementDamageMultiplier(multiplier, player);
                    if (player == players.toArray()[players.size() - 1]) {
                        playerNames.append(player.getName().getString()).append("]");
                    } else {
                        playerNames.append(player.getName().getString()).append(", ");
                    }

                    player.displayClientMessage(
                            Component.translatable("chat.notification.sut.damage_multiplier.subtract", multiplier).withStyle(ChatFormatting.AQUA),
                            false
                    );
                });

            }
            context.getSource().sendSuccess(
                    () -> Component.translatable("chat.notification.general_command.sub_players", "x"+multiplier, "Damage Multiplier", playerNames.toString()).withStyle(ChatFormatting.GREEN),
                    false
            );

        } catch (CommandSyntaxException e) {
            context.getSource().sendFailure(Component.literal(e.getMessage()));
        }

        return 1;
    }
}
