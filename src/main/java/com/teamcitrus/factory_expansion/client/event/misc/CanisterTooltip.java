package com.teamcitrus.factory_expansion.client.event.misc;

import com.teamcitrus.factory_expansion.common.flamethrower.canisterData.CanisterData;
import com.teamcitrus.factory_expansion.core.FactoExpa;
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
        ItemStack itemStack = event.getItemStack();

        if(previous == itemStack) {
            if(name != null) {
                event.getToolTip().add(name);
            }
        } else {
            Holder<Item> item = itemStack.getItemHolder();
            CanisterData data = item.getData(CanisterData.DATAMAP);
            if(data != null) {
                name = data.getCanisterType().getName();
            } else {
                name = null;
            }
        }

        previous = itemStack;
    }
}
