package com.moepus.moestweaks.mixins.noRecipeBook;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.gui.navigation.ScreenPosition;
import net.minecraft.world.inventory.RecipeBookMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(InventoryScreen.class)
public abstract class InventoryScreenMixin {
    @Shadow
    private RecipeBookComponent recipeBookComponent;

    @Shadow
    protected abstract ScreenPosition getRecipeBookButtonPosition();

    @Redirect(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/recipebook/RecipeBookComponent;init(IILnet/minecraft/client/Minecraft;ZLnet/minecraft/world/inventory/RecipeBookMenu;)V"))
    private void noopRecipeBookInit(RecipeBookComponent component, int width, int height, Minecraft minecraft, boolean widthTooNarrow, RecipeBookMenu menu) {
    }

    @Shadow
    protected abstract <T extends GuiEventListener & Renderable & NarratableEntry> T addRenderableWidget(T listener);

    @Shadow
    protected abstract <T extends GuiEventListener & NarratableEntry> T addWidget(T listener);

    @Unique
    private GuiEventListener moestweaks$addRenderableWidget(GuiEventListener listener) {
        return this.addRenderableWidget((GuiEventListener & Renderable & NarratableEntry) listener);
    }

    @Unique
    private GuiEventListener moestweaks$addWidget(GuiEventListener listener) {
        return this.addWidget((GuiEventListener & NarratableEntry) listener);
    }

    @Redirect(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/inventory/InventoryScreen;addRenderableWidget(Lnet/minecraft/client/gui/components/events/GuiEventListener;)Lnet/minecraft/client/gui/components/events/GuiEventListener;"))
    private GuiEventListener skipRecipeBookButton(InventoryScreen screen, GuiEventListener listener) {
        if (listener instanceof ImageButton button) {
            ScreenPosition pos = this.getRecipeBookButtonPosition();
            if (button.getX() == pos.x() && button.getY() == pos.y()) {
                return listener;
            }
        }
        return moestweaks$addRenderableWidget(listener);
    }

    @Redirect(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/inventory/InventoryScreen;addWidget(Lnet/minecraft/client/gui/components/events/GuiEventListener;)Lnet/minecraft/client/gui/components/events/GuiEventListener;"))
    private GuiEventListener skipRecipeBookWidget(InventoryScreen screen, GuiEventListener listener) {
        if (listener == recipeBookComponent) {
            return listener;
        }
        return moestweaks$addWidget(listener);
    }

    @Redirect(method = "containerTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/recipebook/RecipeBookComponent;tick()V"))
    private void noopRecipeBookTick(RecipeBookComponent component) {
    }

    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/recipebook/RecipeBookComponent;isVisible()Z"))
    private boolean neverShowRecipeBook(RecipeBookComponent component) {
        return false;
    }
}
