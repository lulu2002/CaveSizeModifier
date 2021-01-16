package me.lulu.cavesizemodifier.nms.v1_16_R1;

import lombok.SneakyThrows;
import me.lulu.cavesizemodifier.nms.CaveOverrider;
import me.lulu.cavesizemodifier.utils.ReflectionUtils;
import net.minecraft.server.v1_16_R1.*;
import org.bukkit.World;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CaveOverriderImpl implements CaveOverrider {

    private static final String CARVER_ABSTRACT_FIELD_NAME = "b";
    private static final String MAP_FIELD_NAME = "q";

    private Field field;

    @SneakyThrows
    public CaveOverriderImpl() {
        this.field = BiomeBase.class.getDeclaredField(MAP_FIELD_NAME);
        this.field.setAccessible(true);
    }

    @SneakyThrows
    public void override(World bukkitWorld) {
    }

    @SneakyThrows
    @Override
    public void override() {
        for (BiomeBase biomeBase : IRegistry.BIOME) {
            getMap(biomeBase).values().stream()
                    .flatMap(Collection::stream)
                    .forEach(this::setNewCaveGen);
        }
    }

    private void setNewCaveGen(WorldGenCarverWrapper<?> wrapper) {
        WorldGenCarverAbstract genCarverAbstract = ReflectionUtils.getFieldContent(wrapper.getClass(), CARVER_ABSTRACT_FIELD_NAME, wrapper);

        if (genCarverAbstract instanceof WorldGenCaves) {
            ReflectionUtils.setDeclaredField(wrapper, CARVER_ABSTRACT_FIELD_NAME, new NewWorldGenCaves(WorldGenFeatureConfigurationChance.b, 256));
        }
    }

    @SneakyThrows
    private Map<WorldGenStage.Features, List<WorldGenCarverWrapper<?>>> getMap(BiomeBase biomeBase) {
        return ( Map<WorldGenStage.Features, List<WorldGenCarverWrapper<?>>> ) field.get(biomeBase);
    }
}
