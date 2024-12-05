package com.teamcitrus.factory_expansion.common.data.colourapplication;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Hashtable;
import java.util.Map;
import java.util.Optional;

public class ColourMappingData {

    // todo : yeah yeah this shit is horrid this will get a refactor

    public static final Codec<ColourMappingData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            BlockState.CODEC.optionalFieldOf("white").forGetter(i -> i.WHITE),
            BlockState.CODEC.optionalFieldOf("orange").forGetter(i -> i.ORANGE),
            BlockState.CODEC.optionalFieldOf("magenta").forGetter(i -> i.MAGENTA),
            BlockState.CODEC.optionalFieldOf("light_blue").forGetter(i -> i.LIGHT_BLUE),
            BlockState.CODEC.optionalFieldOf("yellow").forGetter(i -> i.YELLOW),
            BlockState.CODEC.optionalFieldOf("lime").forGetter(i -> i.LIME),
            BlockState.CODEC.optionalFieldOf("pink").forGetter(i -> i.PINK),
            BlockState.CODEC.optionalFieldOf("gray").forGetter(i -> i.GRAY),
            BlockState.CODEC.optionalFieldOf("light_gray").forGetter(i -> i.LIGHT_GRAY),
            BlockState.CODEC.optionalFieldOf("cyan").forGetter(i -> i.CYAN),
            BlockState.CODEC.optionalFieldOf("purple").forGetter(i -> i.PURPLE),
            BlockState.CODEC.optionalFieldOf("blue").forGetter(i -> i.BLUE),
            BlockState.CODEC.optionalFieldOf("brown").forGetter(i -> i.BROWN),
            BlockState.CODEC.optionalFieldOf("green").forGetter(i -> i.GREEN),
            BlockState.CODEC.optionalFieldOf("red").forGetter(i -> i.RED),
            BlockState.CODEC.optionalFieldOf("black").forGetter(i -> i.BLACK)
    ).apply(instance, ColourMappingData::new));
    
    public final Optional<BlockState> WHITE;
    public final Optional<BlockState> ORANGE;
    public final Optional<BlockState> MAGENTA;
    public final Optional<BlockState> LIGHT_BLUE;
    public final Optional<BlockState> YELLOW;
    public final Optional<BlockState> LIME;
    public final Optional<BlockState> PINK;
    public final Optional<BlockState> GRAY;
    public final Optional<BlockState> LIGHT_GRAY;
    public final Optional<BlockState> CYAN;
    public final Optional<BlockState> PURPLE;
    public final Optional<BlockState> BLUE;
    public final Optional<BlockState> BROWN;
    public final Optional<BlockState> GREEN;
    public final Optional<BlockState> RED;
    public final Optional<BlockState> BLACK;

    public final Map<BlockState, DyeColor> BLOCKS = new Hashtable<>();

    public ColourMappingData(Optional<BlockState> white, Optional<BlockState> orange, Optional<BlockState> magenta, Optional<BlockState> lightBlue, Optional<BlockState> yellow, Optional<BlockState> lime, Optional<BlockState> pink, Optional<BlockState> gray, Optional<BlockState> lightGray, Optional<BlockState> cyan, Optional<BlockState> purple, Optional<BlockState> blue, Optional<BlockState> brown, Optional<BlockState> green, Optional<BlockState> red, Optional<BlockState> black) {
        this.WHITE = white;
        white.ifPresent(state -> this.BLOCKS.put(state, DyeColor.WHITE));

        this.ORANGE = orange;
        orange.ifPresent(state -> this.BLOCKS.put(state, DyeColor.ORANGE));

        this.MAGENTA = magenta;
        magenta.ifPresent(state -> this.BLOCKS.put(state, DyeColor.MAGENTA));

        this.LIGHT_BLUE = lightBlue;
        lightBlue.ifPresent(state -> this.BLOCKS.put(state, DyeColor.LIGHT_BLUE));

        this.YELLOW = yellow;
        yellow.ifPresent(state -> this.BLOCKS.put(state, DyeColor.YELLOW));

        this.LIME = lime;
        lime.ifPresent(state -> this.BLOCKS.put(state, DyeColor.LIME));

        this.PINK = pink;
        pink.ifPresent(state -> this.BLOCKS.put(state, DyeColor.PINK));

        this.GRAY = gray;
        gray.ifPresent(state -> this.BLOCKS.put(state, DyeColor.GRAY));

        this.LIGHT_GRAY = lightGray;
        lightGray.ifPresent(state -> this.BLOCKS.put(state, DyeColor.LIGHT_GRAY));

        this.CYAN = cyan;
        cyan.ifPresent(state -> this.BLOCKS.put(state, DyeColor.CYAN));

        this.PURPLE = purple;
        purple.ifPresent(state -> this.BLOCKS.put(state, DyeColor.PURPLE));

        this.BLUE = blue;
        blue.ifPresent(state -> this.BLOCKS.put(state, DyeColor.BLUE));

        this.BROWN = brown;
        brown.ifPresent(state -> this.BLOCKS.put(state, DyeColor.BROWN));

        this.GREEN = green;
        green.ifPresent(state -> this.BLOCKS.put(state, DyeColor.GREEN));

        this.RED = red;
        red.ifPresent(state -> this.BLOCKS.put(state, DyeColor.RED));

        this.BLACK = black;
        black.ifPresent(state -> this.BLOCKS.put(state, DyeColor.BLACK));
    }

    public DyeColor isPresent(BlockState state) {
        return this.BLOCKS.get(state);
    }

    public Block isPresent(Block block) {

        for(Map.Entry<BlockState, DyeColor> entry : this.BLOCKS.entrySet()) {

            if(entry.getKey().getBlock() == block) return entry.getKey().getBlock();
        }

        return null;
    }

    public BlockState getNext() {
        return null; //.this.BLOCKS.
    }

    public BlockState get(DyeColor dye) {
        for(Map.Entry<BlockState, DyeColor> entry : this.BLOCKS.entrySet()) {

            if(dye == entry.getValue()) return entry.getKey();
        }
        return null;
    }
}
