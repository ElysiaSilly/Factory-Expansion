package com.teamcitrus.factory_expansion.common.item;

import com.teamcitrus.factory_expansion.common.flamethrower.canisterData.CanisterComponent;
import com.teamcitrus.factory_expansion.common.flamethrower.canisterData.CanisterData;
import com.teamcitrus.factory_expansion.core.FactoExpa;
import com.teamcitrus.factory_expansion.core.registry.FEComponents;
import net.minecraft.advancements.critereon.ItemContainerPredicate;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BundleContents;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.ItemCapability;
import net.neoforged.neoforge.items.IItemHandler;

import java.util.ArrayList;
import java.util.List;

public class FlamethrowerItem extends Item {
    public FlamethrowerItem(Properties properties) {
        super(properties);
    }

    /// inventory
    private final int SIZE = 3, CAN_A = 0, CAN_B = 1, CAN_C = 2;
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
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack mainStack = player.getMainHandItem();

        if(!level.isClientSide()) return super.use(level, player, usedHand);
        if(!(usedHand == InteractionHand.MAIN_HAND)) return super.use(level, player, usedHand);
        if(!(mainStack.getItem() instanceof FlamethrowerItem)) return super.use(level, player, usedHand);

        /// get the Component ( I added the default during item registration, no need for getOrDefault)
        // this works but... // ItemContainerContents contents = mainStack.get(DataComponents.CONTAINER);
        // this works but... // if(contents != null) {
        // this works but... //     var list = new ArrayList<ItemStack>();
        // this works but... //     list.add(player.getOffhandItem());
        // this works but... //     var newComp = ItemContainerContents.fromItems(list);
        // this works but... //     mainStack.set(DataComponents.CONTAINER, newComp);
        // this works but... // }
        // this works but... // /// re-fetching stuff
        // this works but... // contents = mainStack.get(DataComponents.CONTAINER);
        // this works but... // FactoExpa.LOGGER.info("info: " + contents.getSlots());

        IItemHandler itemHandler = mainStack.getCapability(Capabilities.ItemHandler.ITEM);
        if(itemHandler != null) {
            var itemA = itemHandler.getStackInSlot(CAN_A);
            if(itemA.isEmpty()) {
                itemHandler.insertItem(CAN_A, player.getOffhandItem(), false);
                player.setItemSlot(EquipmentSlot.OFFHAND, ItemStack.EMPTY);
            }
            else {
                // player.setItemSlot(EquipmentSlot.OFFHAND, itemA);
                var item = itemHandler.extractItem(CAN_A, 1/*this might be size?*/, false);
                player.setItemSlot(EquipmentSlot.OFFHAND, item);
            }
        }
        return super.use(level, player, usedHand); // change this
    }
}
