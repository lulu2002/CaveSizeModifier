package me.lulu.cavesizemodifier.nms;

import org.bukkit.World;

public interface CaveOverrider {

    void override(World bukkitWorld);

    default void override() {

    }
}
