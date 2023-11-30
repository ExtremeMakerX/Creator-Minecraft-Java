package creatorminecraft.client;

public enum ThemeGUIEnum implements CreatorIGUI {
    LIQUID_UI("Liquid UI V1", "ExtremeMakerX", "creatorminecraft:textures/gui/liquid_ui_black_bar.png", "creatorminecraft:textures/gui/liquid_ui_v1_image.png", ARGB_DARK_GRAY, ""),
    MINECRAFT_UI("Minecraft UI", "ExtremeMakerX", "", "creatorminecraft:textures/gui/minecraft_v1_image.png", ARGB_GRAY, "");

    public final String UI_NAME;
    public final String AUTHOR;
    public final String UI_SKIN;
    public final String CUSTOM_IMAGE_THEME_WIDGET;
    public final int UI_BASE_COLOR;
    public final String UI_SKIN_EXTRA_IMAGE;
    ThemeGUIEnum(
        String UI_NAME,
        String AUTHOR,
        String UI_SKIN,
        String CUSTOM_IMAGE_THEME_WIDGET,
        int UI_BASE_COLOR,
        String UI_SKIN_EXTRA_IMAGE
     ) {
            this.UI_NAME = UI_NAME;
            this.AUTHOR = AUTHOR;
            this.UI_SKIN = UI_SKIN;
            this.CUSTOM_IMAGE_THEME_WIDGET = CUSTOM_IMAGE_THEME_WIDGET;
            this.UI_BASE_COLOR = UI_BASE_COLOR;
            this.UI_SKIN_EXTRA_IMAGE = UI_SKIN_EXTRA_IMAGE;
        }

    public ThemeGUIEnum[] getThemeGUIEnum() {
        return ThemeGUIEnum.values();
    }
    public String getNameUI() {
        return UI_NAME;
    }
    public String getAuthorName() {
        return AUTHOR;
    }
    public String getUISkin() {
        return UI_SKIN;
    }
    public String getCustomImageThemeWidget() {
        return CUSTOM_IMAGE_THEME_WIDGET;
    }
    public int getUIBaseColor() {
        return UI_BASE_COLOR;
    }
    public String getUISkinExtraImage() {
        return UI_SKIN_EXTRA_IMAGE;
    }
}
