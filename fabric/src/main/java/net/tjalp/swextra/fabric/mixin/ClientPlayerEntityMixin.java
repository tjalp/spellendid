package net.tjalp.swextra.fabric.mixin;

import net.minecraft.client.network.ClientPlayerEntity;
import net.tjalp.swextra.core.SwConfig;
import net.tjalp.swextra.fabric.SwExtraFabric;
import net.tjalp.swextra.fabric.networking.FabricNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {

    private final SwExtraFabric swExtraFabric = SwExtraFabric.INSTANCE;
    private final SwConfig config = swExtraFabric.getConfig();
    private final FabricNetworkHandler networkHandler = (FabricNetworkHandler) swExtraFabric.getNetworkHandler();

    @Inject(method = "tick", at = @At(value = "HEAD"))
    private void tick(CallbackInfo ci) {
        if (!config.getPlayFallDamageSounds() && networkHandler.getConnected()) {
            ((ClientPlayerEntity) (Object) this).fallDistance = 0f;
        }
    }
}
