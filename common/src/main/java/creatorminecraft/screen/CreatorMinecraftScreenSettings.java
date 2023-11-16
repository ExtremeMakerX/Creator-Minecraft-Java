package creatorminecraft.screen;

import creatorminecraft.client.ButtonSetter;
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

        ButtonSetter.setAdjustableButtonWidget(buttonThemeSettings, width / 2 - GIANT_SQUARE_SIZE * 2 - STANDARD_SIZE, yStart, width - GIANT_SQUARE_SIZE);
        ButtonSetter.setAdjustableButtonWidget(buttonKeyboardShortcuts, width / 2 - GIANT_SQUARE_SIZE * 2 - STANDARD_SIZE, yStart + STANDARD_SIZE, width - GIANT_SQUARE_SIZE);
        ButtonSetter.setAdjustableButtonWidget(buttonAddonPluginManager, width / 2 - GIANT_SQUARE_SIZE * 2 - STANDARD_SIZE, yStart + STANDARD_SIZE * 2, width - GIANT_SQUARE_SIZE);
        ButtonSetter.setAdjustableButtonWidget(buttonDymanicRegistryManager, width / 2 - GIANT_SQUARE_SIZE * 2 - STANDARD_SIZE, yStart + STANDARD_SIZE * 3, width - GIANT_SQUARE_SIZE);
        ButtonSetter.setAdjustableButtonWidget(buttonExit, 0, height - STANDARD_SIZE, width - SUPER_GIANT_SQUARE_SIZE * 4);

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
            renderBackground(guiGraphics);
            bufferSource.endBatch();
        }
        super.render(guiGraphics, i, j, f);
    }

    @Override
    public void onClose() {
        super.onClose();
        if (minecraft != null) {
            minecraft.options.hideGui = false;
        }
        creatorScreenManager.fileMenuState = 0;
        creatorScreenManager.editMenuState = 0;
        creatorScreenManager.viewMenuState = 0;
        creatorScreenManager.renderMenuState = 0;
        creatorScreenManager.windowMenuState = 0;
    }
}
