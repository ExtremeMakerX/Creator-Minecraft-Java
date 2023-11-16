package creatorminecraft.client;

import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.util.Mth;

public interface ButtonSetter {

    static void setAdjustableButtonWidget(AbstractWidget widget, int x, int y, int widgetWidth) {
        widget.setX(x);
        widget.setY(y);
        widget.setWidth(Mth.clamp(widgetWidth, 0, 380));
    }
}
