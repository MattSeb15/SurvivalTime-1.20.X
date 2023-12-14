package net.most.survivaltimemod.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.item.ItemArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.most.survivaltimemod.data.FormatTimeType;
import net.most.survivaltimemod.item.ModItems;
import net.most.survivaltimemod.item.custom.LostTimeSphereData;
import net.most.survivaltimemod.time.PlayerTimeProvider;

import java.util.Collection;

public class AddNbtToItem {
    public AddNbtToItem(CommandDispatcher<CommandSourceStack> pDispatcher, CommandBuildContext pContext) {
        pDispatcher.register(Commands.literal("sut").requires(
                                (source) -> source.hasPermission(Commands.LEVEL_OWNERS)
                        )
                        .then(Commands.literal("item").then(Commands.literal("lts").then(
                                Commands.argument("time",
                                        IntegerArgumentType.integer(0, 10 * 3600)).executes(this::execute)
                        )))
        );
    }

    private int execute(CommandContext<CommandSourceStack> context) {


        ServerPlayer executor = context.getSource().getPlayer();
        int timeToPutOnItemTag = context.getArgument("time", Integer.class);
        String formattedTime = FormatTimeType.getFormattedStringByType(FormatTimeType.DEPENDS_NAMED,
                timeToPutOnItemTag);

        assert executor != null;
        if (executor.getMainHandItem().isEmpty() || executor.getMainHandItem().getItem() != ModItems.LOST_TIME_SPHERE.get()) {
            context.getSource().sendFailure(Component.literal("You must have lost time sphere item in your main hand!"));
            return 0;
        }

        executor.getMainHandItem().getOrCreateTag().putInt(LostTimeSphereData.TIME_VALUE_TAG, timeToPutOnItemTag);


        context.getSource().sendSuccess(
                () -> Component.literal("You have put " + formattedTime + " on the item!"),
                true
        );

        return 1;
    }
}
