package com.teamcitrus.factory_expansion.common.data.flamethrower.canisterData;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class FlamethrowerComponent {

    public static final FlamethrowerComponent EMPTY = new FlamethrowerComponent(List.of(), ItemStack.EMPTY);

    public static Codec<FlamethrowerComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ItemStack.CODEC.listOf().fieldOf("canisters").forGetter(i -> i.canisters),
            ItemStack.CODEC.fieldOf("nozzle").forGetter(i -> i.nozzle)

            ).apply(instance, FlamethrowerComponent::new)
    );

    //public static final StreamCodec<RegistryFriendlyByteBuf, FlamethrowerComponenta> STREAM_CODEC = ItemStack.STREAM_CODEC
    //        .apply(ByteBufCodecs.list())
    //        .map(FlamethrowerComponenta::new, i -> i.items);

    final List<ItemStack> canisters = new ArrayList<>(3);
    ItemStack nozzle;

    public FlamethrowerComponent(List<ItemStack> canisters, ItemStack nozzle) {
        this.canisters.addAll(canisters);
        this.nozzle = nozzle;
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

    /*
    public List<ItemStack> getItems() {
        return items;
    }

    public boolean addNozzle(ItemStack nozzle) {
       if(items.getFirst().isEmpty()){ items.set(0, nozzle); return true; } else { return false; }
    }

    public ItemStack removeNozzle() {
        if(!items.getFirst().isEmpty()) {
            ItemStack nozzle = items.getFirst().copy();
            items.removeFirst();
            return nozzle;
        } else {
            return ItemStack.EMPTY;
        }
    }

    public boolean addCanister(ItemStack canister) {
        if(items.size() < 3) {
            items.add(canister);
            return true;
        } else {
            return false;
        }
    }

     */
}
