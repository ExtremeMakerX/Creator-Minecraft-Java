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


    public static void drawTexture(PoseStack matrices, VertexConsumer vertexConsumer, float x1, float y1, float z1, float x2, float y2, float z2, Direction facing, int color, int light) {
        drawTexture(matrices, vertexConsumer, x1, y1, z1, x2, y2, z2, 0, 0, 1, 1, facing, color, light);
    }


    static void drawTexture(PoseStack matrices, VertexConsumer vertexConsumer, float x1, float y1, float z1, float x2, float y2, float z2, float u1, float v1, float u2, float v2, Direction facing, int color, int light) {
        drawTexture(matrices, vertexConsumer, x1, y2, z1, x2, y2, z2, x2, y1, z2, x1, y1, z1, u1, v1, u2, v2, facing, color, light);
    }

    static void drawTexture(PoseStack matrices, VertexConsumer vertexConsumer, float x1, float y1, float z1, float x2, float y2, float z2, float x3, float y3, float z3, float x4, float y4, float z4, float u1, float v1, float u2, float v2, Direction facing, int color, int light) {
        final Vec3i vec3i = facing.getNormal();
        final Matrix4f matrix4f = matrices.last().pose();
        final Matrix3f matrix3f = matrices.last().normal();
        final int a = (color >> 24) & 0xFF;
        final int r = (color >> 16) & 0xFF;
        final int g = (color >> 8) & 0xFF;
        final int b = color & 0xFF;
        if (a == 0) {
            return;
        }
        vertexConsumer.vertex(matrix4f, x1, y1, z1).color(r, g, b, a).uv(u1, v2).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(matrix3f, vec3i.getX(), vec3i.getY(), vec3i.getZ()).endVertex();
        vertexConsumer.vertex(matrix4f, x2, y2, z2).color(r, g, b, a).uv(u2, v2).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(matrix3f, vec3i.getX(), vec3i.getY(), vec3i.getZ()).endVertex();
        vertexConsumer.vertex(matrix4f, x3, y3, z3).color(r, g, b, a).uv(u2, v1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(matrix3f, vec3i.getX(), vec3i.getY(), vec3i.getZ()).endVertex();
        vertexConsumer.vertex(matrix4f, x4, y4, z4).color(r, g, b, a).uv(u1, v1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(matrix3f, vec3i.getX(), vec3i.getY(), vec3i.getZ()).endVertex();
    }

}
