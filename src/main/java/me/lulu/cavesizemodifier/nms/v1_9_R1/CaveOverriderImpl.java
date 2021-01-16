package me.lulu.cavesizemodifier.nms.v1_9_R1;

import lombok.SneakyThrows;
import me.lulu.cavesizemodifier.nms.CaveOverrider;
import net.minecraft.server.v1_9_R1.ChunkGenerator;
import net.minecraft.server.v1_9_R1.ChunkProviderGenerate;
import net.minecraft.server.v1_9_R1.WorldServer;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_9_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_9_R1.generator.NormalChunkGenerator;

import java.lang.reflect.Field;

public class CaveOverriderImpl implements CaveOverrider {

    @SneakyThrows
    public void override(World bukkitWorld) {
        WorldServer world = (( CraftWorld ) bukkitWorld).getHandle();
        ChunkGenerator chunkGenerator = world.getChunkProviderServer().chunkGenerator;

        if (chunkGenerator instanceof NormalChunkGenerator) {
            ChunkGenerator generatorProvider = getProvider(chunkGenerator);

            if (generatorProvider instanceof ChunkProviderGenerate) {
                modifyProvider(( ChunkProviderGenerate ) generatorProvider);
            }
        }
    }

    @SneakyThrows
    private void modifyProvider(ChunkProviderGenerate chunkProviderGenerate) {
        Field field = chunkProviderGenerate.getClass().getDeclaredField("v");
        field.setAccessible(true);
        field.set(chunkProviderGenerate, new NewWorldGenCaves());
    }

    @SneakyThrows
    private ChunkGenerator getProvider(ChunkGenerator chunkGenerator) {
        Field field = (( NormalChunkGenerator ) chunkGenerator).getClass().getDeclaredField("generator");

        field.setAccessible(true);

        return ( ChunkGenerator ) field.get(chunkGenerator);
    }
}
