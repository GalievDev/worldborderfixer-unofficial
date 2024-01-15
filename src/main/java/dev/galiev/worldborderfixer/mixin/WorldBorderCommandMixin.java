package dev.galiev.worldborderfixer.mixin;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.commands.WorldBorderCommand;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.border.WorldBorder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(WorldBorderCommand.class)
public abstract class WorldBorderCommandMixin {
    @Redirect(
            method = {"setDamageBuffer", "setDamageAmount", "setWarningTime", "setWarningDistance", "getSize", "setCenter", "setSize"},
            at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;getWorldBorder()Lnet/minecraft/world/level/border/WorldBorder;")
    )
    private static WorldBorder test(ServerLevel instance, CommandSourceStack sourceStack) {
        return sourceStack.getLevel().getWorldBorder();
    }
}
