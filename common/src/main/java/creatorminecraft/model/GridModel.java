package creatorminecraft.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class GridModel extends EntityModel<Entity> {
	private final ModelPartBuilder main;
	private static final ResourceLocation texture = new ResourceLocation("creatorminecraft:textures/gui/grid_cell.png");


	public GridModel() {
		final int textureWidth = 450;
		final int textureHeight = 450;

		main = new ModelPartBuilder(new ModelExtender(resourceLocation -> RenderType.solid(), this, textureWidth, textureHeight));
		main.setPos(0, 0, 0);
		main.texOffs(-200, 0).addBox(-100, 15.7F, -100, 200, 0, 200, 0, false);
	}

	public void render(PoseStack matrices, MultiBufferSource vertexConsumers, int light, int position) {
		ModelObjectBase.renderOnce(main, matrices, vertexConsumers.getBuffer(RenderType.entityTranslucentCull(texture)), light, position);
	}

	@Override
	public void setupAnim(Entity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
	}

	@Override
	public final void renderToBuffer(PoseStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {

	}




}
