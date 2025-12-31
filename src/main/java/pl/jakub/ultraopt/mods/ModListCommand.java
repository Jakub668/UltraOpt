package pl.jakub.ultraopt.mods;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.text.Text;

public class ModListCommand {

    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(
                ClientCommandManager.literal("modlist")
                        .executes(ctx -> {
                            FabricLoader.getInstance().getAllMods().forEach(mod ->
                                    ctx.getSource().sendFeedback(
                                            Text.literal(mod.getMetadata().getName())
                                    )
                            );
                            return 1;
                        })
        );
    }
}
