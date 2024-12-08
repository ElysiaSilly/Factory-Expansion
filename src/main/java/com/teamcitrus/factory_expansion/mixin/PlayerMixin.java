package com.teamcitrus.factory_expansion.mixin;

import com.teamcitrus.factory_expansion.common.data.wrench.WrenchComponent;
import com.teamcitrus.factory_expansion.core.registry.FEComponents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class PlayerMixin {

    // it's the thought that counts

    /*
    @Shadow @Final private Inventory inventory;

    @Inject(
            method = "getItemBySlot",
            at = @At(value = "HEAD"),
            cancellable = true
    )

    private void FactoExpa$getItemBySlot(EquipmentSlot slot1, CallbackInfoReturnable<ItemStack> cir) {
        if (slot1 == EquipmentSlot.MAINHAND) {
            ItemStack stack = this.inventory.getSelected();
            if(stack.has(FEComponents.WRENCH)) {
                WrenchComponent component = stack.get(FEComponents.WRENCH);
                if(component != null) {
                    cir.setReturnValue(component.getIndexWrenchNonNull(stack));
                    cir.cancel();
                }
            }
        }
    }

     */
}
