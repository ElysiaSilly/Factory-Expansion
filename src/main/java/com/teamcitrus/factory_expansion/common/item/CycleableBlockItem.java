package com.teamcitrus.factory_expansion.common.item;

import net.minecraft.Util;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CycleableBlockItem extends BlockItem {

    private final boolean allowRandom;
    private final List<Block> blocks = new ArrayList<>();
    private int current;
    private final int max;
    //private Level level;
    private int random;
    private final boolean assignToItem;

    public CycleableBlockItem(Properties properties, boolean allowRandom, boolean assignToItem, @Nonnull Block...blocks) {
        super(null, properties); // hoping passing in null will be fine LOL
        this.allowRandom = allowRandom;
        this.blocks.addAll(Arrays.asList(blocks));
        this.max = this.blocks.size();// + 1;
        this.current = allowRandom ? 0 : 1;
        this.assignToItem = assignToItem;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack item = player.getItemInHand(usedHand);

        if(!player.isShiftKeyDown())  return InteractionResultHolder.pass(item);

        if(level.isClientSide) return InteractionResultHolder.success(item);

        int min = allowRandom ? 0 : 1;

        if(current < max) {
            current++;
        } else if (current > min) {
            current = min;
        }

        String string = current == 0 ? "(" + getBlockDescription(current) + ")" : "(" + current + "/" + blocks.size() + ") " + getBlockDescription(current);
        player.displayClientMessage(Component.literal(string), true);

        return InteractionResultHolder.success(item);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if(context.getLevel().isClientSide) return InteractionResult.SUCCESS;

        this.random = context.getLevel().random.nextInt(blocks.size());

        InteractionResult interactionResult = this.place(new BlockPlaceContext(context));
        if (!interactionResult.consumesAction() && context.getItemInHand().has(DataComponents.FOOD)) {
            InteractionResult altInteractionResult = super.use(context.getLevel(), context.getPlayer(), context.getHand()).getResult();
            return altInteractionResult == InteractionResult.CONSUME ? InteractionResult.CONSUME_PARTIAL : altInteractionResult;
        } else {
            return interactionResult;
        }
    }

    @Override
    public Block getBlock() {
        if(current == 0) {
            return this.blocks.get(this.random);
        } else {
            return getBlock(current);
        }
    }

    public Block getBlock(Level level) {
        return this.blocks.get(level.random.nextInt(blocks.size()));
    }

    public Block getBlock(int current) {
        return this.blocks.get(current - 1);
    }

    @Override
    public void registerBlocks(Map<Block, Item> blockToItemMap, Item item) {
        if(assignToItem) {
            for(Block block : this.blocks) {
                blockToItemMap.put(block, item);
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }

    @Override
    public String getDescriptionId() {
        return Component.translatable(this.getOrCreateDescriptionId()).getString() + " (" + getBlockDescription(current) + ")";
    }

    public String getBlockDescription(int index) {
        return index == 0 ?
                Component.translatable("tooltip.factory_expansion.random").getString() :
                Component.translatable(Util.makeDescriptionId("item", BuiltInRegistries.ITEM.getKey(this)) + Util.makeDescriptionId("", BuiltInRegistries.BLOCK.getKey(getBlock(index)))).getString();
    }
}
