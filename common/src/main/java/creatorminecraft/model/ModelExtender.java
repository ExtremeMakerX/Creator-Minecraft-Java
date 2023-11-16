package creatorminecraft.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Consumer;
import java.util.function.Function;

public class ModelExtender extends Model implements Consumer<ModelPartBuilder>  {

    protected final Function<ResourceLocation, RenderType> renderType;
    public final Model model;
    public int texWidth;
    public int texHeight;

    public ModelExtender(Function<ResourceLocation, RenderType> function, Model model, int textureWidth, int textureHeight) {
        super(function);
        this.renderType = function;
        this.model = model;
        texWidth = textureWidth;
        texHeight = textureHeight;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int j, float f, float g, float h, float k) {

    }

    @Override
    public void accept(ModelPartBuilder modelPartBuilder) {

    }
}
