package com.teamcitrus.factory_expansion.common.data.flamethrower;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.teamcitrus.factory_expansion.common.canister.CanisterType;
import com.teamcitrus.factory_expansion.core.FERegistries;

public class CanisterComponent {

    public static MapCodec<CanisterComponent> CODEC = RecordCodecBuilder.mapCodec(builder -> builder.group(
            Codec.INT.fieldOf("capacity").forGetter(i -> i.capacity),
            Codec.INT.fieldOf("usesLeft").forGetter(i -> i.usesLeft),
            Codec.BOOL.fieldOf("creative").forGetter(i -> i.canBreak),
            FERegistries.CANISTER_TYPE.byNameCodec().fieldOf("type").forGetter(i -> i.type)
    ).apply(builder, CanisterComponent::new));

    final int capacity;
    int usesLeft;
    final boolean canBreak;
    final CanisterType type;

    private CanisterComponent(int capacity, int usesLeft, boolean canBreak, CanisterType type) {
        this.capacity = capacity;
        this.usesLeft = usesLeft;
        this.canBreak = canBreak;
        this.type = type;
    }

    public CanisterComponent(int capacity, boolean canBreak, CanisterType type) {
        this.capacity = capacity;
        this.usesLeft = capacity;
        this.canBreak = canBreak;
        this.type = type;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public int getCapacity() {
        return this.capacity;
    }

    public int getUsesLeft() {
        return this.usesLeft;
    }

    public boolean isCreative() {
        return this.capacity < 0;
    }

    public boolean canBreak() {
        return this.canBreak;
    }

    public CanisterType getType() {
        return type;
    }

    /// boolean used for breaking
    public boolean decreaseUses() { // todo : fix
        if(isCreative()) return false;
        if(this.usesLeft <= 0 || this.canBreak) return true;
        this.usesLeft--;
        return this.usesLeft <= 0;
    }
}
