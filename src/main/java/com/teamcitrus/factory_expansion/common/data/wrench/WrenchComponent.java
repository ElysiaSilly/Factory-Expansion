package com.teamcitrus.factory_expansion.common.data.wrench;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.teamcitrus.factory_expansion.core.keys.FETags;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class WrenchComponent {

    public static final WrenchComponent EMPTY = new WrenchComponent(List.of(), 0);

    public static final Codec<WrenchComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                    ItemStack.CODEC.listOf().fieldOf("wrenches").forGetter(i -> i.wrenches),
                    Codec.INT.fieldOf("index").forGetter(i -> i.index)
    ).apply(instance, WrenchComponent::new));

    final List<ItemStack> wrenches;
    int index;

    public WrenchComponent(List<ItemStack> wrenches, int index) {
        this.wrenches = wrenches;
        this.index = index;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public boolean insertWrench(ItemStack stack) {
        if(stack.is(FETags.Items.WRENCH)) {
            //for(ItemStack wrench : this.wrenches) {
            //    if(wrench.getItem().equals(stack.getItem())) return false;
            //    System.out.println("help");
            //}
            this.wrenches.add(stack.copy());
            stack.shrink(1);
            return true;
        }
        return false;
    }

    public ItemStack extractLastWrench() {
        if(this.wrenches.isEmpty()) return null;
        ItemStack copy = this.wrenches.getLast();
        this.wrenches.removeLast();
        return copy;
    }

    public void cycleIndex() {
        if(this.index < this.wrenches.size()) {
            this.index++;
        } else {
            this.index = 0;
        }
    }

    public ItemStack getIndexWrench() {
        if(this.index >= this.wrenches.size()) return null;
        return this.wrenches.get(this.index);
    }

    public ItemStack getIndexWrenchNonNull(ItemStack stack) {
        if(this.index >= this.wrenches.size() || this.wrenches.isEmpty()) return stack;
        return this.wrenches.get(this.index);
    }

    public List<ItemStack> getWrenches() {
        return this.wrenches;
    }

    public int getIndex() {
        return this.index;
    }

    public boolean setIndex(int index) {
        if(index <= this.wrenches.size()) {
            this.index = index;
            return true;
        }
        return false;
    }

    public void cycleAppropriateWrench(BlockState state) {
        if(this.wrenches.isEmpty()) return;
        String namespace = BuiltInRegistries.BLOCK.getKey(state.getBlock()).getNamespace();
        System.out.println(namespace);
        int index = 0;
        for(ItemStack stack : this.wrenches) {
            String wrenchNamespace = BuiltInRegistries.ITEM.getKey(stack.getItem()).getNamespace();
            if(wrenchNamespace.equals(namespace)) {this.setIndex(index); return;}
            index++;
        }
        this.index = this.wrenches.size();
    }
}
