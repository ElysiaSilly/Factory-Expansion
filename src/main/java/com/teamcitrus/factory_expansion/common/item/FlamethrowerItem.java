package com.teamcitrus.factory_expansion.common.item;

import com.teamcitrus.factory_expansion.common.data.flamethrower.canisterData.CanisterComponent;
import com.teamcitrus.factory_expansion.common.data.flamethrower.canisterData.CanisterData;
import com.teamcitrus.factory_expansion.common.data.flamethrower.canisterData.FlamethrowerComponent;
import com.teamcitrus.factory_expansion.core.FactoExpa;
import com.teamcitrus.factory_expansion.core.registry.FEComponents;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.ItemCapability;
import net.neoforged.neoforge.items.IItemHandler;

import java.util.List;

public class FlamethrowerItem extends Item {
    public FlamethrowerItem(Properties properties) {
        super(properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {

        // todo : clean up

        if(!isSelected) return;

        if(!(entity instanceof Player player)) return;

        if(!(player.isShiftKeyDown())) return;

        //if(Minecraft.getInstance().hitResult == null) return;

        ItemStack itemStack = player.getInventory().getItem(1);

        Holder<Item> item = itemStack.getItemHolder();

        CanisterData data = item.getData(CanisterData.DATAMAP);
        if(data == null) return;

        data.getCanisterType().process(this, itemStack, level, player, player.getLookAngle());

        if(!itemStack.has(FEComponents.CANISTER)) {
            itemStack.set(FEComponents.CANISTER, new CanisterComponent(data.getCapacity(), data.getCapacity()));
        } else {
            if(itemStack.get(FEComponents.CANISTER).getCapacity() == -1) return;
            if(itemStack.get(FEComponents.CANISTER).used() > 1) {
                itemStack.set(FEComponents.CANISTER, (itemStack.get(FEComponents.CANISTER).decreaseUses()));
                player.displayClientMessage(Component.literal(String.valueOf(itemStack.get(FEComponents.CANISTER).used())), true);
            } else {
                player.getInventory().removeItem(1, 1);
            }
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
                    component.addCanister(item.copy());
                    item.shrink(1);
                } else {
                    component.addNozzle(item.copy());
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
