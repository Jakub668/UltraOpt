package pl.jakub.ultraopt.client.overlay;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.ItemEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;

public class ItemCountOverlay {

    public static void render(MatrixStack matrices, Camera camera) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.world == null || mc.player == null) return;

        // ✅ POPRAWKA BŁĘDU 1 — Camera NIE MA getBoundingBox() w 1.21.4
        Vec3d camPos = camera.getPos();
        Box searchBox = new Box(
                camPos.x - 16, camPos.y - 16, camPos.z - 16,
                camPos.x + 16, camPos.y + 16, camPos.z + 16
        );

        for (ItemEntity item : mc.world.getEntitiesByClass(
                ItemEntity.class,
                searchBox,
                e -> e.getStack().getCount() > 1
        )) {
            renderItemCount(matrices, camera, item);
        }
    }

    private static void renderItemCount(MatrixStack matrices, Camera camera, ItemEntity item) {
        MinecraftClient mc = MinecraftClient.getInstance();
        TextRenderer textRenderer = mc.textRenderer;

        matrices.push();

        matrices.translate(
                item.getX() - camera.getPos().x,
                item.getY() - camera.getPos().y + 0.4,
                item.getZ() - camera.getPos().z
        );

        matrices.scale(-0.025f, -0.025f, 0.025f);

        Matrix4f matrix = matrices.peek().getPositionMatrix();
        VertexConsumerProvider.Immediate consumers =
                mc.getBufferBuilders().getEntityVertexConsumers();

        String text = String.valueOf(item.getStack().getCount());
        float x = -textRenderer.getWidth(text) / 2f;

        textRenderer.draw(
                text,
                x,
                0,
                0xFFFFFF,
                false,
                matrix,
                consumers,
                TextRenderer.TextLayerType.NORMAL,
                0,
                15728880
        );

        consumers.draw();
        matrices.pop();
    }
}
