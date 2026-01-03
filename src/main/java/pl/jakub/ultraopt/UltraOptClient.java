package pl.jakub.ultraopt;

import net.fabricmc.api.ClientModInitializer;

public class UltraOptClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        System.out.println("[UltraOpt] Client initialized");
    }
}
