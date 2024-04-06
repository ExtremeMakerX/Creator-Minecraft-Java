package creatorminecraft.screen;

import creatorminecraft.client.WidgetSetter;
import creatorminecraft.client.CreatorIGUI;
import creatorminecraft.gui.ThemeSelectorList;
import creatorminecraft.widgets.DynamicButtonCMJ;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class CreatorMinecraftThemeScreen extends Screen implements CreatorIGUI {
    private final CreatorMinecraftScreenSettings creatorMinecraftScreenSettings;
    private final ThemeSelectorList themeSelectorList;
    private final DynamicButtonCMJ buttonDone;
    protected CreatorMinecraftThemeScreen(CreatorMinecraftScreenSettings creatorMinecraftScreenSettings) {
        super(Component.empty());
        buttonDone = new DynamicButtonCMJ(0, 0, 0, STANDARD_SIZE, Component.translatable("gui.done"), button -> onClose(), DynamicButtonCMJ.DEFAULT_NARRATION);
        themeSelectorList = new ThemeSelectorList();
        this.creatorMinecraftScreenSettings = creatorMinecraftScreenSettings;
    }

    @Override
    protected void init() {
        super.init();
        WidgetSetter.setAdjustableWidget(buttonDone, 0, height - STANDARD_SIZE, GIANT_SQUARE_SIZE * 2);
        themeSelectorList.setVisible(true);
        addRenderableWidget(buttonDone);
    }

    @Override
    public void renderBackground(GuiGraphics guiGraphics, int i, int j, float f) {

    }

    @Override
    public void render(GuiGraphics guiGraphics, int i, int j, float f) {
        guiGraphics.fillGradient(0, 0, this.width, this.height, -1072689136, -1072689136);
        themeSelectorList.renderBackground(guiGraphics);
        themeSelectorList.render(guiGraphics, font);
        themeSelectorList.screenWidthCenterTarget = width / 2 - SUPER_GIANT_SQUARE_SIZE - BIG_SQUARE_SIZE;
        themeSelectorList.screenHeightCenterTarget = height / 2 - GIANT_SQUARE_SIZE;
        super.render(guiGraphics, i, j, f);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
          themeSelectorList.mouseClicked(mouseX,mouseY,button);
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public void mouseMoved(double mouseX, double mouseY) {
            themeSelectorList.mouseMoved(mouseX, mouseY);
    }

    @Override
    public void onClose() {
        if (minecraft != null) {
            minecraft.setScreen(creatorMinecraftScreenSettings);
        }
    }
}
