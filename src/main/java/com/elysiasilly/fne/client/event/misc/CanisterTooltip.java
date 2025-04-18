package com.elysiasilly.fne.client.event.misc;

import com.elysiasilly.fne.common.component.CanisterComponent;
import com.elysiasilly.fne.FactoExpa;
import com.elysiasilly.fne.core.registry.FEComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

@EventBusSubscriber(modid = FactoExpa.MODID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class CanisterTooltip {


    @SubscribeEvent
    public static void onRenderTooltipEvent(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();

        if(stack.has(FEComponents.CANISTER) && event.getFlags().hasShiftDown()) {
            CanisterComponent component = stack.get(FEComponents.CANISTER);

            String id = event.getToolTip().getFirst().copy().getString();
            String string = Component.translatable("misc.fne.canister").getString().replaceAll("%1", id).replaceAll("%2", component.type().translationKey().getString());

            event.getToolTip().set(0, Component.literal(string));
        }
    }
}
