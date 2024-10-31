package com.teamcitrus.factory_expansion;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(FactoExpa.MODID)
public class FactoExpa {
    public static final String MODID = "factory_expansion";
    private static final Logger LOGGER = LogUtils.getLogger();



    public FactoExpa(IEventBus modEventBus, ModContainer modContainer) {
    }
}
