package me.lulu.cavesizemodifier.settings;


@Config(file = "settings.yml")
public class Settings {

    private static ConfigFile configFile;

    public static class Cave {
        public static Integer GENERATE_RATE = 15;
        public static Integer LARGE_CAVE_GENERATE_RATE = 25;
        public static Integer MIN_ALTITUDE = 8;
        public static Integer MAX_ALTITUDE = 120;
    }
}
