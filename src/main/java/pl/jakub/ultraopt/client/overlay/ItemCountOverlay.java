package pl.jakub.ultraopt.client.overlay;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.ItemEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;

public class ItemCountOverlay implements HudRenderCallback {

    private static final MinecraftClient MC = MinecraftClient.getInstance();

    @Override
    public void onHudRender(DrawContext context, RenderTickCounter tickCounter) {
        if (MC.world == null || MC.player == null) return;

        var camera = MC.gameRenderer.getCamera();
        Vec3d cameraPos = camera.getPos();
        var textRenderer = MC.textRenderer;

        for (ItemEntity item : MC.world.getEntitiesByClass(
                ItemEntity.class,
                MC.player.getBoundingBox().expand(16),
                e -> true
        )) {
            int count = item.getStack().getCount();
            if (count <= 1) continue;

            Vec3d pos = item.getPos().subtract(cameraPos);
            double distSq = pos.lengthSquared();
            if (distSq > 16 * 16) continue;

            double scale = 1.0 / Math.sqrt(distSq);
            int x = (int) (MC.getWindow().getScaledWidth() / 2 + pos.x * 40 * scale);
            int y = (int) (MC.getWindow().getScaledHeight() / 2 - pos.y * 40 * scale);

            context.drawText(
                    textRenderer,
                    Text.literal(String.valueOf(count)),
                    x,
                    y,
                    0xFFFFFF,
                    true
            );
        }
    }
}
