package com.teamcitrus.factory_expansion.common.block.fauxmultiblock;

import com.teamcitrus.factory_expansion.common.block.interfaces.BlockPosRepresentable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.jetbrains.annotations.Nullable;

public class FauxMultiBlock<T extends Enum<T> & BlockPosRepresentable & StringRepresentable> extends Block {

    public final MultiBlock<T> MULTI;

    public static final DirectionProperty FACING = BlockStateProperties.FACING;

    //public static final EnumProperty<TestMulti> BLOCKS = EnumProperty.create("block", TestMulti.class);
    public static final EnumProperty BLOCKS = getProperty();

    public static EnumProperty getProperty() { // cooked rn hopefully i remember where i wanted to go with this
        return EnumProperty.create("block", TestMulti.class);
    }

    public FauxMultiBlock(Properties properties, Class<T> c) {
        super(properties);
        this.MULTI = new MultiBlock<>(c, this);
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(BLOCKS, TestMulti.MIDDLE)
                .setValue(FACING, Direction.UP)
        );
    }

    public EnumProperty getBlocks() {
        return BLOCKS;
    }

    public DirectionProperty getFacing() {
        return FACING;
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return MULTI.isValidPlacement(context.getClickedFace(), context.getLevel(), context.getClickedPos()) ? this.defaultBlockState().setValue(FACING, context.getClickedFace()) : null;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        MULTI.place(state.getValue(FACING), level, pos);
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        MULTI.destroy(state, state.getValue(FACING), level, pos);
        super.onRemove(state, level, pos, newState, movedByPiston);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BLOCKS, FACING);
    }
}
