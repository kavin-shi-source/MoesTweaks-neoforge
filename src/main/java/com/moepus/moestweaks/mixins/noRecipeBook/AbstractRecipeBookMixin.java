package com.moepus.moestweaks.mixins.noRecipeBook;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "net.minecraft.client.gui.screens.inventory.AbstractRecipeBookScreen")
public abstract class AbstractRecipeBookMixin {
    @Inject(method = "initButton", at = @At("HEAD"), cancellable = true)
    private void disableRecipeBookButton(CallbackInfo ci) {
        ci.cancel();
    }

    @Redirect(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/recipebook/RecipeBookComponent;init(IILnet/minecraft/client/Minecraft;Z)V"))
    private void noopRecipeBookInit(RecipeBookComponent component, int width, int height, Minecraft minecraft, boolean widthTooNarrow) {
    }

    @Redirect(method = "containerTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/recipebook/RecipeBookComponent;tick()V"))
    private void noopRecipeBookTick(RecipeBookComponent component) {
    }
}
