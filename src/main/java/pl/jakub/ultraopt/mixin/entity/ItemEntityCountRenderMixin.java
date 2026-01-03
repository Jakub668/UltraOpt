package pl.jakub.ultraopt.mixin.entity;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.ItemEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pl.jakub.ultraopt.UltraOpt;

@Mixin(ItemEntityRenderer.class)
public abstract class ItemEntityCountRenderMixin {

    @Inject(
        method = "render",
        at = @At("TAIL")
    )
    private void ultraopt$renderCount(
            ItemEntity entity,
            float yaw,
            float tickDelta,
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            int light,
            CallbackInfo ci
    ) {
        if (!UltraOpt.CONFIG.enabled) return;
        if (!UltraOpt.CONFIG.itemCounter) return;

        ItemStack stack = entity.getStack();
        if (stack.getCount() <= 1) return;

        matrices.push();

        Vec3d cam = net.minecraft.client.MinecraftClient.getInstance()
                .gameRenderer
                .getCamera()
                .getPos();

        double dx = entity.getX() - cam.x;
        double dy = entity.getY() - cam.y + 0.25;
        double dz = entity.getZ() - cam.z;

        matrices.translate(dx, dy, dz);
        matrices.scale(0.025f, -0.025f, 0.025f);

        TextRenderer textRenderer = net.minecraft.client.MinecraftClient
                .getInstance()
                .textRenderer;

        String text = String.valueOf(stack.getCount());

        float x = -textRenderer.getWidth(text) / 2f;
        textRenderer.draw(
                text,
                x,
                0,
                0xFFFFFF,
                false,
                matrices.peek().getPositionMatrix(),
                vertexConsumers,
                TextRenderer.TextLayerType.NORMAL,
                0,
                light
        );

        matrices.pop();
    }
}
