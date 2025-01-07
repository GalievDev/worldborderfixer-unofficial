package dev.galiev.worldborderfixer;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(WorldBorderFixer.MOD_ID)
public class WorldBorderFixer {
    public static final String MOD_ID = "worldborderfixer";
    private static final Logger LOGGER = LogUtils.getLogger();

    public WorldBorderFixer(IEventBus modBus) {
    }
}
