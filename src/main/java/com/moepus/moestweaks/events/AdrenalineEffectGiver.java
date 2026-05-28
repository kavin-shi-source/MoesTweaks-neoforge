package com.moepus.moestweaks.events;

import com.moepus.moestweaks.effects.EffectRegistry;
import net.minecraft.core.Holder;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

public class AdrenalineEffectGiver {
    @SubscribeEvent
    public static void onLivingDamage(LivingIncomingDamageEvent event) {
        if (event.getEntity() instanceof Player player) {
            Holder<DamageType> dmgType = event.getSource().typeHolder();
            if (!dmgType.is(DamageTypes.MOB_ATTACK) && !dmgType.is(DamageTypes.MOB_PROJECTILE) && !dmgType.is(DamageTypes.MOB_ATTACK_NO_AGGRO) && !dmgType.is(DamageTypes.EXPLOSION) && !dmgType.is(DamageTypes.MAGIC))
                return;

            if (player.isAlive() && player.getHealth() - event.getAmount() <= 1.5F && !player.hasEffect(EffectRegistry.ADRENALINE)) {
                player.addEffect(new MobEffectInstance(EffectRegistry.ADRENALINE, 70, 0));
                if (player.hasEffect(MobEffects.HUNGER)) {
                    player.removeEffect(MobEffects.HUNGER);
                }
            }
        }
    }
}
