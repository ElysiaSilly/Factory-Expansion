package com.teamcitrus.factory_expansion.core;

import com.mojang.logging.LogUtils;
import com.teamcitrus.factory_expansion.core.registry.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

import java.util.Random;

@Mod(FactoExpa.MODID)
public class FactoExpa {

    public static Random random = new java.util.Random();

    public static final String MODID = "factory_expansion";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static String prefix(String string) { return MODID + ":" + string; }

    public static int drywall = random.nextInt(10);

    public FactoExpa(IEventBus bus, ModContainer container) {

        FEItems.ITEMS.register(bus);
        FEBlocks.BLOCKITEMS.register(bus);
        FEBlocks.BLOCKS.register(bus);
        FECreativeTabs.CREATIVETABS.register(bus);
        FEComponents.COMPONENTS.register(bus);
        FEBlockEntities.BLOCKENTITIES.register(bus);

        //? what is this stuff?
        //? FERecipeSerializer.RECIPE_SERIALIZERS.register(bus);
        //? FERecipeTypes.RECIPE_TYPES.register(bus);
        FECanisterTypes.CANISTER.register(bus);

        /// Capabilities, yahoo
        bus.addListener(FECapabilities::registerCapabilities);
    }
}
