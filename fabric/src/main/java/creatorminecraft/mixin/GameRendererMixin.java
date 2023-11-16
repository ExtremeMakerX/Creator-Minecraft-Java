package creatorminecraft.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import creatorminecraft.screen.CreatorMinecraftScreen;
import net.minecraft.client.renderer.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {

	@Inject(method = "renderLevel", at = @At(value = "RETURN"))
	private void renderLevel(float f, long l, PoseStack matrices, CallbackInfo ci) {
		CreatorMinecraftScreen.render(matrices);

	}

}
