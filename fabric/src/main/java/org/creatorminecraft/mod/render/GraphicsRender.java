package org.creatorminecraft.mod.render;

import org.mtr.mapping.holder.Direction;
import org.mtr.mapping.mapper.GraphicsHolder;
import org.mtr.mapping.mapper.GuiDrawing;

public interface GraphicsRender {
     static void graphicsBackgroundDraw(GraphicsHolder graphicsHolder, int color, int light) {
        drawTexture(graphicsHolder, Integer.MIN_VALUE, Integer.MAX_VALUE, -256, Integer.MAX_VALUE, Integer.MIN_VALUE, -256, 0, 0, 1, 1, Direction.UP, color, light);

    }

    static void drawTexture(GraphicsHolder graphicsHolder, float x1, float y1, float z1, float x2, float y2, float z2, float u1, float v1, float u2, float v2, Direction facing, int color, int light) {
        drawTexture(graphicsHolder, x1, y2, z1, x2, y2, z2, x2, y1, z2, x1, y1, z1, u1, v1, u2, v2, facing, color, light);
    }

    static void drawTexture(GraphicsHolder graphicsHolder, float x1, float y1, float z1, float x2, float y2, float z2, float x3, float y3, float z3, float x4, float y4, float z4, float u1, float v1, float u2, float v2, Direction facing, int color, int light) {
        graphicsHolder.drawTextureInWorld(x1, y1, z1, x2, y2, z2, x3, y3, z3, x4, y4, z4, u1, v1, u2, v2, facing, color, light);
    }

    static void drawTexture(GuiDrawing guiDrawing, int x, int y, float u, float v, int width, int height, int textureWidth, int textureHeight) {
        drawTexture(guiDrawing, x, y, width, height, u, v, width, height, textureWidth, textureHeight);
    }

    static void drawTexture(GuiDrawing guiDrawing, int x, int y, int width, int height, float u, float v, int regionWidth, int regionHeight, int textureWidth, int textureHeight) {
        drawTexture(guiDrawing, x, y, x + width, y + height, regionWidth, regionHeight, u, v, textureWidth, textureHeight);
    }

    static void drawTexture(GuiDrawing guiDrawing, int x1, int y1, int x2, int y2, int regionWidth, int regionHeight, float u, float v, int textureWidth, int textureHeight) {
        drawTexture(guiDrawing, x1, y1, x2, y2, u / textureWidth, v / textureHeight, (u + regionWidth) / textureWidth, (v + regionHeight) / textureHeight);
    }

    static void drawTexture(GuiDrawing guiDrawing, int x1, int y1, int x2, int y2, float u1, float v1, float u2, float v2) {
        guiDrawing.drawTexture(x1, y1, x2, y2, u1, v1, u2, v2);
    }
}
