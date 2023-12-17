package org.creatorminecraft.mod.screen;

import org.creatorminecraft.mod.client.CreatorIGUI;
import org.creatorminecraft.mod.client.WidgetSetter;
import org.creatorminecraft.mod.gui.ThemeSelectorList;
import org.creatorminecraft.mod.widgets.DynamicButtonCMJ;
import org.mtr.mapping.holder.ClickableWidget;
import org.mtr.mapping.holder.MinecraftClient;
import org.mtr.mapping.holder.Screen;
import org.mtr.mapping.mapper.GraphicsHolder;
import org.mtr.mapping.mapper.ScreenExtension;
import org.mtr.mapping.mapper.TextHelper;

public class CreatorMinecraftThemeScreen extends ScreenExtension implements CreatorIGUI {
    private final CreatorMinecraftScreenSettings creatorMinecraftScreenSettings;
    private final ThemeSelectorList themeSelectorList;
    private final DynamicButtonCMJ buttonDone;
    protected CreatorMinecraftThemeScreen(CreatorMinecraftScreenSettings creatorMinecraftScreenSettings) {
        super();
        buttonDone = new DynamicButtonCMJ(0, 0, 0, STANDARD_SIZE, TextHelper.translatable("gui.done"), button -> onClose2());
        themeSelectorList = new ThemeSelectorList();
        this.creatorMinecraftScreenSettings = creatorMinecraftScreenSettings;
    }

    @Override
    protected void init2() {
        super.init2();
        WidgetSetter.setAdjustableButtonWidget(buttonDone, 0, height - STANDARD_SIZE, GIANT_SQUARE_SIZE * 2);
        themeSelectorList.setVisible(true);
        addChild(new ClickableWidget(buttonDone));
    }

    @Override
    public void render(GraphicsHolder guiGraphics, int i, int j, float f) {
        //guiGraphics.fillGradient(0, 0, this.width, this.height, -1072689136, -1072689136);
        renderBackground(guiGraphics);
        themeSelectorList.renderBackground(guiGraphics);
        themeSelectorList.render(guiGraphics);
        themeSelectorList.screenWidthCenterTarget = width / 2 - SUPER_GIANT_SQUARE_SIZE - BIG_SQUARE_SIZE;
        themeSelectorList.screenHeightCenterTarget = height / 2 - GIANT_SQUARE_SIZE;
        super.render(guiGraphics, i, j, f);
    }

    @Override
    public boolean mouseClicked2(double mouseX, double mouseY, int button) {
          themeSelectorList.mouseClicked(mouseX,mouseY,button);
        return super.mouseClicked2(mouseX, mouseY, button);
    }

    @Override
    public void mouseMoved2(double mouseX, double mouseY) {
            themeSelectorList.mouseMoved(mouseX, mouseY);
    }

    @Override
    public void onClose2() {
        final MinecraftClient minecraftClient = MinecraftClient.getInstance();
        minecraftClient.openScreen(new Screen(creatorMinecraftScreenSettings));
    }
}
