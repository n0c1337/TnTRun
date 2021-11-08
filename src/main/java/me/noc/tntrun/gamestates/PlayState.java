package me.noc.tntrun.gamestates;

import cn.nukkit.event.HandlerList;
import cn.nukkit.scheduler.NukkitRunnable;
import me.noc.tntrun.TnTRun;
import me.noc.tntrun.listener.LobbyStateListener;

public class PlayState implements GameState{

    private NukkitRunnable runnable;

    @Override
    public void start() {
        TnTRun.getPlugin().getServer().broadcastMessage("Play State");
        // PluginManager pluginManager = TnTRun.getPlugin().getServer().getPluginManager();
        // pluginManager.registerEvents(new LobbyStateListener(), TnTRun.getPlugin());
    }

    @Override
    public void stop() {
        HandlerList.unregisterAll(new LobbyStateListener());
    }
}
