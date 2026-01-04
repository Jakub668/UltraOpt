package pl.jakub.ultraopt.mixin.chunk;

import net.minecraft.client.render.chunk.ChunkBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChunkBuilder.class)
public class ChunkRebuildMixin {

    @Inject(
            method = "scheduleRebuild",
            at = @At("HEAD"),
            cancellable = false
    )
    private void ultraopt$onScheduleRebuild(CallbackInfo ci) {
        // SAFE HOOK — brak ingerencji w threading
        // Tu możesz później dodać profiling / licznik
    }
}
