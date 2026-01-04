package pl.jakub.ultraopt.client.render;

import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.ItemEntity;
import net.minecraft.util.math.Vec3d;

public class ItemCountWorldRenderer {

    private static final MinecraftClient MC = MinecraftClient.getInstance();

    public static void register() {
        WorldRenderEvents.AFTER_ENTITIES.register(ItemCountWorldRenderer::render);
    }

    private static void render(WorldRenderContext context) {
        if (MC.world == null || MC.player == null) return;

        MatrixStack matrices = context.matrixStack();
        VertexConsumerProvider consumers = context.consumers();
        if (consumers == null) return;

        Vec3d cameraPos = context.camera().getPos();

        for (ItemEntity item : MC.world.getEntitiesByClass(
                ItemEntity.class,
                MC.player.getBoundingBox().expand(16),
                e -> true
        )) {
            int count = item.getStack().getCount();
            if (count <= 1) continue;

            Vec3d pos = item.getPos().subtract(cameraPos);

            matrices.push();
            matrices.translate(pos.x, pos.y + 0.5, pos.z);
            matrices.scale(-0.025f, -0.025f, 0.025f);

            MC.textRenderer.draw(
                    String.valueOf(count),
                    -MC.textRenderer.getWidth(String.valueOf(count)) / 2f,
                    0,
                    0xFFFFFF,
                    false,
                    matrices.peek().getPositionMatrix(),
                    consumers,
                    false,
                    0,
                    15728880
            );

            matrices.pop();
        }
    }
}
