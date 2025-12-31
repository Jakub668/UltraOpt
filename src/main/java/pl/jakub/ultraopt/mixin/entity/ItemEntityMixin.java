package pl.jakub.ultraopt.mixin.entity;

import net.minecraft.entity.ItemEntity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public class ItemEntityMixin {

    // Zatrzymuje obracanie itemów (render + tick)
    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void ultraopt$optimizeItemTick(CallbackInfo ci) {
        ItemEntity self = (ItemEntity) (Object) this;

        // lekki grawitacyjny "snap" do ziemi
        if (self.isOnGround()) {
            self.setVelocity(Vec3d.ZERO);
            ci.cancel(); // NIE wykonuj reszty ticka
        }
    }
}
