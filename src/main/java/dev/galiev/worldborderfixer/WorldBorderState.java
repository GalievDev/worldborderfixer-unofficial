package dev.galiev.worldborderfixer;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.saveddata.SavedData;
import org.jetbrains.annotations.NotNull;

public class WorldBorderState extends SavedData {
    private double centerX = WorldBorder.DEFAULT_SETTINGS.getCenterX();
    private double centerZ = WorldBorder.DEFAULT_SETTINGS.getCenterZ();
    private double size = WorldBorder.DEFAULT_SETTINGS.getSize();
    private double buffer = WorldBorder.DEFAULT_SETTINGS.getSafeZone();
    private double damagePerBlock = WorldBorder.DEFAULT_SETTINGS.getDamagePerBlock();
    private int warningBlocks = WorldBorder.DEFAULT_SETTINGS.getWarningBlocks();
    private int warningTime = WorldBorder.DEFAULT_SETTINGS.getWarningTime();


    public WorldBorderState() {

    }

    public WorldBorderState(double centerX, double centerZ, double size, double buffer, double damagePerBlock, int warningBlocks, int warningTime) {
        this.centerX = centerX;
        this.centerZ = centerZ;
        this.size = size;
        this.buffer = buffer;
        this.damagePerBlock = damagePerBlock;
        this.warningBlocks = warningBlocks;
        this.warningTime = warningTime;
    }

    public static WorldBorderState fromNbt(CompoundTag tag) {
        return new WorldBorderState(
                tag.getDouble("BorderCenterX"),
                tag.getDouble("BorderCenterZ"),
                tag.getDouble("BorderSize"),
                tag.getDouble("BorderSafeZone"),
                tag.getDouble("BorderDamagePerBlock"),
                tag.getInt("BorderWarningBlocks"),
                tag.getInt("BorderWarningTime")
        );
    }

    @Override
    public @NotNull CompoundTag save(CompoundTag tag) {
        tag.putDouble("BorderCenterX", centerX);
        tag.putDouble("BorderCenterZ", centerZ);
        tag.putDouble("BorderSize", size);
        tag.putDouble("BorderSafeZone", buffer);
        tag.putDouble("BorderDamagePerBlock", damagePerBlock);
        tag.putInt("BorderWarningBlocks", warningBlocks);
        tag.putInt("BorderWarningTime", warningTime);

        return tag;
    }

    public double getCenterX() {
        return centerX;
    }


    public double getCenterZ() {
        return centerZ;
    }

    public double getSize() {
        return size;
    }

    public double getBuffer() {
        return buffer;
    }

    public double getDamagePerBlock() {
        return damagePerBlock;
    }

    public int getWarningBlocks() {
        return warningBlocks;
    }

    public int getWarningTime() {
        return warningTime;
    }

    public void fromBorder(WorldBorder worldBorder) {
        centerX = worldBorder.getCenterX();
        centerZ = worldBorder.getCenterZ();
        size = worldBorder.getSize();
        buffer = worldBorder.getDamageSafeZone();
        damagePerBlock = worldBorder.getDamagePerBlock();
        warningBlocks = worldBorder.getWarningBlocks();
        warningTime = worldBorder.getWarningTime();

        setDirty();
    }
}
