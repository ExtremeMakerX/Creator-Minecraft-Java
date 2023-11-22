package creatorminecraft.screen;

import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import creatorminecraft.client.WidgetSetter;
import creatorminecraft.client.CreatorIGUI;
import creatorminecraft.gui.CreatorMinecraftEditBox;
import creatorminecraft.gui.ModelOutlinerList;
import creatorminecraft.model.DynamicEntityModelEditing;
import creatorminecraft.model.GridModel;
import creatorminecraft.model.ModelXZPlane;
import creatorminecraft.render.GraphicsRender;
import creatorminecraft.widgets.DynamicButtonCMJ;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.lwjgl.glfw.GLFW;

public class CreatorMinecraftScreen extends Screen implements CreatorIGUI {
;
    private final DynamicButtonCMJ buttonFileSettings;
    private final DynamicButtonCMJ buttonEditSettings;
    private final DynamicButtonCMJ buttonViewerSettings;
    private final DynamicButtonCMJ buttonRenderSettings;
    private final DynamicButtonCMJ buttonWindowSettings;
    private final DynamicButtonCMJ buttonSettings;
    public CreatorScreenManager creatorScreenManager = new CreatorScreenManager();
    private final CreatorMinecraftScreenWindows creatorMinecraftScreenWindows = new CreatorMinecraftScreenWindows();
    private final ModelOutlinerList modelOutlinerList = new ModelOutlinerList();
    public static float cameraYaw;
    public static float cameraPitch;
    public static double cameraX;
    public static double cameraY;
    public static double cameraZ;
    public static boolean GUI_RENDER_GRAPHICS;
    public static boolean movePositionScreenMode;
    public static boolean resizeScreenMode;
    public static boolean rotateScreenMode;
    public static boolean pivotScreenMode;
    public static boolean inflateScreenMode;
    private static float positionXScreenValue;
    private static float positionYScreenValue;
    private static float positionZScreenValue;
    private static float sizeXScreenValue;
    private static float sizeYScreenValue;
    private static float sizeZScreenValue;
    private static float pivotXScreenValue;
    private static float pivotYScreenValue;
    private static float pivotZScreenValue;
    private static float rotationXScreenValue;
    private static float rotationYScreenValue;
    private static float rotationZScreenValue;
    private static float inflateScreenValue;
    public final CreatorMinecraftEditBox textFieldPosX;
    public final CreatorMinecraftEditBox textFieldPosY;
    public final CreatorMinecraftEditBox textFieldPosZ;
    public final CreatorMinecraftEditBox textFieldSizeX;
    public final CreatorMinecraftEditBox textFieldSizeY;
    public final CreatorMinecraftEditBox textFieldSizeZ;
    public final CreatorMinecraftEditBox textFieldInflate;
    public final CreatorMinecraftEditBox textFieldPivotX;
    public final CreatorMinecraftEditBox textFieldPivotY;
    public final CreatorMinecraftEditBox textFieldPivotZ;
    public final CreatorMinecraftEditBox textFieldRotationX;
    public final CreatorMinecraftEditBox textFieldRotationY;
    public final CreatorMinecraftEditBox textFieldRotationZ;
    public final ImageButton buttonCreateModelIcon;
    public final ImageButton buttonCreateFolderIcon;
    private final ImageButton buttonExitModelEditor;
    public DynamicEntityModelEditing dynamicEntityModelEditing = new DynamicEntityModelEditing();
    public static boolean isModelEditGui;
    
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
        textFieldPosX = new CreatorMinecraftEditBox();
        textFieldPosY = new CreatorMinecraftEditBox();
        textFieldPosZ = new CreatorMinecraftEditBox();
        textFieldSizeX = new CreatorMinecraftEditBox();
        textFieldSizeY = new CreatorMinecraftEditBox();
        textFieldSizeZ = new CreatorMinecraftEditBox();
        textFieldInflate = new CreatorMinecraftEditBox();
        textFieldPivotX = new CreatorMinecraftEditBox();
        textFieldPivotY = new CreatorMinecraftEditBox();
        textFieldPivotZ = new CreatorMinecraftEditBox();
        textFieldRotationX = new CreatorMinecraftEditBox();
        textFieldRotationY = new CreatorMinecraftEditBox();
        textFieldRotationZ = new CreatorMinecraftEditBox();
        buttonExitModelEditor = new ImageButton(0, 0, 0, STANDARD_SIZE, 0, 0, 20, new ResourceLocation("creatorminecraft:textures/gui/icon_exit.png"), 20, 40, button -> {
            isModelEditGui = false;
        });
        buttonCreateModelIcon = new ImageButton(0, 0, 0, STANDARD_SIZE, 0, 0, 20, new ResourceLocation("creatorminecraft:textures/gui/icon_add_cube.png"), 20, 40, button -> {
            dynamicEntityModelEditing.addModelSlot();
        });
        buttonCreateFolderIcon = new ImageButton(0, 0, 0, STANDARD_SIZE, 0, 0, 20, new ResourceLocation("creatorminecraft:textures/gui/icon_add_folder.png"), 20, 40, button -> {
        });
        new CreatorScreenManager();
    }

    @Override
    protected void init() {
        final int rightSideX = width - BIG_SQUARE_SIZE * 4;
        final int standardMenuWidth = width / 3 + BIG_SQUARE_SIZE;
        int adaptToNewUIInt = 0;
        cameraYaw = 90;
        cameraPitch = 220;
        cameraX = 0;
        cameraY = 0;
        cameraZ = 17.5;
        onModelEditGui(false);
        WidgetSetter.setAdjustableWidget(buttonFileSettings, adaptToNewUIInt, 0, standardMenuWidth / 4);
        WidgetSetter.setAdjustableWidget(buttonEditSettings, adaptToNewUIInt + standardMenuWidth / 4, 0, standardMenuWidth / 4);
        WidgetSetter.setAdjustableWidget(buttonViewerSettings, adaptToNewUIInt + standardMenuWidth / 4 * 2, 0, standardMenuWidth / 4);
        WidgetSetter.setAdjustableWidget(buttonRenderSettings, adaptToNewUIInt + standardMenuWidth / 4 * 3, 0, standardMenuWidth / 4);
        WidgetSetter.setAdjustableWidget(buttonWindowSettings, adaptToNewUIInt + standardMenuWidth / 4 * 4, 0, standardMenuWidth / 4);
        WidgetSetter.setAdjustableWidget(buttonSettings, adaptToNewUIInt + standardMenuWidth / 4 * 5, 0, standardMenuWidth / 4);

        WidgetSetter.setAdjustableWidget(creatorScreenManager.buttonNewProject, 0, STANDARD_SIZE, standardMenuWidth / 4);
        WidgetSetter.setAdjustableWidget(creatorScreenManager.buttonSaveProject, 0, STANDARD_SIZE * 2, standardMenuWidth / 4);
        WidgetSetter.setAdjustableWidget(creatorScreenManager.buttonSaveAsProject, 0, STANDARD_SIZE * 3, standardMenuWidth / 4);

        WidgetSetter.setAdjustableWidget(creatorScreenManager.buttonModelEditGui, standardMenuWidth / 4, STANDARD_SIZE, standardMenuWidth / 4);
        WidgetSetter.setAdjustableWidget(creatorScreenManager.buttonBlockbenchMode, standardMenuWidth / 4, STANDARD_SIZE * 2, standardMenuWidth / 4);

        WidgetSetter.setAdjustableWidget(creatorScreenManager.buttonCameraReset, standardMenuWidth / 4 * 2, STANDARD_SIZE, standardMenuWidth / 4);
        WidgetSetter.setAdjustableWidget(creatorScreenManager.buttonScreenshotMode, standardMenuWidth / 4 * 2, STANDARD_SIZE * 2, standardMenuWidth / 4);
        WidgetSetter.setAdjustableWidget(creatorScreenManager.buttonPlayerCamera, standardMenuWidth / 4 * 2, STANDARD_SIZE * 3, standardMenuWidth / 4);
        //ButtonSetter.setPositionAndWidth(creatorScreenManager.buttonExitScreenshotMode, standardMenuWidth / 4 * 2, STANDARD_SIZE * 4, standardMenuWidth / 4);

        WidgetSetter.setAdjustableWidget(creatorScreenManager.buttonShaderRender, standardMenuWidth / 4 * 3, STANDARD_SIZE, standardMenuWidth / 4);
        WidgetSetter.setAdjustableWidget(creatorScreenManager.buttonColorBlendRender, standardMenuWidth / 4 * 3, STANDARD_SIZE * 2, standardMenuWidth / 4);

        WidgetSetter.setAdjustableWidget(creatorScreenManager.buttonCameraPresets, standardMenuWidth / 4 * 4, STANDARD_SIZE, standardMenuWidth / 4);

        WidgetSetter.setAdjustableWidget(textFieldPosX, rightSideX + BIG_SQUARE_SIZE * 3 + STANDARD_SIZE - STANDARD_SIZE * 2 * 3, STANDARD_SIZE * 3, STANDARD_SIZE * 2);
        WidgetSetter.setAdjustableWidget(textFieldPosY, rightSideX + BIG_SQUARE_SIZE * 3 + STANDARD_SIZE - STANDARD_SIZE * 2 * 2, STANDARD_SIZE * 3, STANDARD_SIZE * 2);
        WidgetSetter.setAdjustableWidget(textFieldPosZ, rightSideX + BIG_SQUARE_SIZE * 3 + STANDARD_SIZE - STANDARD_SIZE * 2, STANDARD_SIZE * 3, STANDARD_SIZE * 2);
        WidgetSetter.setAdjustableWidget(textFieldSizeX, rightSideX + BIG_SQUARE_SIZE * 3 + STANDARD_SIZE - STANDARD_SIZE * 2 * 3, STANDARD_SIZE * 4, STANDARD_SIZE * 2);
        WidgetSetter.setAdjustableWidget(textFieldSizeY, rightSideX + BIG_SQUARE_SIZE * 3 + STANDARD_SIZE - STANDARD_SIZE * 2 * 2, STANDARD_SIZE * 4, STANDARD_SIZE * 2);
        WidgetSetter.setAdjustableWidget(textFieldSizeZ, rightSideX + BIG_SQUARE_SIZE * 3 + STANDARD_SIZE - STANDARD_SIZE * 2, STANDARD_SIZE * 4, STANDARD_SIZE * 2);
        WidgetSetter.setAdjustableWidget(textFieldPivotX, rightSideX + BIG_SQUARE_SIZE * 3 + STANDARD_SIZE - STANDARD_SIZE * 2 * 3, STANDARD_SIZE * 5, STANDARD_SIZE * 2);
        WidgetSetter.setAdjustableWidget(textFieldPivotY, rightSideX + BIG_SQUARE_SIZE * 3 + STANDARD_SIZE - STANDARD_SIZE * 2 * 2, STANDARD_SIZE * 5, STANDARD_SIZE * 2);
        WidgetSetter.setAdjustableWidget(textFieldPivotZ, rightSideX + BIG_SQUARE_SIZE * 3 + STANDARD_SIZE - STANDARD_SIZE * 2, STANDARD_SIZE * 5, STANDARD_SIZE * 2);
        WidgetSetter.setAdjustableWidget(textFieldRotationX, rightSideX + BIG_SQUARE_SIZE * 3 + STANDARD_SIZE - STANDARD_SIZE * 2 * 3, STANDARD_SIZE * 6, STANDARD_SIZE * 2);
        WidgetSetter.setAdjustableWidget(textFieldRotationY, rightSideX + BIG_SQUARE_SIZE * 3 + STANDARD_SIZE - STANDARD_SIZE * 2 * 2, STANDARD_SIZE * 6, STANDARD_SIZE * 2);
        WidgetSetter.setAdjustableWidget(textFieldRotationZ, rightSideX + BIG_SQUARE_SIZE * 3 + STANDARD_SIZE - STANDARD_SIZE * 2, STANDARD_SIZE * 6, STANDARD_SIZE * 2);
        WidgetSetter.setAdjustableWidget(textFieldInflate, rightSideX + BIG_SQUARE_SIZE * 3 + STANDARD_SIZE - STANDARD_SIZE * 2 * 3, STANDARD_SIZE * 7, STANDARD_SIZE * 2);

        WidgetSetter.setAdjustableWidget(buttonExitModelEditor, rightSideX + BIG_SQUARE_SIZE * 3, STANDARD_SIZE + 5, STANDARD_SIZE);

        WidgetSetter.setAdjustableWidget(buttonCreateModelIcon, rightSideX + BIG_SQUARE_SIZE * 3 + STANDARD_SIZE - STANDARD_SIZE * 2 * 3 + STANDARD_SIZE + 2 - 30, STANDARD_SIZE * 8 + 6, STANDARD_SIZE);
        WidgetSetter.setAdjustableWidget(buttonCreateFolderIcon, rightSideX + BIG_SQUARE_SIZE * 3 + STANDARD_SIZE - STANDARD_SIZE * 2 * 3 - 30, STANDARD_SIZE * 8 + 6, STANDARD_SIZE);

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

        addRenderableWidget(textFieldPosX);
        addRenderableWidget(textFieldPosY);
        addRenderableWidget(textFieldPosZ);
        addRenderableWidget(textFieldSizeX);
        addRenderableWidget(textFieldSizeY);
        addRenderableWidget(textFieldSizeZ);
        addRenderableWidget(textFieldInflate);
        addRenderableWidget(textFieldPivotX);
        addRenderableWidget(textFieldPivotY);
        addRenderableWidget(textFieldPivotZ);
        addRenderableWidget(textFieldRotationX);
        addRenderableWidget(textFieldRotationY);
        addRenderableWidget(textFieldRotationZ);
        addRenderableWidget(buttonExitModelEditor);
        addRenderableWidget(buttonCreateModelIcon);
        addRenderableWidget(buttonCreateFolderIcon);
        updateJavaModelPartPosition();
        modelOutlinerList.setVisible(true);
        if (minecraft != null) {
            GUI_RENDER_GRAPHICS = true;
        }
    }

    public void updateJavaModelPartPosition() {
        dynamicEntityModelEditing.getModelSlotData(0);
        float posXModel = DynamicEntityModelEditing.getModelSlotPositionX();
        textFieldPosX.setValue(String.valueOf(posXModel));
        float posYModel = DynamicEntityModelEditing.getModelSlotPositionY();
        textFieldPosY.setValue(String.valueOf(posYModel));
        float posZModel = DynamicEntityModelEditing.getModelSlotPositionZ();
        textFieldPosZ.setValue(String.valueOf(posZModel));
        float sizeXModel = DynamicEntityModelEditing.getModelSlotSizeX();
        textFieldSizeX.setValue(String.valueOf(sizeXModel));
        float sizeYModel = DynamicEntityModelEditing.getModelSlotSizeY();
        textFieldSizeY.setValue(String.valueOf(sizeYModel));
        float sizeZModel = DynamicEntityModelEditing.getModelSlotSizeZ();
        textFieldSizeZ.setValue(String.valueOf(sizeZModel));
        float pivotXModel = DynamicEntityModelEditing.getModelSlotPivotX();
        textFieldPivotX.setValue(String.valueOf(pivotXModel));
        float pivotYModel = DynamicEntityModelEditing.getModelSlotPivotY();
        textFieldPivotY.setValue(String.valueOf(pivotYModel));
        float pivotZModel = DynamicEntityModelEditing.getModelSlotPivotZ();
        textFieldPivotZ.setValue(String.valueOf(pivotZModel));
        float rotationXModel = DynamicEntityModelEditing.getModelSlotRotationX();
        textFieldRotationX.setValue(String.valueOf(rotationXModel));
        float rotationYModel = DynamicEntityModelEditing.getModelSlotRotationY();
        textFieldRotationY.setValue(String.valueOf(rotationYModel));
        float rotationZModel = DynamicEntityModelEditing.getModelSlotRotationZ();
        textFieldRotationZ.setValue(String.valueOf(rotationZModel));
        float inflateModel = DynamicEntityModelEditing.getModelSlotInflate();
        textFieldInflate.setValue(String.valueOf(inflateModel));
    }

    public void updateJavaModelPartPositionX(int slot) {
        dynamicEntityModelEditing.getModelSlotData(slot);
        float posXModel = DynamicEntityModelEditing.getModelSlotPositionX();
        textFieldPosX.setValue(String.valueOf(posXModel));
    }

    public void updateJavaModelPartPositionY(int slot) {
        dynamicEntityModelEditing.getModelSlotData(slot);
        float posYModel = DynamicEntityModelEditing.getModelSlotPositionY();
        textFieldPosY.setValue(String.valueOf(posYModel));
    }

    public void updateJavaModelPartPositionZ(int slot) {
        dynamicEntityModelEditing.getModelSlotData(slot);
        float posZModel = DynamicEntityModelEditing.getModelSlotPositionZ();
        textFieldPosZ.setValue(String.valueOf(posZModel));
    }

    public void updateJavaModelPartSizeX(int slot) {
        dynamicEntityModelEditing.getModelSlotData(slot);
        float sizeXModel = DynamicEntityModelEditing.getModelSlotSizeX();
        textFieldSizeX.setValue(String.valueOf(sizeXModel));
    }

    public void updateJavaModelPartSizeY(int slot) {
        dynamicEntityModelEditing.getModelSlotData(slot);
        float sizeYModel = DynamicEntityModelEditing.getModelSlotSizeY();
        textFieldSizeY.setValue(String.valueOf(sizeYModel));
    }

    public void updateJavaModelPartSizeZ(int slot) {
        dynamicEntityModelEditing.getModelSlotData(slot);
        float sizeZModel = DynamicEntityModelEditing.getModelSlotSizeZ();
        textFieldSizeZ.setValue(String.valueOf(sizeZModel));
    }

    public void updateJavaModelPartPivotX(int slot) {
        dynamicEntityModelEditing.getModelSlotData(slot);
        float pivotXModel = DynamicEntityModelEditing.getModelSlotPivotX();
        textFieldPivotX.setValue(String.valueOf(pivotXModel));
    }

    public void updateJavaModelPartPivotY(int slot) {
        dynamicEntityModelEditing.getModelSlotData(slot);
        float pivotYModel = DynamicEntityModelEditing.getModelSlotPivotY();
        textFieldPivotY.setValue(String.valueOf(pivotYModel));
    }

    public void updateJavaModelPartPivotZ(int slot) {
        dynamicEntityModelEditing.getModelSlotData(slot);
        float pivotZModel = DynamicEntityModelEditing.getModelSlotPivotZ();
        textFieldPivotZ.setValue(String.valueOf(pivotZModel));
    }

    public void updateJavaModelPartRotationX(int slot) {
        dynamicEntityModelEditing.getModelSlotData(slot);
        float rotationXModel = DynamicEntityModelEditing.getModelSlotRotationX();
        textFieldRotationX.setValue(String.valueOf(rotationXModel));
    }

    public void updateJavaModelPartRotationY(int slot) {
        dynamicEntityModelEditing.getModelSlotData(slot);
        float rotationYModel = DynamicEntityModelEditing.getModelSlotRotationY();
        textFieldRotationY.setValue(String.valueOf(rotationYModel));
    }

    public void updateJavaModelPartRotationZ(int slot) {
        dynamicEntityModelEditing.getModelSlotData(slot);
        float rotationZModel = DynamicEntityModelEditing.getModelSlotRotationZ();
        textFieldRotationZ.setValue(String.valueOf(rotationZModel));
    }

    public void updateJavaModelPartInflate(int slot) {
        dynamicEntityModelEditing.getModelSlotData(slot);
        float inflateModel = DynamicEntityModelEditing.getModelSlotInflate();
        textFieldInflate.setValue(String.valueOf(inflateModel));
    }

    public void setJavaModelPartPositionX() {
        positionXScreenValue = Float.parseFloat(textFieldPosX.getValue());
        dynamicEntityModelEditing.setModelSlotPositionX(0, positionXScreenValue);
        updateJavaModelPartPositionX(0);
    }

    public void setJavaModelPartPositionY() {
        positionYScreenValue = Float.parseFloat(textFieldPosY.getValue());
        dynamicEntityModelEditing.setModelSlotPositionY(0, positionYScreenValue);
        updateJavaModelPartPositionY(0);
    }

    public void setJavaModelPartPositionZ() {
        positionZScreenValue = Float.parseFloat(textFieldPosZ.getValue());
        dynamicEntityModelEditing.setModelSlotPositionZ(0, positionZScreenValue);
        updateJavaModelPartPositionZ(0);
    }

    public void setJavaModelPartSizeX() {
        sizeXScreenValue = Float.parseFloat(textFieldSizeX.getValue());
        dynamicEntityModelEditing.setModelSlotSizeX(0, sizeXScreenValue);
        updateJavaModelPartSizeX(0);
    }

    public void setJavaModelPartSizeY() {
        sizeYScreenValue = Float.parseFloat(textFieldSizeY.getValue());
        dynamicEntityModelEditing.setModelSlotSizeY(0, sizeYScreenValue);
        updateJavaModelPartSizeY(0);
    }

    public void setJavaModelPartSizeZ() {
        sizeZScreenValue = Float.parseFloat(textFieldSizeZ.getValue());
        dynamicEntityModelEditing.setModelSlotSizeZ(0, sizeZScreenValue);
        updateJavaModelPartSizeZ(0);
    }

    public void setJavaModelPartPivotX() {
        pivotXScreenValue = Float.parseFloat(textFieldPivotX.getValue());
        dynamicEntityModelEditing.setModelSlotPivotX(0, pivotXScreenValue);
        updateJavaModelPartPivotX(0);
    }

    public void setJavaModelPartPivotY() {
        pivotYScreenValue = Float.parseFloat(textFieldPosY.getValue());
        dynamicEntityModelEditing.setModelSlotPivotY(0, pivotYScreenValue);
        updateJavaModelPartPivotY(0);
    }

    public void setJavaModelPartPivotZ() {
        pivotZScreenValue = Float.parseFloat(textFieldPosZ.getValue());
        dynamicEntityModelEditing.setModelSlotPivotZ(0, pivotZScreenValue);
        updateJavaModelPartPivotZ(0);
    }

    public void setJavaModelPartRotationX() {
        rotationXScreenValue = Float.parseFloat(textFieldRotationX.getValue());
        dynamicEntityModelEditing.setModelSlotRotationX(0, rotationXScreenValue);
        updateJavaModelPartRotationX(0);
    }

    public void setJavaModelPartRotationY() {
        rotationYScreenValue = Float.parseFloat(textFieldRotationY.getValue());
        dynamicEntityModelEditing.setModelSlotRotationY(0, rotationYScreenValue);
        updateJavaModelPartRotationY(0);
    }

    public void setJavaModelPartRotationZ() {
        rotationZScreenValue = Float.parseFloat(textFieldRotationZ.getValue());
        dynamicEntityModelEditing.setModelSlotRotationZ(0, rotationZScreenValue);
        updateJavaModelPartRotationZ(0);
    }

    public void setJavaModelPartInflate() {
        inflateScreenValue = Float.parseFloat(textFieldInflate.getValue());
        dynamicEntityModelEditing.setModelSlotInflate(0, inflateScreenValue);
        updateJavaModelPartInflate(0);
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
        final int rightSideX = width - BIG_SQUARE_SIZE * 4;
        minecraft.options.hideGui = true;
        if (isModelEditGui) {
            onModelEditGui(true);
            creatorMinecraftScreenWindows.renderModelEditorGui(guiGraphics, font, height, width);
            modelOutlinerList.render(guiGraphics, font);
            modelOutlinerList.x = rightSideX + BIG_SQUARE_SIZE * 3 + STANDARD_SIZE - STANDARD_SIZE * 2 * 3 - BIG_SQUARE_SIZE - 30;
            modelOutlinerList.y = STANDARD_SIZE * 10 + 13;
        } else {
            onModelEditGui(false);
        }
        super.render(guiGraphics, i, j, f);
    }



    public static void render(PoseStack poseStack) {
        if (GUI_RENDER_GRAPHICS) {
            final Minecraft minecraft = Minecraft.getInstance();
            final MultiBufferSource.BufferSource bufferSource = minecraft.renderBuffers().bufferSource();
            renderGraphicsBackground(poseStack, bufferSource);
            bufferSource.endBatch();
            poseStack.pushPose();
            poseStack.translate(-cameraX, -cameraY, -cameraZ);
            poseStack.mulPose(Axis.XP.rotationDegrees(cameraPitch));
            poseStack.mulPose(Axis.YP.rotationDegrees(cameraYaw));
            poseStack.mulPose(Axis.ZP.rotationDegrees((0)));
            DynamicEntityModelEditing dynamicEntityModelEditing = new DynamicEntityModelEditing();
            poseStack.pushPose();
            poseStack.translate(0, 0, 0);
            dynamicEntityModelEditing.render(poseStack, bufferSource, 0xF000F0, 0);
            poseStack.popPose();
            GridModel gridModel = new GridModel();
            poseStack.pushPose();
            poseStack.translate(0, 0, 0);
            gridModel.render(poseStack, bufferSource, 0xF600F0, 0);
            poseStack.popPose();
            ModelXZPlane modelXZPlane = new ModelXZPlane();
            poseStack.pushPose();
            poseStack.translate(0, 0.98f, 0);
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
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {

        if (keyCode == GLFW.GLFW_KEY_ENTER && textFieldPosX.isFocused()) {
            setJavaModelPartPositionX();
        }

        if (keyCode == GLFW.GLFW_KEY_ENTER && textFieldPosY.isFocused()) {
            setJavaModelPartPositionY();
        }

        if (keyCode == GLFW.GLFW_KEY_ENTER && textFieldPosZ.isFocused()) {
            setJavaModelPartPositionZ();
        }

        if (keyCode == GLFW.GLFW_KEY_ENTER && textFieldSizeX.isFocused()) {
            setJavaModelPartSizeX();
        }

        if (keyCode == GLFW.GLFW_KEY_ENTER && textFieldSizeY.isFocused()) {
            setJavaModelPartSizeY();
        }

        if (keyCode == GLFW.GLFW_KEY_ENTER && textFieldSizeZ.isFocused()) {
            setJavaModelPartSizeZ();
        }

        if (keyCode == GLFW.GLFW_KEY_ENTER && textFieldPivotX.isFocused()) {
            setJavaModelPartPivotX();
        }

        if (keyCode == GLFW.GLFW_KEY_ENTER && textFieldPivotY.isFocused()) {
            setJavaModelPartPivotY();
        }

        if (keyCode == GLFW.GLFW_KEY_ENTER && textFieldPivotZ.isFocused()) {
            setJavaModelPartPivotZ();
        }

        if (keyCode == GLFW.GLFW_KEY_ENTER && textFieldRotationX.isFocused()) {
            setJavaModelPartRotationX();
        }

        if (keyCode == GLFW.GLFW_KEY_ENTER && textFieldRotationY.isFocused()) {
            setJavaModelPartRotationY();
        }

        if (keyCode == GLFW.GLFW_KEY_ENTER && textFieldRotationZ.isFocused()) {
            setJavaModelPartRotationZ();
        }

        if (keyCode == GLFW.GLFW_KEY_ENTER && textFieldInflate.isFocused()) {
            setJavaModelPartInflate();
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
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

    public void onModelEditGui(boolean isModelEditGui) {
        buttonExitModelEditor.visible = isModelEditGui;

        textFieldPosX.visible = isModelEditGui;
        textFieldPosY.visible = isModelEditGui;
        textFieldPosZ.visible = isModelEditGui;
        textFieldSizeX.visible = isModelEditGui;
        textFieldSizeY.visible = isModelEditGui;
        textFieldSizeZ.visible = isModelEditGui;
        textFieldInflate.visible = isModelEditGui;
        buttonCreateModelIcon.visible = isModelEditGui;
        buttonCreateFolderIcon.visible = isModelEditGui;
        textFieldPivotX.visible = isModelEditGui;
        textFieldPivotY.visible = isModelEditGui;
        textFieldPivotZ.visible = isModelEditGui;
        textFieldRotationX.visible = isModelEditGui;
        textFieldRotationY.visible = isModelEditGui;
        textFieldRotationZ.visible = isModelEditGui;
    }
}
