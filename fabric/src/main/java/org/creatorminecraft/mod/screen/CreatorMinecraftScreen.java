package org.creatorminecraft.mod.screen;

import org.creatorminecraft.mod.client.CreatorIGUI;
import org.creatorminecraft.mod.client.WidgetSetter;
import org.creatorminecraft.mod.gui.ModelOutlinerList;
import org.creatorminecraft.mod.gui.ThemeSelectorList;
import org.creatorminecraft.mod.model.*;
import org.creatorminecraft.mod.render.GraphicsRender;
import org.creatorminecraft.mod.widgets.DynamicButtonCMJ;
import org.creatorminecraft.mod.gui.CreatorMinecraftEditBox;
import org.lwjgl.glfw.GLFW;
import org.mtr.mapping.holder.ClickableWidget;
import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.holder.MinecraftClient;
import org.mtr.mapping.holder.Screen;
import org.mtr.mapping.mapper.*;
import org.mtr.mapping.tool.TextCase;

import java.util.Objects;

public class CreatorMinecraftScreen extends ScreenExtension implements CreatorIGUI {

    private final DynamicButtonCMJ buttonFileSettings;
    private final DynamicButtonCMJ buttonEditSettings;
    private final DynamicButtonCMJ buttonViewerSettings;
    private final DynamicButtonCMJ buttonRenderSettings;
    private final DynamicButtonCMJ buttonWindowSettings;
    private final DynamicButtonCMJ buttonSettings;
    public CreatorScreenManager creatorScreenManager = new CreatorScreenManager();
    private final CreatorMinecraftScreenWindows creatorMinecraftScreenWindows = new CreatorMinecraftScreenWindows();
    private final ModelOutlinerList modelOutlinerList = new ModelOutlinerList();
    public static float cameraYaw = 90;
    public static float cameraPitch = 220;
    public static double cameraX = 0;
    public static double cameraY = 0;
    public static double cameraZ = 17.5;
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
    private static float isVisibleScreenValue;
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
    public final TexturedButtonWidgetExtension buttonCreateModelIcon;
    public final TexturedButtonWidgetExtension buttonCreateFolderIcon;
    private final TexturedButtonWidgetExtension buttonExitModelEditor;
    private final TexturedButtonWidgetExtension buttonModelPartPositionIcon;
    private final TexturedButtonWidgetExtension buttonModelPartSizeIcon;
    private final TexturedButtonWidgetExtension buttonModelPartRotationIcon;
    public DynamicEntityModelEditing dynamicEntityModelEditing = new DynamicEntityModelEditing();
    public static boolean isModelEditing;
    public static boolean isModelEditGui;
    public static int modelPartTarget = 0;
    public boolean EDITING_X_MODE_MOUSE_X;
    public boolean EDITING_X_MODE_MOUSE_Y;
    public boolean EDITING_Y_MODE_MOUSE_Y;
    public boolean EDITING_Z_MODE_MOUSE_X;
    public boolean EDITING_Z_MODE_MOUSE_Y;
    private boolean mouseXMovingRight;
    private boolean mouseXMovingLeft;
    private boolean mouseYMovingUp;
    private boolean mouseYMovingDown;
    private boolean isReversedX;
    private boolean isReversedZ;
    private double mouseXLast;
    private double mouseYLast;

    public CreatorMinecraftScreen() {
        super();
        buttonFileSettings = new DynamicButtonCMJ(0, 0, 0, 20, TextHelper.literal("File"), button -> creatorScreenManager.onFileMenu());
        buttonEditSettings = new DynamicButtonCMJ(0, 0, 0, 20, TextHelper.literal("Edit"), button -> creatorScreenManager.onEditMenu());
        buttonViewerSettings = new DynamicButtonCMJ(0, 0, 0, 20, TextHelper.literal("Viewer"), button -> creatorScreenManager.onViewMenu());
        buttonRenderSettings = new DynamicButtonCMJ(0, 0, 0, 20, TextHelper.literal("Render"), button -> creatorScreenManager.onRenderMenu());
        buttonWindowSettings = new DynamicButtonCMJ(0, 0, 0, 20, TextHelper.literal("Window"), button -> creatorScreenManager.onWindowMenu());
        buttonSettings = new DynamicButtonCMJ(0, 0, 0, 20, TextHelper.literal("Settings"), button -> {
            final MinecraftClient minecraftClient = MinecraftClient.getInstance();
            minecraftClient.openScreen(new Screen(new CreatorMinecraftScreenSettings()));
        });
        textFieldPosX = new CreatorMinecraftEditBox(0,0,0,STANDARD_SIZE, 16, TextCase.DEFAULT, "[^\\d.,\\[\\]\\- ]", "0");
        textFieldPosY = new CreatorMinecraftEditBox(0,0,0,STANDARD_SIZE, 16, TextCase.DEFAULT, "[^\\d.,\\[\\]\\- ]", "0");
        textFieldPosZ = new CreatorMinecraftEditBox(0,0,0,STANDARD_SIZE, 16, TextCase.DEFAULT, "[^\\d.,\\[\\]\\- ]", "0");
        textFieldSizeX = new CreatorMinecraftEditBox(0,0,0,STANDARD_SIZE, 16, TextCase.DEFAULT, "[^\\d.,\\[\\]\\- ]", "0");
        textFieldSizeY = new CreatorMinecraftEditBox(0,0,0,STANDARD_SIZE, 16, TextCase.DEFAULT, "[^\\d.,\\[\\]\\- ]", "0");
        textFieldSizeZ = new CreatorMinecraftEditBox(0,0,0,STANDARD_SIZE, 16, TextCase.DEFAULT, "[^\\d.,\\[\\]\\- ]", "0");
        textFieldInflate = new CreatorMinecraftEditBox(0,0,0,STANDARD_SIZE, 16, TextCase.DEFAULT, "[^\\d.,\\[\\]\\- ]", "0");
        textFieldPivotX = new CreatorMinecraftEditBox(0,0,0,STANDARD_SIZE, 16, TextCase.DEFAULT, "[^\\d.,\\[\\]\\- ]", "0");
        textFieldPivotY = new CreatorMinecraftEditBox(0,0,0,STANDARD_SIZE, 16, TextCase.DEFAULT, "[^\\d.,\\[\\]\\- ]", "0");
        textFieldPivotZ = new CreatorMinecraftEditBox(0,0,0,STANDARD_SIZE, 16, TextCase.DEFAULT, "[^\\d.,\\[\\]\\- ]", "0");
        textFieldRotationX = new CreatorMinecraftEditBox(0,0,0,STANDARD_SIZE, 16, TextCase.DEFAULT, "[^\\d.,\\[\\]\\- ]", "0");
        textFieldRotationY = new CreatorMinecraftEditBox(0,0,0,STANDARD_SIZE, 16, TextCase.DEFAULT, "[^\\d.,\\[\\]\\- ]", "0");
        textFieldRotationZ = new CreatorMinecraftEditBox(0,0,0,STANDARD_SIZE, 16, TextCase.DEFAULT, "[^\\d.,\\[\\]\\- ]", "0");
        buttonExitModelEditor = new TexturedButtonWidgetExtension(0, 0, 0, STANDARD_SIZE, new Identifier("creatorminecraft:textures/gui/icon_exit.png"), new Identifier("creatorminecraft:textures/gui/icon_exit.png"), button -> {
            isModelEditGui = false;
        });
        buttonCreateModelIcon = new TexturedButtonWidgetExtension(0, 0, 0, STANDARD_SIZE, new Identifier("creatorminecraft:textures/gui/icon_add_cube.png"), new Identifier("creatorminecraft:textures/gui/icon_add_cube.png"), button -> {
            dynamicEntityModelEditing.addModelSlot();
        });
        buttonCreateFolderIcon = new TexturedButtonWidgetExtension(0, 0, 0, STANDARD_SIZE, new Identifier("creatorminecraft:textures/gui/icon_add_folder.png"), new Identifier("creatorminecraft:textures/gui/icon_add_folder.png"), button -> {
        });
        buttonModelPartPositionIcon = new TexturedButtonWidgetExtension(0, 0, 0, STANDARD_SIZE, new Identifier("creatorminecraft:textures/gui/modelpart_move_icon.png"), new Identifier("creatorminecraft:textures/gui/modelpart_move_icon.png"), button -> {
            movePositionScreenMode = true;
            resizeScreenMode = false;
            rotateScreenMode = false;
            dynamicEntityModelEditing.screenGUIToolMode();
        });
        buttonModelPartSizeIcon = new TexturedButtonWidgetExtension(0, 0, 0, STANDARD_SIZE, new Identifier("creatorminecraft:textures/gui/modelpart_resize_icon.png"), new Identifier("creatorminecraft:textures/gui/modelpart_resize_icon.png"), button -> {
            resizeScreenMode = true;
            movePositionScreenMode = false;
            rotateScreenMode = false;
            dynamicEntityModelEditing.screenGUIToolMode();
        });
        buttonModelPartRotationIcon = new TexturedButtonWidgetExtension(0, 0, 0, STANDARD_SIZE, new Identifier("creatorminecraft:textures/gui/modelpart_rotation_icon.png"), new Identifier("creatorminecraft:textures/gui/modelpart_rotation_icon.png"), button -> {
            rotateScreenMode = true;
            movePositionScreenMode = false;
            resizeScreenMode = false;
            dynamicEntityModelEditing.screenGUIToolMode();
        });
        new CreatorScreenManager();
    }

    @Override
    protected void init2() {
        final int rightSideX = width - BIG_SQUARE_SIZE * 4;
        final int standardMenuWidth = width / 3 + BIG_SQUARE_SIZE;
        int UIDynamicX = 0;
        int adaptToLiquidUI = 0;
        int adaptToLiquidUIModelIcons = 0;

        if (ThemeSelectorList.getUIWidgetIsLiquidUIEnum()) {
            adaptToLiquidUI = 35;
            adaptToLiquidUIModelIcons = 5;
        }
        WidgetSetter.setAdjustableButtonWidget(buttonFileSettings, UIDynamicX + adaptToLiquidUI, 0, standardMenuWidth / 4);
        WidgetSetter.setAdjustableButtonWidget(buttonEditSettings, UIDynamicX + standardMenuWidth / 4 + adaptToLiquidUI, 0, standardMenuWidth / 4);
        WidgetSetter.setAdjustableButtonWidget(buttonViewerSettings, UIDynamicX + standardMenuWidth / 4 * 2 + adaptToLiquidUI, 0, standardMenuWidth / 4);
        WidgetSetter.setAdjustableButtonWidget(buttonRenderSettings, UIDynamicX + standardMenuWidth / 4 * 3 + adaptToLiquidUI, 0, standardMenuWidth / 4);
        WidgetSetter.setAdjustableButtonWidget(buttonWindowSettings, UIDynamicX + standardMenuWidth / 4 * 4 + adaptToLiquidUI, 0, standardMenuWidth / 4);
        WidgetSetter.setAdjustableButtonWidget(buttonSettings, UIDynamicX + standardMenuWidth / 4 * 5 + adaptToLiquidUI, 0, standardMenuWidth / 4);

        WidgetSetter.setAdjustableButtonWidget(creatorScreenManager.buttonNewProject, 0 , STANDARD_SIZE, standardMenuWidth / 4);
        WidgetSetter.setAdjustableButtonWidget(creatorScreenManager.buttonSaveProject, 0, STANDARD_SIZE * 2, standardMenuWidth / 4);
        WidgetSetter.setAdjustableButtonWidget(creatorScreenManager.buttonSaveAsProject, 0, STANDARD_SIZE * 3, standardMenuWidth / 4);

        WidgetSetter.setAdjustableButtonWidget(creatorScreenManager.buttonModelEditGui, standardMenuWidth / 4 + adaptToLiquidUI, STANDARD_SIZE, standardMenuWidth / 4);
        WidgetSetter.setAdjustableButtonWidget(creatorScreenManager.buttonBlockbenchMode, standardMenuWidth / 4 + adaptToLiquidUI, STANDARD_SIZE * 2, standardMenuWidth / 4);

        WidgetSetter.setAdjustableButtonWidget(creatorScreenManager.buttonCameraReset, standardMenuWidth / 4 * 2 + adaptToLiquidUI, STANDARD_SIZE, standardMenuWidth / 4);
        WidgetSetter.setAdjustableButtonWidget(creatorScreenManager.buttonScreenshotMode, standardMenuWidth / 4 * 2 + adaptToLiquidUI, STANDARD_SIZE * 2, standardMenuWidth / 4);
        WidgetSetter.setAdjustableButtonWidget(creatorScreenManager.buttonPlayerCamera, standardMenuWidth / 4 * 2 + adaptToLiquidUI, STANDARD_SIZE * 3, standardMenuWidth / 4);
        //ButtonSetter.setPositionAndWidth(creatorScreenManager.buttonExitScreenshotMode, standardMenuWidth / 4 * 2, STANDARD_SIZE * 4, standardMenuWidth / 4);

        WidgetSetter.setAdjustableButtonWidget(creatorScreenManager.buttonShaderRender, standardMenuWidth / 4 * 3 + adaptToLiquidUI, STANDARD_SIZE, standardMenuWidth / 4);
        WidgetSetter.setAdjustableButtonWidget(creatorScreenManager.buttonColorBlendRender, standardMenuWidth / 4 * 3 + adaptToLiquidUI, STANDARD_SIZE * 2, standardMenuWidth / 4);

        WidgetSetter.setAdjustableButtonWidget(creatorScreenManager.buttonCameraPresets, standardMenuWidth / 4 * 4 + adaptToLiquidUI, STANDARD_SIZE, standardMenuWidth / 4);

        WidgetSetter.setPositionAndTextFieldWidget(textFieldPosX, rightSideX + BIG_SQUARE_SIZE * 3 + STANDARD_SIZE - STANDARD_SIZE * 2 * 3, STANDARD_SIZE * 3, STANDARD_SIZE * 2);
        WidgetSetter.setPositionAndTextFieldWidget(textFieldPosY, rightSideX + BIG_SQUARE_SIZE * 3 + STANDARD_SIZE - STANDARD_SIZE * 2 * 2, STANDARD_SIZE * 3, STANDARD_SIZE * 2);
        WidgetSetter.setPositionAndTextFieldWidget(textFieldPosZ, rightSideX + BIG_SQUARE_SIZE * 3 + STANDARD_SIZE - STANDARD_SIZE * 2, STANDARD_SIZE * 3, STANDARD_SIZE * 2);
        WidgetSetter.setPositionAndTextFieldWidget(textFieldSizeX, rightSideX + BIG_SQUARE_SIZE * 3 + STANDARD_SIZE - STANDARD_SIZE * 2 * 3, STANDARD_SIZE * 4, STANDARD_SIZE * 2);
        WidgetSetter.setPositionAndTextFieldWidget(textFieldSizeY, rightSideX + BIG_SQUARE_SIZE * 3 + STANDARD_SIZE - STANDARD_SIZE * 2 * 2, STANDARD_SIZE * 4, STANDARD_SIZE * 2);
        WidgetSetter.setPositionAndTextFieldWidget(textFieldSizeZ, rightSideX + BIG_SQUARE_SIZE * 3 + STANDARD_SIZE - STANDARD_SIZE * 2, STANDARD_SIZE * 4, STANDARD_SIZE * 2);
        WidgetSetter.setPositionAndTextFieldWidget(textFieldPivotX, rightSideX + BIG_SQUARE_SIZE * 3 + STANDARD_SIZE - STANDARD_SIZE * 2 * 3, STANDARD_SIZE * 5, STANDARD_SIZE * 2);
        WidgetSetter.setPositionAndTextFieldWidget(textFieldPivotY, rightSideX + BIG_SQUARE_SIZE * 3 + STANDARD_SIZE - STANDARD_SIZE * 2 * 2, STANDARD_SIZE * 5, STANDARD_SIZE * 2);
        WidgetSetter.setPositionAndTextFieldWidget(textFieldPivotZ, rightSideX + BIG_SQUARE_SIZE * 3 + STANDARD_SIZE - STANDARD_SIZE * 2, STANDARD_SIZE * 5, STANDARD_SIZE * 2);
        WidgetSetter.setPositionAndTextFieldWidget(textFieldRotationX, rightSideX + BIG_SQUARE_SIZE * 3 + STANDARD_SIZE - STANDARD_SIZE * 2 * 3, STANDARD_SIZE * 6, STANDARD_SIZE * 2);
        WidgetSetter.setPositionAndTextFieldWidget(textFieldRotationY, rightSideX + BIG_SQUARE_SIZE * 3 + STANDARD_SIZE - STANDARD_SIZE * 2 * 2, STANDARD_SIZE * 6, STANDARD_SIZE * 2);
        WidgetSetter.setPositionAndTextFieldWidget(textFieldRotationZ, rightSideX + BIG_SQUARE_SIZE * 3 + STANDARD_SIZE - STANDARD_SIZE * 2, STANDARD_SIZE * 6, STANDARD_SIZE * 2);
        WidgetSetter.setPositionAndTextFieldWidget(textFieldInflate, rightSideX + BIG_SQUARE_SIZE * 3 + STANDARD_SIZE - STANDARD_SIZE * 2 * 3, STANDARD_SIZE * 7, STANDARD_SIZE * 2);

        WidgetSetter.setPositionAndTexturedButtonWidget(buttonExitModelEditor, rightSideX + BIG_SQUARE_SIZE * 3, STANDARD_SIZE + 5, STANDARD_SIZE);

        WidgetSetter.setPositionAndTexturedButtonWidget(buttonCreateModelIcon, rightSideX + BIG_SQUARE_SIZE * 3 + STANDARD_SIZE - STANDARD_SIZE * 2 * 3 - 30, STANDARD_SIZE * 8 + 6, STANDARD_SIZE);
        //WidgetSetter.setAdjustableWidget(buttonCreateFolderIcon, rightSideX + BIG_SQUARE_SIZE * 3 + STANDARD_SIZE - STANDARD_SIZE * 2 * 3 - 30, STANDARD_SIZE * 8 + 6, STANDARD_SIZE);

        WidgetSetter.setPositionAndTexturedButtonWidget(buttonModelPartPositionIcon, UIDynamicX, STANDARD_SIZE + adaptToLiquidUIModelIcons, STANDARD_SIZE);
        WidgetSetter.setPositionAndTexturedButtonWidget(buttonModelPartSizeIcon, UIDynamicX + STANDARD_SIZE, STANDARD_SIZE + adaptToLiquidUIModelIcons, STANDARD_SIZE);
        WidgetSetter.setPositionAndTexturedButtonWidget(buttonModelPartRotationIcon, UIDynamicX + STANDARD_SIZE * 2, STANDARD_SIZE + adaptToLiquidUIModelIcons, STANDARD_SIZE);

        addChild(new ClickableWidget(buttonFileSettings));
        addChild(new ClickableWidget(buttonEditSettings));
        addChild(new ClickableWidget(buttonViewerSettings));
        addChild(new ClickableWidget(buttonRenderSettings));
        addChild(new ClickableWidget(buttonWindowSettings));
        addChild(new ClickableWidget(buttonSettings));

        //addRenderableWidget(creatorScreenManager.buttonNewProject);
        //addRenderableWidget(creatorScreenManager.buttonSaveProject);
        //addRenderableWidget(creatorScreenManager.buttonSaveAsProject);

        addChild(new ClickableWidget(creatorScreenManager.buttonModelEditGui));
        //addRenderableWidget(creatorScreenManager.buttonBlockbenchMode);

        addChild(new ClickableWidget(creatorScreenManager.buttonCameraReset));
        //addRenderableWidget(creatorScreenManager.buttonScreenshotMode);
        //addRenderableWidget(creatorScreenManager.buttonPlayerCamera);
        //addRenderableWidget(creatorScreenManager.buttonExitScreenshotMode);

        //addRenderableWidget(creatorScreenManager.buttonShaderRender);
        //addRenderableWidget(creatorScreenManager.buttonColorBlendRender);

        //addRenderableWidget(creatorScreenManager.buttonCameraPresets);

        addChild(new ClickableWidget(textFieldPosX));
        addChild(new ClickableWidget(textFieldPosY));
        addChild(new ClickableWidget(textFieldPosZ));
        addChild(new ClickableWidget(textFieldSizeX));
        addChild(new ClickableWidget(textFieldSizeY));
        addChild(new ClickableWidget(textFieldSizeZ));
        addChild(new ClickableWidget(textFieldInflate));
        addChild(new ClickableWidget(textFieldPivotX));
        addChild(new ClickableWidget(textFieldPivotY));
        addChild(new ClickableWidget(textFieldPivotZ));
        addChild(new ClickableWidget(textFieldRotationX));
        addChild(new ClickableWidget(textFieldRotationY));
        addChild(new ClickableWidget(textFieldRotationZ));
        addChild(new ClickableWidget(buttonExitModelEditor));
        addChild(new ClickableWidget(buttonCreateModelIcon));
        addChild(new ClickableWidget(buttonCreateFolderIcon));
        addChild(new ClickableWidget(buttonModelPartPositionIcon));
        addChild(new ClickableWidget(buttonModelPartSizeIcon));
        addChild(new ClickableWidget(buttonModelPartRotationIcon));
        updateJavaModelPartPosition();
        modelOutlinerList.setVisible(true);
        GUI_RENDER_GRAPHICS = true;
    }

    public void updateJavaModelPartPosition() {
        dynamicEntityModelEditing.getModelSlotData(modelPartTarget);
        float posXModel = DynamicEntityModelEditing.getModelSlotPositionX();
        textFieldPosX.setText2(String.valueOf(posXModel));
        float posYModel = DynamicEntityModelEditing.getModelSlotPositionY();
        textFieldPosY.setText2(String.valueOf(posYModel));
        float posZModel = DynamicEntityModelEditing.getModelSlotPositionZ();
        textFieldPosZ.setText2(String.valueOf(posZModel));
        float sizeXModel = DynamicEntityModelEditing.getModelSlotSizeX();
        textFieldSizeX.setText2(String.valueOf(sizeXModel));
        float sizeYModel = DynamicEntityModelEditing.getModelSlotSizeY();
        textFieldSizeY.setText2(String.valueOf(sizeYModel));
        float sizeZModel = DynamicEntityModelEditing.getModelSlotSizeZ();
        textFieldSizeZ.setText2(String.valueOf(sizeZModel));
        float pivotXModel = DynamicEntityModelEditing.getModelSlotPivotX();
        textFieldPivotX.setText2(String.valueOf(pivotXModel));
        float pivotYModel = DynamicEntityModelEditing.getModelSlotPivotY();
        textFieldPivotY.setText2(String.valueOf(pivotYModel));
        float pivotZModel = DynamicEntityModelEditing.getModelSlotPivotZ();
        textFieldPivotZ.setText2(String.valueOf(pivotZModel));
        float rotationXModel = DynamicEntityModelEditing.getModelSlotRotationX();
        textFieldRotationX.setText2(String.valueOf(rotationXModel));
        float rotationYModel = DynamicEntityModelEditing.getModelSlotRotationY();
        textFieldRotationY.setText2(String.valueOf(rotationYModel));
        float rotationZModel = DynamicEntityModelEditing.getModelSlotRotationZ();
        textFieldRotationZ.setText2(String.valueOf(rotationZModel));
        float inflateModel = DynamicEntityModelEditing.getModelSlotInflate();
        textFieldInflate.setText2(String.valueOf(inflateModel));
    }

    public void updateJavaModelPartPositionX(int slot) {
        dynamicEntityModelEditing.getModelSlotData(slot);
        float posXModel = DynamicEntityModelEditing.getModelSlotPositionX();
        textFieldPosX.setText2(String.valueOf(posXModel));
    }

    public void updateJavaModelPartPositionY(int slot) {
        dynamicEntityModelEditing.getModelSlotData(slot);
        float posYModel = DynamicEntityModelEditing.getModelSlotPositionY();
        textFieldPosY.setText2(String.valueOf(posYModel));
    }

    public void updateJavaModelPartPositionZ(int slot) {
        dynamicEntityModelEditing.getModelSlotData(slot);
        float posZModel = DynamicEntityModelEditing.getModelSlotPositionZ();
        textFieldPosZ.setText2(String.valueOf(posZModel));
    }

    public void updateJavaModelPartSizeX(int slot) {
        dynamicEntityModelEditing.getModelSlotData(slot);
        float sizeXModel = DynamicEntityModelEditing.getModelSlotSizeX();
        textFieldSizeX.setText2(String.valueOf(sizeXModel));
    }

    public void updateJavaModelPartSizeY(int slot) {
        dynamicEntityModelEditing.getModelSlotData(slot);
        float sizeYModel = DynamicEntityModelEditing.getModelSlotSizeY();
        textFieldSizeY.setText2(String.valueOf(sizeYModel));
    }

    public void updateJavaModelPartSizeZ(int slot) {
        dynamicEntityModelEditing.getModelSlotData(slot);
        float sizeZModel = DynamicEntityModelEditing.getModelSlotSizeZ();
        textFieldSizeZ.setText2(String.valueOf(sizeZModel));
    }

    public void updateJavaModelPartPivotX(int slot) {
        dynamicEntityModelEditing.getModelSlotData(slot);
        float pivotXModel = DynamicEntityModelEditing.getModelSlotPivotX();
        textFieldPivotX.setText2(String.valueOf(pivotXModel));
    }

    public void updateJavaModelPartPivotY(int slot) {
        dynamicEntityModelEditing.getModelSlotData(slot);
        float pivotYModel = DynamicEntityModelEditing.getModelSlotPivotY();
        textFieldPivotY.setText2(String.valueOf(pivotYModel));
    }

    public void updateJavaModelPartPivotZ(int slot) {
        dynamicEntityModelEditing.getModelSlotData(slot);
        float pivotZModel = DynamicEntityModelEditing.getModelSlotPivotZ();
        textFieldPivotZ.setText2(String.valueOf(pivotZModel));
    }

    public void updateJavaModelPartRotationX(int slot) {
        dynamicEntityModelEditing.getModelSlotData(slot);
        float rotationXModel = DynamicEntityModelEditing.getModelSlotRotationX();
        textFieldRotationX.setText2(String.valueOf(rotationXModel));
    }

    public void updateJavaModelPartRotationY(int slot) {
        dynamicEntityModelEditing.getModelSlotData(slot);
        float rotationYModel = DynamicEntityModelEditing.getModelSlotRotationY();
        textFieldRotationY.setText2(String.valueOf(rotationYModel));
    }

    public void updateJavaModelPartRotationZ(int slot) {
        dynamicEntityModelEditing.getModelSlotData(slot);
        float rotationZModel = DynamicEntityModelEditing.getModelSlotRotationZ();
        textFieldRotationZ.setText2(String.valueOf(rotationZModel));
    }

    public void updateJavaModelPartInflate(int slot) {
        dynamicEntityModelEditing.getModelSlotData(slot);
        float inflateModel = DynamicEntityModelEditing.getModelSlotInflate();
        textFieldInflate.setText2(String.valueOf(inflateModel));
    }

    public void updateJavaModelPartIsVisible(int slot) {
        dynamicEntityModelEditing.getModelSlotData(slot);
        boolean isVisibleModel = DynamicEntityModelEditing.getModelSlotIsVisible();
    }

    public void setJavaModelPartPositionX() {
        positionXScreenValue = Float.parseFloat(textFieldPosX.getText2());
        dynamicEntityModelEditing.setModelSlotPositionX(modelPartTarget, positionXScreenValue);
        updateJavaModelPartPositionX(modelPartTarget);
    }

    public void setJavaModelPartPositionY() {
        positionYScreenValue = Float.parseFloat(textFieldPosY.getText2());
        dynamicEntityModelEditing.setModelSlotPositionY(modelPartTarget, positionYScreenValue);
        updateJavaModelPartPositionY(modelPartTarget);
    }

    public void setJavaModelPartPositionZ() {
        positionZScreenValue = Float.parseFloat(textFieldPosZ.getText2());
        dynamicEntityModelEditing.setModelSlotPositionZ(modelPartTarget, positionZScreenValue);
        updateJavaModelPartPositionZ(modelPartTarget);
    }

    public void setJavaModelPartSizeX() {
        sizeXScreenValue = Float.parseFloat(textFieldSizeX.getText2());
        dynamicEntityModelEditing.setModelSlotSizeX(modelPartTarget, sizeXScreenValue);
        updateJavaModelPartSizeX(modelPartTarget);
    }

    public void setJavaModelPartSizeY() {
        sizeYScreenValue = Float.parseFloat(textFieldSizeY.getText2());
        dynamicEntityModelEditing.setModelSlotSizeY(modelPartTarget, sizeYScreenValue);
        updateJavaModelPartSizeY(modelPartTarget);
    }

    public void setJavaModelPartSizeZ() {
        sizeZScreenValue = Float.parseFloat(textFieldSizeZ.getText2());
        dynamicEntityModelEditing.setModelSlotSizeZ(modelPartTarget, sizeZScreenValue);
        updateJavaModelPartSizeZ(modelPartTarget);
    }

    public void setJavaModelPartPivotX() {
        pivotXScreenValue = Float.parseFloat(textFieldPivotX.getText2());
        dynamicEntityModelEditing.setModelSlotPivotX(modelPartTarget, pivotXScreenValue);
        updateJavaModelPartPivotX(modelPartTarget);
    }

    public void setJavaModelPartPivotY() {
        pivotYScreenValue = Float.parseFloat(textFieldPosY.getText2());
        dynamicEntityModelEditing.setModelSlotPivotY(modelPartTarget, pivotYScreenValue);
        updateJavaModelPartPivotY(modelPartTarget);
    }

    public void setJavaModelPartPivotZ() {
        pivotZScreenValue = Float.parseFloat(textFieldPosZ.getText2());
        dynamicEntityModelEditing.setModelSlotPivotZ(modelPartTarget, pivotZScreenValue);
        updateJavaModelPartPivotZ(modelPartTarget);
    }

    public void setJavaModelPartRotationX() {
        rotationXScreenValue = Float.parseFloat(textFieldRotationX.getText2());
        dynamicEntityModelEditing.setModelSlotRotationX(modelPartTarget, rotationXScreenValue);
        updateJavaModelPartRotationX(modelPartTarget);
    }

    public void setJavaModelPartRotationY() {
        rotationYScreenValue = Float.parseFloat(textFieldRotationY.getText2());
        dynamicEntityModelEditing.setModelSlotRotationY(modelPartTarget, rotationYScreenValue);
        updateJavaModelPartRotationY(modelPartTarget);
    }

    public void setJavaModelPartRotationZ() {
        rotationZScreenValue = Float.parseFloat(textFieldRotationZ.getText2());
        dynamicEntityModelEditing.setModelSlotRotationZ(modelPartTarget, rotationZScreenValue);
        updateJavaModelPartRotationZ(modelPartTarget);
    }

    public void setJavaModelPartInflate() {
        inflateScreenValue = Float.parseFloat(textFieldInflate.getText2());
        dynamicEntityModelEditing.setModelSlotInflate(modelPartTarget, inflateScreenValue);
        updateJavaModelPartInflate(modelPartTarget);
    }

    @Override
    public boolean mouseClicked2(double mouseX, double mouseY, int button) {
        modelOutlinerList.mouseClicked(mouseX, mouseY, button);
        //setFocused2(false);
        return super.mouseClicked2(mouseX, mouseY, button);
    }

    @Override
    public void mouseMoved2(double mouseX, double mouseY) {
        super.mouseMoved2(mouseX, mouseY);
    }

    @Override
    public boolean mouseDragged2(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        mouseXMovingRight = deltaX > 0;
        mouseXMovingLeft = deltaX < 0;

        mouseYMovingUp = deltaY > 0;
        mouseYMovingDown = deltaY < 0;

        if (mouseX > mouseXLast) {
            mouseXMovingRight = true;
        } else if (mouseX < mouseXLast) {
            mouseXMovingLeft = true;
        } else {
            mouseXMovingRight = false;
            mouseXMovingLeft = false;
        }

        if (mouseY > mouseYLast) {
            mouseYMovingUp = true;
        } else if (mouseY < mouseYLast) {
            mouseYMovingDown = true;
        } else {
            mouseYMovingUp = false;
            mouseYMovingDown = false;
        }


        if (isModelEditing && isModelEditGui) {
            if (EDITING_X_MODE_MOUSE_X) {
                if (movePositionScreenMode) {
                    if (mouseXMovingRight) {
                        if (isReversedX) {
                            dynamicEntityModelEditing.modelSlotPositionXDecrease(modelPartTarget);
                        } else {
                            dynamicEntityModelEditing.modelSlotPositionXIncrease(modelPartTarget);
                        }
                        updateJavaModelPartPositionX(modelPartTarget);
                    } else if (mouseXMovingLeft) {
                        if (isReversedX) {
                            dynamicEntityModelEditing.modelSlotPositionXIncrease(modelPartTarget);
                        } else {
                            dynamicEntityModelEditing.modelSlotPositionXDecrease(modelPartTarget);
                        }
                        updateJavaModelPartPositionX(modelPartTarget);
                    }
                } else if (resizeScreenMode) {
                    if (mouseXMovingRight) {
                        if (isReversedX) {
                            dynamicEntityModelEditing.modelSlotSizeXDecrease(modelPartTarget);
                        } else {
                            dynamicEntityModelEditing.modelSlotSizeXIncrease(modelPartTarget);
                        }
                        updateJavaModelPartSizeX(modelPartTarget);
                    } else if (mouseXMovingLeft) {
                        if (isReversedX) {
                            dynamicEntityModelEditing.modelSlotSizeXIncrease(modelPartTarget);
                        } else {
                            dynamicEntityModelEditing.modelSlotSizeXDecrease(modelPartTarget);

                        }
                        updateJavaModelPartSizeX(modelPartTarget);
                    }
                } else if (rotateScreenMode) {
                    if (mouseXMovingRight) {
                        if (isReversedX) {
                            dynamicEntityModelEditing.modelSlotRotationXDecrease(modelPartTarget);
                        } else {
                            dynamicEntityModelEditing.modelSlotRotationXIncrease(modelPartTarget);
                        }
                        updateJavaModelPartRotationX(modelPartTarget);
                    } else if (mouseXMovingLeft) {
                        if (isReversedX) {
                            dynamicEntityModelEditing.modelSlotRotationXIncrease(modelPartTarget);
                        } else {
                            dynamicEntityModelEditing.modelSlotRotationXDecrease(modelPartTarget);
                        }
                        updateJavaModelPartRotationX(modelPartTarget);
                    }
                }
            }
            if (EDITING_X_MODE_MOUSE_Y) {
                if (movePositionScreenMode) {
                    if (mouseYMovingUp) {
                        if (isReversedX) {
                            dynamicEntityModelEditing.modelSlotPositionXDecrease(modelPartTarget);
                        } else {
                            dynamicEntityModelEditing.modelSlotPositionXIncrease(modelPartTarget);
                        }
                        updateJavaModelPartPositionX(modelPartTarget);
                    } else if (mouseYMovingDown) {
                        if (isReversedX) {
                            dynamicEntityModelEditing.modelSlotPositionXIncrease(modelPartTarget);
                        } else {
                            dynamicEntityModelEditing.modelSlotPositionXDecrease(modelPartTarget);
                        }
                        updateJavaModelPartPositionX(modelPartTarget);
                    }
                } else if (resizeScreenMode) {
                    if (mouseYMovingUp) {
                        if (isReversedX) {
                            dynamicEntityModelEditing.modelSlotSizeXDecrease(modelPartTarget);
                        } else {
                            dynamicEntityModelEditing.modelSlotSizeXIncrease(modelPartTarget);
                        }
                        updateJavaModelPartSizeX(modelPartTarget);
                    } else if (mouseYMovingDown) {
                        if (isReversedX) {
                            dynamicEntityModelEditing.modelSlotSizeXIncrease(modelPartTarget);
                        } else {
                            dynamicEntityModelEditing.modelSlotSizeXDecrease(modelPartTarget);

                        }
                        updateJavaModelPartSizeX(modelPartTarget);
                    }
                } else if (rotateScreenMode) {
                    if (mouseYMovingUp) {
                        if (isReversedX) {
                            dynamicEntityModelEditing.modelSlotRotationXDecrease(modelPartTarget);
                        } else {
                            dynamicEntityModelEditing.modelSlotRotationXIncrease(modelPartTarget);
                        }
                        updateJavaModelPartRotationX(modelPartTarget);
                    } else if (mouseYMovingDown) {
                        if (isReversedX) {
                            dynamicEntityModelEditing.modelSlotRotationXIncrease(modelPartTarget);
                        } else {
                            dynamicEntityModelEditing.modelSlotRotationXDecrease(modelPartTarget);
                        }
                        updateJavaModelPartRotationX(modelPartTarget);
                    }
                }
            }
            if (EDITING_Y_MODE_MOUSE_Y) {
                if (movePositionScreenMode) {
                    if (mouseYMovingUp) {
                        dynamicEntityModelEditing.modelSlotPositionYIncrease(modelPartTarget);
                        updateJavaModelPartPositionY(modelPartTarget);
                    } else if (mouseYMovingDown) {
                        dynamicEntityModelEditing.modelSlotPositionYDecrease(modelPartTarget);
                        updateJavaModelPartPositionY(modelPartTarget);
                    }
                } else if (resizeScreenMode) {
                    if (mouseYMovingUp) {
                        dynamicEntityModelEditing.modelSlotSizeYIncrease(modelPartTarget);
                        updateJavaModelPartSizeY(modelPartTarget);
                    } else if (mouseYMovingDown) {
                        dynamicEntityModelEditing.modelSlotSizeYDecrease(modelPartTarget);
                        updateJavaModelPartSizeY(modelPartTarget);
                    }
                } else if (rotateScreenMode) {
                    if (mouseYMovingUp) {
                        dynamicEntityModelEditing.modelSlotRotationYIncrease(modelPartTarget);
                        updateJavaModelPartRotationY(modelPartTarget);
                    } else if (mouseYMovingDown) {
                        dynamicEntityModelEditing.modelSlotRotationYDecrease(modelPartTarget);
                        updateJavaModelPartRotationY(modelPartTarget);
                    }
                }
            }
            if (EDITING_Z_MODE_MOUSE_X) {
                if (movePositionScreenMode) {
                    if (mouseXMovingRight) {
                        if (isReversedZ) {
                            dynamicEntityModelEditing.modelSlotPositionZDecrease(modelPartTarget);
                        } else {
                            dynamicEntityModelEditing.modelSlotPositionZIncrease(modelPartTarget);
                        }
                        updateJavaModelPartPositionZ(modelPartTarget);
                    } else if (mouseXMovingLeft) {
                        if (isReversedZ) {
                            dynamicEntityModelEditing.modelSlotPositionZIncrease(modelPartTarget);
                        } else {
                            dynamicEntityModelEditing.modelSlotPositionZDecrease(modelPartTarget);
                        }
                        updateJavaModelPartPositionZ(modelPartTarget);
                    }
                } else if (resizeScreenMode) {
                    if (mouseXMovingRight) {
                        if (isReversedZ) {
                            dynamicEntityModelEditing.modelSlotSizeZDecrease(modelPartTarget);
                        } else {
                            dynamicEntityModelEditing.modelSlotSizeZIncrease(modelPartTarget);
                        }
                        updateJavaModelPartSizeZ(modelPartTarget);
                    } else if (mouseXMovingLeft) {
                        if (isReversedZ) {
                            dynamicEntityModelEditing.modelSlotSizeZIncrease(modelPartTarget);
                        } else {
                            dynamicEntityModelEditing.modelSlotSizeZDecrease(modelPartTarget);
                        }
                        updateJavaModelPartSizeZ(modelPartTarget);
                    }
                } else if (rotateScreenMode) {
                    if (mouseXMovingRight) {
                        if (isReversedZ) {
                            dynamicEntityModelEditing.modelSlotRotationZDecrease(modelPartTarget);
                        } else {
                            dynamicEntityModelEditing.modelSlotRotationZIncrease(modelPartTarget);
                        }
                        updateJavaModelPartRotationZ(modelPartTarget);
                    } else if (mouseXMovingLeft) {
                        if (isReversedZ) {
                            dynamicEntityModelEditing.modelSlotRotationZIncrease(modelPartTarget);
                        } else {
                            dynamicEntityModelEditing.modelSlotRotationZDecrease(modelPartTarget);
                        }
                        updateJavaModelPartRotationZ(modelPartTarget);
                    }
                }
            }
            if (EDITING_Z_MODE_MOUSE_Y) {
                if (movePositionScreenMode) {
                    if (mouseYMovingUp) {
                        if (isReversedZ) {
                            dynamicEntityModelEditing.modelSlotPositionZDecrease(modelPartTarget);
                        } else {
                            dynamicEntityModelEditing.modelSlotPositionZIncrease(modelPartTarget);
                        }
                        updateJavaModelPartPositionZ(modelPartTarget);
                    } else if (mouseYMovingDown) {
                        if (isReversedZ) {
                            dynamicEntityModelEditing.modelSlotPositionZIncrease(modelPartTarget);
                        } else {
                            dynamicEntityModelEditing.modelSlotPositionZDecrease(modelPartTarget);
                        }
                        updateJavaModelPartPositionZ(modelPartTarget);
                    }
                } else if (resizeScreenMode) {
                    if (mouseYMovingUp) {
                        if (isReversedZ) {
                            dynamicEntityModelEditing.modelSlotSizeZDecrease(modelPartTarget);
                        } else {
                            dynamicEntityModelEditing.modelSlotSizeZIncrease(modelPartTarget);
                        }
                        updateJavaModelPartSizeZ(modelPartTarget);
                    } else if (mouseYMovingDown) {
                        if (isReversedZ) {
                            dynamicEntityModelEditing.modelSlotSizeZIncrease(modelPartTarget);
                        } else {
                            dynamicEntityModelEditing.modelSlotSizeZDecrease(modelPartTarget);
                        }
                        updateJavaModelPartSizeZ(modelPartTarget);
                    }
                } else if (rotateScreenMode) {
                    if (mouseYMovingUp) {
                        if (isReversedZ) {
                            dynamicEntityModelEditing.modelSlotRotationZDecrease(modelPartTarget);
                        } else {
                            dynamicEntityModelEditing.modelSlotRotationZIncrease(modelPartTarget);
                        }
                        updateJavaModelPartRotationZ(modelPartTarget);
                    } else if (mouseYMovingDown) {
                        if (isReversedZ) {
                            dynamicEntityModelEditing.modelSlotRotationZIncrease(modelPartTarget);
                        } else {
                            dynamicEntityModelEditing.modelSlotRotationZDecrease(modelPartTarget);
                        }
                        updateJavaModelPartRotationZ(modelPartTarget);
                    }
                }
            }
        } else {
            if (button == 0) {
                cameraYaw -= (float) deltaX * 0.5f;
                cameraPitch += (float) deltaY * 0.55f;
            }
            if (button == 1) {
                cameraX -= (float) deltaX * 0.1f;
                cameraY += (float) deltaY * 0.1f;
            }
        }
        mouseXLast = mouseX;
        mouseYLast = mouseY;
        return super.mouseDragged2(mouseX, mouseY, button, deltaX, deltaY);
    }

    public void drawLiquidBar(GraphicsHolder guiGraphics) {
        int width = 960;
        int height = 54;
        if (Objects.equals(ThemeSelectorList.getUISkinExtraImageEnum(), "creatorminecraft:textures/gui/liquid_ui_black_bar.png")) {
            //guiGraphics.blit(new Identifier("creatorminecraft:textures/gui/liquid_ui_black_bar.png"), 0, 0, 0, 0, width, height, width, height);
            final GuiDrawing guiDrawing = new GuiDrawing(guiGraphics);
            guiDrawing.beginDrawingTexture(new Identifier("creatorminecraft:textures/gui/liquid_ui_black_bar.png"));
            GraphicsRender.drawTexture(guiDrawing, 0, 0, (float) 0, 0, width, height, width, height);
            guiDrawing.finishDrawingTexture();
        }
    }

    public void drawLiquidBarIcon(GraphicsHolder guiGraphics) {
        int width = 15;
        int height = 17;
        if (Objects.equals(ThemeSelectorList.getUISkinExtraImageEnum(), "creatorminecraft:textures/gui/liquid_ui_black_bar.png")) {
            //guiGraphics.blit(new Identifier("creatorminecraft:textures/gui/model_creator_icon.png"), 5, 4, 0, 0, width, height, width, height);
            final GuiDrawing guiDrawing = new GuiDrawing(guiGraphics);
            guiDrawing.beginDrawingTexture(new Identifier("creatorminecraft:textures/gui/model_creator_icon.png"));
            GraphicsRender.drawTexture(guiDrawing, 5, 4, (float) 0, 0, width, height, width, height);
            guiDrawing.finishDrawingTexture();
        }
    }


    @Override
    public boolean mouseScrolled3(double mouseX, double mouseY, double amount) {
        if (isModelEditing && isModelEditGui) {
                if (amount < 0) {
                    modelPartTarget += 1;
                    if (modelPartTarget >= DynamicEntityModelEditing.getModelListSize()) {
                        modelPartTarget = DynamicEntityModelEditing.getModelListSize() - 1;
                    }
                } else {
                    modelPartTarget -= 1;
                    if (modelPartTarget < 0) {
                        modelPartTarget = 0;
                    }
                }
        } else {
            double sensitivity = 1.5;
            cameraZ -= amount * sensitivity;
            cameraZ = Math.max(cameraZ, -50.1f);
            cameraZ = Math.min(cameraZ, 100.0f);
        }
        return super.mouseScrolled3(mouseX, mouseY, amount);
    }

    @Override
    public void render(GraphicsHolder graphicsHolder, int mouseX, int mouseY, float tickDelta) {
        final MinecraftClient minecraft = MinecraftClient.getInstance();
        final int rightSideX = width - BIG_SQUARE_SIZE * 4;
        minecraft.getOptionsMapped().setHudHiddenMapped(true);
        drawLiquidBar(graphicsHolder);
        drawLiquidBarIcon(graphicsHolder);
        if (isModelEditGui) {
            onModelEditGui(true);
            creatorMinecraftScreenWindows.renderModelEditorGui(graphicsHolder, height, width);
            modelOutlinerList.renderHighlight(graphicsHolder);
            modelOutlinerList.render(graphicsHolder);
            modelOutlinerList.x = rightSideX + BIG_SQUARE_SIZE * 3 + STANDARD_SIZE - STANDARD_SIZE * 2 * 3 - BIG_SQUARE_SIZE - 30;
            modelOutlinerList.y = STANDARD_SIZE * 10 + 13;
        } else {
            onModelEditGui(false);
        }
        super.render(graphicsHolder, mouseX, mouseY, tickDelta);
    }

    public static void render(GraphicsHolder poseStack) {
        if (GUI_RENDER_GRAPHICS) {
            renderGraphicsBackground(poseStack);
            
            poseStack.push();
            poseStack.translate(-cameraX, -cameraY, -cameraZ);
            poseStack.rotateXDegrees(cameraPitch);
            poseStack.rotateYDegrees(cameraYaw);
            poseStack.rotateZDegrees(0);

            if (isModelEditGui) {
                if (movePositionScreenMode) {
                    poseStack.push();
                    MovementArrows movementArrows = new MovementArrows();
                    poseStack.push();
                    poseStack.translate(0, 0, 0);
                    movementArrows.render(poseStack, 0xF000F0, 0);
                    poseStack.pop();
                } else if (resizeScreenMode) {
                    poseStack.push();
                    ResizeArrows resizeArrows = new ResizeArrows();
                    poseStack.push();
                    poseStack.translate(0, 0, 0);
                    resizeArrows.render(poseStack, 0xF000F0, 0);
                    poseStack.pop();
                } else if (rotateScreenMode) {
                    poseStack.push();
                    RotationArrows rotationArrows = new RotationArrows();
                    poseStack.push();
                    poseStack.translate(0, 0, 0);
                    rotationArrows.render(poseStack, 0xF000F0, 0);
                    poseStack.pop();
                }
            }

            poseStack.push();
            DynamicEntityModelEditing dynamicEntityModelEditing = new DynamicEntityModelEditing();
            poseStack.push();
            poseStack.translate(0, 0, 0);
            dynamicEntityModelEditing.render(poseStack, 0xF000F0, 0);
            poseStack.pop();

            GridModel gridModel = new GridModel();
            poseStack.push();
            poseStack.translate(0, 0, 0);
            gridModel.render(poseStack, 0xF600F0, 0);
            poseStack.pop();

            ModelXZPlane modelXZPlane = new ModelXZPlane();
            poseStack.push();
            poseStack.translate(0, 0.98f, 0);
            modelXZPlane.render(poseStack, 0xF000F0, 0);
            poseStack.pop();
            poseStack.pop();
        }
    }

    public static void renderGraphicsBackground(GraphicsHolder graphicsHolder) {
        final GuiDrawing guiDrawing = new GuiDrawing(graphicsHolder);
        guiDrawing.beginDrawingRectangle();
        guiDrawing.drawRectangle(Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, 0xFF000000 | 0xFF4D4D4D);
        guiDrawing.finishDrawingRectangle();
        GraphicsRender.graphicsBackgroundDraw(graphicsHolder, 0xFF000000 | 0xFF4D4D4D, 0xF000F0);
    }

    @Override
    public boolean keyPressed2(int keyCode, int scanCode, int modifiers) {
        if (keyCode == GLFW.GLFW_KEY_BACKSPACE) {
            super.keyPressed2(keyCode, scanCode, modifiers);
        }
        if (keyCode == 256 && this.shouldCloseOnEsc2()) {
            this.onClose2();
            return true;
        }

        if (keyCode == GLFW.GLFW_KEY_ENTER && textFieldPosX.isFocused2()) {
            setJavaModelPartPositionX();
        }

        if (keyCode == GLFW.GLFW_KEY_ENTER && textFieldPosY.isFocused2()) {
            setJavaModelPartPositionY();
        }

        if (keyCode == GLFW.GLFW_KEY_ENTER && textFieldPosZ.isFocused2()) {
            setJavaModelPartPositionZ();
        }

        if (keyCode == GLFW.GLFW_KEY_ENTER && textFieldSizeX.isFocused2()) {
            setJavaModelPartSizeX();
        }

        if (keyCode == GLFW.GLFW_KEY_ENTER && textFieldSizeY.isFocused2()) {
            setJavaModelPartSizeY();
        }

        if (keyCode == GLFW.GLFW_KEY_ENTER && textFieldSizeZ.isFocused2()) {
            setJavaModelPartSizeZ();
        }

        if (keyCode == GLFW.GLFW_KEY_ENTER && textFieldPivotX.isFocused2()) {
            setJavaModelPartPivotX();
        }

        if (keyCode == GLFW.GLFW_KEY_ENTER && textFieldPivotY.isFocused2()) {
            setJavaModelPartPivotY();
        }

        if (keyCode == GLFW.GLFW_KEY_ENTER && textFieldPivotZ.isFocused2()) {
            setJavaModelPartPivotZ();
        }

        if (keyCode == GLFW.GLFW_KEY_ENTER && textFieldRotationX.isFocused2()) {
            setJavaModelPartRotationX();
        }

        if (keyCode == GLFW.GLFW_KEY_ENTER && textFieldRotationY.isFocused2()) {
            setJavaModelPartRotationY();
        }

        if (keyCode == GLFW.GLFW_KEY_ENTER && textFieldRotationZ.isFocused2()) {
            setJavaModelPartRotationZ();
        }

        if (keyCode == GLFW.GLFW_KEY_ENTER && textFieldInflate.isFocused2()) {
            setJavaModelPartInflate();
        }

        if (keyCode == GLFW.GLFW_KEY_UP) {
            modelPartTarget -= 1;
            if (modelPartTarget < 0) {
                modelPartTarget = 0;
            }
            updateJavaModelPartPosition();
        }

        if (keyCode == GLFW.GLFW_KEY_DOWN) {
            modelPartTarget += 1;
            if (modelPartTarget >= DynamicEntityModelEditing.getModelListSize()) {
                modelPartTarget = DynamicEntityModelEditing.getModelListSize() - 1;
            }
            updateJavaModelPartPosition();
        }
        if (keyCode == GLFW.GLFW_KEY_TAB) {
            isModelEditing = !isModelEditing;
        }

        if (keyCode == GLFW.GLFW_KEY_1) {
            movePositionScreenMode = true;
            resizeScreenMode = false;
            rotateScreenMode = false;
            dynamicEntityModelEditing.screenGUIToolMode();
        } else if (keyCode == GLFW.GLFW_KEY_2) {
            movePositionScreenMode = false;
            resizeScreenMode = true;
            rotateScreenMode = false;
            dynamicEntityModelEditing.screenGUIToolMode();
        } else if (keyCode == GLFW.GLFW_KEY_3) {
            movePositionScreenMode = false;
            resizeScreenMode = false;
            rotateScreenMode = true;
            dynamicEntityModelEditing.screenGUIToolMode();
        }

        if (keyCode == GLFW.GLFW_KEY_DELETE) {
            dynamicEntityModelEditing.removeModelSlot(modelPartTarget);
            updateJavaModelPartPosition();
        }
        return false;
    }

    @Override
    public void tick2() {

        if (ModelOutlinerList.updateModelPartData) {
            updateJavaModelPartPosition();
            ModelOutlinerList.updateModelPartData = false;
        }

        if (creatorScreenManager.fileMenuState == 1) {
            if (!buttonFileSettings.isHovered2() && !creatorScreenManager.buttonNewProject.isHovered2() && !creatorScreenManager.buttonSaveAsProject.isHovered2() && !creatorScreenManager.buttonSaveProject.isHovered2()) {
                creatorScreenManager.onFileMenu();
            }
        }

        if (creatorScreenManager.editMenuState == 1) {
            if (!buttonEditSettings.isHovered2() && !creatorScreenManager.buttonModelEditGui.isHovered2() && !creatorScreenManager.buttonBlockbenchMode.isHovered2()) {
                creatorScreenManager.onEditMenu();
            }
        }

        if (creatorScreenManager.viewMenuState == 1) {
            if (!buttonViewerSettings.isHovered2() && !creatorScreenManager.buttonScreenshotMode.isHovered2() && !creatorScreenManager.buttonCameraReset.isHovered2() && !creatorScreenManager.buttonPlayerCamera.isHovered2()) {
                creatorScreenManager.onViewMenu();
            }
        }

        if (creatorScreenManager.renderMenuState == 1) {
            if (!buttonRenderSettings.isHovered2() && !creatorScreenManager.buttonShaderRender.isHovered2() && !creatorScreenManager.buttonColorBlendRender.isHovered2()) {
                creatorScreenManager.onRenderMenu();
            }
        }

        if (creatorScreenManager.windowMenuState == 1) {
            if (!buttonWindowSettings.isHovered2() && !creatorScreenManager.buttonCameraPresets.isHovered2()) {
                creatorScreenManager.onWindowMenu();
            }
        }

        if (cameraPitch < 220) {
            cameraYaw %= 360;
            if (cameraYaw < 45) {
                EDITING_X_MODE_MOUSE_X = true;
                EDITING_Y_MODE_MOUSE_Y = true;
                EDITING_Z_MODE_MOUSE_X = false;
                EDITING_X_MODE_MOUSE_Y = false;
                EDITING_Z_MODE_MOUSE_Y = false;
                isReversedX = false;
                isReversedZ = false;
            } else if (cameraYaw < 90) {
                EDITING_X_MODE_MOUSE_X = false;
                EDITING_Y_MODE_MOUSE_Y = true;
                EDITING_Z_MODE_MOUSE_X = true;
                EDITING_X_MODE_MOUSE_Y = false;
                EDITING_Z_MODE_MOUSE_Y = false;
                isReversedX = true;
                isReversedZ = false;
            } else if (cameraYaw < 135) {
                EDITING_X_MODE_MOUSE_X = false;
                EDITING_Y_MODE_MOUSE_Y = true;
                EDITING_Z_MODE_MOUSE_X = true;
                EDITING_X_MODE_MOUSE_Y = false;
                EDITING_Z_MODE_MOUSE_Y = false;
                isReversedX = true;
                isReversedZ = false;
            } else if (cameraYaw < 180) {
                EDITING_X_MODE_MOUSE_X = true;
                EDITING_Y_MODE_MOUSE_Y = true;
                EDITING_Z_MODE_MOUSE_X = false;
                EDITING_X_MODE_MOUSE_Y = false;
                EDITING_Z_MODE_MOUSE_Y = false;
                isReversedX = true;
                isReversedZ = false;
            } else if (cameraYaw < 225) {
                EDITING_X_MODE_MOUSE_X = true;
                EDITING_Y_MODE_MOUSE_Y = true;
                EDITING_Z_MODE_MOUSE_X = false;
                EDITING_X_MODE_MOUSE_Y = false;
                EDITING_Z_MODE_MOUSE_Y = false;
                isReversedX = true;
                isReversedZ = false;
            } else if (cameraYaw < 270) {
                EDITING_X_MODE_MOUSE_X = false;
                EDITING_Y_MODE_MOUSE_Y = true;
                EDITING_Z_MODE_MOUSE_X = true;
                EDITING_X_MODE_MOUSE_Y = false;
                EDITING_Z_MODE_MOUSE_Y = false;
                isReversedX = false;
                isReversedZ = true  ;
            } else if (cameraYaw < 305) {
                EDITING_X_MODE_MOUSE_X = false;
                EDITING_Y_MODE_MOUSE_Y = true;
                EDITING_Z_MODE_MOUSE_X = true;
                EDITING_X_MODE_MOUSE_Y = false;
                EDITING_Z_MODE_MOUSE_Y = false;
                isReversedX = false;
                isReversedZ = true;
        } else if (cameraYaw < 350) {
                EDITING_X_MODE_MOUSE_X = true;
                EDITING_Y_MODE_MOUSE_Y = true;
                EDITING_Z_MODE_MOUSE_X = false;
                EDITING_X_MODE_MOUSE_Y = false;
                EDITING_Z_MODE_MOUSE_Y = false;
                isReversedX = false;
                isReversedZ = false;
            }
            if (cameraYaw < 0) {
                cameraYaw += 360;
            }
            cameraYaw %= 360;
        } else if (cameraPitch < 310) {
            cameraYaw %= 360;
            if (cameraYaw < 45) {
                EDITING_X_MODE_MOUSE_X = true;
                EDITING_Y_MODE_MOUSE_Y = false;
                EDITING_Z_MODE_MOUSE_Y = true;
                EDITING_X_MODE_MOUSE_Y = false;
                EDITING_Z_MODE_MOUSE_X = false;
                isReversedX = false;
                isReversedZ = true;
            } else if (cameraYaw < 90) {
                EDITING_X_MODE_MOUSE_Y = true;
                EDITING_Y_MODE_MOUSE_Y = false;
                EDITING_Z_MODE_MOUSE_X = true;
                EDITING_X_MODE_MOUSE_X = false;
                EDITING_Z_MODE_MOUSE_Y = false;
                isReversedX = false;
                isReversedZ = false;
            } else if (cameraYaw < 135) {
                EDITING_X_MODE_MOUSE_Y = true;
                EDITING_Y_MODE_MOUSE_Y = false;
                EDITING_Z_MODE_MOUSE_X = true;
                EDITING_X_MODE_MOUSE_X = false;
                EDITING_Z_MODE_MOUSE_Y = false;
                isReversedX = false;
                isReversedZ = false;
            } else if (cameraYaw < 180) {
                EDITING_X_MODE_MOUSE_X = true;
                EDITING_Y_MODE_MOUSE_Y = false;
                EDITING_Z_MODE_MOUSE_Y = true;
                EDITING_X_MODE_MOUSE_Y = false;
                EDITING_Z_MODE_MOUSE_X = false;
                isReversedX = true;
                isReversedZ = false;
            } else if (cameraYaw < 225) {
                EDITING_X_MODE_MOUSE_X = true;
                EDITING_Y_MODE_MOUSE_Y = false;
                EDITING_Z_MODE_MOUSE_Y = true;
                EDITING_X_MODE_MOUSE_Y = false;
                EDITING_Z_MODE_MOUSE_X = false;
                isReversedX = true;
                isReversedZ = false;
            } else if (cameraYaw < 270) {
                EDITING_X_MODE_MOUSE_Y = true;
                EDITING_Y_MODE_MOUSE_Y = false;
                EDITING_Z_MODE_MOUSE_X = true;
                EDITING_X_MODE_MOUSE_X = false;
                EDITING_Z_MODE_MOUSE_Y = false;
                isReversedX = true;
                isReversedZ = true;
            } else if (cameraYaw < 305) {
                EDITING_X_MODE_MOUSE_Y = true;
                EDITING_Y_MODE_MOUSE_Y = false;
                EDITING_Z_MODE_MOUSE_X = true;
                EDITING_X_MODE_MOUSE_X = false;
                EDITING_Z_MODE_MOUSE_Y = false;
                isReversedX = true;
                isReversedZ = true;
            } else if (cameraYaw < 350) {
                EDITING_X_MODE_MOUSE_X = true;
                EDITING_Y_MODE_MOUSE_Y = false;
                EDITING_Z_MODE_MOUSE_Y = true;
                EDITING_X_MODE_MOUSE_Y = false;
                EDITING_Z_MODE_MOUSE_X = false;
                isReversedX = false;
                isReversedZ = true;
            }
            if (cameraYaw < 0) {
                cameraYaw += 360;
            }
            cameraYaw %= 360;
        }

        super.tick2();
    }

    @Override
    public void onClose2() {
        super.onClose2();
        MinecraftClient.getInstance().getOptionsMapped().setHudHiddenMapped(false);
        GUI_RENDER_GRAPHICS = false;
        creatorScreenManager.fileMenuState = 0;
        creatorScreenManager.editMenuState = 0;
        creatorScreenManager.viewMenuState = 0;
        creatorScreenManager.renderMenuState = 0;
        creatorScreenManager.windowMenuState = 0;
    }

    @Override
    public boolean isPauseScreen2() {
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
        buttonModelPartPositionIcon.visible = isModelEditGui && creatorScreenManager.fileMenuState != 1;
        buttonModelPartSizeIcon.visible = isModelEditGui && creatorScreenManager.fileMenuState != 1;
        buttonModelPartRotationIcon.visible = isModelEditGui && creatorScreenManager.fileMenuState != 1;
        textFieldPivotX.visible = isModelEditGui;
        textFieldPivotY.visible = isModelEditGui;
        textFieldPivotZ.visible = isModelEditGui;
        textFieldRotationX.visible = isModelEditGui;
        textFieldRotationY.visible = isModelEditGui;
        textFieldRotationZ.visible = isModelEditGui;
    }
}
