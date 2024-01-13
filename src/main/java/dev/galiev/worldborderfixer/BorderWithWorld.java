package dev.galiev.worldborderfixer;


import net.minecraft.world.level.Level;

public interface BorderWithWorld {
    Level getLevel();

    void setLevel(Level level);
}
