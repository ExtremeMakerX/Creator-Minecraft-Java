package creatorminecraft.gui;

import creatorminecraft.client.CreatorIGUI;
import creatorminecraft.client.ThemeGUIEnum;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

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
    private final Boolean[] UIWidgetisLiquidUI = Arrays.stream(ThemeGUIEnum.values()).map(ThemeGUIEnum::getExperimentWidgetLiquidUI).toArray(Boolean[]::new);
    private static String getUISkinExtraImage = "creatorminecraft:textures/gui/liquid_ui_black_bar.png";
    private static Boolean getUIWidgetisLiquidUI = true;
    private final int THEME_WIDGET_SLOT_SIZE = 160;
    private final int WIDGET_POSITION = THEME_WIDGET_SLOT_SIZE - BIG_SQUARE_SIZE;
    private boolean isVisible;
    private final boolean[] isHoveredOver = new boolean[2];

    public void render(GuiGraphics guiGraphics, Font textRenderer) {
        if (isVisible && screenWidthCenterTarget != 0) {
            for (int i = 0; i < 2; i++) {
                final int drawX = THEME_WIDGET_SLOT_SIZE * i + WIDGET_POSITION + screenWidthCenterTarget;
                drawThemeWidget(guiGraphics, textRenderer, x + drawX, y + screenHeightCenterTarget, i);
            }
        }
    }

    public void drawThemeWidget(GuiGraphics guiGraphics, Font textRenderer, int positionX, int positionY, int index) {
        int x = positionX + STANDARD_SIZE * 2;
        int width = 120;
        int height = 120;
        guiGraphics.drawCenteredString(textRenderer, "Name: " + nameUI[index], x, positionY + BIG_SQUARE_SIZE * 3, ARGB_WHITE);
        guiGraphics.drawCenteredString(textRenderer, "Author: " + authorName[index], x, positionY + BIG_SQUARE_SIZE * 3 + STANDARD_SIZE, ARGB_WHITE);
        guiGraphics.blit(new ResourceLocation(customImageThemeWidget[index]), x - BIG_SQUARE_SIZE - STANDARD_SIZE, positionY - 10, 0, 0, width, height, width, height);
    }

    public void renderBackground(GuiGraphics guiGraphics) {
        if (isVisible && screenWidthCenterTarget != 0) {
            for (int i = 0; i < 2; i++) {
                final int drawX = THEME_WIDGET_SLOT_SIZE * i + WIDGET_POSITION + screenWidthCenterTarget;
                if (isHoveredOver[i]) {
                    drawThemeWidgetBackground(guiGraphics, x + drawX, y + screenHeightCenterTarget);
                }
            }
        }
    }

    public void drawThemeWidgetBackground(GuiGraphics guiGraphics, int positionX, int positionY) {
        int x = positionX + STANDARD_SIZE * 2;
        int width = 140;
        int height = 200;
       guiGraphics.fill(RenderType.guiOverlay(), x - BIG_SQUARE_SIZE - STANDARD_SIZE - HALF_SQUARE_SIZE, positionY - STANDARD_SIZE, x - BIG_SQUARE_SIZE - STANDARD_SIZE - HALF_SQUARE_SIZE + width, positionY - STANDARD_SIZE + height, ARGB_DARK_GRAY);
    }
    public void mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0) {
        for (int i = 0; i < 2; i++) {
            final int drawX = THEME_WIDGET_SLOT_SIZE * i + WIDGET_POSITION + screenWidthCenterTarget;
            if (mouseX >= x + drawX && mouseX < x + drawX + 140 && mouseY >= y + WIDGET_POSITION && mouseY < y + WIDGET_POSITION + THEME_WIDGET_SLOT_SIZE) {
                getUISkinExtraImage = UISkinExtraImage[i];
                getUIWidgetisLiquidUI = UIWidgetisLiquidUI[i];
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
