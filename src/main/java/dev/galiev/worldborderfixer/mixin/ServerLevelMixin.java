package dev.galiev.worldborderfixer.mixin;

import dev.galiev.worldborderfixer.WorldBorderState;
import net.minecraft.server.level.ServerLevel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.mojang.text2speech.Narrator.LOGGER;

@Mixin(ServerLevel.class)
public abstract class ServerLevelMixin {
    @Inject(method = "saveLevelData", at = @At("HEAD"))
    private void saveBorder(CallbackInfo ci) {
        LOGGER.info("save border");
        ServerLevel level = (ServerLevel) (Object) this;
        WorldBorderState worldBorderState = level.getDataStorage().computeIfAbsent(WorldBorderState::fromNbt, WorldBorderState::new,"worldBorder");

        worldBorderState.fromBorder(level.getWorldBorder());
    }
}
