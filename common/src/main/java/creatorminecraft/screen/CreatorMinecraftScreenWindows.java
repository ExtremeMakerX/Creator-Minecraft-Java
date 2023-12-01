package creatorminecraft.screen;

import creatorminecraft.client.CreatorIGUI;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

public class CreatorMinecraftScreenWindows implements CreatorIGUI {

    protected CreatorMinecraftScreenWindows() {

    }

    public void renderModelEditorGui(GuiGraphics guiGraphics, Font textRenderer, int height, int width) {
        final int rightSideX = width - BIG_SQUARE_SIZE * 4;
        int GuiX = width - 175;
        int GuiY = STANDARD_SIZE * 9 + 10;
        int sizeWidth = 180;
        int sizeHeight = 400;

        guiGraphics.fill(width - BIG_SQUARE_SIZE * 4 - STANDARD_SIZE, 0, width + 20, height, ARGB_BLACK);
        guiGraphics.fill(GuiX, GuiY, GuiX + sizeWidth, GuiY + sizeHeight,ARGB_DARK_BLACK);
        guiGraphics.drawCenteredString(textRenderer, Component.translatable("gui.mtr.element_model_editor"), rightSideX + TINY_SQUARE, STANDARD_SIZE + 12, ARGB_WHITE);

        guiGraphics.drawCenteredString(textRenderer, Component.translatable("gui.mtr.position_model_editor"), rightSideX, STANDARD_SIZE * 3 + 5, ARGB_WHITE);
        guiGraphics.drawCenteredString(textRenderer, Component.translatable("gui.mtr.size_model_editor"), rightSideX, STANDARD_SIZE * 4 + 5, ARGB_WHITE);
        guiGraphics.drawCenteredString(textRenderer, Component.translatable("gui.mtr.pivot_model_editor"), rightSideX, STANDARD_SIZE * 5 + 5, ARGB_WHITE);
        guiGraphics.drawCenteredString(textRenderer, Component.translatable("gui.mtr.rotation_model_editor"), rightSideX, STANDARD_SIZE * 6 + 5, ARGB_WHITE);
        guiGraphics.drawCenteredString(textRenderer, Component.translatable("gui.mtr.inflate_model_editor"), rightSideX, STANDARD_SIZE * 7 + 5, ARGB_WHITE);
    }
}
