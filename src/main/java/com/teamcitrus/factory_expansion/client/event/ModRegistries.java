package com.teamcitrus.factory_expansion.client.event;

import com.teamcitrus.factory_expansion.client.render.model.FlamethrowerExtension;
import com.teamcitrus.factory_expansion.client.render.be.DisplayBlockRenderer;
import com.teamcitrus.factory_expansion.client.render.misc.CycleHudRenderer;
import com.teamcitrus.factory_expansion.core.FactoExpa;
import com.teamcitrus.factory_expansion.core.registry.FEBlockEntities;
import com.teamcitrus.factory_expansion.core.registry.FEItems;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
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

    @SubscribeEvent
    public static void onRegisterGuiLayersEvent(RegisterGuiLayersEvent event) {
        event.registerBelowAll(ResourceLocation.fromNamespaceAndPath(FactoExpa.MODID, "cycle_item_icon"), CycleHudRenderer.LAYER);
    }

    @SubscribeEvent
    public static void onRegisterAdditionalModelEvent(ModelEvent.RegisterAdditional event) {
        event.register(new ModelResourceLocation(FactoExpa.location("special/flamethrower"), "standalone"));
    }
}
