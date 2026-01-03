package pl.jakub.ultraopt;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import pl.jakub.ultraopt.config.ConfigLoader;
import pl.jakub.ultraopt.config.UltraOptConfig;

public class UltraOpt implements ClientModInitializer {

    public static UltraOptConfig CONFIG;

    @Override
    public void onInitializeClient() {
        // ⬇️ TU JEST TEN INIT
        CONFIG = ConfigLoader.load(
                FabricLoader.getInstance().getConfigDir()
        );

        System.out.println("[UltraOpt] Config loaded");
    }
}
