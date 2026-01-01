package pl.jakub.ultraopt.mixin.item;

import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public class ItemStackMixin {

    /**
     * Mała optymalizacja:
     * - jeśli stack jest pusty, nie rób zbędnych operacji
     * - minimalnie zmniejsza obciążenie GUI / inventory
     */
    @Inject(method = "isEmpty", at = @At("HEAD"), cancellable = true)
    private void ultraopt$fastEmptyCheck(CallbackInfoReturnable<Boolean> cir) {
        ItemStack self = (ItemStack) (Object) this;

        if (self.getCount() <= 0) {
            cir.setReturnValue(true);
        }
    }
}
