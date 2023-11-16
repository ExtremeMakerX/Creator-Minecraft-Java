package creatorminecraft.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class ModelXZPlane extends EntityModel<Entity> {
    private final ModelPartBuilder main;
    private static final ResourceLocation texture = new ResourceLocation("creatorminecraft:textures/gui/xzplane.png");

    public ModelXZPlane() {
        final int textureWidth = 1680;
        final int textureHeight = 1680;

        main = new ModelPartBuilder(new ModelExtender(resourceLocation -> RenderType.solid(), this, textureWidth, textureHeight));
        main.setPos(0.0F, 24.0F, 0.0F);
        main.texOffs(0, 0).addBox(-208.5F, 0.0F, -208.5F, 417.0F, 0.0F, 417.0F, 0.0F, false);
    }

    public void render(PoseStack matrices, MultiBufferSource vertexConsumers, int light, int position) {
        ModelObjectBase.renderOnce(main, matrices, vertexConsumers.getBuffer(RenderType.entitySmoothCutout(texture)), light, position);
    }

    @Override
    public void setupAnim(Entity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
    }

    @Override
    public final void renderToBuffer(PoseStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {

    }
}
