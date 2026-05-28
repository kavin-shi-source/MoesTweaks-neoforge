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
import java.util.HashMap;
import java.util.Map;

public class NoCustomSpawner {
    @SubscribeEvent
    public static void onModifyCustomSpawners(ModifyCustomSpawnersEvent event) {
        Config config = ConfigParser.getConfig();

        Map<Class<?>, Boolean> spawnerMappings = new HashMap<>();
        spawnerMappings.put(CatSpawner.class, config.noCatSpawner);
        spawnerMappings.put(PhantomSpawner.class, config.noPhantomSpawner);
        spawnerMappings.put(PatrolSpawner.class, config.noPatrolSpawner);
        spawnerMappings.put(VillageSiege.class, config.noVillageSiege);
        spawnerMappings.put(WanderingTraderSpawner.class, config.noWanderingTraderSpawner);

        for (Map.Entry<Class<?>, Boolean> entry : spawnerMappings.entrySet()) {
            if (entry.getValue()) {
                event.getCustomSpawners().removeIf(spawner -> entry.getKey().isInstance(spawner));
            }
        }
    }
}
