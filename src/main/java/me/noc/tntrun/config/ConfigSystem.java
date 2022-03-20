package me.noc.tntrun.config;

import cn.nukkit.utils.Config;
import me.noc.tntrun.TnTRun;

import java.io.File;

public class ConfigSystem {

    private TnTRun plugin;
    private File file;
    private Config config;

    public ConfigSystem(TnTRun plugin) {
        this.plugin = plugin;
        this.file = new File(this.plugin.getDataFolder(), "languages.yml");
        this.config = new Config(this.file, Config.YAML);
    }

    public void createDefaultConfig() {
        this.addDefault("tntrun.prefix", "TnTRun");
        this.addDefault("tntrun.locationset", "Location has been set successfully!");
        this.addDefault("tntrun.time", 1);
        this.addDefault("tntrun.message.start", "Game starts in");
    }

    public String getPrefix() {
        return this.config.getString("tntrun.prefix");
    }

    public String getLocationSet() {
        return this.config.getString("tntrun.locationset");
    }

    public String getStartMessage() {
        return this.config.getString("tntrun.message.start");
    }

    public int getTime() {
        return this.config.getInt("tntrun.time");
    }

    public void addDefault(String path, Object object) {
        if (!this.config.exists(path)) {
            this.config.set(path, object);
            this.config.save(this.file);
        }
    }

}
