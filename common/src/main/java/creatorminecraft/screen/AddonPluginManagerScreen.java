package creatorminecraft.screen;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.network.chat.Component;

public class AddonPluginManagerScreen extends Screen {
    private static int guiCounter;
    private static boolean hideGui;

    public AddonPluginManagerScreen(Component component) {
        super(component);
    }

    @Override
    protected void init() {
    }

    @Override
    public void render(GuiGraphics guiGraphics, int i, int j, float f) {
        super.render(guiGraphics, i, j, f);
        if (guiCounter == 0 && minecraft != null) {
            hideGui = minecraft.options.hideGui;
        }
    }


    public static void render(PoseStack matrices) {
        if (guiCounter > 0) {
            guiCounter--;
            final Minecraft minecraft = Minecraft.getInstance();
            minecraft.options.hideGui = guiCounter != 0 || hideGui;
            matrices.pushPose();
            final MultiBufferSource.BufferSource immediate = minecraft.renderBuffers().bufferSource();
            immediate.endBatch();
            matrices.popPose();
        }
    }
}
