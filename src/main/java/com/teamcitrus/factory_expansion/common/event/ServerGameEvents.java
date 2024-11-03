package com.teamcitrus.factory_expansion.common.event;

import com.teamcitrus.factory_expansion.core.FactoExpa;
import com.teamcitrus.factory_expansion.core.FactoExpaRegistries;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.NewRegistryEvent;

@EventBusSubscriber(modid = FactoExpa.MODID, bus = EventBusSubscriber.Bus.GAME)
public class ServerGameEvents {

    @SubscribeEvent
    static void onNewRegistryEvent(NewRegistryEvent event) {
        event.register(FactoExpaRegistries.CANISTER_TYPE);
    }
}
