package creatorminecraft.forge;

import creatorminecraft.CreatorMCExpectPlatform;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

public class CreatorMCExpectPlatformImpl {
    /**
     * This is our actual method to {@link CreatorMCExpectPlatform#getConfigDirectory()}.
     */
    public static Path getConfigDirectory() {
        return FMLPaths.CONFIGDIR.get();
    }
}
