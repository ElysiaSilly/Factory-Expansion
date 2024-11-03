package com.teamcitrus.factory_expansion.core;

import com.mojang.logging.LogUtils;
import com.teamcitrus.factory_expansion.core.registry.FEBlocks;
import com.teamcitrus.factory_expansion.core.registry.FECreativeModeTabs;
import com.teamcitrus.factory_expansion.core.registry.FEItems;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(FactoExpa.MODID)
public class FactoExpa {
    public static final String MODID = "factory_expansion";
    private static final Logger LOGGER = LogUtils.getLogger();



    public FactoExpa(IEventBus bus, ModContainer container) {

        FEBlocks.BLOCKS.register(bus);
        FEItems.ITEMS.register(bus);
        FECreativeModeTabs.CREATIVETABS.register(bus);

    }
}
