package com.teamcitrus.factory_expansion.common.data.flamethrower;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class FlamethrowerComponent {

    public static final FlamethrowerComponent EMPTY = new FlamethrowerComponent(List.of(), ItemStack.EMPTY, 0);

    public static Codec<FlamethrowerComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ItemStack.CODEC.listOf().fieldOf("canisters").forGetter(i -> i.canisters),
            ItemStack.CODEC.fieldOf("nozzle").forGetter(i -> i.nozzle),
            Codec.INT.fieldOf("index").forGetter(i -> i.index)
    ).apply(instance, FlamethrowerComponent::new));

    //public static final StreamCodec<RegistryFriendlyByteBuf, FlamethrowerComponenta> STREAM_CODEC = ItemStack.STREAM_CODEC
    //        .apply(ByteBufCodecs.list())
    //        .map(FlamethrowerComponenta::new, i -> i.items);

    final List<ItemStack> canisters = new ArrayList<>(3);
    ItemStack nozzle;
    int index;

    public FlamethrowerComponent(List<ItemStack> canisters, ItemStack nozzle, int index) {
        this.canisters.addAll(canisters);
        this.nozzle = nozzle;
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

    public List<ItemStack> getCanisters() {
        return this.canisters;
    }

    public ItemStack getNozzle() {
        return this.nozzle;
    }

    public ItemStack removeNozzle() {
        if(this.nozzle.isEmpty()) {
            return ItemStack.EMPTY;
        } else {
            ItemStack stack = this.nozzle;
            this.nozzle = ItemStack.EMPTY;
            return stack;
        }
    }

    public ItemStack getCanisterAtIndex(int index) {
        return this.canisters.size() > index ? this.canisters.get(index) : ItemStack.EMPTY;
    }

    public boolean addNozzle(ItemStack nozzle) {
        if(this.nozzle.isEmpty()) {
            this.nozzle = nozzle; return true;
        } else {
            return false;
        }
    }

    public boolean addCanister(ItemStack canister) {
        if(this.canisters.size() < 3) {
            this.canisters.add(canister);
            return true;
        } else {
            return false;
        }
    }

    public ItemStack removeLastCanister() {
        if(this.canisters.isEmpty()) return ItemStack.EMPTY;
        ItemStack nozzle = this.canisters.getLast().copy();
        this.canisters.removeLast();
        return nozzle;
    }

    public ItemStack getCurrentCanister() {
        return getCanisterAtIndex(this.index);
    }

    public void cycleIndex() {
        if(this.index < 2) {
            this.index = this.index++;
        } else {
            this.index = 0;
        }
    }
}
