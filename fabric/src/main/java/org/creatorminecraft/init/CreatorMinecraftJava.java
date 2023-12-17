package org.creatorminecraft.init;

import net.fabricmc.api.ModInitializer;
import org.creatorminecraft.mod.Init;

public class CreatorMinecraftJava implements ModInitializer {

	@Override
	public void onInitialize() {
		Init.init();
	}
}
