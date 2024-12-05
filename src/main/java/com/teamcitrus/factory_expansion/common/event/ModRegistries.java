package com.teamcitrus.factory_expansion.common.event;

import com.teamcitrus.factory_expansion.common.data.dyeing.DyeData;
import com.teamcitrus.factory_expansion.common.data.dyeing.DyeingData;
import com.teamcitrus.factory_expansion.common.data.flamethrower.CanisterData;
import com.teamcitrus.factory_expansion.core.FERegistries;
import com.teamcitrus.factory_expansion.core.FactoExpa;
import com.teamcitrus.factory_expansion.core.keys.FEResourceKeys;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;
import net.neoforged.neoforge.registries.NewRegistryEvent;

@EventBusSubscriber(modid = FactoExpa.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModRegistries {

    @SubscribeEvent
    private static void onDataPackRegistryEvent(DataPackRegistryEvent.NewRegistry event) {
        event.dataPackRegistry(FEResourceKeys.DYEING, DyeingData.CODEC, DyeingData.CODEC);
        event.dataPackRegistry(FEResourceKeys.CANISTER, CanisterData.CODEC, CanisterData.CODEC);
        event.dataPackRegistry(FEResourceKeys.DYE, DyeData.CODEC, DyeData.CODEC);
    }

    //@SubscribeEvent
    //private static void onRegisterDataMapTypes(RegisterDataMapTypesEvent event) {
    //    event.register(CanisterData.DATAMAP);
    //}

    @SubscribeEvent
    static void onNewRegistryEvent(NewRegistryEvent event) {
        event.register(FERegistries.CANISTER_TYPE);
    }
}
