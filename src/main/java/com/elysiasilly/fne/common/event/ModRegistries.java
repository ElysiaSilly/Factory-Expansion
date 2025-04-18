package com.elysiasilly.fne.common.event;

import com.elysiasilly.fne.common.data.dyeing.DyeData;
import com.elysiasilly.fne.common.data.dyeing.DyeingData;
import com.elysiasilly.fne.common.datamap.CanisterData;
import com.elysiasilly.fne.core.FERegistries;
import com.elysiasilly.fne.FactoExpa;
import com.elysiasilly.fne.core.keys.FEResourceKeys;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;
import net.neoforged.neoforge.registries.NewRegistryEvent;
import net.neoforged.neoforge.registries.datamaps.RegisterDataMapTypesEvent;

@EventBusSubscriber(modid = FactoExpa.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModRegistries {

    @SubscribeEvent
    private static void register(DataPackRegistryEvent.NewRegistry event) {
        event.dataPackRegistry(FEResourceKeys.registries.DYEING, DyeingData.CODEC, DyeingData.CODEC);
        event.dataPackRegistry(FEResourceKeys.registries.DYE, DyeData.CODEC, DyeData.CODEC);
    }

    @SubscribeEvent
    private static void register(RegisterDataMapTypesEvent event) {
        event.register(CanisterData.DATAMAP);
    }

    @SubscribeEvent
    private static void register(NewRegistryEvent event) {
        event.register(FERegistries.CANISTER_TYPE);
    }
}
