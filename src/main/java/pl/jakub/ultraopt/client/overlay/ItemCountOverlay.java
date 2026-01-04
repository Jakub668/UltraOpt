package pl.jakub.ultraopt.client.overlay;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.ItemEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;

public class ItemCountOverlay implements HudRenderCallback {

    private static final MinecraftClient MC = MinecraftClient.getInstance();

    @Override
    public void onHudRender(DrawContext context, float tickDelta) {
        if (MC.world == null || MC.player == null) return;

        var textRenderer = MC.textRenderer;
        var camera = MC.gameRenderer.getCamera();
        Vec3d camPos = camera.getPos();

        for (ItemEntity item : MC.world.getEntitiesByClass(
                ItemEntity.class,
                MC.player.getBoundingBox().expand(16),
                e -> true
        )) {
            int count = item.getStack().getCount();
            if (count <= 1) continue;

            Vec3d pos = item.getPos().subtract(camPos);

            // prosta projekcja (bez bounding boxa kamery – bo go NIE MA)
            double yOffset = item.getHeight() + 0.3;

            int screenX = (int) (MC.getWindow().getScaledWidth() / 2 + pos.x * 40);
            int screenY = (int) (MC.getWindow().getScaledHeight() / 2 - (pos.y + yOffset) * 40);

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
