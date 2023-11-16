package creatorminecraft.fabriclike;

import creatorminecraft.CreatorMC;
import creatorminecraft.Renderers;

public class CreatorMCFabricLike {
    public static void init() {
        CreatorMC.init();
        Renderers.registerBlockEntityRenderer();
    }
}
