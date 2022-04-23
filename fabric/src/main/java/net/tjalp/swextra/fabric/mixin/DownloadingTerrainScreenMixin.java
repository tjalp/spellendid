package net.tjalp.swextra.fabric.mixin;

import net.minecraft.client.gui.screen.DownloadingTerrainScreen;
import net.tjalp.swextra.core.SwConfig;
import net.tjalp.swextra.fabric.SwExtraFabric;
import net.tjalp.swextra.fabric.networking.FabricNetworkHandler;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(DownloadingTerrainScreen.class)
public class DownloadingTerrainScreenMixin {

    private final SwExtraFabric swExtraFabric = SwExtraFabric.INSTANCE;
    private final SwConfig config = swExtraFabric.getConfig();
    private final FabricNetworkHandler networkHandler = (FabricNetworkHandler) swExtraFabric.getNetworkHandler();

    @Redirect(method = "tick", at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/screen/DownloadingTerrainScreen;loadStartTime:J", opcode = Opcodes.GETFIELD))
    private long redirectLoadStartTime(DownloadingTerrainScreen instance) {
        if (config.getRemoveLoadingTerrainDelay()
                && (networkHandler.getConnected() || config.getRemoveLoadingTerrainDelayEverywhere())) {
            return 0;
        }
        return ((DownloadingTerrainScreenAccessor) instance).getLoadStartTime();
    }
}
