package org.creatorminecraft.mod.screen;

import org.creatorminecraft.mod.client.CreatorIGUI;
import org.creatorminecraft.mod.widgets.DynamicButtonCMJ;
import org.mtr.mapping.mapper.ScreenExtension;
import org.mtr.mapping.mapper.TextHelper;

public class CreatorScreenManager extends ScreenExtension implements CreatorIGUI {

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
        super();
        buttonNewProject = new DynamicButtonCMJ(0, 0, 0, STANDARD_SIZE, TextHelper.translatable("gui.creatorminecraft.new_project"), button -> {});
        buttonSaveProject = new DynamicButtonCMJ(0, 0, 0, STANDARD_SIZE, TextHelper.translatable("gui.creatorminecraft.save_project"), button -> {});
        buttonSaveAsProject = new DynamicButtonCMJ(0, 0, 0, STANDARD_SIZE, TextHelper.translatable("gui.creatorminecraft.save_as_project"), button -> {});

        buttonModelEditGui = new DynamicButtonCMJ(0, 0, 0, STANDARD_SIZE, TextHelper.translatable("gui.creatorminecraft.model_edit"), button -> {CreatorMinecraftScreen.isModelEditGui = true;});
        buttonBlockbenchMode = new DynamicButtonCMJ(0, 0, 0, STANDARD_SIZE, TextHelper.translatable("gui.creatorminecraft.blockbench_mode"), button -> {});

        buttonCameraReset = new DynamicButtonCMJ(0, 0, 0, STANDARD_SIZE, TextHelper.translatable("gui.creatorminecraft.camera_reset"), button -> {camereReset();});
        buttonScreenshotMode = new DynamicButtonCMJ(0, 0, 0, STANDARD_SIZE, TextHelper.translatable("gui.creatorminecraft.screenshot_mode"), button -> {});
        buttonPlayerCamera = new DynamicButtonCMJ(0, 0, 0, STANDARD_SIZE, TextHelper.translatable("gui.creatorminecraft.player_camera"), button -> {});
        buttonExitScreenshotMode = new DynamicButtonCMJ(0, 0, 0, STANDARD_SIZE, TextHelper.translatable("gui.creatorminecraft.exit.screenshot_mode"), button -> {});

        buttonShaderRender = new DynamicButtonCMJ(0, 0, 0, STANDARD_SIZE, TextHelper.translatable("gui.creatorminecraft.shader_render"), button -> {});
        buttonColorBlendRender = new DynamicButtonCMJ(0, 0, 0, STANDARD_SIZE, TextHelper.translatable("gui.creatorminecraft.color_blend_render"), button -> {});

        buttonCameraPresets = new DynamicButtonCMJ(0, 0, 0, STANDARD_SIZE, TextHelper.translatable("gui.creatorminecraft.camera_presets"), button -> {});

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

    public void camereReset() {
        CreatorMinecraftScreen.cameraYaw = 90;
        CreatorMinecraftScreen.cameraPitch = 220;
        CreatorMinecraftScreen.cameraX = 0;
        CreatorMinecraftScreen.cameraY = 0;
        CreatorMinecraftScreen.cameraZ = 17.5;
    }

}
