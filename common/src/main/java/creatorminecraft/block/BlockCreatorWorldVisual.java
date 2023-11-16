package creatorminecraft.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BlockCreatorWorldVisual extends Block implements EntityBlock {
    public static int rotationAngleMode = 1;
    public BlockCreatorWorldVisual(Properties properties) {
        super(properties);
    }



    @Override
    public @NotNull InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if (level.isClientSide) {
            if (interactionHand == InteractionHand.MAIN_HAND) {
                rotationAngleMode++;
                if (rotationAngleMode == 5) {
                    rotationAngleMode = 1;
                }
            }
        }
        return super.use(blockState, level, blockPos, player, interactionHand, blockHitResult);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new BlockCreatorWorldVisualRenderer.TileEntityCreatorWorldVisual(blockPos, blockState);
    }
}
