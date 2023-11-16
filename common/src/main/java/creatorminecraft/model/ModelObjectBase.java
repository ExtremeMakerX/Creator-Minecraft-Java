package creatorminecraft.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.world.entity.Entity;

public class ModelObjectBase extends EntityModel<Entity> {
    @Override
    public void setupAnim(Entity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
    }
    @Override
    public final void renderToBuffer(PoseStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha ) {
    }

    public static void renderOnce(ModelPartBuilder bone, PoseStack matrices, VertexConsumer vertices, int light, int position) {
        bone.render(matrices, vertices, light, position);
    }
}
