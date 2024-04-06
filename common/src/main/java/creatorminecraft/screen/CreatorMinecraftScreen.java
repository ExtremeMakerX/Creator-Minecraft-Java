package creatorminecraft.screen;

import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import creatorminecraft.client.WidgetSetter;
import creatorminecraft.client.CreatorIGUI;
import creatorminecraft.gui.CreatorMinecraftEditBox;
import creatorminecraft.gui.ModelOutlinerList;
import creatorminecraft.gui.ThemeSelectorList;
import creatorminecraft.model.*;
import creatorminecraft.render.GraphicsRender;
import creatorminecraft.widgets.DynamicButtonCMJ;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.lwjgl.glfw.GLFW;

import java.util.Objects;

public class CreatorMinecraftScreen extends Screen implements CreatorIGUI {

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
    public final ImageButton buttonCreateModelIcon;
    public final ImageButton buttonCreateFolderIcon;
    private final ImageButton buttonExitModelEditor;
    private final ImageButton buttonModelPartPositionIcon;
    private final ImageButton buttonModelPartSizeIcon;
    private final ImageButton buttonModelPartRotationIcon;
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
        buttonExitModelEditor = new ImageButton(0, 0, 0, STANDARD_SIZE, new WidgetSprites(new ResourceLocation("widget/icon_exit"), new ResourceLocation("widget/icon_exit_highlighted")),button -> {
            isModelEditGui = false;
        });
        buttonCreateModelIcon = new ImageButton(0, 0, 0, STANDARD_SIZE, new WidgetSprites(new ResourceLocation("widget/icon_add_cube"), new ResourceLocation("widget/icon_add_cube_highlighted")), button -> {
            dynamicEntityModelEditing.addModelSlot();
        });
        buttonCreateFolderIcon = new ImageButton(0, 0, 0, STANDARD_SIZE, new WidgetSprites(new ResourceLocation("widget/icon_add_folder"), new ResourceLocation("widget/icon_add_folder_highlighted")), button -> {
        });
        buttonModelPartPositionIcon = new ImageButton(0, 0, 0, STANDARD_SIZE, new WidgetSprites(new ResourceLocation("widget/modelpart_move_icon"), new ResourceLocation("widget/modelpart_move_icon_highlighted")), button -> {
            movePositionScreenMode = true;
            resizeScreenMode = false;
            rotateScreenMode = false;
            dynamicEntityModelEditing.screenGUIToolMode();
        });
        buttonModelPartSizeIcon = new ImageButton(0, 0, 0, STANDARD_SIZE, new WidgetSprites(new ResourceLocation("widget/modelpart_resize_icon"), new ResourceLocation("widget/modelpart_resize_icon_highlighted")), button -> {
            resizeScreenMode = true;
            movePositionScreenMode = false;
            rotateScreenMode = false;
            dynamicEntityModelEditing.screenGUIToolMode();
        });
        buttonModelPartRotationIcon = new ImageButton(0, 0, 0, STANDARD_SIZE, new WidgetSprites(new ResourceLocation("widget/modelpart_rotation_icon"), new ResourceLocation("widget/modelpart_rotation_icon_highlighted")), button -> {
            rotateScreenMode = true;
            movePositionScreenMode = false;
            resizeScreenMode = false;
            dynamicEntityModelEditing.screenGUIToolMode();
        });
        new CreatorScreenManager();
    }

    @Override
    protected void init() {
        final int rightSideX = width - BIG_SQUARE_SIZE * 4;
        final int standardMenuWidth = width / 3 + BIG_SQUARE_SIZE;
        int UIDynamicX = 0;
        int adaptToLiquidUI = 0;
        int adaptToLiquidUIModelIcons = 0;

        if (ThemeSelectorList.getUIWidgetIsLiquidUIEnum()) {
            adaptToLiquidUI = 35;
            adaptToLiquidUIModelIcons = 5;
        }
        WidgetSetter.setAdjustableWidget(buttonFileSettings, UIDynamicX + adaptToLiquidUI, 0, standardMenuWidth / 4);
        WidgetSetter.setAdjustableWidget(buttonEditSettings, UIDynamicX + standardMenuWidth / 4 + adaptToLiquidUI, 0, standardMenuWidth / 4);
        WidgetSetter.setAdjustableWidget(buttonViewerSettings, UIDynamicX + standardMenuWidth / 4 * 2 + adaptToLiquidUI, 0, standardMenuWidth / 4);
        WidgetSetter.setAdjustableWidget(buttonRenderSettings, UIDynamicX + standardMenuWidth / 4 * 3 + adaptToLiquidUI, 0, standardMenuWidth / 4);
        WidgetSetter.setAdjustableWidget(buttonWindowSettings, UIDynamicX + standardMenuWidth / 4 * 4 + adaptToLiquidUI, 0, standardMenuWidth / 4);
        WidgetSetter.setAdjustableWidget(buttonSettings, UIDynamicX + standardMenuWidth / 4 * 5 + adaptToLiquidUI, 0, standardMenuWidth / 4);

        WidgetSetter.setAdjustableWidget(creatorScreenManager.buttonNewProject, 0 , STANDARD_SIZE, standardMenuWidth / 4);
        WidgetSetter.setAdjustableWidget(creatorScreenManager.buttonSaveProject, 0, STANDARD_SIZE * 2, standardMenuWidth / 4);
        WidgetSetter.setAdjustableWidget(creatorScreenManager.buttonSaveAsProject, 0, STANDARD_SIZE * 3, standardMenuWidth / 4);

        WidgetSetter.setAdjustableWidget(creatorScreenManager.buttonModelEditGui, standardMenuWidth / 4 + adaptToLiquidUI, STANDARD_SIZE, standardMenuWidth / 4);
        WidgetSetter.setAdjustableWidget(creatorScreenManager.buttonBlockbenchMode, standardMenuWidth / 4 + adaptToLiquidUI, STANDARD_SIZE * 2, standardMenuWidth / 4);

        WidgetSetter.setAdjustableWidget(creatorScreenManager.buttonCameraReset, standardMenuWidth / 4 * 2 + adaptToLiquidUI, STANDARD_SIZE, standardMenuWidth / 4);
        WidgetSetter.setAdjustableWidget(creatorScreenManager.buttonScreenshotMode, standardMenuWidth / 4 * 2 + adaptToLiquidUI, STANDARD_SIZE * 2, standardMenuWidth / 4);
        WidgetSetter.setAdjustableWidget(creatorScreenManager.buttonPlayerCamera, standardMenuWidth / 4 * 2 + adaptToLiquidUI, STANDARD_SIZE * 3, standardMenuWidth / 4);
        //ButtonSetter.setPositionAndWidth(creatorScreenManager.buttonExitScreenshotMode, standardMenuWidth / 4 * 2, STANDARD_SIZE * 4, standardMenuWidth / 4);

        WidgetSetter.setAdjustableWidget(creatorScreenManager.buttonShaderRender, standardMenuWidth / 4 * 3 + adaptToLiquidUI, STANDARD_SIZE, standardMenuWidth / 4);
        WidgetSetter.setAdjustableWidget(creatorScreenManager.buttonColorBlendRender, standardMenuWidth / 4 * 3 + adaptToLiquidUI, STANDARD_SIZE * 2, standardMenuWidth / 4);

        WidgetSetter.setAdjustableWidget(creatorScreenManager.buttonCameraPresets, standardMenuWidth / 4 * 4 + adaptToLiquidUI, STANDARD_SIZE, standardMenuWidth / 4);

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

        WidgetSetter.setAdjustableWidget(buttonCreateModelIcon, rightSideX + BIG_SQUARE_SIZE * 3 + STANDARD_SIZE - STANDARD_SIZE * 2 * 3 - 30, STANDARD_SIZE * 8 + 6, STANDARD_SIZE);
        //WidgetSetter.setAdjustableWidget(buttonCreateFolderIcon, rightSideX + BIG_SQUARE_SIZE * 3 + STANDARD_SIZE - STANDARD_SIZE * 2 * 3 - 30, STANDARD_SIZE * 8 + 6, STANDARD_SIZE);

        WidgetSetter.setAdjustableWidget(buttonModelPartPositionIcon, UIDynamicX, STANDARD_SIZE + adaptToLiquidUIModelIcons, STANDARD_SIZE);
        WidgetSetter.setAdjustableWidget(buttonModelPartSizeIcon, UIDynamicX + STANDARD_SIZE, STANDARD_SIZE + adaptToLiquidUIModelIcons, STANDARD_SIZE);
        WidgetSetter.setAdjustableWidget(buttonModelPartRotationIcon, UIDynamicX + STANDARD_SIZE * 2, STANDARD_SIZE + adaptToLiquidUIModelIcons, STANDARD_SIZE);

        addRenderableWidget(buttonFileSettings);
        addRenderableWidget(buttonEditSettings);
        addRenderableWidget(buttonViewerSettings);
        addRenderableWidget(buttonRenderSettings);
        addRenderableWidget(buttonWindowSettings);
        addRenderableWidget(buttonSettings);

        //addRenderableWidget(creatorScreenManager.buttonNewProject);
        //addRenderableWidget(creatorScreenManager.buttonSaveProject);
        //addRenderableWidget(creatorScreenManager.buttonSaveAsProject);

        addRenderableWidget(creatorScreenManager.buttonModelEditGui);
        //addRenderableWidget(creatorScreenManager.buttonBlockbenchMode);

        addRenderableWidget(creatorScreenManager.buttonCameraReset);
        //addRenderableWidget(creatorScreenManager.buttonScreenshotMode);
        //addRenderableWidget(creatorScreenManager.buttonPlayerCamera);
        //addRenderableWidget(creatorScreenManager.buttonExitScreenshotMode);

        //addRenderableWidget(creatorScreenManager.buttonShaderRender);
        //addRenderableWidget(creatorScreenManager.buttonColorBlendRender);

        //addRenderableWidget(creatorScreenManager.buttonCameraPresets);

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
        addRenderableWidget(buttonModelPartPositionIcon);
        addRenderableWidget(buttonModelPartSizeIcon);
        addRenderableWidget(buttonModelPartRotationIcon);
        updateJavaModelPartPosition();
        modelOutlinerList.setVisible(true);
        if (minecraft != null) {
            GUI_RENDER_GRAPHICS = true;
        }
    }

    @Override
    public void renderBackground(GuiGraphics guiGraphics, int i, int j, float f) {

    }

    public void updateJavaModelPartPosition() {
        dynamicEntityModelEditing.getModelSlotData(modelPartTarget);
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

    public void updateJavaModelPartIsVisible(int slot) {
        dynamicEntityModelEditing.getModelSlotData(slot);
        boolean isVisibleModel = DynamicEntityModelEditing.getModelSlotIsVisible();
    }

    public void setJavaModelPartPositionX() {
        positionXScreenValue = Float.parseFloat(textFieldPosX.getValue());
        dynamicEntityModelEditing.setModelSlotPositionX(modelPartTarget, positionXScreenValue);
        updateJavaModelPartPositionX(modelPartTarget);
    }

    public void setJavaModelPartPositionY() {
        positionYScreenValue = Float.parseFloat(textFieldPosY.getValue());
        dynamicEntityModelEditing.setModelSlotPositionY(modelPartTarget, positionYScreenValue);
        updateJavaModelPartPositionY(modelPartTarget);
    }

    public void setJavaModelPartPositionZ() {
        positionZScreenValue = Float.parseFloat(textFieldPosZ.getValue());
        dynamicEntityModelEditing.setModelSlotPositionZ(modelPartTarget, positionZScreenValue);
        updateJavaModelPartPositionZ(modelPartTarget);
    }

    public void setJavaModelPartSizeX() {
        sizeXScreenValue = Float.parseFloat(textFieldSizeX.getValue());
        dynamicEntityModelEditing.setModelSlotSizeX(modelPartTarget, sizeXScreenValue);
        updateJavaModelPartSizeX(modelPartTarget);
    }

    public void setJavaModelPartSizeY() {
        sizeYScreenValue = Float.parseFloat(textFieldSizeY.getValue());
        dynamicEntityModelEditing.setModelSlotSizeY(modelPartTarget, sizeYScreenValue);
        updateJavaModelPartSizeY(modelPartTarget);
    }

    public void setJavaModelPartSizeZ() {
        sizeZScreenValue = Float.parseFloat(textFieldSizeZ.getValue());
        dynamicEntityModelEditing.setModelSlotSizeZ(modelPartTarget, sizeZScreenValue);
        updateJavaModelPartSizeZ(modelPartTarget);
    }

    public void setJavaModelPartPivotX() {
        pivotXScreenValue = Float.parseFloat(textFieldPivotX.getValue());
        dynamicEntityModelEditing.setModelSlotPivotX(modelPartTarget, pivotXScreenValue);
        updateJavaModelPartPivotX(modelPartTarget);
    }

    public void setJavaModelPartPivotY() {
        pivotYScreenValue = Float.parseFloat(textFieldPosY.getValue());
        dynamicEntityModelEditing.setModelSlotPivotY(modelPartTarget, pivotYScreenValue);
        updateJavaModelPartPivotY(modelPartTarget);
    }

    public void setJavaModelPartPivotZ() {
        pivotZScreenValue = Float.parseFloat(textFieldPosZ.getValue());
        dynamicEntityModelEditing.setModelSlotPivotZ(modelPartTarget, pivotZScreenValue);
        updateJavaModelPartPivotZ(modelPartTarget);
    }

    public void setJavaModelPartRotationX() {
        rotationXScreenValue = Float.parseFloat(textFieldRotationX.getValue());
        dynamicEntityModelEditing.setModelSlotRotationX(modelPartTarget, rotationXScreenValue);
        updateJavaModelPartRotationX(modelPartTarget);
    }

    public void setJavaModelPartRotationY() {
        rotationYScreenValue = Float.parseFloat(textFieldRotationY.getValue());
        dynamicEntityModelEditing.setModelSlotRotationY(modelPartTarget, rotationYScreenValue);
        updateJavaModelPartRotationY(modelPartTarget);
    }

    public void setJavaModelPartRotationZ() {
        rotationZScreenValue = Float.parseFloat(textFieldRotationZ.getValue());
        dynamicEntityModelEditing.setModelSlotRotationZ(modelPartTarget, rotationZScreenValue);
        updateJavaModelPartRotationZ(modelPartTarget);
    }

    public void setJavaModelPartInflate() {
        inflateScreenValue = Float.parseFloat(textFieldInflate.getValue());
        dynamicEntityModelEditing.setModelSlotInflate(modelPartTarget, inflateScreenValue);
        updateJavaModelPartInflate(modelPartTarget);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        modelOutlinerList.mouseClicked(mouseX, mouseY, button);
        setFocused(false);
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public void mouseMoved(double mouseX, double mouseY) {
        new ModelPartBuilder.CubeMouseDetection((int) mouseX, (int) mouseY);
        super.mouseMoved(mouseX, mouseY);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
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
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }

    public void drawLiquidBar(GuiGraphics guiGraphics) {
        int width = 960;
        int height = 54;
        if (Objects.equals(ThemeSelectorList.getUISkinExtraImageEnum(), "creatorminecraft:textures/gui/liquid_ui_black_bar.png")) {
            guiGraphics.blit(new ResourceLocation("creatorminecraft:textures/gui/liquid_ui_black_bar.png"), 0, 0, 0, 0, width, height, width, height);
        }
    }

    public void drawLiquidBarIcon(GuiGraphics guiGraphics) {
        int width = 15;
        int height = 17;
        if (Objects.equals(ThemeSelectorList.getUISkinExtraImageEnum(), "creatorminecraft:textures/gui/liquid_ui_black_bar.png")) {
            guiGraphics.blit(new ResourceLocation("creatorminecraft:textures/gui/model_creator_icon.png"), 5, 4, 0, 0, width, height, width, height);
        }
    }


    @Override
    public boolean mouseScrolled(double d, double e, double f, double g) {
        if (isModelEditing && isModelEditGui) {
            if (g < 0) {
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
            cameraZ -= g * sensitivity;
            cameraZ = Math.max(cameraZ, -50.1f);
            cameraZ = Math.min(cameraZ, 100.0f);
        }
        return super.mouseScrolled(d, e, f, g);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float tickDelta) {
        final Minecraft minecraft = Minecraft.getInstance();
        final int rightSideX = width - BIG_SQUARE_SIZE * 4;
        minecraft.options.hideGui = true;
        drawLiquidBar(guiGraphics);
        drawLiquidBarIcon(guiGraphics);
        if (isModelEditGui) {
            onModelEditGui(true);
            creatorMinecraftScreenWindows.renderModelEditorGui(guiGraphics, font, height, width);
            modelOutlinerList.renderHighlight(guiGraphics);
            modelOutlinerList.render(guiGraphics, font);
            modelOutlinerList.x = rightSideX + BIG_SQUARE_SIZE * 3 + STANDARD_SIZE - STANDARD_SIZE * 2 * 3 - BIG_SQUARE_SIZE - 30;
            modelOutlinerList.y = STANDARD_SIZE * 10 + 13;
        } else {
            onModelEditGui(false);
        }
        super.render(guiGraphics, mouseX, mouseY, tickDelta);
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

            if (isModelEditGui) {
                if (movePositionScreenMode) {
                    poseStack.pushPose();
                    MovementArrows movementArrows = new MovementArrows();
                    poseStack.pushPose();
                    poseStack.translate(0, 0, 0);
                    movementArrows.render(poseStack, bufferSource, 0xF000F0, 0);
                    poseStack.popPose();
                } else if (resizeScreenMode) {
                    poseStack.pushPose();
                    ResizeArrows resizeArrows = new ResizeArrows();
                    poseStack.pushPose();
                    poseStack.translate(0, 0, 0);
                    resizeArrows.render(poseStack, bufferSource, 0xF000F0, 0);
                    poseStack.popPose();
                } else if (rotateScreenMode) {
                    poseStack.pushPose();
                    RotationArrows rotationArrows = new RotationArrows();
                    poseStack.pushPose();
                    poseStack.translate(0, 0, 0);
                    rotationArrows.render(poseStack, bufferSource, 0xF000F0, 0);
                    poseStack.popPose();
                }
            }

            poseStack.pushPose();
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
        if (keyCode == GLFW.GLFW_KEY_BACKSPACE) {
            super.keyPressed(keyCode, scanCode, modifiers);
        }
        if (keyCode == 256 && this.shouldCloseOnEsc()) {
            this.onClose();
            return true;
        }

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
    public void tick() {

        if (ModelOutlinerList.updateModelPartData) {
            updateJavaModelPartPosition();
            ModelOutlinerList.updateModelPartData = false;
        }

        if (creatorScreenManager.fileMenuState == 1) {
            if (!buttonFileSettings.isHovered() && !creatorScreenManager.buttonNewProject.isHovered() && !creatorScreenManager.buttonSaveAsProject.isHovered() && !creatorScreenManager.buttonSaveProject.isHovered()) {
                creatorScreenManager.onFileMenu();
            }
        }

        if (creatorScreenManager.editMenuState == 1) {
            if (!buttonEditSettings.isHovered() && !creatorScreenManager.buttonModelEditGui.isHovered() && !creatorScreenManager.buttonBlockbenchMode.isHovered()) {
                creatorScreenManager.onEditMenu();
            }
        }

        if (creatorScreenManager.viewMenuState == 1) {
            if (!buttonViewerSettings.isHovered() && !creatorScreenManager.buttonScreenshotMode.isHovered() && !creatorScreenManager.buttonCameraReset.isHovered() && !creatorScreenManager.buttonPlayerCamera.isHovered()) {
                creatorScreenManager.onViewMenu();
            }
        }

        if (creatorScreenManager.renderMenuState == 1) {
            if (!buttonRenderSettings.isHovered() && !creatorScreenManager.buttonShaderRender.isHovered() && !creatorScreenManager.buttonColorBlendRender.isHovered()) {
                creatorScreenManager.onRenderMenu();
            }
        }

        if (creatorScreenManager.windowMenuState == 1) {
            if (!buttonWindowSettings.isHovered() && !creatorScreenManager.buttonCameraPresets.isHovered()) {
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
