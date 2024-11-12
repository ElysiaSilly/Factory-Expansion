package com.teamcitrus.factory_expansion.common.item.CycleBlockItem;

import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;


public class OptionalStateBlock<T extends Comparable<T>, V extends T> {
    // this <> stuff is new to me so its probably very unstable atm, if something goes wrong scream at me

    private final Block block;
    private boolean context = false;

    public Dictionary<Property<T>, V> properties = new Hashtable<>();

    public OptionalStateBlock(Block block) {
        this.block = block;
    }

    public OptionalStateBlock<T, V> setState(Property<T> property, V value) {
        properties.put(property, value);
        return this;
    }

    public OptionalStateBlock placementContext() {
        context = true;
        return this;
    }

    public Block getBlock() {
        return this.block;
    }

    public BlockState getState(BlockPlaceContext context) {

        BlockState state = this.context ? getBlock().getStateForPlacement(context) : getBlock().defaultBlockState();

        Enumeration<Property<T>> i = properties.keys();

        while(i.hasMoreElements()) {
            Property<T> property = i.nextElement();
            V value = properties.get(property);

            state = state.setValue(property, value);
        }

        return state;
    }
}
