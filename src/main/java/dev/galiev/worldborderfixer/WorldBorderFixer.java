package dev.galiev.worldborderfixer;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod(WorldBorderFixer.MOD_ID)
public class WorldBorderFixer {
    public static final String MOD_ID = "worldborderfixer";

    public WorldBorderFixer() {
        MinecraftForge.EVENT_BUS.register(this);
    }
}
