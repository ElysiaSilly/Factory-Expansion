package dev.teamcitrusmods.factory_expansion;

import com.mojang.logging.LogUtils;
import dev.teamcitrusmods.factory_expansion.registry.BlockRegistry;
import dev.teamcitrusmods.factory_expansion.registry.CreativeModeTabRegistry;
import dev.teamcitrusmods.factory_expansion.registry.ItemRegistry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(FactoryExpansion.MODID)
public class FactoryExpansion
{
    public static final String MODID = "factory_expansion";
    public static final Logger LOGGER = LogUtils.getLogger();

    public FactoryExpansion()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        BlockRegistry.BLOCKS.register(modEventBus);
        BlockRegistry.ITEMS.register(modEventBus);
        ItemRegistry.ITEMS.register(modEventBus);
        CreativeModeTabRegistry.CREATIVE_TABS.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // intentionally left blank
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // intentionally left blank
        }
    }
}
