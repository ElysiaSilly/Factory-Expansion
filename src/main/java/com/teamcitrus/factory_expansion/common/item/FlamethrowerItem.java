package com.teamcitrus.factory_expansion.common.item;

import com.teamcitrus.factory_expansion.common.data.flamethrower.CanisterComponent;
import com.teamcitrus.factory_expansion.common.data.flamethrower.CanisterData;
import com.teamcitrus.factory_expansion.common.data.flamethrower.FlamethrowerComponent;
import com.teamcitrus.factory_expansion.core.registry.FEComponents;
import com.teamcitrus.factory_expansion.core.util.RegistryUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
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
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {

        // todo : clean up

        if(!isSelected) return;

        if(!(entity instanceof Player player)) return;

        if(!(player.isShiftKeyDown())) return;
        ItemStack potentialCanister = player.getInventory().getItem(1);

        if(!level.isClientSide) {

            if(!potentialCanister.has(FEComponents.CANISTER)) {
                CanisterData data = RegistryUtils.getCanisterData(level, potentialCanister.getItem());
                if(data != null) {
                    potentialCanister.set(FEComponents.CANISTER, data.createComponent());
                }
            }
        }

        if(potentialCanister.has(FEComponents.CANISTER)) {
            CanisterComponent component = potentialCanister.get(FEComponents.CANISTER);

            component.getType().process(stack, potentialCanister, level, player, player.getLookAngle());
            boolean destroy = component.decreaseUses();
            if(destroy) potentialCanister.shrink(1);
            System.out.println(component);
        }
    }

    @Override
    public boolean overrideStackedOnOther(ItemStack stack, Slot slot, ClickAction action, Player player) {


        System.out.println(stack.has(FEComponents.FLAMETHROWER));

        FlamethrowerComponent component = stack.get(FEComponents.FLAMETHROWER);

        if(action == ClickAction.SECONDARY) {

            ItemStack item = slot.getItem();

            if(slot.hasItem()) {

                if(item.has(FEComponents.CANISTER)) {
                    ItemStack copy = item.copy();
                    copy.setCount(1);
                    component.addCanister(copy);
                    item.shrink(1);
                } else {
                    ItemStack copy = item.copy();
                    copy.setCount(1);
                    component.addNozzle(copy);
                    item.shrink(1);
                }
                return true;

            } else {
                if(!component.getNozzle().isEmpty()) {
                    slot.safeInsert(component.removeNozzle());
                } else {
                    slot.safeInsert(component.removeLastCanister());
                }
                return true;

            }
        }

        return false;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        FlamethrowerComponent component = stack.get(FEComponents.FLAMETHROWER);

        if(!component.getNozzle().isEmpty()) { tooltipComponents.add(Component.literal("nozzle: " + component.getNozzle())); } else { tooltipComponents.add(Component.literal("nozzle: *")); }
        if(!component.getCanisterAtIndex(0).isEmpty()) { tooltipComponents.add(Component.literal("canister: " + component.getCanisterAtIndex(0))); } else { tooltipComponents.add(Component.literal("canister: *")); }
        if(!component.getCanisterAtIndex(1).isEmpty()) { tooltipComponents.add(Component.literal("canister: " + component.getCanisterAtIndex(1))); } else { tooltipComponents.add(Component.literal("canister: *")); }
        if(!component.getCanisterAtIndex(2).isEmpty()) { tooltipComponents.add(Component.literal("canister: " + component.getCanisterAtIndex(2))); } else { tooltipComponents.add(Component.literal("canister: *")); }

    }
}
