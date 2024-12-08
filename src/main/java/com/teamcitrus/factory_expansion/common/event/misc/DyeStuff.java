package com.teamcitrus.factory_expansion.common.event.misc;

import com.teamcitrus.factory_expansion.common.data.dyeing.DyeData;
import com.teamcitrus.factory_expansion.common.data.dyeing.DyeingData;
import com.teamcitrus.factory_expansion.core.FEConfig;
import com.teamcitrus.factory_expansion.core.FactoExpa;
import com.teamcitrus.factory_expansion.core.keys.FEResourceKeys;
import com.teamcitrus.factory_expansion.core.util.RegistryUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.UseItemOnBlockEvent;

import java.util.Arrays;

@EventBusSubscriber(modid = FactoExpa.MODID, bus = EventBusSubscriber.Bus.GAME)
public class DyeStuff {

    @SubscribeEvent
    public static void onUseItemOnBlockEvent(UseItemOnBlockEvent event) {

        if(!FEConfig.BLOCK_APPLY_DYE.get()) return;

        Level level = event.getLevel();

        BlockPos pos = event.getPos();
        BlockState state = event.getLevel().getBlockState(pos);
        DyeData dyeData = RegistryUtils.getDyeData(level, event.getItemStack().getItem());

        if(dyeData != null) {
            DyeingData dyeingData = RegistryUtils.getDyeingData(level, state.getBlock());

            if(dyeingData != null) {
                Block block = dyeingData.getBlock(event.getItemStack().getItem());
                if(block != null) {
                    BlockState newState = block.withPropertiesOf(state);
                    if(block == state.getBlock()) return;
                    level.setBlockAndUpdate(pos, newState);
                    event.cancelWithResult(ItemInteractionResult.SUCCESS);
                    if(FEConfig.BLOCK_DYE_PARTICLES.get()) {
                        level.levelEvent(2001, pos, Block.getId(state));
                        level.levelEvent(2001, pos, Block.getId(level.getBlockState(pos)));
                    }
                    if(FEConfig.BLOCK_CONSUME_DYE.get() && !event.getPlayer().hasInfiniteMaterials()) {
                        event.getItemStack().shrink(1);
                    }
                }
            }
        }
    }
}
