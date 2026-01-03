package pl.jakub.ultraopt.client.overlay;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.ItemEntity;

public class ItemCountOverlay {

    public static void renderWorld(MatrixStack matrices, Camera camera) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.world == null || mc.player == null) return;

        for (ItemEntity entity : mc.world.getEntitiesByClass(
                ItemEntity.class,
                camera.getBoundingBox().expand(16),
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

        String text = String.valueOf(entity.getStack().getCount());
        float x = -tr.getWidth(text) / 2f;

        tr.draw(
                matrices,
                text,
                x,
                0,
                0xFFFFFF
        );

        matrices.pop();
    }
}
