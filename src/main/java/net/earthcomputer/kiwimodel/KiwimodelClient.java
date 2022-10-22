package net.earthcomputer.kiwimodel;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SpawnEggItem;

@Environment(EnvType.CLIENT)
public class KiwimodelClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ((SpawnEggItem) Items.CHICKEN_SPAWN_EGG).backgroundColor = 0x522e29;
        ((SpawnEggItem) Items.CHICKEN_SPAWN_EGG).highlightColor = 0x70bb29;
        EntityRenderers.register(EntityType.CHICKEN, KiwiRenderer::new);
    }
}
