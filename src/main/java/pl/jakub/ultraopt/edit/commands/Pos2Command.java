package pl.jakub.ultraopt.edit.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import pl.jakub.ultraopt.edit.UltraEdit;

public class Pos2Command {

    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(
                ClientCommandManager.literal("pos2")
                        .executes(ctx -> {
                            MinecraftClient client = MinecraftClient.getInstance();
                            if (client.player != null) {
                                UltraEdit.SELECTION.setPos2(client.player.getBlockPos());
                                ctx.getSource().sendFeedback(
                                        Text.literal("UltraEdit: Pos2 set")
                                );
                            }
                            return 1;
                        })
        );
    }
}