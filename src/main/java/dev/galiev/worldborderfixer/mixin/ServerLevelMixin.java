package dev.galiev.worldborderfixer.mixin;

import dev.galiev.worldborderfixer.WorldBorderState;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerLevel.class)
public abstract class ServerLevelMixin {
    @SuppressWarnings("UnreachableCode")
    @Inject(method = "saveLevelData", at = @At("HEAD"))
    private void saveBorder(CallbackInfo ci) {
        ServerLevel level = (ServerLevel) (Object) this;
        WorldBorderState worldBorderState = level.getChunkSource().getDataStorage().computeIfAbsent(new SavedData.Factory<>(WorldBorderState::new, WorldBorderState::fromNbt), "worldBorder");
        worldBorderState.fromBorder(level.getWorldBorder());
    }
}
