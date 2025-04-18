package com.elysiasilly.fne.common.datamap;

import com.elysiasilly.fne.common.canister.Canister;
import com.elysiasilly.fne.common.component.CanisterComponent;
import com.elysiasilly.fne.core.FERegistries;
import com.elysiasilly.fne.FactoExpa;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.datamaps.DataMapType;

import java.util.Optional;

public record CanisterData(Optional<Integer> capacity, Optional<Boolean> consume, Optional<Boolean> hide, Canister type) {

    public static final Codec<CanisterData> CODEC = RecordCodecBuilder.create(builder -> builder.group(
            Codec.optionalField("capacity", Codec.intRange(1, Integer.MAX_VALUE), false).forGetter(i -> i.capacity),
            Codec.optionalField("consume", Codec.BOOL, false).forGetter(i -> i.consume),
            Codec.optionalField("hide", Codec.BOOL, false).forGetter(i -> i.hide),
            FERegistries.CANISTER_TYPE.byNameCodec().fieldOf("type").forGetter(i -> i.type)
    ).apply(builder, CanisterData::new));

    public static final DataMapType<Item, CanisterData> DATAMAP = DataMapType.builder(FactoExpa.location("canister"), Registries.ITEM, CODEC).synced(CODEC, false).build();

    public CanisterComponent create() {
        return new CanisterComponent(capacity().orElse(-1), consume().orElse(false), hide().orElse(false), type());
    }
}
