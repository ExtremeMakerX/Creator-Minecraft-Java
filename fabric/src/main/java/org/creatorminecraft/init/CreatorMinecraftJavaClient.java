package org.creatorminecraft.init;

import net.fabricmc.api.ClientModInitializer;
import org.creatorminecraft.mod.InitClient;

public class CreatorMinecraftJavaClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		InitClient.init();
	}
}
