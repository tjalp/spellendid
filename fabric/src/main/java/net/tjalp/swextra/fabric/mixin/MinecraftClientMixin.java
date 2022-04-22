package net.tjalp.swextra.fabric.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.tjalp.swextra.fabric.SwExtraFabric;
import net.tjalp.swextra.fabric.networking.FabricNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

    @Inject(method = "disconnect(Lnet/minecraft/client/gui/screen/Screen;)V", at = @At("HEAD"))
    private void onDisconnect(Screen screen, CallbackInfo ci) {
        FabricNetworkHandler handler = (FabricNetworkHandler) SwExtraFabric.INSTANCE.getNetworkHandler();

        if (handler.getConnected()) handler.disconnect();
    }
}