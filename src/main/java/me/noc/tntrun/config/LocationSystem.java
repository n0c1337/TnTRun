package me.noc.tntrun.config;

import cn.nukkit.level.Level;
import cn.nukkit.level.Location;
import cn.nukkit.utils.Config;
import me.noc.tntrun.TnTRun;

import java.io.File;

public class LocationSystem {

    private TnTRun plugin;
    private File file;
    private Config config;

    public LocationSystem(TnTRun plugin) {
        this.plugin = plugin;
        this.file = new File(this.plugin.getDataFolder(), "locations.yml");
        this.config = new Config(this.file, Config.YAML);
    }

    public void addLocation(String path, Location location) {
        this.config.set("tntrun." + path + ".World", location.getLevel().getFolderName());
        this.config.set("tntrun." + path + ".X", location.getX());
        this.config.set("tntrun." + path + ".Y", location.getY());
        this.config.set("tntrun." + path + ".Z", location.getZ());
        this.config.set("tntrun." + path + ".Yaw", location.getYaw());
        this.config.set("tntrun." + path + ".Pitch", location.getPitch());
        this.config.save(this.file);
    }

    public Location getLocation(String path) {
        Location location = null;
        Level level = this.plugin.getServer().getLevelByName(this.config.getString("tntrun." + path + ".World"));
        if (level != null) {
            double x = this.config.getDouble("tntrun." + path + ".X");
            double y = this.config.getDouble("tntrun." + path + ".Y");
            double z = this.config.getDouble("tntrun." + path + ".Z");
            double yaw = this.config.getDouble("tntrun." + path + ".Yaw");
            double pitch = this.config.getDouble("tntrun." + path + ".Pitch");
            location = new Location(x, y, z, yaw, pitch, level);
        }
        return location;
    }
}
