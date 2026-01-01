package pl.jakub.ultraopt.client.overlay;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.ItemEntity;
import net.minecraft.util.math.Box;

import java.util.List;

public class ItemCountOverlay {

    public static void render(DrawContext context) {
        MinecraftClient client = MinecraftClient.getInstance();

        if (client.player == null || client.world == null) return;

        // Zasięg sprawdzania itemów (w blokach)
        double range = 8.0;

        Box box = client.player.getBoundingBox().expand(range);

        List<ItemEntity> items = client.world.getEntitiesByClass(
                ItemEntity.class,
                box,
                item -> true
        );

        int count = items.size();

        TextRenderer textRenderer = client.textRenderer;

        context.drawText(
                textRenderer,
                "Items nearby: " + count,
                5,      // X
                5,      // Y
                0xFFFFFF,
                true
        );
    }
}
