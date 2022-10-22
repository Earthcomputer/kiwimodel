package net.earthcomputer.kiwimodel.mixin;

import com.google.common.collect.ImmutableMap;
import net.earthcomputer.kiwimodel.KiwiModel;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(LayerDefinitions.class)
public class LayerDefinitionsMixin {
    @ModifyVariable(method = "createRoots", at = @At(value = "STORE", ordinal = 0), ordinal = 0)
    private static ImmutableMap.Builder<ModelLayerLocation, LayerDefinition> addToBuilder(ImmutableMap.Builder<ModelLayerLocation, LayerDefinition> builder) {
        return builder.put(KiwiModel.LAYER_LOCATION, KiwiModel.createBodyLayer());
    }
}
