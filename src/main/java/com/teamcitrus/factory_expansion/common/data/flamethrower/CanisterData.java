package com.teamcitrus.factory_expansion.common.data.flamethrower;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.teamcitrus.factory_expansion.common.canister.CanisterType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;

public class CanisterData {

    public static final Codec<CanisterData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            BuiltInRegistries.ITEM.byNameCodec().fieldOf("item").forGetter(i -> i.item),
            CanisterType.CODEC.fieldOf("type").forGetter(i -> i.canisterType),
            Codec.INT.fieldOf("capacity").forGetter(i -> i.capacity),
            Codec.BOOL.fieldOf("canBreak").forGetter(i -> i.canBreak)
    ).apply(instance, CanisterData::new));

    private final Item item;
    private final CanisterType canisterType;
    private final int capacity;
    private final boolean canBreak;

    private CanisterData(Item item, CanisterType canisterType, int capacity, boolean canBreak) {
        this.item = item;
        this.capacity =  capacity;
        this.canisterType = canisterType;
        this.canBreak = canBreak;
    }

    public CanisterType getCanisterType() {
        return this.canisterType;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public boolean breakWhenEmpty() {
        return this.canBreak;
    }

    public Item getItem() {
        return this.item;
    }

    public CanisterComponent createComponent() {
        return new CanisterComponent(this.capacity, this.canBreak, this.canisterType);
    }
}
