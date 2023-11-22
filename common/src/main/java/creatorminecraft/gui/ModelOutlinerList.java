package creatorminecraft.gui;

import creatorminecraft.client.CreatorIGUI;
import creatorminecraft.client.ThemeGUIEnum;
import creatorminecraft.model.DynamicEntityModelEditing;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;

import java.util.Arrays;

public class ModelOutlinerList implements CreatorIGUI {

    public int x;
    public int y;
    public int screenWidthCenterTarget;
    public int screenHeightCenterTarget;
    //private static final int getModelListSize = DynamicEntityModelEditing.getModelListSize();
    private static final int[] getModelListSizeArray = DynamicEntityModelEditing.getModelListSizeArray();
    private static final String[] customImageThemeWidget = Arrays.stream(ThemeGUIEnum.values()).map(ThemeGUIEnum::getCustomImageThemeWidget).toArray(String[]::new);
    private static final int THEME_WIDGET_SLOT_SIZE = 25;
    private static final int WIDGET_POSITION = THEME_WIDGET_SLOT_SIZE - BIG_SQUARE_SIZE;
    private boolean isVisible;
    private final boolean[] isHoveredOver = new boolean[2];

    public void render(GuiGraphics guiGraphics, Font textRenderer) {
        if (isVisible) {
            for (int i = 0; i < DynamicEntityModelEditing.getModelListSize(); i++) {
                final int drawY = THEME_WIDGET_SLOT_SIZE * i + WIDGET_POSITION;
                    drawModelCubeWidget(guiGraphics, textRenderer, x, y + drawY, i);
            }
        }
    }

    public void drawModelCubeWidget(GuiGraphics guiGraphics, Font textRenderer, int positionX, int positionY, int index) {
        int x = positionX + STANDARD_SIZE * 2;
        int width = 20;
        int height = 20;
        guiGraphics.drawCenteredString(textRenderer, "modelpart_" + index, x + BIG_SQUARE_SIZE + 10, positionY, ARGB_WHITE);
        guiGraphics.blit(new ResourceLocation("creatorminecraft:textures/gui/icon_cube.png"), x, positionY, 0, 0, 15, 15, 15, 15);
    }

    public void setVisible(boolean visible) {
        this.isVisible = visible;
    }
}
