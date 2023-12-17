package org.creatorminecraft.mod.block;

import org.creatorminecraft.mod.BlockEntityTypes;
import org.creatorminecraft.mod.model.DynamicEntityModelEditing;
import org.creatorminecraft.mod.model.GridModel;
import org.creatorminecraft.mod.model.ModelXZPlane;
import org.jetbrains.annotations.NotNull;
import org.mtr.mapping.holder.BlockEntityType;
import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.BlockState;
import org.mtr.mapping.mapper.BlockEntityExtension;
import org.mtr.mapping.mapper.BlockEntityRenderer;
import org.mtr.mapping.mapper.GraphicsHolder;

public class BlockCreatorWorldVisualRenderer extends BlockEntityExtension {
    public BlockCreatorWorldVisualRenderer(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);

    }

    public static class Renderer<T extends TileEntityCreatorWorldVisual> extends BlockEntityRenderer<T> {


        public Renderer(Argument argument) {
            super(argument);
        }

        @Override
        public void render(@NotNull T blockEntity, float v, GraphicsHolder graphicsHolder, int i, int i1) {
            graphicsHolder.push();
            graphicsHolder.translate(0.5, 0, 0); // Adjust the position of the model to fit your needs
            graphicsHolder.rotateXDegrees(0);
            if (BlockCreatorWorldVisual.rotationAngleMode == 1) {
                graphicsHolder.rotateYDegrees(0);
            } else if (BlockCreatorWorldVisual.rotationAngleMode == 2) {
                graphicsHolder.rotateYDegrees(90);
            } else if (BlockCreatorWorldVisual.rotationAngleMode == 3) {
                graphicsHolder.rotateYDegrees(180);
            } else if (BlockCreatorWorldVisual.rotationAngleMode == 4) {
                graphicsHolder.rotateYDegrees(270);
            }
            graphicsHolder.rotateZDegrees(180);
            DynamicEntityModelEditing dynamicEntityModelEditing = new DynamicEntityModelEditing();
            graphicsHolder.push();
            graphicsHolder.translate(0, -2.0f, 0.5);
            dynamicEntityModelEditing.render(graphicsHolder, 0xF000F0, 0);
            graphicsHolder.pop();
            GridModel gridModel = new GridModel();
            graphicsHolder.push();
            graphicsHolder.translate(0, -2.0f, 0.5);
            gridModel.render(graphicsHolder, 0xF000F0, 0);
            graphicsHolder.pop();
            ModelXZPlane modelXZPlane = new ModelXZPlane();
            graphicsHolder.push();
            graphicsHolder.translate(0, -1.02f, 0.5);
            modelXZPlane.render(graphicsHolder, 0xF000F0, 0);
            graphicsHolder.pop();
            graphicsHolder.pop();
        }
    }

    public static class TileEntityCreatorWorldVisual extends BlockEntityExtension {
        public TileEntityCreatorWorldVisual(BlockPos pos, BlockState state) {
            super(BlockEntityTypes.CREATOR_WORLD_VISUAL_RENDERER.get(), pos, state);
        }
    }
}
