package creatorminecraft;

import java.util.logging.Logger;

public class CreatorMC {

    public static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static void init() {
        Blocks.init();
        Items.init();
        ItemGroups.init();
        System.out.println(CreatorMCExpectPlatform.getConfigDirectory().toAbsolutePath().normalize().toString());
    }
}
