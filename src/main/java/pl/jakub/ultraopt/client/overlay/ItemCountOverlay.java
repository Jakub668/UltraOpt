package pl.jakub.ultraopt.client.overlay;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.ItemEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;

public class ItemCountOverlay implements HudRenderCallback {

    private static final MinecraftClient MC = MinecraftClient.getInstance();

    @Override
    public void onHudRender(DrawContext context, float tickDelta) {
        if (MC.world == null || MC.player == null) return;

        var camera = MC.gameRenderer.getCamera();
        Vec3d cameraPos = camera.getPos();

        var textRenderer = MC.textRenderer;
        MatrixStack matrices = context.getMatrices();

        for (ItemEntity item : MC.world.getEntitiesByClass(
                ItemEntity.class,
                MC.player.getBoundingBox().expand(16),
                e -> true
        )) {
            Vec3d pos = item.getPos().subtract(cameraPos);

            double distanceSq = pos.lengthSquared();
            if (distanceSq > 16 * 16) continue;

            int count = item.getStack().getCount();
            if (count <= 1) continue;

            // prosta projekcja 3D → 2D (bez grzebania w camera bounding box)
            double scale = 1.0 / Math.sqrt(distanceSq);
            int screenX = (int) (MC.getWindow().getScaledWidth() / 2 + pos.x * 40 * scale);
            int screenY = (int) (MC.getWindow().getScaledHeight() / 2 - pos.y * 40 * scale);

            Text text = Text.literal(String.valueOf(count));

            context.drawText(
                    textRenderer,
                    text,
                    screenX,
                    screenY,
                    0xFFFFFF,
                    true
            );
        }
    }
}
