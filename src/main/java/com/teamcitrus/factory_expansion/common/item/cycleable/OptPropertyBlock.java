package com.teamcitrus.factory_expansion.common.item.cycleable;

import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;


public class OptPropertyBlock<T extends Comparable<T>, V extends T> {
    // this <> stuff is new to me so its probably very unstable atm, if something goes wrong scream at me

    private final Block block;
    private boolean context = false;
    private final int cost;

    public Dictionary<Property<T>, V> properties = new Hashtable<>();

    public OptPropertyBlock(Block block, int cost) {
        this.block = block;
        this.cost = cost;
    }

    public OptPropertyBlock<T, V> setProperty(Property<T> property, V value) {
        properties.put(property, value);
        return this;
    }

    public OptPropertyBlock<T, V> placementContext() {
        context = true;
        return this;
    }

    public Block getBlock() {
        return this.block;
    }

    public int getCost() {
        return this.cost;
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
