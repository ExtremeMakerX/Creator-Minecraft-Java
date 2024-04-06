package creatorminecraft.widgets;

import com.mojang.blaze3d.systems.RenderSystem;
import creatorminecraft.client.ThemeGUIEnum;
import creatorminecraft.gui.ThemeSelectorList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

import java.util.function.Supplier;

public class DynamicButtonCMJ extends Button {

    public static final CreateNarration DEFAULT_NARRATION = Supplier::get;
    public static final WidgetSprites SPRITES = new WidgetSprites(new ResourceLocation("widget/button"), new ResourceLocation("widget/button_disabled"), new ResourceLocation("widget/button_highlighted"));
    public static final WidgetSprites LIQUID_SPRITES = new WidgetSprites(new ResourceLocation("widget/liquid_ui_button"), new ResourceLocation("widget/liquid_ui_button_disabled"), new ResourceLocation("widget/liquid_ui_button_highlighted"));

    public DynamicButtonCMJ(int i, int j, int k, int l, Component component, OnPress onPress, CreateNarration createNarration) {
        super(i, j, k, l, component, onPress, createNarration);
    }

    protected void renderWidget(GuiGraphics guiGraphics, int i, int j, float f) {
        Minecraft minecraft = Minecraft.getInstance();
        guiGraphics.setColor(1.0F, 1.0F, 1.0F, this.alpha);
        RenderSystem.enableBlend();
        RenderSystem.enableDepthTest();
        if (ThemeSelectorList.getUIWidgetIsLiquidUIEnum()) {
            guiGraphics.blitSprite(LIQUID_SPRITES.get(this.active, this.isHoveredOrFocused()), this.getX(), this.getY(), this.getWidth(), this.getHeight());
        } else {
            guiGraphics.blitSprite(SPRITES.get(this.active, this.isHoveredOrFocused()), this.getX(), this.getY(), this.getWidth(), this.getHeight());
        }
        guiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
        int k = this.active ? 16777215 : 10526880;
        this.renderString(guiGraphics, minecraft.font, k | Mth.ceil(this.alpha * 255.0F) << 24);
    }
}
