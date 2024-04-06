package dev.teamcitrusmods.factory_expansion.registry;

import dev.teamcitrusmods.factory_expansion.FactoryExpansion;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CreativeModeTabRegistry {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, FactoryExpansion.MODID);

    public static final RegistryObject<CreativeModeTab> TAB_FACTORY_EXPANSION = CREATIVE_TABS.register("factory_expansion", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.factory_expansion"))
            .icon(() -> new ItemStack(BlockRegistry.FLUX_LAMP_INVERTED.get()))
            .displayItems((pParameters, pOutput) -> {
                for (RegistryObject<Item> item : BlockRegistry.ITEMS.getEntries()) {
                    pOutput.accept(item.get());
                }
                for (RegistryObject<Item> item : ItemRegistry.ITEMS.getEntries()) {
                    pOutput.accept(item.get());
                }
            })
            .build()
    );
}
