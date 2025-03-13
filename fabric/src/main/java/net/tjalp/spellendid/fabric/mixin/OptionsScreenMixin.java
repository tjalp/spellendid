package net.tjalp.spellendid.fabric.mixin;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;
import net.tjalp.spellendid.fabric.SpellendidConfigFabric;
import net.tjalp.spellendid.fabric.SpellendidFabric;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collections;

@Mixin(OptionsScreen.class)
abstract class OptionsScreenMixin extends Screen {

    protected OptionsScreenMixin(Text title) {
        super(title);
    }

    @Inject(method = "init", at = @At("RETURN"))
    private void init(CallbackInfo ci) {
        if (this.client == null || !SpellendidFabric.INSTANCE.getNetworkHandler().getConnected()) return;
        this.addDrawableChild(
                new ButtonWidget.Builder(
                        Text.translatable("title.spellendid"),
                        (button) -> this.client.setScreen(SpellendidConfigFabric.INSTANCE.buildConfigScreen(this))
                )
                        .dimensions(this.width / 2 - 155, this.height / 6 + 24 - 6, 310, 20)
                        .build()
//                new ButtonWidget(
//                        this.width / 2 - 155,
//                        this.height / 6 + 24 - 6,
//                        310,
//                        20,
//                        new TranslatableTextContent("title.spellendid", "Spellendid", Collections.emptyList().toArray()),
//                        (button) -> this.client.setScreen(SpellendidConfigFabric.INSTANCE.buildConfigScreen(this))
//                )
        );
    }
}
