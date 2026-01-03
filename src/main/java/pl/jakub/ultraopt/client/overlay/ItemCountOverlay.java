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

    public static void renderWorld(MatrixStack matrices, Camera camera) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.world == null || mc.player == null) return;

        Vec3d camPos = camera.getPos();
        Box box = new Box(
                camPos.x - 16, camPos.y - 16, camPos.z - 16,
                camPos.x + 16, camPos.y + 16, camPos.z + 16
        );

        for (ItemEntity entity : mc.world.getEntitiesByClass(
                ItemEntity.class,
                box,
                e -> e.getStack().getCount() > 1
        )) {
            renderCount(matrices, camera, entity);
        }
    }

    private static void renderCount(MatrixStack matrices, Camera camera, ItemEntity entity) {
        MinecraftClient mc = MinecraftClient.getInstance();
        TextRenderer tr = mc.textRenderer;

        matrices.push();
        matrices.translate(
                entity.getX() - camera.getPos().x,
                entity.getY() - camera.getPos().y + 0.4,
                entity.getZ() - camera.getPos().z
        );
        matrices.scale(-0.025f, -0.025f, 0.025f);

        Matrix4f matrix = matrices.peek().getPositionMatrix();
        VertexConsumerProvider.Immediate vcp =
                mc.getBufferBuilders().getEntityVertexConsumers();

        String text = String.valueOf(entity.getStack().getCount());
        float x = -tr.getWidth(text) / 2f;

        tr.draw(
                text,
                x,
                0,
                0xFFFFFF,
                false,
                matrix,
                vcp,
                TextRenderer.TextLayerType.NORMAL,
                0,
                15728880
        );

        vcp.draw();
        matrices.pop();
    }
}
