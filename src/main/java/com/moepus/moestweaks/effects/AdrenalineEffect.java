package com.moepus.moestweaks.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import org.jetbrains.annotations.NotNull;

public class AdrenalineEffect extends MobEffect {
    public AdrenalineEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xFFD700);
    }

    @Override
    public boolean applyEffectTick(@NotNull LivingEntity entity, int amplifier) {
        if (entity instanceof Player player) {
            if (player.level().isClientSide)
                return true;

            if (player.getAbsorptionAmount() < 2.0f) {
                player.setAbsorptionAmount(2.0f);
            }

            FoodData foodData = player.getFoodData();
            if (foodData.getFoodLevel() > 6 && player.getHealth() <= player.getMaxHealth() * 0.36f) {
                foodData.addExhaustion(7);
                player.heal(1.0F);
            }
        }
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return duration % 7 == 1;
    }
}
