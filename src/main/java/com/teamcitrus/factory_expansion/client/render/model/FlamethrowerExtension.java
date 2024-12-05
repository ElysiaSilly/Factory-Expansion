package com.teamcitrus.factory_expansion.client.render.model;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

public class FlamethrowerExtension implements IClientItemExtensions {

    private final FlamethrowerRenderer FLAMETHROWER = new FlamethrowerRenderer();

    @Override
    public BlockEntityWithoutLevelRenderer getCustomRenderer() {
        return FLAMETHROWER;
    }
}
