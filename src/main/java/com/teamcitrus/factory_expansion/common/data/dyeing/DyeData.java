package com.teamcitrus.factory_expansion.common.data.dyeing;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.teamcitrus.factory_expansion.core.keys.FEResourceKeys;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.resources.RegistryFileCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.Optional;

public class DyeData {

    public static final Codec<DyeData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(i -> i.ingredient),
            Codec.INT.optionalFieldOf("hexCode").forGetter(i -> i.colour)
    ).apply(instance, DyeData::new));

    public static final RegistryFileCodec<DyeData> HOLDER_CODEC = RegistryFileCodec.create(FEResourceKeys.DYE, CODEC);

    final Ingredient ingredient;
    final Optional<Integer> colour;

    public DyeData(Ingredient ingredient, Optional<Integer> colour) {
        this.ingredient = ingredient;
        this.colour = colour;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public boolean checkIngredient(Item item) {
        return this.ingredient.test(item.getDefaultInstance());
    }

    public int getColour() {
        return this.colour.orElse(0);
    }
}
