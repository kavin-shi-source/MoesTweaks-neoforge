package com.moepus.moestweaks.mixins.noRecipeBook;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "net.minecraft.client.gui.screens.inventory.AbstractRecipeBookScreen")
public abstract class AbstractRecipeBookMixin {
    @Inject(method = "initButton", at = @At("HEAD"), cancellable = true)
    private void disableRecipeBookButton(CallbackInfo ci) {
        ci.cancel();
    }
}
