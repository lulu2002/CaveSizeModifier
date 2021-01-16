package me.lulu.cavesizemodifier;

import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldInitEvent;

public class WorldCreateListener implements Listener {
    private CaveSizeModifier plugin;

    public WorldCreateListener(CaveSizeModifier plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onWorldCreate(WorldInitEvent e) {
        if (e.getWorld().getEnvironment() == World.Environment.NORMAL) {
            plugin.getOverrider().override(e.getWorld());
        }
    }
}
