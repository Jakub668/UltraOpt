package pl.jakub.ultraopt.mixin.particle;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ParticleManager.class)
public class EntityRenderLimiterMixin {

    private static int count = 0;
    private static final int MAX = 300;

    @Inject(method = "addParticle", at = @At("HEAD"), cancellable = true)
    private void ultraopt$limitParticles(Particle particle, CallbackInfo ci) {
        if (count++ > MAX) {
            ci.cancel();
        }
    }

}
