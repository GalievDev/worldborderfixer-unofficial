package dev.galiev.worldborderfixer.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import net.minecraft.world.level.border.WorldBorder;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(PlayerList.class)
public abstract class PlayerListMixin {
    @Shadow @Final private static Logger LOGGER;

    @ModifyVariable(
            method = "sendLevelInfo",
            at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/server/level/ServerLevel;getWorldBorder()Lnet/minecraft/world/level/border/WorldBorder;")
    )
    private WorldBorder getPlayerWorldBorder(WorldBorder worldBorder, ServerPlayer player, ServerLevel level) {
        LOGGER.info("Player border");
        return level.getWorldBorder();
    }
}
