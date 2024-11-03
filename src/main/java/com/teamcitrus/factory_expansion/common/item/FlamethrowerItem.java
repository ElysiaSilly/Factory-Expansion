package com.teamcitrus.factory_expansion.common.item;

import com.teamcitrus.factory_expansion.common.flamethrower.canisterData.CanisterComponent;
import com.teamcitrus.factory_expansion.common.flamethrower.canisterData.CanisterData;
import com.teamcitrus.factory_expansion.core.FactoExpa;
import com.teamcitrus.factory_expansion.core.registry.FEComponents;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.capabilities.ItemCapability;
import net.neoforged.neoforge.items.IItemHandler;

import java.util.ArrayList;
import java.util.List;

public class FlamethrowerItem extends Item {
    public FlamethrowerItem(Properties properties) {
        super(properties);
    }

    /// inventory
    private final int SIZE = 3, SLOT_A = 0, SLOT_B = 1, SLOT_C = 2;
    private static final String INV_NAME = "flamethrower";
    private static final ItemCapability<IItemHandler, Void> ITEM_HANDLER =
            ItemCapability.createVoid(
                    ResourceLocation.fromNamespaceAndPath(FactoExpa.MODID, INV_NAME),
                    IItemHandler.class
            );

    public ItemCapability<IItemHandler, Void> getItemHandler() { return ITEM_HANDLER; }

    private final List<ItemEntity> items = new ArrayList<>();
    
    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {

        if(!isSelected) return;

        if(!(entity instanceof Player player)) return;

        if(!(player.isShiftKeyDown())) return;

        if(Minecraft.getInstance().hitResult == null) return;

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


    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Player player = context.getPlayer();
        InteractionHand usedHand = context.getHand();
        ItemStack mainStack = player.getMainHandItem();
        ItemStack offStack = player.getOffhandItem();

        if(!level.isClientSide()) return InteractionResult.PASS;
        if(!(usedHand == InteractionHand.MAIN_HAND)) return InteractionResult.PASS;
        if(!(mainStack.getItem() instanceof FlamethrowerItem)) return InteractionResult.PASS;



        IItemHandler handler = mainStack.getCapability(ITEM_HANDLER);
        if(handler != null) {

            handler.insertItem(0, offStack, true);

            FactoExpa.LOGGER.info("SLOT A: " + handler.getStackInSlot(0).getItem().toString());

            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }
}
