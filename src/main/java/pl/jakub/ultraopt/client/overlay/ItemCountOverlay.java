textRenderer.draw(
    net.minecraft.text.Text.literal("x" + count),
    x,
    y,
    0xFFFFFF,
    false,
    matrices.peek().getPositionMatrix(),
    vertexConsumers,
    net.minecraft.client.font.TextRenderer.TextLayerType.NORMAL,
    0,
    light
);
