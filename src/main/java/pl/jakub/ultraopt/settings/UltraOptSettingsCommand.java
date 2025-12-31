// UltraOptSettingsCommand.java
// /ultraopt on|off|items|counter|physics|model

package pl.jakub.ultraopt.settings;

import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.text.Text;
import pl.jakub.ultraopt.UltraOpt;
import pl.jakub.ultraopt.config.ConfigLoader;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.*;

public class UltraOptSettingsCommand {

    public static void register() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, reg) ->
            dispatcher.register(
                literal("ultraopt")
                    .then(argument("option", StringArgumentType.word())
                        .executes(ctx -> {
                            String opt = StringArgumentType.getString(ctx, "option");

                            switch (opt) {
                                case "on" -> UltraOpt.CONFIG.enabled = true;
                                case "off" -> UltraOpt.CONFIG.enabled = false;
                                case "items" -> UltraOpt.CONFIG.itemPhysics ^= true;
                                case "counter" -> UltraOpt.CONFIG.itemCounter ^= true;
                                case "model" -> UltraOpt.CONFIG.itemSingleModel ^= true;
                                default -> {
                                    ctx.getSource().sendFeedback(
                                        Text.literal("§cOpcje: on, off, items, counter, model")
                                    );
                                    return 0;
                                }
                            }

                            ConfigLoader.save(UltraOpt.CONFIG);
                            ctx.getSource().sendFeedback(
                                Text.literal("§aUltraOpt zaktualizowany")
                            );
                            return 1;
                        })
                    )
            )
        );
    }
}