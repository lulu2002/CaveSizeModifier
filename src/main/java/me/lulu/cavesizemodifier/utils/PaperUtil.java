package me.lulu.cavesizemodifier.utils;

import co.aikar.timings.TimedChunkGenerator;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PaperUtil {
    public boolean isPaper() {
        try {
            Class.forName("co.aikar.timings.Timing");
            return true;
        } catch (Throwable var1) {
            return false;
        }
    }

    public static <T> T getPaperChunkGenerator(Object chunkGenerator) {
        TimedChunkGenerator timedChunkGenerator = ( TimedChunkGenerator ) chunkGenerator;
        return ReflectionUtils.getFieldContent(timedChunkGenerator.getClass(), "timedGenerator", timedChunkGenerator);
    }
}
