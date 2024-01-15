package dev.galiev.worldborderfixer.mixin;

import dev.galiev.worldborderfixer.BorderWithWorld;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.storage.WritableLevelData;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Supplier;

@Mixin(Level.class)
public abstract class LevelMixin {

    @Shadow @Final public boolean isClientSide;

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/dimension/DimensionType;coordinateScale()D"))
    private double setBorderCoordinateScale(DimensionType dimensionType) {
        if (this.isClientSide) {
            return dimensionType.coordinateScale();
        } else {
            return 1.0D;
        }
    }

    @Inject(
            method = "<init>",
            at = @At(value = "TAIL", target = "Ljava/lang/Thread;currentThread()Ljava/lang/Thread;")
    )
    private void setWorldBorder(WritableLevelData pLevelData, ResourceKey pDimension, Holder pDimensionTypeRegistration, Supplier pProfiler, boolean pIsClientSide, boolean pIsDebug, long pBiomeZoomSeed, int pMaxChainedNeighborUpdates, CallbackInfo ci) {
        Level level = (Level) (Object) this;
        ((BorderWithWorld) level.getWorldBorder()).setLevel(level);
    }
}
