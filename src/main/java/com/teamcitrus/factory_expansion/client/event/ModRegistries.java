package com.teamcitrus.factory_expansion.client.event;

import com.mojang.blaze3d.platform.InputConstants;
import com.teamcitrus.factory_expansion.client.render.be.DisplayBERenderer;
import com.teamcitrus.factory_expansion.client.render.be.VentBERenderer;
import com.teamcitrus.factory_expansion.client.render.misc.CycleHudRenderer;
import com.teamcitrus.factory_expansion.client.render.model.FlamethrowerExtension;
import com.teamcitrus.factory_expansion.core.FactoExpa;
import com.teamcitrus.factory_expansion.core.registry.FEBlockEntities;
import com.teamcitrus.factory_expansion.core.registry.FEItems;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import net.neoforged.neoforge.common.util.Lazy;
import org.lwjgl.glfw.GLFW;

@SuppressWarnings({"unused"})
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
    public static void onRegisterGuiLayersEvent(RegisterGuiLayersEvent event) {
        event.registerBelowAll(ResourceLocation.fromNamespaceAndPath(FactoExpa.MODID, "cycle_item_icon"), CycleHudRenderer.LAYER);
    }

    @SubscribeEvent
    public static void onRegisterAdditionalModelEvent(ModelEvent.RegisterAdditional event) {
        event.register(new ModelResourceLocation(FactoExpa.location("special/flamethrower"), "standalone"));
    }

    public static final String CATEGORY = "key.category.factory_expansion";

    public static final Lazy<KeyMapping> CYCLE_CYCLEABLE = Lazy.of(() ->
            new KeyMapping("key.factory_expansion.cycle_cycleable", KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_V, CATEGORY));

    @SubscribeEvent
    private static void onRegisterKeyMappingsEvent(RegisterKeyMappingsEvent event) {
        event.register(CYCLE_CYCLEABLE.get());
    }
}
