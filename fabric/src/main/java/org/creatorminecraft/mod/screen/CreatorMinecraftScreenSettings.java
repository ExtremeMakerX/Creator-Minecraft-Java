package org.creatorminecraft.mod.screen;

import org.creatorminecraft.mod.client.CreatorIGUI;
import org.creatorminecraft.mod.widgets.DynamicButtonCMJ;
import org.mtr.mapping.holder.ClickableWidget;
import org.mtr.mapping.holder.MinecraftClient;
import org.mtr.mapping.holder.Screen;
import org.mtr.mapping.mapper.GraphicsHolder;
import org.mtr.mapping.mapper.ScreenExtension;
import org.creatorminecraft.mod.client.WidgetSetter;
import org.mtr.mapping.mapper.TextHelper;

public class CreatorMinecraftScreenSettings extends ScreenExtension implements CreatorIGUI {

    private final DynamicButtonCMJ buttonThemeSettings;
    private final DynamicButtonCMJ buttonKeyboardShortcuts;
    private final DynamicButtonCMJ buttonAddonPluginManager;
    private final DynamicButtonCMJ buttonDymanicRegistryManager;
    private final DynamicButtonCMJ buttonExit;
    public static CreatorScreenManager creatorScreenManager = new CreatorScreenManager();
    public CreatorMinecraftScreenSettings() {
        super();
        buttonThemeSettings = new DynamicButtonCMJ(0, 0, 0, STANDARD_SIZE, TextHelper.translatable("gui.creatorminecraft.theme.settings"), button -> {
            final MinecraftClient minecraftClient = MinecraftClient.getInstance();
            minecraftClient.openScreen(new Screen(new CreatorMinecraftThemeScreen(this)));
        });

        buttonKeyboardShortcuts = new DynamicButtonCMJ(0, 0, 0, STANDARD_SIZE, TextHelper.translatable("gui.creatorminecraft.keyboard.shortcuts"), checked -> {
        });
        buttonAddonPluginManager = new DynamicButtonCMJ(0, 0, 0, STANDARD_SIZE, TextHelper.translatable("gui.creatorminecraft.addon.plugin.manager"), button -> {
        });

        buttonDymanicRegistryManager = new DynamicButtonCMJ(0, 0, 0, STANDARD_SIZE, TextHelper.translatable("gui.creatorminecraft.dynamic.registry.manager"), checked -> {
        });
        buttonExit = new DynamicButtonCMJ(0, 0, 0, STANDARD_SIZE, TextHelper.translatable("gui.creatorminecraft.exit.creator.settings"), checked -> {
            final MinecraftClient minecraftClient = MinecraftClient.getInstance();
            minecraftClient.openScreen(new Screen(new CreatorMinecraftScreen()));
        });
    }

    @Override
    protected void init2() {
        super.init2();
        final int yStart = (height - STANDARD_SIZE * 10 - 16) / 2;

        WidgetSetter.setAdjustableButtonWidget(buttonThemeSettings, width / 2 - GIANT_SQUARE_SIZE * 2 - STANDARD_SIZE, yStart, width - GIANT_SQUARE_SIZE);
        WidgetSetter.setAdjustableButtonWidget(buttonKeyboardShortcuts, width / 2 - GIANT_SQUARE_SIZE * 2 - STANDARD_SIZE, yStart + STANDARD_SIZE, width - GIANT_SQUARE_SIZE);
        WidgetSetter.setAdjustableButtonWidget(buttonAddonPluginManager, width / 2 - GIANT_SQUARE_SIZE * 2 - STANDARD_SIZE, yStart + STANDARD_SIZE * 2, width - GIANT_SQUARE_SIZE);
        WidgetSetter.setAdjustableButtonWidget(buttonDymanicRegistryManager, width / 2 - GIANT_SQUARE_SIZE * 2 - STANDARD_SIZE, yStart + STANDARD_SIZE * 3, width - GIANT_SQUARE_SIZE);
        WidgetSetter.setAdjustableButtonWidget(buttonExit, 0, height - STANDARD_SIZE, GIANT_SQUARE_SIZE * 2);

        addChild(new ClickableWidget(buttonThemeSettings));
        //addRenderableWidget(buttonKeyboardShortcuts);
       //addRenderableWidget(buttonAddonPluginManager);
        //addRenderableWidget(buttonDymanicRegistryManager);
        addChild(new ClickableWidget(buttonExit));
    }

    @Override
    public void render(GraphicsHolder guiGraphics, int i, int j, float f) {
        renderBackground(guiGraphics);
        MinecraftClient.getInstance().getOptionsMapped().setHudHiddenMapped(true);
        //guiGraphics.fillGradient(0, 0, this.width, this.height, -1072689136, -1072689136);
        super.render(guiGraphics, i, j, f);
    }

    @Override
    public void onClose2() {
        super.onClose2();
        final MinecraftClient minecraftClient = MinecraftClient.getInstance();
        minecraftClient.openScreen(new Screen(new CreatorMinecraftScreen()));
        CreatorMinecraftScreen.GUI_RENDER_GRAPHICS = false;
        creatorScreenManager.fileMenuState = 0;
        creatorScreenManager.editMenuState = 0;
        creatorScreenManager.viewMenuState = 0;
        creatorScreenManager.renderMenuState = 0;
        creatorScreenManager.windowMenuState = 0;
    }
}
