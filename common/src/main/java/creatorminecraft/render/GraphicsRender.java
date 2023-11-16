package creatorminecraft.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public record GraphicsRender(MultiBufferSource.BufferSource multiBufferSource) {
    public static void graphicsBackgroundDraw(PoseStack poseStack, VertexConsumer vertexConsumer, int color, int light) {
        final Vec3i vec3i = Direction.UP.getNormal();
        final Matrix4f matrix4f = poseStack.last().pose();
        final Matrix3f matrix3f = poseStack.last().normal();
        final int alpha = (color >> 24) & 0xFF;
        final int red = (color >> 16) & 0xFF;
        final int green = (color >> 8) & 0xFF;
        final int blue = color & 0xFF;
        if (alpha == 0) {
            return;
        }
        vertexConsumer.vertex(matrix4f, Integer.MIN_VALUE, Integer.MIN_VALUE, -256).color(red, green, blue, alpha).uv(0, 1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(matrix3f, vec3i.getX(), vec3i.getY(), vec3i.getZ()).endVertex();
        vertexConsumer.vertex(matrix4f, Integer.MAX_VALUE, Integer.MIN_VALUE, -256).color(red, green, blue, alpha).uv(1, 1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(matrix3f, vec3i.getX(), vec3i.getY(), vec3i.getZ()).endVertex();
        vertexConsumer.vertex(matrix4f, Integer.MAX_VALUE, Integer.MAX_VALUE, -256).color(red, green, blue, alpha).uv(1, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(matrix3f, vec3i.getX(), vec3i.getY(), vec3i.getZ()).endVertex();
        vertexConsumer.vertex(matrix4f, Integer.MIN_VALUE, Integer.MAX_VALUE, -256).color(red, green, blue, alpha).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(matrix3f, vec3i.getX(), vec3i.getY(), vec3i.getZ()).endVertex();
    }

}
