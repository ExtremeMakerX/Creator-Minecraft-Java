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

public class RotationArrows extends EntityModel<Entity> {

	private static final ResourceLocation red_texture = new ResourceLocation("creatorminecraft:textures/texture_color/red_texture.png");
	private static final ResourceLocation blue_texture = new ResourceLocation("creatorminecraft:textures/texture_color/blue_texture.png");
	private static final ResourceLocation green_texture = new ResourceLocation("creatorminecraft:textures/texture_color/green_texture.png");
	private ModelPartBuilder red_circle;
	private ModelPartBuilder hexadecagon_r1;
	private ModelPartBuilder hexadecagon_r2;
	private ModelPartBuilder hexadecagon_r3;
	private ModelPartBuilder hexadecagon_r4;
	private ModelPartBuilder blue_circle;
	private ModelPartBuilder hexadecagon_r5;
	private ModelPartBuilder hexadecagon_r6;
	private ModelPartBuilder hexadecagon_r7;
	private ModelPartBuilder hexadecagon_r8;
	private ModelPartBuilder green_circle;
	private ModelPartBuilder hexadecagon_r9;
	private ModelPartBuilder hexadecagon_r10;
	private ModelPartBuilder hexadecagon_r11;
	private ModelPartBuilder hexadecagon_r12;

	public RotationArrows() {
		final int textureWidth = 112;
		final int textureHeight = 112;
		if (CreatorMinecraftScreen.modelPartTarget >= 0 && CreatorMinecraftScreen.modelPartTarget < modelPartDataList.size()) {
			DynamicEntityModelEditing.ModelPartData modelPartData = modelPartDataList.get(CreatorMinecraftScreen.modelPartTarget);
			if (modelPartData.isVisible) {
				red_circle = new ModelPartBuilder(new ModelExtender(resourceLocation -> RenderType.solid(), this, textureWidth, textureHeight));
				red_circle.setPos(modelPartData.getCenterModelPartX(), modelPartData.getCenterModelPartY(), modelPartData.getCenterModelPartZ());
				red_circle.setRotationAngle(modelPartData.getCenterModelPartRotationX(), modelPartData.getCenterModelPartRotationY(), modelPartData.getCenterModelPartRotationZ());
				red_circle.texOffs(0, 0).addBox(-9.9456F, 50.0F, -1.0F, 19.8912F, 0.0F, 2.0F, 0.0F, false);
				red_circle.texOffs(0, 0).addBox(-9.9456F, -50.0F, -1.0F, 19.8912F, 0.0F, 2.0F, 0.0F, false);
				red_circle.texOffs(0, 0).addBox(50.0F, -9.9456F, -1.0F, 0.0F, 19.8912F, 2.0F, 0.0F, false);
				red_circle.texOffs(0, 0).addBox(-50.0F, -9.9456F, -1.0F, 0.0F, 19.8912F, 2.0F, 0.0F, false);

				hexadecagon_r1 = new ModelPartBuilder(new ModelExtender(resourceLocation -> RenderType.solid(), this, textureWidth, textureHeight));
				hexadecagon_r1.setPos(0.0F, 0.0F, 0.0F);
				red_circle.addChild(hexadecagon_r1);
				hexadecagon_r1.setRotationAngle(0.0F, 0.0F, 0.3927F);
				hexadecagon_r1.texOffs(0, 0).addBox(-50.0F, -9.9456F, -1.0F, 0.0F, 19.8912F, 2.0F, 0.0F, false);
				hexadecagon_r1.texOffs(0, 0).addBox(50.0F, -9.9456F, -1.0F, 0.0F, 19.8912F, 2.0F, 0.0F, false);
				hexadecagon_r1.texOffs(0, 0).addBox(-9.9456F, -50.0F, -1.0F, 19.8912F, 0.0F, 2.0F, 0.0F, false);
				hexadecagon_r1.texOffs(0, 0).addBox(-9.9456F, 50.0F, -1.0F, 19.8912F, 0.0F, 2.0F, 0.0F, false);

				hexadecagon_r2 = new ModelPartBuilder(new ModelExtender(resourceLocation -> RenderType.solid(), this, textureWidth, textureHeight));
				hexadecagon_r2.setPos(0.0F, 0.0F, 0.0F);
				red_circle.addChild(hexadecagon_r2);
				hexadecagon_r2.setRotationAngle(0.0F, 0.0F, -0.3927F);
				hexadecagon_r2.texOffs(0, 0).addBox(-50.0F, -9.9456F, -1.0F, 0.0F, 19.8912F, 2.0F, 0.0F, false);
				hexadecagon_r2.texOffs(0, 0).addBox(50.0F, -9.9456F, -1.0F, 0.0F, 19.8912F, 2.0F, 0.0F, false);
				hexadecagon_r2.texOffs(0, 0).addBox(-9.9456F, -50.0F, -1.0F, 19.8912F, 0.0F, 2.0F, 0.0F, false);
				hexadecagon_r2.texOffs(0, 0).addBox(-9.9456F, 50.0F, -1.0F, 19.8912F, 0.0F, 2.0F, 0.0F, false);

				hexadecagon_r3 = new ModelPartBuilder(new ModelExtender(resourceLocation -> RenderType.solid(), this, textureWidth, textureHeight));
				hexadecagon_r3.setPos(0.0F, 0.0F, 0.0F);
				red_circle.addChild(hexadecagon_r3);
				hexadecagon_r3.setRotationAngle(0.0F, 0.0F, 0.7854F);
				hexadecagon_r3.texOffs(0, 0).addBox(-9.9456F, -50.0F, -1.0F, 19.8912F, 0.0F, 2.0F, 0.0F, false);
				hexadecagon_r3.texOffs(0, 0).addBox(-9.9456F, 50.0F, -1.0F, 19.8912F, 0.0F, 2.0F, 0.0F, false);

				hexadecagon_r4 = new ModelPartBuilder(new ModelExtender(resourceLocation -> RenderType.solid(), this, textureWidth, textureHeight));
				hexadecagon_r4.setPos(0.0F, 0.0F, 0.0F);
				red_circle.addChild(hexadecagon_r4);
				hexadecagon_r4.setRotationAngle(0.0F, 0.0F, -0.7854F);
				hexadecagon_r4.texOffs(0, 0).addBox(-9.9456F, -50.0F, -1.0F, 19.8912F, 0.0F, 2.0F, 0.0F, false);
				hexadecagon_r4.texOffs(0, 0).addBox(-9.9456F, 50.0F, -1.0F, 19.8912F, 0.0F, 2.0F, 0.0F, false);

				blue_circle = new ModelPartBuilder(new ModelExtender(resourceLocation -> RenderType.solid(), this, textureWidth, textureHeight));
				blue_circle.setPos(modelPartData.getCenterModelPartX(), modelPartData.getCenterModelPartY(), modelPartData.getCenterModelPartZ());
				blue_circle.setRotationAngle(modelPartData.getCenterModelPartRotationX(), modelPartData.getCenterModelPartRotationY(), modelPartData.getCenterModelPartRotationZ());
				blue_circle.texOffs(0, 0).addBox(-1.0F, -9.9456F, -50.0F, 2.0F, 19.8912F, 0.0F, 0.0F, false);
				blue_circle.texOffs(0, 0).addBox(-1.0F, -9.9456F, 50.0F, 2.0F, 19.8912F, 0.0F, 0.0F, false);
				blue_circle.texOffs(0, 0).addBox(-1.0F, 50.0F, -9.9456F, 2.0F, 0.0F, 19.8912F, 0.0F, false);
				blue_circle.texOffs(0, 0).addBox(-1.0F, -50.0F, -9.9456F, 2.0F, 0.0F, 19.8912F, 0.0F, false);

				hexadecagon_r5 = new ModelPartBuilder(new ModelExtender(resourceLocation -> RenderType.solid(), this, textureWidth, textureHeight));
				hexadecagon_r5.setPos(0.0F, 0.0F, 0.0F);
				blue_circle.addChild(hexadecagon_r5);
				hexadecagon_r5.setRotationAngle(-0.3927F, 0.0F, 0.0F);
				hexadecagon_r5.texOffs(0, 0).addBox(-1.0F, -50.0F, -9.9456F, 2.0F, 0.0F, 19.8912F, 0.0F, false);
				hexadecagon_r5.texOffs(0, 0).addBox(-1.0F, 50.0F, -9.9456F, 2.0F, 0.0F, 19.8912F, 0.0F, false);
				hexadecagon_r5.texOffs(0, 0).addBox(-1.0F, -9.9456F, 50.0F, 2.0F, 19.8912F, 0.0F, 0.0F, false);
				hexadecagon_r5.texOffs(0, 0).addBox(-1.0F, -9.9456F, -50.0F, 2.0F, 19.8912F, 0.0F, 0.0F, false);

				hexadecagon_r6 = new ModelPartBuilder(new ModelExtender(resourceLocation -> RenderType.solid(), this, textureWidth, textureHeight));
				hexadecagon_r6.setPos(0.0F, 0.0F, 0.0F);
				blue_circle.addChild(hexadecagon_r6);
				hexadecagon_r6.setRotationAngle(0.3927F, 0.0F, 0.0F);
				hexadecagon_r6.texOffs(0, 0).addBox(-1.0F, -50.0F, -9.9456F, 2.0F, 0.0F, 19.8912F, 0.0F, false);
				hexadecagon_r6.texOffs(0, 0).addBox(-1.0F, 50.0F, -9.9456F, 2.0F, 0.0F, 19.8912F, 0.0F, false);
				hexadecagon_r6.texOffs(0, 0).addBox(-1.0F, -9.9456F, 50.0F, 2.0F, 19.8912F, 0.0F, 0.0F, false);
				hexadecagon_r6.texOffs(0, 0).addBox(-1.0F, -9.9456F, -50.0F, 2.0F, 19.8912F, 0.0F, 0.0F, false);

				hexadecagon_r7 = new ModelPartBuilder(new ModelExtender(resourceLocation -> RenderType.solid(), this, textureWidth, textureHeight));
				hexadecagon_r7.setPos(0.0F, 0.0F, 0.0F);
				blue_circle.addChild(hexadecagon_r7);
				hexadecagon_r7.setRotationAngle(-0.7854F, 0.0F, 0.0F);
				hexadecagon_r7.texOffs(0, 0).addBox(-1.0F, -9.9456F, 50.0F, 2.0F, 19.8912F, 0.0F, 0.0F, false);
				hexadecagon_r7.texOffs(0, 0).addBox(-1.0F, -9.9456F, -50.0F, 2.0F, 19.8912F, 0.0F, 0.0F, false);

				hexadecagon_r8 = new ModelPartBuilder(new ModelExtender(resourceLocation -> RenderType.solid(), this, textureWidth, textureHeight));
				hexadecagon_r8.setPos(0.0F, 0.0F, 0.0F);
				blue_circle.addChild(hexadecagon_r8);
				hexadecagon_r8.setRotationAngle(0.7854F, 0.0F, 0.0F);
				hexadecagon_r8.texOffs(0, 0).addBox(-1.0F, -9.9456F, 50.0F, 2.0F, 19.8912F, 0.0F, 0.0F, false);
				hexadecagon_r8.texOffs(0, 0).addBox(-1.0F, -9.9456F, -50.0F, 2.0F, 19.8912F, 0.0F, 0.0F, false);

				green_circle = new ModelPartBuilder(new ModelExtender(resourceLocation -> RenderType.solid(), this, textureWidth, textureHeight));
				green_circle.setPos(modelPartData.getCenterModelPartX(), modelPartData.getCenterModelPartY(), modelPartData.getCenterModelPartZ());
				green_circle.setRotationAngle(modelPartData.getCenterModelPartRotationX(), modelPartData.getCenterModelPartRotationY(), modelPartData.getCenterModelPartRotationZ());
				green_circle.texOffs(0, 0).addBox(-9.9456F, -1.0F, -50.0F, 19.8912F, 2.0F, 0.0F, 0.0F, false);
				green_circle.texOffs(0, 0).addBox(-9.9456F, -1.0F, 50.0F, 19.8912F, 2.0F, 0.0F, 0.0F, false);
				green_circle.texOffs(0, 0).addBox(50.0F, -1.0F, -9.9456F, 0.0F, 2.0F, 19.8912F, 0.0F, false);
				green_circle.texOffs(0, 0).addBox(-50.0F, -1.0F, -9.9456F, 0.0F, 2.0F, 19.8912F, 0.0F, false);

				hexadecagon_r9 = new ModelPartBuilder(new ModelExtender(resourceLocation -> RenderType.solid(), this, textureWidth, textureHeight));
				hexadecagon_r9.setPos(0.0F, 0.0F, 0.0F);
				green_circle.addChild(hexadecagon_r9);
				hexadecagon_r9.setRotationAngle(0.0F, -0.3927F, 0.0F);
				hexadecagon_r9.texOffs(0, 0).addBox(-50.0F, -1.0F, -9.9456F, 0.0F, 2.0F, 19.8912F, 0.0F, false);
				hexadecagon_r9.texOffs(0, 0).addBox(50.0F, -1.0F, -9.9456F, 0.0F, 2.0F, 19.8912F, 0.0F, false);
				hexadecagon_r9.texOffs(0, 0).addBox(-9.9456F, -1.0F, 50.0F, 19.8912F, 2.0F, 0.0F, 0.0F, false);
				hexadecagon_r9.texOffs(0, 0).addBox(-9.9456F, -1.0F, -50.0F, 19.8912F, 2.0F, 0.0F, 0.0F, false);

				hexadecagon_r10 = new ModelPartBuilder(new ModelExtender(resourceLocation -> RenderType.solid(), this, textureWidth, textureHeight));
				hexadecagon_r10.setPos(0.0F, 0.0F, 0.0F);
				green_circle.addChild(hexadecagon_r10);
				hexadecagon_r10.setRotationAngle(0.0F, 0.3927F, 0.0F);
				hexadecagon_r10.texOffs(0, 0).addBox(-50.0F, -1.0F, -9.9456F, 0.0F, 2.0F, 19.8912F, 0.0F, false);
				hexadecagon_r10.texOffs(0, 0).addBox(50.0F, -1.0F, -9.9456F, 0.0F, 2.0F, 19.8912F, 0.0F, false);
				hexadecagon_r10.texOffs(0, 0).addBox(-9.9456F, -1.0F, 50.0F, 19.8912F, 2.0F, 0.0F, 0.0F, false);
				hexadecagon_r10.texOffs(0, 0).addBox(-9.9456F, -1.0F, -50.0F, 19.8912F, 2.0F, 0.0F, 0.0F, false);

				hexadecagon_r11 = new ModelPartBuilder(new ModelExtender(resourceLocation -> RenderType.solid(), this, textureWidth, textureHeight));
				hexadecagon_r11.setPos(0.0F, 0.0F, 0.0F);
				green_circle.addChild(hexadecagon_r11);
				hexadecagon_r11.setRotationAngle(0.0F, -0.7854F, 0.0F);
				hexadecagon_r11.texOffs(0, 0).addBox(-9.9456F, -1.0F, 50.0F, 19.8912F, 2.0F, 0.0F, 0.0F, false);
				hexadecagon_r11.texOffs(0, 0).addBox(-9.9456F, -1.0F, -50.0F, 19.8912F, 2.0F, 0.0F, 0.0F, false);

				hexadecagon_r12 = new ModelPartBuilder(new ModelExtender(resourceLocation -> RenderType.solid(), this, textureWidth, textureHeight));
				hexadecagon_r12.setPos(0.0F, 0.0F, 0.0F);
				green_circle.addChild(hexadecagon_r12);
				hexadecagon_r12.setRotationAngle(0.0F, 0.7854F, 0.0F);
				hexadecagon_r12.texOffs(0, 0).addBox(-9.9456F, -1.0F, 50.0F, 19.8912F, 2.0F, 0.0F, 0.0F, false);
				hexadecagon_r12.texOffs(0, 0).addBox(-9.9456F, -1.0F, -50.0F, 19.8912F, 2.0F, 0.0F, 0.0F, false);
			}
		}
	}

	public void render(PoseStack matrices, MultiBufferSource vertexConsumers, int light, int position) {
		ModelObjectBase.renderOnce(red_circle, matrices, vertexConsumers.getBuffer(RenderType.entitySmoothCutout(red_texture)), light, position);
		ModelObjectBase.renderOnce(blue_circle, matrices, vertexConsumers.getBuffer(RenderType.entitySmoothCutout(blue_texture)), light, position);
		ModelObjectBase.renderOnce(green_circle, matrices, vertexConsumers.getBuffer(RenderType.entitySmoothCutout(green_texture)), light, position);
	}

	@Override
	public void setupAnim(Entity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
	}

	@Override
	public final void renderToBuffer(PoseStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
	}
}