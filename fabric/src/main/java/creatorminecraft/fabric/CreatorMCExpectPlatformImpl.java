package creatorminecraft.fabric;

import creatorminecraft.CreatorMCExpectPlatform;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

public class CreatorMCExpectPlatformImpl {
    /**
     * This is our actual method to {@link CreatorMCExpectPlatform#getConfigDirectory()}.
     */
    public static Path getConfigDirectory() {
        return FabricLoader.getInstance().getConfigDir();
    }
}
