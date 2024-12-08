package com.teamcitrus.factory_expansion.common.item;

import com.teamcitrus.factory_expansion.common.data.wrench.WrenchComponent;
import com.teamcitrus.factory_expansion.core.FEConfig;
import com.teamcitrus.factory_expansion.core.keys.FETags;
import com.teamcitrus.factory_expansion.core.registry.FEComponents;
import com.teamcitrus.factory_expansion.core.util.RegistryUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
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
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class WrenchItem extends Item {
    public WrenchItem() {
        super(new Item.Properties().stacksTo(1).component(FEComponents.WRENCH, WrenchComponent.EMPTY));
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        // todo : figure out what i wanna do with this
    }

    @Override
    public InteractionResult onItemUseFirst(ItemStack stack, UseOnContext context) {

        Player player = context.getPlayer();
        if(player == null) return InteractionResult.PASS;

        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = level.getBlockState(pos);
        InteractionResult result = null;
        WrenchComponent component = stack.get(FEComponents.WRENCH);

        boolean flag = false;

        component.cycleAppropriateWrench(state);

        if(component.getIndexWrench() != null) {

            result = component.getIndexWrench().getItem().useOn(context);
            if(result == InteractionResult.PASS) result = component.getIndexWrench().getItem().onItemUseFirst(component.getIndexWrench(), context);
            flag = result == InteractionResult.SUCCESS;

        } else if (!player.isShiftKeyDown() &&!state.is(FETags.Blocks.WRENCH_CONFIGURE_BLACKLIST)) {

            flag =  cycleState(BlockStateProperties.FACING, level, state, pos) ||
                    cycleState(BlockStateProperties.HORIZONTAL_FACING, level, state, pos) ||
                    cycleState(BlockStateProperties.AXIS, level, state, pos) ||
                    cycleState(BlockStateProperties.ROTATION_16, level, state, pos);
        }

        if(flag && FEConfig.BLOCK_WRENCH_PARTICLES.get()) level.levelEvent(2001, pos, Block.getId(level.getBlockState(pos)));

        return result != null ? result : flag ? InteractionResult.SUCCESS : InteractionResult.FAIL;
    }

    public static boolean cycleState(Property property, Level level, BlockState state, BlockPos pos) {
        if(!state.getOptionalValue(property).isPresent()) return false;
        level.setBlockAndUpdate(pos, check(state, level, pos, property));
        return true;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack item = player.getItemInHand(usedHand);

        if(!player.isShiftKeyDown()) return InteractionResultHolder.pass(item);

        if(level.isClientSide) return InteractionResultHolder.success(item);

        if(Minecraft.getInstance().hitResult.getType() != HitResult.Type.MISS) return InteractionResultHolder.pass(item);

        WrenchComponent component = item.get(FEComponents.WRENCH);

        component.cycleIndex();

        ItemStack wrench = component.getIndexWrench();
        if(wrench != null) {
            player.displayClientMessage(wrench.getDisplayName(), true);
        } else {
            player.displayClientMessage(getName(item), true);
        }

        return InteractionResultHolder.success(item);
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
            if(state.is(FETags.Blocks.WRENCH_PICKUP_WHITELIST)) {
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
        return state.is(FETags.Blocks.WRENCH_PICKUP_WHITELIST) ? Float.MAX_VALUE : Float.MIN_VALUE;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        WrenchComponent component = stack.get(FEComponents.WRENCH);

        int index = 0;
        for(ItemStack wrench : component.getWrenches()) {
            ChatFormatting format = index == component.getIndex() ? ChatFormatting.WHITE : ChatFormatting.DARK_GRAY;
            tooltipComponents.add(Component.literal(wrench.getHoverName().getString()).withStyle(format));
            index++;
        }
    }

    @Override
    public boolean overrideOtherStackedOnMe(ItemStack stack, ItemStack other, Slot slot, ClickAction action, Player player, SlotAccess access) {
        return super.overrideOtherStackedOnMe(stack, other, slot, action, player, access);
    }

    @Override
    public boolean overrideStackedOnOther(ItemStack stack, Slot slot, ClickAction action, Player player) {

        WrenchComponent component = stack.get(FEComponents.WRENCH);

        if(action == ClickAction.SECONDARY) {
            if(slot.hasItem()) {
                return component.insertWrench(slot.getItem());
            } else {
                ItemStack extracted = component.extractLastWrench();
                if(extracted != null) slot.safeInsert(extracted);
                return true;
            }
        }

        return false;
    }
}
