package org.creatorminecraft.mod.widgets;


import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.ButtonWidgetExtension;

public class DynamicButtonCMJ extends ButtonWidgetExtension {

    public static final Identifier LIQUID_WIDGETS_LOCATION = new Identifier("creatorminecraft:textures/gui/widgets_liquid_ui.png");
    public static final Identifier WIDGETS_TEXTURE = new Identifier("textures/gui/widgets.png");

    public DynamicButtonCMJ(int x, int y, int width, int height, MutableText message, org.mtr.mapping.holder.PressAction onPress) {
        super(x, y, width, height, message, onPress);
    }

//TO_DO List Button Texture Widget Change
    /*@Override
    public void render(GraphicsHolder graphicsHolder, int mouseX, int mouseY, float delta) {

    }*/
}
