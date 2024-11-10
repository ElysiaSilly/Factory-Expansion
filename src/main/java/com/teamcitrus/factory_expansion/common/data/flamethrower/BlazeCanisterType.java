package com.teamcitrus.factory_expansion.common.data.flamethrower;

import com.teamcitrus.factory_expansion.common.data.flamethrower.canisterData.CanisterType;
import com.teamcitrus.factory_expansion.common.item.FlamethrowerItem;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;

public class BlazeCanisterType extends CanisterType {

    public BlazeCanisterType(Component name, ResourceLocation icon) {
        super(name, icon);
    }

    @Override
    public void process(FlamethrowerItem flamethrower, ItemStack canisterItem, Level level, Player player, Vec3 playerLookVector) {

        // TODO : horrid code

        level.addParticle(ParticleTypes.FLAME, player.position().x, player.position().y + 1.4, player.position().z, player.getLookAngle().x, player.getLookAngle().y, player.getLookAngle().z);
        level.addParticle(ParticleTypes.LARGE_SMOKE, player.position().x, player.position().y + 1.4, player.position().z, player.getLookAngle().x, player.getLookAngle().y, player.getLookAngle().z);

        List<ItemEntity> items = new ArrayList<>();

        Vec3 pos = Minecraft.getInstance().hitResult.getLocation();

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
    }
}
