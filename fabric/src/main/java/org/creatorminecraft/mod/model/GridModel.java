package org.creatorminecraft.mod.model;

import org.mtr.mapping.holder.EntityAbstractMapping;
import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.mapper.EntityModelExtension;
import org.mtr.mapping.mapper.GraphicsHolder;
import org.mtr.mapping.mapper.ModelPartExtension;

public class GridModel extends EntityModelExtension<EntityAbstractMapping> {
	public final ModelPartExtension main;
	private static final Identifier texture = new Identifier("creatorminecraft:textures/gui/grid_cell.png");

	@Override
	public void setAngles2(EntityAbstractMapping entityAbstractMapping, float v, float v1, float v2, float v3, float v4) {

	}

	public GridModel() {
        super(450, 450);
		main = createModelPart();
		main.setPivot(0, 0, 0);
		main.setTextureUVOffset(-200, 0).addCuboid(-100, 15.7F, -100, 200, 0, 200, 0, false);

		buildModel();
	}

	public void render(GraphicsHolder matrices, int light, int position) {
		ModelObjectBase.renderOnce(main, matrices, light, position);
	}


	@Override
	public void render(GraphicsHolder graphicsHolder, int i, int i1, float v, float v1, float v2, float v3) {

	}
}
