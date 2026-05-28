package com.moepus.moestweaks.events;

import net.neoforged.neoforge.event.level.BlockEvent;

public class NoNetherPortal {
    public static void onPortalSpawn(BlockEvent.PortalSpawnEvent event)
    {
        event.setCanceled(true);
    }
}
