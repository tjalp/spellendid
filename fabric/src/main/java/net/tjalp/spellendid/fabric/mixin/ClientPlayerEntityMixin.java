package net.tjalp.spellendid.fabric.mixin;

import net.minecraft.client.network.ClientPlayerEntity;
import net.tjalp.spellendid.core.SpellendidConfig;
import net.tjalp.spellendid.fabric.SpellendidFabric;
import net.tjalp.spellendid.fabric.networking.FabricNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
abstract class ClientPlayerEntityMixin {

    private final SpellendidFabric spellendidFabric = SpellendidFabric.INSTANCE;
    private final SpellendidConfig config = spellendidFabric.getConfig();
    private final FabricNetworkHandler networkHandler = (FabricNetworkHandler) spellendidFabric.getNetworkHandler();

    @Inject(method = "tick", at = @At(value = "HEAD"))
    private void tick(CallbackInfo ci) {
        if (!config.getPlayFallDamageSounds() && networkHandler.getConnected()) {
            ((ClientPlayerEntity) (Object) this).fallDistance = 0f;
        }
    }
}
