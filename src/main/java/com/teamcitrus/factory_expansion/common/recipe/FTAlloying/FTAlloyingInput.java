package com.teamcitrus.factory_expansion.common.recipe.FTAlloying;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

import java.util.List;

public class FTAlloyingInput implements RecipeInput {

    public static final FTAlloyingInput EMPTY = new FTAlloyingInput(List.of());

    private final List<ItemStack> items;

    private FTAlloyingInput(List<ItemStack> items) {
        this.items = items;

    }

    @Override
    public ItemStack getItem(int index) {
        return this.items.get(index);
    }

    @Override
    public int size() {
        return this.items.size();
    }
}
