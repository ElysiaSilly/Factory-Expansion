package com.teamcitrus.factory_expansion.client;

import com.teamcitrus.factory_expansion.common.flamethrower.canisterData.CanisterData;
import com.teamcitrus.factory_expansion.core.FactoExpa;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderTooltipEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

@SuppressWarnings({"unused"})
@EventBusSubscriber(modid = FactoExpa.MODID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class ClientGameEvents {

    @SubscribeEvent
    public static void onRenderTooltipEvent(ItemTooltipEvent event) {
        ItemStack itemStack = event.getItemStack();

        // TODO : cache these values, doing the lookup every tick is silly

        Holder<Item> item = itemStack.getItemHolder();

        CanisterData data = item.getData(CanisterData.getDataMap());

        if(data == null) return;

        event.getToolTip().add(data.getCanisterType().getName());
    }
}
