package net.earthcomputer.kiwimodel;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Chicken;

public class KiwiRenderer extends MobRenderer<Chicken, KiwiModel<Chicken>> {
    private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation("kiwimodel", "textures/entity/kiwi.png");

    public KiwiRenderer(EntityRendererProvider.Context context) {
        super(context, new KiwiModel<>(context.bakeLayer(KiwiModel.LAYER_LOCATION)), 0.3f);
    }

    @Override
    public ResourceLocation getTextureLocation(Chicken entity) {
        return TEXTURE_LOCATION;
    }
}
