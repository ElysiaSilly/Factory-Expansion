package com.teamcitrus.factory_expansion.common.item;

import com.teamcitrus.factory_expansion.common.block.interfaces.block.IWrenchableBlock;
import com.teamcitrus.factory_expansion.core.keys.FEBlockTags;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.List;

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

        if(level.isClientSide) return;

        BlockState state = level.getBlockState(blockHitResult.getBlockPos());

        boolean override = false;
        boolean isWrenchableBlock = false;

        if(state.getBlock() instanceof IWrenchableBlock block) {
            override = block.overrideDefaultWrenchBehaviour();
            isWrenchableBlock = true;

            if(override || player.isShiftKeyDown()) {
                block.onWrenchHover(level, blockHitResult.getBlockPos(), state, blockHitResult.getDirection(), hitResult.getLocation(), player);
            }
        }


        if(!override && (!isWrenchableBlock || !player.isShiftKeyDown())) {

            if(state.getOptionalValue(BlockStateProperties.FACING).isPresent()) {
                player.displayClientMessage(Component.literal("cycling through orientation: " + state.getValue(BlockStateProperties.FACING).getSerializedName() + " -> " + state.cycle(BlockStateProperties.FACING).getValue(BlockStateProperties.FACING).getSerializedName()).withStyle(ChatFormatting.GRAY), true);
            }
            if(state.getOptionalValue(BlockStateProperties.AXIS).isPresent()) {
                player.displayClientMessage(Component.literal("cycling through orientation: " + state.getValue(BlockStateProperties.AXIS).getSerializedName() + " -> " + state.cycle(BlockStateProperties.AXIS).getValue(BlockStateProperties.AXIS).getSerializedName()).withStyle(ChatFormatting.GRAY), true);
            }
            if(state.getOptionalValue(BlockStateProperties.HORIZONTAL_FACING).isPresent()) {
                player.displayClientMessage(Component.literal("cycling through orientation: " + state.getValue(BlockStateProperties.HORIZONTAL_FACING).getSerializedName() + " -> " + state.cycle(BlockStateProperties.HORIZONTAL_FACING).getValue(BlockStateProperties.HORIZONTAL_FACING).getSerializedName()).withStyle(ChatFormatting.GRAY), true);
            }
        }
    }

    @Override
    public InteractionResult onItemUseFirst(ItemStack stack, UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();

        BlockState state = level.getBlockState(pos);

        boolean successful = false;

        Player player = context.getPlayer();

        if(player == null) return InteractionResult.SUCCESS;

        if(!player.isShiftKeyDown() &&!state.is(FEBlockTags.WRENCH_CONFIGURE_BLACKLIST)) {
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

        if(successful) level.levelEvent(2001, pos, Block.getId(state));

        return successful ? InteractionResult.SUCCESS : InteractionResult.FAIL;
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity miningEntity) {
        if(miningEntity instanceof Player player) {
            if(state.is(FEBlockTags.WRENCH_PICKUP_WHITELIST)) {
                level.destroyBlock(pos, false);
                if(level instanceof ServerLevel serverLevel) {
                    BlockEntity blockentity = serverLevel.getBlockEntity(pos);

                    LootParams.Builder lootparams$builder = new LootParams.Builder(serverLevel)
                            .withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pos))
                            .withParameter(LootContextParams.BLOCK_STATE, state)
                            .withOptionalParameter(LootContextParams.BLOCK_ENTITY, blockentity)
                            .withOptionalParameter(LootContextParams.THIS_ENTITY, player)
                            .withParameter(LootContextParams.TOOL, stack);

                    List<ItemStack> items = state.getDrops(lootparams$builder);
                    for(ItemStack item : items) {
                        if(!player.getInventory().add(item)) player.drop(item, false);
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        return state.is(FEBlockTags.WRENCH_PICKUP_WHITELIST) ? Float.MAX_VALUE : super.getDestroySpeed(stack, state);
    }
}
