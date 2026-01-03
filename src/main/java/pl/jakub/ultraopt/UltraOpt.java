package pl.jakub.ultraopt;

import net.fabricmc.api.ClientModInitializer;
import pl.jakub.ultraopt.config.UltraOptConfig;
import pl.jakub.ultraopt.config.ConfigLoader;

public class UltraOpt implements ClientModInitializer {

    public static UltraOptConfig CONFIG;

    @Override
    public void onInitializeClient() {
        CONFIG = ConfigLoader.load();
    }
}
