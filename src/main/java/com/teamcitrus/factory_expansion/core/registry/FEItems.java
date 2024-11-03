package com.teamcitrus.factory_expansion.core.registry;

import com.teamcitrus.factory_expansion.common.item.FlamethrowerItem;
import com.teamcitrus.factory_expansion.core.FactoExpa;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class FEItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(FactoExpa.MODID);

    public static final DeferredItem<FlamethrowerItem> FLAMETHROWER =
            ITEMS.register("flamethrower", () ->
                    new FlamethrowerItem(new Item.Properties()
                            .stacksTo(1))
            );

    /// BLOCKITEMS
    public static final DeferredItem<BlockItem> TRANS_BLOCK = ITEMS.registerSimpleBlockItem(FEBlocks.TRANS_BLOCK);
}
