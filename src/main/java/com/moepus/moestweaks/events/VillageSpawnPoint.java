package com.moepus.moestweaks.events;

import com.moepus.moestweaks.utils.ChunkStuff;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.features.MiscOverworldFeatures;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.StructureTags;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.levelgen.WorldOptions;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.neoforged.neoforge.event.level.LevelEvent;

public class VillageSpawnPoint {
    public static void onCreateSpawnPosition(LevelEvent.CreateSpawnPosition event) {
        LevelAccessor _level = event.getLevel();
        if (_level instanceof ServerLevel level) {
            WorldOptions worldGeneratorOptions = level.getServer().getWorldData().worldGenOptions();
            if (!worldGeneratorOptions.generateStructures()) return;

            BlockPos initialPos = BlockPos.ZERO.above(80);
            BlockPos village = ChunkStuff.locateStructure(level, initialPos, StructureTags.VILLAGE);
            if (village == null) return;

            level.setDefaultSpawnPos(village, 1.0f);

            if (worldGeneratorOptions.generateBonusChest()) {
                HolderLookup<ConfiguredFeature<?, ?>> featureLookup = level.registryAccess().lookup(Registries.CONFIGURED_FEATURE).orElse(null);
                if (featureLookup != null) {
                    featureLookup.get(MiscOverworldFeatures.BONUS_CHEST).ifPresent((holder) -> {
                        holder.value().place(level, level.getChunkSource().getGenerator(), level.random, village);
                    });
                }
            }
            event.setCanceled(true);
        }
    }
}
