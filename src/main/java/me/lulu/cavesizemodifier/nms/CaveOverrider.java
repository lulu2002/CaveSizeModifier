package me.lulu.cavesizemodifier.nms;

import org.bukkit.Bukkit;
import org.bukkit.World;

public interface CaveOverrider {

    void override(World bukkitWorld);

    default void override() {
        for (World world : Bukkit.getWorlds()) {
            override(world);
        }
    }
}
