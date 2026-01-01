package pl.jakub.ultraopt.client.overlay;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.text.Text;

public class ItemCountOverlay {

    public static void render(
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            int light
    ) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;

        TextRenderer textRenderer = client.textRenderer;

        int x = 10;
        int y = 10;
        int count = 5;

        // ✅ TUTAJ I TYLKO TUTAJ
        textRenderer.draw(
                Text.literal("x" + count),
                x,
                y,
                0xFFFFFF,
                false,
                matrices.peek().getPositionMatrix(),
                vertexConsumers,
                TextRenderer.TextLayerType.NORMAL,
                0,
                light
        );
    }
}
