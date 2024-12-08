package com.teamcitrus.factory_expansion.core.registry;

import com.teamcitrus.factory_expansion.core.FactoExpa;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;

public class FECreativeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, FactoExpa.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TAB = CREATIVE_MODE_TAB.register("factory_expansion", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.factory_expansion"))
            .icon(() -> new ItemStack(Blocks.BARRIER))
            .displayItems((parameters, output) -> output.acceptAll(list(
                    FEItems.SCORCHED_BRICK,
                    FEItems.SCORCHED_BRICKS,
                    FEItems.SCORCHED_BRICK_STAIRS,
                    FEItems.SCORCHED_BRICK_SLAB,
                    FEItems.SCORCHED_BRICK_WALL,
                    FEItems.SCORCHED_TILE,
                    FEItems.SCORCHED_TILE_STAIRS,
                    FEItems.SCORCHED_TILE_SLAB,
                    FEItems.SCORCHED_TILE_WALL,

                    FEItems.ARID_ALLOY,
                    FEBlocks.ARID_ALLOY_BLOCK,
                    FEBlocks.CUT_ARID_ALLOY,
                    FEBlocks.CUT_ARID_ALLOY_STAIRS,
                    FEBlocks.CUT_ARID_ALLOY_SLAB,
                    FEBlocks.ARID_GRATE,
                    FEItems.ARID_GIRDER,

                    FEItems.PALE_ALLOY,
                    FEBlocks.PALE_ALLOY_BLOCK,
                    FEBlocks.CUT_PALE_ALLOY,
                    FEBlocks.CUT_PALE_ALLOY_STAIRS,
                    FEBlocks.CUT_PALE_ALLOY_SLAB,
                    FEBlocks.PALE_GRATE,
                    FEItems.PALE_GIRDER,

                    FEItems.BLACK_ALLOY,
                    FEBlocks.BLACK_ALLOY_BLOCK,
                    FEBlocks.CUT_BLACK_ALLOY,
                    FEBlocks.CUT_BLACK_ALLOY_STAIRS,
                    FEBlocks.CUT_BLACK_ALLOY_SLAB,
                    FEBlocks.BLACK_GRATE,
                    FEItems.BLACK_GIRDER
            )))
            .build()
    );

    private static List<ItemStack> list(Holder<?>...entries) {
        List<ItemStack> list = new ArrayList<>();
        for(Holder<?> entry : entries) {
            if(entry.value() instanceof Item item) {
                list.add(item.getDefaultInstance());
            }
            if(entry.value() instanceof Block block) {
                list.add(block.asItem().getDefaultInstance());
            }
        }
        return list;
    }
}
