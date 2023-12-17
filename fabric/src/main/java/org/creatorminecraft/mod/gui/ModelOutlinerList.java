package org.creatorminecraft.mod.gui;

import org.creatorminecraft.mod.client.CreatorIGUI;
import org.creatorminecraft.mod.model.DynamicEntityModelEditing;
import org.creatorminecraft.mod.render.GraphicsRender;
import org.creatorminecraft.mod.screen.CreatorMinecraftScreen;
import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.holder.RenderLayer;
import org.mtr.mapping.holder.TextRenderer;
import org.mtr.mapping.mapper.GraphicsHolder;
import org.mtr.mapping.mapper.GuiDrawing;

public class ModelOutlinerList implements CreatorIGUI {

    public int x;
    public int y;
    private static final int THEME_WIDGET_SLOT_SIZE = 25;
    private static final int WIDGET_POSITION = THEME_WIDGET_SLOT_SIZE - BIG_SQUARE_SIZE;
    private boolean isVisible;
    private final boolean[] isHoveredOver = new boolean[0];
    public static boolean updateModelPartData = false;

    public void render(GraphicsHolder guiGraphics) {
        if (isVisible) {
            for (int i = 0; i < DynamicEntityModelEditing.getModelListSize(); i++) {
                final int drawY = THEME_WIDGET_SLOT_SIZE * i + WIDGET_POSITION;
                    drawModelCubeWidget(guiGraphics, x, y + drawY, i);
            }
        }
    }

    public void drawModelCubeWidget(GraphicsHolder guiGraphics, int positionX, int positionY, int index) {
        int x = positionX + STANDARD_SIZE * 2;
        guiGraphics.drawCenteredText("modelpart_" + index, x + BIG_SQUARE_SIZE + 10, positionY, ARGB_WHITE);
       // guiGraphics.blit(new Identifier("creatorminecraft:textures/gui/icon_cube.png"), x, positionY, 0, 0, 15, 15, 15, 15);
        final GuiDrawing guiDrawing = new GuiDrawing(guiGraphics);
        guiDrawing.beginDrawingTexture(new Identifier("creatorminecraft:textures/gui/icon_cube.png"));
        GraphicsRender.drawTexture(guiDrawing, x, positionY, (float) 0, 0, 15, 15, 15, 15);
        guiDrawing.finishDrawingTexture();
    }

    public void renderHighlight(GraphicsHolder guiGraphics) {
        if (isVisible && DynamicEntityModelEditing.getModelListSize() != 0) {
            final int drawY = THEME_WIDGET_SLOT_SIZE * CreatorMinecraftScreen.modelPartTarget + WIDGET_POSITION;
            drawModelCubeWidgetHighlight(guiGraphics, x, y + drawY);
        }
    }

    public void drawModelCubeWidgetHighlight(GraphicsHolder guiGraphics, int width, int positionY) {
        int GuiX = width / 2;
        final GuiDrawing guiDrawing = new GuiDrawing(guiGraphics);
        guiDrawing.beginDrawingRectangle();
        guiDrawing.drawRectangle(x + BIG_SQUARE_SIZE - 5, positionY - 5, x + BIG_SQUARE_SIZE - 5 + GuiX, positionY + 20, ARGB_DARK_MID_GRAY);
        guiDrawing.finishDrawingRectangle();
    }

    public void mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0) {
            for (int i = 0; i < DynamicEntityModelEditing.getModelListSize(); i++) {
                final int drawY = THEME_WIDGET_SLOT_SIZE * i + WIDGET_POSITION;
                if (mouseX >= x + BIG_SQUARE_SIZE - 5 && mouseX < x + THEME_WIDGET_SLOT_SIZE + SUPER_GIANT_SQUARE_SIZE + GIANT_SQUARE_SIZE * 3 && mouseY >= y + drawY && mouseY < y + drawY + THEME_WIDGET_SLOT_SIZE) {
                    CreatorMinecraftScreen.modelPartTarget = i;
                    updateModelPartData = true;
                }
            }
        }
    }

    public void setVisible(boolean visible) {
        this.isVisible = visible;
    }
}
