package com.teamcitrus.factory_expansion.core.keys;

import com.teamcitrus.factory_expansion.common.data.colourapplication.ColourMappingData;
import com.teamcitrus.factory_expansion.core.FactoExpa;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public class FEResourceKeys {

    public static final ResourceKey<Registry<ColourMappingData>> COLOUR_MAPPING
            = ResourceKey.createRegistryKey(FactoExpa.location("colour_mapping"));

}
