package com.moepus.moestweaks.mixins.noRecipeBook;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.world.inventory.RecipeBookMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InventoryScreen.class)
public abstract class InventoryScreenMixin {
    @Redirect(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/recipebook/RecipeBookComponent;init(IILnet/minecraft/client/Minecraft;ZLnet/minecraft/world/inventory/RecipeBookMenu;)V"))
    private void noopRecipeBookInit(RecipeBookComponent component, int width, int height, Minecraft minecraft, boolean widthTooNarrow, RecipeBookMenu menu) {
    }

    @Redirect(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/inventory/InventoryScreen;addRenderableWidget(Lnet/minecraft/client/gui/components/events/GuiEventListener;)Lnet/minecraft/client/gui/components/events/GuiEventListener;"))
    private net.minecraft.client.gui.components.events.GuiEventListener skipRecipeBookButton(InventoryScreen screen, net.minecraft.client.gui.components.events.GuiEventListener listener) {
        return listener;
    }

    @Redirect(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/inventory/InventoryScreen;addWidget(Lnet/minecraft/client/gui/components/events/GuiEventListener;)Lnet/minecraft/client/gui/components/events/GuiEventListener;"))
    private net.minecraft.client.gui.components.events.GuiEventListener skipRecipeBookWidget(InventoryScreen screen, net.minecraft.client.gui.components.events.GuiEventListener listener) {
        return listener;
    }

    @Redirect(method = "containerTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/recipebook/RecipeBookComponent;tick()V"))
    private void noopRecipeBookTick(RecipeBookComponent component) {
    }

    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/recipebook/RecipeBookComponent;isVisible()Z"))
    private boolean neverShowRecipeBook(RecipeBookComponent component) {
        return false;
    }
}
