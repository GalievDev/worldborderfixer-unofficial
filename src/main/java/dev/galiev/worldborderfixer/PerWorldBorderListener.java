package dev.galiev.worldborderfixer;


import net.minecraft.network.protocol.game.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.border.BorderChangeListener;
import net.minecraft.world.level.border.WorldBorder;

public class PerWorldBorderListener implements BorderChangeListener {

    private final ServerLevel world;

    public PerWorldBorderListener(ServerLevel world) {
        this.world = world;
    }

    @Override
    public void onBorderSizeSet(WorldBorder pBorder, double pSize) {
        world.players().forEach(playerEntity -> playerEntity.connection.send((new ClientboundSetBorderSizePacket(pBorder))));
    }

    @Override
    public void onBorderSizeLerping(WorldBorder pBorder, double pOldSize, double pNewSize, long pTime) {
        world.players().forEach(playerEntity -> playerEntity.connection.send((new ClientboundInitializeBorderPacket(pBorder))));
    }

    @Override
    public void onBorderCenterSet(WorldBorder pBorder, double pX, double pZ) {
        world.players().forEach(playerEntity -> playerEntity.connection.send((new ClientboundSetBorderCenterPacket(pBorder))));
    }

    @Override
    public void onBorderSetWarningTime(WorldBorder pBorder, int pWarningTime) {
        world.players().forEach(playerEntity -> playerEntity.connection.send((new ClientboundSetBorderWarningDelayPacket(pBorder))));
    }

    @Override
    public void onBorderSetWarningBlocks(WorldBorder pBorder, int pWarningBlocks) {
        world.players().forEach(playerEntity -> playerEntity.connection.send((new ClientboundSetBorderWarningDistancePacket(pBorder))));
    }

    @Override
    public void onBorderSetDamagePerBlock(WorldBorder pBorder, double pDamagePerBlock) {

    }

    @Override
    public void onBorderSetDamageSafeZOne(WorldBorder pBorder, double pDamageSafeZone) {

    }
}
