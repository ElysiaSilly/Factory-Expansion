package com.teamcitrus.factory_expansion.core.registry;

import net.minecraft.core.component.DataComponents;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.items.ComponentItemHandler;

public class FECapabilities {

    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerItem(
                Capabilities.ItemHandler.ITEM,
                (itemStack, context) -> new ComponentItemHandler(itemStack, DataComponents.CONTAINER, 3),
                FEItems.FLAMETHROWER.get()
        );
    }
}
