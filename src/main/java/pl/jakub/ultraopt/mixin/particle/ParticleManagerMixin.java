package pl.jakub.ultraopt.mixin.particle;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ParticleManager.class)
public class ParticleManagerMixin {

    private static int ultraopt$particleCounter = 0;
    private static final int ULTRAOPT_MAX_PARTICLES = 500; // bezpieczny limit

    @Inject(method = "addParticle", at = @At("HEAD"), cancellable = true)
    private void ultraopt$limitParticles(Particle particle, CallbackInfo ci) {
        ultraopt$particleCounter++;

        if (ultraopt$particleCounter > ULTRAOPT_MAX_PARTICLES) {
            ci.cancel(); // NIE dodawaj więcej
        }
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void ultraopt$resetCounter(CallbackInfo ci) {
        ultraopt$particleCounter = 0;
    }
}
