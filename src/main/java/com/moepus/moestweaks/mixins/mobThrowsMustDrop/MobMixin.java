package com.moepus.moestweaks.mixins.mobThrowsMustDrop;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Mob.class)
public abstract class MobMixin {
    @Shadow
    public abstract ItemStack setItemSlotAndDropWhenKilled(EquipmentSlot slot, ItemStack stack);

    @Unique
    private boolean moestweaks$dropped = false;

    @Inject(method = "equipItemIfPossible", at = @At("HEAD"))
    private void moestweaks$resetDropFlag(ItemStack stack, CallbackInfoReturnable<ItemStack> cir) {
        this.moestweaks$dropped = false;
    }

    @Redirect(method = "equipItemIfPossible", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Mob;setItemSlotAndDropWhenKilled(Lnet/minecraft/world/entity/EquipmentSlot;Lnet/minecraft/world/item/ItemStack;)V"))
    private void moestweaks$dropOldItemBeforeEquip(EquipmentSlot slot, ItemStack stack) {
        if (!this.moestweaks$dropped) {
            this.moestweaks$dropped = true;
            Mob self = (Mob)(Object)this;
            ItemStack oldItem = self.getItemBySlot(slot);
            if (!oldItem.isEmpty()) {
                self.spawnAtLocation(oldItem);
            }
        }
        this.setItemSlotAndDropWhenKilled(slot, stack);
    }
}
