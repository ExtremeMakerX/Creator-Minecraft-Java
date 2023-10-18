package creatorminecraft.fabric;

import creatorminecraft.fabriclike.CreatorMCFabricLike;
import net.fabricmc.api.ModInitializer;

public class CreatorMCFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        CreatorMCFabricLike.init();
    }
}
