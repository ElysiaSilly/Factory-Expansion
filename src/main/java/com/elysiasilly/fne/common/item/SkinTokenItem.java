package com.elysiasilly.fne.common.item;

import com.elysiasilly.babel.util.resource.RGBA;
import com.elysiasilly.fne.common.component.SkinTokenComponent;
import com.elysiasilly.fne.FactoExpa;
import com.elysiasilly.fne.core.registry.FEComponents;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;

import java.util.Map;
import java.util.UUID;

public class SkinTokenItem extends Item {

    private static final Map<UUID, SkinTokenComponent> GOOBER = Map.of(
            UUID.fromString("849ad69c-b812-43bf-9f35-12a25dfb4b5c"), new SkinTokenComponent("849ad69c-b812-43bf-9f35-12a25dfb4b5c", FactoExpa.location("starfleet"), new RGBA(248, 202, 97)), // Lake
            UUID.fromString("2c2c487e-4196-428d-9d73-a7cb3627a642"), new SkinTokenComponent("2c2c487e-4196-428d-9d73-a7cb3627a642", FactoExpa.location("silly"), RGBA.WHITE), // Me
            UUID.fromString("9b5c13f7-9107-49d0-b0af-7184b65798b9"), new SkinTokenComponent("9b5c13f7-9107-49d0-b0af-7184b65798b9", FactoExpa.location("aurora"), RGBA.WHITE) // Moonstone
    );

    public SkinTokenItem() {
        super(new Properties().stacksTo(1).rarity(Rarity.RARE));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack stack = player.getItemInHand(usedHand);

        if(!stack.has(FEComponents.SKIN_TOKEN)) {
            if(GOOBER.containsKey(player.getUUID()) || true) {
                stack.set(FEComponents.SKIN_TOKEN, GOOBER.get(UUID.fromString("849ad69c-b812-43bf-9f35-12a25dfb4b5c")));
                stack.set(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true);

                player.displayClientMessage(Component.translatable("misc.fne.skin_token_success"), true);
            } else {
                player.displayClientMessage(Component.translatable("misc.fne.skin_token_failure"), true);
            }
        }

        return InteractionResultHolder.success(stack);
    }

    @Override
    public Component getName(ItemStack stack) {
        if(stack.has(FEComponents.SKIN_TOKEN)) {
            SkinTokenComponent component = stack.get(FEComponents.SKIN_TOKEN);
            return Component.translatable(Component.translatable("misc.fne.skin_token").getString().replaceAll("%1", super.getName(stack).getString()).replaceAll("%2",component.translationKey().getString()));
        } else {
            return super.getName(stack);
        }
    }
}
