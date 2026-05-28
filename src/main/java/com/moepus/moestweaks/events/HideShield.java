package com.moepus.moestweaks.events;

import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.neoforged.neoforge.client.event.RenderHandEvent;
import net.neoforged.neoforge.common.Tags;

public class HideShield {
    private static final Minecraft mc = Minecraft.getInstance();
    public static void onHandRender(RenderHandEvent event) {
        if(mc.player == null) return;
        if(mc.player.isUsingItem()) return;

        if(!event.getHand().equals(InteractionHand.OFF_HAND))
            return;

        if(event.getItemStack().is(Tags.Items.TOOLS_SHIELD))
            event.setCanceled(true);
    }
}
