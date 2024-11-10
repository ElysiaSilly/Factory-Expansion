package com.teamcitrus.factory_expansion.common.block.be;

import com.teamcitrus.factory_expansion.core.registry.FEBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class DisplayBlockBE extends BlockEntity {

    private char character = ' ';
    private int colour = 9127187;

    public DisplayBlockBE(BlockPos pos, BlockState blockState) {
        super(FEBlockEntities.DISPLAY_BE.get(), pos, blockState);
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    public char getCharacter() {
        return this.character;
    }

    public void setColour(int colour) {
        this.colour = colour;
    }

    public int getColour() {
        return this.colour;
    }
}
