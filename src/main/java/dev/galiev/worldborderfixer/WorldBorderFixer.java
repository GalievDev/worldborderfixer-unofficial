package dev.galiev.worldborderfixer;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

@Mod(WorldBorderFixer.MOD_ID)
public class WorldBorderFixer {
    public static final String MOD_ID = "worldborderfixer";
    private static final Logger LOGGER = LogUtils.getLogger();

    public WorldBorderFixer() {
        MinecraftForge.EVENT_BUS.register(this);
    }
}
