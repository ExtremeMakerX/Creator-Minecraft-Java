package org.creatorminecraft.mod;


import org.mtr.mapping.registry.Registry;
import org.mtr.mapping.tool.DummyClass;

public final class Init {

	public static final String MOD_ID = "creatorminecraft";

	public static void init() {
		Blocks.init();
		Items.init();
		BlockEntityTypes.init();
		ItemGroups.init();
		DummyClass.enableLogging();

		Registry.init();
	}
}
