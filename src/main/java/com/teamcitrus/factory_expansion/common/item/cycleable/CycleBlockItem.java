package com.teamcitrus.factory_expansion.common.item.cycleable;

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
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CycleBlockItem extends BlockItem {

    private Mode mode = Mode.RANDOM_AND_CYCLE;
    private boolean assignToItem = false;


    private final List<OptPropertyBlock> blocks = new ArrayList<>();
    private int index = 0;
    private final int max;

    private int random;

    public CycleBlockItem(Properties properties, @Nonnull OptPropertyBlock...blocks) {
        super(null, properties); // hoping passing in null will be fine LOL

        this.blocks.addAll(Arrays.asList(blocks));
        this.max = this.blocks.size();// + 1;
    }

    public CycleBlockItem mode(Mode mode) {
        this.mode = mode;
        this.index = this.mode.random ? 0 : 1;
        return this;
    }

    public CycleBlockItem assignToItem() {
        this.assignToItem = true;
        return this;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack item = player.getItemInHand(usedHand);

        if(!player.isShiftKeyDown()) return InteractionResultHolder.pass(item);

        if(level.isClientSide) return InteractionResultHolder.success(item);

        boolean flag = cycleBlock();

        if(!flag) return InteractionResultHolder.pass(item);

        String string = index == 0 ? getBlockDescription(index) : "(" + index + "/" + blocks.size() + ") " + getBlockDescription(index);
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
    protected @Nullable BlockState getPlacementState(BlockPlaceContext context) {
        BlockState blockstate = getOptStateBlock().getState(context); //this.getBlock().getStateForPlacement(context);
        return blockstate != null && this.canPlace(context, blockstate) ? blockstate : null;
    }

    @Override
    public Block getBlock() {
        return getOptStateBlock().getBlock();
    }

    public OptPropertyBlock getOptStateBlock() {
        if(index == 0) {
            return this.blocks.get(this.random);
        } else {
            return getOptStateBlock(index);
        }
    }

    public Block getRandomBlock(Level level) {
        return this.blocks.get(level.random.nextInt(blocks.size())).getBlock();
    }

    public boolean cycleBlock() {
        if(this.mode == Mode.RANDOM_ONLY) return false;

        int min = this.mode.random ? 0 : 1;

        if(index < max) {
            index++;
        } else if (index > min) {
            index = min;
        }

        return true;
    }

    public OptPropertyBlock getOptStateBlock(int index) {
        return this.blocks.get(index - 1);
    }

    @Override
    public void registerBlocks(Map<Block, Item> blockToItemMap, Item item) {
        if(assignToItem) {
            for(OptPropertyBlock block : this.blocks) {
                blockToItemMap.put(block.getBlock(), item);
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }

    @Override
    public String getDescriptionId() {
        return Component.translatable(this.getOrCreateDescriptionId()).getString() + getBlockDescription(index);
    }

    public String getBlockDescription(int index) {
        if(this.mode == Mode.RANDOM_ONLY) {
            return "";
        } else {
            String string = index == 0 ?
                    Component.translatable("tooltip.factory_expansion.random").getString() :
                    Component.translatable(Util.makeDescriptionId("item", BuiltInRegistries.ITEM.getKey(this)) + "." + index).getString();
            return " " + "(" + string + ")";
        }
    }

    public enum Mode {

        RANDOM_ONLY(true, false),
        CYCLE_ONLY(false, true),
        RANDOM_AND_CYCLE(true, true);

        public final boolean random;
        public final boolean selection;

        Mode(boolean random, boolean selection) {
            this.random = random;
            this.selection = selection;
        }
    }
}
