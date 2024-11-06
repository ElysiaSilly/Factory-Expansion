package com.teamcitrus.factory_expansion.mixin;

import com.teamcitrus.factory_expansion.client.render.misc.MiscRendering;
import com.teamcitrus.factory_expansion.core.registry.FEComponents;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiGraphics.class)
public class GUIGraphicsMixin {

    @Inject(
            method = "renderItemDecorations(Lnet/minecraft/client/gui/Font;Lnet/minecraft/world/item/ItemStack;IILjava/lang/String;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/mojang/blaze3d/vertex/PoseStack;popPose()V"
            )
    )

    private void FactoExpa$renderItemDecorations(Font font, ItemStack stack, int x, int y, String text, CallbackInfo ci) {
        if(stack.has(FEComponents.CANISTER)) MiscRendering.renderCapacityBar(stack, x, y, (GuiGraphics) (Object) this);
    }

}
