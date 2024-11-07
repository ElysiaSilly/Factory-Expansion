package com.teamcitrus.factory_expansion.client.event.misc;

import com.teamcitrus.factory_expansion.common.flamethrower.canisterData.CanisterData;
import com.teamcitrus.factory_expansion.core.FactoExpa;
import com.teamcitrus.factory_expansion.core.registry.FEComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

@SuppressWarnings({"unused"})
@EventBusSubscriber(modid = FactoExpa.MODID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class CanisterTooltip {

    private static ItemStack previous;
    private static Component name;

    @SubscribeEvent
    public static void onRenderTooltipEvent(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();

        if(previous == stack) {
            if(name == null) return;
            event.getToolTip().set(0, Component.literal(event.getToolTip().getFirst().copy().getString() + " (" + name.getString() + ")"));
            //event.getToolTip().add(name);
            if(!stack.has(FEComponents.CANISTER)) return;
            if(stack.get(FEComponents.CANISTER).getCapacity() == -1) {
                event.getToolTip().add(Component.literal("Capacity: CREATIVE").withStyle(ChatFormatting.GRAY));
            } else {
                event.getToolTip().add(Component.literal(String.format("Capacity: %s / %s", stack.get(FEComponents.CANISTER).used(), stack.get(FEComponents.CANISTER).capacity())).withStyle(ChatFormatting.GRAY));
            }
        } else {
            Holder<Item> item = stack.getItemHolder();
            CanisterData data = item.getData(CanisterData.DATAMAP);
            if(data != null) {
                name = data.getCanisterType().getName();
            } else {
                name = null;
            }
        }

        previous = stack;
    }

}
