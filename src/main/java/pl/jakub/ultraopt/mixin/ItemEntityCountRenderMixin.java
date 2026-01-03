package pl.jakub.ultraopt.mixin;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.ItemEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import pl.jakub.ultraopt.client.overlay.ItemCountOverlay;

@Mixin(ItemEntityRenderer.class)
public class ItemEntityCountRenderMixin {

    @Inject(
        method = "render(Lnet/minecraft/entity/ItemEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
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
        ItemStack stack = entity.getStack();
        int count = stack.getCount();
        if (count <= 1) return;

        matrices.push();

        float bob = MathHelper.sin((entity.getItemAge() + tickDelta) / 10.0F) * 0.1F + 0.1F;
        matrices.translate(0.0F, bob + 0.25F, 0.0F);
        matrices.scale(0.02F, -0.02F, 0.02F);

        ItemCountOverlay.render(matrices, -4, 0, count);

        matrices.pop();
    }
}
