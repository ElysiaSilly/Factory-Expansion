package com.teamcitrus.factory_expansion.common.data.flamethrower.canisterData;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.teamcitrus.factory_expansion.core.FERegistries;
import com.teamcitrus.factory_expansion.core.FactoExpa;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.datamaps.DataMapType;

public class CanisterData {

    // i fucking hate data so much you cant believe the rage that courses through my veins

    public static final Codec<CanisterData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("type").forGetter(CanisterData -> CanisterData._type),
            Codec.INT.fieldOf("capacity").forGetter(CanisterData -> CanisterData.capacity),
            Codec.BOOL.fieldOf("break").forGetter(CanisterData -> CanisterData.breakWhenEmpty),
            Codec.STRING.fieldOf("model").forGetter((CanisterData -> CanisterData._modelPath))
    ).apply(instance, CanisterData::new));

    private final String _type;
    private final String _modelPath;

    private final CanisterType canisterType;
    private final int capacity;
    private final boolean breakWhenEmpty;
    private final ResourceLocation modelPath; // will be used for rendering the canister on the flamethrower

    private CanisterData(String type, int capacity, boolean breakWhenEmpty, String modelPath) {
        this._type = type;
        this._modelPath = modelPath;

        this.capacity =  capacity;
        this.canisterType = FERegistries.CANISTER_TYPE.get(ResourceLocation.parse(type));
        this.breakWhenEmpty = breakWhenEmpty;
        this.modelPath = ResourceLocation.parse(modelPath);

        // TODO : datamaps apparently just fail completely if one entry is borked |:
        // TODO : look into fixing this somehow
    }

    public CanisterType getCanisterType() {
        return this.canisterType;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public boolean breakWhenEmpty() {
        return this.breakWhenEmpty;
    }

    public ResourceLocation getModelPath() {
        return this.modelPath;
    }

    public static final DataMapType<Item, CanisterData> DATAMAP = DataMapType.builder(
            ResourceLocation.fromNamespaceAndPath(FactoExpa.MODID, "canister_definitions"),
            Registries.ITEM,
            CODEC
    ).build();
}
