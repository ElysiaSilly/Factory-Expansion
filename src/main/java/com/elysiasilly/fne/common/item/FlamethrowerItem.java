package com.elysiasilly.fne.common.item;

import com.elysiasilly.fne.common.component.CanisterComponent;
import com.elysiasilly.fne.common.component.FlamethrowerComponent;
import com.elysiasilly.fne.common.component.SkinTokenComponent;
import com.elysiasilly.fne.core.registry.FEComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FastColor;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import java.util.List;

public class FlamethrowerItem extends Item {

    public FlamethrowerItem() {
        super(new Item.Properties()
                .stacksTo(1)
                .component(FEComponents.FLAMETHROWER, FlamethrowerComponent.EMPTY)
        );
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean selected) {

        if(selected && entity instanceof Player player && player.isShiftKeyDown() && stack.has(FEComponents.FLAMETHROWER)) {
            FlamethrowerComponent component = stack.get(FEComponents.FLAMETHROWER);
            ItemStack canister = component.getCanisterAtIndex();

            if(!canister.isEmpty() && canister.has(FEComponents.CANISTER)) {
                if(CanisterComponent.use(canister)) {
                    canister.get(FEComponents.CANISTER).type().process(stack, canister, level, player);
                }
            }
        }
    }

    @Override
    public void onUseTick(Level level, LivingEntity livingEntity, ItemStack stack, int remainingUseDuration) {
        super.onUseTick(level, livingEntity, stack, remainingUseDuration);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        return super.use(level, player, usedHand);
    }

    @Override
    public boolean overrideStackedOnOther(ItemStack stack, Slot slot, ClickAction action, Player player) {

        if(stack.has(FEComponents.FLAMETHROWER) && action.equals(ClickAction.SECONDARY)) {
            FlamethrowerComponent component = stack.get(FEComponents.FLAMETHROWER);

            if(slot.hasItem()) {
                ItemStack clickedStack = slot.getItem();

                if(clickedStack.has(FEComponents.CANISTER) && component.canAdd()) {
                    FlamethrowerComponent.putCanister(stack, clickedStack.copyWithCount(1));
                    slot.remove(1);
                    return true;
                }
                if(clickedStack.has(FEComponents.SKIN_TOKEN)) {
                    stack.set(FEComponents.SKIN_TOKEN, clickedStack.get(FEComponents.SKIN_TOKEN));
                }

            } else if(component.canRemove()) {
                slot.safeInsert(component.getLast().copy());
                FlamethrowerComponent.removeCanister(stack);
                return true;
            }
        }

        return false;
    }

    @Override
    public Component getName(ItemStack stack) {
        if(stack.has(FEComponents.SKIN_TOKEN)) {
            return Component.translatable(this.getDescriptionId(stack)).withColor(stack.get(FEComponents.SKIN_TOKEN).rgba().abgr());
        } else {
            return super.getName(stack);
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {

        if(stack.has(FEComponents.FLAMETHROWER)) {
            FlamethrowerComponent component = stack.get(FEComponents.FLAMETHROWER);
            int selected = component.index();

            for(int i = 0; i < component.size(); i++) {
                tooltipComponents.add(Component.literal("Canister: " + (component.getCanisterAtIndex(i).isEmpty() ? "*" : component.getCanisterAtIndex(i))).withColor(selected == i ? DyeColor.WHITE.getTextColor() : DyeColor.GRAY.getTextColor()));
            }
        }

        if(stack.has(FEComponents.SKIN_TOKEN)) {
            SkinTokenComponent skinToken = stack.get(FEComponents.SKIN_TOKEN);

            String id = tooltipComponents.getFirst().copy().getString();
            String string = Component.translatable("misc.fne.flamethrower").getString().replaceAll("%1", id).replaceAll("%2", skinToken.translationKey().getString());

            tooltipComponents.set(0, Component.literal(string).withColor(skinToken.rgba().abgr()));
        }
    }
}
