package com.teamcitrus.factory_expansion.core;

import com.mojang.logging.LogUtils;
import com.teamcitrus.factory_expansion.core.registry.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(FactoExpa.MODID)
public class FactoExpa {

    public static final String MODID = "factory_expansion";
    public static final Logger LOGGER = LogUtils.getLogger();

    public FactoExpa(IEventBus bus, ModContainer container) {

        FEItems.ITEMS.register(bus);
        FEBlocks.BLOCKITEMS.register(bus);
        FEBlocks.BLOCKS.register(bus);
        FECreativeTabs.CREATIVETABS.register(bus);
        FEComponents.COMPONENTS.register(bus);

        //? what is this stuff?
        //? FERecipeSerializer.RECIPE_SERIALIZERS.register(bus);
        //? FERecipeTypes.RECIPE_TYPES.register(bus);
        FECanisterTypes.CANISTER.register(bus);

        /// Capabilities, yahoo
        bus.addListener(FECapabilities::registerCapabilities);

    }
}
