package com.elysiasilly.fne.common.event;

import com.elysiasilly.babel.api.events.ItemStackEvents;
import com.elysiasilly.fne.FactoExpa;
import com.elysiasilly.fne.common.datamap.CanisterData;
import com.elysiasilly.fne.core.registry.FEComponents;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber(modid = FactoExpa.MODID, bus = EventBusSubscriber.Bus.MOD)
public class GameRegistries {

    @SubscribeEvent
    private static void canisterItem(ItemStackEvents.Created event) {
        ItemStack stack = event.stack();

        CanisterData data = stack.getItemHolder().getData(CanisterData.DATAMAP);
        if(data != null && !stack.has(FEComponents.CANISTER.get())) {
            stack.set(FEComponents.CANISTER.get(), data.create());
        }
    }
}
