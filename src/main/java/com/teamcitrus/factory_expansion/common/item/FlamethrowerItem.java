package com.teamcitrus.factory_expansion.common.item;

import com.teamcitrus.factory_expansion.common.flamethrower.itemCanisterData.CanisterData;
import com.teamcitrus.factory_expansion.core.FactoExpa;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
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

        CanisterData data = item.getData(CanisterData.getDataMap());

        if(data != null) item.getData(CanisterData.getDataMap()).getCanisterType().process(getDefaultInstance(), itemStack, level, player, player.getLookAngle());

        /*
        level.addParticle(ParticleTypes.FLAME, player.position().x, player.position().y + 1.4, player.position().z, player.getLookAngle().x, player.getLookAngle().y, player.getLookAngle().z);
        level.addParticle(ParticleTypes.LARGE_SMOKE, player.position().x, player.position().y + 1.4, player.position().z, player.getLookAngle().x, player.getLookAngle().y, player.getLookAngle().z);

        //level.addParticle(ParticleTypes.FLAME, player.position().x, player.position().y + 0.3, player.position().z, 0, 2, 0);


        Vec3 pos = Minecraft.getInstance().hitResult.getLocation();

        items.clear();

        for(Entity resultEntity : level.getEntities(null, new AABB(pos, pos).inflate(0.5))) {

            if(resultEntity instanceof ItemEntity itemEntity) {
                items.add(itemEntity);
            } else {
                resultEntity.igniteForSeconds(10);
                //resultEntity.hurt(DamageTypes.ON_FIRE.)
                Vec3 entityPos = resultEntity.position();
                level.addParticle(ParticleTypes.LAVA, entityPos.x, entityPos.y + 0.3, entityPos.z, 0, 0.01, 0);

            }

        }

        if(items.isEmpty()) return;

        ItemEntity firstItem = items.getFirst();
        Vec3 firstItemPos = firstItem.position();

        RecipeManager.CachedCheck<SingleRecipeInput, ? extends AbstractCookingRecipe> quickCheck = RecipeManager.createCheck(RecipeType.SMELTING);

        SingleRecipeInput recipe = new SingleRecipeInput(firstItem.getItem());

        if(quickCheck.getRecipeFor(recipe, level).isPresent()) {

            level.addParticle(ParticleTypes.LAVA, firstItemPos.x, firstItemPos.y + 0.3, firstItemPos.z, 0, 0.01, 0);

            ItemStack output = quickCheck.getRecipeFor(recipe, level).get().value().getResultItem(level.registryAccess());

            ItemEntity newItem = new ItemEntity(level, firstItem.position().x, firstItem.position().y, firstItem.position().z, new ItemStack(output.getItem()));
            level.addFreshEntity(newItem);

            if(firstItem.getItem().getCount() > 1) {
                firstItem.setItem(firstItem.getItem().split(firstItem.getItem().getCount() - 1));
            } else {
                firstItem.discard();
            }

            //quickCheck.getRecipeFor(recipe, level).get().value().

            items.remove(firstItem);
        } else {
            level.addParticle(ParticleTypes.SMOKE, firstItemPos.x, firstItemPos.y + 0.3, firstItemPos.z, 0, 0.01, 0);

        }

        /*
        if(firstItem.getItem().is(Items.IRON_ORE)) {

            ItemEntity newItem = new ItemEntity(level, firstItem.position().x, firstItem.position().y, firstItem.position().z, new ItemStack(Items.IRON_INGOT));

            level.addFreshEntity(newItem);

            if(firstItem.getItem().getCount() > 1) {
                firstItem.setItem(firstItem.getItem().split(firstItem.getItem().getCount() - 1));
            } else {
                firstItem.discard();
            }

            items.remove(firstItem);
        }

         */
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
