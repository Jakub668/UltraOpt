package pl.jakub.ultraopt.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigLoader {

    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    private static final String FILE_NAME = "ultraopt.json";

    public static UltraOptConfig load(Path configDir) {
        try {
            Path file = configDir.resolve(FILE_NAME);

            if (Files.notExists(file)) {
                UltraOptConfig config = new UltraOptConfig();
                save(configDir, config);
                return config;
            }

            String json = Files.readString(file);
            return GSON.fromJson(json, UltraOptConfig.class);

        } catch (Exception e) {
            e.printStackTrace();
            return new UltraOptConfig();
        }
    }

    public static void save(Path configDir, UltraOptConfig config) {
        try {
            Files.createDirectories(configDir);
            Path file = configDir.resolve(FILE_NAME);
            Files.writeString(file, GSON.toJson(config));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
