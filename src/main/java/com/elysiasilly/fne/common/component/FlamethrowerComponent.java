package com.elysiasilly.fne.common.component;

import com.elysiasilly.babel.util.resource.RGBA;
import com.elysiasilly.fne.FactoExpa;
import com.elysiasilly.fne.core.registry.FEComponents;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public record FlamethrowerComponent(int size, List<ItemStack> canisters, int index) {

    public static final FlamethrowerComponent EMPTY = new FlamethrowerComponent(3, List.of(), 0);

    public static final Codec<FlamethrowerComponent> CODEC = RecordCodecBuilder.create(builder -> builder.group(
            Codec.INT.fieldOf("size").forGetter(FlamethrowerComponent::size),
            ItemStack.OPTIONAL_CODEC.listOf().fieldOf("canisters").forGetter(FlamethrowerComponent::canisters),
            Codec.INT.fieldOf("index").forGetter(i -> i.index)
    ).apply(builder, FlamethrowerComponent::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, FlamethrowerComponent> STREAM = StreamCodec.composite(
            ByteBufCodecs.INT, FlamethrowerComponent::size,
            ItemStack.OPTIONAL_LIST_STREAM_CODEC, FlamethrowerComponent::canisters,
            ByteBufCodecs.INT, FlamethrowerComponent::index,
            FlamethrowerComponent::new
    );

    public boolean canRemove() {
        return !canisters().isEmpty();
    }

    public FlamethrowerComponent removeLast() {
        List<ItemStack> canisters = new ArrayList<>(canisters());
        canisters.removeLast();
        return new FlamethrowerComponent(size(), canisters, index());
    }

    public ItemStack getLast() {
        return canisters().getLast();
    }

    public boolean canAdd() {
        return size() >= canisters().size() + 1;
    }

    private FlamethrowerComponent addLast(ItemStack canister) {
        List<ItemStack> canisters = new ArrayList<>(canisters());
        canisters.add(canister);
        return new FlamethrowerComponent(size(), canisters, index());
    }

    public ItemStack getCanisterAtIndex() {
        return getCanisterAtIndex(index());
    }

    public ItemStack getCanisterAtIndex(int index) {
        return index >= canisters().size() || index < 0 ? ItemStack.EMPTY : canisters().get(index);
    }

    public FlamethrowerComponent nextIndex() {
        return new FlamethrowerComponent(size(), canisters(), index() >= size() ? 0 : index() + 1);
    }

    public FlamethrowerComponent previousIndex() {
        return new FlamethrowerComponent(size(), canisters(), index() <= 0 ? size() : index() - 1);
    }

    ///

    public static void removeCanister(ItemStack flamethrower) {
        flamethrower.set(FEComponents.FLAMETHROWER, flamethrower.get(FEComponents.FLAMETHROWER).removeLast());
    }

    public static void putCanister(ItemStack flamethrower, ItemStack canister) {
        if(flamethrower.has(FEComponents.FLAMETHROWER)) {
            FlamethrowerComponent component = flamethrower.get(FEComponents.FLAMETHROWER);
            if(canister.has(FEComponents.CANISTER) && component.canAdd()) {
                flamethrower.set(FEComponents.FLAMETHROWER, flamethrower.get(FEComponents.FLAMETHROWER).addLast(canister.copyWithCount(1)));
            }
        }
    }
}
