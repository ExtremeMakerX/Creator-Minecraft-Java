package org.creatorminecraft.init;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import org.creatorminecraft.mod.Init;
import org.creatorminecraft.mod.InitClient;

@Mod(Init.MOD_ID)
public class CreatorMinecraftJava {

	public CreatorMinecraftJava() {
		Init.init();
		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> InitClient::init);
	}
}
