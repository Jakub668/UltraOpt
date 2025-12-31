package pl.jakub.ultraopt.mixin.chunk;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "net.minecraft.client.render.chunk.ChunkBuilder$BuiltChunk")
public class ChunkRebuildMixin {

    @Inject(method = "rebuild", at = @At("HEAD"), cancellable = true)
    private void ultraopt$skipRebuild(CallbackInfo ci) {
        // placeholder pod inteligentne skipy
    }
}
