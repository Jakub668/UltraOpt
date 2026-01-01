// ItemCountOverlay.java
// Licznik ilości itemów nad ziemią

package pl.jakub.ultraopt.mixin.item;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.ItemEntity;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pl.jakub.ultraopt.UltraOpt;

@Mixin(net.minecraft.client.render.entity.ItemEntityRenderer.class)
public class ItemCountOverlay {

    @Inject(method = "render", at = @At("TAIL"))
    private void ultraopt$count(
            ItemEntity e, float yaw, float tickDelta,
            MatrixStack m, VertexConsumerProvider v, int light,
            CallbackInfo ci
    ) {
        if (!UltraOpt.CONFIG.enabled) return;
        if (!UltraOpt.CONFIG.itemCounter) return;

        int c = e.getStack().getCount();
        if (c <= 1) return;

        MinecraftClient mc = MinecraftClient.getInstance();

        m.push();
        m.translate(0, 0.4, 0);
        m.scale(0.025f, 0.025f, 0.025f);

        String txt = "x" + c;
        mc.textRenderer.draw(
            txt,
            -mc.textRenderer.getWidth(txt) / 2f,
            0,
            0xFFFFFF,
            false,
            m.peek().getPositionMatrix(),
            v,
            false,
            0,
            light
        );
        m.pop();
    }
}