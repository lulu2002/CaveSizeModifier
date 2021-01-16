package me.lulu.cavesizemodifier.nms.v1_13_R1;

import lombok.SneakyThrows;
import me.lulu.cavesizemodifier.nms.CaveOverrider;
import me.lulu.cavesizemodifier.utils.ReflectionUtils;
import net.minecraft.server.v1_13_R1.*;
import org.bukkit.World;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CaveOverriderImpl implements CaveOverrider {

    private static final String CARVER_ABSTRACT_FIELD_NAME = "a";
    private static final String MAP_FIELD_NAME = "aW";

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
        for (BiomeBase biomeBase : BiomeBase.aG) {
            getMap(biomeBase).values().stream()
                    .flatMap(Collection::stream)
                    .forEach(this::setNewCaveGen);
        }
    }

    private void setNewCaveGen(WorldGenCarverWrapper<?> wrapper) {
        WorldGenCarverAbstract genCarverAbstract = ReflectionUtils.getFieldContent(wrapper.getClass(), CARVER_ABSTRACT_FIELD_NAME, wrapper);

        if (genCarverAbstract instanceof WorldGenCaves) {
            ReflectionUtils.setDeclaredField(wrapper, CARVER_ABSTRACT_FIELD_NAME, new NewWorldGenCaves());
        }
    }

    @SneakyThrows
    private Map<WorldGenStage.Features, List<WorldGenCarverWrapper<?>>> getMap(BiomeBase biomeBase) {
        return ( Map<WorldGenStage.Features, List<WorldGenCarverWrapper<?>>> ) field.get(biomeBase);
    }
}
