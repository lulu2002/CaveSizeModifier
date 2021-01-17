package me.lulu.cavesizemodifier.settings;

import me.lulu.cavesizemodifier.CaveSizeModifier;
import org.bukkit.configuration.InvalidConfigurationException;

import java.io.File;
import java.io.IOException;

public class ConfigLoader {
    private Class configClass;
    private File file;
    private ConfigFile configFile;
    private Config annotation;

    public ConfigLoader(Class configClass) {
        this.configClass = configClass;
        this.annotation = ( Config ) configClass.getAnnotation(Config.class);

        loadConfigFile();
        new ValuesLoader(configFile, configClass);
        save();
    }

    private void loadConfigFile() {
        ConfigFile configFile = new ConfigFile();

        try {
            this.file = getFile();
            configFile.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }

        this.configFile = configFile;
    }

    private void save() {
        try {
            this.configFile.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File getFile() throws IOException {
        File folder = CaveSizeModifier.getPlugin().getDataFolder();

        if (!folder.exists())
            folder.mkdir();

        File file = new File(folder, annotation.file());

        if (!file.exists())
            file.createNewFile();

        return file;
    }

}
