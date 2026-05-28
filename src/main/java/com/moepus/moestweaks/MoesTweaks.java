package com.moepus.moestweaks;

import com.moepus.moestweaks.effects.EffectRegistry;
import com.moepus.moestweaks.events.*;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Mod(MoesTweaks.MODID)
public class MoesTweaks {
    public static final String MODID = "moestweaks";
    public static final Logger LOGGER = LoggerFactory.getLogger(MoesTweaks.class);
    private final Config config;

    public MoesTweaks(IEventBus modEventBus) {
        ConfigParser.loadConfig();
        config = ConfigParser.getConfig();

        EffectRegistry.MOB_EFFECTS.register(modEventBus);

        if (config.stopFire) NeoForge.EVENT_BUS.addListener(NoFireSpread::onLevelLoaded);
        NeoForge.EVENT_BUS.addListener(NoCustomSpawner::onModifyCustomSpawners);
        if (config.villageSpawnPoint) NeoForge.EVENT_BUS.addListener(VillageSpawnPoint::onCreateSpawnPosition);
        if (config.stopNetherPortal) NeoForge.EVENT_BUS.addListener(NoNetherPortal::onPortalSpawn);
        if (config.bakaSilverFish) NeoForge.EVENT_BUS.addListener(SilverFishNoExp::onExpDrop);
        if (config.monsterWearsArmor) NeoForge.EVENT_BUS.addListener(MonsterWearsArmor::onEntitySpawn);
        if (config.damagedMonsterArmor) NeoForge.EVENT_BUS.addListener(DamagedMonsterArmor::onEntitySpawn);
        if (config.adrenalineEffect) NeoForge.EVENT_BUS.addListener(AdrenalineEffectGiver::onLivingDamage);

        if (FMLEnvironment.dist == Dist.CLIENT && config.hideShield) {
            NeoForge.EVENT_BUS.addListener(HideShield::onHandRender);
        }
    }
}
