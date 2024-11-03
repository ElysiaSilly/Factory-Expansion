package com.teamcitrus.factory_expansion.common.item;

import com.teamcitrus.factory_expansion.common.flamethrower.canisterData.CanisterComponent;
import com.teamcitrus.factory_expansion.common.flamethrower.canisterData.CanisterData;
import com.teamcitrus.factory_expansion.core.registry.FEComponents;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class FlamethrowerItem extends Item {

    public FlamethrowerItem(Properties properties) {
        super(properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {

        if(!isSelected) return;

        if(!(entity instanceof Player player)) return;

        if(!(player.isShiftKeyDown())) return;

        if(Minecraft.getInstance().hitResult == null) return;

        //level.registryAccess().registryOrThrow(Registries.ITEM).getDataMap(CanisterData.getDataMap()).keySet().forEach(key ->
        //        System.out.println(key.registry())
        //);

        // TODO : horrid code

        ItemStack itemStack = player.getInventory().getItem(1);

        Holder<Item> item = itemStack.getItemHolder();

        CanisterData data = item.getData(CanisterData.DATAMAP);

        if(data == null) return;

        data.getCanisterType().process(getDefaultInstance(), itemStack, level, player, player.getLookAngle());

        if(!itemStack.has(FEComponents.CANISTER)) {
            itemStack.set(FEComponents.CANISTER, new CanisterComponent(data.getCapacity(), data.getCapacity()));
        } else {
            if((itemStack.get(FEComponents.CANISTER).used()) > 1) {
                itemStack.set(FEComponents.CANISTER, (itemStack.get(FEComponents.CANISTER).decreaseUses()));
                player.displayClientMessage(Component.literal(String.valueOf(itemStack.get(FEComponents.CANISTER).used())), true);
            } else {
                player.getInventory().removeItem(1, 1);
            }
        }
    }
}
