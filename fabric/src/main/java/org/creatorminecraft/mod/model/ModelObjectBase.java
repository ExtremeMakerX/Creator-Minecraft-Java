package org.creatorminecraft.mod.model;

import org.mtr.mapping.holder.EntityAbstractMapping;
import org.mtr.mapping.holder.OverlayTexture;
import org.mtr.mapping.mapper.EntityModelExtension;
import org.mtr.mapping.mapper.GraphicsHolder;
import org.mtr.mapping.mapper.ModelPartExtension;

public class ModelObjectBase extends EntityModelExtension<EntityAbstractMapping> {

    public ModelObjectBase(int textureWidth, int textureHeight) {
        super(textureWidth, textureHeight);
    }

    public static void renderOnce(ModelPartExtension bone, GraphicsHolder matrices, int light, int position) {
        if (bone != null) {
            bone.render(matrices, 0, position, 0, light, OverlayTexture.getDefaultUvMapped());
        }
    }

    @Override
    public void setAngles2(EntityAbstractMapping entityAbstractMapping, float v, float v1, float v2, float v3, float v4) {

    }

    @Override
    public void render(GraphicsHolder graphicsHolder, int i, int i1, float v, float v1, float v2, float v3) {

    }
}
