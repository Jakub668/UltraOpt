// ConfigLoader.java
// Zapis i odczyt ultraopt.json

package pl.jakub.ultraopt.config;

import com.google.gson.*;
import net.fabricmc.loader.api.FabricLoader;

import java.io.*;

public class ConfigLoader {

    private static final Gson GSON =
            new GsonBuilder().setPrettyPrinting().create();

    private static final String FILE = "ultraopt.json";

    public static UltraOptConfig load() {
        try {
            File file = new File(
                    FabricLoader.getInstance().getConfigDir().toFile(),
                    FILE
            );
            if (!file.exists()) return new UltraOptConfig();
            return GSON.fromJson(new FileReader(file), UltraOptConfig.class);
        } catch (Exception e) {
            return new UltraOptConfig();
        }
    }

    public static void save(UltraOptConfig cfg) {
        try {
            File file = new File(
                    FabricLoader.getInstance().getConfigDir().toFile(),
                    FILE
            );
            FileWriter w = new FileWriter(file);
            GSON.toJson(cfg, w);
            w.close();
        } catch (Exception ignored) {}
    }
}