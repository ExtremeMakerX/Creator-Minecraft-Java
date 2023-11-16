package creatorminecraft.screen;

import creatorminecraft.client.CreatorIGUI;
import creatorminecraft.widgets.DynamicButtonCMJ;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class CreatorScreenManager extends Screen implements CreatorIGUI {

    public final DynamicButtonCMJ buttonNewProject;
    public final DynamicButtonCMJ buttonSaveProject;
    public final DynamicButtonCMJ buttonSaveAsProject;
    public final DynamicButtonCMJ buttonModelEditGui;
    public final DynamicButtonCMJ buttonBlockbenchMode;
    public final DynamicButtonCMJ buttonCameraReset;
    public final DynamicButtonCMJ buttonScreenshotMode;
    public final DynamicButtonCMJ buttonPlayerCamera;
    public final DynamicButtonCMJ buttonExitScreenshotMode;
    public final DynamicButtonCMJ buttonShaderRender;
    public final DynamicButtonCMJ buttonColorBlendRender;
    public final DynamicButtonCMJ buttonCameraPresets;

    public int fileMenuState = 0;
    public int editMenuState = 0;
    public int viewMenuState = 0;
    public int renderMenuState = 0;
    public int windowMenuState = 0;

    public CreatorScreenManager() {
        super(Component.empty());
        buttonNewProject = new DynamicButtonCMJ(0, 0, 0, STANDARD_SIZE, Component.translatable("gui.creatorminecraft.new_project"), button -> {}, DynamicButtonCMJ.DEFAULT_NARRATION);
        buttonSaveProject = new DynamicButtonCMJ(0, 0, 0, STANDARD_SIZE, Component.translatable("gui.creatorminecraft.save_project"), button -> {}, DynamicButtonCMJ.DEFAULT_NARRATION);
        buttonSaveAsProject = new DynamicButtonCMJ(0, 0, 0, STANDARD_SIZE, Component.translatable("gui.creatorminecraft.save_as_project"), button -> {}, DynamicButtonCMJ.DEFAULT_NARRATION);

        buttonModelEditGui = new DynamicButtonCMJ(0, 0, 0, STANDARD_SIZE, Component.translatable("gui.creatorminecraft.model_edit"), button -> {}, DynamicButtonCMJ.DEFAULT_NARRATION);
        buttonBlockbenchMode = new DynamicButtonCMJ(0, 0, 0, STANDARD_SIZE, Component.translatable("gui.creatorminecraft.blockbench_mode"), button -> {}, DynamicButtonCMJ.DEFAULT_NARRATION);

        buttonCameraReset = new DynamicButtonCMJ(0, 0, 0, STANDARD_SIZE, Component.translatable("gui.creatorminecraft.camera_reset"), button -> {}, DynamicButtonCMJ.DEFAULT_NARRATION);
        buttonScreenshotMode = new DynamicButtonCMJ(0, 0, 0, STANDARD_SIZE, Component.translatable("gui.creatorminecraft.screenshot_mode"), button -> {}, DynamicButtonCMJ.DEFAULT_NARRATION);
        buttonPlayerCamera = new DynamicButtonCMJ(0, 0, 0, STANDARD_SIZE, Component.translatable("gui.creatorminecraft.player_camera"), button -> {}, DynamicButtonCMJ.DEFAULT_NARRATION);
        buttonExitScreenshotMode = new DynamicButtonCMJ(0, 0, 0, STANDARD_SIZE, Component.translatable("gui.creatorminecraft.exit.screenshot_mode"), button -> {}, DynamicButtonCMJ.DEFAULT_NARRATION);

        buttonShaderRender = new DynamicButtonCMJ(0, 0, 0, STANDARD_SIZE, Component.translatable("gui.creatorminecraft.shader_render"), button -> {}, DynamicButtonCMJ.DEFAULT_NARRATION);
        buttonColorBlendRender = new DynamicButtonCMJ(0, 0, 0, STANDARD_SIZE, Component.translatable("gui.creatorminecraft.color_blend_render"), button -> {}, DynamicButtonCMJ.DEFAULT_NARRATION);

        buttonCameraPresets = new DynamicButtonCMJ(0, 0, 0, STANDARD_SIZE, Component.translatable("gui.creatorminecraft.camera_presets"), button -> {}, DynamicButtonCMJ.DEFAULT_NARRATION);

        buttonNewProject.visible = false;
        buttonSaveProject .visible = false;
        buttonSaveAsProject .visible = false;

        buttonModelEditGui .visible = false;
        buttonBlockbenchMode .visible = false;

        buttonCameraReset .visible = false;
        buttonScreenshotMode .visible = false;
        buttonPlayerCamera .visible = false;
        buttonExitScreenshotMode .visible = false;

        buttonShaderRender .visible = false;
        buttonColorBlendRender .visible = false;

        buttonCameraPresets .visible = false;
    }

    public void onFileMenu() {
        fileMenuState++;

        buttonNewProject.visible = fileMenuState == 1;
        buttonSaveProject.visible = fileMenuState == 1;
        buttonSaveAsProject.visible = fileMenuState == 1;

        fileMenuState = (fileMenuState == 2) ? 0 : fileMenuState;
    }

    public void onEditMenu() {
        editMenuState++;

        buttonModelEditGui.visible = editMenuState == 1;
        buttonBlockbenchMode.visible = editMenuState == 1;

        editMenuState = (editMenuState == 2) ? 0 : editMenuState;
    }

    public void onViewMenu() {
        viewMenuState++;

        buttonCameraReset.visible = viewMenuState == 1;
        buttonScreenshotMode.visible = viewMenuState == 1;
        buttonPlayerCamera.visible = viewMenuState == 1;
        //buttonExitScreenshotMode.visible = fileMenuState == 0;

        viewMenuState = (viewMenuState == 2) ? 0 : viewMenuState;
    }

    public void onRenderMenu() {
        renderMenuState++;

        buttonShaderRender.visible = renderMenuState == 1;
        buttonColorBlendRender.visible = renderMenuState == 1;

        renderMenuState = (renderMenuState == 2) ? 0 : renderMenuState;
    }

    public void onWindowMenu() {
        windowMenuState++;

        buttonCameraPresets.visible = windowMenuState == 1;

        windowMenuState = (windowMenuState == 2) ? 0 : windowMenuState;
    }

}
