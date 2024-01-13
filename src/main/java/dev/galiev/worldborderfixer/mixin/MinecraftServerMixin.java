package dev.galiev.worldborderfixer.mixin;

import dev.galiev.worldborderfixer.PerWorldBorderListener;
import dev.galiev.worldborderfixer.WorldBorderState;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.progress.ChunkProgressListener;
import net.minecraft.server.players.PlayerList;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.border.BorderChangeListener;
import net.minecraft.world.level.border.WorldBorder;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin {

    @Shadow @Final private Map<ResourceKey<Level>, ServerLevel> levels;

    @Inject(method = "createLevels", at = @At(value = "TAIL"))
    private void loadOtherBorder(ChunkProgressListener pListener, CallbackInfo ci) {
        levels.forEach((registryKey, world) -> {
            WorldBorder worldBorder = world.getWorldBorder();

            if (registryKey.registry() != Level.OVERWORLD.registry()) {
                WorldBorderState worldBorderState = world.getDataStorage().computeIfAbsent(WorldBorderState::fromNbt, WorldBorderState::new,"worldBorder");

                worldBorder.setCenter(worldBorderState.getCenterX(), worldBorderState.getCenterZ());
                worldBorder.setSize(worldBorderState.getSize());
                worldBorder.setDamageSafeZone(worldBorderState.getBuffer());
                worldBorder.setDamagePerBlock(worldBorderState.getDamagePerBlock());
                worldBorder.setWarningBlocks(worldBorderState.getWarningBlocks());
                worldBorder.setWarningTime(worldBorderState.getWarningTime());
            }

            worldBorder.addListener(new PerWorldBorderListener(world));
        });
    }

    @Redirect(method = "createLevels", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/border/WorldBorder;addListener(Lnet/minecraft/world/level/border/BorderChangeListener;)V"))
    private void addListener(WorldBorder instance, BorderChangeListener pListener) {}

    @Redirect(method = "createLevels", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/players/PlayerList;addWorldborderListener(Lnet/minecraft/server/level/ServerLevel;)V"))
    private void setBorderListeners(PlayerList instance, ServerLevel pLevel) {}
}
