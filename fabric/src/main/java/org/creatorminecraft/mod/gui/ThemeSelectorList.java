package org.creatorminecraft.mod.gui;

import org.creatorminecraft.mod.client.CreatorIGUI;
import org.creatorminecraft.mod.client.ThemeGUIEnum;
import org.creatorminecraft.mod.render.GraphicsRender;
import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.mapper.GraphicsHolder;
import org.mtr.mapping.mapper.GuiDrawing;

import java.util.*;

public class ThemeSelectorList implements CreatorIGUI {
    public int x;
    public int y;
    public int screenWidthCenterTarget;
    public int screenHeightCenterTarget;
    private final String[] nameUI = Arrays.stream(ThemeGUIEnum.values()).map(ThemeGUIEnum::getNameUI).toArray(String[]::new);
    private final String[] authorName = Arrays.stream(ThemeGUIEnum.values()).map(ThemeGUIEnum::getAuthorName).toArray(String[]::new);

    private final String[] customImageThemeWidget = Arrays.stream(ThemeGUIEnum.values()).map(ThemeGUIEnum::getCustomImageThemeWidget).toArray(String[]::new);
    private final String[] UISkinExtraImage = Arrays.stream(ThemeGUIEnum.values()).map(ThemeGUIEnum::getUISkinExtraImage).toArray(String[]::new);
    private final Boolean[] UIWidgetIsLiquidUI = Arrays.stream(ThemeGUIEnum.values()).map(ThemeGUIEnum::getExperimentWidgetLiquidUI).toArray(Boolean[]::new);
    private static String getUISkinExtraImage = "creatorminecraft:textures/gui/liquid_ui_black_bar.png";
    private static Boolean getUIWidgetisLiquidUI = true;
    private final int THEME_WIDGET_SLOT_SIZE = 160;
    private final int WIDGET_POSITION = THEME_WIDGET_SLOT_SIZE - BIG_SQUARE_SIZE;
    private boolean isVisible;
    private final boolean[] isHoveredOver = new boolean[2];

    public void render(GraphicsHolder guiGraphics) {
        if (isVisible && screenWidthCenterTarget != 0) {
            for (int i = 0; i < 2; i++) {
                final int drawX = THEME_WIDGET_SLOT_SIZE * i + WIDGET_POSITION + screenWidthCenterTarget;
                drawThemeWidget(guiGraphics, x + drawX, y + screenHeightCenterTarget, i);
            }
        }
    }

    public void drawThemeWidget(GraphicsHolder guiGraphics, int positionX, int positionY, int index) {
        int x = positionX + STANDARD_SIZE * 2;
        int width = 120;
        int height = 120;
        guiGraphics.drawCenteredText("Name: " + nameUI[index], x, positionY + BIG_SQUARE_SIZE * 3, ARGB_WHITE);
        guiGraphics.drawCenteredText("Author: " + authorName[index], x, positionY + BIG_SQUARE_SIZE * 3 + STANDARD_SIZE, ARGB_WHITE);
        final GuiDrawing guiDrawing = new GuiDrawing(guiGraphics);
        guiDrawing.beginDrawingTexture(new Identifier(customImageThemeWidget[index]));
        GraphicsRender.drawTexture(guiDrawing, x - BIG_SQUARE_SIZE - STANDARD_SIZE, positionY - 10, (float) 0, 0, width, height, width, height);
        guiDrawing.finishDrawingTexture();
        //guiGraphics.blit(new ResourceLocation(customImageThemeWidget[index]), x - BIG_SQUARE_SIZE - STANDARD_SIZE, positionY - 10, 0, 0, width, height, width, height);
    }

    public void renderBackground(GraphicsHolder guiGraphics) {
        if (isVisible && screenWidthCenterTarget != 0) {
            for (int i = 0; i < 2; i++) {
                final int drawX = THEME_WIDGET_SLOT_SIZE * i + WIDGET_POSITION + screenWidthCenterTarget;
                if (isHoveredOver[i]) {
                    drawThemeWidgetBackground(guiGraphics, x + drawX, y + screenHeightCenterTarget);
                }
            }
        }
    }

    public void drawThemeWidgetBackground(GraphicsHolder guiGraphics, int positionX, int positionY) {
        int x = positionX + STANDARD_SIZE * 2;
        int width = 140;
        int height = 200;
        final GuiDrawing guiDrawing = new GuiDrawing(guiGraphics);
        guiDrawing.beginDrawingRectangle();
        guiDrawing.drawRectangle(x - BIG_SQUARE_SIZE - STANDARD_SIZE - HALF_SQUARE_SIZE, positionY - STANDARD_SIZE, x - BIG_SQUARE_SIZE - STANDARD_SIZE - HALF_SQUARE_SIZE + width, positionY - STANDARD_SIZE + height, ARGB_DARK_GRAY);
        guiDrawing.finishDrawingRectangle();
       //guiGraphics.fill(RenderType.guiOverlay(), x - BIG_SQUARE_SIZE - STANDARD_SIZE - HALF_SQUARE_SIZE, positionY - STANDARD_SIZE, x - BIG_SQUARE_SIZE - STANDARD_SIZE - HALF_SQUARE_SIZE + width, positionY - STANDARD_SIZE + height, ARGB_DARK_GRAY);
    }
    public void mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0) {
        for (int i = 0; i < 2; i++) {
            final int drawX = THEME_WIDGET_SLOT_SIZE * i + WIDGET_POSITION + screenWidthCenterTarget;
            if (mouseX >= x + drawX && mouseX < x + drawX + 140 && mouseY >= y + WIDGET_POSITION && mouseY < y + WIDGET_POSITION + THEME_WIDGET_SLOT_SIZE) {
                getUISkinExtraImage = UISkinExtraImage[i];
                getUIWidgetisLiquidUI = UIWidgetIsLiquidUI[i];
            }
            }
        }
    }

    public void mouseMoved(double mouseX, double mouseY) {
        for (int i = 0; i < 2; i++) {
            final int drawX = THEME_WIDGET_SLOT_SIZE * i + WIDGET_POSITION + screenWidthCenterTarget - 35;
            if (mouseX >= x + drawX && mouseX < x + drawX + 140 && mouseY >= y + 40 + WIDGET_POSITION && mouseY < y + 40 + WIDGET_POSITION + THEME_WIDGET_SLOT_SIZE) {
                isHoveredOver[i] = true;
            } else {
                isHoveredOver[i] = false;
            }
        }
    }
    public void setVisible(boolean visible) {
        this.isVisible = visible;
    }

    public static String getUISkinExtraImageEnum() {
        return getUISkinExtraImage;
    }

    public static Boolean getUIWidgetIsLiquidUIEnum() {
        return getUIWidgetisLiquidUI;
    }
}
