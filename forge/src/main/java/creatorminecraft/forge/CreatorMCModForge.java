package creatorminecraft.forge;

import creatorminecraft.CreatorMC;
import creatorminecraft.CreatorMCID;
import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(CreatorMCID.MOD_ID)
public class CreatorMCModForge {
    public CreatorMCModForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(CreatorMCID.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        CreatorMC.init();
    }
}
