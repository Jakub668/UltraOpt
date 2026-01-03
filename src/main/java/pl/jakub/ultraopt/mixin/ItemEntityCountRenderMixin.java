package pl.jakub.ultraopt.mixin;

import net.minecraft.client.render.entity.ItemEntityRenderer;
import net.minecraft.entity.ItemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntityRenderer.class)
public class ItemEntityCountRenderMixin {

    // ❗ NIE renderujemy tekstu
    // ❗ Tylko zapisujemy, że entity było renderowane (hook)
    @Inject(
            method = "render",
            at = @At("HEAD")
    )
    private void ultraopt$onRender(ItemEntity entity, float yaw, float tickDelta,
                                   net.minecraft.client.util.math.MatrixStack matrices,
                                   net.minecraft.client.render.VertexConsumerProvider vertexConsumers,
                                   int light, CallbackInfo ci) {
        // INTENCJONALNIE PUSTE
        // Mixin zostaje, ale NIC NIE BLOKUJE initu
    }
}
