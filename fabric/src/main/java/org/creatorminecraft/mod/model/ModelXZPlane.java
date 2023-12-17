package org.creatorminecraft.mod.model;

import org.mtr.mapping.holder.EntityAbstractMapping;
import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.mapper.EntityModelExtension;
import org.mtr.mapping.mapper.GraphicsHolder;
import org.mtr.mapping.mapper.ModelPartExtension;

public class ModelXZPlane extends EntityModelExtension<EntityAbstractMapping> {
    private final ModelPartExtension main;
    private static final Identifier texture = new Identifier("creatorminecraft:textures/gui/xzplane.png");

    @Override
    public void setAngles2(EntityAbstractMapping entityAbstractMapping, float v, float v1, float v2, float v3, float v4) {

    }

    public ModelXZPlane() {
        super(1680, 1680);
        main = createModelPart();
        main.setPivot(0.0F, 0.0F, 0.0F);
        main.setTextureUVOffset(0, 0).addCuboid(-208.5F, 0.0F, -208.5F, (int) 417.0F, (int) 0.0F, (int) 417.0F, 0.0F, false);
    }

    public void render(GraphicsHolder matrices, int light, int position) {
        ModelObjectBase.renderOnce(main, matrices, light, position);
    }

    @Override
    public void render(GraphicsHolder graphicsHolder, int i, int i1, float v, float v1, float v2, float v3) {

    }
}
