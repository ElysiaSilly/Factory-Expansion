package com.teamcitrus.factory_expansion.common.data.dyeing;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.*;

public class DyeingData {

    public static final Codec<DyeingData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.compoundList(DyeData.HOLDER_CODEC, BuiltInRegistries.BLOCK.byNameCodec()).fieldOf("map").forGetter(i -> i.MAP)
    ).apply(instance, DyeingData::new));

    final List<Pair<Holder<DyeData>, Block>> MAP = new ArrayList<>();

    public DyeingData(List<Pair<Holder<DyeData>, Block>> map) {
        this.MAP.addAll(map);
    }

    public boolean isPresent(Block block) {
        for(Pair<Holder<DyeData>, Block> entry : this.MAP) {
            if(entry.getSecond().equals(block)) return true;
        }
        return false;
    }

    public Block getBlock(Item item) {
        for(Pair<Holder<DyeData>, Block> entry : this.MAP) {
            if(entry.getFirst().value().checkIngredient(item)) return entry.getSecond();
        }
        return null;
    }
}
