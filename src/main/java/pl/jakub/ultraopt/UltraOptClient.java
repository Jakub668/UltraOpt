package pl.jakub.ultraopt;

import net.fabricmc.api.ClientModInitializer;
import pl.jakub.ultraopt.client.render.ItemCountWorldRenderer;

public class UltraOptClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ItemCountWorldRenderer.register();
    }
}
