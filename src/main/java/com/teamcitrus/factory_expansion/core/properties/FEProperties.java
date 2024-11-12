package com.teamcitrus.factory_expansion.core.properties;

import com.teamcitrus.factory_expansion.core.properties.properties.FluxBulbMode;
import com.teamcitrus.factory_expansion.core.properties.properties.LargeVentBlocks;
import com.teamcitrus.factory_expansion.core.properties.properties.MediumVentBlocks;
import com.teamcitrus.factory_expansion.core.properties.properties.WarningLightState;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class FEProperties {

    public static final EnumProperty<FluxBulbMode> FLUX_BULB_MODE = EnumProperty.create("mode", FluxBulbMode.class);
    public static final EnumProperty<WarningLightState> WARNING_LIGHT_STATE = EnumProperty.create("state", WarningLightState.class);
    public static final EnumProperty<LargeVentBlocks> LARGE_VENT_BLOCKS = EnumProperty.create("block", LargeVentBlocks.class);
    public static final EnumProperty<MediumVentBlocks> MEDIUM_VENT_BLOCKS = EnumProperty.create("block", MediumVentBlocks.class);

}
