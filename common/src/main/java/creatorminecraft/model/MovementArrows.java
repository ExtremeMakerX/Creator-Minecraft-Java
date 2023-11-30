package creatorminecraft.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import creatorminecraft.screen.CreatorMinecraftScreen;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

import static creatorminecraft.model.DynamicEntityModelEditing.modelPartDataList;

public class MovementArrows extends EntityModel<Entity> {

	private static final ResourceLocation red_texture = new ResourceLocation("creatorminecraft:textures/texture_color/red_texture.png");
	private static final ResourceLocation blue_texture = new ResourceLocation("creatorminecraft:textures/texture_color/blue_texture.png");
	private static final ResourceLocation green_texture = new ResourceLocation("creatorminecraft:textures/texture_color/green_texture.png");
private ModelPartBuilder red_arrow;
	private ModelPartBuilder blue_arrow;
	private ModelPartBuilder green_arrow;
public MovementArrows() {
	final int textureWidth = 112;
	final int textureHeight = 112;
	if (CreatorMinecraftScreen.modelPartTarget >= 0 && CreatorMinecraftScreen.modelPartTarget < modelPartDataList.size()) {
		DynamicEntityModelEditing.ModelPartData modelPartData = modelPartDataList.get(CreatorMinecraftScreen.modelPartTarget);
		if (modelPartData.isVisible) {
			red_arrow = new ModelPartBuilder(new ModelExtender(resourceLocation -> RenderType.solid(), this, textureWidth, textureHeight));
			red_arrow.setPos(modelPartData.getCenterModelPartX(), modelPartData.getCenterModelPartY(), modelPartData.getCenterModelPartZ());
			red_arrow.texOffs(4, 50).addBox(-67.0F, -7.0F, -8.5F, 21.0F, 11.0F, 17.0F, 0.0F, false);
			red_arrow.texOffs(39, 49).addBox(-46.0F, -2.0F, -0.5F, 45.0F, 2.0F, 2.0F, 0.0F, false);
			red_arrow.texOffs(39, 49).addBox(-46.0F, -2.0F, -0.5F, 45.0F, 2.0F, 2.0F, 0.0F, false);
			blue_arrow = new ModelPartBuilder(new ModelExtender(resourceLocation -> RenderType.solid(), this, textureWidth, textureHeight));
			blue_arrow.setPos(modelPartData.getCenterModelPartX(), modelPartData.getCenterModelPartY(), modelPartData.getCenterModelPartZ());
			blue_arrow.texOffs(-7, -7).addBox(-1.0F, -2.0F, -45.5F, 2.0F, 2.0F, 47.0F, 0.0F, false);
			blue_arrow.texOffs(0, 46).addBox(-8.5F, -8.0F, -66.5F, 17.0F, 11.0F, 21.0F, 0.0F, false);

			green_arrow = new ModelPartBuilder(new ModelExtender(resourceLocation -> RenderType.solid(), this, textureWidth, textureHeight));
			green_arrow.setPos(modelPartData.getCenterModelPartX(), modelPartData.getCenterModelPartY(), modelPartData.getCenterModelPartZ());
			green_arrow.texOffs(39, 49).addBox(-1.0F, -49.0F, -0.5F, 2.0F, 47.0F, 2.0F, 0.0F, false);
			green_arrow.texOffs(10, 56).addBox(-8.5F, -70.0F, -4.75F, 17.0F, 21.0F, 11.0F, 0.0F, false);
		}
	}
}


	public void render(PoseStack matrices, MultiBufferSource vertexConsumers, int light, int position) {
		ModelObjectBase.renderOnce(red_arrow, matrices, vertexConsumers.getBuffer(RenderType.entitySmoothCutout(red_texture)), light, position);
		ModelObjectBase.renderOnce(blue_arrow, matrices, vertexConsumers.getBuffer(RenderType.entitySmoothCutout(blue_texture)), light, position);
		ModelObjectBase.renderOnce(green_arrow, matrices, vertexConsumers.getBuffer(RenderType.entitySmoothCutout(green_texture)), light, position);
	}

	@Override
	public void setupAnim(Entity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
	}

	@Override
	public final void renderToBuffer(PoseStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
	}
}