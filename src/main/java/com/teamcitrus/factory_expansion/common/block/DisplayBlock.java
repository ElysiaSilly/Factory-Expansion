package com.teamcitrus.factory_expansion.common.block;

import com.mojang.serialization.MapCodec;
import com.teamcitrus.factory_expansion.common.block.be.DisplayBlockBE;
import com.teamcitrus.factory_expansion.common.block.interfaces.block.IWrenchableBlock;
import com.teamcitrus.factory_expansion.common.data.dyeing.DyeData;
import com.teamcitrus.factory_expansion.core.util.RegistryUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public class DisplayBlock extends BaseEntityBlock implements IWrenchableBlock {

    public static final BooleanProperty RIGHT = BooleanProperty.create("right");
    public static final BooleanProperty LEFT = BooleanProperty.create("left");
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

    private static final VoxelShape[] SHAPE = {
            Block.box(0, 0, 14, 16, 16, 16),
            Block.box(0, 0, 0, 2, 16, 16),
            Block.box(0, 0, 0, 16, 16, 2),
            Block.box(14, 0, 0, 16, 16, 16),
    };

    public DisplayBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(RIGHT, false)
                .setValue(LEFT, false)
                .setValue(FACING, Direction.NORTH)
                .setValue(POWERED, false)
        );
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return null;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new DisplayBlockBE(pos, state);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch(state.getValue(FACING)) {
            case DOWN, UP -> null;
            case NORTH -> SHAPE[0];
            case EAST  -> SHAPE[1];
            case SOUTH -> SHAPE[2];
            case WEST  -> SHAPE[3];
        };
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {

        if(stack.has(DataComponents.CUSTOM_NAME)) {
            String name = stack.get(DataComponents.CUSTOM_NAME).getString();
            BlockPos relative = pos;
            int index = 0;

            while(level.getBlockEntity(relative) instanceof DisplayBlockBE neighbour) {
                if(index >= name.length()) break;
                neighbour.setCharacter(name.charAt(index));

                relative = relative.relative(state.getValue(FACING).getCounterClockWise());
                index++;
            }
            return ItemInteractionResult.SUCCESS;
        }

        DyeData data = RegistryUtils.getDyeData(level, stack.getItem());
        if(data != null) {
            if(data.getColour() != 0) {
                if(level.getBlockEntity(pos) instanceof  DisplayBlockBE be) {
                    be.setColour(data.getColour());
                    return ItemInteractionResult.SUCCESS;
                }
            }
        }

        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    public boolean onWrenchUse(Level level, BlockPos pos, BlockState state, Direction direction, Vec3 posSpecific, Player player) {

        /*
        boolean flag = false;

        ItemStack item = player.getOffhandItem();

        if(item.has(DataComponents.CUSTOM_NAME)) {

            String name = item.get(DataComponents.CUSTOM_NAME).getString();
            BlockPos relative = pos;

            int index = 0;

            while(level.getBlockEntity(relative) instanceof DisplayBlockBE neighbour) {
                flag = true;

                if(index >= name.length()) break;

                neighbour.setCharacter(name.charAt(index));

                relative = relative.relative(state.getValue(FACING).getCounterClockWise());
                index++;
            }
        }
        if(item.getItem() instanceof DyeItem dye) {
            if(level.getBlockEntity(pos) instanceof  DisplayBlockBE be) {
                flag = true;
                be.setColour(dye.getDyeColor().getTextColor());
            }
        }
        if(item.getItem().getDefaultInstance().is(Items.EXPERIENCE_BOTTLE)) {
            if(level.getBlockEntity(pos) instanceof  DisplayBlockBE be) {
                be.setFont("alt");
            }
        }
        if(item.getItem().getDefaultInstance().is(Items.OMINOUS_BOTTLE)) {
            if(level.getBlockEntity(pos) instanceof  DisplayBlockBE be) {
                be.setFont("illageralt");
            }
        }
        if(item.getItem() instanceof DyeItem dye) {
            if(level.getBlockEntity(pos) instanceof  DisplayBlockBE be) {
                flag = true;
                be.setColour(dye.getDyeColor().getTextColor());
            }
        }
        if(player.isShiftKeyDown() && item.isEmpty()) {
            if(level.getBlockEntity(pos) instanceof DisplayBlockBE be) {
                flag = true;
                be.setCharacter(' ');
            }
        }
        if(!player.isShiftKeyDown() && item.isEmpty()) {
            if(level.getBlockEntity(pos) instanceof DisplayBlockBE be && !level.isClientSide) {
                be.seed(level);
            }
        }

        return flag;

         */

        return false;
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) { // todo
        if(context.getClickedFace().getAxis() == Direction.Axis.Y) return null; //this.defaultBlockState().setValue(FACING, context.getHorizontalDirection());
        return this.defaultBlockState().setValue(FACING, context.getClickedFace());
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {

        if(direction == state.getValue(FACING).getCounterClockWise()) {
            if(neighborState.is(this)) {
                if(state.getValue(FACING) != neighborState.getValue(FACING)) return state;
                level.setBlock(pos, state.setValue(LEFT, true), 3);
            } else {
                level.setBlock(pos, state.setValue(LEFT, false), 3);
            }
        }

        if(direction == state.getValue(FACING).getClockWise()) {
            if(neighborState.is(this)) {
                if(state.getValue(FACING) != neighborState.getValue(FACING)) return state;
                level.setBlock(pos, state.setValue(RIGHT, true), 3);
            } else {
                level.setBlock(pos, state.setValue(RIGHT, false), 3);
            }
        }

        if(state.getValue(POWERED) != level.hasNeighborSignal(pos)) {
            if(state.getValue(POWERED)) {
                level.scheduleTick(pos, this, 2);
            } else {
                level.setBlock(pos, state.cycle(POWERED), 2);
            }
        }

        return state;
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if(state.getValue(POWERED) && !level.hasNeighborSignal(pos)) {
            level.setBlock(pos, state.cycle(POWERED), 2);
        }
    }

    @Override
    public void onWrenchHover(Level level, BlockPos pos, BlockState state, Direction direction, Vec3 posSpecific, Player player) {

        /*
        ItemStack item = player.getOffhandItem();

        if(item.has(DataComponents.CUSTOM_NAME)) {

            String name = item.get(DataComponents.CUSTOM_NAME).getString();
            BlockPos relative = pos;

            int index = 0;

            while(level.getBlockEntity(relative) instanceof DisplayBlockBE neighbour) {
                relative = relative.relative(state.getValue(FACING).getCounterClockWise());
                index++;
            }

            if(index >= name.length()) {
                player.displayClientMessage(Component.literal("change display to '" + name + "'").withStyle(ChatFormatting.GRAY), true);
            } else {
                player.displayClientMessage(Component.literal("missing " + (name.length() - index) + " display(s) to fit '" + name + "'").withStyle(ChatFormatting.GRAY), true);
            }
        }
        if(player.isShiftKeyDown() && item.isEmpty()) {
            if(level.getBlockEntity(pos) instanceof DisplayBlockBE be) {
                player.displayClientMessage(Component.literal("clear display").withStyle(ChatFormatting.GRAY), true);
            }
        }
        if(item.getItem() instanceof DyeItem dye) {
            if(level.getBlockEntity(pos) instanceof DisplayBlockBE be) {
                player.displayClientMessage(Component.literal("change colour to " + dye.getDyeColor().name()).withStyle(ChatFormatting.GRAY), true);
            }
        }
        if(!player.isShiftKeyDown() && item.isEmpty()) {
            if(level.getBlockEntity(pos) instanceof DisplayBlockBE be) {
                player.displayClientMessage(Component.literal(String.valueOf(be.getSeed())).withStyle(ChatFormatting.GRAY), true);
            }
        }

         */
    }

    @Override
    public boolean overrideDefaultWrenchBehaviour() {
        return true;
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(RIGHT, LEFT, FACING, POWERED);
    }
}
