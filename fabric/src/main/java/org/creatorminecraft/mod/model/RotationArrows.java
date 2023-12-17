package org.creatorminecraft.mod.model;

import org.creatorminecraft.mod.screen.CreatorMinecraftScreen;
import org.mtr.mapping.holder.EntityAbstractMapping;
import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.holder.RenderLayer;
import org.mtr.mapping.mapper.EntityModelExtension;
import org.mtr.mapping.mapper.GraphicsHolder;
import org.mtr.mapping.mapper.ModelPartExtension;

import static org.creatorminecraft.mod.model.DynamicEntityModelEditing.modelPartDataList;

public class RotationArrows extends EntityModelExtension<EntityAbstractMapping> {

	private static final Identifier red_texture = new Identifier("creatorminecraft:textures/texture_color/red_texture.png");
	private static final Identifier blue_texture = new Identifier("creatorminecraft:textures/texture_color/blue_texture.png");
	private static final Identifier green_texture = new Identifier("creatorminecraft:textures/texture_color/green_texture.png");
	private ModelPartExtension red_circle;
	private ModelPartExtension hexadecagon_r1;
	private ModelPartExtension hexadecagon_r2;
	private ModelPartExtension hexadecagon_r3;
	private ModelPartExtension hexadecagon_r4;
	private ModelPartExtension blue_circle;
	private ModelPartExtension hexadecagon_r5;
	private ModelPartExtension hexadecagon_r6;
	private ModelPartExtension hexadecagon_r7;
	private ModelPartExtension hexadecagon_r8;
	private ModelPartExtension green_circle;
	private ModelPartExtension hexadecagon_r9;
	private ModelPartExtension hexadecagon_r10;
	private ModelPartExtension hexadecagon_r11;
	private ModelPartExtension hexadecagon_r12;

	@Override
	public void setAngles2(EntityAbstractMapping entityAbstractMapping, float v, float v1, float v2, float v3, float v4) {

	}

	public RotationArrows() {
        super(112, 112);
        final int textureWidth = 112;
		final int textureHeight = 112;
		if (CreatorMinecraftScreen.modelPartTarget >= 0 && CreatorMinecraftScreen.modelPartTarget < modelPartDataList.size()) {
			DynamicEntityModelEditing.ModelPartData modelPartData = modelPartDataList.get(CreatorMinecraftScreen.modelPartTarget);
			if (modelPartData.isVisible) {
				red_circle = createModelPart();
				red_circle.setPivot(modelPartData.getCenterModelPartX(), modelPartData.getCenterModelPartY(), modelPartData.getCenterModelPartZ());
				red_circle.setRotation(modelPartData.getCenterModelPartRotationX(), modelPartData.getCenterModelPartRotationY(), modelPartData.getCenterModelPartRotationZ());
				red_circle.setTextureUVOffset(0, 0).addCuboid(-9.9456F, 50.0F, -1.0F, (int) 19.8912F, (int) 0.0F, (int) 2.0F, 0.0F, false);
				red_circle.setTextureUVOffset(0, 0).addCuboid(-9.9456F, -50.0F, -1.0F, (int) 19.8912F,  (int) 0.0F, (int) 2.0F, 0.0F, false);
				red_circle.setTextureUVOffset(0, 0).addCuboid(50.0F, -9.9456F, -1.0F, (int) 0.0F, (int) 19.8912F, (int) 2.0F, 0.0F, false);
				red_circle.setTextureUVOffset(0, 0).addCuboid(-50.0F, -9.9456F, -1.0F, (int) 0.0F, (int) 19.8912F, (int) 2.0F, 0.0F, false);

				hexadecagon_r1 = createModelPart();
				hexadecagon_r1.setPivot(0.0F, 0.0F, 0.0F);
				red_circle.addChild(hexadecagon_r1);
				hexadecagon_r1.setRotation(0.0F, 0.0F, 0.3927F);
				hexadecagon_r1.setTextureUVOffset(0, 0).addCuboid(-50.0F, -9.9456F, -1.0F, (int) 0.0F, (int) 19.8912F, (int) 2.0F, 0.0F, false);
				hexadecagon_r1.setTextureUVOffset(0, 0).addCuboid(50.0F, -9.9456F, -1.0F, (int) 0.0F, (int) 19.8912F, (int) 2.0F, 0.0F, false);
				hexadecagon_r1.setTextureUVOffset(0, 0).addCuboid(-9.9456F, -50.0F, -1.0F, (int) 19.8912F, (int) 0.0F, (int) 2.0F, 0.0F, false);
				hexadecagon_r1.setTextureUVOffset(0, 0).addCuboid(-9.9456F, 50.0F, -1.0F, (int) 19.8912F, (int) 0.0F, (int) 2.0F, 0.0F, false);

				hexadecagon_r2 = createModelPart();
				hexadecagon_r2.setPivot(0.0F, 0.0F, 0.0F);
				red_circle.addChild(hexadecagon_r2);
				hexadecagon_r2.setRotation(0.0F, 0.0F, -0.3927F);
				hexadecagon_r2.setTextureUVOffset(0, 0).addCuboid(-50.0F, -9.9456F, -1.0F, (int) 0.0F, (int) 19.8912F, (int) 2.0F, 0.0F, false);
				hexadecagon_r2.setTextureUVOffset(0, 0).addCuboid(50.0F, -9.9456F, -1.0F, (int) 0.0F, (int) 19.8912F, (int) 2.0F, 0.0F, false);
				hexadecagon_r2.setTextureUVOffset(0, 0).addCuboid(-9.9456F, -50.0F, -1.0F, (int) 19.8912F, (int) 0.0F, (int) 2.0F, 0.0F, false);
				hexadecagon_r2.setTextureUVOffset(0, 0).addCuboid(-9.9456F, 50.0F, -1.0F, (int)19.8912F, (int) 0.0F, (int) 2.0F, 0.0F, false);

				hexadecagon_r3 = createModelPart();
				hexadecagon_r3.setPivot(0.0F, 0.0F, 0.0F);
				red_circle.addChild(hexadecagon_r3);
				hexadecagon_r3.setRotation(0.0F, 0.0F, 0.7854F);
				hexadecagon_r3.setTextureUVOffset(0, 0).addCuboid(-9.9456F, -50.0F, -1.0F, (int) 19.8912F, (int) 0.0F, (int) 2.0F, 0.0F, false);
				hexadecagon_r3.setTextureUVOffset(0, 0).addCuboid(-9.9456F, 50.0F, -1.0F, (int) 19.8912F, (int) 0.0F, (int) 2.0F, 0.0F, false);

				hexadecagon_r4 = createModelPart();
				hexadecagon_r4.setPivot(0.0F, 0.0F, 0.0F);
				red_circle.addChild(hexadecagon_r4);
				hexadecagon_r4.setRotation(0.0F, 0.0F, -0.7854F);
				hexadecagon_r4.setTextureUVOffset(0, 0).addCuboid(-9.9456F, -50.0F, -1.0F, (int) 19.8912F, (int) 0.0F, (int) 2.0F, 0.0F, false);
				hexadecagon_r4.setTextureUVOffset(0, 0).addCuboid(-9.9456F, 50.0F, -1.0F, (int) 19.8912F, (int) 0.0F, (int) 2.0F, 0.0F, false);

				blue_circle = createModelPart();
				blue_circle.setPivot(modelPartData.getCenterModelPartX(), modelPartData.getCenterModelPartY(), modelPartData.getCenterModelPartZ());
				blue_circle.setRotation(modelPartData.getCenterModelPartRotationX(), modelPartData.getCenterModelPartRotationY(), modelPartData.getCenterModelPartRotationZ());
				blue_circle.setTextureUVOffset(0, 0).addCuboid(-1.0F, -9.9456F, -50.0F, (int) 2.0F, (int) 19.8912F, (int) 0.0F, 0.0F, false);
				blue_circle.setTextureUVOffset(0, 0).addCuboid(-1.0F, -9.9456F, 50.0F, (int) 2.0F, (int) 19.8912F, (int) 0.0F, 0.0F, false);
				blue_circle.setTextureUVOffset(0, 0).addCuboid(-1.0F, 50.0F, -9.9456F, (int) 2.0F, (int) 0.0F, (int) 19.8912F, 0.0F, false);
				blue_circle.setTextureUVOffset(0, 0).addCuboid(-1.0F, -50.0F, -9.9456F, (int) 2.0F, (int) 0.0F, (int) 19.8912F, 0.0F, false);

				hexadecagon_r5 = createModelPart();
				hexadecagon_r5.setPivot(0.0F, 0.0F, 0.0F);
				blue_circle.addChild(hexadecagon_r5);
				hexadecagon_r5.setRotation(-0.3927F, 0.0F, 0.0F);
				hexadecagon_r5.setTextureUVOffset(0, 0).addCuboid(-1.0F, -50.0F, -9.9456F, (int) 2.0F, (int) 0.0F, (int) 19.8912F, 0.0F, false);
				hexadecagon_r5.setTextureUVOffset(0, 0).addCuboid(-1.0F, 50.0F, -9.9456F, (int) 2.0F, (int) 0.0F, (int) 19.8912F, 0.0F, false);
				hexadecagon_r5.setTextureUVOffset(0, 0).addCuboid(-1.0F, -9.9456F, 50.0F, (int) 2.0F, (int) 19.8912F, (int) 0.0F, 0.0F, false);
				hexadecagon_r5.setTextureUVOffset(0, 0).addCuboid(-1.0F, -9.9456F, -50.0F, (int) 2.0F, (int) 19.8912F, (int) 0.0F, 0.0F, false);

				hexadecagon_r6 = createModelPart();
				hexadecagon_r6.setPivot(0.0F, 0.0F, 0.0F);
				blue_circle.addChild(hexadecagon_r6);
				hexadecagon_r6.setRotation(0.3927F, 0.0F, 0.0F);
				hexadecagon_r6.setTextureUVOffset(0, 0).addCuboid(-1.0F, -50.0F, -9.9456F, (int) 2.0F, (int) 0.0F, (int) 19.8912F, 0.0F, false);
				hexadecagon_r6.setTextureUVOffset(0, 0).addCuboid(-1.0F, 50.0F, -9.9456F, (int) 2.0F, (int) 0.0F, (int) 19.8912F, 0.0F, false);
				hexadecagon_r6.setTextureUVOffset(0, 0).addCuboid(-1.0F, -9.9456F, 50.0F, (int) 2.0F,  (int) 19.8912F, (int) 0.0F, 0.0F, false);
				hexadecagon_r6.setTextureUVOffset(0, 0).addCuboid(-1.0F, -9.9456F, -50.0F,  (int)2.0F, (int) 19.8912F, (int) 0.0F, 0.0F, false);

				hexadecagon_r7 = createModelPart();
				hexadecagon_r7.setPivot(0.0F, 0.0F, 0.0F);
				blue_circle.addChild(hexadecagon_r7);
				hexadecagon_r7.setRotation(-0.7854F, 0.0F, 0.0F);
				hexadecagon_r7.setTextureUVOffset(0, 0).addCuboid(-1.0F, -9.9456F, 50.0F, (int) 2.0F, (int) 19.8912F, (int) 0.0F, 0.0F, false);
				hexadecagon_r7.setTextureUVOffset(0, 0).addCuboid(-1.0F, -9.9456F, -50.0F, (int) 2.0F, (int) 19.8912F, (int) 0.0F, 0.0F, false);

				hexadecagon_r8 = createModelPart();
				hexadecagon_r8.setPivot(0.0F, 0.0F, 0.0F);
				blue_circle.addChild(hexadecagon_r8);
				hexadecagon_r8.setRotation(0.7854F, 0.0F, 0.0F);
				hexadecagon_r8.setTextureUVOffset(0, 0).addCuboid(-1.0F, -9.9456F, 50.0F, (int) 2.0F, (int) 19.8912F, (int) 0.0F, 0.0F, false);
				hexadecagon_r8.setTextureUVOffset(0, 0).addCuboid(-1.0F, -9.9456F, -50.0F, (int) 2.0F, (int) 19.8912F, (int) 0.0F, 0.0F, false);

				green_circle = createModelPart();
				green_circle.setPivot(modelPartData.getCenterModelPartX(), modelPartData.getCenterModelPartY(), modelPartData.getCenterModelPartZ());
				green_circle.setRotation(modelPartData.getCenterModelPartRotationX(), modelPartData.getCenterModelPartRotationY(), modelPartData.getCenterModelPartRotationZ());
				green_circle.setTextureUVOffset(0, 0).addCuboid(-9.9456F, -1.0F, -50.0F, (int) 19.8912F, (int) 2.0F, (int) 0.0F, 0.0F, false);
				green_circle.setTextureUVOffset(0, 0).addCuboid(-9.9456F, -1.0F, 50.0F, (int) 19.8912F, (int) 2.0F, (int) 0.0F, 0.0F, false);
				green_circle.setTextureUVOffset(0, 0).addCuboid(50.0F, -1.0F, -9.9456F, (int) 0.0F, (int) 2.0F, (int) 19.8912F, 0.0F, false);
				green_circle.setTextureUVOffset(0, 0).addCuboid(-50.0F, -1.0F, -9.9456F, (int) 0.0F, (int) 2.0F, (int) 19.8912F, 0.0F, false);

				hexadecagon_r9 = createModelPart();
				hexadecagon_r9.setPivot(0.0F, 0.0F, 0.0F);
				green_circle.addChild(hexadecagon_r9);
				hexadecagon_r9.setRotation(0.0F, -0.3927F, 0.0F);
				hexadecagon_r9.setTextureUVOffset(0, 0).addCuboid(-50.0F, -1.0F, -9.9456F, (int) 0.0F, (int) 2.0F, (int) 19.8912F, 0.0F, false);
				hexadecagon_r9.setTextureUVOffset(0, 0).addCuboid(50.0F, -1.0F, -9.9456F, (int) 0.0F, (int) 2.0F, (int) 19.8912F, 0.0F, false);
				hexadecagon_r9.setTextureUVOffset(0, 0).addCuboid(-9.9456F, -1.0F, 50.0F, (int) 19.8912F, (int) 2.0F, (int) 0.0F, 0.0F, false);
				hexadecagon_r9.setTextureUVOffset(0, 0).addCuboid(-9.9456F, -1.0F, -50.0F, (int)19.8912F, (int) 2.0F, (int) 0.0F, 0.0F, false);

				hexadecagon_r10 = createModelPart();
				hexadecagon_r10.setPivot(0.0F, 0.0F, 0.0F);
				green_circle.addChild(hexadecagon_r10);
				hexadecagon_r10.setRotation(0.0F, 0.3927F, 0.0F);
				hexadecagon_r10.setTextureUVOffset(0, 0).addCuboid(-50.0F, -1.0F, -9.9456F, (int) 0.0F, (int) 2.0F, (int) 19.8912F, 0.0F, false);
				hexadecagon_r10.setTextureUVOffset(0, 0).addCuboid(50.0F, -1.0F, -9.9456F, (int) 0.0F, (int) 2.0F, (int) 19.8912F, 0.0F, false);
				hexadecagon_r10.setTextureUVOffset(0, 0).addCuboid(-9.9456F, -1.0F, 50.0F, (int) 19.8912F, (int) 2.0F, (int) 0.0F, 0.0F, false);
				hexadecagon_r10.setTextureUVOffset(0, 0).addCuboid(-9.9456F, -1.0F, -50.0F,  (int) 19.8912F, (int) 2.0F, (int) 0.0F, 0.0F, false);

				hexadecagon_r11 = createModelPart();
				hexadecagon_r11.setPivot(0.0F, 0.0F, 0.0F);
				green_circle.addChild(hexadecagon_r11);
				hexadecagon_r11.setRotation(0.0F, -0.7854F, 0.0F);
				hexadecagon_r11.setTextureUVOffset(0, 0).addCuboid(-9.9456F, -1.0F, 50.0F, (int) 19.8912F, (int)  2.0F, (int) 0.0F, 0.0F, false);
				hexadecagon_r11.setTextureUVOffset(0, 0).addCuboid(-9.9456F, -1.0F, -50.0F, (int) 19.8912F, (int) 2.0F, (int) 0.0F, 0.0F, false);

				hexadecagon_r12 = createModelPart();
				hexadecagon_r12.setPivot(0.0F, 0.0F, 0.0F);
				green_circle.addChild(hexadecagon_r12);
				hexadecagon_r12.setRotation(0.0F, 0.7854F, 0.0F);
				hexadecagon_r12.setTextureUVOffset(0, 0).addCuboid(-9.9456F, -1.0F, 50.0F, (int) 19.8912F, (int) 2.0F, (int) 0.0F, 0.0F, false);
				hexadecagon_r12.setTextureUVOffset(0, 0).addCuboid(-9.9456F, -1.0F, -50.0F, (int) 19.8912F, (int) 2.0F, (int) 0.0F, 0.0F, false);
			}
		}
	}

	public void render(GraphicsHolder matrices, int light, int position) {
		ModelObjectBase.renderOnce(red_circle, matrices, light, position);
		ModelObjectBase.renderOnce(blue_circle, matrices, light, position);
		ModelObjectBase.renderOnce(green_circle, matrices, light, position);
	}

	@Override
	public void render(GraphicsHolder graphicsHolder, int i, int i1, float v, float v1, float v2, float v3) {

	}
}