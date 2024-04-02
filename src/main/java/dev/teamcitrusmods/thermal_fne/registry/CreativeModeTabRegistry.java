package dev.teamcitrusmods.thermal_fne.registry;

import dev.teamcitrusmods.thermal_fne.ThermalFNE;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CreativeModeTabRegistry {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ThermalFNE.MODID);

    public static final RegistryObject<CreativeModeTab> TAB_THERMAL_FNE = CREATIVE_TABS.register("thermal_fne", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.thermal_fne"))
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
