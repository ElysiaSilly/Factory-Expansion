package com.teamcitrus.factory_expansion.client.event;

import com.mojang.blaze3d.platform.InputConstants;
import com.teamcitrus.factory_expansion.common.item.cycleable.CycleBlockItem;
import com.teamcitrus.factory_expansion.core.FactoExpa;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MaceItem;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import net.neoforged.neoforge.common.util.Lazy;
import org.lwjgl.glfw.GLFW;

@SuppressWarnings({"unused"})
@EventBusSubscriber(modid = FactoExpa.MODID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class GameRegistries {

    @SubscribeEvent
    public static void onClientTickEvent(ClientTickEvent.Post event) {
        while(ModRegistries.CYCLE_CYCLEABLE.get().consumeClick()) {
            if(Minecraft.getInstance().player == null) return;
            ItemStack item = Minecraft.getInstance().player.getMainHandItem();
            if(item.getItem() instanceof CycleBlockItem cycleItem) {
                cycleItem.cycleBlock();
            }
        }
    }
}
