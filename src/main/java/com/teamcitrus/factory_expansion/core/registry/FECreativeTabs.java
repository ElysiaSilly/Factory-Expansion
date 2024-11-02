package com.teamcitrus.factory_expansion.core.registry;

import com.teamcitrus.factory_expansion.core.FactoExpa;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class FECreativeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVETABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, FactoExpa.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> FACTO_EXPA = CREATIVETABS.register("factory_expansio", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.factory_expansion"))
            .icon(() -> new ItemStack(Blocks.BARRIER))
            .displayItems((parameters, output) -> {
                for (DeferredHolder<Item, ? extends Item> item : FEBlocks.BLOCKITEMS.getEntries()) {
                    output.accept(item.get());
                }
                for (DeferredHolder<Item, ? extends Item> item : FEItems.ITEMS.getEntries()) {
                    output.accept(item.get());
                }
            })
            .build()
    );
}
