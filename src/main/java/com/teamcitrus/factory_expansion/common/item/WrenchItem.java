package com.teamcitrus.factory_expansion.common.item;

import com.teamcitrus.factory_expansion.common.block.interfaces.IWrenchableBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class WrenchItem extends Item {
    public WrenchItem(Properties properties) {
        super(properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {

        if(!(entity instanceof Player player && isSelected)) return;

        HitResult hitResult = Minecraft.getInstance().hitResult;

        if(hitResult == null) return;

        if(!(hitResult instanceof BlockHitResult blockHitResult)) return;

        BlockState state = level.getBlockState(blockHitResult.getBlockPos());

        if(!(state.getBlock() instanceof IWrenchableBlock block)) return;

        block.onWrenchHover(level, blockHitResult.getBlockPos(), state, blockHitResult.getDirection(), hitResult.getLocation(), player);

    }

    @Override
    public InteractionResult useOn(UseOnContext context) {

        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();

        BlockState state = level.getBlockState(pos);

        boolean successful = false;

        if(state.getBlock() instanceof IWrenchableBlock block) {
            Direction direction = context.getClickedFace();
            Vec3 posSpecific = context.getClickLocation();
            Player player = context.getPlayer();

            successful = block.onWrenchUse(level, pos, state, direction, posSpecific, player);
        } else {

            if(state.getOptionalValue(BlockStateProperties.FACING).isPresent()) {
                level.setBlockAndUpdate(pos, state.cycle(BlockStateProperties.FACING));
                successful = true;
            }
            if(state.getOptionalValue(BlockStateProperties.AXIS).isPresent()) {
                level.setBlockAndUpdate(pos, state.cycle(BlockStateProperties.AXIS));
                successful = true;
            }
            if(state.getOptionalValue(BlockStateProperties.HORIZONTAL_FACING).isPresent()) {
                level.setBlockAndUpdate(pos, state.cycle(BlockStateProperties.HORIZONTAL_FACING));
                successful = true;
            }
        }

        return successful ? InteractionResult.SUCCESS : super.useOn(context);
    }
}
