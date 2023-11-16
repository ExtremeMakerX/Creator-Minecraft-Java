package creatorminecraft.screen;

import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import creatorminecraft.client.ButtonSetter;
import creatorminecraft.client.CreatorIGUI;
import creatorminecraft.model.GridModel;
import creatorminecraft.model.ModelXZPlane;
import creatorminecraft.render.GraphicsRender;
import creatorminecraft.widgets.DynamicButtonCMJ;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;

public class CreatorMinecraftScreen extends Screen implements CreatorIGUI {
;
    private final DynamicButtonCMJ buttonFileSettings;
    private final DynamicButtonCMJ buttonEditSettings;
    private final DynamicButtonCMJ buttonViewerSettings;
    private final DynamicButtonCMJ buttonRenderSettings;
    private final DynamicButtonCMJ buttonWindowSettings;
    private final DynamicButtonCMJ buttonSettings;
    public static CreatorScreenManager creatorScreenManager = new CreatorScreenManager();
    public static float cameraYaw;
    public static float cameraPitch;
    public static double cameraX;
    public static double cameraY;
    public static double cameraZ;
    public static boolean GUI_RENDER_GRAPHICS;

    public CreatorMinecraftScreen() {
        super(Component.empty());
        buttonFileSettings = new DynamicButtonCMJ(0, 0, 0, 20, Component.literal("File"), button -> creatorScreenManager.onFileMenu(), DynamicButtonCMJ.DEFAULT_NARRATION);
        buttonEditSettings = new DynamicButtonCMJ(0, 0, 0, 20, Component.literal("Edit"), button -> creatorScreenManager.onEditMenu(), DynamicButtonCMJ.DEFAULT_NARRATION);
        buttonViewerSettings = new DynamicButtonCMJ(0, 0, 0, 20, Component.literal("Viewer"), button -> creatorScreenManager.onViewMenu(), DynamicButtonCMJ.DEFAULT_NARRATION);
        buttonRenderSettings = new DynamicButtonCMJ(0, 0, 0, 20, Component.literal("Render"), button -> creatorScreenManager.onRenderMenu(), DynamicButtonCMJ.DEFAULT_NARRATION);
        buttonWindowSettings = new DynamicButtonCMJ(0, 0, 0, 20, Component.literal("Window"), button -> creatorScreenManager.onWindowMenu(), DynamicButtonCMJ.DEFAULT_NARRATION);
        buttonSettings = new DynamicButtonCMJ(0, 0, 0, 20, Component.literal("Settings"), button -> {
            if (minecraft != null) {
                minecraft.setScreen(new CreatorMinecraftScreenSettings());
            }
        }, DynamicButtonCMJ.DEFAULT_NARRATION);
        new CreatorScreenManager();
    }

    @Override
    protected void init() {
        final int standardMenuWidth = width - 288 * 2 - 8 * 2;
        int adaptToNewUIInt = 0;
        cameraYaw = 90;
        cameraPitch = 220;
        cameraX = 0;
        cameraY = 0;
        cameraZ = 15;
        ButtonSetter.setAdjustableButtonWidget(buttonFileSettings, adaptToNewUIInt, 0, standardMenuWidth / 4);
        ButtonSetter.setAdjustableButtonWidget(buttonEditSettings, adaptToNewUIInt + standardMenuWidth / 4, 0, standardMenuWidth / 4);
        ButtonSetter.setAdjustableButtonWidget(buttonViewerSettings, adaptToNewUIInt + standardMenuWidth / 4 * 2, 0, standardMenuWidth / 4);
        ButtonSetter.setAdjustableButtonWidget(buttonRenderSettings, adaptToNewUIInt + standardMenuWidth / 4 * 3, 0, standardMenuWidth / 4);
        ButtonSetter.setAdjustableButtonWidget(buttonWindowSettings, adaptToNewUIInt + standardMenuWidth / 4 * 4, 0, standardMenuWidth / 4);
        ButtonSetter.setAdjustableButtonWidget(buttonSettings, adaptToNewUIInt + standardMenuWidth / 4 * 5, 0, standardMenuWidth / 4);

        ButtonSetter.setAdjustableButtonWidget(creatorScreenManager.buttonNewProject, 0, STANDARD_SIZE, standardMenuWidth / 4);
        ButtonSetter.setAdjustableButtonWidget(creatorScreenManager.buttonSaveProject, 0, STANDARD_SIZE * 2, standardMenuWidth / 4);
        ButtonSetter.setAdjustableButtonWidget(creatorScreenManager.buttonSaveAsProject, 0, STANDARD_SIZE * 3, standardMenuWidth / 4);

        ButtonSetter.setAdjustableButtonWidget(creatorScreenManager.buttonModelEditGui, standardMenuWidth / 4, STANDARD_SIZE, standardMenuWidth / 4);
        ButtonSetter.setAdjustableButtonWidget(creatorScreenManager.buttonBlockbenchMode, standardMenuWidth / 4, STANDARD_SIZE * 2, standardMenuWidth / 4);

        ButtonSetter.setAdjustableButtonWidget(creatorScreenManager.buttonCameraReset, standardMenuWidth / 4 * 2, STANDARD_SIZE, standardMenuWidth / 4);
        ButtonSetter.setAdjustableButtonWidget(creatorScreenManager.buttonScreenshotMode, standardMenuWidth / 4 * 2, STANDARD_SIZE * 2, standardMenuWidth / 4);
        ButtonSetter.setAdjustableButtonWidget(creatorScreenManager.buttonPlayerCamera, standardMenuWidth / 4 * 2, STANDARD_SIZE * 3, standardMenuWidth / 4);
        //ButtonSetter.setPositionAndWidth(creatorScreenManager.buttonExitScreenshotMode, standardMenuWidth / 4 * 2, STANDARD_SIZE * 4, standardMenuWidth / 4);

        ButtonSetter.setAdjustableButtonWidget(creatorScreenManager.buttonShaderRender, standardMenuWidth / 4 * 3, STANDARD_SIZE, standardMenuWidth / 4);
        ButtonSetter.setAdjustableButtonWidget(creatorScreenManager.buttonColorBlendRender, standardMenuWidth / 4 * 3, STANDARD_SIZE * 2, standardMenuWidth / 4);

        ButtonSetter.setAdjustableButtonWidget(creatorScreenManager.buttonCameraPresets, standardMenuWidth / 4 * 4, STANDARD_SIZE, standardMenuWidth / 4);

        addRenderableWidget(buttonFileSettings);
        addRenderableWidget(buttonEditSettings);
        addRenderableWidget(buttonViewerSettings);
        addRenderableWidget(buttonRenderSettings);
        addRenderableWidget(buttonWindowSettings);
        addRenderableWidget(buttonSettings);

        addRenderableWidget(creatorScreenManager.buttonNewProject);
        addRenderableWidget(creatorScreenManager.buttonSaveProject);
        addRenderableWidget(creatorScreenManager.buttonSaveAsProject);

        addRenderableWidget(creatorScreenManager.buttonModelEditGui);
        addRenderableWidget(creatorScreenManager.buttonBlockbenchMode);

        addRenderableWidget(creatorScreenManager.buttonCameraReset);
        addRenderableWidget(creatorScreenManager.buttonScreenshotMode);
        addRenderableWidget(creatorScreenManager.buttonPlayerCamera);
        addRenderableWidget(creatorScreenManager.buttonExitScreenshotMode);

        addRenderableWidget(creatorScreenManager.buttonShaderRender);
        addRenderableWidget(creatorScreenManager.buttonColorBlendRender);

        addRenderableWidget(creatorScreenManager.buttonCameraPresets);
        if (minecraft != null) {
            GUI_RENDER_GRAPHICS = true;
        }
    }

    @Override
    public boolean mouseDragged(double d, double e, int i, double f, double g) {
        if (i == 0) {
            cameraYaw -= (float) f * 0.5f;
            cameraPitch += (float) g * 0.55f;
        }
        if (i == 1) {
            cameraX -= (float) f * 0.1f;
            cameraY += (float) g * 0.1f;
        }
        return super.mouseDragged(d, e, i, f, g);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        double sensitivity = 1.5;
        cameraZ -= amount * sensitivity;
        cameraZ = Math.max(cameraZ, -50.1f);
        cameraZ = Math.min(cameraZ, 100.0f);
        return super.mouseScrolled(mouseX, mouseY, amount);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int i, int j, float f) {
        final Minecraft minecraft = Minecraft.getInstance();
        minecraft.options.hideGui = true;
        super.render(guiGraphics, i, j, f);
    }



    public static void render(PoseStack poseStack) {
        if (GUI_RENDER_GRAPHICS) {
            final Minecraft minecraft = Minecraft.getInstance();
            final MultiBufferSource.BufferSource bufferSource = minecraft.renderBuffers().bufferSource();
            renderGraphicsBackground(poseStack, bufferSource);
            bufferSource.endBatch();
            poseStack.pushPose();
            GridModel gridModel = new GridModel();
            poseStack.translate(-cameraX, -cameraY, -cameraZ);
            poseStack.mulPose(Axis.XP.rotationDegrees(cameraPitch));
            poseStack.mulPose(Axis.YP.rotationDegrees(cameraYaw));
            poseStack.mulPose(Axis.ZP.rotationDegrees((0)));
            poseStack.pushPose();
            poseStack.translate(0, 0.1f, 0.5);
            gridModel.render(poseStack, bufferSource, 0xF400F0, 0);
            poseStack.popPose();
            ModelXZPlane modelXZPlane = new ModelXZPlane();
            poseStack.pushPose();
            poseStack.translate(0, 1.08f, 0.5);
            modelXZPlane.render(poseStack, bufferSource, 0xF000F0, 0);
            poseStack.popPose();
            poseStack.popPose();
            bufferSource.endBatch();
        }
    }

    public static void renderGraphicsBackground(PoseStack poseStack, MultiBufferSource.BufferSource bufferSource) {
        GraphicsRender.graphicsBackgroundDraw(poseStack, bufferSource.getBuffer(RenderType.gui()), 0xFF000000 | 0xFF4D4D4D, 0xF000F0);
    }

    @Override
    public boolean keyPressed(int i, int j, int k) {
        return super.keyPressed(i, j, k);
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void onClose() {
        super.onClose();
        if (minecraft != null) {
            minecraft.options.hideGui = false;
            GUI_RENDER_GRAPHICS = false;
        }
        creatorScreenManager.fileMenuState = 0;
        creatorScreenManager.editMenuState = 0;
        creatorScreenManager.viewMenuState = 0;
        creatorScreenManager.renderMenuState = 0;
        creatorScreenManager.windowMenuState = 0;
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
