package com.teamcitrus.factory_expansion.common.event;

import com.teamcitrus.factory_expansion.common.data.colourapplication.ColourMappingData;
import com.teamcitrus.factory_expansion.common.data.flamethrower.canisterData.CanisterData;
import com.teamcitrus.factory_expansion.core.FERegistries;
import com.teamcitrus.factory_expansion.core.FactoExpa;
import com.teamcitrus.factory_expansion.core.keys.FEResourceKeys;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;
import net.neoforged.neoforge.registries.NewRegistryEvent;
import net.neoforged.neoforge.registries.datamaps.RegisterDataMapTypesEvent;

@EventBusSubscriber(modid = FactoExpa.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModRegistries {

    @SubscribeEvent
    private static void onDataPackRegistryEvent(DataPackRegistryEvent.NewRegistry event) {
        event.dataPackRegistry(FEResourceKeys.COLOUR_MAPPING, ColourMappingData.CODEC);
    }

    @SubscribeEvent
    private static void onRegisterDataMapTypes(RegisterDataMapTypesEvent event) {
        event.register(CanisterData.DATAMAP);
    }

    @SubscribeEvent
    static void onNewRegistryEvent(NewRegistryEvent event) {
        event.register(FERegistries.CANISTER_TYPE);
    }
}
