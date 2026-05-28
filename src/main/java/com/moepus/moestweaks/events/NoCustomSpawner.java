package com.moepus.moestweaks.events;

import com.moepus.moestweaks.Config;
import com.moepus.moestweaks.ConfigParser;
import net.minecraft.world.entity.ai.village.VillageSiege;
import net.minecraft.world.entity.npc.CatSpawner;
import net.minecraft.world.entity.npc.WanderingTraderSpawner;
import net.minecraft.world.level.levelgen.PatrolSpawner;
import net.minecraft.world.level.levelgen.PhantomSpawner;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.level.ModifyCustomSpawnersEvent;

public class NoCustomSpawner {
    @SubscribeEvent
    public static void onModifyCustomSpawners(ModifyCustomSpawnersEvent event) {
        Config config = ConfigParser.getConfig();
        if (config.noCatSpawner) event.getCustomSpawners().removeIf(spawner -> spawner instanceof CatSpawner);
        if (config.noPhantomSpawner) event.getCustomSpawners().removeIf(spawner -> spawner instanceof PhantomSpawner);
        if (config.noPatrolSpawner) event.getCustomSpawners().removeIf(spawner -> spawner instanceof PatrolSpawner);
        if (config.noVillageSiege) event.getCustomSpawners().removeIf(spawner -> spawner instanceof VillageSiege);
        if (config.noWanderingTraderSpawner) event.getCustomSpawners().removeIf(spawner -> spawner instanceof WanderingTraderSpawner);
    }
}
