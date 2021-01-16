package me.lulu.cavesizemodifier.utils;

import lombok.experimental.UtilityClass;
import me.lulu.cavesizemodifier.settings.Settings;

import java.util.Random;

@UtilityClass
public class Randoms {
    public boolean randomCaveRate(Random random) {
        return random.nextInt(100) < Settings.Cave.GENERATE_RATE;
    }

    public boolean randomLargeRate(Random random) {
        return random.nextInt(100) < Settings.Cave.LARGE_CAVE_GENERATE_RATE;
    }

    public int getGenerateY(Random random) {
        return random.nextInt(random.nextInt(Settings.Cave.MAX_ALTITUDE) + Settings.Cave.MIN_ALTITUDE);
    }
}
