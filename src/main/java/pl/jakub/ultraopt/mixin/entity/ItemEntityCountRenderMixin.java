package pl.jakub.ultraopt.mixin.entity;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.ItemEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.ItemEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.RotationAxis;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntityRenderer.class)
public class ItemEntityCountRenderMixin {

    @Inject(
        method = "render",
        at = @At("TAIL")
    )
    private void ultraopt$renderItemCount(
            ItemEntity entity,
            float yaw,
            float tickDelta,
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            int light,
            CallbackInfo ci
    ) {
        int count = entity.getStack().getCount();
        if (count <= 1) return;

        matrices.push();

        // pozycja NAD itemem
        matrices.translate(0.0, 0.6, 0.0);

        // zawsze przodem do kamery
        matrices.multiply(
                RotationAxis.POSITIVE_Y.rotationDegrees(
                        -entity.getYaw()
                )
        );

        matrices.scale(-0.025f, -0.025f, 0.025f);

        TextRenderer renderer =
                net.minecraft.client.MinecraftClient.getInstance().textRenderer;

        Text text = Text.literal(String.valueOf(count));

        renderer.draw(
                text,
                -renderer.getWidth(text) / 2f,
                0,
                0xFFFFFF,
                false,
                matrices,
                vertexConsumers,
                TextRenderer.TextLayerType.NORMAL,
                0,
                light
        );

        matrices.pop();
    }
}