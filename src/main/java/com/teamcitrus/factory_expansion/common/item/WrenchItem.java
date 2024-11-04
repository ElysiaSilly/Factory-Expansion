package com.teamcitrus.factory_expansion.common.item;

import com.teamcitrus.factory_expansion.common.block.interfaces.IWrenchableBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class WrenchItem extends Item {
    public WrenchItem(Properties properties) {
        super(properties);
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

            successful = block.onWrench(level, pos, state, direction, posSpecific, player);
        }

        return successful ? InteractionResult.SUCCESS : super.useOn(context);
    }
}
