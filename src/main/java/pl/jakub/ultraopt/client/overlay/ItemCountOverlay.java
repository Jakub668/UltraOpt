package pl.jakub.ultraopt.client.overlay;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.font.TextRenderer.TextLayerType;
import net.minecraft.client.render.BufferBuilderStorage;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

public class ItemCountOverlay {

    private static final MinecraftClient mc = MinecraftClient.getInstance();

    /**
     * Rysuje ilość itemów jako overlay (MC 1.21.4)
     */
    public static void render(MatrixStack matrices, int x, int y, int count) {
        if (mc.player == null || mc.textRenderer == null) return;
        if (count <= 1) return;

        Text text = Text.literal(String.valueOf(count));

        // ❗ W 1.21.4 MUSI być Immediate
        BufferBuilderStorage buffers = mc.getBufferBuilders();
        VertexConsumerProvider.Immediate vertexConsumers = buffers.getEntityVertexConsumers();

        matrices.push();

        mc.textRenderer.draw(
                text,                                   // Text
                x,
                y,
                0xFFFFFF,                               // kolor
                true,                                   // cień
                matrices.peek().getPositionMatrix(),
                vertexConsumers,
                TextLayerType.NORMAL,                   // OBOWIĄZKOWE
                0,
                15728880
        );

        // ❗ BEZ TEGO JEST CRASH
        vertexConsumers.draw();

        matrices.pop();
    }
}
