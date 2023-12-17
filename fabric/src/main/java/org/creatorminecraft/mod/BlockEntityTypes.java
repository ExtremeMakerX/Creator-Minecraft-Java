package org.creatorminecraft.mod;

import org.creatorminecraft.mod.block.BlockCreatorWorldVisualRenderer;
import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.registry.BlockEntityTypeRegistryObject;
import org.mtr.mapping.registry.Registry;

public final class BlockEntityTypes {

	static {
		CREATOR_WORLD_VISUAL_RENDERER = Registry.registerBlockEntityType(new Identifier(Init.MOD_ID, "creator_world_visual"), BlockCreatorWorldVisualRenderer.TileEntityCreatorWorldVisual::new, Blocks.CREATOR_WORLD_VISUAL::get);
	}

	public final static BlockEntityTypeRegistryObject<BlockCreatorWorldVisualRenderer.TileEntityCreatorWorldVisual> CREATOR_WORLD_VISUAL_RENDERER;

	public static void init() {
		CreatorMC.LOGGER.info("Registering Creator Minecraft Java Entity Types");
	}
}
