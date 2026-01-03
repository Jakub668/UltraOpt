package pl.jakub.ultraopt.client.overlay;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer.TextLayerType;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

public class ItemCountOverlay {

    private static final MinecraftClient mc = MinecraftClient.getInstance();

    /**
     * Rysuje ilość itemów jako overlay
     */
    public static void render(
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            int x,
            int y,
            int count
    ) {
        if (mc.player == null || count <= 1) {
            return;
        }

        Text text = Text.literal(String.valueOf(count));

        mc.textRenderer.draw(
                text,                                   // Text (NIE String)
                x,
                y,
                0xFFFFFF,                               // kolor
                true,                                   // cień
                matrices.peek().getPositionMatrix(),   // Matrix4f
                vertexConsumers,
                TextLayerType.NORMAL,                   // WYMAGANE w 1.21+
                0,
                15728880
        );
    }
}
