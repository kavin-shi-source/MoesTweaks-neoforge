package com.moepus.moestweaks.events;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.FinalizeSpawnEvent;

public class DamagedMonsterArmor {
    @SubscribeEvent
    public static void onEntitySpawn(FinalizeSpawnEvent event) {
        if (!(event.getEntity() instanceof Mob mob))
            return;

        for (EquipmentSlot slot : EquipmentSlot.values()) {
            if (slot.getType() == EquipmentSlot.Type.HUMANOID_ARMOR) {
                ItemStack itemStack = mob.getItemBySlot(slot);

                if (itemStack.isEmpty() || !itemStack.isDamageableItem())
                    continue;

                int maxDamage = itemStack.getMaxDamage();
                int minDamage = 0;
                int maxMinus = (int) (maxDamage * 0.30f);
                double random = Math.random();
                double probability = 0.50;

                if (random < probability) {
                    int damage = minDamage + (int) (Math.random() * (maxMinus - minDamage + 1));

                    if (damage != 0) {
                        itemStack.setDamageValue(damage);
                    }
                }
            }
        }
    }
}
