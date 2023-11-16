package creatorminecraft;

import creatorminecraft.block.BlockCreatorWorldVisualRenderer;
import dev.architectury.registry.client.rendering.BlockEntityRendererRegistry;

public final class Renderers {
    public static void registerBlockEntityRenderer() {
       BlockEntityRendererRegistry.register(
                Blocks.CREATOR_WORLD_VISUAL_RENDERER.get(),
                 new BlockCreatorWorldVisualRenderer.Renderer<>()
        );
    }
}
