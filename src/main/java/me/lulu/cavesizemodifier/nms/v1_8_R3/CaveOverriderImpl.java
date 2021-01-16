package me.lulu.cavesizemodifier.nms.v1_8_R3;

import lombok.SneakyThrows;
import me.lulu.cavesizemodifier.nms.CaveOverrider;
import net.minecraft.server.v1_8_R3.ChunkProviderGenerate;
import net.minecraft.server.v1_8_R3.IChunkProvider;
import net.minecraft.server.v1_8_R3.WorldServer;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.generator.NormalChunkGenerator;

import java.lang.reflect.Field;

public class CaveOverriderImpl implements CaveOverrider {

    @SneakyThrows
    public void override(World bukkitWorld) {
        WorldServer world = (( CraftWorld ) bukkitWorld).getHandle();
        IChunkProvider chunkGenerator = world.chunkProviderServer.chunkProvider;

        if (chunkGenerator instanceof NormalChunkGenerator) {
            IChunkProvider generatorProvider = getProvider(( NormalChunkGenerator ) chunkGenerator);

            if (generatorProvider instanceof ChunkProviderGenerate) {
                modifyProvider(( ChunkProviderGenerate ) generatorProvider);
            }
        }
    }

    @SneakyThrows
    private void modifyProvider(ChunkProviderGenerate chunkProviderGenerate) {
        Field field = chunkProviderGenerate.getClass().getDeclaredField("u");
        field.setAccessible(true);
        field.set(chunkProviderGenerate, new NewWorldGenCaves());
    }

    @SneakyThrows
    private IChunkProvider getProvider(NormalChunkGenerator chunkGenerator) {
        Field field = chunkGenerator.getClass().getDeclaredField("provider");

        field.setAccessible(true);

        return ( IChunkProvider ) field.get(chunkGenerator);
    }
}
