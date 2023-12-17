package org.creatorminecraft.mod.block;

import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockEntityExtension;
import org.mtr.mapping.mapper.BlockExtension;
import org.mtr.mapping.mapper.BlockWithEntity;

public class BlockCreatorWorldVisual extends BlockExtension implements BlockWithEntity {
    public static int rotationAngleMode = 1;

    public BlockCreatorWorldVisual(BlockSettings blockSettings) {
        super(blockSettings);
    }



    @Override
    public ActionResult onUse2(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient()) {
                rotationAngleMode++;
                if (rotationAngleMode == 5) {
                    rotationAngleMode = 1;
                }
            }
        return super.onUse2(state, world, pos, player, hand, hit);
    }

    @Override
    public BlockEntityExtension createBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new BlockCreatorWorldVisualRenderer.TileEntityCreatorWorldVisual(blockPos, blockState);
    }
}
