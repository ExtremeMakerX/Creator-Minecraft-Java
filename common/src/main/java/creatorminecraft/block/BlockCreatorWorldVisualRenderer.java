package creatorminecraft.block;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import creatorminecraft.Blocks;
import creatorminecraft.model.GridModel;
import creatorminecraft.model.ModelXZPlane;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class BlockCreatorWorldVisualRenderer extends BlockEntity {
    public BlockCreatorWorldVisualRenderer(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);

    }

    @Environment(EnvType.CLIENT)
    public static class Renderer<T extends TileEntityCreatorWorldVisual> implements BlockEntityRenderer<T>, BlockEntityRendererProvider<T> {
        @Override
        public void render(T blockEntity, float f, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int j) {
            poseStack.pushPose();
            poseStack.translate(0.5, 0, 0); // Adjust the position of the model to fit your needs
            poseStack.mulPose(Axis.XP.rotationDegrees(0));
            if (BlockCreatorWorldVisual.rotationAngleMode == 1) {
                poseStack.mulPose(Axis.YP.rotationDegrees(0));
            } else if (BlockCreatorWorldVisual.rotationAngleMode == 2) {
                poseStack.mulPose(Axis.YP.rotationDegrees(90));
            } else if (BlockCreatorWorldVisual.rotationAngleMode == 3) {
                poseStack.mulPose(Axis.YP.rotationDegrees(180));
            } else if (BlockCreatorWorldVisual.rotationAngleMode == 4) {
                poseStack.mulPose(Axis.YP.rotationDegrees(270));
            }
            poseStack.mulPose(Axis.ZP.rotationDegrees(180));
            final Minecraft minecraft = Minecraft.getInstance();
            final MultiBufferSource.BufferSource bufferSource = minecraft.renderBuffers().bufferSource();
            GridModel gridModel = new GridModel();
            poseStack.pushPose();
            poseStack.translate(0, -3.5f, 0.5);
            gridModel.render(poseStack, bufferSource, 0xF000F0, 0);
            poseStack.popPose();
            ModelXZPlane modelXZPlane = new ModelXZPlane();
            poseStack.pushPose();
            poseStack.translate(0, -2.52f, 0.5);
            modelXZPlane.render(poseStack, bufferSource, 0xF000F0, 0);
            poseStack.popPose();
            poseStack.popPose();
            bufferSource.endBatch();
        }

        @Override
        public @NotNull BlockEntityRenderer<T> create(Context context) {
            return this;
        }
    }

    public static class TileEntityCreatorWorldVisual extends BlockEntity {
        public TileEntityCreatorWorldVisual(BlockPos pos, BlockState state) {
            super(Blocks.CREATOR_WORLD_VISUAL_RENDERER.get(), pos, state);
        }
    }
}
