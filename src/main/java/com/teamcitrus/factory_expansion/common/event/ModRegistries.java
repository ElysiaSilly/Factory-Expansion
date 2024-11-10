package com.teamcitrus.factory_expansion.common.event;

import com.teamcitrus.factory_expansion.common.data.flamethrower.canisterData.CanisterData;
import com.teamcitrus.factory_expansion.core.FactoExpa;
import com.teamcitrus.factory_expansion.core.FactoExpaRegistries;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.NewRegistryEvent;
import net.neoforged.neoforge.registries.datamaps.RegisterDataMapTypesEvent;

@EventBusSubscriber(modid = FactoExpa.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModRegistries {

    @SubscribeEvent
    private static void onRegisterDataMapTypes(RegisterDataMapTypesEvent event) {
        event.register(CanisterData.DATAMAP);
    }

    @SubscribeEvent
    static void onNewRegistryEvent(NewRegistryEvent event) {
        event.register(FactoExpaRegistries.CANISTER_TYPE);
    }
}
