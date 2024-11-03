package com.teamcitrus.factory_expansion.common.block;

import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.jetbrains.annotations.Nullable;

public class VariantBlock extends Block
{
    public static final IntegerProperty VARIANT = IntegerProperty.create("variant", 0, 7);

    // max allowed variants, up to 8 (0..7)
    private final int MAXVARIANTS;

    // stored random number to keep it synced between client and server
    private static int storedVariant;

    public VariantBlock(Properties pProperties, int maxVariants)
    {
        super(pProperties);
        if(maxVariants > 7)
            throw new IllegalArgumentException("Using too many variants for " + this.asBlock());
        MAXVARIANTS = maxVariants;
        storedVariant = -1;
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(VARIANT,0));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext)
    {
        int toSetVariant = storedVariant;

        // if no random number was generated
        if(storedVariant < 0)
        {
            var level = pContext.getLevel();
            var random = level.random;
            random.setSeed(level.getGameTime());

            // generate and store random number
            storedVariant = toSetVariant = random.nextInt(MAXVARIANTS);
        }
        else
        {
            // "forget" the stored random number
            storedVariant = -1;
        }

        return super.getStateForPlacement(pContext)
                .setValue(VARIANT, toSetVariant);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        builder.add(VARIANT);
    }
}
