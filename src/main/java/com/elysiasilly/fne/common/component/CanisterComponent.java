package com.elysiasilly.fne.common.component;

import com.elysiasilly.fne.common.canister.Canister;
import com.elysiasilly.fne.core.FERegistries;
import com.elysiasilly.fne.core.registry.FEComponents;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;

public record CanisterComponent(int capacity, int uses, boolean consume, boolean hide, Canister type) {

    public CanisterComponent(int capacity, boolean consume, boolean hide, Canister type) {
        this(capacity, capacity, consume, hide, type);
    }

    public static final Codec<CanisterComponent> CODEC = RecordCodecBuilder.create(builder -> builder.group(
            Codec.INT.fieldOf("capacity").forGetter(i -> i.capacity),
            Codec.INT.fieldOf("uses").forGetter(i -> i.uses),
            Codec.BOOL.fieldOf("consume").forGetter(i -> i.consume),
            Codec.BOOL.fieldOf("hide").forGetter(i -> i.hide),
            FERegistries.CANISTER_TYPE.byNameCodec().fieldOf("type").forGetter(i -> i.type)
    ).apply(builder, CanisterComponent::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, CanisterComponent> STREAM = StreamCodec.composite(
            ByteBufCodecs.INT, CanisterComponent::capacity,
            ByteBufCodecs.INT, CanisterComponent::uses,
            ByteBufCodecs.BOOL, CanisterComponent::consume,
            ByteBufCodecs.BOOL, CanisterComponent::hide,
            ByteBufCodecs.registry(FERegistries.CANISTER_TYPE.key()), CanisterComponent::type,
            CanisterComponent::new
    );

    public static boolean use(ItemStack canister) {
        CanisterComponent component = canister.get(FEComponents.CANISTER);

        if(component.creative()) {
            return true;
        } else {
            if(component.uses() < 1 && component.consume()) {
                canister.setCount(0);
                return false;
            } else {
                canister.set(FEComponents.CANISTER, component.use());
                return true;
            }
        }
    }

    public boolean creative() {
        return capacity() < 0;
    }

    private CanisterComponent use() {
        return new CanisterComponent(capacity(), uses() - 1, consume(), hide(), type());
    }

    public CanisterComponent copy() {
        return new CanisterComponent(capacity(), uses(), consume(), hide(), type());
    }
}
