// ItemEntityRendererMixin.java
// Jeden renderowany model

package pl.jakub.ultraopt.mixin.item;

import net.minecraft.client.render.entity.ItemEntityRenderer;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import pl.jakub.ultraopt.UltraOpt;

@Mixin(ItemEntityRenderer.class)
public class ItemEntityRendererMixin {

    @Redirect(
        method = "render",
        at = @At(
            value = "INVOKE",
            target =
              "Lnet/minecraft/client/render/entity/ItemEntityRenderer;getRenderedAmount(I)I"
        )
    )
    private int ultraopt$oneModel(int count) {
        if (!UltraOpt.CONFIG.enabled) return count;
        if (!UltraOpt.CONFIG.itemSingleModel) return count;
        return 1;
    }
}