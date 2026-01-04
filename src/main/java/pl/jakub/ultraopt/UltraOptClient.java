package pl.jakub.ultraopt;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import pl.jakub.ultraopt.client.overlay.ItemCountOverlay;

public class UltraOptClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register(new ItemCountOverlay());
    }
}
