// ItemEntityPhysicsMixin.java
// Przyklejenie itemów do ziemi

package pl.jakub.ultraopt.mixin.item;

import net.minecraft.entity.ItemEntity;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pl.jakub.ultraopt.UltraOpt;

@Mixin(ItemEntity.class)
public class ItemEntityPhysicsMixin {

    @Inject(method = "tick", at = @At("HEAD"))
    private void ultraopt$physics(CallbackInfo ci) {
        if (!UltraOpt.CONFIG.enabled) return;
        if (!UltraOpt.CONFIG.itemPhysics) return;

        ItemEntity e = (ItemEntity)(Object)this;
        e.setYaw(0);
        e.setPitch(90);
        e.setVelocity(e.getVelocity().multiply(1, 0, 1));
    }
}