package net.earthcomputer.kiwimodel.mixin;

import com.google.common.collect.Iterators;
import net.minecraft.client.resources.language.ClientLanguage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Slice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

@Mixin(ClientLanguage.class)
public class ClientLanguageMixin {
    @ModifyVariable(
        method = "loadFrom",
        at = @At(value = "STORE", ordinal = 0),
        ordinal = 1,
        slice = @Slice(from = @At(value = "INVOKE", target = "Lnet/minecraft/server/packs/resources/ResourceManager;getNamespaces()Ljava/util/Set;")))
    private static Iterator<String> sortNamespaces(Iterator<String> itr) {
        String[] namespaces = Iterators.toArray(itr, String.class);
        Arrays.sort(namespaces, Comparator.comparingInt(ns -> "kiwimodel".equals(ns) ? 1 : 0));
        return Iterators.forArray(namespaces);
    }
}
