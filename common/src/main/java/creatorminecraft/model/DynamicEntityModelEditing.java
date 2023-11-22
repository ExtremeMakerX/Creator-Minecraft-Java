package creatorminecraft.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import creatorminecraft.screen.CreatorMinecraftScreen;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class DynamicEntityModelEditing extends EntityModel<Entity> {
	//public ModelPartBuilder main = new ModelPartBuilder();
	private static final ResourceLocation texture = new ResourceLocation("creatorminecraft:textures/block/white.png");
	private boolean movePositionMode;
	private boolean resizeMode;
	private boolean rotateMode;
	private boolean pivotMode;
	private boolean inflateMode;
	private static float positionXScreenGetter;
	private static float positionYScreenGetter;
	private static float positionZScreenGetter;
	private static float sizeXScreenGetter;
	private static float sizeYScreenGetter;
	private static float sizeZScreenGetter;
	private static float pivotXScreenGetter;
	private static float pivotYScreenGetter;
	private static float pivotZScreenGetter;
	private static float rotationXScreenGetter;
	private static float rotationYScreenGetter;
	private static float rotationZScreenGetter;
	private static float inflateScreenGetter;
	private static final List<ModelPartData> modelPartDataList = new ArrayList<>();
	private final List<ModelPartBuilder> mainList = new ArrayList<>();

	public DynamicEntityModelEditing() {
		final int textureWidth = 450;
		final int textureHeight = 450;
			for (ModelPartData modelPartData : modelPartDataList) {
				modelPartData.main = new ModelPartBuilder(new ModelExtender(resourceLocation -> RenderType.solid(), this, textureWidth, textureHeight));
				modelPartData.main.setPos(modelPartData.pivotX, modelPartData.pivotY, modelPartData.pivotZ);
				modelPartData.main.setRotationAngle(modelPartData.rotationX, modelPartData.rotationY, modelPartData.rotationZ);
				modelPartData.main.texOffs(0, 0).addBox(modelPartData.positionX, modelPartData.positionY, modelPartData.positionZ, modelPartData.sizeX, modelPartData.sizeY, modelPartData.sizeZ, modelPartData.inflate, false);
			}
	}

	public void ScreenGUIToolMode() {
		if (CreatorMinecraftScreen.movePositionScreenMode) {
			resizeMode = rotateMode = pivotMode = inflateMode = false;
			movePositionMode = true;
		} else if (CreatorMinecraftScreen.resizeScreenMode) {
			movePositionMode = rotateMode = pivotMode = inflateMode = false;
			resizeMode = true;
		} else if (CreatorMinecraftScreen.rotateScreenMode) {
			movePositionMode = resizeMode = pivotMode = inflateMode = false;
			rotateMode = true;
		} else if (CreatorMinecraftScreen.pivotScreenMode) {
			movePositionMode = resizeMode = rotateMode = inflateMode = false;
			pivotMode = true;
		} else if (CreatorMinecraftScreen.inflateScreenMode) {
			movePositionMode = resizeMode = rotateMode = pivotMode = false;
			inflateMode = true;
		}
	}

	public void getModelSlotData(int slot) {
		if (slot >= 0 && slot < modelPartDataList.size()) {
			ModelPartData modelPartData = modelPartDataList.get(slot);

			// Retrieve and use the model part data as needed
			positionXScreenGetter = modelPartData.positionX;
			positionYScreenGetter = modelPartData.positionY;
			positionZScreenGetter = modelPartData.positionZ;
			sizeXScreenGetter = modelPartData.sizeX;
			sizeYScreenGetter = modelPartData.sizeY;
			sizeZScreenGetter = modelPartData.sizeZ;
			pivotXScreenGetter = modelPartData.pivotX;
			pivotYScreenGetter = modelPartData.pivotY;
			pivotZScreenGetter = modelPartData.pivotZ;
			rotationXScreenGetter = modelPartData.rotationX;
			rotationYScreenGetter = modelPartData.rotationY;
			rotationZScreenGetter = modelPartData.rotationZ;
			inflateScreenGetter = modelPartData.inflate;
		}
	}

	public void setModelSlotData(int slot) {
		if (slot >= 0 && slot < modelPartDataList.size()) {
			ModelPartData modelPartData = new ModelPartData();

			// Set the values for the model part data
			modelPartData.positionX = positionXScreenGetter;
			modelPartData.positionY = positionYScreenGetter;
			modelPartData.positionZ = positionZScreenGetter;
			modelPartData.sizeX = sizeXScreenGetter;
			modelPartData.sizeY = sizeYScreenGetter;
			modelPartData.sizeZ = sizeZScreenGetter;
			modelPartData.pivotX = pivotXScreenGetter;
			modelPartData.pivotY = pivotYScreenGetter;
			modelPartData.pivotZ = pivotZScreenGetter;
			modelPartData.rotationX = rotationXScreenGetter;
			modelPartData.rotationY = rotationYScreenGetter;
			modelPartData.rotationZ = rotationZScreenGetter;
			modelPartData.inflate = inflateScreenGetter;

			// Store the model part data at the specified slot
			modelPartDataList.set(slot, modelPartData);
		}
	}

	public void addModelSlot() {
		modelPartDataList.add(new ModelPartData());
	}

	public void removeModelSlot(int slot) {
		if (slot >= 0 && slot < modelPartDataList.size()) {
			modelPartDataList.remove(slot);
		}
	}

	public void modelSlotPositionXIncrease(int slot) {
		if (movePositionMode && slot >= 0 && slot < modelPartDataList.size()) {
			ModelPartData modelPartData = modelPartDataList.get(slot);
			modelPartData.positionX += 1;
		}
	}

	public void modelSlotPositionXDecrease(int slot) {
		if (movePositionMode && slot >= 0 && slot < modelPartDataList.size()) {
			ModelPartData modelPartData = modelPartDataList.get(slot);
			modelPartData.positionX -= 1;
		}
	}

	public void modelSlotPositionYIncrease(int slot) {
		if (movePositionMode && slot >= 0 && slot < modelPartDataList.size()) {
			ModelPartData modelPartData = modelPartDataList.get(slot);
			modelPartData.positionY += 1;
		}
	}

	public void modelSlotPositionYDecrease(int slot) {
		if (movePositionMode && slot >= 0 && slot < modelPartDataList.size()) {
			ModelPartData modelPartData = modelPartDataList.get(slot);
			modelPartData.positionY -= 1;
		}
	}

	public void modelSlotPositionZIncrease(int slot) {
		if (movePositionMode && slot >= 0 && slot < modelPartDataList.size()) {
			ModelPartData modelPartData = modelPartDataList.get(slot);
			modelPartData.positionZ += 1;
		}
	}

	public void modelSlotPositionZDecrease(int slot) {
		if (movePositionMode && slot >= 0 && slot < modelPartDataList.size()) {
			ModelPartData modelPartData = modelPartDataList.get(slot);
			modelPartData.positionZ -= 1;
		}
	}

	public void modelSlotSizeXIncrease(int slot) {
		if (resizeMode && slot >= 0 && slot < modelPartDataList.size()) {
			ModelPartData modelPartData = modelPartDataList.get(slot);
			modelPartData.sizeX += 1;
		}
	}

	public void modelSlotSizeXDecrease(int slot) {
		if (resizeMode && slot >= 0 && slot < modelPartDataList.size()) {
			ModelPartData modelPartData = modelPartDataList.get(slot);
			modelPartData.sizeX -= 1;
		}
	}

	public void modelSlotSizeYIncrease(int slot) {
		if (resizeMode && slot >= 0 && slot < modelPartDataList.size()) {
			ModelPartData modelPartData = modelPartDataList.get(slot);
			modelPartData.sizeY += 1;
		}
	}

	public void modelSlotSizeYDecrease(int slot) {
		if (resizeMode && slot >= 0 && slot < modelPartDataList.size()) {
			ModelPartData modelPartData = modelPartDataList.get(slot);
			modelPartData.sizeY -= 1;
		}
	}

	public void modelSlotSizeZIncrease(int slot) {
		if (resizeMode && slot >= 0 && slot < modelPartDataList.size()) {
			ModelPartData modelPartData = modelPartDataList.get(slot);
			modelPartData.sizeZ += 1;
		}
	}

	public void modelSlotSizeZDecrease(int slot) {
		if (resizeMode && slot >= 0 && slot < modelPartDataList.size()) {
			ModelPartData modelPartData = modelPartDataList.get(slot);
			modelPartData.sizeZ -= 1;
		}
	}

	public void modelSlotRotationXIncrease(int slot) {
		if (rotateMode && slot >= 0 && slot < modelPartDataList.size()) {
			ModelPartData modelPartData = modelPartDataList.get(slot);
			modelPartData.rotationX += 1;
		}
	}

	public void modelSlotRotationXDecrease(int slot) {
		if (rotateMode && slot >= 0 && slot < modelPartDataList.size()) {
			ModelPartData modelPartData = modelPartDataList.get(slot);
			modelPartData.rotationX -= 1;
		}
	}

	public void modelSlotRotationYIncrease(int slot) {
		if (rotateMode && slot >= 0 && slot < modelPartDataList.size()) {
			ModelPartData modelPartData = modelPartDataList.get(slot);
			modelPartData.rotationY += 1;
		}
	}

	public void modelSlotRotationYDecrease(int slot) {
		if (rotateMode && slot >= 0 && slot < modelPartDataList.size()) {
			ModelPartData modelPartData = modelPartDataList.get(slot);
			modelPartData.rotationY -= 1;
		}
	}

	public void modelSlotRotationZIncrease(int slot) {
		if (rotateMode && slot >= 0 && slot < modelPartDataList.size()) {
			ModelPartData modelPartData = modelPartDataList.get(slot);
			modelPartData.rotationZ += 1;
		}
	}

	public void modelSlotRotationZDecrease(int slot) {
		if (rotateMode && slot >= 0 && slot < modelPartDataList.size()) {
			ModelPartData modelPartData = modelPartDataList.get(slot);
			modelPartData.rotationZ -= 1;
		}
	}

	public void modelSlotPivotXIncrease(int slot) {
		if (pivotMode && slot >= 0 && slot < modelPartDataList.size()) {
			ModelPartData modelPartData = modelPartDataList.get(slot);
			modelPartData.pivotX += 1;
		}
	}

	public void modelSlotPivotXDecrease(int slot) {
		if (pivotMode && slot >= 0 && slot < modelPartDataList.size()) {
			ModelPartData modelPartData = modelPartDataList.get(slot);
			modelPartData.pivotX -= 1;
		}
	}

	public void modelSlotPivotYIncrease(int slot) {
		if (pivotMode && slot >= 0 && slot < modelPartDataList.size()) {
			ModelPartData modelPartData = modelPartDataList.get(slot);
			modelPartData.pivotY += 1;
		}
	}

	public void modelSlotPivotYDecrease(int slot) {
		if (pivotMode && slot >= 0 && slot < modelPartDataList.size()) {
			ModelPartData modelPartData = modelPartDataList.get(slot);
			modelPartData.pivotY -= 1;
		}
	}

	public void modelSlotPivotZIncrease(int slot) {
		if (pivotMode && slot >= 0 && slot < modelPartDataList.size()) {
			ModelPartData modelPartData = modelPartDataList.get(slot);
			modelPartData.pivotZ += 1;
		}
	}

	public void modelSlotPivotZDecrease(int slot) {
		if (pivotMode && slot >= 0 && slot < modelPartDataList.size()) {
			ModelPartData modelPartData = modelPartDataList.get(slot);
			modelPartData.pivotZ -= 1;
		}
	}

	public void modelSlotInflateIncrease(int slot) {
		if (inflateMode && slot >= 0 && slot < modelPartDataList.size()) {
			ModelPartData modelPartData = modelPartDataList.get(slot);
			modelPartData.inflate += 1;
		}
	}

	public void modelSlotInflateDecrease(int slot) {
		if (inflateMode && slot >= 0 && slot < modelPartDataList.size()) {
			ModelPartData modelPartData = modelPartDataList.get(slot);
			modelPartData.inflate -= 1;
		}
	}

	public void setModelSlotPositionX(int slot, float positionXScreenInputValue) {
		positionXScreenGetter = positionXScreenInputValue;
		if (slot >= 0 && slot < modelPartDataList.size()) {
			ModelPartData modelPartData = modelPartDataList.get(slot);
			modelPartData.positionX = positionXScreenGetter;
		}
	}

	public void setModelSlotPositionY(int slot, float positionYScreenInputValue) {
		positionYScreenGetter = positionYScreenInputValue;
		if (slot >= 0 && slot < modelPartDataList.size()) {
			ModelPartData modelPartData = modelPartDataList.get(slot);
			modelPartData.positionY = positionYScreenGetter;
		}
	}

	public void setModelSlotPositionZ(int slot, float positionZScreenInputValue) {
		positionZScreenGetter = positionZScreenInputValue;
		if (slot >= 0 && slot < modelPartDataList.size()) {
			ModelPartData modelPartData = modelPartDataList.get(slot);
			modelPartData.positionZ = positionZScreenGetter;
		}
	}

	public void setModelSlotSizeX(int slot, float sizeXScreenInputValue) {
		sizeXScreenGetter = sizeXScreenInputValue;
		if (slot >= 0 && slot < modelPartDataList.size()) {
			ModelPartData modelPartData = modelPartDataList.get(slot);
			modelPartData.sizeX = sizeXScreenGetter;
		}
	}

	public void setModelSlotSizeY(int slot, float sizeYScreenInputValue) {
		sizeYScreenGetter = sizeYScreenInputValue;
		if (slot >= 0 && slot < modelPartDataList.size()) {
			ModelPartData modelPartData = modelPartDataList.get(slot);
			modelPartData.sizeY = sizeYScreenGetter;
		}
	}

	public void setModelSlotSizeZ(int slot, float sizeZScreenInputValue) {
		sizeZScreenGetter = sizeZScreenInputValue;
		if (slot >= 0 && slot < modelPartDataList.size()) {
			ModelPartData modelPartData = modelPartDataList.get(slot);
			modelPartData.sizeZ = sizeZScreenGetter;
		}
	}

	public void setModelSlotPivotX(int slot, float pivotXScreenInputValue) {
		pivotXScreenGetter = pivotXScreenInputValue;
		if (slot >= 0 && slot < modelPartDataList.size()) {
			ModelPartData modelPartData = modelPartDataList.get(slot);
			modelPartData.pivotX = pivotXScreenGetter;
		}
	}

	public void setModelSlotPivotY(int slot, float pivotYScreenInputValue) {
		pivotYScreenGetter = pivotYScreenInputValue;
		if (slot >= 0 && slot < modelPartDataList.size()) {
			ModelPartData modelPartData = modelPartDataList.get(slot);
			modelPartData.pivotY = pivotYScreenGetter;
		}
	}

	public void setModelSlotPivotZ(int slot, float pivotZScreenInputValue) {
		pivotZScreenGetter = pivotZScreenInputValue;
		if (slot >= 0 && slot < modelPartDataList.size()) {
			ModelPartData modelPartData = modelPartDataList.get(slot);
			modelPartData.pivotZ = pivotZScreenGetter;
		}
	}

	public void setModelSlotRotationX(int slot, float rotationXScreenInputValue) {
		rotationXScreenGetter = rotationXScreenInputValue;
		if (slot >= 0 && slot < modelPartDataList.size()) {
			ModelPartData modelPartData = modelPartDataList.get(slot);
			modelPartData.rotationX = rotationXScreenGetter;
		}
	}

	public void setModelSlotRotationY(int slot, float rotationYScreenInputValue) {
		rotationYScreenGetter = rotationYScreenInputValue;
		if (slot >= 0 && slot < modelPartDataList.size()) {
			ModelPartData modelPartData = modelPartDataList.get(slot);
			modelPartData.rotationY = rotationYScreenGetter;
		}
	}

	public void setModelSlotRotationZ(int slot, float rotationZScreenInputValue) {
		rotationZScreenGetter = rotationZScreenInputValue;
		if (slot >= 0 && slot < modelPartDataList.size()) {
			ModelPartData modelPartData = modelPartDataList.get(slot);
			modelPartData.rotationZ = rotationZScreenGetter;
		}
	}

	public void setModelSlotInflate(int slot, float inflateScreenInputValue) {
		inflateScreenGetter = inflateScreenInputValue;
		if (slot >= 0 && slot < modelPartDataList.size()) {
			ModelPartData modelPartData = modelPartDataList.get(slot);
			modelPartData.inflate = inflateScreenGetter;
		}
	}

	public static void setModelSlotPivotX(float pivotXScreenInputValue) {
		pivotXScreenGetter = pivotXScreenInputValue;
	}

	public static void setModelSlotPivotY(float pivotYScreenInputValue) {
		pivotYScreenGetter = pivotYScreenInputValue;
	}

	public static void setModelSlotPivotZ(float pivotZScreenInputValue) {
		pivotZScreenGetter = pivotZScreenInputValue;
	}

	public static void setModelSlotRotationX(float rotationXScreenInputValue) {
		rotationXScreenGetter = rotationXScreenInputValue;
	}

	public static void setModelSlotRotationY(float rotationYScreenInputValue) {
		rotationYScreenGetter = rotationYScreenInputValue;
	}

	public static void setModelSlotRotationZ(float rotationZScreenInputValue) {
		rotationZScreenGetter = rotationZScreenInputValue;
	}

	public static float getModelSlotPositionX() {
		return positionXScreenGetter;
	}

	public static float getModelSlotPositionY() {
		return positionYScreenGetter;
	}

	public static float getModelSlotPositionZ() {
		return positionZScreenGetter;
	}

	public static float getModelSlotSizeX() {
		return sizeXScreenGetter;
	}

	public static float getModelSlotSizeY() {
		return sizeYScreenGetter;
	}

	public static float getModelSlotSizeZ() {
		return sizeZScreenGetter;
	}

	public static float getModelSlotPivotX() {
		return pivotXScreenGetter;
	}

	public static float getModelSlotPivotY() {
		return pivotYScreenGetter;
	}

	public static float getModelSlotPivotZ() {
		return pivotZScreenGetter;
	}

	public static float getModelSlotRotationX() {
		return rotationXScreenGetter;
	}

	public static float getModelSlotRotationY() {
		return rotationYScreenGetter;
	}

	public static float getModelSlotRotationZ() {
		return rotationZScreenGetter;
	}

	public static float getModelSlotInflate() {
		return inflateScreenGetter;
	}

	public static int getModelListSize() {
		return modelPartDataList.size();
	}

	public static int[] getModelListSizeArray() {
		return new int[]{modelPartDataList.size()};
	}

	private static class ModelPartData {
		public ModelPartBuilder main = new ModelPartBuilder();
		private float positionX;
		private float positionY;
		private float positionZ;
		private float sizeX;
		private float sizeY;
		private float sizeZ;
		private float pivotX;
		private float pivotY;
		private float pivotZ;
		private float rotationX;
		private float rotationY;
		private float rotationZ;
		private float inflate;
	}

	public void render(PoseStack matrices, MultiBufferSource vertexConsumers, int light, int position) {
		for (ModelPartData modelPartData : modelPartDataList) {
			ModelObjectBase.renderOnce(modelPartData.main, matrices, vertexConsumers.getBuffer(RenderType.entitySmoothCutout(texture)), light, position);
		}
		}

	@Override
	public void setupAnim(Entity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
	}

	@Override
	public final void renderToBuffer(PoseStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {

	}
}
