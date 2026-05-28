package com.moepus.moestweaks.utils;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.structure.Structure;
import org.jetbrains.annotations.Nullable;

public class ChunkStuff {
    @Nullable
    public static BlockPos locateStructure(ServerLevel level, BlockPos initialPos, TagKey<Structure> target) {
        HolderLookup.RegistryLookup<Structure> lookup = level.registryAccess().lookup(Registries.STRUCTURE).orElse(null);
        if(lookup == null) return null;
        HolderSet<Structure> holderset = lookup.get(target).orElse(null);
        if(holderset == null) return null;
        Pair<BlockPos, Holder<Structure>> pair = level.getChunkSource().getGenerator().findNearestMapStructure(level, holderset, initialPos, 100, false);
        if (pair == null) {
            return null;
        } else {
            return pair.getFirst();
        }
    }
}
