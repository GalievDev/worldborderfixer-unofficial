package dev.galiev.worldborderfixer.mixin;

import dev.galiev.worldborderfixer.BorderWithWorld;
import net.minecraft.network.protocol.game.ClientboundSetBorderCenterPacket;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.border.WorldBorder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static com.mojang.text2speech.Narrator.LOGGER;

@Mixin(ClientboundSetBorderCenterPacket.class)
public abstract class ClientboundSetBorderCenterPacketMixin {
    @Redirect(
            method = "<init>(Lnet/minecraft/world/level/border/WorldBorder;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/border/WorldBorder;getCenterX()D")
    )
    private double scaleCenterX(WorldBorder worldBorder) {
        Level level = ((BorderWithWorld) worldBorder).getLevel();
        LOGGER.info("scale center x");
        final double centerX = worldBorder.getCenterX();
        return level == null || level.isClientSide ? centerX : centerX * level.dimensionType().coordinateScale();
    }

    @Redirect(
            method = "<init>(Lnet/minecraft/world/level/border/WorldBorder;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/border/WorldBorder;getCenterZ()D")
    )
    private double scaleCenterZ(WorldBorder worldBorder) {
        Level level = ((BorderWithWorld) worldBorder).getLevel();
        LOGGER.info("scale center z");
        final double centerZ = worldBorder.getCenterZ();
        return level == null || level.isClientSide ? centerZ : centerZ * level.dimensionType().coordinateScale();
    }
}
