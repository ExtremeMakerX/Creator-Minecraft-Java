package creatorminecraft.screen;

import creatorminecraft.client.WidgetSetter;
import creatorminecraft.client.CreatorIGUI;
import creatorminecraft.widgets.DynamicButtonCMJ;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.network.chat.Component;

public class CreatorMinecraftScreenSettings extends Screen implements CreatorIGUI {

    private final DynamicButtonCMJ buttonThemeSettings;
    private final DynamicButtonCMJ buttonKeyboardShortcuts;
    private final DynamicButtonCMJ buttonAddonPluginManager;
    private final DynamicButtonCMJ buttonDymanicRegistryManager;
    private final DynamicButtonCMJ buttonExit;
    public static CreatorScreenManager creatorScreenManager = new CreatorScreenManager();
    public CreatorMinecraftScreenSettings() {
        super(Component.empty());
        buttonThemeSettings = new DynamicButtonCMJ(0, 0, 0, STANDARD_SIZE, Component.translatable("gui.creatorminecraft.theme.settings"), button -> {
            if (minecraft != null) {
                minecraft.setScreen(new CreatorMinecraftThemeScreen(this));
            }
        }, DynamicButtonCMJ.DEFAULT_NARRATION);

        buttonKeyboardShortcuts = new DynamicButtonCMJ(0, 0, 0, STANDARD_SIZE, Component.translatable("gui.creatorminecraft.keyboard.shortcuts"), checked -> {
        }, DynamicButtonCMJ.DEFAULT_NARRATION);
        buttonAddonPluginManager = new DynamicButtonCMJ(0, 0, 0, STANDARD_SIZE, Component.translatable("gui.creatorminecraft.addon.plugin.manager"), button -> {
        }, DynamicButtonCMJ.DEFAULT_NARRATION);

        buttonDymanicRegistryManager = new DynamicButtonCMJ(0, 0, 0, STANDARD_SIZE, Component.translatable("gui.creatorminecraft.dynamic.registry.manager"), checked -> {
        }, DynamicButtonCMJ.DEFAULT_NARRATION);
        buttonExit = new DynamicButtonCMJ(0, 0, 0, STANDARD_SIZE, Component.translatable("gui.creatorminecraft.exit.creator.settings"), checked -> {
            if (minecraft != null) {
                minecraft.setScreen(new CreatorMinecraftScreen());
            }
        }, DynamicButtonCMJ.DEFAULT_NARRATION);
    }

    @Override
    protected void init() {
        super.init();
        final int yStart = (height - STANDARD_SIZE * 10 - 16) / 2;

        WidgetSetter.setAdjustableWidget(buttonThemeSettings, width / 2 - GIANT_SQUARE_SIZE * 2 - STANDARD_SIZE, yStart, width - GIANT_SQUARE_SIZE);
        WidgetSetter.setAdjustableWidget(buttonKeyboardShortcuts, width / 2 - GIANT_SQUARE_SIZE * 2 - STANDARD_SIZE, yStart + STANDARD_SIZE, width - GIANT_SQUARE_SIZE);
        WidgetSetter.setAdjustableWidget(buttonAddonPluginManager, width / 2 - GIANT_SQUARE_SIZE * 2 - STANDARD_SIZE, yStart + STANDARD_SIZE * 2, width - GIANT_SQUARE_SIZE);
        WidgetSetter.setAdjustableWidget(buttonDymanicRegistryManager, width / 2 - GIANT_SQUARE_SIZE * 2 - STANDARD_SIZE, yStart + STANDARD_SIZE * 3, width - GIANT_SQUARE_SIZE);
        WidgetSetter.setAdjustableWidget(buttonExit, 0, height - STANDARD_SIZE, GIANT_SQUARE_SIZE * 2);

        addRenderableWidget(buttonThemeSettings);
        addRenderableWidget(buttonKeyboardShortcuts);
        addRenderableWidget(buttonAddonPluginManager);
        addRenderableWidget(buttonDymanicRegistryManager);
        addRenderableWidget(buttonExit);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int i, int j, float f) {
        if (minecraft != null) {
            final MultiBufferSource.BufferSource bufferSource = minecraft.renderBuffers().bufferSource();
            minecraft.options.hideGui = true;
            guiGraphics.fillGradient(0, 0, this.width, this.height, -1072689136, -1072689136);
            bufferSource.endBatch();
        }
        super.render(guiGraphics, i, j, f);
    }

    @Override
    public void onClose() {
        super.onClose();
        if (minecraft != null) {
            minecraft.options.hideGui = false;
            CreatorMinecraftScreen.GUI_RENDER_GRAPHICS = false;
        }
        creatorScreenManager.fileMenuState = 0;
        creatorScreenManager.editMenuState = 0;
        creatorScreenManager.viewMenuState = 0;
        creatorScreenManager.renderMenuState = 0;
        creatorScreenManager.windowMenuState = 0;
    }
}
