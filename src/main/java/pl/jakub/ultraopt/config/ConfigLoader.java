package pl.jakub.ultraopt.config;

public class ConfigLoader {

    public static UltraOptConfig load() {
        return new UltraOptConfig();
    }

    public static void save(UltraOptConfig config) {
        // na razie pusto – wystarczy do buildu
    }
}
