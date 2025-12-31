package pl.jakub.ultraopt;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import pl.jakub.ultraopt.mods.ModListCommand;
import pl.jakub.ultraopt.settings.UltraOptSettingsCommand;
import pl.jakub.ultraopt.edit.commands.Pos1Command;
import pl.jakub.ultraopt.edit.commands.Pos2Command;

public class UltraOpt implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {

            // /ultraopt (ON / OFF)
            UltraOptSettingsCommand.register(dispatcher);

            // /modlist
            ModListCommand.register(dispatcher);

            // UltraEdit basic
            Pos1Command.register(dispatcher);
            Pos2Command.register(dispatcher);
        });
    }
}
