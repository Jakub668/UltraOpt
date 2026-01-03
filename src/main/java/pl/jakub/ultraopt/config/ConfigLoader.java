package pl.jakub.ultraopt.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigLoader {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final String FILE_NAME = "ultraopt.json";

    private static Path getConfigPath() {
        return FabricLoader.getInstance()
                .getConfigDir()
                .resolve(FILE_NAME);
    }

    public static UltraOptConfig load(Path configDir) {
        Path path = getConfigPath();

        if (Files.exists(path)) {
            try {
                return GSON.fromJson(Files.readString(path), UltraOptConfig.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        UltraOptConfig config = new UltraOptConfig();
        save(config);
        return config;
    }

    // ⭐ TĘ METODĘ UŻYWASZ WSZĘDZIE
    public static void save(UltraOptConfig config) {
        Path path = getConfigPath();
        try {
            Files.writeString(path, GSON.toJson(config));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
