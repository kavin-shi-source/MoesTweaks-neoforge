package com.moepus.moestweaks.effects;

import com.moepus.moestweaks.MoesTweaks;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

public class EffectRegistry {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, MoesTweaks.MODID);
    public static final DeferredHolder<MobEffect, ? extends MobEffect> ADRENALINE = MOB_EFFECTS.register("adrenaline", AdrenalineEffect::new);
}
