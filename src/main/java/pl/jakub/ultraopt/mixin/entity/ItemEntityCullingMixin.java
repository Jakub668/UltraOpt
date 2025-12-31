package pl.jakub.ultraopt.mixin.entity;

import net.minecraft.entity.ItemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public abstract class ItemEntityCullingMixin {

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void ultraopt$skipItemTick(CallbackInfo ci) {
        // skip fizyki i animacji
        ci.cancel();
    }
}