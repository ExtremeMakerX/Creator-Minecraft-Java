package org.creatorminecraft.mod.client;

import org.mtr.mapping.holder.MathHelper;
import org.mtr.mapping.mapper.ButtonWidgetExtension;
import org.mtr.mapping.mapper.TextFieldWidgetExtension;
import org.mtr.mapping.mapper.TexturedButtonWidgetExtension;

public interface WidgetSetter {

    static void setAdjustableButtonWidget(ButtonWidgetExtension widget, int x, int y, int widgetWidth) {
        widget.setX2(x);
        widget.setY2(y);
        widget.setWidth2(MathHelper.clamp(widgetWidth, 0, 380));
    }

    static void setPositionAndTexturedButtonWidget(TexturedButtonWidgetExtension widget, int x, int y, int widgetWidth) {
        widget.setX2(x);
        widget.setY2(y);
        widget.setWidth2(MathHelper.clamp(widgetWidth, 0, 380));
    }

    static void setPositionAndTextFieldWidget(TextFieldWidgetExtension widget, int x, int y, int widgetWidth) {
        widget.setX2(x);
        widget.setY2(y);
        widget.setWidth2(MathHelper.clamp(widgetWidth, 0, 380));
    }
}
