package com.elysiasilly.fne.client.event;

import com.elysiasilly.fne.client.render.be.DisplayBERenderer;
import com.elysiasilly.fne.client.render.be.VentBERenderer;
import com.elysiasilly.fne.client.render.model.FlamethrowerExtension;
import com.elysiasilly.fne.FactoExpa;
import com.elysiasilly.fne.core.registry.FEBlockEntities;
import com.elysiasilly.fne.core.registry.FEItems;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

@EventBusSubscriber(modid = FactoExpa.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModRegistries {

    @SubscribeEvent
    public static void onRegisterClientExtensions(RegisterClientExtensionsEvent event) {
        event.registerItem(new FlamethrowerExtension(), FEItems.FLAMETHROWER);
    }

    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(FEBlockEntities.DISPLAY_BE.get(), DisplayBERenderer::new);
        event.registerBlockEntityRenderer(FEBlockEntities.VENT_BE.get(), VentBERenderer::new);

    }

    @SubscribeEvent
    public static void onRegisterAdditionalModelEvent(ModelEvent.RegisterAdditional event) {
        event.register(new ModelResourceLocation(FactoExpa.location("special/flamethrower"), "standalone"));
    }
}
