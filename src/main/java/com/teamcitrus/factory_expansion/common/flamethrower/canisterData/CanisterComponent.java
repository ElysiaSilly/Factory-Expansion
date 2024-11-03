package com.teamcitrus.factory_expansion.common.flamethrower.canisterData;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record CanisterComponent(int capacity, int used) {

    // TODO : move this from a record so fields arent final

    public static MapCodec<CanisterComponent> MAP_CODEC = RecordCodecBuilder.mapCodec(builder -> builder.group(
            Codec.INT.fieldOf("capacity").forGetter(CanisterComponent::capacity),
            Codec.INT.fieldOf("used").forGetter(CanisterComponent::used)
    ).apply(builder, CanisterComponent::new));

    public static StreamCodec<ByteBuf, CanisterComponent> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, CanisterComponent::capacity,
            ByteBufCodecs.INT, CanisterComponent::used,
            CanisterComponent::new
    );

    public CanisterComponent setCanister(int capacity, int used) {
        return new CanisterComponent(capacity, used);
    }

    public CanisterComponent decreaseUses() {
        int uses = used - 1;
        return new CanisterComponent(capacity, uses);
    }

    public int getUses() {
        return used;
    }

    public int getCapacity() {
        return capacity;
    }
}
