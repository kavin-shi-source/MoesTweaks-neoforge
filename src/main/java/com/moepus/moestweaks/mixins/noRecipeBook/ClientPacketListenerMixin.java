package com.moepus.moestweaks.mixins.noRecipeBook;

import net.minecraft.client.multiplayer.ClientPacketListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPacketListener.class)
public abstract class ClientPacketListenerMixin {

    @Inject(
            method = {"handleRecipeBookAdd", "handleRecipeBookRemove", "handlePlaceRecipe"},
            at = {@At("HEAD")},
            cancellable = true
    )
    public void onhandleRecipes(CallbackInfo ci) {
        ci.cancel();
    }

}

