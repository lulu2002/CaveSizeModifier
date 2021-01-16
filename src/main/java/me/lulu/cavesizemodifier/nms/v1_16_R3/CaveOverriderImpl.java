package me.lulu.cavesizemodifier.nms.v1_16_R3;

import lombok.SneakyThrows;
import me.lulu.cavesizemodifier.nms.CaveOverrider;
import me.lulu.cavesizemodifier.utils.ReflectionUtils;
import net.minecraft.server.v1_16_R3.*;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;

import java.lang.reflect.Field;
import java.util.List;
import java.util.function.Supplier;

public class CaveOverriderImpl implements CaveOverrider {

    private Field field;

    @SneakyThrows
    public CaveOverriderImpl() {
        this.field = WorldGenCarverWrapper.class.getDeclaredField("d");
        this.field.setAccessible(true);
        ReflectionUtils.removeFinal(field);
    }

    @SneakyThrows
    public void override(World bukkitWorld) {
        IRegistryCustom registryCustom = (( CraftWorld ) bukkitWorld).getHandle().r();

        for (BiomeBase biomeBase : registryCustom.b(IRegistry.ay)) {
            override(biomeBase);
        }
    }

    private void override(BiomeBase biomeBase) {
        List<Supplier<WorldGenCarverWrapper<?>>> list = biomeBase.e().a(WorldGenStage.Features.AIR);

        for (Supplier<WorldGenCarverWrapper<?>> supplier : list) {
            WorldGenCarverWrapper<?> wrapper = supplier.get();
            overrideWrapperIfCave(wrapper);
        }
    }

    private void overrideWrapperIfCave(WorldGenCarverWrapper<?> wrapper) {
        WorldGenCarverAbstract carver = getCarver(wrapper);

        if (carver.getClass() == WorldGenCaves.class) {
            setCaveGenToOwnOne(wrapper);
        }
    }

    @SneakyThrows
    private void setCaveGenToOwnOne(WorldGenCarverWrapper<?> wrapper) {
        field.set(wrapper, new NewWorldGenCaves(WorldGenFeatureConfigurationChance.b, 256));
    }

    @SneakyThrows
    private WorldGenCarverAbstract getCarver(WorldGenCarverWrapper<?> wrapper) {

        return ( WorldGenCarverAbstract ) field.get(wrapper);
    }
}
