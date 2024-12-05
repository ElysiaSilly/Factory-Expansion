package com.teamcitrus.factory_expansion.common.event.misc;

import com.teamcitrus.factory_expansion.core.FERegistries;
import com.teamcitrus.factory_expansion.core.FactoExpa;
import net.minecraft.core.BlockPos;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.UseItemOnBlockEvent;

@EventBusSubscriber(modid = FactoExpa.MODID, bus = EventBusSubscriber.Bus.GAME)
public class DyeStuff {

    @SubscribeEvent
    public static void onUseItemOnBlockEvent(UseItemOnBlockEvent event) {

        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        BlockState state = event.getLevel().getBlockState(pos);

        if(event.getItemStack().getItem() instanceof DyeItem item) {
            if(!level.isClientSide) {

                level.registryAccess().registry(FERegistries.COLOUR_MAPPING).get().stream().forEach(mapping -> {

                    if(mapping.isPresent(state.getBlock()) != null) {

                        Block block = mapping.get(item.getDyeColor()).getBlock();
                        BlockState newState = block.withPropertiesOf(state);

                        if(newState != null) {
                            level.setBlockAndUpdate(pos, newState);
                            event.cancelWithResult(ItemInteractionResult.SUCCESS);
                            level.levelEvent(2001, pos, Block.getId(state));
                            level.levelEvent(2001, pos, Block.getId(level.getBlockState(pos)));
                        }
                    }
                });
            }
        }
    }
}
