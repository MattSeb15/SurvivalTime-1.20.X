package net.most.survivaltimemod.command.item;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.most.survivaltimemod.data.FormatTimeType;
import net.most.survivaltimemod.item.ModItems;
import net.most.survivaltimemod.item.custom.LostTimeSphereItem;

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
            context.getSource().sendFailure(Component.translatable("chat.notification.sut.item.lts.fail"));
            return 0;
        }

        executor.getMainHandItem().getOrCreateTag().putInt(LostTimeSphereItem.TIME_VALUE_TAG, timeToPutOnItemTag);


        context.getSource().sendSuccess(
                () -> Component.translatable("chat.notification.sut.item.lts.success", formattedTime),
                true
        );

        return 1;
    }
}
