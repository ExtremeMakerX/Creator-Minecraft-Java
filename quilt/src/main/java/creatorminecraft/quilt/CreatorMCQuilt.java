package creatorminecraft.quilt;

import creatorminecraft.fabriclike.CreatorMCFabricLike;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

public class CreatorMCQuilt implements ModInitializer {
    @Override
    public void onInitialize(ModContainer mod) {
        CreatorMCFabricLike.init();
    }
}
