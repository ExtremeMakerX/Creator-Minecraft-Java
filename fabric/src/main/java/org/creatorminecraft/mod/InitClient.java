package org.creatorminecraft.mod;

import org.creatorminecraft.mod.block.BlockCreatorWorldVisualRenderer;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.registry.RegistryClient;

public final class InitClient {

	public static void init() {
		RegistryClient.registerBlockRenderType(RenderLayer.getCutout(), Blocks.CREATOR_WORLD_VISUAL);
		RegistryClient.registerBlockEntityRenderer(BlockEntityTypes.CREATOR_WORLD_VISUAL_RENDERER, BlockCreatorWorldVisualRenderer.Renderer::new);
		//RegistryClient.registerItemModelPredicate(Items.CREATOR_MINECRAFT_JAVA, );
		RegistryClient.init();
	}
}
