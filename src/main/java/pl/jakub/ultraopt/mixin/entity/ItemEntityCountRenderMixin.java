package pl.jakub.ultraopt.mixin.entity;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.ItemEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.ItemEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntityRenderer.class)
public class ItemEntityCountRenderMixin {

    @Inject(
        method = "render(Lnet/minecraft/entity/ItemEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
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

        MinecraftClient mc = MinecraftClient.getInstance();
        TextRenderer renderer = mc.textRenderer;

        matrices.push();

        // lekko nad itemem
        Vec3d pos = entity.getPos();
        matrices.translate(0.0D, 0.25D, 0.0D);
        matrices.scale(0.02F, -0.02F, 0.02F);

        Matrix4f matrix4f = matrices.peek().getPositionMatrix();

        renderer.draw(
                Text.literal("x" + count),
                -renderer.getWidth("x" + count) / 2.0F,
                0,
                0xFFFFFF,
                false,
                matrix4f,
                vertexConsumers,
                TextRenderer.TextLayerType.NORMAL,
                0,
                light
        );

        matrices.pop();
    }
}
