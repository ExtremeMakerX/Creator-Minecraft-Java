package creatorminecraft.fabric;

import creatorminecraft.CreatorMCExpectPlatform;
import org.quiltmc.loader.api.QuiltLoader;

import java.nio.file.Path;

public class CreatorMCExpectPlatformImpl {
    /**
     * This is our actual method to {@link CreatorMCExpectPlatform#getConfigDirectory()}.
     */
    public static Path getConfigDirectory() {
        return QuiltLoader.getConfigDir();
    }
}
