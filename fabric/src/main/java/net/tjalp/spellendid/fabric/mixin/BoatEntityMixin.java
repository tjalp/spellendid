package net.tjalp.spellendid.fabric.mixin;

import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.nbt.NbtCompound;
import net.tjalp.spellendid.fabric.SpellendidFabric;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BoatEntity.class)
abstract class BoatEntityMixin {

    @Inject(method = "writeCustomDataToNbt", at = @At("RETURN"))
    private void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
        if (!SpellendidFabric.INSTANCE.getNetworkHandler().getConnected()) return;
        nbt.putBoolean("Invisible", ((BoatEntity) (Object) this).isInvisible());
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("RETURN"))
    private void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci) {
        if (!SpellendidFabric.INSTANCE.getNetworkHandler().getConnected()) return;
        ((BoatEntity) (Object) this).setInvisible(nbt.getBoolean("Invisible"));
    }
}
