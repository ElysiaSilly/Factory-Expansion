package com.teamcitrus.factory_expansion.core.util;

import net.minecraft.world.level.block.state.BlockState;

public class  BlockUtils<T extends Comparable<T>, V extends T> {

    public BlockState copyPossibleStates(BlockState origin, BlockState result) {

        /*
        origin.getBlock().withPropertiesOf()

        //Property<T> test = origin.

        BlockState newState = result;


        origin.getValue()

        Map<Property<?>, Comparable<?>> properties = origin.getValues();

        for(Map.Entry<Property<?>, Comparable<?>> property : properties.entrySet()) {

            if(newState.getOptionalValue(property.getKey()).isPresent()) {

                newState = newState.setValue(property.getKey(), property.getKey())
            }

        }

        return newState;

         */
        return null;
    }
}
