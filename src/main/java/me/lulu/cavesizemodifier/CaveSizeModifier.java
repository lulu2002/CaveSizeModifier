package me.lulu.cavesizemodifier;

import lombok.Getter;
import lombok.SneakyThrows;
import me.lulu.cavesizemodifier.nms.CaveOverrider;
import me.lulu.cavesizemodifier.nms.v1_8_R3.CaveOverriderImpl;
import me.lulu.cavesizemodifier.settings.ConfigLoader;
import me.lulu.cavesizemodifier.settings.Settings;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class CaveSizeModifier extends JavaPlugin {

    @Getter
    private static CaveSizeModifier plugin;
    @Getter
    private CaveOverrider overrider;

    @Override
    public void onLoad() {
        plugin = this;

        new ConfigLoader(Settings.class);
        overrider = getOverrideByNmsVersion();
        overrider.override();
    }

    @Override
    public void onEnable() {
        registerEvents(new WorldCreateListener(this));
    }


    private void registerEvents(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, this);
    }

    @SneakyThrows
    private CaveOverrider getOverrideByNmsVersion() {
        String name = CaveOverriderImpl.class.getName()
                .replace("v1_8_R3", getNmsVersion());
        return ( CaveOverrider ) Class.forName(name).newInstance();
    }

    private String getNmsVersion() {
        String packageName = getServer().getClass().getPackage().getName();

        return packageName.substring(packageName.lastIndexOf('.') + 1);
    }
}
