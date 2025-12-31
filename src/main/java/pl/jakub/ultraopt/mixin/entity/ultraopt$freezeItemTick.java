@Inject(method = "tick", at = @At("HEAD"), cancellable = true)
private void ultraopt$skipItemTick(CallbackInfo ci) {
    ci.cancel();
}