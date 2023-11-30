package creatorminecraft.gui;

import creatorminecraft.client.CreatorIGUI;
import creatorminecraft.model.DynamicEntityModelEditing;
import creatorminecraft.screen.CreatorMinecraftScreen;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

public class ModelOutlinerList implements CreatorIGUI {

    public int x;
    public int y;
    private static final int THEME_WIDGET_SLOT_SIZE = 25;
    private static final int WIDGET_POSITION = THEME_WIDGET_SLOT_SIZE - BIG_SQUARE_SIZE;
    private boolean isVisible;
    private final boolean[] isHoveredOver = new boolean[0];
    public static boolean updateModelPartData = false;

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
        guiGraphics.drawCenteredString(textRenderer, "modelpart_" + index, x + BIG_SQUARE_SIZE + 10, positionY, ARGB_WHITE);
        guiGraphics.blit(new ResourceLocation("creatorminecraft:textures/gui/icon_cube.png"), x, positionY, 0, 0, 15, 15, 15, 15);

    }

    public void renderHighlight(GuiGraphics guiGraphics) {
        if (isVisible && DynamicEntityModelEditing.getModelListSize() != 0) {
            final int drawY = THEME_WIDGET_SLOT_SIZE * CreatorMinecraftScreen.modelPartTarget + WIDGET_POSITION;
            drawModelCubeWidgetHighlight(guiGraphics, x, y + drawY);
        }
    }

    public void drawModelCubeWidgetHighlight(GuiGraphics guiGraphics, int width, int positionY) {
        int GuiX = width / 2;
        guiGraphics.fill(RenderType.guiOverlay(), x + BIG_SQUARE_SIZE - 5, positionY - 5, x + BIG_SQUARE_SIZE - 5 + GuiX, positionY + 20, ARGB_DARK_MID_GRAY);
    }

    public void mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0) {
            for (int i = 0; i < DynamicEntityModelEditing.getModelListSize(); i++) {
                final int drawY = THEME_WIDGET_SLOT_SIZE * i + WIDGET_POSITION;
                if (mouseX >= x + BIG_SQUARE_SIZE - 5 && mouseX < x + THEME_WIDGET_SLOT_SIZE + SUPER_GIANT_SQUARE_SIZE + GIANT_SQUARE_SIZE * 3 && mouseY >= y + drawY && mouseY < y + drawY + THEME_WIDGET_SLOT_SIZE) {
                    CreatorMinecraftScreen.modelPartTarget = i;
                    updateModelPartData = true;
                    System.out.println("Testing 1 2 3");
                }
            }
        }
    }

    public void mouseMoved(double mouseX, double mouseY) {
        for (int i = 0; i < DynamicEntityModelEditing.getModelListSize(); i++) {
            final int drawY = THEME_WIDGET_SLOT_SIZE * i + WIDGET_POSITION;
            //isHoveredOver[i] = mouseX >= x + WIDGET_POSITION && mouseX < x + WIDGET_POSITION + STANDARD_SIZE * 3 && mouseY >= y + drawY && mouseY < y + drawY + THEME_WIDGET_SLOT_SIZE;
        }
    }

    public void setVisible(boolean visible) {
        this.isVisible = visible;
    }
}
