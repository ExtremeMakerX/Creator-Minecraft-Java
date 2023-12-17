package org.creatorminecraft.mod.model;

import org.creatorminecraft.mod.screen.CreatorMinecraftScreen;
import org.mtr.mapping.holder.EntityAbstractMapping;
import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.holder.RenderLayer;
import org.mtr.mapping.mapper.EntityModelExtension;
import org.mtr.mapping.mapper.GraphicsHolder;
import org.mtr.mapping.mapper.ModelPartExtension;

import static org.creatorminecraft.mod.model.DynamicEntityModelEditing.modelPartDataList;

public class MovementArrows extends EntityModelExtension<EntityAbstractMapping> {

	private static final Identifier red_texture = new Identifier("creatorminecraft:textures/texture_color/red_texture.png");
	private static final Identifier blue_texture = new Identifier("creatorminecraft:textures/texture_color/blue_texture.png");
	private static final Identifier green_texture = new Identifier("creatorminecraft:textures/texture_color/green_texture.png");
private ModelPartExtension red_arrow;
	private ModelPartExtension blue_arrow;
	private ModelPartExtension green_arrow;

	@Override
	public void setAngles2(EntityAbstractMapping entityAbstractMapping, float v, float v1, float v2, float v3, float v4) {

	}

	public MovementArrows() {
        super(112, 112);
	if (CreatorMinecraftScreen.modelPartTarget >= 0 && CreatorMinecraftScreen.modelPartTarget < modelPartDataList.size()) {
		DynamicEntityModelEditing.ModelPartData modelPartData = modelPartDataList.get(CreatorMinecraftScreen.modelPartTarget);
		if (modelPartData.isVisible) {
			red_arrow = createModelPart();
			red_arrow.setPivot(modelPartData.getCenterModelPartX(), modelPartData.getCenterModelPartY(), modelPartData.getCenterModelPartZ());
			red_arrow.setRotation(modelPartData.getCenterModelPartRotationX(), modelPartData.getCenterModelPartRotationY(), modelPartData.getCenterModelPartRotationZ());
			red_arrow.setTextureUVOffset(4, 50).addCuboid(-67.0F, -7.0F, -8.5F, (int) 21.0F, (int) 11.0F, (int) 17.0F, 0.0F, false);
			red_arrow.setTextureUVOffset(39, 49).addCuboid(-46.0F, -2.0F, -0.5F, (int) 45.0F, (int) 2.0F, (int) 2.0F, 0.0F, false);
			red_arrow.setTextureUVOffset(39, 49).addCuboid(-46.0F, -2.0F, -0.5F, (int) 45.0F, (int) 2.0F, (int) 2.0F, 0.0F, false);
			blue_arrow = createModelPart();
			blue_arrow.setPivot(modelPartData.getCenterModelPartX(), modelPartData.getCenterModelPartY(), modelPartData.getCenterModelPartZ());
			blue_arrow.setRotation(modelPartData.getCenterModelPartRotationX(), modelPartData.getCenterModelPartRotationY(), modelPartData.getCenterModelPartRotationZ());
			blue_arrow.setTextureUVOffset(-7, -7).addCuboid(-1.0F, -2.0F, -45.5F, (int) 2.0F, (int) 2.0F, (int) 47.0F, 0.0F, false);
			blue_arrow.setTextureUVOffset(0, 46).addCuboid(-8.5F, -8.0F, -66.5F, (int) 17.0F, (int) 11.0F, (int) 21.0F, 0.0F, false);

			green_arrow = createModelPart();
			green_arrow.setPivot(modelPartData.getCenterModelPartX(), modelPartData.getCenterModelPartY(), modelPartData.getCenterModelPartZ());
			green_arrow.setRotation(modelPartData.getCenterModelPartRotationX(), modelPartData.getCenterModelPartRotationY(), modelPartData.getCenterModelPartRotationZ());
			green_arrow.setTextureUVOffset(39, 49).addCuboid(-1.0F, -49.0F, -0.5F, (int) 2.0F, (int) 47.0F, (int) 2.0F, 0.0F, false);
			green_arrow.setTextureUVOffset(10, 56).addCuboid(-8.5F, -70.0F, -4.75F, (int) 17.0F, (int) 21.0F, (int) 11.0F, 0.0F, false);
		}
	}
}


	public void render(GraphicsHolder matrices,  int light, int position) {
		ModelObjectBase.renderOnce(red_arrow, matrices, light, position);
		ModelObjectBase.renderOnce(blue_arrow, matrices, light, position);
		ModelObjectBase.renderOnce(green_arrow, matrices, light, position);
	}

	@Override
	public void render(GraphicsHolder graphicsHolder, int i, int i1, float v, float v1, float v2, float v3) {

	}
}