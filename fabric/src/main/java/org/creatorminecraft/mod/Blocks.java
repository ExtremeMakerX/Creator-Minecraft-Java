package org.creatorminecraft.mod;

import org.creatorminecraft.mod.block.BlockCreatorWorldVisual;
import org.mtr.mapping.holder.Block;
import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.mapper.BlockHelper;
import org.mtr.mapping.registry.BlockRegistryObject;
import org.mtr.mapping.registry.Registry;


public final class Blocks {

    static {
    CREATOR_WORLD_VISUAL = Registry.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "creator_world_visual"), () -> new Block(new BlockCreatorWorldVisual(BlockHelper.createBlockSettings(false))), ItemGroups.CREATOR_ESSENTIALS_TAB);
   }

    public final static BlockRegistryObject CREATOR_WORLD_VISUAL;

    public static void init() {
        CreatorMC.LOGGER.info("Registering Creator Minecraft Java blocks");
    }
}
