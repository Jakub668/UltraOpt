package pl.jakub.ultraopt.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import pl.jakub.ultraopt.client.overlay.ItemCountOverlay;

public class UltraOptClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        WorldRenderEvents.AFTER_ENTITIES.register(context -> {
            ItemCountOverlay.renderWorld(
                    context.matrixStack(),
                    context.camera()
            );
        });
    }
}
