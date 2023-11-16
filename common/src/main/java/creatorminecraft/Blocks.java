package creatorminecraft;

import creatorminecraft.block.BlockCreatorWorldVisual;
import creatorminecraft.block.BlockCreatorWorldVisualRenderer;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;


public final class Blocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(CreatorMCID.MOD_ID, Registries.BLOCK);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY = DeferredRegister.create(CreatorMCID.MOD_ID, Registries.BLOCK_ENTITY_TYPE);

    static {
    CREATOR_WORLD_VISUAL = BLOCKS.register("creator_world_visual", () -> new BlockCreatorWorldVisual(BlockBehaviour.Properties.of()));
    CREATOR_WORLD_VISUAL_RENDERER = BLOCK_ENTITY.register("creator_world_visual", () -> BlockEntityType.Builder.of(BlockCreatorWorldVisualRenderer.TileEntityCreatorWorldVisual::new, Blocks.CREATOR_WORLD_VISUAL.get()).build(null));
    }

    public final static RegistrySupplier<Block> CREATOR_WORLD_VISUAL;
    public final static RegistrySupplier<BlockEntityType<BlockCreatorWorldVisualRenderer.TileEntityCreatorWorldVisual>> CREATOR_WORLD_VISUAL_RENDERER;

    public static void init() {
        BLOCKS.register();
        BLOCK_ENTITY.register();
        CreatorMC.LOGGER.info("Registering Creator Minecraft Java blocks");
    }
}
