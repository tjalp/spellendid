package net.tjalp.spellendid.fabric.mixin;

import net.minecraft.client.gui.screen.DownloadingTerrainScreen;
import net.tjalp.spellendid.fabric.networking.FabricNetworkHandler;
import net.tjalp.spellendid.core.SpellendidConfig;
import net.tjalp.spellendid.fabric.SpellendidFabric;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(DownloadingTerrainScreen.class)
public class DownloadingTerrainScreenMixin {

    private final SpellendidFabric spellendidFabric = SpellendidFabric.INSTANCE;
    private final SpellendidConfig config = spellendidFabric.getConfig();
    private final FabricNetworkHandler networkHandler = (FabricNetworkHandler) spellendidFabric.getNetworkHandler();

    @Redirect(method = "tick", at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/screen/DownloadingTerrainScreen;loadStartTime:J", opcode = Opcodes.GETFIELD))
    private long redirectLoadStartTime(DownloadingTerrainScreen instance) {
        if (config.getRemoveLoadingTerrainDelay()
                && (networkHandler.getConnected() || config.getRemoveLoadingTerrainDelayEverywhere())) {
            return 0;
        }
        return ((DownloadingTerrainScreenAccessor) instance).getLoadStartTime();
    }
}
