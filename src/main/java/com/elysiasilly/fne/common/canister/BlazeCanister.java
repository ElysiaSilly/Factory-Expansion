package com.elysiasilly.fne.common.canister;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3d;

import java.util.ArrayList;
import java.util.List;

public class BlazeCanister extends Canister {

    @Override
    public void tick(ItemStack flamethrower, ItemStack canister, Level level, Player player, List<Vector3d> raycast) {

        Vec3 result = Minecraft.getInstance().hitResult.getLocation();

        Vec3 particleOrigin = player.getRopeHoldPosition(Minecraft.getInstance().getFps());

        Vec3 origin = particleOrigin.subtract(player.getLookAngle());

        Vec3 direction = result.subtract(origin).normalize();

        level.addParticle(ParticleTypes.FLAME, origin.x, origin.y, origin.z, direction.x, direction.y, direction.z);
        level.addParticle(ParticleTypes.LARGE_SMOKE, origin.x, origin.y, origin.z, direction.x, direction.y, direction.z);
    }

    @Override
    public void blockBurn(ItemStack flamethrower, ItemStack canister, Level level, Player player, BlockState state, BlockPos pos) {
        if(level.getGameRules().getBoolean(GameRules.RULE_DOFIRETICK) && !state.isAir()) {

        }
    }

    @Override
    public void entityBurn(ItemStack flamethrower, ItemStack canister, Level level, Player player, Entity entity) {
        entity.igniteForSeconds(10);
        level.addParticle(ParticleTypes.LAVA, entity.getX(), entity.getY() + 0.3,entity.getZ(), 0, 0.01, 0);
    }

    @Override
    public void itemBurn(ItemStack flamethrower, ItemStack canister, Level level, Player player, List<ItemEntity> items) {

        /*
        for(ItemEntity item : items) {
            ItemStack stack = item.getItem();

            stack.getBurnTime(RecipeType.SMELTING);


            RecipeManager.CachedCheck<SingleRecipeInput, ? extends AbstractCookingRecipe> quickCheck = RecipeManager.createCheck(RecipeType.SMELTING);
            SingleRecipeInput recipe = new SingleRecipeInput(item.getItem());

            if(quickCheck.getRecipeFor(recipe, level).isPresent()) {

                level.addParticle(ParticleTypes.LAVA, item.getX(), item.getY() + 0.3, item.getZ(), 0, 0.01, 0);

                ItemStack output = quickCheck.getRecipeFor(recipe, level).get().value().assemble(recipe, level.registryAccess());

                ItemEntity newItem = new ItemEntity(level, item.getX(), item.getY(), item.getZ(), output.copy());
                level.addFreshEntity(newItem);

                if (firstItem.getItem().getCount() > 1) {
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

         */
    }
}
