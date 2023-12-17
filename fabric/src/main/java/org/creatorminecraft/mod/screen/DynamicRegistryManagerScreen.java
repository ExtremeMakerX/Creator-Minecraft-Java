package org.creatorminecraft.mod.screen;

import org.mtr.mapping.holder.MinecraftClient;
import org.mtr.mapping.mapper.GraphicsHolder;
import org.mtr.mapping.mapper.ScreenExtension;

public class DynamicRegistryManagerScreen extends ScreenExtension {
    private static int guiCounter;
    private static boolean hideGui;

    public DynamicRegistryManagerScreen() {
        super();
    }

    @Override
    protected void init2() {
    }

    @Override
    public void render(GraphicsHolder guiGraphics, int i, int j, float f) {
        super.render(guiGraphics, i, j, f);
        if (guiCounter == 0) {
            hideGui = MinecraftClient.getInstance().getOptionsMapped().getHudHiddenMapped();
        }
    }


    public static void render(GraphicsHolder matrices) {
        if (guiCounter > 0) {
            guiCounter--;
            MinecraftClient.getInstance().getOptionsMapped().setHudHiddenMapped(guiCounter != 0 || hideGui);
            matrices.push();
            matrices.pop();
        }
    }
}
