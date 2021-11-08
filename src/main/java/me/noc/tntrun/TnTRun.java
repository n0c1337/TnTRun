package me.noc.tntrun;


import cn.nukkit.command.SimpleCommandMap;
import cn.nukkit.plugin.PluginBase;
import me.noc.tntrun.commands.CommandSetArena;
import me.noc.tntrun.config.ConfigSystem;
import me.noc.tntrun.config.LocationSystem;
import me.noc.tntrun.gamestates.GameStateManager;
import me.noc.tntrun.gamestates.LobbyState;

public final class TnTRun extends PluginBase {

    private ConfigSystem configSystem;
    private LocationSystem locationSystem;
    public static final String prefix = "[TnTRun]";
    private static TnTRun plugin;

    @Override
    public void onEnable() {
        plugin = this;
        this.configSystem = new ConfigSystem(this);
        this.configSystem.createDefaultConfig();

        this.locationSystem = new LocationSystem(this);

        GameStateManager gameStateManager = GameStateManager.getInstance();
        gameStateManager.setGameState(new LobbyState());

        registerCommands();

        this.getLogger().info("Starting TnTRun!");
    }

    @Override
    public void onDisable() {
        this.getLogger().info("Disabling TnTRun!");
    }

    private void registerCommands() {
        SimpleCommandMap commandMap = this.getServer().getCommandMap();
        commandMap.register("setarena", new CommandSetArena("setarena", "Set Location of an Arena", "/setArena name", this));
    }

    public ConfigSystem getConfigSystem() {
        return this.configSystem;
    }

    public LocationSystem getLocationSystem() {
        return this.locationSystem;
    }

    public static TnTRun getPlugin() {
        return plugin;
    }
}
