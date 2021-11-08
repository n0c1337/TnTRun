package me.noc.tntrun.gamestates;

import cn.nukkit.event.HandlerList;
import cn.nukkit.plugin.PluginManager;
import me.noc.tntrun.TnTRun;
import me.noc.tntrun.listener.LobbyStateListener;
import me.noc.tntrun.utils.Settings;
import me.noc.tntrun.utils.Timer;
import me.noc.tntrun.utils.TimerListener;

public class LobbyState implements GameState {

    private Timer timer;

    @Override
    public void start() {
        PluginManager pluginManager = TnTRun.getPlugin().getServer().getPluginManager();
        pluginManager.registerEvents(new LobbyStateListener(), TnTRun.getPlugin());
        startTimer();
    }

    @Override
    public void stop() {
        HandlerList.unregisterAll(new LobbyStateListener());
    }

    public void startTimer() {
        timer = new Timer(new TimerListener() {
            @Override
            public void onTick(int time) {
                switch (time) {
                    case 60:
                    case 50:
                    case 40:
                    case 30:
                    case 20:
                    case 10:
                    case 5:
                    case 3:
                    case 2:
                    case 1:
                        TnTRun.getPlugin().getServer().broadcastMessage(TnTRun.getPlugin().getConfigSystem().getPrefix().replace("&", "§") + TnTRun.getPlugin().getConfigSystem().getStartMessage() + time);
                        if (time == 1) GameStateManager.getInstance().setGameState(new PlayState());
                }
            }

            @Override
            public void onPause(int time) {

            }

            @Override
            public void onResume(int time) {

            }

            @Override
            public void onStop() {

            }
        }, 60);
    }

    public Timer getTimer() {
        return timer;
    }

    public void recalculateTime() {
        int players = TnTRun.getPlugin().getServer().getOnlinePlayers().size();
        boolean paused = timer.isPaused();

        if (paused && players == 2) {
            timer.setTime(60);
            timer.setPaused(false);
            return;
        } else if (paused  && players < 2) {
            timer.setPaused(true);
            TnTRun.getPlugin().getServer().broadcastMessage(TnTRun.getPlugin().getConfigSystem().getPrefix() + " Countdown wurde abgebrochen!");
            return;
        }
        skipTime(timer.getTime(), players);
    }

    public void skipTime(int time, int players) {
        if (Settings.SKIP_TIME_3 >= players && !(time < 20)) {
            timer.setTime(20);
        } else if (Settings.SKIP_TIME_2 >= players && !(time < 30)) {
            timer.setTime(30);
        }
    }
}
