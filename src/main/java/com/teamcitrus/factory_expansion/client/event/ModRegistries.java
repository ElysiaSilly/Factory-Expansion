package com.teamcitrus.factory_expansion.client.event;

import com.teamcitrus.factory_expansion.client.render.FlamethrowerExtension;
import com.teamcitrus.factory_expansion.client.render.be.DisplayBlockRenderer;
import com.teamcitrus.factory_expansion.core.FactoExpa;
import com.teamcitrus.factory_expansion.core.registry.FEBlockEntities;
import com.teamcitrus.factory_expansion.core.registry.FEItems;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

@SuppressWarnings({"unused"})
@EventBusSubscriber(modid = FactoExpa.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModRegistries {

    @SubscribeEvent
    public static void onRegisterClientExtensions(RegisterClientExtensionsEvent event) {
        event.registerItem(new FlamethrowerExtension(), FEItems.FLAMETHROWER);
    }

    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(FEBlockEntities.DISPLAY_BE.get(), DisplayBlockRenderer::new);
    }
}
