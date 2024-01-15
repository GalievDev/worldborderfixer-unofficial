package dev.galiev.worldborderfixer.mixin;

import dev.galiev.worldborderfixer.BorderWithWorld;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.border.WorldBorder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import static com.mojang.text2speech.Narrator.LOGGER;

@Mixin(WorldBorder.class)
public abstract class WorldBorderMixin implements BorderWithWorld {
    @Unique
    private Level level;

    @Override
    public Level getLevel() {
        LOGGER.info("get level");
        return level;
    }

    @Override
    public void setLevel(Level level) {
        LOGGER.info("set level");
        this.level = level;
    }
}
