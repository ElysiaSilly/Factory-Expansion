package com.teamcitrus.factory_expansion.common.item;

import net.minecraft.core.component.DataComponents;
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

    public CycleableBlockItem(Properties properties, boolean allowRandom, Block...blocks) {
        super(null, properties); // hoping passing in null will be fine LOL
        this.allowRandom = allowRandom;
        this.blocks.addAll(Arrays.asList(blocks));
        this.max = this.blocks.size();// + 1;
        this.current = allowRandom ? 0 : 1;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack item = player.getItemInHand(usedHand);

        if(level.isClientSide) return InteractionResultHolder.success(item);

        if(player.isShiftKeyDown()) {
            int min = allowRandom ? 0 : 1;

            if(current < max) {
                current++;
            } else if (current > min) {
                current = min;
            }

            String string = current == 0 ? "(Random)" : "(" + current + "/" + blocks.size() + ") " + getBlock(current).getName().getString();
            player.displayClientMessage(Component.literal(string), true);
        }

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

    public Block getBlock(int current) {
        return this.blocks.get(current - 1);
    }

    @Override
    public void registerBlocks(Map<Block, Item> blockToItemMap, Item item) {
        //super.registerBlocks(blockToItemMap, item);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }

    @Override
    public String getDescriptionId() {
        String string = current == 0 ? "Random" : getBlock(current).getName().getString();
        return Component.translatable(this.getOrCreateDescriptionId()).getString() + " (" + string + ")";
    }
}
