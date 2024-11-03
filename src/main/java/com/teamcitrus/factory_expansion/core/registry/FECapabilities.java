package com.teamcitrus.factory_expansion.core.registry;

import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

public class FECapabilities {

    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerItem(
                Capabilities.ItemHandler.ITEM,
                (itemStack, context) -> FEItems.FLAMETHROWER.get().getItemHandler().getCapability(itemStack, context),
                FEItems.FLAMETHROWER.asItem()
        );
    }
}
