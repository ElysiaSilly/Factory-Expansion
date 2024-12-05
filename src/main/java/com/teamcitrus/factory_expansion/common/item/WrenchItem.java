package com.teamcitrus.factory_expansion.common.item;

import com.teamcitrus.factory_expansion.core.keys.FEBlockTags;
import com.teamcitrus.factory_expansion.core.util.RegistryUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class WrenchItem extends Item {
    public WrenchItem(Properties properties) {
        super(properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {

        // todo : figure out what i wanna do with this

        /*
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

         */
    }

    @Override
    public InteractionResult onItemUseFirst(ItemStack stack, UseOnContext context) {
        Level level = context.getLevel();

        BlockPos pos = context.getClickedPos();

        BlockState state = level.getBlockState(pos);

        boolean flag = false;

        Player player = context.getPlayer();

        if(player == null) return InteractionResult.PASS;

        if(!player.isShiftKeyDown() &&!state.is(FEBlockTags.WRENCH_CONFIGURE_BLACKLIST)) {
            if(state.getOptionalValue(BlockStateProperties.FACING).isPresent()) {
                level.setBlockAndUpdate(pos, check(state, level, pos, BlockStateProperties.FACING));
                flag = true;
            }
            if(state.getOptionalValue(BlockStateProperties.AXIS).isPresent()) {
                level.setBlockAndUpdate(pos, check(state, level, pos, BlockStateProperties.AXIS));
                flag = true;
            }
            if(state.getOptionalValue(BlockStateProperties.HORIZONTAL_FACING).isPresent()) {
                level.setBlockAndUpdate(pos, check(state, level, pos, BlockStateProperties.HORIZONTAL_FACING));
                flag = true;
            }
            if(state.getOptionalValue(BlockStateProperties.ROTATION_16).isPresent()) {
                level.setBlockAndUpdate(pos, check(state, level, pos, BlockStateProperties.ROTATION_16));
                flag = true;
            }
        }

        if(flag) level.levelEvent(2001, pos, Block.getId(level.getBlockState(pos)));

        return flag ? InteractionResult.SUCCESS : InteractionResult.FAIL;
    }

    public static BlockState check(BlockState state, Level level, BlockPos pos, Property property) {
        int throttle = 0;

        state = state.cycle(property);

        while(!state.canSurvive(level, pos)) {
            state = state.cycle(property);
            throttle++;
            if(state.canSurvive(level, pos) || throttle > 16) break;
        }

        return state;
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity miningEntity) {
        if(miningEntity instanceof Player player) {
            if(state.is(FEBlockTags.WRENCH_PICKUP_WHITELIST)) {
                level.destroyBlock(pos, false);
                if(level instanceof ServerLevel serverLevel) {
                    BlockEntity blockentity = serverLevel.getBlockEntity(pos);

                    ItemStack temp = stack.copy();
                    temp.enchant(RegistryUtils.getEnchantment(level, Enchantments.SILK_TOUCH), 1);

                    LootParams.Builder lootparams$builder = new LootParams.Builder(serverLevel)
                            .withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pos))
                            .withParameter(LootContextParams.BLOCK_STATE, state)
                            .withOptionalParameter(LootContextParams.BLOCK_ENTITY, blockentity)
                            .withOptionalParameter(LootContextParams.THIS_ENTITY, player)
                            .withParameter(LootContextParams.TOOL, temp);

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
