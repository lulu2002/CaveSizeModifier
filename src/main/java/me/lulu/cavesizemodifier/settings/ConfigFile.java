package me.lulu.cavesizemodifier.settings;

import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

public class ConfigFile extends YamlConfiguration {

    public Integer getInteger(@NotNull String path) {
        return super.getInt(path);
    }
}
