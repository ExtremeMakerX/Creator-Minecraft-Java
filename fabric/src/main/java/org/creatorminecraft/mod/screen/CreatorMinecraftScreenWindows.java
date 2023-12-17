package org.creatorminecraft.mod.screen;

import org.creatorminecraft.mod.client.CreatorIGUI;
import org.mtr.mapping.mapper.GraphicsHolder;
import org.mtr.mapping.mapper.GuiDrawing;
import org.mtr.mapping.mapper.TextHelper;

public class CreatorMinecraftScreenWindows implements CreatorIGUI {

    protected CreatorMinecraftScreenWindows() {

    }

    public void renderModelEditorGui(GraphicsHolder guiGraphics, int height, int width) {
        final int rightSideX = width - BIG_SQUARE_SIZE * 4;
        int GuiX = width - 175;
        int GuiY = STANDARD_SIZE * 9 + 10;
        int sizeWidth = 180;
        int sizeHeight = 400;

        final GuiDrawing guiDrawing = new GuiDrawing(guiGraphics);
        guiDrawing.beginDrawingRectangle();
        guiDrawing.drawRectangle(width - BIG_SQUARE_SIZE * 4 - STANDARD_SIZE, 0, width + 20, height, ARGB_BLACK);
        guiDrawing.drawRectangle(GuiX, GuiY, GuiX + sizeWidth, GuiY + sizeHeight,ARGB_DARK_BLACK);
        guiDrawing.finishDrawingRectangle();
        guiGraphics.drawCenteredText(TextHelper.translatable("gui.mtr.element_model_editor"), rightSideX + TINY_SQUARE, STANDARD_SIZE + 12, ARGB_WHITE);

        guiGraphics.drawCenteredText(TextHelper.translatable("gui.mtr.position_model_editor"), rightSideX, STANDARD_SIZE * 3 + 5, ARGB_WHITE);
        guiGraphics.drawCenteredText(TextHelper.translatable("gui.mtr.size_model_editor"), rightSideX, STANDARD_SIZE * 4 + 5, ARGB_WHITE);
        guiGraphics.drawCenteredText(TextHelper.translatable("gui.mtr.pivot_model_editor"), rightSideX, STANDARD_SIZE * 5 + 5, ARGB_WHITE);
        guiGraphics.drawCenteredText(TextHelper.translatable("gui.mtr.rotation_model_editor"), rightSideX, STANDARD_SIZE * 6 + 5, ARGB_WHITE);
        guiGraphics.drawCenteredText(TextHelper.translatable("gui.mtr.inflate_model_editor"), rightSideX, STANDARD_SIZE * 7 + 5, ARGB_WHITE);
    }
}
